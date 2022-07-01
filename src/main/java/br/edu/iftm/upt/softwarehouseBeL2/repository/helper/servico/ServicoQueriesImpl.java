package br.edu.iftm.upt.softwarehouseBeL2.repository.helper.servico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;


public class ServicoQueriesImpl implements ServicoQueries 
{

	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public List<Servico> buscarCompleto() {
		String jpql = "select s from Servico s left join fetch s.usuario left join fetch s.desenvolvedor";
		TypedQuery<Servico> query = manager.createQuery(jpql, Servico.class);
		List<Servico> servicos = query.getResultList();
		return servicos;
	}
	
	@Override
	public List<Servico> buscarCompleto(Usuario usuario) {
		String jpql = "select s from Servico s left join fetch s.usuario left join fetch s.desenvolvedor where s.usuario.idUsuario = :codigo";
		TypedQuery<Servico> query = manager.createQuery(jpql, Servico.class);
		query.setParameter("codigo", usuario.getIdUsuario());
		List<Servico> servicos = query.getResultList();
		return servicos;
	}
	
}
