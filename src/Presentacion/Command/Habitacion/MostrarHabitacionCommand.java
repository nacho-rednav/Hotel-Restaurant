package Presentacion.Command.Habitacion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.SAHabitacion;
import Negocio.Habitacion.THabitacion;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class MostrarHabitacionCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAHabitacion sah = fsa.generarSAHabitacion();
		THabitacion res = sah.buscarUnaHabitacionPorId((Integer) data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.MOSTRAR_HABITACION_ID_KO, null);	
		}
		else{
			resContext = new Context(Events.MOSTRAR_HABITACION_ID_OK, res);
		}
		
		return resContext;
	}
	
}