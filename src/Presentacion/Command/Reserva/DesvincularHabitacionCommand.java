package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TLineaPedidoHabitacion;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class DesvincularHabitacionCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		int res = sar.desvincularHabitacionReserva((TLineaPedidoHabitacion) data);
		
		Context resContext = null;
		if(res == Events.ERROR_RESERVA_VINCULACION){
			resContext = new Context(Events.DESVINCULAR_HABITACION_RESERVA_KO, "Error:Habitación no vinculada a dicha reserva");
		}
		else if(res == Events.ERROR_RESERVA_ENTITDADES){
			resContext = new Context(Events.DESVINCULAR_HABITACION_RESERVA_KO, "Error:Entidades incorrectas");
		}
		else if(res == Events.ERROR_RESERVA_DATOS){
			resContext = new Context(Events.DESVINCULAR_HABITACION_RESERVA_KO, "Datos incorrectas");
		}
		else{
			resContext = new Context(Events.DESVINCULAR_HABITACION_RESERVA_OK, null);
		}
		
		return resContext;
	}
}