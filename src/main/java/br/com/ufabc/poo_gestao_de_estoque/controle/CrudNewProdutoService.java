package br.com.ufabc.poo_gestao_de_estoque.controle;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ufabc.poo_gestao_de_estoque.modelo.LoteRepository;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProdutoRepository;




@Service
public class CrudNewProdutoService {
	private LoteRepository loteRepository;
	private NewProdutoRepository newprodutoRepository;

	public CrudNewProdutoService(LoteRepository loteRepository,NewProdutoRepository newprodutoRepository) {
		this.loteRepository = loteRepository;
		this.newprodutoRepository= newprodutoRepository;
	}
	/*
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-cadastrar novo produto");
			System.out.println("2-listar produtos");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.visualizar();
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

		System.out.print("Digite a Descricao: ");
		String descricao=scanner.next();
		
		NewProduto produto=new NewProduto(nome, descricao);
		newprodutoRepository.save(produto);
		System.out.print("Salvo\n");

	}
	

	
	
	private void visualizar() {
		System.out.println("----Listando Produtos-----");
		Iterable<NewProduto> lista = this.newprodutoRepository.findAll();
		for(NewProduto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("----------FIM-------------");
		System.out.println();
	}*/
	
	public NewProduto adicionarNovo(String nome, String descricao) {
		NewProduto produto=new NewProduto(nome, descricao);
		newprodutoRepository.save(produto);
		return produto;
	}
	
	public Iterable<NewProduto> visualizarProdutos(){
		Iterable<NewProduto> lista = this.newprodutoRepository.findAll();
		return lista;
	}
	public Optional<NewProduto> BuscarPeloId(Long id) {
		Optional<NewProduto> optional =this.newprodutoRepository.findById(id);
		return optional;
	}
	
	
}
