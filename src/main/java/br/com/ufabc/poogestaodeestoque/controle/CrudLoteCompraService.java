package br.com.ufabc.poogestaodeestoque.controle;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poogestaodeestoque.modelo.LoteCompra;
import br.com.ufabc.poogestaodeestoque.modelo.LoteCompraRepository;
import br.com.ufabc.poogestaodeestoque.modelo.Produto;
import br.com.ufabc.poogestaodeestoque.modelo.ProdutoRepository;




@Service
public class CrudLoteCompraService {
	
	private LoteCompraRepository loteRepository;
	private ProdutoRepository newProdutoRepository;
	

	
	
	public CrudLoteCompraService( LoteCompraRepository loteRepository,
			ProdutoRepository newProdutoRepository) {
		super();

		this.loteRepository = loteRepository;
		this.newProdutoRepository = newProdutoRepository;
	}/*
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
	}*/
	public LoteCompra adicionarNovo(String referencia, String data,String status,Produto produto,int qtd,float precoUn) {
		LoteCompra novoLote= new LoteCompra(referencia,data,status,produto,qtd,precoUn);
		this.loteRepository.save(novoLote);
		return novoLote;
	}
	public Iterable<LoteCompra> listarLotes(){

		Iterable<LoteCompra> lista = this.loteRepository.findAll();
		return lista;
	}
	public Optional<LoteCompra> buscaPeloId(Long id){
		Optional<LoteCompra> optional =this.loteRepository.findById(id);
		return optional;
	}
	public LoteCompra recebeLote(LoteCompra lote) {
		lote.setStatus("em maos");
		this.loteRepository.save(lote);
		return lote;
	}
	public LoteCompra reativaLote(LoteCompra lote,int qtd) {
		lote.setQtdVendida(-qtd);	//qtdVendida+=qtd
		lote.setStatus("em maos");
		this.loteRepository.save(lote);
		return lote;
	}
	public LoteCompra encerraLote(LoteCompra lote) {
		lote.setStatus("encerrado");
		this.loteRepository.save(lote);
		return lote;
	}

}
