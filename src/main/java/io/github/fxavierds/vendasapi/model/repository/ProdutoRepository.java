package io.github.fxavierds.vendasapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.fxavierds.vendasapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
