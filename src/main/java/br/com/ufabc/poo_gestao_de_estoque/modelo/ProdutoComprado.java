package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class ProdutoComprado extends Produto{
	@Deprecated
	public ProdutoComprado() {
		
	}
	public ProdutoComprado(int qtd, float valorUn, float valorTotal, String pedidoCompra) {
		super();
		this.qtd = qtd;
		this.valorUn = valorUn;
		this.valorTotal = valorTotal;
		this.pedidoCompra = pedidoCompra;
	}

	public int qtd;
	
	public float valorUn;
	
	public float valorTotal;
	
	//public Long Idcompra;
	
	public String pedidoCompra;
	
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public float getValorUn() {
		return valorUn;
	}

	public void setValorUn(float valorUn) {
		this.valorUn = valorUn;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	/*public Long getIdcompra() {
		return Idcompra;
	}

	public void setIdcompra(Long idcompra) {
		Idcompra = idcompra;
	}*/

	public String getPedidoCompra() {
		return pedidoCompra;
	}

	public void setPedidoCompra(String pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}

	@Override
	public String toString() {
		return "ProdutoComprado [Nome="+ nome+ " qtd=" + qtd + ", valorUn=" + valorUn + ", valorTotal=" + valorTotal + ", pedidoCompra="
				+ pedidoCompra + "]";
	}

	

}
