package br.com.solivos.appcondominio.enums;

public enum TipoPessoa {
	F("Pessoa Física"), J("Pessoa Jurídica");

	private final String nome;

	private TipoPessoa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
