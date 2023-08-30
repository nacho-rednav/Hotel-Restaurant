package Presentacion.Command.Empleado;

import java.util.Collection;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosEmpleadoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAEmpleado saEmpleado = factoria.generarSAEmpleado();
		Collection<TEmpleado> res = saEmpleado.mostrarTodos();

		Context resContext = null;

		resContext = new Context(Events.ABRIR_VMOSTRAR_EMPLEADO_TODOS, res);

		return resContext;
	}

}
