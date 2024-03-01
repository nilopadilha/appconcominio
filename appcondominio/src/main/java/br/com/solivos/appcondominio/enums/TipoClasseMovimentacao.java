package br.com.solivos.appcondominio.enums;

public enum TipoClasseMovimentacao {
	
	L("Lançamento"),
	T("Transferência");
	
	private final String nome;

	private TipoClasseMovimentacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
