package br.com.status.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.status.domain.Processo;
import br.com.status.domain.ProcessoUsuario;
import br.com.status.domain.Usuario;
import br.com.status.repository.ProcessoRepository;
import br.com.status.repository.ProcessoUsuarioRepository;
import br.com.status.repository.UsuarioRepository;

@Service
public class ProcessoService {
	
	private final ProcessoRepository processoRepository;
	private final ProcessoUsuarioRepository processoUsuarioRepository;
	private final UsuarioRepository usuarioRepository;
	private final UsuarioService usuarioService;

	@Autowired
	public ProcessoService(ProcessoRepository processoRepository,
			ProcessoUsuarioRepository processoUsuarioRepository,
			UsuarioRepository usuarioRepository,
			UsuarioService usuarioService) {
		this.processoRepository = processoRepository;
		this.processoUsuarioRepository = processoUsuarioRepository;
		this.usuarioRepository = usuarioRepository;
		this.usuarioService = usuarioService;
	}
	
	public Processo getProcessoByNumero(String numeroProcesso) {
		
		return processoRepository.findByNumero(numeroProcesso);
	}
	
	@Transactional
	public Processo criarProcesso(Processo processo, String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		processo.setDataCadastro(LocalDate.now());
		processo = processoRepository.save(processo);
		
		ProcessoUsuario processoUsuario = new ProcessoUsuario(processo, usuario);
		processoUsuarioRepository.save(processoUsuario);
		
		return processo;
	}
	
	public Processo editarProcesso(Processo processo) {
		
		return processoRepository.save(processo);
	}

	public List<Processo> getProcessosByEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		return processoUsuarioRepository.findByUsuarioId(usuario.getId());
	}

	public Processo recuperarProcesso(Long id, String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		ProcessoUsuario processoUsuario = processoUsuarioRepository.findByUsuarioIdAndProcessoId(usuario.getId(), id);
		
		return processoUsuario == null ? null : processoUsuario.getProcesso();
	}

	public void addUserInProcess(Long id, Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioSalvo == null)
			usuarioSalvo = usuarioService.criarUsuarioCliente(usuario);
		
		ProcessoUsuario pu = processoUsuarioRepository.findByUsuarioIdAndProcessoId(usuarioSalvo.getId(), id);
		if (pu != null)
			return;
		
		Processo processo = new Processo();
		processo.setId(id);
		ProcessoUsuario processoUsuario = new ProcessoUsuario(processo, usuarioSalvo);
		processoUsuarioRepository.save(processoUsuario);
	}

	public int removeUserInProcesso(Long idProcesso, Long idUsuario) {

		return processoUsuarioRepository.removerUsuarioDoProcesso(idProcesso, idUsuario);
	}

}
