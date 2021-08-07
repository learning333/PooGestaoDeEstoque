package br.com.ufabc.poogestaodeestoque.controle;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ufabc.poogestaodeestoque.modelo.LoteCompraRepository;
import br.com.ufabc.poogestaodeestoque.modelo.Produto;
import br.com.ufabc.poogestaodeestoque.modelo.ProdutoRepository;




@Service
public class CrudProdutoService {
	private LoteCompraRepository loteRepository;
	private ProdutoRepository newprodutoRepository;

	public CrudProdutoService(LoteCompraRepository loteRepository,ProdutoRepository newprodutoRepository) {
		this.loteRepository = loteRepository;
		this.newprodutoRepository= newprodutoRepository;
	}
	
	
	public Produto adicionarNovo(String nome, String descricao) throws Exception {
		if(nome.length()>0 && descricao.length()>0) {
			Produto produto=new Produto(nome, descricao);
			newprodutoRepository.save(produto);
			return produto;
		}else {
			throw new ProdutoInvalidoException("Nome e descricao nao podem ser nulos");
		}
	}
	
	public Iterable<Produto> visualizarProdutos(){
		Iterable<Produto> lista = this.newprodutoRepository.findAll();
		return lista;
	}
	public Optional<Produto> BuscarPeloId(Long id) {
		Optional<Produto> optional =this.newprodutoRepository.findById(id);
		return optional;
	}
	
	

	
}
