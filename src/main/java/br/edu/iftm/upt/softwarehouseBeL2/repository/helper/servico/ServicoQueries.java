package br.edu.iftm.upt.softwarehouseBeL2.repository.helper.servico;

import java.util.List;

import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;

public interface ServicoQueries 
{

	List<Servico> buscarCompleto();
	List<Servico> buscarCompleto(Usuario usuario);
	
}
