package br.com.solivos.appcondominio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.solivos.appcondominio.enums.TipoCategoria;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable, Comparable<Categoria> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int NIVEL_MAX = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcategoria")
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCategoria tipo;

	@Size(min = 1, max = 50)
	@NotBlank
	private String descricao;

	@Max(NIVEL_MAX)
	private Integer nivel;

	// LATER criar método para ordenação automática, lembrar que edição exige
	// reescrever ordem nas categorias filhas
	@Size(min = 1, max = 255)
	@NotBlank
	private String ordem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcategoriapai")
	private Categoria categoriaPai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	@OneToMany(mappedBy = "categoriaPai", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Categoria> categoriasFilhas = new ArrayList<>();

	@OneToMany(mappedBy = "categoriaPai", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "descricao")
	private List<Subcategoria> Subcategorias = new ArrayList<>();
	
	public static Integer getNivelMax() {
		return NIVEL_MAX;
	}
	@Override
	public int compareTo(Categoria o) {
		// TODO Auto-generated method stub
		return this.ordem.compareTo(o.getOrdem());
	}

}
