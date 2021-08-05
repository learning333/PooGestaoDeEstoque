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
