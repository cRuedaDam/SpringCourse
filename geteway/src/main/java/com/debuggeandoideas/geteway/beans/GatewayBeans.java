package com.debuggeandoideas.geteway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Set;

/**
 * Clase de configuración de rutas para Spring Cloud Gateway.
 * Define dos `RouteLocator` distintos en función del perfil activo:
 * - Si Eureka está desactivado (`eureka-off`), define rutas estáticas con direcciones fijas.
 * - Si Eureka está activado (`eureka-on`), define rutas dinámicas usando load balancing (`lb://`).
 */

@Configuration
public class GatewayBeans {

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/*")
                        .uri("http://localhost:8081")
                )
                .route(route -> route
                        .path("/report-ms/report/*")
                        .uri("http://localhost:7070")
                )
                .build();
    }


    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/**")
                        .uri("lb://companies-crud")
                )
                .route(route -> route
                        .path("/report-ms/report/**")
                        .uri("lb://report-ms")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on-cb")
    public RouteLocator routeLocatorEurekaOnCB(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/**")
                        .filters(filter -> {
                            filter.circuitBreaker(config -> config
                                    .setName("gatewaw-cb")
                                    .setStatusCodes(Set.of("500","400"))
                                    .setFallbackUri("forward:/companies-crud-fallback/company/*"));
                            return filter;
                        })
                        .uri("lb://companies-crud")
                )
                .route(route -> route
                        .path("/report-ms/report/**")
                        .uri("lb://report-ms")
                )
                .route(route -> route
                        .path("/companies-crud-fallback/company/**")
                        .uri("lb://companies-crud-fallback")
                )
                .build();
    }
}
