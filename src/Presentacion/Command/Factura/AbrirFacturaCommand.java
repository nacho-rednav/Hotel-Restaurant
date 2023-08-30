package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class AbrirFacturaCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		int res = saF.abrir((TFactura)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res  == -1 || (res > 2000 && res < 2100)){
			resContext = new Context(Events.ALTA_FACTURA_KO, res);
		}
		else {
			resContext = new Context(Events.ALTA_FACTURA_OK, res);
		}
		
		return resContext;
	}

}
