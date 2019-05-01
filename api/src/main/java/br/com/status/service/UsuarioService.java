package br.com.status.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.status.domain.Usuario;
import br.com.status.dto.Email;
import br.com.status.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private MailService mailService;
	
	private PasswordEncoder passEncoder = new BCryptPasswordEncoder();
	
	public ResponseEntity<Usuario> salvar(Usuario usuario) {
		
		Usuario usuarioSalvo = repository.findByEmail(usuario.getEmail());
		
		if (usuarioSalvo != null)
			return ResponseEntity.badRequest().build();
		
		enviaEmailCadastro(usuario);
		
		usuario.setPassword(convertPassToBcrypt(usuario.getPassword()));
		usuario.setDataCadastro(LocalDateTime.now());
		repository.save(usuario);
		
		return ResponseEntity.ok().build();
	}

	private void enviaEmailCadastro(Usuario usuario) {
		Email email = new Email();
		email.setAssunto("eStatus - Confirme seu email");
		email.setEmailDestinatario(usuario.getEmail());
		email.setNomeDestinatario(usuario.getNome());
		email.setMensagem("Olá, " + usuario.getNome()
					+ "\n\nSeja bem-vindo ao eStatus,"
					+ "\nPara confirmar seu e-mail, clique no link abaixo:"
					+ "\nhttp://localhost:8080/public/usuario/ativar?email=" + usuario.getEmail());
		
		
		try {
			mailService.enviarAssincrono(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	private void enviaEmailCliente(Usuario usuario, String senha) {
		Email email = new Email();
		email.setAssunto("eStatus - Sua senha de acesso");
		email.setEmailDestinatario(usuario.getEmail());
		email.setNomeDestinatario(usuario.getNome());
		email.setMensagem("Olá, " + usuario.getNome()
					+ "\n\nVocê foi cadastrado por seu advogado no eStatus"
					+ "\nSua senha de acesso é: " + senha
					+ "\nPara acompanhar seus processos, acesse o link abaixo para entrar no sistema."
					+ "\nhttp://localhost:4200/");
		
		
		try {
			mailService.enviarAssincrono(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	public Optional<Usuario> recuperar(Long id) {
		
		return repository.findById(id);
	}
	
	public String convertPassToBcrypt(String textoPlano) {
		
		return passEncoder.encode(textoPlano);
	}

	public Usuario login(String email, String password) {
		
		return null;		
	}
	
	public List<Usuario> findAdvogadoByTermo(String busca) {
		
		return repository.findAdvogadoByAll(busca);
	}

	public ResponseEntity<Usuario> ativarUsuario(String email) {
		int rows = repository.ativarDesativar(true, email);
		
		return rows > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	public Usuario criarUsuarioCliente(Usuario usuario) {
	    String senhaAleatoria = criarSenhaAleatoria();
		
		usuario.setOab(null);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setDataAtivacao(LocalDateTime.now());
		usuario.setAtivo(true);
		usuario.setPassword(new BCryptPasswordEncoder().encode(senhaAleatoria));

		usuario = repository.save(usuario);
		if (usuario.getId() != null)
			enviaEmailCliente(usuario, senhaAleatoria);
		
		return usuario;
	}

	private String criarSenhaAleatoria() {
		int[] letras = new int[62];
		int start = 48;
		for (int i = 0; i < letras.length; i++) {
			while ((start > 57 && start < 65) || (start > 90 && start < 97))
				start++;
			letras[i] = start++;
		}
	    int targetStringLength = 8;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomInt = (int) (random.nextFloat() * 52);
	        buffer.append((char) letras[randomInt]);
	    }
	    String generatedString = buffer.toString();
	    
	    return generatedString;
	}

	public Usuario getByEmail(String email) {
		
		return repository.findByEmail(email);
	}

}
