package com.example.cuenta_movimientos_service.controller;

import com.example.cuenta_movimientos_service.entities.Cuenta;
import com.example.cuenta_movimientos_service.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@Validated @RequestBody Cuenta cuenta, @RequestParam Long clienteId) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta, clienteId);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listarCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @Validated @RequestBody Cuenta cuentaDetalles) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuentaDetalles);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}