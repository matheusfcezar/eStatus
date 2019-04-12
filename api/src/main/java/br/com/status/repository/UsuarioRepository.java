package br.com.status.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.status.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);

}
