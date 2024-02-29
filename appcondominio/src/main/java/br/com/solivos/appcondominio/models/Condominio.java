package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.solivos.appcondominio.enums.Estado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "condominios")
public class Condominio implements Serializable, Comparable<Condominio>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcondominio")
	private Long id;

	@NotBlank
	@Size(min = 1, max = 100)
	@Column(name = "razaosocial")
	private String razaoSocial;

	@CNPJ
	private String cnpj;

	@Size(max = 14)
	private String ie;

	@Size(max = 30)
	private String im;

	@Email
	@Size(max = 100)
	private String email;

	@Size(max = 15)
	private String telefone;

	@Size(max = 15)
	private String celular;

	@NotBlank
	@Size(min = 1, max = 100)
	private String endereco;

	@NotBlank
	@Size(min = 1, max = 6)
	@Column(name = "numeroend")
	private String numeroEnd;

	@Size(max = 30)
	@Column(name = "complementoend")
	private String complementoEnd;

	@NotBlank
	@Size(min = 1, max = 30)
	private String bairro;

	@NotBlank
	@Size(min = 1, max = 30)
	private String cidade;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@NotBlank
	@Size(min = 8, max = 8)
	private String cep;

	// Dicas de relações:
	// https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/

	// LATER colocar este campo obrigatório. Ideia: ter no controller geral o
	// usuario
	// logado sempre pronto.
	@OneToOne(mappedBy = "condominio", fetch = FetchType.LAZY)
	private Usuario sindico;

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "sigla")
	private List<Bloco> blocos = new ArrayList<>();

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "nome")
	private List<Pessoa> pessoas = new ArrayList<>();

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "sigla")
	private List<Conta> contas = new ArrayList<>();

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "ordem")
	private List<Categoria> categorias = new ArrayList<>();

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "dataEmissao desc, moradia, numero, parcela")
	private List<Cobranca> cobrancas = new ArrayList<>();

	@OneToMany(mappedBy = "condominio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "inicio desc")
	private List<Periodo> periodos = new ArrayList<>();

	@Override
	public int compareTo(Condominio o) {
		return this.razaoSocial.compareTo(o.getRazaoSocial());
	}
}
