package br.com.solivos.appcondominio.enums;

public enum TipoContaBancaria {
	C("Conta Corrente"), P("Poupança");

	private final String nome;

	private TipoContaBancaria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
