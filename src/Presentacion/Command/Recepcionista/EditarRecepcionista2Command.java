/**
 * 
 */
package Presentacion.Command.Recepcionista;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EditarRecepcionista2Command implements Command {
	

	@Override
	public Context executeCommand(Object data) {
		SARecepcionista saRecep = FactoriaSA.getInstance().generarSARecepcionista();
		
		int res = saRecep.editar((TRecepcionista) data);
		
		Context resContext = null;
		String mensaje = "";
		if(res == -1){
			mensaje = "Recepcionista no ha podido ser editado";
			resContext = new Context(Events.MODIFICAR_RECEPCIONISTA_KO, mensaje);
		}else{
			mensaje = "Recepcionista con id " + res + " editado con exito";
			resContext = new Context(Events.MODIFICAR_RECEPCIONISTA_OK, mensaje);
		}
		return resContext;
	}
}