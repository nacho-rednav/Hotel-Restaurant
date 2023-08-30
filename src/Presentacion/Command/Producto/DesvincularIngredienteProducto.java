package Presentacion.Command.Producto;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProductoIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class DesvincularIngredienteProducto implements Command {
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		int res = saProd.desvincular((TProductoIngrediente) data);
		TProductoIngrediente tProdIngr = (TProductoIngrediente) data;
		
		Context resContext = null;
		String mensaje = null;
		
		if (res == -3) {
			mensaje = "El producto no existe y el ingrediente no existe";
			resContext = new Context(Events.DESVINCULAR_INGREDIENTE_NO_EXISTE_PRODUCTO_NI_INGREDIENTE , mensaje);
		}
		else if (res == -2) {
			mensaje = "El producto no contiene el ingrediente";
			resContext = new Context(Events.DESVINCULAR_INGREDIENTE_NO_VINCULADO, mensaje);
		}
		else if (res == -1) {
			mensaje = "No se pudo desvincular ingrediente del producto";
			resContext = new Context(Events.DESVINCULAR_INGREDIENTE_KO, mensaje);
		}
		else {
			mensaje = "Ingrediente: " + tProdIngr.getIdIngrediente() + " vinculado al producto: " + tProdIngr.getIdProducto();
			resContext = new Context(Events.DESVINCULAR_INGREDIENTE_OK, res);
		}
		
		return resContext;
	}
}
