package com.example.cuenta_movimientos_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReporteDTO {

    // Datos Cliente
    private LocalDateTime fecha;
    private String nombreUsuarioCliente;
    private String nombreCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private String identificacionCliente;
    private String generoCliente;
    private int edadCliente;
    // Datos Cuenta
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;

}