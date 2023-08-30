package Presentacion.Command.Cliente;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarCliente1Command implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		TCliente res = saCliente.buscarUnCliente((int) data);
		
		Context resContext = null;
		String mensaje = null;
		
		if(res  == null){
			mensaje = "Error: Cliente no existente";
			resContext = new Context(Events.MODIFICAR_CLIENTE_KO, mensaje);
		}
		else{
			resContext = new Context(Events.ABRIR_VMODIFICAR_CLIENTE2, res);			
		}
		return resContext;
	}
}