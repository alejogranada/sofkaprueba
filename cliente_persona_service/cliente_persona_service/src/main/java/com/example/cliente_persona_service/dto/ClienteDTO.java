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
    private String identificacion;
    private String direccion;
    private String telefono;
    private boolean estado;
    private String genero;
    private int edad;

}