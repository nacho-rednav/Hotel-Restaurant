/**
 * 
 */
package Presentacion.Command.Mesa;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoMesaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMesa saMesa = factoria.generarSAMesa();
		TMesa res = saMesa.mostrarUno((Integer)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res == null){
			resContext = new Context(Events.MOSTRAR_UNA_MESA_KO, res);
		}
		else {
			resContext = new Context(Events.MOSTRAR_UNA_MESA_OK, res);
		}
		
		return resContext;
	}
}