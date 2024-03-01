package br.com.solivos.appcondominio.enums;

public enum Genero {
	
		M("Masculino"),
		F("Feminino"),
		O("Outro");
		

		private final String nome;

		private Genero(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
}
