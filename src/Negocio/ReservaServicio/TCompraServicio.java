package Negocio.ReservaServicio;

import java.util.Date;

public class TCompraServicio {
	private Integer id_Reserva;
	private Integer id_Servicio;
	private Date fecha;
	
	public TCompraServicio(){}
	
	public TCompraServicio(Integer res, Integer serv, Date f){
		id_Reserva = res;
		id_Servicio = serv;
		fecha = f;
	}
	
	public Integer getId_Reserva() {
		return id_Reserva;
	}
	public void setId_Reserva(Integer id_Reserva) {
		this.id_Reserva = id_Reserva;
	}
	public Integer getId_Servicio() {
		return id_Servicio;
	}
	public void setId_Servicio(Integer id_Servicio) {
		this.id_Servicio = id_Servicio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
