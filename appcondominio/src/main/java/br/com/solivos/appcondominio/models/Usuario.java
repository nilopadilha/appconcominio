package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.solivos.appcondominio.enums.Autorizacao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")

public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String username;

	@NotBlank
	@Size(min = 4, max = 100)
	private String password;

	@NotNull
	private Boolean ativo;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;

	@NotBlank
	@Size(min = 1, max = 100)
	private String sobrenome;

	@NotBlank
	@Size(min = 1, max = 100)
	@Email
	private String email;
	// LATER validar email ao criar conta

	@ElementCollection(targetClass = Autorizacao.class)
	@CollectionTable(name = "autorizacoes", joinColumns = @JoinColumn(name = "id_usuario"))
	@Enumerated(EnumType.STRING)
	@Column(name = "autorizacao")
	private Set<Autorizacao> autorizacoes = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	public String nomeCompleto() {
		if (sobrenome != null) {
			return nome + " " + sobrenome;
		} else {
			return nome;
		}
	}

}
