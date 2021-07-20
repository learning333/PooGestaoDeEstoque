package br.com.ufabc.poo_gestao_de_estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ufabc.poo_gestao_de_estoque.modelo.Compra;
import br.com.ufabc.poo_gestao_de_estoque.repository.OrdemCompraRepository;



@Controller
public class WebOCController {
	@Autowired//injecao de dependencia
	private OrdemCompraRepository ocr;
	
	@RequestMapping(value="/cadastrarCompra", method=RequestMethod.GET)
	public String form() {
		return "formOrdemcompra";
	}
	@RequestMapping(value="/cadastrarCompra", method=RequestMethod.POST)
	public String form(Compra oc) {
		ocr.save(oc);//persiste a oc no banco
		return "redirect:/cadastrarCompra";
	}
	
	
	
}
