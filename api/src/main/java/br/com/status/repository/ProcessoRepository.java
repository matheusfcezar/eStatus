package br.com.status.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.status.domain.Processo;

public interface ProcessoRepository  extends JpaRepository<Processo, Long> {
	
	Processo findByNumero(String numero);

}
