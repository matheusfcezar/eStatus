package br.com.status.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.status.domain.Andamento;

public interface AndamentoRepository extends JpaRepository<Andamento, Long>{

	@Query("select a from Andamento a where a.idProcesso = ?1 order by a.data desc")
	List<Andamento> getAndamentosByProcesso(Long id);
	
}
