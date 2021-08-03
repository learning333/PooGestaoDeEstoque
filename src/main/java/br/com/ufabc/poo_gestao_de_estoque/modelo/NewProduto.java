package br.com.ufabc.poo_gestao_de_estoque.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NewProdutos")
public class NewProduto{// extends EntidadeBase{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(nullable=false)
	protected String nome;
		
	@Column(nullable=false)
	protected String descricao;
	
	@OneToMany(mappedBy="produto", fetch=FetchType.EAGER)
	private List<Lote> lotes;
	
	@Deprecated
	public NewProduto() {}
	
	public NewProduto(String nome, String descricao) {
		super();
		this.nome=nome;
		this.descricao=descricao;
	}


	@Override
	public String toString() {
		return "ID: [" + id + "] Nome=" + nome + " Descricao=" + descricao;//, lotes=" + lotes + "]";
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
