package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Produto;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoCadastrado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoRepository;

@Service
public class CrudCompraService {
	private OrdemCompraRepository compraRepository;
	private ProdutoRepository produtoRepository;
	private ProdutoCompraRepository produtoCompraRepository;
	private List<ProdutoComprado> lista;
	
	
	
	public CrudCompraService(OrdemCompraRepository compraRepository,ProdutoRepository produtoRepository,ProdutoCompraRepository produtoCompraRepository,List<ProdutoComprado> lista) {
		super();
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.produtoCompraRepository = produtoCompraRepository;
		this.lista=lista;
	}


	
	@Transactional
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Nova Ordem de compra");
			System.out.println("2-Visualizar Ordem de compra");
			System.out.println("3-Deletar Ordem de compra");
			System.out.println("4-Recebimento");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.visualizar(scanner);
				break;
			case 3:
				this.deletar(scanner);
				break;
			case 4:
				this.recebimento(scanner);
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.print("Digite a referencia para o pedido de compra: ");
		String pedido=scanner.next();
		
		boolean gatilho=true;
		float vtotal=0;
		while(gatilho) {
			float vlinha=0;
			String statusestoque="transito";
			
			System.out.println("\nAdicionando Pedido: [" +pedido+"]   Escolha acao:");
			System.out.println("0-Voltar");
			System.out.println("1-Adicionar Novo Produto");
			System.out.println("2-Fechar Pedido");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				System.out.print("Nome Produto:");
				String nomeprod=scanner.next();
				//verifica se produto existe na tabela produtos
				Optional<ProdutoCadastrado> optional=this.produtoRepository.findProduct(nomeprod);
				if(optional.isPresent()) {//se existeir
					ProdutoCadastrado produto =    optional.get();
					
					System.out.print("Qtd:");
					int qtd=scanner.nextInt();
					System.out.print("Valor Unitario:");
					float valor=scanner.nextFloat();

					ProdutoComprado novo= new ProdutoComprado(qtd,valor,valor*qtd,pedido);
					novo.setNome(produto.getNome());
					novo.setDescricao(produto.getDescricao());
					System.out.print("Produto antes do lista add: "+novo);
					lista.add(novo);
					vlinha+=valor*qtd;
					vtotal+=vlinha;
				}else {
					System.out.println("Produto nao cadastrado.");
				}
				break;
			case 2:
				Compra pedidoCompra=new Compra(pedido,vtotal,statusestoque);
				System.out.print("compra antes do .save"+pedidoCompra);
				compraRepository.save(pedidoCompra);
				for(ProdutoComprado produto: lista) {
					produtoCompraRepository.save(produto);
				}
				lista.clear();//limpa a lista
				

				gatilho=false;
				break;
			case 3:
				
				break;

			default:
				gatilho=false;
			}
			
		}//while
	}

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
}