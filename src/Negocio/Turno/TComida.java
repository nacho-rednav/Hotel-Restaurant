package Negocio.Turno;

import java.util.Date;

public class TComida extends TTurno {

	public TComida(Integer id, Date dia, Integer capacidad, Float precio, Boolean activo, Float costeServicio, String menuDia) {
		super(id, dia, precio, activo,capacidad);
		this.costeServicio = costeServicio;
		this.menuDia = menuDia;
	}

	public TComida(Date dia, Integer capacidad, Float precio, Boolean activo, Float costeServicio, String menuDia) {
		super(dia, precio, activo, capacidad);
		this.costeServicio = costeServicio;
		this.menuDia = menuDia;
		
	}

	public Float getCosteServicio() {
		return this.costeServicio;
	}

	public String getMenuDia() {
		return this.menuDia;
	}

	public void setCosteServicio(Float costeServicio) {
		this.costeServicio = costeServicio;
	}

	public void setMenuDia(String menuDia) {
		this.menuDia = menuDia;
	}
}