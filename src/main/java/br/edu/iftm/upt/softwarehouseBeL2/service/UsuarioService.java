package br.edu.iftm.upt.softwarehouseBeL2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;
import br.edu.iftm.upt.softwarehouseBeL2.repository.UsuarioRepository;

@Service
public class UsuarioService 
{

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public void salvar(Usuario usuario) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o Usuario {}", usuario);
		usuarioRepository.save(usuario);
		logger.debug("Usuario salvo com sucesso {}", usuario);
	}
	
	@Transactional
	public void alterar(Usuario usuario) {
		logger.trace("Entrou em alterar");
		logger.debug("Alterando o Usuario {}", usuario);
		usuarioRepository.save(usuario);
		logger.debug("Usuario alterado com sucesso {}", usuario);
	}
	
	@Transactional
	public void remover(Usuario usuario) {
		logger.trace("Entrou em remover");
		logger.debug("Removendo o Usuario {}", usuario);
		usuarioRepository.delete(usuario);
		logger.debug("Usuario removido com sucesso {}", usuario);
	}
	
	@Transactional
	public boolean isNomeUsuarioJaUsado(String nomeUsuario) {
		logger.trace("Entrou em isNomeUsuarioJaUsado");
		logger.debug("nomeUsuario recebido para testar {}", nomeUsuario);
		return usuarioRepository.findByNomeUsuario(nomeUsuario) != null;
	}
	
}
