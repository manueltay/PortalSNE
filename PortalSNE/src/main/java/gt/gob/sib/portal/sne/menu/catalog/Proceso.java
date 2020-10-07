package gt.gob.sib.portal.sne.menu.catalog;

import java.util.List;


public enum Proceso {
	SISTEMA_NOTIFICACIONES("Sistema de Notificaciones Electrónicas"),
	AGENTES("Agentes de Seguro Independientes");
	
	protected String nombre;
	protected List<Opcion> opciones;
	
	Proceso(String nombre) {
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}
}
