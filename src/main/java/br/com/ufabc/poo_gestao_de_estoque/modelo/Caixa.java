package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Caixa")
public class Caixa extends Ordem{//traz id e data de ordem
	
	@Column
	public Long idOrdem;
	
	@Column
	public String tipoOrdem;
	
	@Column
	public Float valor;

	@Deprecated
	public Caixa() {}
	
	public Caixa(Long idOrdem, String tipoOrdem, Float valor) {
		super();
		this.idOrdem = idOrdem;
		this.tipoOrdem = tipoOrdem;
		this.valor = valor;
	}

	public Long getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public String getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	
}
