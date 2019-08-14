package gt.gob.sib.portal.sne.menu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

import gt.gob.sib.portal.sne.core.PortalCustomComponent;


public class MenuAplicacionesCtl extends PortalCustomComponent {
	private static final long serialVersionUID = -8434649591335054094L;
	
	private MenuAplicaciones pantalla;
	private Boolean selected = true;
	private BrowserFrame navegador;

	public MenuAplicacionesCtl() {
		try {
			pantalla = new MenuAplicaciones();
			setCompositionRoot(pantalla);
			loadPage("http://appdesa.sib.gob.gt/ComunicacionesElectronicasExt/");
			addFunctionality();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e.getMessage());
		}
	}

	private void addFunctionality() {
		pantalla.btnMenu.addClickListener(event -> {
			if (selected) {
				hideMenu();
			} else {
				showMenu();
			}
		});

		pantalla.registros.addClickListener(event -> {
			loadPage("http://appdesa.sib.gob.gt/SegurosExt/#!menu");
		});
		
		pantalla.notificaciones.addClickListener(even ->{
			loadPage("http://appdesa.sib.gob.gt/ComunicacionesElectronicasExt/");
		});
		
	}
	
	private void loadPage(String url) {
		pantalla.content.removeAllComponents();
		navegador = new BrowserFrame("", new ExternalResource(url));
		navegador.setWidth("100%");
		navegador.addStyleName("embedded-frame");
		pantalla.content.addComponent(navegador);
		hideMenu();
	}

	private void showMenu() {
		selected = true;
		pantalla.navBarContainer.removeStyleName("menu-toggle");
		pantalla.btnMenu.setIcon(VaadinIcons.CLOSE);
	}

	private void hideMenu() {
		selected = false;
		pantalla.navBarContainer.addStyleName("menu-toggle");
		pantalla.btnMenu.setIcon(VaadinIcons.MENU);
	}

	public CssLayout getContent() {
		return pantalla.content;
	}

	public void addUserInfo(Label lblInformacionConexion) {
		pantalla.header.addComponent(lblInformacionConexion);	
	}

}
