package com.example.cuenta_movimientos_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Define un bean de RestTemplate para inyección de dependencias.
     *
     * @return RestTemplate configurado.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
