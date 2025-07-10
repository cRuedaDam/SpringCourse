package com.debuggeandoideas.report_ms.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
Esta clase configura el balanceador de carga que usa Spring Cloud LoadBalancer.
    - withBlockingDiscoveryClient() le dice a Spring que use Eureka
      para descubrir instancias del servicio "companies-crud".
    - Se inyecta como configuración personalizada usando @LoadBalancerClient.
*/

@Slf4j
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context){
        log.info("Configuring load balancer");
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient()
                .build(context);
    }
}
