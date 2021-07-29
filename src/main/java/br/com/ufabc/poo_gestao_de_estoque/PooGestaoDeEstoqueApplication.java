package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import br.com.ufabc.poo_gestao_de_estoque.servico.CrudLoteService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudNewProdutoService;
import br.com.ufabc.poo_gestao_de_estoque.servico.CrudNewVendaService;




@SpringBootApplication
public class PooGestaoDeEstoqueApplication implements CommandLineRunner{


	
	private CrudLoteService loteService;
	private CrudNewProdutoService npservice;
	private CrudNewVendaService nvservice;
	
	public PooGestaoDeEstoqueApplication( CrudLoteService loteService,
			CrudNewProdutoService npservice, CrudNewVendaService nvservice) {
		super();
		this.loteService = loteService;
		this.npservice = npservice;
		this.nvservice = nvservice;
	}
	/*public PooGestaoDeEstoqueApplication(CaixaService caixaService, CrudProdutoService produtoService , CrudCompraService compraService, CrudVendaService vendaService) {
		this.caixaService=caixaService;
		this.produtoService=produtoService;
		this.compraService=compraService;
		this.vendaService=vendaService;
	}*/
	public static void main(String[] args) {
		SpringApplication.run(PooGestaoDeEstoqueApplication.class, args);
	}
	


	//@Override
	public void run(String... args) throws Exception{
		Boolean gatilho=true;
		Scanner scanner=new Scanner(System.in);
		
		while(gatilho) {
			System.out.println("Escolha entidade");
			System.out.println("-----------");
			System.out.println("6-NewProduto");
			System.out.println("7-NewCompra");
			System.out.println("8-NewVenda");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 6:
				this.npservice.menu(scanner);
				break;
			case 7:
				this.loteService.menu(scanner);
				break;
			case 8:
				this.nvservice.menu(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
	}
}
