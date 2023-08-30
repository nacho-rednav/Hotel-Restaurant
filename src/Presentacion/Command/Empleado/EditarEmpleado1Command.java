package Presentacion.Command.Empleado;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarEmpleado1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAEmpleado saEmpleado = factoria.generarSAEmpleado();
		TEmpleado res = saEmpleado.mostrarUno((Integer) data);

		Context resContext = null;

		String mensaje;
		if (res == null) {
			mensaje = "No se puede modificar el empleado";
			resContext = new Context(Events.MODIFICAR_EMPLEADO_KO, mensaje);
		} else {
			resContext = new Context(Events.ABRIR_VMODIFICAR_EMPLEADO2, res);
		}

		return resContext;
	}

}
