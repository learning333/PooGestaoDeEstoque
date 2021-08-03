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
			System.out.println("1-Produtos");
			System.out.println("2-Compras");
			System.out.println("3-Vendas");
			System.out.println("4-Situacional");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.npservice.menu(scanner);
				break;
			case 2:
				this.loteService.menu(scanner);
				break;
			case 3:
				this.nvservice.menu(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
	}
}
