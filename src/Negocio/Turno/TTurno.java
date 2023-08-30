package Negocio.Turno;

import java.util.Date;

public class TTurno {

	private Date dia;

	private Float precio;

	private Integer id;

	private Boolean activo;
	
	private Integer capacidad;
	
	protected Float costeServicio;
	
	protected String menuDia;
	
	protected String tipo;
	
	protected Boolean complementoCafe;
	
	protected Float costeComplemento;
	
	protected Boolean complementoZumo;
	
	protected Boolean complementoFruta;
	
	public TTurno(Integer id, Date dia,Float precio, Boolean activo, Integer capacidad) {
		this.id = id;
		this.dia = dia;
		this.capacidad = capacidad;
		this.precio = precio;
		this.activo = activo;
	}
	
	public TTurno(Date dia,Float precio, Boolean activo, Integer capacidad) {
		this.dia = dia;
		this.capacidad = capacidad;
		this.precio = precio;
		this.activo = activo;
	}


	public TTurno() {

	}

	public Date getDia() {
		return this.dia;
	}

	public Float getPrecio() {
		return this.precio;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getCapacidad(){
		return this.capacidad;
	}

	public Boolean getActivo() {
		return this.activo;
	}
	
	public Float getCosteServicio() {
		return this.costeServicio;
	}
	
	public String getMenuDia() {
		return this.menuDia;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public Boolean getComplementoCafe() {
		return this.complementoCafe;
	}
	
	public Float getCosteComplemento() {
		return this.costeComplemento;
	}

	public Boolean getComplementoFruta() {
		return this.complementoFruta;
	}
	
	public Boolean getComplementoZumo() {
		return this.complementoZumo;
	}
	
	public void setDia(Date dia) {
		this.dia = dia;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	public void setCapacidad(Integer capacidad){
		this.capacidad = capacidad;
	}
		
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public void setCosteServicio(Float costeServicio) {
		this.costeServicio = costeServicio;
	}
	
	public void setMenuDia(String menuDia) {
		this.menuDia = menuDia;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setComplementoCafe(Boolean complementoCafe) {
		this.complementoCafe = complementoCafe;
	}
	
	public void setCosteComplemento(Float costeComplemento) {
		this.costeComplemento = costeComplemento;
	}
	
	public void setComplementoFruta(Boolean complementoFruta) {
		this.complementoFruta = complementoFruta;
	}
	
	public void setComplementoZumo(Boolean complementoZumo) {
		this.complementoZumo = complementoZumo;
	}
}