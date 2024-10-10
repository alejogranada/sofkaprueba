package com.example.cuenta_movimientos_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReporteDTO {
    private LocalDateTime fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
