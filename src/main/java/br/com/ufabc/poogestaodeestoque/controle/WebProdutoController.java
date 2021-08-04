package br.com.ufabc.poogestaodeestoque.controle;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Comecei a aprender a parte web para tentar fazer um front-end 
 * porem vi que no ritmo de aprendizado que eu estava tendo nao ia dar tempo de fazer uma entrega
 * atendendo os criterios/recomendacoes definidos, entao comentei o pouco que tinha conseguido fazer abaixo 
 * e decidi focar o tempo restante em atender os criterios mesmo sem a persistencia de dados e front web:
 */

@Controller
public class WebProdutoController {
	
	
	/*@Autowired//injecao de dependencia
	private ProdutoRepository pr;
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.GET)
	public String form() {
		return "cadProd";
	}
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.POST)
	public String form(ProdutoCadastrado oc) {
		
		pr.save(oc);//persiste a oc no banco
		return "redirect:/cadastrarProduto";
	}
	
	
	@RequestMapping(value="/listarProd")
	public ModelAndView listaOrdens() {
		ModelAndView mv=new ModelAndView("cadProd");
		Iterable<ProdutoCadastrado> produtos=pr.findAll();
		mv.addObject("produtosCadastrados", produtos);
		return mv;
	}
	
*/
	
}
