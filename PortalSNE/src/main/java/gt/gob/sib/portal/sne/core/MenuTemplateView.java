package gt.gob.sib.portal.sne.core;

import com.vaadin.ui.UI;

import gt.gob.sib.framework.core.services.BaseServiceLocator;
import gt.gob.sib.portal.sne.PrincipalUI;
import gt.gob.sib.portal.sne.core.model_ws.Usuario;

public class MenuTemplateView extends PortalTemplateView<BaseServiceLocator> {

	public MenuTemplateView() {

		Usuario usuario = getPrincipalUIExterno().getUsuario();
		if (usuario != null) {
			String value = "Usuario conectado: " + usuario.getNombres() + ", Institución: "
					+ usuario.getNombreInstitucion();
			addInformacionConexion(value);
		}

	}

	public PrincipalUI getPrincipalUIExterno() {
		return ((PrincipalUI) UI.getCurrent());
	}

}
