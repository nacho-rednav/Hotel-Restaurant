package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Negocio.Turno.TTurnoCliente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarClienteATurnoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		int res = saTurno.eliminarClienteATurno((TTurnoCliente) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res > 0) {
			mensaje = "Eliminado cliente en turno correctamente con id " + res;
			resContext = new Context(Events.DESVINCULAR_TURNO_CLIENTE_OK, mensaje);
		}
		else {
			mensaje = "No se ha podido eliminar el cliente del turno";
			resContext = new Context(Events.DESVINCULAR_TURNO_CLIENTE_KO, mensaje);
		}
		
		return resContext;
	}
}