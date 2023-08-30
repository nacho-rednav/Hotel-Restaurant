package Presentacion.Command.Habitacion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.SAHabitacion;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarHabitacionCommand implements Command {


	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAHabitacion sah = fsa.generarSAHabitacion();
		Integer res = sah.eliminarHabitacion((Integer) data);
		
		Context resContext = null;
		if(res == Events.ERROR_HABITACION_NO_EXISTE){
			resContext = new Context(Events.BAJA_HABITACION_KO, "Error:Habitaci�n no existente");	
		}
		else if(res == Events.ERROR_HABITACION_YA_DADA_DE_BAJA){
			resContext = new Context(Events.BAJA_HABITACION_KO, "Error:Habitaci�n ya dada de baja");	
		}
		else if(res == Events.ERROR_HABITACION_RESERVADA){
			resContext = new Context(Events.BAJA_HABITACION_KO, "Error:Habitaci�n reservada");	
		}
		else{
			resContext = new Context(Events.BAJA_HABITACION_OK, res);
		}
		return resContext;
	}
}