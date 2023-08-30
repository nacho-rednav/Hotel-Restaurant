package Presentacion.Command.Producto;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Presentacion.Command.Context;
import Presentacion.Command.Command;
import Presentacion.Controller.Events;

public class EditarProductoCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		TProducto res = saProd.mostrarUno((Integer) data);
		
		Context resContext = null;

		if (res == null) {
			resContext = new Context(Events.MODIFICAR_PRODUCTO_KO, null);
		}
		else {
			resContext = new Context(Events.ABRIR_VMODIFICAR_PRODUCTO2, res);
		}
		
		return resContext;
	}
}
