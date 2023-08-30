package Presentacion.Command.Producto;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProductoIngrediente;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class VincularIngredienteProducto implements Command {
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		int res = saProd.vincular((TProductoIngrediente) data);
		
		Context resContext = null;
		String mensaje = null;
		
		if (res == -5) {
			mensaje = "El producto no existe o no está activo";
			resContext = new Context(Events.VINCULAR_INGREDIENTE_NO_EXISTE_PRODUCTO, mensaje);
		}
		else if (res == -4) {
			mensaje = "El ingrediente no existe o no está activo";
			resContext = new Context(Events.VINCULAR_INGREDIENTE_NO_EXISTE_INGREDIENTE, mensaje);
		}
		else if (res == -2) {
			mensaje = "El ingrediente ya esta vinculado";
			resContext = new Context(Events.VINCULAR_INGREDIENTE_YA_VINCULADO, mensaje);
		}
		else if (res == -1) {
			mensaje = "No se puedo vincular ingrediente al producto";
			resContext = new Context(Events.VINCULAR_INGREDIENTE_KO, mensaje);
		}
		else {
			resContext = new Context(Events.ABRIR_VVINCULAR_INGREDIENTE, res);
		}
		
		return resContext;
	}
}
