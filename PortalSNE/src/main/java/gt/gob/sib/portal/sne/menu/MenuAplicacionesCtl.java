package gt.gob.sib.portal.sne.menu;

import java.util.HashMap;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

import gt.gob.sib.portal.sne.core.PortalCustomComponent;
import gt.gob.sib.portal.sne.core.model_ws.Option;
import gt.gob.sib.portal.sne.core.model_ws.Process;
import gt.gob.sib.portal.sne.info.PaginaInformacionCtl;

public class MenuAplicacionesCtl extends PortalCustomComponent {
	private static final long serialVersionUID = -8434649591335054094L;

	private MenuAplicaciones pantalla;
	private Boolean selected = true;
	private BrowserFrame navegador;

	public MenuAplicacionesCtl() {
		try {
			pantalla = new MenuAplicaciones();
			setCompositionRoot(pantalla);
			addFunctionality();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e.getMessage());
		}
	}

	private void addFunctionality() throws Exception {
		cargarMenu(getServiceLocatorWS().listAvailableOptions(getUsuario().getUsuario()));
		pantalla.btnMenu.addClickListener(event -> {
			if (selected) {
				hideMenu();
			} else {
				showMenu();
			}
		});
	}

	private void cargarMenu(List<Process> procesos) {
		HashMap<Long, MenuItem> items = new HashMap<Long, MenuItem>();
		MenuBar menuOpciones = null;
		if (procesos != null) {
			for (Process proceso : procesos) {
				if (proceso.getOpciones() != null) {
					MenuItem item = null;
					menuOpciones = new MenuBar();
					menuOpciones.addStyleName("borderless");
					pantalla.menu.addComponent(menuOpciones);
					for (Option opcion : proceso.getOpciones())
						if (opcion.getIdPadre() != null) {
							item = items.get(opcion.getIdPadre()).addItem(opcion.getNombre(), null,
									mycommand(opcion.getUrlOpcion()));
							items.put(opcion.getId(), item);
						} else {
							if (opcion.getUrlOpcion() == null || opcion.getUrlOpcion().isEmpty()) {
								item = menuOpciones.addItem(opcion.getNombre(), null, null);
								items.put(opcion.getId(), item);
							} else {
								item = menuOpciones.addItem(opcion.getNombre(), null, mycommand(opcion.getUrlOpcion()));
								items.put(opcion.getId(), item);
							}
						}
				}
			}
		}
	}

	private <T> MenuBar.Command mycommand(String url) {
		return new MenuBar.Command() {
			private static final long serialVersionUID = 1L;

			public void menuSelected(MenuItem selectedItem) {
				if (url != null)
					loadPage(url);
			}
		};
	}

	public void loadPage(String url) {
		if (url.contains("info")) {
			pantalla.content.removeAllComponents();
			pantalla.content.addComponent(new PaginaInformacionCtl());
		} else {
			String protocol = UI.getCurrent().getPage().getLocation().toString().split("//")[0];
			String url2 = "";
			if (isAmbienteDesarrollo())
				url2 = protocol + "//" + UI.getCurrent().getPage().getLocation().getHost() + ":8080" + url;
			else 
				url2 = protocol + "//" + UI.getCurrent().getPage().getLocation().getHost() + url;
			System.out.println(url2);
			getPrincipalUIExterno().getNavigator().navigateTo("");
			pantalla.content.removeAllComponents();
			navegador = new BrowserFrame("", new ExternalResource(url2));			
			navegador.setWidth("100%");
			navegador.addStyleName("embedded-frame");
			pantalla.content.addComponent(navegador);
		}
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
	
	public boolean isAmbienteDesarrollo() {
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}
}
