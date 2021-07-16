package br.com.ufabc.poo_gestao_de_estoque.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="compras")
public class Compra extends Ordem{

	
	@Column(name="pedido")
	private String pedido;

	@Column
	private float valorTotal;
	
	@Column
	private String statusestoque;
	
	//@Column
	//private String statuspagto;
	
	//@Column(nullable=false)
	//private String descricao;
	@Deprecated
	public Compra() {
		
	}
	
	public Compra(String pedido, float valorTotal, String statusestoque) {
		super();
		this.pedido = pedido;
		this.valorTotal = valorTotal;
		this.statusestoque = statusestoque;
	}

	public Long getId() {
		return id;
	}

	public String getPedido() {
		
		return pedido;
	}

	public void setStatusEstoque(String string) {
		this.statusestoque=string;
		
	}
		




}
