package gt.gob.sib.portal.sne.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import gt.gob.sib.portal.sne.core.PortalCustomComponent;
import gt.gob.sib.portal.sne.core.model_ws.Option;
import gt.gob.sib.portal.sne.menu.catalog.Opcion;
import gt.gob.sib.portal.sne.menu.catalog.Proceso;

public class MenuAplicacionesCtl extends PortalCustomComponent {
	private static final long serialVersionUID = -8434649591335054094L;

	private MenuAplicaciones pantalla;
	private Boolean selected = true;
	private BrowserFrame navegador;

	public MenuAplicacionesCtl() {
		try {
			pantalla = new MenuAplicaciones();
			setCompositionRoot(pantalla);
			//loadPage("http://appdesa.sib.gob.gt/ComunicacionesElectronicasExt/");
			addFunctionality();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e.getMessage());
		}
	}

	private void addFunctionality() {
		cargarOpciones();
		pantalla.btnMenu.addClickListener(event -> {
			if (selected) {
				hideMenu();
			} else {
				showMenu();
			}
		});
	}

	private void cargarOpciones() {
		try {
			MenuBar menuOpciones = new MenuBar();
			menuOpciones.addStyleName("borderless");
			cargarMenu(menuOpciones, getServiceLocatorWS().listAvailableOptions("MTAY"));
			pantalla.menu.addComponent(menuOpciones);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarMenu(MenuBar menuOpciones, List<Option> opciones) {
		menuOpciones.removeItems();
		HashMap<Long, MenuItem> items = new HashMap<Long, MenuItem>();

		if (opciones != null) {
			for (Option opcion : opciones) {
				MenuItem item = null;
				if (opcion.getIdPadre() != null) {
					item = items.get(opcion.getIdPadre()).addItem(opcion.getNombre(), null, mycommand(opcion.getUrlOpcion()));
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
		} else {
			menuOpciones.addItem("No se tienen procesos");
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
