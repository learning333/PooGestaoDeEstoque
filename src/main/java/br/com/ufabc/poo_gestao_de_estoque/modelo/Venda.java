package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendas")
public class Venda extends Ordem{

	@Deprecated
	public Venda() {}
	
	public Venda(float valorTotal, float lucroTotal, String status, String plataforma, String nome_cliente) {
		super();
		this.valorTotal = valorTotal;
		this.lucroTotal = lucroTotal;
		this.status = status;
		this.plataforma = plataforma;
		this.nome_cliente = nome_cliente;
	}

	@Column
	private float valorTotal;
	
	@Column
	private float lucroTotal;
	
	@Column
	private String status;
	
	@Column
	private String plataforma;
	
	@Column
	private String nome_cliente;
	
	
	
	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getLucroTotal() {
		return lucroTotal;
	}

	public void setLucroTotal(float lucroTotal) {
		this.lucroTotal = lucroTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
}
