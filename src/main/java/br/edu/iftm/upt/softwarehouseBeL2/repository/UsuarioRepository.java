package br.edu.iftm.upt.softwarehouseBeL2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import br.edu.iftm.upt.softwarehouseBeL2.model.Usuario;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

	List<Usuario> findByNomeUsuarioContainingIgnoreCase (String nomeUsuario);
	
	Usuario findByNomeUsuario (String nomeUsuario);
	
	Usuario findByEmailUsuario (String emailUsuario);
	long countByEmailUsuarioContainingIgnoreCase(String emailUsuario);
	
}
