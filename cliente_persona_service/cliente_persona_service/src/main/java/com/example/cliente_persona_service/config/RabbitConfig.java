package com.example.cliente_persona_service.config;

import com.example.cliente_persona_service.converter.CustomMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit  // Habilita el soporte de RabbitMQ en Spring
public class RabbitConfig {

    private final ObjectMapper objectMapper;

    public RabbitConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Define la cola para solicitudes de cliente
    @Bean
    public Queue colaClienteSolicitud() {
        return new Queue("cola_cliente_solicitud", true); // true para hacer la cola persistente
    }

    // Define la cola para respuestas de cliente
    @Bean
    public Queue colaClienteRespuesta() {
        return new Queue("cola_cliente_respuesta", true); // true para hacer la cola persistente
    }

    @Bean
    public MessageConverter messageConverter() {
        return new CustomMessageConverter(new ObjectMapper());
    }

}