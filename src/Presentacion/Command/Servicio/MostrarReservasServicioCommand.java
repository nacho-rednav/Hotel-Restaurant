package Presentacion.Command.Servicio;

import java.awt.List;
import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.TReserva;
import Negocio.Servicio.SAServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarReservasServicioCommand implements Command {
	

	@Override
	public Context executeCommand(Object data) {
		SAServicio saServicio = FactoriaSA.getInstance().generarSAServicio();	
		ArrayList<TReserva> reservas = saServicio.mostrarReservasServicio((Integer)data);
		Context resContext = null;
		if (reservas == null) {
			resContext= new Context(Events.MOSTRAR_SERVICIO_RESERVAS_KO, "Ese servicio no existe");
		}
		else{
			resContext = new Context(Events.MOSTRAR_SERVICIO_RESERVAS_OK, reservas);
		}
		return resContext;
	}
}