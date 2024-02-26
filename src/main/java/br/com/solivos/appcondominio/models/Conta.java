package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Conta implements Serializable, Comparable<Conta>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconta")
	private Long id;

	@Size(min = 1, max = 2)
	@NotBlank
	private String sigla;

	@Size(max = 30)
	private String descricao;

	@Column(name = "saldoinicial")
	private BigDecimal saldoInicial;

	// O saldo é atualizado por TRIGGER ao modificar a tabela Movimentos, e é
	// atualizado no JAVA ao modificar a tabela Contas
	@Column(name = "saldoatual")
	private BigDecimal saldoAtual;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Movimento> movimentos = new ArrayList<>();

	@OneToMany(mappedBy = "contaInversa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Transferencia> transferenciasRecebidas = new ArrayList<>();
	

	@Override
	public int compareTo(Conta o) {
		// TODO Auto-generated method stub
		return this.sigla.compareTo(o.getSigla());
	}

}
