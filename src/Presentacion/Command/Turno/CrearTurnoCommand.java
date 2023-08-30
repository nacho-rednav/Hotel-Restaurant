package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearTurnoCommand implements Command {


	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		int res = saTurno.crearTurno((TTurno) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res > 0) {
			mensaje = "Turno creado con el id " + res;
			resContext = new Context(Events.CREAR_TURNO_OK, mensaje);
		}
		else {
			mensaje = "No se ha podido crear turno con el id " + res;
			resContext = new Context(Events.CREAR_TURNO_KO, mensaje);
		}
		
		return resContext;
	}
}