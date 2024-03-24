package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.List;

import br.com.solivos.appcondominio.enums.TipoMoradia;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Table(name = "moradias")
public class Moradia implements Serializable, Comparable<Moradia>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmoradia")
	private Long id;

	@Size(min = 1, max = 10)
	@NotBlank
	private String sigla;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoMoradia tipo;

	private Float area;

	@Max(100)
	@Min(0)
	@Column(name = "fracaoideal")
	private Float fracaoIdeal;

	@Size(max = 30)
	private String matricula;

	private Integer vagas;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idbloco")
	private Bloco bloco;

	@OneToMany(mappedBy = "relacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "dataEntrada")
	@Valid
	private List<Relacao> relacoes ;

	@OneToMany(mappedBy = "cobraca", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "numero, parcela")
	private List<Cobranca> cobrancas;

	@Override
	public int compareTo(Moradia o) {
		return this.toString().compareTo(o.toString());
	}
}
