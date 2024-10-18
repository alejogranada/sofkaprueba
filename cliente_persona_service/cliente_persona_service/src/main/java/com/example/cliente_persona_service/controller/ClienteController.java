package com.example.cliente_persona_service.controller;

import com.example.cliente_persona_service.entities.Cliente;
import com.example.cliente_persona_service.producer.ClienteProducer;
import com.example.cliente_persona_service.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Validated @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/async/{id}")
    public CompletableFuture<ResponseEntity<Cliente>> obtenerClienteAsync(@PathVariable Long id) {
        return clienteService.obtenerClientePorIdAsync(id)
                .thenApply(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Validated @RequestBody Cliente clienteDetalles) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Prueba de mensajes con Rabbit
    /* Inicio */
    private final ClienteProducer clienteProducer;

    @Autowired
    public ClienteController(ClienteProducer clienteProducer) {
        this.clienteProducer = clienteProducer;
    }

    @PostMapping("/solicitar-cliente")
    public ResponseEntity<String> solicitarCliente(@RequestParam Long clienteId) {
        clienteProducer.solicitarCliente(clienteId);
        return ResponseEntity.ok("Solicitud enviada para el cliente ID: " + clienteId);
    }
    /* Fin */

}