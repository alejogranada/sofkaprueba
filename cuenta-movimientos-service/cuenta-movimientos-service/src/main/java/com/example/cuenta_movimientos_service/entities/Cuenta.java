package com.example.cuenta_movimientos_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "cuenta")
@Data
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private boolean estado;

    private Long clienteId;

}