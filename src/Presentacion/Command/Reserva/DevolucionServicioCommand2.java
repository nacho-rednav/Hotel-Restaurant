package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TLineaPedidoServicios;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class DevolucionServicioCommand2 implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		int res = sar.devolucionServicio((TLineaPedidoServicios)data);
		
		Context resContext = null;
		if(res == Events.ERROR_RESERVA_COMPRA_INEXISTENTE){
			resContext = new Context(Events.DEVOLUCION_SERVICIO_RESERVA_KO, "Error:Compra inexistente");
		}
		else if(res == Events.ERROR_RESERVA_ENTITDADES){
			resContext = new Context(Events.DEVOLUCION_SERVICIO_RESERVA_KO, "Error:Entidades incorrectas");
		}
		else if(res == Events.ERROR_RESERVA_DATOS){
			resContext = new Context(Events.DEVOLUCION_SERVICIO_RESERVA_KO, "Datos incorrectas");
		}
		else{
			resContext = new Context(Events.DEVOLUCION_SERVICIO_RESERVA_OK, null);
		}
		
		return resContext;
	}
}