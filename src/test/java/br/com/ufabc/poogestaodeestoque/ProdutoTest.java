package br.com.ufabc.poogestaodeestoque;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.ufabc.poogestaodeestoque.modelo.Produto;
import br.com.ufabc.poogestaodeestoque.modelo.ProdutoRepository;

@DataJpaTest
public class ProdutoTest {

	@Autowired
	private ProdutoRepository produtos;
	
	@Test
	public void testCriacao() {
		Produto produto= new Produto("ProdTeste","DescTeste");
		Produto salvo=produtos.save(produto);
		assertNotNull(salvo);
	}

	

}
