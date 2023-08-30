package Negocio.Reserva;

import java.util.ArrayList;

import Negocio.Habitacion.THabitacion;
import Negocio.ReservaServicio.TCompraServicio;


public class TReservaCompleta {
	
	private TReserva reserva;
	private Integer idCliente;
	private Integer idRecepcionista;
	private ArrayList<TCompraServicio> servicios;
	private ArrayList<THabitacion> habitaciones;

	public TReservaCompleta(TReserva r, ArrayList<TCompraServicio> servs, ArrayList<THabitacion> habs){
		reserva = r;
		idCliente = r.getId_Cliente();
		idRecepcionista = r.getId_Recepcionista();
		servicios = servs;
		habitaciones = habs;
	}
	
	public void setReserva(TReserva reserva) {
		this.reserva = reserva;
	}

	
	public void setIdCliente(Integer id) {
		this.idCliente = id;
	}

	
	public void setIdRecepcionista(Integer id) {
		this.idRecepcionista = id;
	}

	
	public TReserva getReserva() {
		return this.reserva;
	}

	
	public Integer getIdCliente() {
		return this.idCliente;
	}

	
	public Integer getIdRecepcionista() {
		return this.idRecepcionista;
	}

	
	public ArrayList<TCompraServicio> getServicios() {
		return this.servicios;
	}

	
	public ArrayList<THabitacion> getHabitaciones() {
		return this.habitaciones;
	}


	public void setServicios(ArrayList<TCompraServicio> servicios) {
		this.servicios = servicios;
	}


	public void setHabitaciones(ArrayList<THabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	
}