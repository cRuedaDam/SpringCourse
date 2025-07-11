package com.debuggeandoideas.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque del proyecto Spring Cloud Gateway.
 * Lanza el contenedor de Spring Boot y todas las configuraciones asociadas.
 */

@SpringBootApplication
public class GetewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetewayApplication.class, args);
	}

}
