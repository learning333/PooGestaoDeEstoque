package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Caixa;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoComprado;
import br.com.ufabc.poo_gestao_de_estoque.modelo.ProdutoEmMaos;
import br.com.ufabc.poo_gestao_de_estoque.modelo.Venda;
import br.com.ufabc.poo_gestao_de_estoque.repository.CaixaRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemVendaRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoCompraRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoEmMaosRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.ProdutoVendaRepository;

@Service
public class CaixaService {
	private CaixaRepository caixaRepository;
	//transito
	private OrdemCompraRepository compraRepository;
	private ProdutoCompraRepository produtoCompraRepository;
	//estoque
	private ProdutoEmMaosRepository produtoEmMaosRepository;
	//venda
	private OrdemVendaRepository vendaRepository;
	private ProdutoVendaRepository produtoVendaRepository;
	
	
	public CaixaService(CaixaRepository caixaRepository, OrdemCompraRepository compraRepository,
			ProdutoCompraRepository produtoCompraRepository, ProdutoEmMaosRepository produtoEmMaosRepository,
			OrdemVendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		super();
		this.caixaRepository = caixaRepository;
		this.compraRepository = compraRepository;
		this.produtoCompraRepository = produtoCompraRepository;
		this.produtoEmMaosRepository = produtoEmMaosRepository;
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}

	
	@Transactional
	public void menu(Scanner scanner) {
		boolean gatilho=true;
		while(gatilho) {
			System.out.println("\nEscolha acao");
			System.out.println("0-voltar");
			System.out.println("1-Situacao");
			System.out.println("2-Adicionar Entrada");
			System.out.println("3-Adicionar Saida");
			System.out.println();
			System.out.println("Relatorios:");
			System.out.println("4-Estoque");
			System.out.println("5-Transito");
			System.out.println("6-Faturamento Mes");


			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.situacao();
				break;
			case 2:
				this.entrada(scanner);
				break;
			case 3:
				this.saida(scanner);
				break;
			case 4:
				this.relatorioEstoque();
				break;
			case 5:
				this.relatorioTransito();
				break;
			case 6:
				this.faturamentoMes();
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	public void situacao() {
		System.out.println("------------------------------");
		Iterable<Caixa> registros=caixaRepository.findAll();
		Iterator<Caixa> iterator=registros.iterator();
		float caixaatual=0;
		while (iterator.hasNext()) {
			Caixa aux=iterator.next();
			caixaatual+=aux.getValor();
		}
		System.out.println("|Caixa: "+caixaatual+"               |");
		System.out.println("------------------------------");
	}
	public void entrada(Scanner scanner) {
		System.out.print("Digite o montante a ser adicionado ao caixa:");
		float valor=scanner.nextFloat();
		Caixa operacao = new Caixa((long) 0,"ENTRADA_MANUAL",valor);
		caixaRepository.save(operacao);
	}
	public void saida(Scanner scanner) {
		System.out.print("Digite o montante a ser retirado do caixa:");
		float valor=scanner.nextFloat();
		Caixa operacao = new Caixa((long) 0,"SAIDA_MANUAL",-valor);
		caixaRepository.save(operacao);
	}
	
	
	public void relatorioEstoque() {
		System.out.println("------------------------------");
		Iterable<ProdutoEmMaos> registros=produtoEmMaosRepository.findAll();
		Iterator<ProdutoEmMaos> iterator=registros.iterator();
		//Iterator<ProdutoEmMaos> iterator2=registros.iterator();
		List<ProdutoEmMaos> listaauxiliar=new ArrayList<ProdutoEmMaos>();
		float valor=0;
		while (iterator.hasNext()) {
			ProdutoEmMaos aux=iterator.next();
			listaauxiliar.add(aux);
			valor+=(aux.getCustoUn()*(aux.getQtdOriginal()-aux.getQtdVendida()));
		}
		System.out.println("|Valor estoque em maos: "+valor+"|");
		System.out.println("------------------------------");
		for(ProdutoEmMaos p : listaauxiliar) {
			System.out.println(p);
		}
	}
	public void relatorioTransito() {
		
		System.out.println("------------------------------");
		Iterable<Compra> registros=compraRepository.findByStatus("transito");
		List<Compra> listaOC=new ArrayList<Compra>();
		Iterator<Compra> iterator=registros.iterator();
		float valor=0;
		while (iterator.hasNext()) {
			Compra aux=iterator.next();
			valor+=(aux.getvalorTotal());
			listaOC.add(aux);
		}
		System.out.println("|Valor transito: "+valor+"   |");
		System.out.println("------------------------------");
		for(Compra c : listaOC) {
			List<ProdutoComprado> produtos=produtoCompraRepository.findOrdem(c.getPedido());
			Iterator<ProdutoComprado> produtoaux=produtos.iterator();
			
			while (produtoaux.hasNext()) {
				ProdutoComprado aux2=produtoaux.next();
				System.out.println(aux2);
			}
			
		}
	}
	
	public void faturamentoMes() {
		LocalDate hoje = LocalDate.now();
		String left=String.valueOf(hoje).substring(0,7);
		
		Iterable<Venda> registros=vendaRepository.findByYearMonth(left);
		Iterator<Venda> iterator=registros.iterator();
		List<Venda> listaOV=new ArrayList<Venda>();
		float valor=0,lucro=0;
		while (iterator.hasNext()) {
			Venda aux=iterator.next();
			valor+=aux.getValorTotal();
			lucro+=aux.getLucroTotal();
			
		}
		System.out.println("--------"+left+"---------------");
		System.out.println("|                             |");
		System.out.println("|Vendido: "+valor+"                 |");
		System.out.println("|Lucro: "+lucro+"                   |");
		System.out.println("------------------------------");

	}
}
