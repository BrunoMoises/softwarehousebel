package br.edu.iftm.upt.softwarehouseBeL2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.edu.iftm.upt.softwarehouseBeL2.validation.EmailValido;
import br.edu.iftm.upt.softwarehouseBeL2.validation.Emailunico;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -6603087024327696824L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
	private String nomeUsuario;
	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "O e-mail deve ser bem formatado?")
	private String emailUsuario;
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 1, max = 20, message = "A senha deve ter entre 1 e 20 caracteres")
	private String senhaUsuario;
	@NotBlank(message = "O telefone é obrigatório")
	@Pattern(regexp = "\\(?([1-9]{2}\\)?\\s?(?:[2-8]|9[1-9])\\d{3}\\-?\\d{4}$)", message = "O telefone deve incluir o DDD e o número")
	private String numeroUsuario;
	private boolean ativoUsuario;
	@NotEmpty (message = "O usuario deve ter ao menos um papel")
	@ManyToMany
	@JoinTable(name="usuario_papel",joinColumns=@JoinColumn(name = "codigo_usuario"),inverseJoinColumns=@JoinColumn(name="codigo_papel"))
	private List<Papel> papeis = new ArrayList<>();
	
	
	public Usuario() {
		
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getEmailUsuario() {
		return emailUsuario;
	}


	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}


	public String getSenhaUsuario() {
		return senhaUsuario;
	}


	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	
	
	public String getNumeroUsuario() {
		return numeroUsuario;
	}


	public void setNumeroUsuario(String numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public boolean isAtivoUsuario() {
		return ativoUsuario;
	}

	public void setAtivoUsuario(boolean ativoUsuario) {
		this.ativoUsuario = ativoUsuario;
	}
	
	public void adicionarPapel(Papel papel) {
		papeis.add(papel);
	}

	public void removerPapel(Papel papel) {
		papeis.remove(papel);
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", emailUsuario=" + emailUsuario
				+ ", senhaUsuario=" + senhaUsuario + ", numeroUsuario=" + numeroUsuario + "]";
	}

}
