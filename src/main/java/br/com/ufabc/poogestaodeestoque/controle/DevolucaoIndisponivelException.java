package br.com.ufabc.poogestaodeestoque.controle;

public class DevolucaoIndisponivelException  extends Exception{
	

	public DevolucaoIndisponivelException(String mensagemErro) {
		super(mensagemErro);
		
	}
}
