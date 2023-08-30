package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EliminarReservaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		Integer res = sar.eliminarReserva((Integer) data);
		
		Context resContext = null;
		if(res == Events.ERROR_RESERVA_ENTITDADES){
			resContext = new Context(Events.BAJA_RESERVA_KO, "Error:Entidades incorrectas");
		}
		else if(res == Events.ERROR_RESERVA_DATOS){
			resContext = new Context(Events.BAJA_RESERVA_KO, "Datos incorrectas");
		}
		else{
			resContext = new Context(Events.BAJA_RESERVA_OK, res);
		}
		return resContext;
	}
}