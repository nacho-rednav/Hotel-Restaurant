package Negocio.Turno;

import java.sql.Time;
import java.util.Date;

public class TDesayuno extends TTurno {

	protected String tipo;
	
	protected Boolean complementoCafe;
	
	protected Float costeComplemento;
	
	protected Boolean complementoZumo;
	
	protected Boolean complementoFruta;
	
	public TDesayuno(Integer id, Date dia, Integer capacidad, Float precio, Boolean activo, String tipo, Boolean complementoCafe, Float costeComplemento, Boolean complementoZumo,
			Boolean complementoFruta) {
		super(id, dia, precio, activo,capacidad);
		this.tipo = tipo;
		this.complementoCafe = complementoCafe;
		this.costeComplemento = costeComplemento;
		this.complementoZumo = complementoZumo;
		this.complementoFruta = complementoFruta;
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

	public Boolean getComplementoZumo() {
		return this.complementoZumo;
	}

	public Boolean getComplementoFruta() {
		return this.complementoFruta;
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

	public void setComplementoZumo(Boolean complementoZumo) {
		this.complementoZumo = complementoZumo;
	}

	public void setComplementoFruta(Boolean complementoFruta) {
		this.complementoFruta = complementoFruta;
	}
}