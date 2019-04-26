package br.com.status.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.status.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.ativo = :ativo, u.dataAtivacao = NOW() WHERE u.email = :email")
	@Transactional
	int ativarDesativar(@Param("ativo") boolean ativo, @Param("email") String email);
	
	Usuario findByEmailAndAtivo(String username, boolean b);

}
