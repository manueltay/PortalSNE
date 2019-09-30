package gt.gob.sib.portal.sne.menu;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import gt.gob.sib.portal.sne.core.MenuTemplateView;

public class PantallaPrincipalView extends MenuTemplateView {
	private static final long serialVersionUID = 1L;
	public Boolean production = false;

	@Override
	public void enter(ViewChangeEvent event) {
		try {
			loadPage("/ComunicacionesElectronicasExt/?no_redir=true");
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e);
		}
	}
	
}
