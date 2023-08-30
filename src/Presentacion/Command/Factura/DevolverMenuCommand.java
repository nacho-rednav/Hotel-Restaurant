package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TLineaDePedido;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class DevolverMenuCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		int res = saF.devolverMenus((TLineaDePedido)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res  != 0){
			resContext = new Context(Events.DEVOLVER_MENU_KO, res);
		}
		else {
			resContext = new Context(Events.DEVOLVER_MENU_OK, res);
		}
		
		return resContext;
	}
}
