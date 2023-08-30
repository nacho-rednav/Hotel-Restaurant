package Negocio.Servicio;

public class TServicio {
	
	private Integer id;
	private String tipo;
	private String descripcion;
	private Float precio;
	private Boolean activo;
	
	public TServicio(String tipo, String descripcion, Float precio, Boolean activo) {
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.activo = activo;
	}
	
	public TServicio(Integer id, String tipo, String descripcion, Float precio, Boolean activo) {
		this(tipo, descripcion, precio, activo);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}