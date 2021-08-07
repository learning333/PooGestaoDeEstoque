package br.com.ufabc.poogestaodeestoque;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufabc.poogestaodeestoque.controle.CrudProdutoService;
import br.com.ufabc.poogestaodeestoque.controle.ProdutoInvalidoException;
import br.com.ufabc.poogestaodeestoque.modelo.Produto;

@Service
public class VisaoProdutos {

	@Autowired
	private CrudProdutoService crudProduto;
	

	
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-cadastrar novo produto");
			System.out.println("2-listar produtos");
			
			int opcao=-1;
			try {
				opcao=scanner.nextInt();
			}catch(InputMismatchException e){
				scanner.nextLine();
				opcao=-1;
			}
			
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
		System.out.println("-------------Cadastrando novo produto-------------");
		System.out.print("Digite Nome: ");
		scanner.nextLine();
		String nome=scanner.nextLine();
	
		//scanner.nextLine();
		System.out.print("Digite a Descricao: ");
		String descricao=scanner.nextLine();
		
		Produto novo;
		try {
			novo=crudProduto.adicionarNovo(nome,descricao);
			System.out.println("Salvo!\n");
			System.out.println(novo);
		} catch (Exception e) {

			System.out.print(e);
		}

		
		System.out.println("--------------------------------------------------");

	}
	

	
	
	private void visualizar() {
		System.out.println("\n----------Listando Produtos Cadastrados-----------");
		Iterable<Produto> lista = crudProduto.visualizarProdutos();
		for(Produto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}


	
}
