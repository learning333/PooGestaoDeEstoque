package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Lote;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewProduto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewVenda;
import br.com.ufabc.poo_gestao_de_estoque.repository.LoteRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.NewProdutoRepository;




@Service
public class CrudLoteService {
	private LoteRepository loteRepository;
	private NewProdutoRepository newProdutoRepository;
	
	public CrudLoteService(LoteRepository loteRepository,NewProdutoRepository newProdutoRepository) {
		this.loteRepository = loteRepository;
		this.newProdutoRepository= newProdutoRepository;
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
			//System.out.println("4-Deletar Lote");
			//System.out.println("5-Visualizar Lote");
			
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
			case 4:
				this.deletar(scanner);
				break;
			case 5:
				//this.fichaprof(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.print("Id do produto:");
		Long idnewProduto=scanner.nextLong();
		
		Optional<NewProduto> optional = this.newProdutoRepository.findById(idnewProduto);
		// produto esta cadastrado?
		if(optional.isPresent()) {
			System.out.print("Digite quantidade: ");
			int qtd=scanner.nextInt();
			System.out.print("Digite referencia: ");
			String referencia=scanner.next();
			System.out.print("Digite data");
			String data=scanner.next();
			System.out.print("Digite preco unitario");
			float precoUn=scanner.nextFloat();
			NewProduto newProduto = optional.get();
			//				(String referencia, String data, String status, Produto produto)
			Lote lote=new Lote(referencia,data,"transito",newProduto,qtd,precoUn);
			this.loteRepository.save(lote);
			System.out.println("SALVO!\n\n");
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
	private void atualizar(Scanner scanner) {
		/*System.out.print("Digite o id do lote a ser atualizado: ");
		Long id=scanner.nextLong();

		Optional<Lote> optional = this.loteRepository.findById(id);
		if(optional.isPresent()) {
			
			System.out.print("Digite Nome: ");
			String nome=scanner.next();
			System.out.print("Digite o prontuario");
			String prontuario=scanner.next();
			
			Lote lote = optional.get();
			lote.setNome(nome);
			lote.setProntuario(prontuario);
			
			loteRepository.save(lote);
			System.out.print("Lote Atualizado");
		}
		else {
			System.out.println("ID INVALIDO\n");
		}*/
	}

	private void deletar(Scanner scanner) {/*
		System.out.print("Digite o id do lote a ser deletado: ");
		Long id=scanner.nextLong();

		Optional<Lote> optional = this.loteRepository.findById(id);
		if(optional.isPresent()) {
			Lote lote = optional.get();
			Long id2 =lote.getId();
			this.loteRepository.deleteById(id2);
			System.out.println("Lote deletado\n");
		}else {
			System.out.println("Id invalido");
		}
	}
	@Transactional//pra poder buscar as materias com fetch lazy
	private void fichaprof(Scanner scanner) {
		System.out.print("Digite o id do lote: ");
		Long id=scanner.nextLong();

		Optional<Lote> optional = this.loteRepository.findById(id);
		if(optional.isPresent()) {
			Lote lote = optional.get();
			System.out.println("Lote: {");
			System.out.println("ID: "+lote.getId());
			System.out.println("Nome: "+lote.getNome());
			System.out.println("Prontuario: "+lote.getProntuario());
			System.out.println("NewVendas: [");
			
			for(NewVenda newVenda: lote.getNewVendas()) {
				System.out.println("\tID: "+newVenda.getId());
				System.out.println("\tNome: "+newVenda.getNome());
				System.out.println("\tSemestre: "+newVenda.getSemestre());
				System.out.println();
			}
			
			System.out.println("]\n}");
		}else {
			System.out.println("id invalido\n");
		}*/
	}
}
