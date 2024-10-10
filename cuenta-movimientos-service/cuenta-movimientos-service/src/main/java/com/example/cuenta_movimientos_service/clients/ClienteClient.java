package com.example.cuenta_movimientos_service.clients;

import com.example.cuenta_movimientos_service.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Cliente REST para comunicarse con cliente_persona_service
 */
@Component
public class ClienteClient {

    @Autowired
    private RestTemplate restTemplate;

    // URL base de clientes-service
    private final String CLIENTES_SERVICE_URL = "http://localhost:8080/clientes";

    /**
     * Obtiene un cliente por su ID desde clientes-service.
     *
     * @param id ID del cliente.
     * @return Objeto ClienteDTO o null si no se encuentra.
     */
    public ClienteDTO getClienteById(Long id) {
        try {
            return restTemplate.getForObject(CLIENTES_SERVICE_URL + "/" + id, ClienteDTO.class);
        } catch (Exception e) {
            // TODO: Manejar excepciones como 404, 500, etc.
            return null;
        }
    }
}
