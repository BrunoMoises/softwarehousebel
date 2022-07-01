package br.edu.iftm.upt.softwarehouseBeL2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "papel")
public class Papel implements Serializable{

	private static final long serialVersionUID = -274106108304588343L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPapel;
	private String nomePapel;
	
	public Long getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Long idPapel) {
		this.idPapel = idPapel;
	}

	public String getNomePapel() {
		return nomePapel;
	}

	public void setNomePapel(String nomePapel) {
		this.nomePapel = nomePapel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPapel == null) ? 0 : idPapel.hashCode());
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
		Papel other = (Papel) obj;
		if (idPapel == null) {
			if (other.idPapel != null)
				return false;
		} else if (!idPapel.equals(other.idPapel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Papel [idPapel=" + idPapel + ", nomePapel=" + nomePapel + "]";
	}
	
	

}
