package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TReserva;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarReserva2Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		int res = sar.editarReserva((TReserva)data);
		
		Context resContext = null;
		if(res == Events.ERROR_RESERVA_CODIGO_YA_EXISTE){
			resContext = new Context(Events.MODIFICAR_RESERVA_KO, "Error:Ya existe una reserva con este c�digo");
		}
		else if(res == Events.ERROR_RESERVA_ENTITDADES){
			resContext = new Context(Events.MODIFICAR_RESERVA_KO, "Error:Entidades incorrectas");
		}
		else if(res == Events.ERROR_RESERVA_DATOS){
			resContext = new Context(Events.MODIFICAR_RESERVA_KO, "Datos incorrectas");
		}
		else{
			resContext = new Context(Events.MODIFICAR_RESERVA_OK, null);
		}
		return resContext;
	}
}