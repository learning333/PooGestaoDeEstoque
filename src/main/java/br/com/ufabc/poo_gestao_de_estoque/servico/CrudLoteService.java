package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.ufabc.poo_gestao_de_estoque.modelo.Lote;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;

import br.com.ufabc.poo_gestao_de_estoque.repository.LoteRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.NewProdutoRepository;




@Service
public class CrudLoteService {
	
	private LoteRepository loteRepository;
	private NewProdutoRepository newProdutoRepository;
	

	
	
	public CrudLoteService( LoteRepository loteRepository,
			NewProdutoRepository newProdutoRepository) {
		super();

		this.loteRepository = loteRepository;
		this.newProdutoRepository = newProdutoRepository;
	}
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
		
		System.out.println("----Listando Produtos Disponiveis-----");
		Iterable<NewProduto> lista = this.newProdutoRepository.findAll();
		for(NewProduto produto: lista) {
			
			System.out.println(produto);
		}
		System.out.println("-------Fim da listagem----");
		System.out.println();
		
		
		System.out.print("Digite Id do produto:");
		Long idnewProduto=scanner.nextLong();
		
		Optional<NewProduto> optional = this.newProdutoRepository.findById(idnewProduto);
		// produto esta cadastrado?
		if(optional.isPresent()) {
			System.out.print("Digite quantidade: ");
			int qtd=scanner.nextInt();
			System.out.print("Digite preco unitario");
			float precoUn=scanner.nextFloat();
			float valorTotal=qtd*precoUn;
			NewProduto newProduto = optional.get();
			//				(String referencia, String data, String status, Produto produto)
			Lote lote=new Lote(referencia,data,"transito",newProduto,qtd,precoUn);
			this.loteRepository.save(lote);
			System.out.println();
			System.out.print(lote);

			System.out.println("\nSALVO!\n\n");
		}else {
			System.out.println("id newProduto não encontrado!\n\n");
		}
	}
	private void visualizar() {
		Iterable<Lote> lista = this.loteRepository.findAll();
		for(Lote lote: lista) {
			System.out.println(lote);
		}
		System.out.println();
	}
	private void recebimento(Scanner scanner) {
		System.out.println("-------Listando Lotes Disponiveis-----");
		Iterable<Lote> lista = this.loteRepository.findAll();
		for(Lote lote: lista) {
			if(lote.getStatus().equals("transito")){
				System.out.println(lote);
				System.out.println();
			}
		}
		System.out.println("-------Fim da listagem----");
		System.out.println();
		
		System.out.println();
		System.out.print("Digite o id do pedido de compra: ");
		Long id=scanner.nextLong();
		Optional<Lote> optional = this.loteRepository.findById(id);
		if(optional.isPresent()) {
			Lote lote = optional.get();
			lote.setStatus("em maos");
			this.loteRepository.save(lote);//update
			System.out.print("Concluido!\n");
		}else {
			System.out.print("ID do lote nao existe");
		}
	}

}
