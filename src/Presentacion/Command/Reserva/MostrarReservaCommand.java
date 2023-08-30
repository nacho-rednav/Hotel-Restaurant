package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TReserva;
import Negocio.Reserva.TReservaCompleta;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarReservaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		SAReserva saR = FactoriaSA.getInstance().generarSAReserva();
		TReservaCompleta res = saR.mostrarReservaCompleta((Integer)data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.MOSTRAR_RESERVA_ID_KO, "Error:Reserva no existente");
		}
		else{
			resContext = new Context(Events.MOSTRAR_RESERVA_ID_OK, res);
		}
		
		return resContext;
	}
}