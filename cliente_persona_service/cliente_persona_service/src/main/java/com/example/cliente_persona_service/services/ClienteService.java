package com.example.cliente_persona_service.services;

import com.example.cliente_persona_service.dto.ClienteDTO;
import com.example.cliente_persona_service.entities.Cliente;
import com.example.cliente_persona_service.exceptions.ResourceNotFoundException;
import com.example.cliente_persona_service.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));

        cliente.setNombre(clienteDetalles.getNombre());
        cliente.setGenero(clienteDetalles.getGenero());
        cliente.setEdad(clienteDetalles.getEdad());
        cliente.setIdentificacion(clienteDetalles.getIdentificacion());
        cliente.setDireccion(clienteDetalles.getDireccion());
        cliente.setTelefono(clienteDetalles.getTelefono());
        cliente.setClienteId(clienteDetalles.getClienteId());
        cliente.setContraseña(clienteDetalles.getContraseña());
        cliente.setEstado(clienteDetalles.isEstado());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /*
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
    }
    */

    public ClienteDTO obtenerClientePorId(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        return clienteOpt.map(this::convertirAClienteDTO).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
    }

    public Cliente obtenerClientePorClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con clienteId " + clienteId));
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setEstado(cliente.isEstado());
        dto.setNombreUsuario(cliente.getClienteId());
        return dto;
    }
}