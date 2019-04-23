package br.com.status.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long numero;
	
	private Date dataCadastro;

    private Date dataEncerramento;

    private String tipoJustica;

    private String regiao;

    private String secao;

    private String subsecao;

    private String juiz;

    private String cidade;

    private String foro;

    private String vara;
	
	//private StatusDoProcessoEnum status;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getNumeroProcesso() {
		return numero;
	}

	public void setNumeroProcesso(Long numero) {
		this.numero = numero;
	}

    public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

    public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

    public String getTipoJustica() {
		return tipoJustica;
	}

	public void setTipoJustica(String tipoJustica) {
		this.tipoJustica = tipoJustica;
	}

    public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

    public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = isecaod;
	}

    public String getSubsecao() {
		return subsecao;
	}

	public void setSubsecao(String subsecao) {
		this.subsecao = subsecao;
	}

    public String getJuiz() {
		return juiz;
	}

	public void setJuiz(String juiz) {
		this.juiz = juiz;
	}

    public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

    public String getForo() {
		return foro;
	}

	public void setForo(String foro) {
		this.foro = foro;
	}

    public String getVara() {
		return vara;
	}

	public void setVara(String vara) {
		this.vara = vara;
	}

    // public StatusDoProcessoEnum get(){
    //     return status;
    // }

    // public void set(StatusDoProcessoEnum status){
    //     this.status = status;
    // }
	
}
