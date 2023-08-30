package Presentacion.Command.Producto;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProdIngrs;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoProductoCommand implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAProducto saProducto = factoria.generarSAProducto();
		TProducto producto = saProducto.mostrarUno((Integer) data);
		ArrayList<TIngrediente> ingrs = saProducto.mostrarIngrsPorProd((Integer) data);
		TProdIngrs prodIngrs = new TProdIngrs(producto, ingrs);

		Context resContext = null;
		String mensaje;
		if (producto != null) {
			resContext = new Context(Events.MOSTRAR_UN_PRODUCTO_OK, prodIngrs);
		} else {
			mensaje = "No se ha podido leer el producto";
			resContext = new Context(Events.MOSTRAR_UN_PRODUCTO_KO, mensaje);
		}

		return resContext;
	}

}
