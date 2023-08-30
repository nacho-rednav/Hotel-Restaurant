package Presentacion.Command.Servicio;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosServicioCommand implements Command {
	

	@Override
	public Context executeCommand(Object data) {
		SAServicio saServicio = FactoriaSA.getInstance().generarSAServicio();	
		ArrayList<TServicio> servicios = saServicio.mostrarTodos();
		Context resContext = new Context(Events.MOSTRAR_TODOS_SERVICIOS_OK, servicios);
		return resContext;
	
	}
}