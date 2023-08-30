package Presentacion.Command.Empleado;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarEmpleado2Command implements Command{

	@Override
	public Context executeCommand(Object data) {
		SAEmpleado saEmp = FactoriaSA.getInstance().generarSAEmpleado();
		
		int res = saEmp.editar((TEmpleado)data);
		
		Context resContext = null; 
		String mensaje;
        if(res == -1){
        	mensaje = "Empleado no ha podido ser editado";
            resContext = new Context(Events.MODIFICAR_EMPLEADO_KO, mensaje);
        }
        else {
        	mensaje = "Empleado editado con exito";
            resContext = new Context(Events.MODIFICAR_EMPLEADO_OK, mensaje);
        }

        return resContext;
	}

}
