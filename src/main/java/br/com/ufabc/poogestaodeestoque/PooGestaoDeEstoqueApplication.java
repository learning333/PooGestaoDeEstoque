package br.com.ufabc.poogestaodeestoque;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ufabc.poogestaodeestoque.controle.CrudLoteCompraService;
import br.com.ufabc.poogestaodeestoque.controle.CrudProdutoService;
import br.com.ufabc.poogestaodeestoque.controle.CrudVendaService;




@SpringBootApplication
public class PooGestaoDeEstoqueApplication implements CommandLineRunner{


	
	private CrudLoteCompraService loteService;
	private CrudProdutoService npservice;
	private CrudVendaService nvservice;
	private VisaoProdutos visaoProd;
	private VisaoLotes visaoLotes;
	private VisaoVendas visaoVendas;
	
	
	@Autowired
	private ApplicationContext context;

	public void shutdownApp() {

	    int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
	    System.exit(exitCode);
	}
	
	public PooGestaoDeEstoqueApplication( VisaoVendas visaoVendas,CrudLoteCompraService loteService,
			CrudProdutoService npservice, CrudVendaService nvservice, VisaoProdutos visaoProd,VisaoLotes visaoLotes) {
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
			System.out.println("Digite 0 para sair ou escolha sub-menu:");
			System.out.println("-----------");
			System.out.println("1-Produtos");
			System.out.println("2-Compras");
			System.out.println("3-Vendas");
			
			
			//int opcao=scanner.nextInt();
			
			int opcao=-1;
			try {
				opcao=scanner.nextInt();
			}catch(InputMismatchException e){
				scanner.nextLine();//System.out.println("\nEntrada Invalida!\n");
				opcao=-1;
			}

			
			
			switch(opcao) {
			case 0:
				gatilho=false;
				//SpringApplication.run(PooGestaoDeEstoqueApplication.class, args).close();
				shutdownApp();//this.npservice.menu(scanner);
				break;
			
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
				System.out.println("\nEscolha Invalida!\n");
				break;
			}
			
		}
	}
}
