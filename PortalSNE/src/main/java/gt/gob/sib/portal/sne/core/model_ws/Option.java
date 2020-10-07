package gt.gob.sib.portal.sne.core.model_ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Option {
	private Long id;
	private String nombre;
	private String urlOpcion;
	private Long idPadre;
	private Long idProceso;
	public Long getId() {
		return id;
	}
	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	@XmlElement
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrlOpcion() {
		return urlOpcion;
	}
	@XmlElement
	public void setUrlOpcion(String urlOpcion) {
		this.urlOpcion = urlOpcion;
	}
	public Long getIdPadre() {
		return idPadre;
	}
	@XmlElement
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	public Long getIdProceso() {
		return idProceso;
	}
	@XmlElement
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}
	
}
