/**
 * 
 */
package Negocio.Habitacion;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;


@Entity
public class Basica extends Habitacion implements Serializable {
	
	private static final long serialVersionUID = 0;
	private Float descuento;

	
	public Basica() {
	}
	
	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float dc) {
		descuento = dc;
	}
	
	@Override
	public void transferToEntity(THabitacion hab){
		super.transferToEntity(hab);
		TBasica bas = (TBasica) hab;
		descuento = bas.getDescuento();
	}

	@Override
	public THabitacion entityToTransfer() {
		return new TBasica(id, planta, numero, activo, descuento);
	}
}