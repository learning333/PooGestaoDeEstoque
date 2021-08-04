package br.com.ufabc.poogestaodeestoque.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VendaRepository extends CrudRepository<Venda, Long> {
	
}


