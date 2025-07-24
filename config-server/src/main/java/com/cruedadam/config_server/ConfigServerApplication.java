package com.cruedadam.config_server;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

	@Value("${spring.cloud.config.server.git.password:NOT_SET}")
	private String gitToken;

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

	@PostConstruct
	public void printToken() {
		System.out.println("GIT TOKEN = " + gitToken);
	}

}
