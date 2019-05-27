package br.com.status.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.status.domain.Arquivo;
import br.com.status.domain.Natureza;
import br.com.status.domain.Processo;
import br.com.status.domain.Usuario;
import br.com.status.repository.ArquivoRepository;
import br.com.status.repository.ProcessoUsuarioRepository;
import br.com.status.service.ProcessoService;

@RestController
@RequestMapping("processo")
public class ProcessoController {
	
	private final ProcessoService processoService;
	private final ProcessoUsuarioRepository processoUsuarioRepository;
	private final ArquivoRepository arquivoRepository;

	@Autowired
	public ProcessoController(ProcessoService processoService,
			ProcessoUsuarioRepository processoUsuarioRepository,
			ArquivoRepository arquivoRepository) {
		this.processoService = processoService;
		this.processoUsuarioRepository = processoUsuarioRepository;
		this.arquivoRepository = arquivoRepository;
	}
	
	@GetMapping("search")
	public List<Processo> filtrarProcessos(@RequestParam String busca) {
		
		return processoUsuarioRepository.findBuscaGeral(busca);
	}
	
	@GetMapping("{id}/users")
	public List<Usuario> getUsersFromProcesso(@PathVariable Long id) {
		List<Usuario> users = processoUsuarioRepository.findByProcessoId(id);
		
		return users;
	}
	
	@DeleteMapping("{idProcesso}/{idUsuario}")
	public ResponseEntity<String> removeUserInProcesso(@PathVariable Long idProcesso, @PathVariable Long idUsuario) {
		Boolean retorno = processoService.removeUserInProcesso(idProcesso, idUsuario);
		
		return retorno ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("É proibido excluir todas as partes de um processo.");
	}
	
	@PostMapping("{id}/users")
	public ResponseEntity<Usuario> addUserInProcesso(@PathVariable Long id, @RequestBody Usuario usuario) {
		processoService.addUserInProcess(id, usuario);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}")
	public Processo getProcesso(@PathVariable Long id, Principal principal) {
		
		return processoService.recuperarProcesso(id, principal.getName());
	}
	
	@GetMapping
	public List<Processo> getProcessos(Principal principal) {
			
		return processoService.getProcessosByEmail(principal.getName());
	}
	
	@PostMapping("new")
	public Processo criarProcesso(@RequestBody @Valid Processo processo, Principal principal) {
		
		return processoService.criarProcesso(processo, principal.getName());
	}
	
	@PutMapping("{id}")
	public Processo editarProcesso(@RequestBody Processo processo, Principal principal) {
		
		return processoService.editarProcesso(processo);
	}
	
	@GetMapping("naturezas")
	public Natureza[] getNaturezas() {
		
		return Natureza.values();
	}
	
	@PostMapping("/arquivo/add/{idProcesso}")
	public ResponseEntity<Arquivo> addAnexo(@RequestParam("anexo") MultipartFile arquivoEnviado, 
			@PathVariable Long idProcesso) {
		Arquivo arquivo = new Arquivo();
		try {
			arquivo.setDados(arquivoEnviado.getBytes());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		arquivo.setIdProcesso(idProcesso);
		arquivo.setNome(arquivoEnviado.getOriginalFilename());
		
		arquivoRepository.save(arquivo);

		return ResponseEntity.ok().build();
	}

}
