package com.example.cuenta_movimientos_service.controller;

import com.example.cuenta_movimientos_service.dto.ReporteDTO;
import com.example.cuenta_movimientos_service.services.ClienteSolicitudService;
import com.example.cuenta_movimientos_service.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ClienteSolicitudService clienteSolicitudService;

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        // Enviar solicitud para obtener info del cliente
        clienteSolicitudService.solicitarInfoCliente(clienteId);

        // Esperar la respuesta del cliente en el servicio asíncrono
        List<ReporteDTO> reportes = reporteService.generarReporteAsync(clienteId, fechaInicio, fechaFin);

        // Retornar el reporte generado
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

}