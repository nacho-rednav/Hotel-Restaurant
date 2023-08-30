package Presentacion.Command.Factura;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarFacturaTodasCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		Collection<TFactura> res = saF.mostrarTodos();
		
		Context resContext = new Context(Events.ABRIR_VMOSTRAR_FACTURA_TODAS, res);; //Si llega null algo va mal
		
		return resContext;
	}

}
