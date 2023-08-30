/**
 * 
 */
package Presentacion.Command.Mesa;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosMesaCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMesa saMesa = factoria.generarSAMesa();
		Collection<TMesa> res = saMesa.mostrarTodos();
		
		Context resContext = null; //Si llega null algo va mal
		
		resContext = new Context(Events.ABRIR_VMOSTRAR_MESA_TODAS, res);
		
		return resContext;
	}
}