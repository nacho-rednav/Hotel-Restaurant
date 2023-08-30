/**
 * 
 */
package Presentacion.Command.Cliente;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class CrearClienteCommand implements Command {
	
	

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCli = fsa.generarSACliente();
		int res = saCli.crearCliente((TCliente) data);
		
		Context resContext = null;
		String mensaje = null;
		if(res == Events.ERROR_CLIENTE_DATOS){
			resContext = new Context(Events.ALTA_CLIENTE_KO, "Error:Datos incorrectos");	
		}
		else if(res == Events.ERROR_CLIENTE_NOMBRE_YA_EXISTE){
			resContext = new Context(Events.ALTA_CLIENTE_KO, "Error:Ya existe un cliente con ese nombre");	
		}
		else{
			resContext = new Context(Events.ALTA_CLIENTE_OK, res);
		}
		/*if (res > 0) {
			//mensaje = "Cliente creado con id" + res;
			resContext = new Context(Events.ALTA_CLIENTE_OK, res);
		}
		else {
			mensaje = "Cliente no se ha podido crear";
			resContext = new Context(Events.ALTA_CLIENTE_KO, mensaje);
		}*/
		
		return resContext;
	}
}