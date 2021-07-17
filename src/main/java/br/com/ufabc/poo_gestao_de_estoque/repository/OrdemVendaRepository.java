package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoVenda;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Venda;


@Repository
public interface OrdemVendaRepository extends CrudRepository<Venda, Long>{
	@Query(
			  value = "SELECT * FROM produto_comprado where nome_cliente= :nome", 
			  nativeQuery = true)
	List<Venda> findOrdemByCliente(@Param("nome") String nome);
	
	@Query(
			  value = "SELECT * FROM vendas where data like :anomes%", 
			  nativeQuery = true)
	List<Venda> findByYearMonth(@Param("anomes") String anomes);
}
