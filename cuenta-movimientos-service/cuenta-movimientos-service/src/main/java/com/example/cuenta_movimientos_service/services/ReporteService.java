package com.example.cuenta_movimientos_service.services;

import com.example.cuenta_movimientos_service.dto.ClienteRespuestaEvent;
import com.example.cuenta_movimientos_service.dto.ReporteDTO;
import com.example.cuenta_movimientos_service.entities.Cuenta;
import com.example.cuenta_movimientos_service.entities.Movimiento;
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
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    // TODO: Se puede usar un mecanismo de cache temporal o sincronización para recibir la respuesta
    private ClienteRespuestaEvent clienteRespuestaEvent;

    public synchronized void procesarRespuestaCliente(ClienteRespuestaEvent clienteRespuesta) {
        this.clienteRespuestaEvent = clienteRespuesta;
        notifyAll();  // Notifica cuando se reciba la respuesta
    }

    public List<ReporteDTO> generarReporteAsync(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        synchronized (this) {
            try {
                wait();  // Espera la respuesta del cliente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Cuando la respuesta llegue, genera el reporte
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteRespuestaEvent.getClienteId());
        List<ReporteDTO> reportes = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            for (Movimiento movimiento : movimientos) {
                ReporteDTO reporte = new ReporteDTO();
                reporte.setFecha(movimiento.getFecha());
                // Datos obtenidos del evento de respuesta
                reporte.setNombreCliente(clienteRespuestaEvent.getNombre());
                reporte.setNombreUsuarioCliente(clienteRespuestaEvent.getNombreUsuario());
                reporte.setGeneroCliente(clienteRespuestaEvent.getGenero());
                reporte.setDireccionCliente(clienteRespuestaEvent.getDireccion());
                reporte.setEdadCliente(clienteRespuestaEvent.getEdad());
                reporte.setIdentificacionCliente(clienteRespuestaEvent.getIdentificacion());
                reporte.setTelefonoCliente(clienteRespuestaEvent.getTelefono());
                // Datos cuenta
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