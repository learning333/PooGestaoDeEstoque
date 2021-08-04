package br.com.ufabc.poo_gestao_de_estoque.controle;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



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
	
	@RequestMapping("/{id}")
	public ModelAndView detalhesOrdem(@PathVariable("id") Long id) {
		Optional<ProdutoCadastrado> produto=pr.findById(id);
		ModelAndView mv=new ModelAndView("detalhesProduto");
		mv.addObject("Produto", produto);
		return mv;
	}*/
	
}
