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
		cargarProcesos();
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
		for (Proceso proceso: Proceso.values()) {
			MenuBar menuOpciones = new MenuBar();
			menuOpciones.addStyleName("borderless");
			cargarMenu(menuOpciones, proceso.getOpciones());
			pantalla.menu.addComponent(menuOpciones);
		}
	}

	private void cargarMenu(MenuBar menuOpciones, List<Opcion> opciones) {
		menuOpciones.removeItems();
		HashMap<Integer, MenuItem> items = new HashMap<Integer, MenuItem>();

		if (opciones != null) {
			for (Opcion opcion : opciones) {
				MenuItem item = null;
				if (opcion.getIdPadre() != null) {
					item = items.get(opcion.getIdPadre()).addItem(opcion.getNombre(), null, mycommand(opcion.getUrl()));
					items.put(opcion.getId(), item);
				} else {
					if (opcion.getUrl() == null || opcion.getUrl().isEmpty()) {
						item = menuOpciones.addItem(opcion.getNombre(), null, null);
						items.put(opcion.getId(), item);
					} else {
						item = menuOpciones.addItem(opcion.getNombre(), null, mycommand(opcion.getUrl()));
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

	// TODO create data structures and implement data obtainability
	private void cargarProcesos() {
		Proceso.SISTEMA_NOTIFICACIONES.setOpciones(getOpcionesSNE());
		Proceso.AGENTES.setOpciones(getOpcionesAgentes());
	}

	private List<Opcion> getOpcionesAgentes() {
		ArrayList<Opcion> opciones = new ArrayList<Opcion>();
		opciones.add(new Opcion("Agentes Independientes", 2, null, null));
		opciones.add(new Opcion("Polizas de Responsabilidad Civil", 3, null, 2));
		opciones.add(new Opcion("Renovación de Pólizas ", 4, "http://appdesa.sib.gob.gt/SegurosExt/#!agenteList", 3));
		opciones.add(new Opcion("Historial de Envíos", 5, "http://appdesa.sib.gob.gt/SegurosExt/#!historialList", 3));
		opciones.add(new Opcion("Ayuda", 6, "http://appdesa.sib.gob.gt/SegurosExt/#!historialList", 3));
		return opciones;
	}

	private List<Opcion> getOpcionesSNE() {
		ArrayList<Opcion> opciones = new ArrayList<Opcion>();
		opciones.add(new Opcion("Sistema de Notificaciones Electrónicas", 1, "http://appdesa.sib.gob.gt/ComunicacionesElectronicasExt/", null));
		return opciones;
	}
}
