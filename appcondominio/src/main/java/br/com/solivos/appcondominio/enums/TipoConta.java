package br.com.solivos.appcondominio.enums;

public enum TipoConta {
	CX("Caixa"), BC("Conta Bancária");

	private final String nome;

	private TipoConta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
