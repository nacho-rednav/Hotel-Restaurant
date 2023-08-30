
package Presentacion.Command.Servicio;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearServioCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAServicio saServicio = fsa.generarSAServicio();
		int res = saServicio.crear((TServicio)data);
		
		Context resContext = null;
		String mensaje = null;
		
		if(res > 0){
			mensaje = "Servicio dado de alta con id " + res;
			resContext = new Context(Events.ALTA_SERVICIO_OK, mensaje);
		}
		else{
			mensaje = "Recepcionista no se ha podido dar de alta";
			resContext = new Context(Events.ALTA_SERVICIO_KO, mensaje);
			
		}
		return resContext;
	}
}