package com.example.cliente_persona_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClientePersonaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientePersonaServiceApplication.class, args);
	}

}
