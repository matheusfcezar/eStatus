package br.com.status.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Column(unique = true)
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@Column(name="data_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name="data_ativacao")
	private LocalDateTime dataAtivacao;
	
	private boolean ativo;
	
	private String oab;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtivacao() {
		return dataAtivacao;
	}

	public void setDataAtivacao(LocalDateTime dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}
	
	public String getUfOab() {
		return isAdvogado() ? this.oab.substring(oab.length()-2, oab.length()) : null;
	}
	
	public boolean isAdvogado() {
		return this.oab != null;
	}
}
