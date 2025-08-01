package io.github.fxavierds.vendasapi.rest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.fxavierds.vendasapi.model.Produto;
import io.github.fxavierds.vendasapi.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public List<ProdutoFormRequest> getLista(){
	
		return repository.findAll().stream().map(p -> ProdutoFormRequest.fromModel(p)).toList();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoFormRequest> getProduto(@PathVariable Long id) {
		Optional<Produto> produtoExistente = repository.findById(id);
		if(produtoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var produto = produtoExistente.map(ProdutoFormRequest::fromModel).get();
		return ResponseEntity.ok(produto);
	}

	@PostMapping
	public ProdutoFormRequest salvar( @RequestBody ProdutoFormRequest produto ) {
		
		Produto entidadeProduto = produto.toModel();		
		repository.save(entidadeProduto);		
		return ProdutoFormRequest.fromModel(entidadeProduto);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto) {
		Optional<Produto> produtoExistente = repository.findById(id);
		if(produtoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Produto entidadeProduto = produto.toModel();	
		entidadeProduto.setId(id);
		repository.save(entidadeProduto);	
		return ResponseEntity.ok().build();
	}
}
