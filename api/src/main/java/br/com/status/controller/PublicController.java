package br.com.status.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Usuario;
import br.com.status.service.UsuarioService;

@RestController
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("usuario/new")
	public HttpStatus criarUsuario(@Valid @RequestBody Usuario usuario) {

		return usuarioService.salvar(usuario);
	}
	
	@PutMapping("usuario/ativar/{id}")
	public HttpStatus ativarUsuario(@PathVariable Long id, @RequestParam String hash) {
		
		return null;
	}
	
	@PostMapping("usuario/resetPassword")
	public HttpStatus resetarSenha(String email) {
		
		return null;
	}

}
