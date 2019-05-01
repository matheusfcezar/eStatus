package br.com.status.repository;

import java.util.List;

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
	
	@Query("select u from Usuario u where u.oab is not null and (u.oab like ?1% or u.cpf like ?1% or u.email like ?1%)")
	List<Usuario> findAdvogadoByAll(String busca);

}
