package gt.gob.sib.portal.sne.core;

import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import gt.gob.sib.framework.core.model.Usuario;
import gt.gob.sib.framework.core.services.BaseServiceLocator;
import gt.gob.sib.framework.vaadin.SIBCustomComponent;
import gt.gob.sib.framework.vaadin.common.ConfiguracionGeneral;
import gt.gob.sib.framework.vaadin.common.ConfiguracionGeneral.Servidor;
import gt.gob.sib.portal.sne.menu.MenuAplicacionesCtl;

public abstract class PortalTemplateView  <T extends BaseServiceLocator> extends SIBCustomComponent<T> implements View {
	private VerticalLayout mainLayout;
	private Image imagenBanner;
	private Label lblInformacionConexion;
	private MenuAplicacionesCtl content;

	public PortalTemplateView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		addHeaderImage();
		
	}
	
	private VerticalLayout buildMainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);
		mainLayout.setStyleName("panel-principal");

		setWidth("100.0%");
		setHeight("100.0%");

		return mainLayout;
	}

	public void addHeaderImage() {
		imagenBanner = getHeaderImage();
		imagenBanner.addStyleName("encabezado");
		
		content = new MenuAplicacionesCtl();

		lblInformacionConexion = new Label("");
		lblInformacionConexion.addStyleName("title");
		lblInformacionConexion.setVisible(false);
		content.addUserInfo(lblInformacionConexion);
		
		mainLayout.addComponent(content);
	}

	public Image getHeaderImage() {
		Image imagen = new Image(null,
				new ExternalResource("http://aplicaciones.sib.gob.gt:7778/i/bannerInstitucional_1_1_1.png"));
		imagen.setWidth("100%");
		return imagen;
	}

	public void closeCurrentPage() {
		JavaScript.getCurrent().execute("window.open('','_self').close();");
	}

	public Object obtenerParametro(String parametro) {
		return VaadinService.getCurrentRequest().getParameter(parametro);
	}

	public T getServiceLocator() {
		return (T) getPrincipalUI().getServiceLocator();
	}

	public String getUserName() {
		return getPrincipalUI().getUserName();
	}

	public Usuario getUsuarioAutenticado() {
		return getPrincipalUI().getUsuarioAutenticado();
	}

	/***
	 * Permite obtener el parametro de una session de Vaadin.
	 * 
	 * @param nombre El nombre del parametro a buscar.
	 * @return El objeto con la referencia buscada.
	 */
	public Object getParameter(String nombre) {
		return VaadinService.getCurrentRequest().getParameter(nombre);
	}

	public String getServidor() {
		return ConfiguracionGeneral.getServidor(Servidor.SERVER_FORMS);

	}
	public String getReportServer() {
		return ConfiguracionGeneral.getReportServer();
	}

	public void addInformacionConexion(String value) {
		lblInformacionConexion.setValue(value);
		lblInformacionConexion.setVisible(true);
	}

	public void hideInformacionConexion() {
		lblInformacionConexion.setValue("");
		lblInformacionConexion.setVisible(false);
	}

	public CssLayout getMainLayout() {
		return content.getContent();
	}
	
	public void loadPage(String url) {
		content.loadPage(url);
	}
	
	public void restart() {
		content = new MenuAplicacionesCtl();
	}

}
