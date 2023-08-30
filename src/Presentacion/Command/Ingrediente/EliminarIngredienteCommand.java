package Presentacion.Command.Ingrediente;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarIngredienteCommand implements Command {

	/** 
	* (non-Javadoc)
	* @see Command#executeCommand()
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void executeCommand() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAIngrediente saIng = factoria.generarSAIngrediente();
		int res = saIng.eliminar((Integer) data);

		Context resContext = null;
		String mensaje;
		if (res == -1) {
			mensaje = "El ingrediente no se ha podido eliminar";
			resContext = new Context(Events.BAJA_INGREDIENTE_KO, mensaje);
		} else if (res == 0) {
			mensaje = "Ingrediente eliminado con id " + res;
			resContext = new Context(Events.BAJA_INGREDIENTE_OK, mensaje);
		}

		return resContext;
	}

}