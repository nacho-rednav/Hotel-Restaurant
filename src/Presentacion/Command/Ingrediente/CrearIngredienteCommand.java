package Presentacion.Command.Ingrediente;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearIngredienteCommand implements Command {

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
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAIngrediente saIngr = fsa.generarSAIngrediente();
		int res = saIngr.crear((TIngrediente) data);

		Context resContext = null;
		String mensaje;
		if (res >= 0) {
			mensaje = "Ingrediente creado con id " + res;
			resContext = new Context(Events.ALTA_INGREDIENTE_OK, mensaje);
		} else {
			mensaje = "El ingrediente no se ha podido crear";
			resContext = new Context(Events.ALTA_INGREDIENTE_KO, mensaje);
		}

		return resContext;
	}
}