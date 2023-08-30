package Negocio.Recepcionista;


public class TRecepcionista {
	
	private Integer id;
	private Integer numRecepcionista;
	private String nombre;
	private Float salario;
	private Boolean activo;
	
	public TRecepcionista(Integer numRecepcionista, String nombre, Float salario, Boolean activo) {
		this.numRecepcionista = numRecepcionista;
		this.nombre = nombre;
		this.salario = salario;
		this.activo = activo;
	}
	
	public TRecepcionista(Integer id, Integer numRecepcionista, String nombre, Float salario, Boolean activo) {
		this(numRecepcionista, nombre, salario, activo);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer _id) {
		this.id = _id;
	}

	public Integer getNumRecepcionista() {
		return numRecepcionista;
	}

	public void setNumRecepcionista(Integer _numRecepcionista) {
		this.numRecepcionista = _numRecepcionista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String _nombre) {
		this.nombre = _nombre;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}