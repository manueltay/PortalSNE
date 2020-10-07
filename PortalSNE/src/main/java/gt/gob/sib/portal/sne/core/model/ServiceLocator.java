package gt.gob.sib.portal.sne.core.model;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.UI;

import gt.gob.sib.portal.sne.core.model_ws.Process;
import gt.gob.sib.portal.sne.core.model_ws.Usuario;
import gt.gob.sib.portal.sne.core.model_ws.Videos;

public class ServiceLocator implements Serializable {
	private static final long serialVersionUID = 1L;
	private Client client;
	private boolean produccion;

	public ServiceLocator(boolean produccion) {
		this.produccion = produccion;
		client = ClientBuilder.newClient();
	}

	public String getServidorWS() {
		if (produccion)
			return "http://ews.sib.gob.gt:8080";
		else
			return "http://ews-desa.sib.gob.gt:8080";
	}

	public String getServidorReportes() {
		if (produccion)
			return "https://" + UI.getCurrent().getPage().getLocation().getHost() + "/GenerarReporte/servletJR/Generar?";
		else
			return "http://appdesa.sib.gob.gt/Reports/reports/?";
	}

	// TODO implement authentication service
	public Usuario findUsuario(String username) {
		String url = getServidorWS() + "/SegurosExtWS/rest/Seguros/usuario";
		return client.target(url).queryParam("username", username).request(MediaType.APPLICATION_XML)
				.get(Usuario.class);
	}

	public List<Process> listAvailableOptions(String username) throws Exception {
		GenericType<List<Process>> tipo = new GenericType<List<Process>>() {
		};
		String url = getServidorWS() + "/PortalSNEWS/rest/portal/options";
//		String url = getServidorWS() + "/portal.sen.ws/rest/portal/options";
		System.out.println(url);
		List<Process> lista = client.target(url).queryParam("user", username).request(MediaType.APPLICATION_JSON)
				.get(tipo);

		return lista;
	}

	public List<Videos> listVideos(String username) throws Exception {
		GenericType<List<Videos>> tipo = new GenericType<List<Videos>>() {
		};
		String url = getServidorWS() + "/PortalSNEWS/rest/portal/videos";
//		String url = getServidorWS() + "/portal.sen.ws/rest/portal/videos";
		List<Videos> lista = client.target(url).queryParam("user", username).request(MediaType.APPLICATION_JSON)
				.get(tipo);

		return lista;
	}

	public Resource getVideo(String id) {
		String surl = getServidorWS() + "/PortalSNEWS/rest/portal/stream?video_id=" + id;
//		String surl = getServidorWS() + "/portal.sen.ws/rest/portal/stream?video_id=" + id;
		StreamResource a = new StreamResource(new StreamSource() {
			private static final long serialVersionUID = 2253624014442312065L;

			@Override
			public InputStream getStream() {
				URL url;
				try {
					url = new URL(surl);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					return con.getInputStream();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				return null;
			}
		}, "video.mp4");
		return a;
	}

}
