package br.edu.iftm.upt.softwarehouseBeL2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iftm.upt.softwarehouseBeL2.model.Desenvolvedor;
import br.edu.iftm.upt.softwarehouseBeL2.repository.DesenvolvedorRepository;

@Service
public class DesenvolvedorService 
{

	private static final Logger logger = LoggerFactory.getLogger(DesenvolvedorService.class);

	@Autowired
	private DesenvolvedorRepository desenvolvedorRepository;
	
	@Transactional
	public void salvar(Desenvolvedor desenvolvedor) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o desenvolvedor {}", desenvolvedor);
		desenvolvedorRepository.save(desenvolvedor);
		logger.debug("Contato salvo com sucesso {}", desenvolvedor);
	}
	
	@Transactional
	public void alterar(Desenvolvedor desenvolvedor) {
		logger.trace("Entrou em alterar");
		logger.debug("Alterando o contato {}", desenvolvedor);
		desenvolvedorRepository.save(desenvolvedor);
		logger.debug("Contato alterado com sucesso {}", desenvolvedor);
	}
	
	@Transactional
	public void remover(Desenvolvedor desenvolvedor) {
		logger.trace("Entrou em remover");
		logger.debug("Removendo o contato {}", desenvolvedor);
		desenvolvedorRepository.delete(desenvolvedor);
		logger.debug("Contato removido com sucesso {}", desenvolvedor);
	}
	
}
