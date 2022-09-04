package com.springback.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springback.cliente.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByRut(String rut);
    Optional<Cliente> findByEmail(String email);

}
