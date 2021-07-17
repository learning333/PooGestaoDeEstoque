package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;

public interface ProdutoCompraRepository extends CrudRepository<ProdutoComprado, Long> {
	@Query(
			  value = "SELECT * FROM produto_comprado where pedido_compra= :nome", 
			  nativeQuery = true)
	List<ProdutoComprado> findOrdem(@Param("nome") String nome);
	//Collection<Produto>
	@Query(
			  value = "SELECT TOP FROM produto_em_maos where pedido_original= :lote", 
			  nativeQuery = true)
	Optional<ProdutoComprado> findByLote(@Param("lote") String lote);
}
