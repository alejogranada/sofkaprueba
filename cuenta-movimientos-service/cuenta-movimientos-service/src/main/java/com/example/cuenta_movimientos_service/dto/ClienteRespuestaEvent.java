package com.example.cuenta_movimientos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRespuestaEvent {

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