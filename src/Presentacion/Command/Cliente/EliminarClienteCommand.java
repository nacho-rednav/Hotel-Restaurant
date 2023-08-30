package Presentacion.Command.Cliente;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarClienteCommand implements Command {


	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		int res = saCliente.eliminarCliente((int) data);
		
		Context resContext = null;
		String mensaje = null;
		if(res == Events.ERROR_CLIENTE_NO_EXISTE){
			resContext = new Context(Events.BAJA_CLIENTE_KO, "Error:Cliente no existente");	
		}
		else if(res == Events.ERROR_CLIENTE_YA_DADO_DE_BAJA){
			resContext = new Context(Events.BAJA_CLIENTE_KO, "Error:Cliente ya dado de baja");	
		}
		else if(res == Events.ERROR_CLIENTE_TIENE_RESERVA){
			resContext = new Context(Events.BAJA_CLIENTE_KO, "Error:Cliente tiene una reserva");	
		}
		else{
			resContext = new Context(Events.BAJA_CLIENTE_OK, res);
		}
		/*if(res > 0){
			//mensaje = "Eliminado cliente con el id " + res;
			resContext = new Context(Events.BAJA_CLIENTE_OK, res);
		}
		else{
			mensaje = "No se ha podido eliminar el cliente";
			resContext = new Context(Events.BAJA_CLIENTE_KO, mensaje);
			
		}*/
		return resContext;
	}
}