/**
 * 
 */
package Presentacion.Command.Servicio;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EditarServicioComand2 implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		SAServicio saRecep = FactoriaSA.getInstance().generarSAServicio();
		
		int res = saRecep.editar((TServicio) data);
		
		Context resContext = null;
		String mensaje = "";
		if(res == -1){
			mensaje = "Servicio no ha podido ser editado";
			resContext = new Context(Events.MODIFICAR_SERVICIO_KO, mensaje);
		}else{
			mensaje = "Servicio con id " + res + " editado con exito";
			resContext = new Context(Events.MODIFICAR_SERVICIO_OK, mensaje);
		}
		return resContext;
	}
}