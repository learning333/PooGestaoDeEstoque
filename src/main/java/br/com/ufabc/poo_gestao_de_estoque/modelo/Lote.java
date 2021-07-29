package br.com.ufabc.poo_gestao_de_estoque.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Lote")
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(nullable=false)
	protected String referencia;
	
	//public NewProduto produto;
	
	public String data;
	
	public String status;
	
	
	@OneToMany(mappedBy="lote", fetch=FetchType.EAGER)
	//FEtch EAGER - traz as materias junto, lazy não
	//cascade all- quando apaga prof as disciplinas vao junto
	private List<NewVenda> ListaDeVendas;
	
	@ManyToOne
	@JoinColumn(name="produto_id",nullable=true)
	private NewProduto produto;
	
	private int qtd;
	
	@Column(columnDefinition="int default 0")
	private int qtdVendida;
	
	private float custo;
	
	@Deprecated
	public Lote() {}

	public Lote(String referencia, String data, String status, NewProduto produto,int qtd, float custo) {
		super();
		this.referencia = referencia;
		this.data = data;
		this.status = status;
		this.produto = produto;
		this.qtd=qtd;
		this.custo=custo;
	}

	public void setStatus(String string) {
		this.status=string;
		
	}
	public String getStatus() {
		return status;		
	}

	public int getQtd() {
		return qtd;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public float getCusto() {
		return custo;
	}

	public void setQtdVendida(int i) {
		this.qtdVendida+=i;
		
	}
	
	
	
}
