package Negocio.Turno;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.Comida.findBymenuDia", query = "select obj from Comida obj where :menuDia = obj.menuDia "),
		@NamedQuery(name = "Negocio.Turno.Comida.findBycosteServicio", query = "select obj from Comida obj where :costeServicio = obj.costeServicio ") })
public class Comida extends Turno implements Serializable {

	private static final long serialVersionUID = 0;

	public Comida() {
	}

	private String menuDia;

	private Float costeServicio;

	public String getMenuDia() {
		return menuDia;
	}

	public void setMenuDia(String menuDia) {
		this.menuDia = menuDia;
	}

	public Float getCosteServicio() {
		return costeServicio;
	}

	public void setCosteServicio(Float costeServicio) {
		this.costeServicio = costeServicio;
	}
	@Override
	public void transferToEntity(TTurno tturno) {
		super.transferToEntity(tturno);
		TComida comida = (TComida) tturno;
		this.setMenuDia(comida.getMenuDia());
		this.setCosteServicio(comida.getCosteServicio());
	}
	
	public TTurno entityToTransfer() {
		TTurno tturno = super.entityToTransfer();
		TComida tcomida = new TComida(tturno.getId(), tturno.getDia(), tturno.getCapacidad(), tturno.getPrecio(), tturno.getActivo(), costeServicio, menuDia);
		return tcomida;
	}
	
	@Override
	public Float getPrecio(){
		return super.getPrecio();
	}

	@Override
	public Float getCalcularPrecio(){
		return super.getPrecio() + costeServicio;
	}
}