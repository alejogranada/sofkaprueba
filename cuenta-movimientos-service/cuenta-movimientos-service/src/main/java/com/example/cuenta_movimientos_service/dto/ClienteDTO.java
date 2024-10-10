package com.example.cuenta_movimientos_service.dto;

import lombok.Data;

/**
 * DTO para representar la información de un Cliente obtenida desde cliente_persona_service.
 */
@Data
public class ClienteDTO {

    private Long clienteId;
    private String nombre;
    private String nombreUsuario;
    private boolean estado;

}