package com.example.cliente_persona_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRespuestaEvent {
    private Long clienteId;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono;
}