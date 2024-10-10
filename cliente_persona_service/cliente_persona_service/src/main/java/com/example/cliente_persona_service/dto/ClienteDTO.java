package com.example.cliente_persona_service.dto;

import lombok.Data;

/**
 * DTO para representar la información de un Cliente
 */
@Data
public class ClienteDTO {

    private Long clienteId;
    private String nombre;
    private String nombreUsuario;
    private boolean estado;

}