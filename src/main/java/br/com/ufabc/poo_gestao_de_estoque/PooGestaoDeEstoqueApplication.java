package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ufabc.poo_gestao_de_estoque.servico.CaixaService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudCompraService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudProdutoService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudVendaService;



@SpringBootApplication
public class PooGestaoDeEstoqueApplication implements CommandLineRunner{

	private CaixaService caixaService;
	private CrudProdutoService produtoService;
	private CrudCompraService compraService;
	private CrudVendaService vendaService;
	
	
	
	public PooGestaoDeEstoqueApplication(CaixaService caixaService, CrudProdutoService produtoService , CrudCompraService compraService, CrudVendaService vendaService) {
		this.caixaService=caixaService;
		this.produtoService=produtoService;
		this.compraService=compraService;
		this.vendaService=vendaService;
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
			System.out.println("3-vendas/Estoque");
			System.out.println("5-Financas");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.produtoService.menu(scanner);
				break;
			case 2:
				this.compraService.menu(scanner);
				break;
			case 3:
				this.vendaService.menu(scanner);
				break;
			case 5:
				this.caixaService.menu(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
	}
}
