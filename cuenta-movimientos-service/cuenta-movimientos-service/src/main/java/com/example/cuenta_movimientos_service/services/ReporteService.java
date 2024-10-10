package com.example.cuenta_movimientos_service.services;

import com.example.cuenta_movimientos_service.clients.ClienteClient;
import com.example.cuenta_movimientos_service.dto.ClienteDTO;
import com.example.cuenta_movimientos_service.dto.ReporteDTO;
import com.example.cuenta_movimientos_service.entities.Cuenta;
import com.example.cuenta_movimientos_service.entities.Movimiento;
import com.example.cuenta_movimientos_service.exceptions.ResourceNotFoundException;
import com.example.cuenta_movimientos_service.repositories.CuentaRepository;
import com.example.cuenta_movimientos_service.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ClienteClient clienteClient; // Cliente REST para comunicarse con clientes-service

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<ReporteDTO> generarReporte(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // Obtener el cliente desde clientes-service
        ClienteDTO cliente = clienteClient.getClienteById(clienteId);
        if (cliente == null || !cliente.isEstado()) {
            throw new ResourceNotFoundException("Cliente no encontrado o inactivo con id " + clienteId);
        }

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<ReporteDTO> reportes = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            for (Movimiento movimiento : movimientos) {
                ReporteDTO reporte = new ReporteDTO();
                reporte.setFecha(movimiento.getFecha());
                reporte.setCliente(cliente.getNombre());
                reporte.setNumeroCuenta(cuenta.getNumeroCuenta());
                reporte.setTipo(cuenta.getTipoCuenta());
                reporte.setSaldoInicial(cuenta.getSaldoInicial());
                reporte.setEstado(cuenta.isEstado());
                reporte.setMovimiento(movimiento.getValor());
                reporte.setSaldoDisponible(movimiento.getSaldo());
                reportes.add(reporte);
            }
        }

        return reportes;
    }
}

