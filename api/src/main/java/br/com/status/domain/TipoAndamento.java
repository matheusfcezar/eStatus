package br.com.status.domain;

public enum TipoAndamento {
	
	DISTRIBUICAO("Distribuição"),
	AUTOMATICO("Atualização automática"),
	INTIMACAO("Intimação"),
	DECISAO("Decisão"),
	LIMINAR("Medida Liminar"),
	CONCLUSO("Concluso para decisão"),
	REMETIDO("Processo remetido")
	;
	
	private String descricao;

	private TipoAndamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
