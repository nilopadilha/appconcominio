package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.solivos.appcondominio.enums.MotivoBaixa;
import br.com.solivos.appcondominio.enums.MotivoEmissao;
import br.com.solivos.appcondominio.enums.SituacaoCobranca;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cobrancas")
public class Cobranca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcobranca")
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "motivoemissao")
	private MotivoEmissao motivoEmissao;

	@Size(max = 10)
	@NotBlank
	private String numero;

	@Size(max = 3)
	private String parcela;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "dataemissao")
	private LocalDate dataEmissao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "datavencimento")
	private LocalDate dataVencimento;

	@NotNull
	@Min(0)
	private BigDecimal valor;

	@Min(0)
	private BigDecimal desconto;

	@Min(0)
	private BigDecimal abatimento;

	@Min(0)
	@Column(name = "outrasdeducoes")
	private BigDecimal outrasDeducoes;

	// Juros tem atualização no banco de dados
	@Min(0)
	@Column(name = "jurosmora")
	private BigDecimal jurosMora;

	// Multa tem atualização no banco de dados
	@Min(0)
	private BigDecimal multa;

	@Min(0)
	@Column(name = "outrosacrescimos")
	private BigDecimal outrosAcrescimos;

	// Total é atualizado no banco de dados quando Juros e Multa são atualizados
	@Min(0)
	@NotNull
	private BigDecimal total;

	@Size(max = 255)
	private String descricao;

	@Min(0)
	@Column(name = "percentualjurosmes")
	private Float percentualJurosMes;

	@Min(0)
	@Column(name = "percentualmulta")
	private Float percentualMulta;

	@Enumerated(EnumType.STRING)
	private SituacaoCobranca situacao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "datarecebimento")
	private LocalDate dataRecebimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "motivobaixa")
	private MotivoBaixa motivoBaixa;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmoradia")
	private Moradia moradia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;
}
