package com.example.cuenta_movimientos_service.controller;

import com.example.cuenta_movimientos_service.entities.Movimiento;
import com.example.cuenta_movimientos_service.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@Validated @RequestBody Movimiento movimiento, @RequestParam String numeroCuenta) {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(numeroCuenta, movimiento);
        return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
    }
}
