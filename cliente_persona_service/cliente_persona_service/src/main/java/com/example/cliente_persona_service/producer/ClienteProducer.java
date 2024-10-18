package com.example.cliente_persona_service.producer;

import com.example.cliente_persona_service.dto.ClienteSolicitudEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public ClienteProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void solicitarCliente(Long clienteId) {
        ClienteSolicitudEvent solicitud = new ClienteSolicitudEvent(clienteId);
        rabbitTemplate.convertAndSend("cola_cliente_solicitud", solicitud);
        System.out.println("Solicitud de cliente enviada: " + clienteId);
    }

}