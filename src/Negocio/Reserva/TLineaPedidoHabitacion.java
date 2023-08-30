package Negocio.Reserva;


public class TLineaPedidoHabitacion {
	
	private Integer idHab;
	private Integer idReserva;

	
	public TLineaPedidoHabitacion(int id_reserva, int id_habitacion) {
		idHab = id_habitacion;
		idReserva = id_reserva;
	}


	public void setIdHab(Integer id) {
		this.idHab = id;
	}

	
	public void setIdReserva(Integer id) {
		this.idReserva = id;
	}

	
	public Integer getIdHab() {
		return this.idHab;
	}

	
	public Integer getIdReserva() {
		return this.idReserva;
	}
	
}