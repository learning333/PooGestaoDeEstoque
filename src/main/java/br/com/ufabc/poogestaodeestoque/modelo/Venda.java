package br.com.ufabc.poogestaodeestoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="NewVendas")
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	public String nomeCliente;
	
	
	@Column
	public String status;
	
	public float precoVenda;
	
	public int qtd;
	
	public float lucro;
	@ManyToOne
	@JoinColumn(name="lote_Id",nullable=true)
	public LoteCompra lote;

	@Deprecated
	public Venda() {}
	
	public Venda(String nomeCliente, float precoVenda, LoteCompra lote, int qtd,float lucro) {
		super();
		this.nomeCliente = nomeCliente;
		this.precoVenda = precoVenda;
		this.lote=lote;
		this.qtd=qtd;
		this.lucro=lucro;
	}

	public void setStatus(String string) {
		this.status=string;
		
	}
	//public long getLoteID() {
	//	return this.lote.getId();
	//}
	public int getQtd() {
		return this.qtd;
	}


	
	public String toString() {
		return " ID VENDA: ["+id+"] Cliente=" + nomeCliente + " Status=" + status + 
				"\n Produto ["+lote.getNomeProduto()+"] Qtd: "+qtd+ " Valor Un: "+precoVenda+
				"\n Valor Total: "+qtd*precoVenda +" Lucro: "+lucro;
	}

	public LoteCompra getLote() {
		return this.lote;
	}

	public String getStatus() {
		return status;
	}
	
}

