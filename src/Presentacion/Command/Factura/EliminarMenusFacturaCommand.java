package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TLineaDePedido;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarMenusFacturaCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		int res = saF.eliminarMenus((TLineaDePedido)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res  != 0){
			resContext = new Context(Events.ELIMINAR_MENUS_FACTURA_KO, res);
		}
		else {
			resContext = new Context(Events.ELIMINAR_MENUS_FACTURA_OK, res);
		}
		
		return resContext;
	}
}
