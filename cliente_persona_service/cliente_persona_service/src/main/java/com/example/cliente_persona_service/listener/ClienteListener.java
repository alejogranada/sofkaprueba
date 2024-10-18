package com.example.cliente_persona_service.listener;

import com.example.cliente_persona_service.dto.ClienteSolicitudEvent;
import com.example.cliente_persona_service.dto.ClienteRespuestaEvent;
import com.example.cliente_persona_service.entities.Cliente;
import com.example.cliente_persona_service.services.ClienteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteListener {

    private final ClienteService clienteService;
    private final RabbitTemplate rabbitTemplate;

    public ClienteListener(ClienteService clienteService, RabbitTemplate rabbitTemplate) {
        this.clienteService = clienteService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "cola_cliente_solicitud")
    public void manejarSolicitudCliente(ClienteSolicitudEvent solicitud) {
        Cliente cliente = clienteService.obtenerClientePorId(solicitud.getClienteId());

        // Crear el evento de respuesta
        ClienteRespuestaEvent respuesta = new ClienteRespuestaEvent(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono()
        );

        // Enviar la respuesta a la cola correspondiente
        rabbitTemplate.convertAndSend("cola_cliente_respuesta", respuesta);
        System.out.println("Respuesta del cliente enviada: " + cliente.getId());
    }
}
