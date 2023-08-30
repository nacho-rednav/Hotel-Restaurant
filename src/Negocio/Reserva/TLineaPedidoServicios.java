package Negocio.Reserva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import Negocio.Servicio.TServicio;


public class TLineaPedidoServicios {
	
	private Integer idReserva;
	private ArrayList<Pair<TServicio, Date>> idsServicios;

	
	public TLineaPedidoServicios(Integer data, ArrayList<Pair<TServicio, Date>> servicios) {
		idReserva = data;
		idsServicios = servicios;
	}


	public void setIdReserva(Integer id) {
		this.idReserva = id;
	}

	
	public Integer getIdReserva() {
		return this.idReserva;
	}

	
	public void setIdsServicios(ArrayList<Pair<TServicio, Date>> servicios) {
		this.idsServicios = servicios;
	}

	
	public ArrayList<Pair<TServicio, Date>> getIdsServicios() {
		return this.idsServicios;
	}
	
}