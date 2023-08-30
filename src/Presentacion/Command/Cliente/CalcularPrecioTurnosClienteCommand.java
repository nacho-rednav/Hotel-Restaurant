package Presentacion.Command.Cliente;

import Negocio.Cliente.SACliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CalcularPrecioTurnosClienteCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SACliente saCliente = fsa.generarSACliente();
		double res = saCliente.calcularPrecioTurnosCliente( (int) data);
		
		Context resContext = null;
		String mensaje = null;
		
		if(res == Events.ERROR_CLIENTE_DATOS){
			//mensaje =  res;
			mensaje = "Cliente no existente";
			resContext = new Context(Events.CALCULAR_PRECIO_TURNOS_KO, mensaje);
			
		}
		else if(res >= 0){
			resContext = new Context(Events.CALCULAR_PRECIO_TURNOS_OK, res);
			
		}
		else {
			mensaje = "Error";
			resContext = new Context(Events.ABRIR_VCLIENTE, mensaje);
		}
		return resContext;
	}
}