package Negocio.Habitacion;


public class THabitacion {
	
	private Integer planta;
	private Integer id;
	private Integer numero;
	private Boolean activo;
	
	public THabitacion(Integer planta, Integer numero, Boolean activo){
		this.planta = planta;
		this.numero = numero;
		this.activo = activo;
	}
	public THabitacion(Integer id, Integer planta, Integer numero, Boolean activo){
		this(planta, numero, activo);
		this.id = id;
	}
	
	public THabitacion() {
		
	}

	public Integer getPlanta() {
		return planta;
	}

	public void setPlanta(Integer planta) {
		this.planta = planta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}