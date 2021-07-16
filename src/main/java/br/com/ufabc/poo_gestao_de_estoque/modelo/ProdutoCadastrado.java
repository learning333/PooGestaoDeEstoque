package br.com.ufabc.poo_gestao_de_estoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
public class ProdutoCadastrado extends Produto {

	//public String datacadastro;

	@Deprecated
	public ProdutoCadastrado() {}
	
	public ProdutoCadastrado(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao =descricao;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

}
