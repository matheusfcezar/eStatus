package br.com.status.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Foro;
import br.com.status.repository.ForoRepository;

@RestController
@RequestMapping("foro")
public class ForoController {
	
	@Autowired
	private ForoRepository repository;
	
	@PostMapping("new")
	public void criarForo(@RequestBody @Valid Foro foro) {
		
	}
	
	@PostMapping("edit")
	public void editarForo(@RequestBody @Valid Foro foro) {
		
	}

	@GetMapping("{id}")
	public void getForo(@PathVariable Long id) {
		
	}
	
	@GetMapping("filtrar")
	public List<Foro> getForos(@RequestParam String busca) {
		
		return repository.findByCidadeContaining(busca);
	}
	
}
