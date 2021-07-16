package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Entity;

@Entity
public class ProdutoVenda extends Produto {
	@Deprecated
	public ProdutoVenda() {
	
	}
	
	public ProdutoVenda(int qtd,float valorUn){//String lote, int qtd, float valorUn, float valorTotal, float lucroTotal, String status,
			//String nome_cliente, String plataforma) {
		super();
		//this.lote = lote;
		this.qtd = qtd;
		this.valorUn = valorUn;
		/*this.valorTotal = valorTotal;
		this.lucroTotal = lucroTotal;
		this.status = status;*/
	}

	public String lote;
	
	public int qtd;
	
	public float valorUn;
	
	public float valorTotal;
	
	public float lucroTotal;
	
	public String status;
	

	@Override
	public String toString() {
		return "ProdutoVenda [lote=" + lote + ", qtd=" + qtd + ", valorUn=" + valorUn + ", valorTotal=" + valorTotal
				+ ", lucroTotal=" + lucroTotal + ", status=" + status + "]";
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

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


	
	
}
