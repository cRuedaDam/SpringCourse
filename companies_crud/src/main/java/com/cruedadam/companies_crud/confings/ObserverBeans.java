package com.cruedadam.companies_crud.confings;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.*;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.LogRecordProcessor;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.semconv.ResourceAttributes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration(proxyBeanMethods = false)
public class ObserverBeans {

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    public SdkLoggerProvider sdkLoggerProvider(Environment env, ObjectProvider<LogRecordProcessor> processor) {
        var applicationName = env.getProperty("spring.application.name:", "application");
        var springResources = Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, applicationName));
        var builder = SdkLoggerProvider.builder()
                .setResource(Resource.getDefault().merge(springResources));
        processor.orderedStream().forEach(builder::addLogRecordProcessor);
        return builder.build();
    }

    @Bean
    public OpenTelemetry openTelemetry(SdkLoggerProvider loggerProvider,
                                       SdkTracerProvider tracerProvider,
                                       ContextPropagators contextPropagators) {

        return OpenTelemetrySdk
                .builder()
                .setLoggerProvider(loggerProvider)
                .setTracerProvider(tracerProvider)
                .setPropagators(contextPropagators)
                .build();

    }

    @Bean
    public LogRecordProcessor logRecordProcessor() {
        var otlpLogRecord = OtlpGrpcLogRecordExporter
                .builder()
                .setEndpoint("http://localhost:4317")
                .build();
        return BatchLogRecordProcessor
                .builder(otlpLogRecord)
                .build();
    }

    @Bean
    public SpanAspect spanAspect(MethodInvocationProcessor processor) {
        return new SpanAspect(processor);
    }

    @Bean
    public MethodInvocationProcessor methodInvocationProcessor(NewSpanParser spanParser,
                                                               Tracer tracer,
                                                               BeanFactory beanFactory) {
        return new ImperativeMethodInvocationProcessor(
                spanParser,
                tracer,
                beanFactory::getBean,
                beanFactory::getBean
        );
    }

    @Bean
    public NewSpanParser newSpanParser() {
        return new DefaultNewSpanParser();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry){
        return new TimedAspect(registry);
    }
}

