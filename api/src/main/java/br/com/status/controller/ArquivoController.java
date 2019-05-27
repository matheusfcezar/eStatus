package br.com.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.status.domain.Arquivo;
import br.com.status.repository.ArquivoRepository;

@RestController
@RequestMapping("arquivo")
public class ArquivoController {

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@GetMapping("processo/{id}")
	public ResponseEntity<List<Arquivo>> getArquivosByProcesso(@PathVariable Long id) {
		
		return ResponseEntity.ok(arquivoRepository.findByIdProcesso(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> removeAnexo(@PathVariable Long id) {
		arquivoRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
}
