package Presentacion.Command.Habitacion;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.SAHabitacion;
import Negocio.Habitacion.THabitacion;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class MostrarTodasHabitacionCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAHabitacion sah = fsa.generarSAHabitacion();

		Collection<THabitacion> habitacion = sah.buscarTodosHabitaciones();
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_HABITACION_TODOS, habitacion);
		
		return resContext;
	}
	
}