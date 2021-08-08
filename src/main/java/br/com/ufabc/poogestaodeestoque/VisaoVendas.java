package br.com.ufabc.poogestaodeestoque;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufabc.poogestaodeestoque.controle.CrudLoteCompraService;
import br.com.ufabc.poogestaodeestoque.controle.CrudProdutoService;
import br.com.ufabc.poogestaodeestoque.controle.CrudVendaService;
import br.com.ufabc.poogestaodeestoque.modelo.LoteCompra;
import br.com.ufabc.poogestaodeestoque.modelo.Venda;

@Service
public class VisaoVendas {
	
	@Autowired
	private CrudProdutoService crudProduto;
	@Autowired
	private CrudLoteCompraService crudLote;
	@Autowired
	private CrudVendaService crudVenda;
	

	
	
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova Venda");
			System.out.println("2-Listar Vendas");
			System.out.println("3-Listar Produtos Em Maos");
			System.out.println("4-Devolucao");

			
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
				this.visualizarEmMaos();
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
	
	
	private void listarLotesEmMaos() {
		System.out.println("-----------Listando Lotes Em Maos-----------");
		Iterable<LoteCompra> lista = this.crudLote.listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("em maos")){
				System.out.println(lote.listagemParaVenda());
			}
		}
		System.out.println("--------------------Fim---------------------");
	}
	
	
	private void cadastrar(Scanner scanner) {
		System.out.println("--------------Cadastrando nova venda--------------");
		listarLotesEmMaos();
		System.out.print("Digite o Id do lote: ");
		Long idlote=this.crudLote.InputId(scanner);
		
		Optional<LoteCompra> optional = this.crudLote.buscaPeloId(idlote);
		if(optional.isPresent()) {
			LoteCompra lote = optional.get();
			int qtdDisponivel=lote.getQtd()-lote.getQtdVendida();
			if(lote.getStatus().equals("em maos")) {//tem quantidade disponivel>0
				
				System.out.println("Produto:["+lote.getNomeProduto()+"]");
				System.out.println("Quantidade Disponivel:["+qtdDisponivel+"]");

				System.out.print("Qtd vendida:");
				int qtd=this.crudLote.InputQtdProduto(scanner);//valida que o user vai entrar um int
				
				//validacao se qtd esta disponivel pra venda:
				boolean filtro=true;
				while(filtro) {
					if(qtd>qtdDisponivel) {
						System.out.print("Quantidade inserida ["+qtd+"] maior do que quantidade disponivel ["+qtdDisponivel+"]" );
						System.out.print( "\nDigite um valor valido: ");
						qtd=this.crudLote.InputQtdProduto(scanner);
					}else {//digitou quantidade valida

						filtro=false;
					}
				}

				
				System.out.print("Preco unitario:");
				float precoVenda=this.crudLote.InputValorProduto(scanner);//valida que user vai entrar float
				float valorTotal=qtd*precoVenda;
				
				scanner.nextLine();
				System.out.print("nome cliente: ");
				String nome=scanner.nextLine();
				
				float lucro=(precoVenda-lote.getCusto())*qtd;
				
				Venda novaVenda=this.crudVenda.adicionarNovo(nome,precoVenda,lote,qtd,lucro);
				
							
				//lote.setQtdVendida(qtd);//qtdvendida+=qtd
				if(lote.getQtd()==lote.getQtdVendida()) {
					this.crudLote.encerraLote(lote);//vendeu toda quantidade do lote de compra
				}
				
				System.out.print("Salvo\n");
				System.out.println(novaVenda);
				
			}else {
				System.out.println("Pedido de compra nao disponivel [status="+lote.getStatus()+"]");
			}
		}else {
			System.out.println("id nao existe");
		}
		System.out.println("--------------------------------------------------");
	}
	
	
	
	private void visualizar() {
		System.out.println("-----------------Listando Vendas------------------");

		Iterable<Venda> lista = this.crudVenda.listarVendas();
		for(Venda venda: lista) {
				System.out.println(venda);
				System.out.println("\n");
		}
		System.out.println("-----------------------Fim------------------------");
		System.out.println();
	}
	
	
	
	
	private void visualizarEmMaos() {

		System.out.println("-----------Listando Lotes Em Maos-----------");

		Iterable<LoteCompra> lista = this.crudLote.listarLotes();
		for(LoteCompra lote: lista) {
			if(lote.getStatus().equals("em maos")){
				System.out.println(lote);
				System.out.println("\n");
			}
		}
		System.out.println("--------------------Fim---------------------");
		System.out.println();
		
	}

	
	
	private void devolucao(Scanner scanner) {
		System.out.println("-------------Nova devolucao de venda--------------");
		visualizar();
		
		System.out.print("Digite o id da venda: ");
		Long id=this.crudLote.InputId(scanner);
		Optional<Venda> optional = this.crudVenda.buscaPeloId(id);
		if(optional.isPresent()) {
			Venda venda = optional.get();
			
			
			try {
				venda=this.crudVenda.entradaDevolucao(venda);//mudar o status da venda para "devolvido"
				LoteCompra lotecompra=venda.getLote();
				lotecompra=this.crudLote.devolucaoDeVenda(lotecompra, venda.getQtd());//devolver quantidade para o lotecompra:
				System.out.print("Concluido!\n");
				System.out.println("Venda devolvida: ");
				System.out.println(venda);
				System.out.println("------------------------------");
				System.out.println("Item retornado ao estoque: ");
				System.out.println(lotecompra);
			} catch (Exception e) {
				
				System.out.print(e);
			}
			
			


			

			
			
		}else {
			System.out.print("ID do lote nao existe");
		}
		System.out.println("--------------------------------------------------");
	}
}
