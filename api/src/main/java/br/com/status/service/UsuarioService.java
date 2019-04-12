package br.com.status.service;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	public HttpStatus salvar(Usuario usuario) {
		
		Usuario usuarioSalvo = repository.findByEmail(usuario.getEmail());
		
		if (usuarioSalvo != null)
			return HttpStatus.BAD_REQUEST;
		
		enviaEmailCadastro(usuario);
		
		usuario.setPassword(convertPassToBcrypt(usuario.getPassword()));
		repository.save(usuario);
		
		return HttpStatus.CREATED;
	}

	private void enviaEmailCadastro(Usuario usuario) {
		Email email = new Email();
		email.setAssunto("Usuário Cadastrado");
		email.setEmailDestinatario(usuario.getEmail());
		email.setNomeDestinatario(usuario.getNome());
		
		try {
			mailService.enviar(email);
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

}
