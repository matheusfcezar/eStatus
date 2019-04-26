package br.com.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Usuario;
import br.com.status.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("all")
	public List<Usuario> findAll() {
		
		return repository.findAll();
	}
	
	@GetMapping("ok")
	public ResponseEntity<String> ok() {
		
		return ResponseEntity.ok("OK");
	}

}
