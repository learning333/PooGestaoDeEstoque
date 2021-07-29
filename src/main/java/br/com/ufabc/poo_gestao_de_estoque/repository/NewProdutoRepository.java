package br.com.ufabc.poo_gestao_de_estoque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;


@Repository
public interface NewProdutoRepository  extends CrudRepository<NewProduto, Long>{

}
