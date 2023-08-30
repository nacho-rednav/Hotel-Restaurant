package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarFactura2Command implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		int res = saF.editar((TFactura)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res  != 0){
			resContext = new Context(Events.MODIFICAR_FACTURA_KO, res);
		}
		else {
			resContext = new Context(Events.MODIFICAR_FACTURA_OK, res);
		}
		
		return resContext;
	}
}
