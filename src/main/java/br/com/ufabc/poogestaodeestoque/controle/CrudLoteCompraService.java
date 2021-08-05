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
	}
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
	
	public LoteCompra devolucaoDeVenda(LoteCompra lote, int qtd) {

		lote.setQtdVendida(qtd*(-1));
		this.loteRepository.save(lote);//update qtd vendida
		if(lote.getStatus().equals("encerrado")) {//se estava zerado reativa o lote compra
			lote=reativaLote(lote);
			
		}
		return lote;
	}
	public LoteCompra reativaLote(LoteCompra lote) {
			//qtdVendida+=qtd
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
