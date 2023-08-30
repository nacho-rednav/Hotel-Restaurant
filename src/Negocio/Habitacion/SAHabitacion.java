package Negocio.Habitacion;

import java.util.List;

import Negocio.Reserva.TReserva;


public interface SAHabitacion {
	
	public Integer crearHabitacion(THabitacion habitacion);

	public Integer eliminarHabitacion(Integer id);

	public Integer editarHabitacion(THabitacion habitacion);

	public THabitacion buscarUnaHabitacionPorId(Integer id);

	public List<THabitacion> buscarTodosHabitaciones();
	
	public THabitacion buscarUnaHabitacionPorNumero(Integer num);

	public List<TReserva> mostrarReservasHabitacion(Integer id);
}