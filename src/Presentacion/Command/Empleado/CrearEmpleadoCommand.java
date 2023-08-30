package Presentacion.Command.Empleado;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearEmpleadoCommand implements Command{

	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAEmpleado saEmp = fsa.generarSAEmpleado();
		int res = saEmp.crear((TEmpleado) data);
		
		
		Context resContext = null;
		String mensaje = null;
		if (res > 0) {
			mensaje = "Empleado dado de alta con id " + res;
			resContext = new Context(Events.ALTA_EMPLEADO_OK, mensaje);
		}
		else {
			mensaje = "Empleado no se ha podido dar de alta";
			resContext = new Context(Events.ALTA_EMPLEADO_KO, mensaje);
		}
		
		return resContext;
	}

}
