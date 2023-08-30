package Presentacion.Command.Empleado;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoEmpleadoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAEmpleado saEmpleado = factoria.generarSAEmpleado();
		TEmpleado res = saEmpleado.mostrarUno((Integer) data);

		Context resContext = null;
		String mensaje;
		if (res != null) {
			resContext = new Context(Events.MOSTRAR_UN_EMPLEADO_OK, res);
		} else {
			mensaje = "No se ha podido leer el empleado";
			resContext = new Context(Events.MOSTRAR_UN_EMPLEADO_KO, mensaje);
		}

		return resContext;
	}

}
