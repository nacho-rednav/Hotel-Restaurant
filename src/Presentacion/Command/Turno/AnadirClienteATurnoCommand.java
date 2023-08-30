package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurnoCliente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class AnadirClienteATurnoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		int res = saTurno.aniadirClienteATurno((TTurnoCliente) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res > 0) {
			mensaje = "Anyadido correctamente el cliente a turno";
			resContext = new Context(Events.VINCULAR_TURNO_CLIENTE_OK, mensaje);
		}
		else {
			mensaje = "No se ha podido anyadir el cliente al turno";
			resContext = new Context(Events.VINCULAR_TURNO_CLIENTE_KO, mensaje);
		}
		
		return resContext;
	}
}