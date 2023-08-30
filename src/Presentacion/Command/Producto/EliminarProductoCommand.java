package Presentacion.Command.Producto;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarProductoCommand implements Command {
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		int res = saProd.eliminar((Integer) data);
		
		Context resContext = null;
		String mensaje;
		if (res == -1) {
			mensaje = "No se ha podido eliminar el producto";
			resContext = new Context(Events.BAJA_PRODUCTO_KO, mensaje);
		}
		else {
			mensaje = "Producto eliminado con exito";
			resContext = new Context(Events.BAJA_PRODUCTO_OK, mensaje);
		}
		
		return resContext;
	}
}
