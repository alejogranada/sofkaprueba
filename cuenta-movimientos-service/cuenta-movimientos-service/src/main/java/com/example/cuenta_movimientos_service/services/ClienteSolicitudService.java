package com.example.cuenta_movimientos_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta_movimientos_service.config.RabbitConfig;

@Service
public class ClienteSolicitudService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Método para enviar la solicitud con el clienteId
    public void solicitarInfoCliente(Long clienteId) {
        // Envía el clienteId a la cola de solicitudes
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_CLIENTE,         // Exchange definido en RabbitConfig
                RabbitConfig.COLA_CLIENTE_SOLICITUD,   // Routing key (cola destino)
                clienteId                              // El mensaje es el clienteId
        );
    }
}