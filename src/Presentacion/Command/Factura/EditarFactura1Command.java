package Presentacion.Command.Factura;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Factura.SAFactura;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaCompleta;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarFactura1Command implements Command{
	@Override
	public Context executeCommand(Object data) {
		SAFactura saF = FactoriaSA.getInstance().generarSAFactura();
		TFactura res = saF.leerUnoPorId((Integer)data);
		
		Context resContext = null; //Si llega null algo va mal
	
		if(res == null){
			resContext = new Context(Events.MOSTRAR_UNA_FACTURA_KO, res);//Si falla aquí es como falla run mostrar, es por el id
		}
		else {
			resContext = new Context(Events.ABRIR_VMODIFICAR_FACTURA2, res);
		}
		
		return resContext;
	}
}
