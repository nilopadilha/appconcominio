package br.com.solivos.appcondominio.enums;

public enum SituacaoCobranca {

		N("Normal"),
		A("Notificado"),
		P("Protestado"),
		J("Ação Judicial"),
		O("Outras");
	

		private final String nome;

		private SituacaoCobranca(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
}
