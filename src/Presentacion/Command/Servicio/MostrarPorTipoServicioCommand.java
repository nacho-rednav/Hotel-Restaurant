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


public class MostrarPorTipoServicioCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		SAServicio saS = FactoriaSA.getInstance().generarSAServicio();
		TServicio res = saS.mostrarPorTipo((String)data);
		
		Context resContext = null;
		
		if (res != null) {
			resContext = new Context(Events.MOSTRAR_SERVICIO_TIPO_OK, res);
		}
		else {
			String mensaje = "No se ha encontrado el servicio";
			resContext = new Context(Events.MOSTRAR_SERVICIO_ID_KO, mensaje);
		}
		
		return resContext;
	}
}