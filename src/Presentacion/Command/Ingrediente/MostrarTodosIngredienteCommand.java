package Presentacion.Command.Ingrediente;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosIngredienteCommand implements Command {

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

		Collection<TIngrediente> ingredientes = saIngr.mostrarTodos();
		Context resContext = new Context(Events.MOSTRAR_INGREDIENTE_TODOS_OK, ingredientes);

		return resContext;
	}

}