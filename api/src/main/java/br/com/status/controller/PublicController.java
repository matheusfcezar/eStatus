package br.com.status.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {

		return usuarioService.salvar(usuario);
	}
	
	@GetMapping("usuario/ativar")
	public ResponseEntity<Usuario> ativarUsuario(@RequestParam String email) {
		
		return usuarioService.ativarUsuario(email);
	}
	
	@PostMapping("usuario/resetPassword")
	public ResponseEntity<Usuario> resetarSenha(String email) {
		
		return null;
	}

}
