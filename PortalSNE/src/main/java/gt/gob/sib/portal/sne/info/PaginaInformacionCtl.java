package gt.gob.sib.portal.sne.info;
 
import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Video;

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
		File file = new File("C:/Users/mtay/Pictures/Videos exportados/Video_Pequeño.mp4");
		System.out.println("File " + file.getAbsolutePath());
		FileResource fileResource = new FileResource(file);
		Video vv = new Video("Video",fileResource);
		pantalla.content.addComponent(vv);
	}

}
