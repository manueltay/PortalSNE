package gt.gob.sib.portal.sne.info;

import gt.gob.sib.portal.sne.core.PortalCustomComponent;

public class PaginaInformacionCtl extends PortalCustomComponent {
	PaginaInformacion pantalla;

	public PaginaInformacionCtl() {
		constructor();
	}
	
	private void constructor() {
		try {
			pantalla = new PaginaInformacion();
			setCompositionRoot(pantalla);
			addFunctionality();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(e.getMessage());
		}
	}

	private void addFunctionality() {
		// TODO Auto-generated method stub
		
	}

}
