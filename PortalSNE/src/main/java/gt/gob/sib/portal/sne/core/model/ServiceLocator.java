package gt.gob.sib.portal.sne.core.model;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import gt.gob.sib.portal.sne.core.model_ws.Option;
import gt.gob.sib.portal.sne.core.model_ws.Usuario;


public class ServiceLocator implements Serializable {
	private static final long serialVersionUID = 1L;
	private Client client;
	private boolean produccion;

	public ServiceLocator(boolean produccion) {
		client = ClientBuilder.newClient();
	}

	public String getServidorWS() {
		/*
		 * if (produccion) return "http://appjava.sib.gob.gt"; else return
		 * "http://localhost:8080";
		 */

		// return "http://ews.sib.gob.gt:8080";
		return "http://localhost:8080";
		// return "http://appdesa.sib.gob.gt:8080";
	}

	public String getServidorReportes() {
		/*
		 * if (produccion) return "http://appjava.sib.gob.gt"; else return
		 * "http://localhost:8080";
		 */

		return "http://appdesa.sib.gob.gt";
	}

	// TODO implement authentication service
	public Usuario findUsuario(String username) {
		String url = getServidorWS() + "/SegurosExtWS/rest/Seguros/usuario";
		return client.target(url).queryParam("username", username).request(MediaType.APPLICATION_XML)
				.get(Usuario.class);
	}
	
	public List<Option> listAvailableOptions(String username) throws Exception {
		GenericType<List<Option>> tipo = new GenericType<List<Option>>() {
		};
		 String url = getServidorWS() + "/portal.sen.ws/rest/portal/options";
		List<Option> lista = client.target(url).queryParam("user", username)
				.request(MediaType.APPLICATION_JSON)
				.get(tipo);

		return lista;
	}

}
