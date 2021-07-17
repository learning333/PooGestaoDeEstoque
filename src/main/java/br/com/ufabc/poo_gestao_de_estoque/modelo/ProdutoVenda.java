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
		this.valor_un = valorUn;
		/*this.valorTotal = valorTotal;
		this.lucroTotal = lucroTotal;
		this.status = status;*/
	}

	public String lote;
	
	public int qtd;
	
	public float valor_un;
	
	public String status_venda;
	
	public Long id_venda;

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

	public float getValor_un() {
		return valor_un;
	}

	public void setValor_un(float valor_un) {
		this.valor_un = valor_un;
	}

	public String getStatus_venda() {
		return status_venda;
	}

	public Long getId_venda() {
		return id_venda;
	}

	public void setId_venda(Long id_venda) {
		this.id_venda = id_venda;
	}

	@Override
	public String toString() {
		return "ProdutoVenda [Nome=" +nome+ ", qtd=" + qtd + ", valor_un=" + valor_un + ", status_venda="
				+ status_venda + ", id_venda=" + id_venda +  ", lotecompra=" + lote +"]";
	}

	public void setStatus_venda(String status_venda2) {
		this.status_venda=status_venda2;
		
	}
	




	
	
}
