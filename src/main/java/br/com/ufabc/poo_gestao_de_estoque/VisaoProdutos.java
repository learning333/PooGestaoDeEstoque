package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufabc.poo_gestao_de_estoque.controle.CrudNewProdutoService;
import br.com.ufabc.poo_gestao_de_estoque.modelo.LoteRepository;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProdutoRepository;

@Service
public class VisaoProdutos {

	@Autowired
	private CrudNewProdutoService crudProduto;
	

	
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
		
		NewProduto novo=crudProduto.adicionarNovo(nome,descricao);
		System.out.println(novo);
		System.out.print("Salvo!\n");

	}
	

	
	
	private void visualizar() {
		System.out.println("----------Listando Produtos Cadastrados-----------");
		Iterable<NewProduto> lista = crudProduto.visualizarProdutos();
		for(NewProduto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}

}
