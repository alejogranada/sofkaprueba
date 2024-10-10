package com.example.cuenta_movimientos_service.services;

import com.example.cuenta_movimientos_service.entities.Cuenta;
import com.example.cuenta_movimientos_service.entities.Movimiento;
import com.example.cuenta_movimientos_service.exceptions.InsufficientFundsException;
import com.example.cuenta_movimientos_service.exceptions.ResourceNotFoundException;
import com.example.cuenta_movimientos_service.repositories.CuentaRepository;
import com.example.cuenta_movimientos_service.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrarMovimiento(String numeroCuenta, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con numeroCuenta " + numeroCuenta));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(movimiento.getValor());

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }
}
