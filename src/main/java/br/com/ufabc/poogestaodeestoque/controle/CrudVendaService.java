package br.com.ufabc.poogestaodeestoque.controle;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ufabc.poogestaodeestoque.modelo.LoteCompra;
import br.com.ufabc.poogestaodeestoque.modelo.LoteCompraRepository;
import br.com.ufabc.poogestaodeestoque.modelo.Venda;
import br.com.ufabc.poogestaodeestoque.modelo.VendaRepository;




@Service
public class CrudVendaService {

	private LoteCompraRepository loteRepository;
	private VendaRepository vendaRepository;
	
	public CrudVendaService(LoteCompraRepository loteRepository, VendaRepository vendaRepository) {

		this.loteRepository = loteRepository;
		this.vendaRepository=vendaRepository;
	}
	
	
	
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova Venda");
			System.out.println("2-Listar Vendas");
			System.out.println("3-Listar Produtos Em Maos");
			System.out.println("4-Devolucao");

			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.visualizar();
				break;
			case 3:
				this.estoqueEmMaos();
				break;
			case 4:
				this.devolucao(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		Iterable<LoteCompra> lista = this.loteRepository.findAll();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("em maos")){
				System.out.println(lote.listagemParaVenda());
			}
		}
		System.out.println();
		System.out.print("Digite o Id do lote de origem do produto a ser vendido: ");
		Long idprof=scanner.nextLong();
		
		Optional<LoteCompra> optional = this.loteRepository.findById(idprof);
		if(optional.isPresent()) {
			LoteCompra lote = optional.get();
			int qtdDisponivel=lote.getQtd()-lote.getQtdVendida();
			if(lote.getStatus().equals("em maos")) {//tem quantidade disponivel>0
				
				System.out.println("Produto:["+lote.getNomeProduto()+"]");
				System.out.println("Quantidade Disponivel:["+qtdDisponivel+"]");

				System.out.print("Qtd vendida:");
				int qtd=scanner.nextInt();
				
				
				boolean filtro=true;
				while(filtro) {
					if(qtd>qtdDisponivel) {
						System.out.print("Quantidade inserida ["+qtd+"] maior do que quantidade disponivel ["+qtdDisponivel+"]" );
						System.out.print( "\nDigite um valor valido: ");
						qtd=scanner.nextInt();
					}else {//digitou quantidade valida

						filtro=false;
					}
				}

				
				System.out.print("Preco unitario:");
				float precoVenda=scanner.nextFloat();
				float valorTotal=qtd*precoVenda;
				
				System.out.print("nome cliente: ");
				String nome=scanner.next();
				
				float lucro=(precoVenda-lote.getCusto())*qtd;
				Venda newVenda=new Venda(nome,precoVenda,lote,qtd,lucro);
				newVenda.setStatus("normal");
				lote.setQtdVendida(lote.getQtdVendida()+qtd);
				vendaRepository.save(newVenda);
				
				
				if(qtdDisponivel-qtd==0) {
					lote.setStatus("encerrado");//vendeu toda quantidade do lote de compra
				}
				loteRepository.save(lote);//update qtd vendida
				
				
				//LivroCaixa operacaoVenda= new LivroCaixa(lote,"venda",valorTotal);
				//this.livroCaixaRepository.save(operacaoVenda);
				System.out.print("Salvo\n");
			}else {
				System.out.print("Pedido de compra nao disponivel [status="+lote.getStatus()+"]");
			}
		}else {
			System.out.print("id nao existe");
		}

	}
	private void visualizar() {
		Iterable<Venda> lista = this.vendaRepository.findAll();
		for(Venda newVenda: lista) {
			System.out.println(newVenda);
		}
		System.out.println();
	}
	private void estoqueEmMaos() {
		Iterable<LoteCompra> lista = this.loteRepository.findAll();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("em maos")){
				System.out.println(lote);
			}
		}
		System.out.println();
	}
	private void devolucao(Scanner scanner) {
		
		Iterable<Venda> lista = this.vendaRepository.findAll();
		for(Venda newVenda: lista) {
			if(newVenda.getStatus().equals("normal")){
				System.out.println(newVenda);
				System.out.println();
			}
			
		}
		System.out.println();
		
		System.out.print("Digite o id da venda: ");
		Long id=scanner.nextLong();
		Optional<Venda> optional = this.vendaRepository.findById(id);
		if(optional.isPresent()) {
			Venda venda = optional.get();
			venda.setStatus("devolvido");
			this.vendaRepository.save(venda);//update
			
			//devolver quantidade para o lotecompra

			LoteCompra lotecompra=venda.getLote();//optLote.get();
			lotecompra.setQtdVendida(-venda.getQtd());
			if(lotecompra.getStatus().equals("encerrado")) {//se estava zerado reativa o lote compra
				lotecompra.setStatus("em maos");
			}
			this.loteRepository.save(lotecompra);//update
			System.out.println("Venda devolvida: ");
			System.out.println(venda);
			System.out.println("------------------------------");
			System.out.println("Item retornado ao estoque: ");
			System.out.println(lotecompra);
			System.out.println("------------------------------");
			System.out.print("Concluido!\n");
		}else {
			System.out.print("ID do lote nao existe");
		}
	}

	public Optional<Venda> buscaPeloId(Long id){
		Optional<Venda> optional =this.vendaRepository.findById(id);
		return optional;
	}

	public Venda adicionarNovo(String nome, float precoVenda, LoteCompra lote, int qtd, float lucro) {
		Venda novo=new Venda(nome,precoVenda,lote,qtd,lucro);
		lote.setQtdVendida(qtd);
		this.loteRepository.save(lote);//update qtd vendida
		novo.setStatus("normal");
		this.vendaRepository.save(novo);
		return novo;
	}
	public Iterable<Venda> listarVendas(){

		Iterable<Venda> lista = this.vendaRepository.findAll();
		return lista;
	}
	public Venda entradaDevolucao(Venda venda) {
		venda.setStatus("devolvido");
		this.vendaRepository.save(venda);//update
		return venda;
	}

}
