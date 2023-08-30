package Presentacion.Command.Recepcionista;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class MostrarPorIdRecepcionistaCommand implements Command {
	

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SARecepcionista saRecep = fsa.generarSARecepcionista();
		TRecepcionista res = saRecep.mostrarPorId((Integer)data);
		
		Context resContext = null;
		
		if(res == null){
			String mensaje = "No se ha encontrado el recepcionista";
			resContext = new Context(Events.MOSTRAR_RECEPCIONISTA_ID_KO, mensaje);
		}
		else{
			resContext = new Context(Events.MOSTRAR_RECEPCIONISTA_ID_OK, res);
		}
		return resContext;
	}
}