package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarTurno1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurn = fsa.generarSATurno();
		TTurno res = saTurn.buscarTurno((Integer) data);
		
		Context resContext = null;

		if (res == null) {
			resContext = new Context(Events.MODIFICAR_TURNO_KO, null);
		}
		else {
			resContext = new Context(Events.ABRIR_VMODIFICAR_TURNO2, res);
		}
		
		return resContext;
	}
}