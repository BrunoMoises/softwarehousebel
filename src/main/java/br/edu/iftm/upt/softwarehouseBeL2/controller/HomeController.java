package br.edu.iftm.upt.softwarehouseBeL2.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iftm.upt.softwarehouseBeL2.model.Desenvolvedor;
import br.edu.iftm.upt.softwarehouseBeL2.model.Papel;
import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;
import br.edu.iftm.upt.softwarehouseBeL2.repository.DesenvolvedorRepository;
import br.edu.iftm.upt.softwarehouseBeL2.repository.PapelRepository;
import br.edu.iftm.upt.softwarehouseBeL2.repository.ServicoRepository;
import br.edu.iftm.upt.softwarehouseBeL2.repository.UsuarioRepository;
import br.edu.iftm.upt.softwarehouseBeL2.service.DesenvolvedorService;
import br.edu.iftm.upt.softwarehouseBeL2.service.ServicoService;
import br.edu.iftm.upt.softwarehouseBeL2.service.UsuarioService;

@Controller
@RequestMapping("/home")
public class HomeController 
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DesenvolvedorRepository desenvolvedorRepository;
	
	@Autowired
	private DesenvolvedorService desenvolvedorService;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private PapelRepository papelRepository;
	
	//@Autowired
	//private PapelService papelService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//==============================CADASTRO DE USUARIO========================================================
	@GetMapping("/cadastro")
	public ModelAndView entrarCadastro(Usuario usuario, Desenvolvedor desenvolvedor) {
		logger.trace("Entrou em cadastro");
		ModelAndView mv = new ModelAndView("cadastro");
		List<Papel> papeis = papelRepository.findAll();
		logger.debug("Papeis encontrados para mostrar {}", papeis);
		mv.addObject("todosPapeis", papeis);
		logger.trace("Encaminhando para a view cadastro");
		return mv;
	}
	
	@PostMapping("/cadastroUsuario")
	public ModelAndView inserirCadastroUsuario(@Valid Usuario usuario,BindingResult result, RedirectAttributes atributos, Desenvolvedor desenvolvedor) {
		logger.trace("Entrou em inserirCadastroUsuario");
		logger.debug("Usuario recebido para inserir: {}", usuario);
		logger.debug("Papeis recebidos para o usuario {}", usuario.getPapeis());
		ModelAndView mv;
		if (!result.hasErrors()) {
			usuario.setAtivoUsuario(true);
			usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
			usuarioService.salvar(usuario);
			atributos.addFlashAttribute("mensagem", "Usuario inserido com sucesso!");
			logger.trace("Redirecionando para a URL sucessoUsuario");
			mv = new ModelAndView("redirect:/home/sucessoUsuario");
		} else {
			logger.debug("O usuario recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			logger.trace("Encaminhando para a view cadastro");
			mv = new ModelAndView("cadastro");
			List<Papel> papeis = papelRepository.findAll();
			logger.debug("Papeis encontrados para mostrar {}", papeis);
			mv.addObject("todosPapeis", papeis);
		}
		return mv;
	}
	
	@GetMapping("/sucessoUsuario")
	public String cadastroSucessoUsuario(Model model) {
		logger.trace("Entrou no método cadastroSucessoUsuario");
		model.addAttribute("mensagem", "Cadastro do Usuario efetuado com sucesso");
		logger.trace("Encaminhando para a view sucesso");
		return "sucesso";
	}
	
	@PostMapping("/cadastroDesenvolvedor")
	public ModelAndView inserirCadastroDesenvolvedor(@Valid Desenvolvedor desenvolvedor, BindingResult result, RedirectAttributes atributos, Usuario usuario) {
		logger.trace("Entrou em inserirCadastroDesenvolvedor");
		logger.debug("Desenvolvedor recebido para inserir: {}", desenvolvedor);
		ModelAndView mv;
		if (!result.hasErrors()) {
			desenvolvedorService.salvar(desenvolvedor);
			atributos.addFlashAttribute("mensagem", "Desenvolvedor inserido com sucesso!");
			logger.trace("Redirecionando para a URL sucessoDesenvolvedor");
			mv = new ModelAndView("redirect:/home/sucessoDesenvolvedor");
		} else {
			logger.debug("O desenvolvedor recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			logger.trace("Encaminhando para a view cadastro");
			mv = new ModelAndView("cadastro");
		}
		return mv;
	}
	
	@GetMapping("/sucessoDesenvolvedor")
	public String cadastroSucessoDesenvolvedor(Model model) {
		logger.trace("Entrou no método cadastroSucessoDesenvolvedor");
		model.addAttribute("mensagem", "Cadastro do Desenvolvedor efetuado com sucesso");
		logger.trace("Encaminhando para a view sucesso");
		return "sucesso";
	}
	//==============================CADASTRO DE USUARIO========================================================
	
	
	//==============================CADASTRO DE SERVIÇO========================================================
	
	@GetMapping("/escolha")
	public ModelAndView entrarEscolha(Desenvolvedor desenvolvedor) {
		logger.trace("Entrou no metodo entrarEscolha");
		ModelAndView mv = new ModelAndView("escolha");
		List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
		logger.debug("Desenvolvedores encontrados para mostrar {}", desenvolvedores);
		logger.trace("Encaminhando para a view escolha");
		mv.addObject("desenvolvedores", desenvolvedores);
		return mv;
	}
	
	@GetMapping("/escolha/cadastroServico")
	public String abrirInserirServico(Servico servico, Model model, Desenvolvedor desenvolvedor, Principal principal) {
		logger.trace("Entrou no método abrirInserirServico");
		logger.info("servico recebido: {}", servico);
		logger.info("Usuario do servico recebido: {}", servico.getUsuario());
		logger.info("Desenvolvedor do servico recebido: {}", servico.getDesenvolvedor());
		String emailUsuario = principal.getName();
		Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		logger.debug("Usuario recebido: {}", usuario);
		//List<Usuario> usuarios = usuarioRepository.findAll();
		List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
		//logger.debug("Usuarios recebido: {}", usuarios);
		logger.debug("Desenvolvedores recebido: {}", desenvolvedores);
		model.addAttribute("usuario", usuario);
		//model.addAttribute("usuarios", usuarios);
		model.addAttribute("desenvolvedores", desenvolvedores);
		logger.trace("Encaminhando para a view cadastroServico");
		return "cadastroServico";
	}
	
	@PostMapping("/escolha/cadastroServico")
	public ModelAndView inserirServico(@Valid Servico servico, BindingResult result, RedirectAttributes atributos, Desenvolvedor desenvolvedor,Usuario usuario, Model model)
	{
		logger.trace("Entrou em inserirServico");
		logger.debug("Servico recebido para inserir: {}", servico);
		ModelAndView mv;
		if (!result.hasErrors()) {
			List<Usuario> usuarios = usuarioRepository.findAll();
			List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
			logger.debug("usuarios buscados: {}", usuarios);
			logger.debug("desenvolvedores buscados: {}", desenvolvedores);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("desenvolvedores", desenvolvedores);
			servicoService.salvar(servico);
			atributos.addFlashAttribute("mensagem", "Servico inserido com sucesso!");
			logger.trace("Redirecionando para a URL sucessoServico");
			mv = new ModelAndView("redirect:/home/sucessoServico");
		} else {
			logger.debug("O Servico recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : result.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			logger.trace("Encaminhando para a view cadastroServico");
			mv = new ModelAndView("cadastroServico");
		}
		return mv;
	}
	
	@GetMapping("/sucessoServico")
	public String cadastroSucessoServico(Model model) {
		logger.trace("Entrou no método cadastroSucessoServico");
		model.addAttribute("mensagem", "Cadastro do Servico efetuado com sucesso");
		logger.trace("Encaminhando para a view sucesso");
		return "sucesso";
	}
	
	
	
	//==============================CADASTRO DE SERVIÇO========================================================
	
	//==============================MOSTRAR TODOS SERVICOS========================================================
	
	@GetMapping("/mostrarTodosServicos")
	public ModelAndView mostrarTodosServicos(Model model, Servico servico) {
		logger.trace("Entrou no metodo mostrarTodosServicos");
		ModelAndView mv = new ModelAndView("mostrarTodosServicos");
		List<Servico> servicos = servicoRepository.buscarCompleto();
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
		logger.debug("usuarios buscados: {}", usuarios);
		logger.debug("usuarios buscados: {}", desenvolvedores);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("desenvolvedores", desenvolvedores);
		logger.debug("Servicos encontrados para mostrar {}", servicos);
		logger.trace("Encaminhando para a view mostrarTodosServicos");
		mv.addObject("servicos", servicos);
		return mv;
	}
	
	
	//==============================MOSTRAR TODOS SERVICOS========================================================
	
	//==============================ALTERAR SERVICOS==============================================================
	
	@PostMapping("/mostrarTodosServicos/abrirAlterar")
	public String abrirAlterar(Servico servico, Model model, Principal principal) {
		logger.info("Entro no metodo abrirAlterar");
		logger.info("servico recebido: {}", servico);
		logger.info("Usuario do servico recebido: {}", servico.getUsuario());
		logger.info("Desenvolvedor do servico recebido: {}", servico.getDesenvolvedor());
		String emailUsuario = principal.getName();
		Usuario usuario = usuarioRepository.findByEmailUsuario(emailUsuario);
		logger.debug("Usuario recebido: {}", usuario);
		List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
		logger.debug("Desenvolvedores recebido: {}", desenvolvedores);
		model.addAttribute("desenvolvedores", desenvolvedores);
		return "alterarServico";
	}
	
	@PostMapping("/mostrarTodosServicos/alterarServico")
	public String alterarServico(@Valid Servico servico, BindingResult resultado, Model model) {
		logger.trace("Entrou no método alterarServico");
		logger.debug("servico recebido: {}", servico);;
		if (resultado.hasErrors()) {
			logger.debug("O servico recebido para inserir não é válido");
			logger.debug("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.debug("{}", erro);
			}
			List<Usuario> usuarios = usuarioRepository.findAll();
			List<Desenvolvedor> desenvolvedores = desenvolvedorRepository.findAll();
			logger.debug("usuarios buscados: {}", usuarios);
			logger.debug("desenvolvedores buscados: {}", desenvolvedores);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("desenvolvedores", desenvolvedores);
			logger.trace("Encaminhando para a view mostrarTodosServicos");
			return "mostrarTodosServicos";
		} else {
			servicoService.alterar(servico);
			logger.trace("Redirecionando para a URL mostrarTodosServicos");
			model.addAttribute("mensagem", "Servico alterado com sucesso!");
			return "sucesso";
		}
	}
	
	//==============================ALTERAR SERVICOS==============================================================
}
