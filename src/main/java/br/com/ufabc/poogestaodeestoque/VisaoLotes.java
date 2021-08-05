package br.com.ufabc.poogestaodeestoque;

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

			
			int opcao=scanner.nextInt();
			
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
		
		System.out.print("Digite data");
		String data=scanner.next();
		System.out.print("Digite referencia para o pedido: ");
		String referencia=scanner.next();
		
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
		Long idnewProduto=scanner.nextLong();
		
		Optional<Produto> optional = this.crudProduto.BuscarPeloId(idnewProduto);
		// produto esta cadastrado?
		if(optional.isPresent()) {
			System.out.print("Digite quantidade: ");
			int qtd=scanner.nextInt();
			System.out.print("Digite preco unitario");
			float precoUn=scanner.nextFloat();
			float valorTotal=qtd*precoUn;
			Produto newProduto = optional.get();
		
			LoteCompra lote=crudLote.adicionarNovo(referencia, data, "transito", newProduto, qtd, precoUn);
			
			System.out.println("\nSALVO!\n\n");
			System.out.print(lote);

			
		}else {
			System.out.println("id newProduto não encontrado!\n\n");
		}
		System.out.println("--------------------------------------------------");
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
		Long id=scanner.nextLong();
		Optional<LoteCompra> optional = this.crudLote.buscaPeloId(id);
		
		if(optional.isPresent()) {
			LoteCompra lote = optional.get();
			lote=this.crudLote.recebeLote(lote);
			System.out.print("Recebimento Concluido!\n");
			System.out.println(lote);
			
		}else {
			System.out.print("ID do lote nao existe");
		}
		System.out.println("--------------------------------------------------");
	}

}
