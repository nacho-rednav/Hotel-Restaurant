package Presentacion.Command.Factura;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarFacturaPorMesaCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		Collection<TFactura> res = saF.mostrarTodosPorMesa((Integer)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res == null){
			resContext = new Context(Events.MOSTRAR_FACTURA_POR_KO, res);
		}
		else {
			resContext = new Context(Events.ABRIR_VMOSTRAR_FACTURA_TODAS, res);
		}
		
		return resContext;
	}
}
