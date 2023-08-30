/**
 * 
 */
package Presentacion.Command.Habitacion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.SAHabitacion;
import Negocio.Habitacion.THabitacion;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class EditarHabitacion1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAHabitacion sah = fsa.generarSAHabitacion();
		THabitacion res = sah.buscarUnaHabitacionPorId((Integer) data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.MODIFICAR_HABITACION_KO, "Error:Habitacion no existente");	
		}
		else if(!res.getActivo()){
			resContext = new Context(Events.MODIFICAR_HABITACION_KO, "Error:Habitacion no existente");	
		}
		else{
			resContext = new Context(Events.ABRIR_VMODIFICAR_HABITACION2, res);
		}
		return resContext;
	}
}