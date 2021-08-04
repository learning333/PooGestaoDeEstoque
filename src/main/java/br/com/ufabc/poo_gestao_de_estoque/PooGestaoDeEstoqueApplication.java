package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ufabc.poo_gestao_de_estoque.controle.CrudLoteService;
import br.com.ufabc.poo_gestao_de_estoque.controle.CrudNewProdutoService;
import br.com.ufabc.poo_gestao_de_estoque.controle.CrudNewVendaService;




@SpringBootApplication
public class PooGestaoDeEstoqueApplication implements CommandLineRunner{


	
	private CrudLoteService loteService;
	private CrudNewProdutoService npservice;
	private CrudNewVendaService nvservice;
	private VisaoProdutos visaoProd;
	private VisaoLotes visaoLotes;
	private VisaoVendas visaoVendas;
	
	
	public PooGestaoDeEstoqueApplication( VisaoVendas visaoVendas,CrudLoteService loteService,
			CrudNewProdutoService npservice, CrudNewVendaService nvservice, VisaoProdutos visaoProd,VisaoLotes visaoLotes) {
		super();
		this.visaoVendas=visaoVendas;
		this.visaoLotes=visaoLotes;
		this.visaoProd=visaoProd;
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
				this.visaoProd.menu(scanner);
				//this.npservice.menu(scanner);
				break;
			case 2:
				this.visaoLotes.menu(scanner);
				//this.loteService.menu(scanner);
				break;
			case 3:
				this.visaoVendas.menu(scanner);
				//this.nvservice.menu(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
	}
}
