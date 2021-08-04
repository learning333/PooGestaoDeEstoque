package br.com.ufabc.poo_gestao_de_estoque;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.controle.CrudLoteService;
import br.com.ufabc.poo_gestao_de_estoque.controle.CrudNewProdutoService;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Lote;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;


@Service
public class VisaoLotes {

	@Autowired
	private CrudNewProdutoService crudProduto;
	@Autowired
	private CrudLoteService crudLote;
	
	
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

		
		System.out.print("Digite data");
		String data=scanner.next();
		System.out.print("Digite referencia: ");
		String referencia=scanner.next();
		
		//visualizar produtos disponiveis para compra
		System.out.println("----------Listando Produtos Cadastrados-----------");
		Iterable<NewProduto> lista = crudProduto.visualizarProdutos();
		for(NewProduto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
		//fim visualizacao
		
		System.out.print("Digite Id do produto:");
		Long idnewProduto=scanner.nextLong();
		
		Optional<NewProduto> optional = this.crudProduto.BuscarPeloId(idnewProduto);
		// produto esta cadastrado?
		if(optional.isPresent()) {
			System.out.print("Digite quantidade: ");
			int qtd=scanner.nextInt();
			System.out.print("Digite preco unitario");
			float precoUn=scanner.nextFloat();
			float valorTotal=qtd*precoUn;
			NewProduto newProduto = optional.get();
		
			Lote lote=crudLote.adicionarNovo(referencia, data, "transito", newProduto, qtd, precoUn);

			System.out.print(lote);

			System.out.println("\nSALVO!\n\n");
		}else {
			System.out.println("id newProduto não encontrado!\n\n");
		}
	}
	private void visualizar() {
		System.out.println("------------Listando Todas as Compras-------------");

		Iterable<Lote> lista = this.crudLote.listarLotes();
		for(Lote lote: lista) {
				System.out.println(lote);
				System.out.println("\n");
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	private void visualizarTransito() {
		System.out.println("-----------Listando Compras Em Transito-----------");

		Iterable<Lote> lista = this.crudLote.listarLotes();
		for(Lote lote: lista) {
			if(lote.getStatus().equals("transito")){
				System.out.println(lote);
				System.out.println("\n");
			}
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	
	private void recebimento(Scanner scanner) {
		visualizarTransito();
		

		System.out.print("Digite o id do pedido de compra: ");
		Long id=scanner.nextLong();
		Optional<Lote> optional = this.crudLote.buscaPeloId(id);
		
		if(optional.isPresent()) {
			Lote lote = optional.get();
			lote=this.crudLote.recebeLote(lote);
			System.out.println(lote);
			System.out.print("Concluido!\n");
		}else {
			System.out.print("ID do lote nao existe");
		}
	}

}
