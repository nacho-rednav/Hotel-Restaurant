package Negocio.Habitacion;


public class TBasica extends THabitacion {
	protected Float descuento;

	public TBasica(Integer planta, Integer numero, Boolean activo, Float descuento) {
		super(planta, numero, activo);
		this.descuento = descuento;
	}
	public TBasica(Integer id, Integer planta, Integer numero, Boolean activo, Float descuento) {
		super(id, planta, numero, activo);
		this.descuento = descuento;
	}
	
	public TBasica() {
	}

	public void setDescuento(Float dsc) {
		this.descuento = dsc;
	}

	
	public Float getDescuento() {
		return this.descuento;
	}

}