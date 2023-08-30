package Negocio.Reserva;

import java.util.Collection;


public interface SAReserva {
	
	public Integer crearReserva(TReserva data);

	public Integer eliminarReserva(Integer id);

	public Integer editarReserva(TReserva data);

	public TReservaCompleta mostrarReservaCompleta(Integer id);
	
	public TReserva mostrarReserva(Integer id);

	public Collection<TReserva> mostrarTodasReservas();

	public Integer compraServicio(TLineaPedidoServicios data);

	public Integer devolucionServicio(TLineaPedidoServicios data);

	public Integer desvincularHabitacionReserva(TLineaPedidoHabitacion data);
}