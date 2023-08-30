package Presentacion.Command.Servicio;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EliminarServicioCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAServicio saServicio = fsa.generarSAServicio();
		Integer res = saServicio.eliminar((Integer) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res == -1) {
			mensaje = "No se ha podido eliminar el servicio";
			resContext = new Context(Events.BAJA_SERVICIO_KO, mensaje);
		}
		else {
			mensaje = "Recepcionista eliminado con exito";
			resContext = new Context(Events.BAJA_SERVICIO_OK, mensaje);
		}
		
		return resContext;
	}
}