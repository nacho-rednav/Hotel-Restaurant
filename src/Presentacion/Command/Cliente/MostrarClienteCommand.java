package Presentacion.Command.Cliente;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarClienteCommand implements Command {


	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		TCliente res = saCliente.buscarUnCliente((Integer) data);
		
		Context resContext = null;
		
		if(res != null){
			resContext = new Context(Events.MOSTRAR_CLIENTE_OK, res);
		}
		else{
			resContext = new Context(Events.MOSTRAR_CLIENTE_KO, null);
			
		}
		return resContext;
	}
}