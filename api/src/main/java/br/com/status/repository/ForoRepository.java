package br.com.status.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.status.domain.Foro;

public interface ForoRepository extends JpaRepository<Foro, Long>{
	
	List<Foro> findByCidadeContaining(String cidade);

}
