package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Produto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;


@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoCadastrado, Long>{
	@Query(
			  value = "SELECT * FROM produtos where nome= :nome", 
			  nativeQuery = true)
	Optional<ProdutoCadastrado> findProduct(@Param("nome") String nome);//produto name e unico entao sempre retornara 1 lista de 1 elemento
	//Collection<Produto>
}
