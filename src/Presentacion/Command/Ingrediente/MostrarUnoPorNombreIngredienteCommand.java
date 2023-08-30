package Presentacion.Command.Ingrediente;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoPorNombreIngredienteCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAIngrediente saIngr = fsa.generarSAIngrediente();
		TIngrediente tIngr = saIngr.mostrarUnoPorNombre((String) data);
		
		Context resContext = null;
		String mensaje;
		if (tIngr != null) {
			resContext = new Context(Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE_OK, tIngr);
		}
		else {
			mensaje = "No se ha podido leer el ingrediente";
			resContext = new Context(Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE_KO, mensaje);
		}
		return resContext;
	}
}
