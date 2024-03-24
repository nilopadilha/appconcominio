package br.com.solivos.appcondominio.enums;

public enum TipoRelacao {
	P("Proprietário"), I("Inquilino"), O("Outro");

	private final String nome;

	private TipoRelacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
