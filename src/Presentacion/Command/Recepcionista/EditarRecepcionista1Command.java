
package Presentacion.Command.Recepcionista;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EditarRecepcionista1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SARecepcionista saRecep = fsa.generarSARecepcionista();
		TRecepcionista res = saRecep.mostrarPorId((Integer)data);
		
		Context resContext = null;
		if(res == null || !res.getActivo()){
			resContext = new Context(Events.MODIFICAR_RECEPCIONISTA_KO, "Error: recepcionista no existente"); 
		}else{																	 			
			resContext = new Context(Events.ABRIR_VMODIFICAR_RECEPCIONISTA2, res);
		}
		return resContext;
	}
	

}