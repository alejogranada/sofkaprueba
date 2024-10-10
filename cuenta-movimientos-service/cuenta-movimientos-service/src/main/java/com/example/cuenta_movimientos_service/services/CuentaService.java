package com.example.cuenta_movimientos_service.services;

import com.example.cuenta_movimientos_service.clients.ClienteClient;
import com.example.cuenta_movimientos_service.dto.ClienteDTO;
import com.example.cuenta_movimientos_service.entities.Cuenta;
import com.example.cuenta_movimientos_service.exceptions.ResourceNotFoundException;
import com.example.cuenta_movimientos_service.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteClient clienteClient; // Cliente REST para comunicarse con clientes-service

    public Cuenta crearCuenta(Cuenta cuenta, Long clienteId) {
        // Obtener el cliente desde clientes-service
        ClienteDTO cliente = clienteClient.getClienteById(clienteId);
        if (cliente == null || !cliente.isEstado()) {
            throw new ResourceNotFoundException("Cliente no encontrado o inactivo con id " + clienteId);
        }

        cuenta.setClienteId(clienteId);
        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaDetalles) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));

        cuenta.setNumeroCuenta(cuentaDetalles.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetalles.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetalles.getSaldoInicial());
        cuenta.setEstado(cuentaDetalles.isEstado());

        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
        cuentaRepository.delete(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
    }

    public Cuenta obtenerCuentaPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con numeroCuenta " + numeroCuenta));
    }

    public List<Cuenta> listarCuentasPorClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
