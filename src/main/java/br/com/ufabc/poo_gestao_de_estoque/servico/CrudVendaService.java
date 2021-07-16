package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoEmMaos;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoVenda;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Venda;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemVendaRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoEmMaosRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoVendaRepository;

@Service
public class CrudVendaService {
	private OrdemVendaRepository vendaRepository;
	private OrdemCompraRepository compraRepository;
	private ProdutoVendaRepository produtoVendaRepository;
	private ProdutoEmMaosRepository produtoEmMaosRepository;
	private List<ProdutoVenda> lista;
	
	
	public CrudVendaService(OrdemVendaRepository vendaRepository, OrdemCompraRepository compraRepository,
			ProdutoVendaRepository produtoVendaRepository, ProdutoEmMaosRepository produtoEmMaosRepository,
			List<ProdutoVenda> lista) {
		super();
		this.vendaRepository = vendaRepository;
		this.compraRepository = compraRepository;
		this.produtoVendaRepository = produtoVendaRepository;
		this.produtoEmMaosRepository = produtoEmMaosRepository;
		this.lista = lista;
	}
	
	@Transactional
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova Ordem de Venda");
			System.out.println("2-Visualizar Ordem de Venda");
			System.out.println("3-Devolução");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				//this.visualizar(scanner);
				break;
			case 3:
				//this.devolucao(scanner);
				break;
			case 4:
				//this.recebimento(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.print("Digite o nome do cliente: ");
		String nome=scanner.next();
		System.out.print("Digite o nome da Plataforma de venda: ");
		String plataforma=scanner.next();
		
		boolean gatilho=true;
		float vtotal=0,ltotal=0;
		while(gatilho) {
			List<ProdutoEmMaos> listavelha=new ArrayList<ProdutoEmMaos>();
			float vlinha=0;
			float llinha=0;
			String statusestoque="transito";
			
			System.out.println("\nAdicionando Pedido: [" +nome+"]   Escolha acao:");
			System.out.println("0-Voltar");
			System.out.println("1-Adicionar Novo Produto");
			System.out.println("2-Fechar Pedido");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				System.out.print("Nome Produto:");
				String nomeprod=scanner.next();
				//verifica se produto existe na tabela produtosComprados com status em mãos
				Optional<ProdutoEmMaos> optional=this.produtoEmMaosRepository.findProducto(nomeprod);
				if(optional.isPresent()) {//se existir
					ProdutoEmMaos produto =    optional.get();
					int saldo=produto.getQtdOriginal()-produto.getQtdVendida();
					System.out.print("[Disponivel["+saldo +"]    Qtd Venda:");
					int qtd=scanner.nextInt();
					System.out.print("Valor Unitario:");
					float valor=scanner.nextFloat();

					ProdutoVenda novo= new ProdutoVenda(qtd,valor);
					//valor*qtd,pedido);
					novo.setNome(produto.getNome());
					novo.setDescricao(produto.getDescricao());
					novo.setLote(produto.getPedidoOriginal());
					novo.setValorTotal(valor*qtd);
					novo.setLucroTotal(qtd*(valor-produto.getCustoUn()));
					novo.setStatus("ok");
					
					
					
					System.out.print("Produto antes do lista add: "+novo);
					lista.add(novo);
					
					produto.setQtdVendida(produto.getQtdVendida()+qtd);
					listavelha.add(produto);
					llinha+=qtd*(valor-produto.getCustoUn());
					vlinha+=valor*qtd;
					ltotal+=llinha;
					vtotal+=vlinha;
					
				}else {
					System.out.println("Produto nao disponivel no estoque.");
				}
				break;
			case 2:
				                 //venda((float valorTotal, float lucroTotal, String status, String plataforma, String nome_cliente))
				Venda pedidoVenda=new Venda(vtotal,ltotal,"ok",plataforma,nome);
				//System.out.print("compra antes do .save"+pedidoCompra);
				vendaRepository.save(pedidoVenda);
				for(ProdutoVenda produto: lista) {
					produtoVendaRepository.save(produto);
				}
				for(ProdutoEmMaos produto: listavelha) {
					produtoEmMaosRepository.save(produto);//atualiza qtd vendida na tabela estoque
				}
				lista.clear();//limpa a lista
				listavelha.clear();

				gatilho=false;
				break;
			case 3:
				
				break;

			default:
				gatilho=false;
			}
			
		}//while
	}
	/*
	private void visualizar(Scanner scanner) {
		System.out.print("Digite a referencia para o pedido de compra: ");
		String pedido=scanner.next();
		List<ProdutoComprado> resultado = produtoCompraRepository.findOrdem(pedido);//isso retorna um collection de produtos cadastrados do pedido
        Iterator<ProdutoComprado> iterator = resultado.iterator();

        while (iterator.hasNext()) {
        	System.out.println("value= " + iterator.next());
        }
	}
	
	private void deletar(Scanner scanner) {
		System.out.print("Digite o id do pedido de compra: ");
		Long id=scanner.nextLong();
		Optional<Compra> optional = this.compraRepository.findById(id);
		if(optional.isPresent()) {
			Compra compra = optional.get();
			Long id2 =compra.getId();
			
			List<ProdutoComprado> resultado = produtoCompraRepository.findOrdem(compra.getPedido());//isso retorna um collection de produtos cadastrados do pedido
	        Iterator<ProdutoComprado> iterator = resultado.iterator();

	        while (iterator.hasNext()) {//deleta os itens do pedido na tabela correspondente
	        	produtoCompraRepository.deleteById(iterator.next().getId());;
	        }
			
			
			this.compraRepository.deleteById(id2);
			System.out.println("pedido deletado\n");
		}else {
			System.out.println("Id invalido");
		}
	}
	private void recebimento(Scanner scanner) {
		System.out.print("Digite o id do pedido de compra: ");
		Long id=scanner.nextLong();
		Optional<Compra> optional = this.compraRepository.findById(id);
		if(optional.isPresent()) {
			Compra compra = optional.get();
			compra.setStatusEstoque("maos");
			compraRepository.save(compra);//faz update com o novo status
		}
		else {
			System.out.println("Id invalido");
		}
	}
	
	*/
}
