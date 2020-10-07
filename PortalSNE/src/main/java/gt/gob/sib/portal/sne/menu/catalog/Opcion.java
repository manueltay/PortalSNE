package gt.gob.sib.portal.sne.menu.catalog;

public class Opcion {
	private String nombre;
	private Integer id;
	private String url;
	private Integer idPadre;
	
	public Opcion(String nombre, Integer id, String url, Integer idPadre) {
		this.nombre = nombre;
		this.id = id;
		this.url = url;
		this.idPadre = idPadre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}	
}
