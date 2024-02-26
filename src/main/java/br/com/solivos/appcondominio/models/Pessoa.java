package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable, Comparable<Pessoa> {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpessoa")
	private Long id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;

	@Email
	@Size(max = 100)
	private String email;

	@Size(max = 15)
	private String telefone;

	@Size(max = 15)
	private String celular;

	@Size(max = 100)
	private String endereco;

	@Size(max = 6)
	@Column(name = "numeroend")
	private String numeroEnd;

	@Size(max = 30)
	@Column(name = "complementoend")
	private String complementoEnd;

	@Size(max = 30)
	private String bairro;

	@Size(max = 30)
	private String cidade;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Size(max = 8)
	private String cep;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "dataEntrada")
	@Valid
	private List<Relacao> relacoes = new ArrayList<>();

	@Override
	public int compareTo(Pessoa o) {
		return this.toString().compareTo(o.toString());
	}

}
