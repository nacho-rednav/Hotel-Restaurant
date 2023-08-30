package Presentacion.Command.Servicio;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class MostrarPorIdServicioCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		SAServicio saS = FactoriaSA.getInstance().generarSAServicio();
		TServicio res = saS.mostrarPorId((Integer)data);
		
		Context resContext = null;
		
		if (res != null) {
			
			resContext = new Context(Events.MOSTRAR_SERVICIO_ID_OK, res);
		}
		else {
			String mensaje = "No se ha encontrado el servicio";
			resContext = new Context(Events.MOSTRAR_SERVICIO_ID_KO, mensaje);
		}
		
		return resContext;
	}
}