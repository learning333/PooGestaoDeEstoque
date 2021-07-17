package br.com.ufabc.poo_gestao_de_estoque.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;

@Repository
public interface OrdemCompraRepository extends CrudRepository<Compra, Long>{
	@Query(
			  value = "SELECT * FROM compras where statusestoque= :nome", 
			  nativeQuery = true)
	List<Compra> findByStatus(@Param("nome") String nome);
	
	@Query(
			  value = "SELECT * FROM compras where data like :anomes%", 
			  nativeQuery = true)
	List<Compra> findByYearMonth(@Param("anomes") String anomes);
}
