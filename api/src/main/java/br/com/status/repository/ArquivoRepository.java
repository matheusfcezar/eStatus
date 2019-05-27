package br.com.status.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.status.domain.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	
	List<Arquivo> findByIdProcesso(Long idProcesso);

}
