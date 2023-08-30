package Presentacion.Command.Recepcionista;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearRecepcionistaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SARecepcionista saRecepcionista = fsa.generarSARecepcionista();
		int res = saRecepcionista.crear((TRecepcionista)data);
		
		Context resContext = null;
		String mensaje = null;
		
		if(res > 0){
			mensaje = "Recepcionista dado de alta con id " + res;
			resContext = new Context(Events.ALTA_RECEPCIONISTA_OK, mensaje);
		}
		else{
			mensaje = "Recepcionista no se ha podido dar de alta";
			resContext = new Context(Events.ALTA_RECEPCIONISTA_KO, mensaje);
			
		}
		return resContext;
	}
}