package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "blocos")
public class Bloco implements Serializable, Comparable<Bloco>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbloco")
	private Long id;

	@Size(min = 1, max = 3)
	@NotBlank
	private String sigla;

	@Size(max = 30)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	@OneToMany(mappedBy = "bloco", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "sigla")
	private List<Moradia> moradias = new ArrayList<>();

	@Override
	public int compareTo(Bloco o) {
		return this.sigla.compareTo(o.getSigla());
	}


	

	

	

}
