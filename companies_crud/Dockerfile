FROM bellsoft/liberica-openjdk-alpine:17
VOLUME /tmp
COPY target/companies_crud-0.0.1-SNAPSHOT.jar app.jar
ENV EUREKA_URL=null \
    CONFIG_SERVER_HOST=null \
    OTLP_METRICS_URL=null \
    OTLP_TRACING_URL=null
ENTRYPOINT ["java","-jar","/app.jar"]