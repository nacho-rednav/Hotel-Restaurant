package Presentacion.Command.Turno;

import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

import static Presentacion.Command.Command.*;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;

public class EliminarTurnoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		Integer res = saTurno.eliminarTurno((Integer) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res == -1) {
			mensaje = "No se ha podido eliminar el turno";
			resContext = new Context(Events.ELIMINAR_TURNO_KO, mensaje);
		}
		else {
			mensaje = "Turno eliminado con exito";
			resContext = new Context(Events.ELIMINAR_TURNO_OK, res);
		}
		
		return resContext;
	}
}