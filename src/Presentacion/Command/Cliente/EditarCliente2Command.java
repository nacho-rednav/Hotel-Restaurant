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

public class EditarCliente2Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		int res = saCliente.editarCliente((TCliente) data);
		
		Context resContext = null;
		String mensaje = null;
		
        if (res == -1) {
			mensaje = "No se ha podido editar el cliente";
			resContext = new Context(Events.MODIFICAR_CLIENTE_KO, mensaje);
        }
        else {
			//mensaje = "Cliente editado con el id " + res;
			resContext = new Context(Events.MODIFICAR_CLIENTE_OK, res);
        }

		return resContext;
	}
}