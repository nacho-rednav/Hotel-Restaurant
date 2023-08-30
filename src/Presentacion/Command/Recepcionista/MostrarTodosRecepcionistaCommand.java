package Presentacion.Command.Recepcionista;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class MostrarTodosRecepcionistaCommand implements Command {
	@Override
	public Context executeCommand(Object data) {
		SARecepcionista saRecep = FactoriaSA.getInstance().generarSARecepcionista();
		
		Collection<TRecepcionista> receps = saRecep.mostrarTodos();
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_TODOS_RECEPCIONISTA, receps);
		return resContext;
	}
}