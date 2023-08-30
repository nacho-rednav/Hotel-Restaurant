package Presentacion.Command.Reserva;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TLineaPedidoServicios;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class CompraServiciosCommand2 implements Command {

	@Override
	public Context executeCommand(Object data) {		
		SAReserva saR = FactoriaSA.getInstance().generarSAReserva();
		int res = saR.compraServicio((TLineaPedidoServicios)data);		
		
		Context resContext = null;
		if(res == Events.ERROR_RESERVA_ENTITDADES){
			resContext = new Context(Events.COMPRA_SERVICIO_RESERVA_KO, "Error:Entidades incorrectas");
		}
		else if(res == Events.ERROR_RESERVA_DATOS){
			resContext = new Context(Events.COMPRA_SERVICIO_RESERVA_KO, "Datos incorrectas");
		}
		else{
			resContext = new Context(Events.COMPRA_SERVICIO_RESERVA_OK, null);
		}
		return resContext;
	}
}