package br.com.ufabc.poo_gestao_de_estoque.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface LoteRepository extends CrudRepository<Lote, Long> {
	
}
