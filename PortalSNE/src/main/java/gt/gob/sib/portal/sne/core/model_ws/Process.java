package gt.gob.sib.portal.sne.core.model_ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Process {
	private Long idProceso;
	private String nombre;
	private String abreviatura;
	private String sistema;
	private Long idModelo;
	
	private List<Option> opciones = new ArrayList<>();

	public Long getIdProceso() {
		return idProceso;
	}
	@XmlElement
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	public String getNombre() {
		return nombre;
	}
	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}
	@XmlElement
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getSistema() {
		return sistema;
	}
	@XmlElement
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public Long getIdModelo() {
		return idModelo;
	}
	@XmlElement
	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public List<Option> getOpciones() {
		return opciones;
	}
	@XmlElement
	public void setOpciones(List<Option> opciones) {
		this.opciones = opciones;
	}
	
}
