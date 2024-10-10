package com.example.cliente_persona_service.services;

import com.example.cliente_persona_service.entities.Cliente;
import com.example.cliente_persona_service.exceptions.ResourceNotFoundException;
import com.example.cliente_persona_service.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Cliente");
        cliente.setClienteId("testcliente");
        cliente.setContraseña("password");
        cliente.setEstado(true);

        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente creado = clienteService.crearCliente(cliente);

        assertNotNull(creado);
        assertEquals("Test Cliente", creado.getNombre());
    }

    @Test
    public void testObtenerClientePorId_NoEncontrado() {
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clienteService.obtenerClientePorId(1L);
        });

        String expectedMessage = "Cliente no encontrado con id 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
