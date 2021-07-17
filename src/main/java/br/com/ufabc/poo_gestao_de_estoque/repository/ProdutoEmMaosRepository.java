package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoEmMaos;

public interface ProdutoEmMaosRepository extends CrudRepository<ProdutoEmMaos,Long>{
	@Query(
			  value = "SELECT TOP FROM produto_em_maos where nome= :nome", 
			  nativeQuery = true)
	Optional<ProdutoEmMaos> findProduct(@Param("nome") String nome);
	
	@Query(value="SELECT * FROM produto_em_maos where nome= :nome ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
	Optional<ProdutoEmMaos> findProducto(@Param("nome") String nome);
	
	@Query(
			  value = "SELECT * FROM produto_em_maos where pedido_original= :lote and nome= :nome ORDER BY id DESC LIMIT 0,1", 
			  nativeQuery = true)
	Optional<ProdutoEmMaos> findByLoteAndProduto(@Param("lote") String lote,@Param("nome") String nome);
}
