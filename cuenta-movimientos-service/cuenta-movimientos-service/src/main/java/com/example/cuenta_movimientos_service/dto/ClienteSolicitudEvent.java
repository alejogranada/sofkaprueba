package com.example.cuenta_movimientos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSolicitudEvent implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar un UID para la serialización

    private Long clienteId; // Identificador del cliente

}