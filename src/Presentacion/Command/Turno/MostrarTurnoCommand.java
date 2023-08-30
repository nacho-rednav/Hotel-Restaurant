package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTurnoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		TTurno res = saTurno.buscarTurno((Integer) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res == null) {
			mensaje = "No se ha encontrado el turno";
			resContext = new Context(Events.MOSTRAR_TURNO_ID_KO, mensaje);
		}
		else {
			resContext = new Context(Events.MOSTRAR_TURNO_ID_OK, res);
		}
		
		return resContext;
	}
}