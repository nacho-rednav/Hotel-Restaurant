/**
 * 
 */
package Negocio.Habitacion;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;


@Entity
public class Suite extends Habitacion implements Serializable {
	
	private static final long serialVersionUID = 0;
	private Float sobrecoste;

	
	public Suite() {
	}

	public void setSobrecoste(Float sc) {
		sobrecoste = sc;
	}

	public Float getSobrecoste() {
		return sobrecoste;
	}
	
	@Override
	public void transferToEntity(THabitacion hab){
		super.transferToEntity(hab);
		TSuite suite = (TSuite) hab;
		sobrecoste = suite.getSobrecoste();
	}
	
	@Override
	public THabitacion entityToTransfer() {
		return new TSuite(id, planta, numero, activo, sobrecoste);
	}
}