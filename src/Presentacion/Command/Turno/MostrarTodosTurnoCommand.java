package Presentacion.Command.Turno;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosTurnoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		
		Collection<TTurno> turnos = saTurno.buscarTodosTurno();
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_TODOS_TURNO, turnos);

		return resContext;
	}
}