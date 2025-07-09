package com.cruedadam.companies_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Anotación que marca esta clase como la configuración principal y punto de arranque de Spring Boot
@SpringBootApplication
// Habilita el cliente para que se registre en un servidor de descubrimiento como Eureka
@EnableDiscoveryClient
public class CompaniesCrudApplication {

	// Inicia la aplicación
	public static void main(String[] args) {
		// Lanza la aplicación Spring Boot, inicia el contexto y todos los beans configurados
		SpringApplication.run(CompaniesCrudApplication.class, args);
	}

}
