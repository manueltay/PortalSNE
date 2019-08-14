package gt.gob.sib.portal.sne.menu;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import gt.gob.sib.portal.sne.core.MenuTemplateView;

public class MenuAplicacionesView extends MenuTemplateView {

	private MenuAplicacionesCtl componente = null;

	@Override
	public void enter(ViewChangeEvent event) {
		try {
			if (componente == null) {
				componente = new MenuAplicacionesCtl();
				getMainLayout().addComponent(componente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e);
		}
	}
}
