package Presentacion.Command.Empleado;

import Negocio.Empleado.SAEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarEmpleadoCommand implements Command{

	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAEmpleado saEmpleado = factoria.generarSAEmpleado();
		int res = saEmpleado.eliminar((Integer)data);
		
		Context resContext = null;
		String mensaje;
		if(res == -1){
			mensaje = "No se ha podido dar de baja ese empleado";
			resContext = new Context(Events.BAJA_EMPLEADO_KO, mensaje);
		}
		else {
			mensaje = "Empleado dado de baja con exito";
			resContext = new Context(Events.BAJA_EMPLEADO_OK, mensaje);
		}
		
		return resContext;
	}
}
