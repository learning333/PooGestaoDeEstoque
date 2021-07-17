package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Iterator;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Caixa;
import br.com.ufabc.poo_gestao_de_estoque.repository.CaixaRepository;

@Service
public class CaixaService {
	private CaixaRepository caixaRepository;
	
	public CaixaService( CaixaRepository caixaRepository) {
		this.caixaRepository=caixaRepository;
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
}
