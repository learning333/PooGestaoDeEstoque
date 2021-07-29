package br.com.ufabc.poo_gestao_de_estoque.servico;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Lote;
import br.com.ufabc.poo_gestao_de_estoque.modelo.NewVenda;
import br.com.ufabc.poo_gestao_de_estoque.repository.LoteRepository;
import br.com.ufabc.poo_gestao_de_estoque.repository.NewVendaRepository;




@Service
public class CrudNewVendaService {
	private LoteRepository loteRepository;
	private NewVendaRepository vendaRepository;
	
	public CrudNewVendaService(LoteRepository loteRepository, NewVendaRepository vendaRepository) {
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
			//System.out.println("3-Listar newVendas");
			//System.out.println("4-Deletar newVenda");
			
			int opcao=scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.visualizar();
				break;
			default:
				gatilho=false;
			}
			
		}
		System.out.println();
	}
	private void cadastrar(Scanner scanner) {
		System.out.print("Id lote");
		Long idprof=scanner.nextLong();
		
		Optional<Lote> optional = this.loteRepository.findById(idprof);
		if(optional.isPresent()) {
			Lote lote = optional.get();
			int qtdDisponivel=lote.getQtd()-lote.getQtdVendida();
			if(lote.getStatus().equals("em maos")) {//tem quantidade disponivel>0
				
				System.out.println("Quantidade Disponivel:["+qtdDisponivel+"]");
				System.out.print("Qtd vendida:");
				int qtd=scanner.nextInt();
				// to-do: validar qtd
				System.out.print("Preco unitario:");
				Float precoVenda=scanner.nextFloat();
				
				System.out.print("nome cliente: ");
				String nome=scanner.next();
				
				float lucro=precoVenda-lote.getCusto();
				NewVenda newVenda=new NewVenda(nome,precoVenda,lote,qtd,lucro);
				lote.setQtdVendida(lote.getQtdVendida()+qtd);
				vendaRepository.save(newVenda);
				loteRepository.save(lote);//update qtd vendida
				System.out.print("Salvo\n");
			}else {
				System.out.print("Pedido de compra nao disponivel [status="+lote.getStatus()+"]");
			}
		}else {
			System.out.print("id nao existe");
		}

	}
	private void visualizar() {
		Iterable<NewVenda> lista = this.vendaRepository.findAll();
		for(NewVenda newVenda: lista) {
			System.out.println(newVenda);
		}
		System.out.println();
	}

	private void atualizar(Scanner scanner) {
		/*System.out.print("Digite o id da discplina a ser atualizado: ");
		Long id=scanner.nextLong();

		Optional<NewVenda> optional = this.dRepository.findById(id);
		if(optional.isPresent()) {
			NewVenda newVenda=optional.get();
			
			System.out.print("Digite Nome: ");
			String nome=scanner.next();
			System.out.print("Semestre: ");
			Integer semestre=scanner.nextInt();
			System.out.print("Id lote");
			Long idprof=scanner.nextLong();
			
			Optional<Lote> profoptional = this.loteRepository.findById(idprof);
			if(profoptional.isPresent()) {
				Lote lote = profoptional.get();
				newVenda.setNome(nome);
				newVenda.setSemestre(semestre);
				newVenda.setLote(lote);
				
				dRepository.save(newVenda);
				System.out.print("Atualizado\n");
			}else {
				System.out.print("ID do lote nao existe");
			}
		}//if newVenda existe
		else {
			System.out.println("ID INVALIDO\n");
		}
		*/
	}
	

	private void deletar(Scanner scanner) {
		/*System.out.print("Digite o id da newVenda a ser deletada: ");
		Long id=scanner.nextLong();

		Optional<NewVenda> optional = this.dRepository.findById(id);
		if(optional.isPresent()) {
			NewVenda newVenda = optional.get();
			Long id2 =newVenda.getId();
			this.dRepository.deleteById(id2);
			System.out.println("NewVenda deletada\n");
		}else {
			System.out.println("Id invalido");
		}*/
	}
}
