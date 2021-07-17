package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Caixa;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoEmMaos;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoVenda;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Venda;
import br.com.ufabc.poo_gestao_de_estoque.repository.CaixaRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemVendaRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoEmMaosRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoVendaRepository;

@Service
public class CrudVendaService {
	private CaixaRepository caixaRepository;
	private OrdemVendaRepository vendaRepository;
	private OrdemCompraRepository compraRepository;
	private ProdutoVendaRepository produtoVendaRepository;
	private ProdutoEmMaosRepository produtoEmMaosRepository;
	private ProdutoCompraRepository produtoCompraRepository;
	private List<ProdutoVenda> lista;
	
	
	public CrudVendaService(CaixaRepository caixaRepository, OrdemVendaRepository vendaRepository, OrdemCompraRepository compraRepository,
			ProdutoVendaRepository produtoVendaRepository, ProdutoEmMaosRepository produtoEmMaosRepository,
			List<ProdutoVenda> lista,ProdutoCompraRepository produtoCompraRepository) {
		super();
		this.caixaRepository = caixaRepository;
		this.vendaRepository = vendaRepository;
		this.compraRepository = compraRepository;
		this.produtoVendaRepository = produtoVendaRepository;
		this.produtoEmMaosRepository = produtoEmMaosRepository;
		this.produtoCompraRepository = produtoCompraRepository;
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
				this.visualizar(scanner);
				break;
			case 3:
				this.devolucao(scanner);
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
					novo.setNome(produto.getNome());
					novo.setDescricao(produto.getDescricao());
					novo.setLote(produto.getPedidoOriginal());
					//novo.setValorTotal(valor*qtd);
					//novo.setLucroTotal(qtd*(valor-produto.getCustoUn()));
					novo.setStatus_venda("ok");
					
					
					
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
			case 2://fecha pedido venda
				
				                 //venda((float valorTotal, float lucroTotal, String status, String plataforma, String nome_cliente))
				Venda pedidoVenda=new Venda(vtotal,ltotal,"ok",plataforma,nome);
				//System.out.print("compra antes do .save"+pedidoCompra);
				vendaRepository.save(pedidoVenda);
				
				Caixa operacao=new Caixa(pedidoVenda.getId(),"VENDA",vtotal);//entra vtotal no caixa
				caixaRepository.save(operacao);
				
				
				Long id_futuro=pedidoVenda.getId();
				for(ProdutoVenda produto: lista) {
					produto.setId_venda(id_futuro);
					produtoVendaRepository.save(produto);
				}
				for(ProdutoEmMaos produto: listavelha) {
					produtoEmMaosRepository.save(produto);//atualiza qtd vendida na tabela estoque
				}
				lista.clear();//limpa a lista
				listavelha.clear();

				gatilho=false;
				break;
			default:
				gatilho=false;
			}
			
		}//while
	}
	
	private void visualizar(Scanner scanner) {
		System.out.print("Digite o id da venda: ");
		Long id=scanner.nextLong();
		Optional<Venda> resultado = vendaRepository.findById(id);// findOrdem(cliente);//isso retorna um collection de produtos cadastrados do pedido
		if(resultado.isPresent()) {//se existeir
			Venda venda=resultado.get();
			Long idbuscar=venda.getId();
			List<ProdutoVenda> result2=produtoVendaRepository.findByIdVenda(idbuscar);
			Iterator<ProdutoVenda> iterator = result2.iterator();
			
			while (iterator.hasNext()) {
				ProdutoVenda aux=iterator.next();
	        	System.out.println("value= " + aux);
	        	
			}
		}
	}
	private void devolucao(Scanner scanner) {
		System.out.print("Digite o id da venda: ");
		Long id=scanner.nextLong();
		Optional<Venda> resultado = vendaRepository.findById(id);// findOrdem(cliente);//isso retorna um collection de produtos cadastrados do pedido
		if(resultado.isPresent()) {//se existir ordem digitada
			Venda venda=resultado.get();
			venda.setStatus("dev");//altera status da ordem
			vendaRepository.save(venda);//atualiza ordemvenda com status dev
			
			Caixa operacao=new Caixa(venda.getId(),"DEVOLUCAO_VENDA",-venda.getValorTotal());//tira do caixa dinheiro da venda
			caixaRepository.save(operacao);
			
			
			Long idbuscar=venda.getId();
			//busca todos os produtos da ordem de venda
			List<ProdutoVenda> result2=produtoVendaRepository.findByIdVenda(idbuscar);
			Iterator<ProdutoVenda> iterator = result2.iterator();
			
			while (iterator.hasNext()) {
				ProdutoVenda aux=iterator.next();
				
				
				//alterar statusvenda do produtovenda
				aux.setStatus_venda("dev");
				produtoVendaRepository.save(aux);//update na tabela produtovenda
				
				//devolver qtd pra tabela estoque
				String lotebuscar=aux.getLote();
				Optional<ProdutoEmMaos> optional=produtoEmMaosRepository.findByLoteAndProduto(lotebuscar,aux.getNome());
				if(optional.isPresent()) {//se registro ainda existia na tabela estoque
					ProdutoEmMaos aux2=optional.get();
					aux2.setQtdVendida(aux2.getQtdVendida()-aux.getQtd());//devolve quantidade pro estoque
					produtoEmMaosRepository.save(aux2);//update na tabela estoque em maos
					
				}else {//registro nao existia mais
					//1-buscar produtoComprado por lote_original de aux
					Optional<ProdutoComprado> optcomprado=produtoCompraRepository.findByLote(lotebuscar);
					if(optcomprado.isPresent()) {//se encontrou
						ProdutoComprado aux2=optcomprado.get();
						//prepara um novo lancamento pra tabela estoque
						ProdutoEmMaos novo =new ProdutoEmMaos(aux2.getPedidoCompra(),aux2.getQtd(),aux2.getValorUn());
						novo.setNome(aux.getNome());
						novo.setDescricao(aux.getDescricao());
						//seta quantidade vendida como original-qtd devolvida
						novo.setQtdVendida(aux2.getQtd()-aux.getQtd());
						produtoEmMaosRepository.save(novo);//"devolve" pro estoque
					}else {
						System.out.println("Produto nao encontrado, algo de errado nao esta certo");
					}
				}
			}//while
		}else {
			System.out.println("Id de venda nao encontrado");
		}
	}
}
