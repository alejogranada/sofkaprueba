package com.example.cuenta_movimientos_service.listener;

import com.example.cuenta_movimientos_service.dto.ClienteRespuestaEvent;
import com.example.cuenta_movimientos_service.services.ReporteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteRespuestaListener {

    @Autowired
    private ReporteService reporteService;

    @RabbitListener(queues = "cola_cliente_respuesta")
    public void recibirInfoCliente(ClienteRespuestaEvent clienteRespuestaEvent) {
        // Cuando se reciba la respuesta del cliente, procesarla en el servicio
        reporteService.procesarRespuestaCliente(clienteRespuestaEvent);
    }
}