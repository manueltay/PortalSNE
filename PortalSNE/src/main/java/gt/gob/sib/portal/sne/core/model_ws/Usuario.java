package gt.gob.sib.portal.sne.core.model_ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuario")
public class Usuario {
	
	private String usuario;
	private String nombres;
	private String apellidos;
	private String codigoInstitucion;
	private Integer codigoTipoInstitucion;
	private String nombreInstitucion;
	
	
	public String getUsuario() {
		return usuario;
	}
	@XmlElement
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombres() {
		return nombres;
	}
	@XmlElement
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	@XmlElement
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCodigoInstitucion() {
		return codigoInstitucion;
	}
	@XmlElement
	public void setCodigoInstitucion(String codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}
	public Integer getCodigoTipoInstitucion() {
		return codigoTipoInstitucion;
	}
	@XmlElement
	public void setCodigoTipoInstitucion(Integer codigoTipoInstitucion) {
		this.codigoTipoInstitucion = codigoTipoInstitucion;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	@XmlElement
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

}