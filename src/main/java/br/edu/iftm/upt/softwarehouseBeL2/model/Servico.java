package br.edu.iftm.upt.softwarehouseBeL2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "servico")
public class Servico implements Serializable {

	
	private static final long serialVersionUID = 7333156701849547789L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServico; 
	@NotBlank(message = "O Servico é obrigatório")
	@Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
	private String nomeServico; 
	@NotBlank(message = "A descrição é obrigatório")
	@Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
	private String descricaoServico;
    private Integer statusServico;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_desenvolvedor")
    private Desenvolvedor desenvolvedor;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario")
    private Usuario usuario;
    
    
    public Servico() {
		
	}


	public Long getIdServico() {
		return idServico;
	}


	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}


	public String getNomeServico() {
		return nomeServico;
	}


	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}


	public String getDescricaoServico() {
		return descricaoServico;
	}


	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}


	public Integer getStatusServico() {
		return statusServico;
	}


	public void setStatusServico(Integer statusServico) {
		this.statusServico = statusServico;
	}
	

	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}


	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());
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
		Servico other = (Servico) obj;
		if (idServico == null) {
			if (other.idServico != null)
				return false;
		} else if (!idServico.equals(other.idServico))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", nomeServico=" + nomeServico + ", descricaoServico="
				+ descricaoServico + ", statusServico=" + statusServico + "]";
	}

}
