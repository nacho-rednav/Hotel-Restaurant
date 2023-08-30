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


public class EditarServicioComand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAServicio saRecep = fsa.generarSAServicio();
		TServicio res = saRecep.mostrarPorId((Integer)data);
		
		Context resContext = null;
		if(res == null || !res.getActivo()){
			resContext = new Context(Events.MODIFICAR_SERVICIO_KO, "Error: servicio no existente"); 
		} else{						
			resContext = new Context(Events.ABRIR_VMODIFICAR_SERVICIO2, res);
		}
		return resContext;
	}
}