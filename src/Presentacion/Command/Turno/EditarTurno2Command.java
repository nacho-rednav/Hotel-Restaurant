package Presentacion.Command.Turno;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarTurno2Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		SATurno saTurn = FactoriaSA.getInstance().generarSATurno();
		
		int res = saTurn.editarTurno((TTurno)data);
		
		Context resContext = null; 
		String mensaje;
        if (res == -1) {
        	mensaje = "Turno no ha podido ser editado";
            resContext = new Context(Events.MODIFICAR_TURNO_KO, mensaje);
        }
        else {
        	mensaje = "Turno editado con exito";
            resContext = new Context(Events.MODIFICAR_TURNO_OK, mensaje);
        }

        return resContext;
	}
}