package io.github.fxavierds.vendasapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.fxavierds.vendasapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
