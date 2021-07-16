package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ProdutoEmMaos extends Produto{
	
	private String pedidoOriginal;
	
	private int qtdOriginal;
	
	@Column(columnDefinition="int default 0")
	private int qtdVendida;
	
	private float custoUn;
	
	@Deprecated
	public ProdutoEmMaos() {
	
	}
	
	public ProdutoEmMaos(String pedidoOriginal, int qtdOriginal, float custoUn) {
		super();
		this.pedidoOriginal = pedidoOriginal;
		this.qtdOriginal = qtdOriginal;
		this.custoUn = custoUn;
	}

	public String getPedidoOriginal() {
		return pedidoOriginal;
	}

	public void setPedidoOriginal(String pedidoOriginal) {
		this.pedidoOriginal = pedidoOriginal;
	}

	public int getQtdOriginal() {
		return qtdOriginal;
	}

	public void setQtdOriginal(int qtdOriginal) {
		this.qtdOriginal = qtdOriginal;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public void setQtdVendida(int qtdVendida) {
		this.qtdVendida = qtdVendida;
	}

	public float getCustoUn() {
		return custoUn;
	}

	public void setCustoUn(int custoUn) {
		this.custoUn = custoUn;
	}
	
	


	
}
