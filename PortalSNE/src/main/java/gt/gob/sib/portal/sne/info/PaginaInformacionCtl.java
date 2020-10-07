package gt.gob.sib.portal.sne.info;

import java.util.List;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;

import gt.gob.sib.portal.sne.core.PortalCustomComponent;
import gt.gob.sib.portal.sne.core.model_ws.Videos;

public class PaginaInformacionCtl extends PortalCustomComponent {
	private static final long serialVersionUID = 555454057162873193L;
	private PaginaInformacion pantalla;
	private List<Videos> videos;

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

	private void addFunctionality() throws Exception {
		addVideoCatalog();
	}

	private void addVideoCatalog() throws Exception {
		videos = getServiceLocatorWS().listVideos(getUsuario().getUsuario());
		for (Videos video : videos) {
			String idVideo = video.getId();
			Layout tab1 = new VerticalLayout(); // Wrap in a layout
			tab1.setWidth("100%");
			Label titulo = new Label(video.getNombre());
			titulo.setStyleName("title");
			tab1.addComponent(titulo);
			Label cuerpo = new Label(video.getDescripcion());
			cuerpo.setContentMode(ContentMode.HTML);
			cuerpo.setWidth("100%");
			tab1.addComponent(cuerpo);
			Button select = new Button();
			select.setCaption("Reproducir");
			select.setStyleName("primary");
			select.addClickListener(event -> {
				selectVideo(idVideo);
			});
			tab1.addComponent(select);
			pantalla.listVideo.addTab(tab1, video.getNombre());
		}

	}

	private void selectVideo(String id) {
		pantalla.content.removeAllComponents();
		
		final Resource mp4Resource = getServiceLocatorWS().getVideo(id);
		Video vv = new Video("", mp4Resource);
		vv.setAltText("El navegador no puede reproducir el vídeo.");
		vv.setResponsive(true);
		vv.setAutoplay(true);
		pantalla.content.addComponent(vv);
		
	}

}
