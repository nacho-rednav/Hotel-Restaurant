package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFacturaCompleta;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarFacturaCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		TFacturaCompleta res = saF.mostrarUno((Integer)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res == null){
			resContext = new Context(Events.MOSTRAR_UNA_FACTURA_KO, res);
		}
		else {
			resContext = new Context(Events.MOSTRAR_UNA_FACTURA_OK, res);
		}
		
		return resContext;
	}
}
