package br.edu.iftm.upt.softwarehouseBeL2.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;
import br.edu.iftm.upt.softwarehouseBeL2.repository.ServicoRepository;
import br.edu.iftm.upt.softwarehouseBeL2.repository.UsuarioRepository;
import br.edu.iftm.upt.softwarehouseBeL2.service.ServicoService;
import br.edu.iftm.upt.softwarehouseBeL2.service.UsuarioService;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	

	@GetMapping(value = {"/", "/index.html"} )
	public ModelAndView entrarIndex() {
		logger.trace("Entrou em index");
		ModelAndView mv = new ModelAndView("index");
		//Se voce precisar pode inserir outros objetos no model para que sejam usados
		// na view index.html
		//mv.addObject("nome", valor);
		logger.trace("Encaminhando para a view index");
		return mv;
	}
	
	@GetMapping(value = "/home")
	public ModelAndView entrarHome(Principal principal) 
	{
		String emailUsuario = principal.getName();
		Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		List<Servico> servicos = servicoRepository.buscarCompleto(usuario);
		logger.trace("Entrou em home");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("servicos", servicos);
		logger.trace("Encaminhando para a view home");
		return mv;
	}
	
	@GetMapping("/login")
	public String login() 
	{
		logger.trace("Entrou em login");
		logger.trace("Encaminhando para a view login");
		return "login";
	}
	
	@PostMapping("/login")
	public String Redirecionarlogin() 
	{
		logger.trace("Entrou no metodo Redirecionarlogin");
		logger.trace("Encaminhando para a view home");
		return "home";
	}
	
	@GetMapping("/about")
	public String about() 
	{
		logger.trace("Entrou em about");
		logger.trace("Encaminhando para a view about");
		return "about";
	}
	
	@GetMapping("/perfil")
	public ModelAndView perfil(Principal principal) 
	{
		String emailUsuario = principal.getName();
		Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		List<Servico> servicos = servicoRepository.buscarCompleto(usuario);
		logger.trace("Entrou em perfil");
		ModelAndView mv = new ModelAndView("perfil");
		mv.addObject("servicos", servicos);
		logger.trace("Encaminhando para a view perfil");
		return mv;
	}
	
	//==============================REMOVER SERVICOS==============================================================
	
	@PostMapping("/perfil/confirmacaoRemocaoServico")
	public String confirmarRemocao(Servico servico) {
		logger.trace("Entrou em Confirmar Remocao");
		logger.debug("Servico recebido para confirmar a remocao: {}", servico);		
		logger.trace("Encaminhando para a view Confirmar Remocao");		
		return "confirmarRemocaoServico";
	}
		
	@PostMapping("/perfil/removerservico")
	public String removerServico(Servico servico, RedirectAttributes atributos, Model model) {
		logger.trace("Entrou em Remover Servico");
		logger.debug("Servico recebido para remover: {}", servico);
		servicoService.remover(servico);
		model.addAttribute("mensagem", "Servico removido com sucesso!");
		logger.trace("Redirecionando para a URL /sucesso");
		return "sucesso";
	}
		
		
	//==============================REMOVER SERVICOS==============================================================
	
}