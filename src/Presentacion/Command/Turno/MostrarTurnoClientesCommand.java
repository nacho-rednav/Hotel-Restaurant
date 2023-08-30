package Presentacion.Command.Turno;

import java.util.ArrayList;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTurnoClientesCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SATurno saTurno = fsa.generarSATurno();
		TTurno tur = saTurno.buscarTurno((Integer) data);
		
		Context resContext = null;
		
		if (tur != null) {
			ArrayList<TCliente> res = saTurno.mostrarClientesTurno((Integer) data);
			resContext = new Context(Events.MOSTRAR_TURNO_CLIENTES_OK, res);
		}
		else {
			resContext = new Context(Events.MOSTRAR_TURNO_CLIENTES_KO, null);
		}
		
		return resContext;
	}

}
