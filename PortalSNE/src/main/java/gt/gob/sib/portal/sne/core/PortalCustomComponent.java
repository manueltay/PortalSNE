package gt.gob.sib.portal.sne.core;

import com.vaadin.ui.UI;

import gt.gob.sib.framework.vaadin.SIBCustomComponent;
import gt.gob.sib.portal.sne.PrincipalUI;
import gt.gob.sib.portal.sne.core.model.ServiceLocator;
import gt.gob.sib.portal.sne.core.model_ws.Usuario;

public class PortalCustomComponent extends SIBCustomComponent {
	private static final long serialVersionUID = -4964477353631988297L;

	public ServiceLocator getServiceLocatorWS() {
		return getPrincipalUIExterno().getServiceLocator();
	}
	
	public Usuario getUsuario() {
		return getPrincipalUIExterno().getUsuario();
	}
	
	public String getUsername() {
		return getPrincipalUIExterno().getUsername();
	}

	public PrincipalUI getPrincipalUIExterno() {
		return ((PrincipalUI) UI.getCurrent());
	}
}