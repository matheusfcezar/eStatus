package br.com.status.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Andamento;
import br.com.status.repository.AndamentoRepository;

@RestController
@RequestMapping("andamento")
public class AndamentoCrontroller {
	
	@Autowired
	private AndamentoRepository repository;
	
	@GetMapping("/processo/{idProcesso}")
	public List<Andamento> getAndamentoByProcesso(@PathVariable Long idProcesso) {
		
		return repository.getAndamentosByProcesso(idProcesso);
	}
	
	@PostMapping("")
	public ResponseEntity<Andamento> salvar(@RequestBody @Valid Andamento andamento) {
		repository.save(andamento);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Andamento> remover(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
}
