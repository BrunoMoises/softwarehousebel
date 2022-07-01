package br.edu.iftm.upt.softwarehouseBeL2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.edu.iftm.upt.softwarehouseBeL2.model.Desenvolvedor;

@EnableJpaRepositories
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long>
{

	List<Desenvolvedor> findByNomeDesenvolvedorContainingIgnoreCase (String nomeDesenvolvedor);
	
}
