package Presentacion.Command.Cliente;

import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;
import java.util.Collection;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;

public class MostrarTodosClienteCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		
		Collection<TCliente> clientes = saCliente.buscarTodosCliente();
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_CLIENTE_TODOS, clientes);		
		
		return resContext;
	}
}