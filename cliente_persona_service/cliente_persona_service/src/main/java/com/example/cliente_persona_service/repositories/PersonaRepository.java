package com.example.cliente_persona_service.repositories;

import com.example.cliente_persona_service.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    //TODO: Implementar si se necesita por Identificacion en la tabla Persona
    Optional<Persona> findByIdentificacion(String identificacion);
}