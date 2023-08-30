package Presentacion.Command.Cliente;

import java.util.ArrayList;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTurnosClienteCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		TCliente cli = saCliente.buscarUnCliente((int) data);
		
		Context resContext = null;
		
		if(cli != null){
			ArrayList<TTurno> res = saCliente.mostrarTurnosCliente((int)data);
			resContext = new Context(Events.ABRIR_VMOSTRAR_TURNOS_DE_CLIENTE_OK, res);
		}
		else{
			resContext = new Context(Events.MOSTRAR_TURNOS_DE_CLIENTE_KO, null);
			
		}
		return resContext;
	}
}