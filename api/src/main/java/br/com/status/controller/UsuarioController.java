package br.com.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Processo;
import br.com.status.domain.ProcessoUsuario;
import br.com.status.domain.Usuario;
import br.com.status.repository.ProcessoUsuarioRepository;
import br.com.status.repository.UsuarioRepository;
import br.com.status.service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	private final UsuarioRepository repository;
	private final UsuarioService usuarioService;
	private final ProcessoUsuarioRepository processoUsuarioRepository;

	@Autowired
	public UsuarioController(UsuarioRepository repository, UsuarioService usuarioService,
			ProcessoUsuarioRepository processoUsuarioRepository) {
		this.usuarioService = usuarioService;
		this.repository = repository;
		this.processoUsuarioRepository = processoUsuarioRepository;
	}

	@GetMapping("all")
	public List<Usuario> findAll() {
		
		return repository.findAll();
	}

	@GetMapping("advogado")
	public List<Usuario> buscarAdvogado(@RequestParam String busca) {
		
		return usuarioService.findAdvogadoByTermo(busca);
	}
	
	@GetMapping("filtrar")
	public Usuario buscarUsuario(@RequestParam String email) {
		
		return this.usuarioService.getByEmail(email);
	}
	
	@PostMapping("processo/{id}")
	public void adicionarEmProcesso(@RequestBody Usuario usuario, @PathVariable Long id) {
		
		if (usuario.getId() == null) {
			usuario = usuarioService.criarUsuarioCliente(usuario);
		}
		
		Processo processo = new Processo();
		processo.setId(id);
		processoUsuarioRepository.save(new ProcessoUsuario(processo, usuario));
	}
	@GetMapping("esqueci-senha")
	public Boolean esqueciMinhaSenha(@RequestParam String email) {
		return true;
    }
}
