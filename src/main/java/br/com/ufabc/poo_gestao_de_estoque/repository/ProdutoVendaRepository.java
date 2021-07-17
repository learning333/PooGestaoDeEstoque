package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoVenda;

public interface ProdutoVendaRepository extends CrudRepository<ProdutoVenda, Long>{
	@Query(
			  value = "SELECT * FROM produto_venda where id_venda= :id", 
			  nativeQuery = true)
	List<ProdutoVenda> findByIdVenda(@Param("id") Long id_venda);
}
