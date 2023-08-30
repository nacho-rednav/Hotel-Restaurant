package Negocio.ReservaServicio;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import Negocio.Reserva.Reserva;
import Negocio.Reserva.TReserva;
import Negocio.Servicio.Servicio;

@Entity
@IdClass(ReservaServicioId.class)
public class CompraServicio {
	
	@Id
	@ManyToOne
	private Reserva reserva;
	
	@Id
	@ManyToOne
	private Servicio servicio;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Version
	int version;
	
	public CompraServicio(){}
	
	public CompraServicio(Reserva r, Servicio s, Date fecha){
		reserva = r;
		servicio = s;
		this.fecha = fecha;
	}
	public Reserva getReserva() {
		return reserva;
	}
	
	
	public boolean equals(Object o){
		return((o instanceof CompraServicio) &&
				reserva.getId() == ((CompraServicio)o).getReserva().getId() &&
				servicio.getId() == ((CompraServicio)o).getServicio().getId());
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public TCompraServicio entityToTransfer(){
		return new TCompraServicio(reserva.getId(), servicio.getId(), fecha);
	}
	
}
