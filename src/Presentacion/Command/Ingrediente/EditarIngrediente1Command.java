package Presentacion.Command.Ingrediente;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarIngrediente1Command implements Command {

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
		TIngrediente tIngr = saIngr.mostrarUno((Integer) data);

		Context resContext = null;
		String mensaje;

		if (tIngr == null) {
			mensaje = "No existe el ingrediente";
			resContext = new Context(Events.MODIFICAR_INGREDIENTE_KO, mensaje);
		} else {
			resContext = new Context(Events.ABRIR_VMODIFICAR_INGREDIENTE2, tIngr);
		}
		return resContext;
	}
}