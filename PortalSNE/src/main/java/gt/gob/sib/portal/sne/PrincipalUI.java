package gt.gob.sib.portal.sne;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import ch.qos.logback.classic.Logger;
import gt.gob.sib.Seguridad.Sesion;
import gt.gob.sib.portal.sne.core.model.ServiceLocator;
import gt.gob.sib.portal.sne.core.model_ws.Usuario;
import gt.gob.sib.portal.sne.info.PaginaInformacionView;
import gt.gob.sib.portal.sne.menu.PantallaPrincipalView;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class PrincipalUI extends UI {
	private Navigator navegador;
	private String ambiente;
	private boolean produccion;
	private String username;
	private ServiceLocator serviceLocator;
	private String pathUploads;
	private Usuario usuario;
	
	/**
	 * Utilizado para el manejo de log, almacena la información en tablas del esquema lib
	 */
	private static final Logger LOGGER = (Logger)LoggerFactory.getLogger("PortalSNE"); 

	public PrincipalUI() {
		if (isAmbienteDesarrollo()) {
			produccion = true;
			username = "USR103_1";
			username = "USR_COLUMNA415";
			username = "USR_GENERAL406";
		} else {
			produccion = true;
			username = (String) Sesion.getUser();
			
			if(username == null) {
				LOGGER.info("El usuario es nulo");
			}
		}
		serviceLocator = new ServiceLocator(produccion);
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		getPage().setTitle("Portal SNE");
		
		if (getServiceLocator() == null)
			LOGGER.info("El serviceLocator es nulo");
		
		if (username == null)
			LOGGER.info("El usuario es nulo");
		
		usuario = getServiceLocator().findUsuario(username);

		pathUploads = VaadinServlet.getCurrent().getServletConfig().getServletContext().getRealPath("") + File.separator
				+ "uploads" + File.separator;
		createFolderIfNotExists(pathUploads);

		pathUploads += java.util.UUID.randomUUID().toString() + File.separator;

		// elimina la carpeta de sesion
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				File theDir = new File(pathUploads);
				if (theDir.exists()) {
					try {
						FileUtils.deleteDirectory(theDir);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		this.addDetachListener(new DetachListener() {
			public void detach(DetachEvent event) {
				File theDir = new File(pathUploads);
				if (theDir.exists()) {
					try {
						FileUtils.deleteDirectory(theDir);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		try {
			navegador = new Navigator(this, this);
			navegador.addView("", new PantallaPrincipalView());
			
			//navegador.addView("info", new PaginaInformacionView());
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Ha ocurrido un error: " + e.getMessage(), Type.ERROR_MESSAGE);
		}

	}

	@WebServlet(urlPatterns = "/*", name = "PrincipalUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = PrincipalUI.class, productionMode = false)
	public static class PrincipalUIServlet extends VaadinServlet {
	}

	private void createFolderIfNotExists(String dirName) throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists())
			theDir.mkdir();
	}

	public boolean isAmbienteDesarrollo() {
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public String getUsername() {
		return username;
	}

	public String getPathUploads() {
		return pathUploads;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
