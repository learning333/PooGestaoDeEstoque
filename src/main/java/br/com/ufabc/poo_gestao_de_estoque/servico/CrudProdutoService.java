package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Produto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoRepository;

@Service
public class CrudProdutoService {
	private ProdutoRepository produtoRepository;

	public CrudProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-cadastrar produto");//testado
			System.out.println("2-Atualizar produto");//testado
			System.out.println("3-Listar Produtos");//testado
			System.out.println("4-Deletar Produto");//testado
			System.out.println("5-Visualizar Produto");//testado
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.atualizar(scanner);
				break;
			case 3:
				this.visualizar();
				break;
			case 4:
				this.deletar(scanner);
				break;
			case 5:
				this.teste(scanner);
				//this.fichaprod(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.print("Digite Nome: ");
		String nome=scanner.next();
		System.out.print("Digite a descricao");
		String prontuario=scanner.next();
		ProdutoCadastrado produto=new ProdutoCadastrado(nome,prontuario);
		this.produtoRepository.save(produto);
		System.out.println("SALVO!\n\n");
	}
	private void teste(Scanner scanner) {
		System.out.print("Digite Nome: ");
		String nome=scanner.next();
		Optional<ProdutoCadastrado> bababa=this.produtoRepository.findProduct(nome);
		//for(Produto p: bababa) {
		System.out.println(bababa.get().getNome());
		//}
	}

	private void atualizar(Scanner scanner) {
		System.out.print("Digite o id do produto a ser atualizado: ");
		Long id=scanner.nextLong();

		Optional<ProdutoCadastrado> optional = this.produtoRepository.findById(id);
		if(optional.isPresent()) {
			
			//todo- escolher o campo a ser atualizado
			System.out.print("Digite Nome: ");
			String nome=scanner.next();
			System.out.print("Digite a descricao");
			String desc=scanner.next();
			
			ProdutoCadastrado produto = optional.get();
			produto.setNome(nome);
			produto.setDescricao(desc);
			
			produtoRepository.save(produto);
			System.out.print("Produto Atualizado");
		}
		else {
			System.out.println("ID INVALIDO\n");
		}
	}
	private void visualizar() {
		Iterable<ProdutoCadastrado> lista = this.produtoRepository.findAll();
		for(ProdutoCadastrado produto: lista) {
			System.out.println(produto);
		}
		System.out.println();
	}
	private void deletar(Scanner scanner) {
		System.out.print("Digite o id do produto a ser deletado: ");
		Long id=scanner.nextLong();

		Optional<ProdutoCadastrado> optional = this.produtoRepository.findById(id);
		if(optional.isPresent()) {
			ProdutoCadastrado produto = optional.get();
			Long id2 =produto.getId();
			this.produtoRepository.deleteById(id2);
			System.out.println("produto deletado \n");
		}else {
			System.out.println("Id invalido");
		}
	}

}
