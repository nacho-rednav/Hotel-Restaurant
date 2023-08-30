package Presentacion.Command.Recepcionista;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Turno.SATurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarRecepcionistaCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SARecepcionista saRecep = fsa.generarSARecepcionista();
		Integer res = saRecep.eliminar((Integer) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res == -1) {
			mensaje = "No se ha podido eliminar el recepcionista";
			resContext = new Context(Events.BAJA_RECEPCIONISTA_KO, mensaje);
		}
		else {
			mensaje = "Recepcionista eliminado con exito";
			resContext = new Context(Events.BAJA_RECEPCIONISTA_OK, mensaje);
		}
		
		return resContext;
	}
}