package com.debuggeandoideas.report_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 - Marca esta app como una aplicación Spring Boot.
 - @EnableDiscoveryClient permite que esta app se registre en Eureka y descubra servicios (como companies-crud).
 - @EnableFeignClients habilita la detección automática de interfaces con @FeignClient.

 Cuando haces una petición a:

 GET /report/{name}
 Ocurre lo siguiente:

 ReportController recibe la solicitud.
 Llama a ReportService.makeReport(name).
 ReportServiceImp usa CompaniesRepository (Feign).
 Feign, con ayuda de Eureka y LoadBalancerConfiguration, resuelve las instancias de companies-crud.
 Spring LoadBalancer elige una instancia (por round-robin, por defecto).
 Se hace la llamada HTTP a esa instancia.
 👉 Resultado: balanceo de carga automático entre instancias de companies-crud.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ReportMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportMsApplication.class, args);
	}
}
