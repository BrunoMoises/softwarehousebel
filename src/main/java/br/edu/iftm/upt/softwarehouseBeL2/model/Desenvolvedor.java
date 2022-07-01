package br.edu.iftm.upt.softwarehouseBeL2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "desenvolvedor")
public class Desenvolvedor implements Serializable{
	
	private static final long serialVersionUID = 4342857463985312610L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDesenvolvedor;
	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
	private String nomeDesenvolvedor;
	@NotBlank(message = "A Habilidade é obrigatória")
	@Size(min = 1, max = 255, message = "O texto deve ter entre 1 e 255 caracteres")
	private String habilidadeDesenvolvedor;
	@Min(value = 1, message = "Escolha uma opção")
	private Integer nivelDesenvolvedor;
	
	public Desenvolvedor() {
		
	}
	
	public Long getIdDesenvolvedor() {
		return idDesenvolvedor;
	}
	public void setIdDesenvolvedor(Long idDesenvolvedor) {
		this.idDesenvolvedor = idDesenvolvedor;
	}
	public String getNomeDesenvolvedor() {
		return nomeDesenvolvedor;
	}
	public void setNomeDesenvolvedor(String nomeDesenvolvedor) {
		this.nomeDesenvolvedor = nomeDesenvolvedor;
	}
	public String getHabilidadeDesenvolvedor() {
		return habilidadeDesenvolvedor;
	}
	public void setHabilidadeDesenvolvedor(String habilidadeDesenvolvedor) {
		this.habilidadeDesenvolvedor = habilidadeDesenvolvedor;
	}
	public Integer getNivelDesenvolvedor() {
		return nivelDesenvolvedor;
	}
	public void setNivelDesenvolvedor(Integer nivelDesenvolvedor) {
		this.nivelDesenvolvedor = nivelDesenvolvedor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDesenvolvedor == null) ? 0 : idDesenvolvedor.hashCode());
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
		Desenvolvedor other = (Desenvolvedor) obj;
		if (idDesenvolvedor == null) {
			if (other.idDesenvolvedor != null)
				return false;
		} else if (!idDesenvolvedor.equals(other.idDesenvolvedor))
			return false;
		return true;
	}

	
	@Override
	public String toString() 
	{
		return "Desenvolvedor [idDesenvolvedor=" + idDesenvolvedor + ", nomeDesenvolvedor=" + nomeDesenvolvedor
				+ ", habilidadeDesenvolvedor=" + habilidadeDesenvolvedor + ", nivelDesenvolvedor=" + nivelDesenvolvedor
				+ "]";
	}
	
}
