package br.edu.iftm.upt.softwarehouseBeL2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.edu.iftm.upt.softwarehouseBeL2.model.Servico;
import br.edu.iftm.upt.softwarehouseBeL2.repository.helper.servico.ServicoQueries;


@EnableJpaRepositories
public interface ServicoRepository extends JpaRepository<Servico, Long>, ServicoQueries
{
	List<Servico> findByNomeServicoContainingIgnoreCase (String nomeServico);
}
