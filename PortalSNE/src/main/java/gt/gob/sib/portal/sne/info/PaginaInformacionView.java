package gt.gob.sib.portal.sne.info;

import gt.gob.sib.portal.sne.core.MenuTemplateView;

public class PaginaInformacionView extends MenuTemplateView {
	private PaginaInformacionCtl pantalla;

	public PaginaInformacionView() {
		try {
			if (pantalla == null) {
				pantalla = new PaginaInformacionCtl();
				getMainLayout().addComponent(pantalla);
			}
		} catch (Exception e) {
			errorMessage(e);
			e.printStackTrace();
		}
	}

}
