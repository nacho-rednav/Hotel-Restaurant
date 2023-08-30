package Negocio.ReservaServicio;

import java.io.Serializable;

public class ReservaServicioId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer reserva;
	private Integer servicio;
	
	public ReservaServicioId(){}
	
	public ReservaServicioId(Integer r, Integer s){
		reserva = r;
		servicio = s;
	}
	
	public boolean equals(Object o){
		return((o instanceof ReservaServicioId) &&
				reserva == ((ReservaServicioId)o).getReserva() &&
				servicio == ((ReservaServicioId)o).getServicio());
	}
	
	public int hashCode(){
		return Integer.valueOf(reserva * 31 + servicio).hashCode();
	}
	
	public Integer getReserva() {
		return reserva;
	}

	public void setReserva(Integer reserva) {
		this.reserva = reserva;
	}

	public Integer getServicio() {
		return servicio;
	}

	public void setServicio(Integer servicio) {
		this.servicio = servicio;
	}
}
