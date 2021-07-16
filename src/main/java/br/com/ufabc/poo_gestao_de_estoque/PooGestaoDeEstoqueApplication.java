package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ufabc.poo_gestao_de_estoque.servico.CrudCompraService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudProdutoService;



@SpringBootApplication
public class PooGestaoDeEstoqueApplication implements CommandLineRunner{

	private CrudProdutoService produtoService;
	private CrudCompraService compraService;
	
	
	
	public PooGestaoDeEstoqueApplication(CrudProdutoService produtoService , CrudCompraService compraService) {
		this.produtoService=produtoService;
		this.compraService=compraService;
	}
	public static void main(String[] args) {
		SpringApplication.run(PooGestaoDeEstoqueApplication.class, args);
	}
	
	//@Override
	public void run(String... args) throws Exception{
		Boolean gatilho=true;
		Scanner scanner=new Scanner(System.in);
		
		while(gatilho) {
			System.out.println("Escolha entidade");
			System.out.println("0-sair");
			System.out.println("1-produtos");
			System.out.println("2-compras");
			System.out.println("3-vendas");
			System.out.println("4-relatorios");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.produtoService.menu(scanner);
				break;
			case 2:
				this.compraService.menu(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
	}
}
