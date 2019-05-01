package br.com.status.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.status.domain.Processo;
import br.com.status.domain.ProcessoUsuario;
import br.com.status.domain.Usuario;

public interface ProcessoUsuarioRepository extends JpaRepository<ProcessoUsuario, Long> {
	
	@Query("select pu.processo from ProcessoUsuario pu where pu.usuario.id = ?1")
	List<Processo> findByUsuarioId(Long id);
	
	@Query("select pu.usuario from ProcessoUsuario pu where pu.processo.id = ?1")
	List<Usuario> findByProcessoId(Long id);
	
	@Query("select pu.processo from ProcessoUsuario pu where pu.usuario.nome like ?1% "
			+ "or pu.usuario.cpf like ?1% or pu.usuario.email like ?1% or pu.processo.numero like ?1%"
			+ " or pu.processo.tipoProcesso like ?1%")
	List<Processo> findBuscaGeral(String busca);
	
	ProcessoUsuario findByUsuarioIdAndProcessoId(Long usuarioId, Long processoId);
	
	@Modifying
	@Query("delete from ProcessoUsuario pu WHERE pu.processo.id = ?1 and pu.usuario.id = ?2")
	@Transactional
	int removerUsuarioDoProcesso(Long idProcesso, Long idUsuario);

}
