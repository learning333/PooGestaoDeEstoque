package br.com.ufabc.poogestaodeestoque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poogestaodeestoque.controle.CrudLoteCompraService;
import br.com.ufabc.poogestaodeestoque.controle.CrudProdutoService;
import br.com.ufabc.poogestaodeestoque.modelo.LoteCompra;
import br.com.ufabc.poogestaodeestoque.modelo.Produto;


@Service
public class VisaoLotes {

	@Autowired
	private CrudProdutoService crudProduto;
	@Autowired
	private CrudLoteCompraService crudLote;
	
	
	@Transactional
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova compra");
			System.out.println("2-Listar lotes");
			System.out.println("3-Recebimento");

			
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
			case 3:
				this.recebimento(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.println("-------------Cadastrando nova compra--------------");
		
		System.out.print("Digite data: [dd-mm-aa]");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
		Date data=null;
		try {
			data=dateFormat.parse(scanner.next());
		} catch (ParseException e) {
		    System.out.println("Data invalida nao registrada");
		}
		

		
		
		scanner.nextLine();
		System.out.print("Digite referencia para o pedido: ");
		String referencia=scanner.nextLine();
		
		//visualizar produtos disponiveis para compra
		System.out.println("\n-------Listando Produtos Cadastrados--------");
		Iterable<Produto> lista = crudProduto.visualizarProdutos();
		for(Produto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("--------------------Fim---------------------");
		System.out.println();
		//fim visualizacao
		
		System.out.print("Digite Id do produto:");
		Long idnewProduto=this.crudLote.InputId(scanner);
		
		Optional<Produto> optional = this.crudProduto.BuscarPeloId(idnewProduto);
		// produto esta cadastrado?
		if(optional.isPresent()) {
			System.out.print("Digite quantidade: ");
			int qtd=this.crudLote.InputQtdProduto(scanner);
			System.out.print("Digite preco unitario");
			float precoUn=this.crudLote.InputValorProduto(scanner);
			float valorTotal=qtd*precoUn;
			Produto newProduto = optional.get();
		
			LoteCompra lote=crudLote.adicionarNovo(referencia, data, "transito", newProduto, qtd, precoUn);
			
			System.out.println("\nSALVO!\n\n");
			System.out.print(lote);

			
		}else {
			System.out.println("id newProduto não encontrado!\n\n");
		}
		System.out.println("\n--------------------------------------------------");
	}
	private void visualizar() {
		System.out.println("------------Listando Todas as Compras-------------");

		Iterable<LoteCompra> lista = this.crudLote.listarLotes();
		for(LoteCompra lote: lista) {
				System.out.println(lote);
				System.out.println("\n");
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	private void visualizarTransito() {
		System.out.println("--------Listando Compras Em Transito--------");

		Iterable<LoteCompra> lista = this.crudLote.listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("transito")){
				System.out.println(lote);
				System.out.println("\n");
			}
		}
		System.out.println("--------------------Fim---------------------");
		System.out.println();
	}
	
	private void recebimento(Scanner scanner) {
		System.out.println("-------------------Recebimento--------------------");
		visualizarTransito();
		

		System.out.print("Digite o id do pedido de compra: ");
		Long id=this.crudLote.InputId(scanner);
		Optional<LoteCompra> optional = this.crudLote.buscaPeloId(id);
		
		if(optional.isPresent()) {
			LoteCompra lote = optional.get();
			lote=this.crudLote.recebeLote(lote);
			
			System.out.println(lote);
			
		}else {
			System.out.println("ID do lote nao existe");
		}
		System.out.println("--------------------------------------------------");
	}

}
