package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TReserva;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarReserva1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sah = fsa.generarSAReserva();
		TReserva res = sah.mostrarReserva((Integer) data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.MODIFICAR_RESERVA_KO, "Reserva no existente");	
		}
		else{
			resContext = new Context(Events.ABRIR_VMODIFICAR_RESERVA2, res);
		}
		
		return resContext;
	}
}
