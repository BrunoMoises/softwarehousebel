package br.edu.iftm.upt.softwarehouseBeL2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.repository.ServicoRepository;

@Service
public class ServicoService 
{

	private static final Logger logger = LoggerFactory.getLogger(ServicoService.class);

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Transactional
	public void salvar(Servico servico) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o Servico {}", servico);
		servicoRepository.save(servico);
		logger.debug("Servico salvo com sucesso {}", servico);
	}
	
	@Transactional
	public void alterar(Servico servico) {
		logger.trace("Entrou em alterar");
		logger.debug("Alterando o Servico {}", servico);
		servicoRepository.save(servico);
		logger.debug("Servico alterado com sucesso {}", servico);
	}
	
	@Transactional
	public void remover(Servico servico) {
		logger.trace("Entrou em remover");
		logger.debug("Removendo o Servico {}", servico);
		servicoRepository.delete(servico);
		logger.debug("Servico removido com sucesso {}", servico);
	}
	
}
