package br.com.status.service;

import java.time.LocalDateTime;
import java.util.Optional;

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
	
	public Optional<Usuario> recuperar(Long id) {
		
		return repository.findById(id);
	}
	
	public String convertPassToBcrypt(String textoPlano) {
		
		return passEncoder.encode(textoPlano);
	}

	public Usuario login(String email, String password) {
		
		return null;		
	}

	public ResponseEntity<Usuario> ativarUsuario(String email) {
		int rows = repository.ativarDesativar(true, email);
		
		return rows > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
