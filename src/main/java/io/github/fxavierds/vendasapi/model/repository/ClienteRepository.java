package io.github.fxavierds.vendasapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.fxavierds.vendasapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where upper(c.nome) like :nome and c.cpf like :cpf")
	Page<Cliente> buscarPorNomeCpf(String nome, String cpf, Pageable page);
}
