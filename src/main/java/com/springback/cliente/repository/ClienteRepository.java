package com.springback.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springback.cliente.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByRut(String rut);
    Optional<Cliente> findByEmail(String email);

}
