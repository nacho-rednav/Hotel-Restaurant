package Presentacion.Command.Reserva;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TReserva;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosReservaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {

		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		
		Collection<TReserva> reservas = sar.mostrarTodasReservas();
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_RESERVA_TODOS, reservas);
		
		return resContext;
	}

}