package br.com.status.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Arquivo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private byte[] dados;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataInsercao;
	
	private Long idProcesso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public LocalDate getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDate dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public Long getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Long idProcesso) {
		this.idProcesso = idProcesso;
	}
	
	public String getExtensao() {
		String[] split = this.nome.split("\\.");
		
		return split[split.length-1];
	}
	
}