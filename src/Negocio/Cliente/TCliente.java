package Negocio.Cliente;


public class TCliente {
	
	private Integer id;
	private String nombre;
	private String email;
	private Boolean activo;
	
	
	public TCliente(Integer id, Boolean activo, String nombre,String email) {
		this.id = id;
		this.activo = activo;
		this.nombre = nombre;
		this.email = email;
	}
	
	public TCliente() {
		
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
	
	public  Integer getID() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public void setId(Integer id_cliente) {
		this.id = id_cliente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}