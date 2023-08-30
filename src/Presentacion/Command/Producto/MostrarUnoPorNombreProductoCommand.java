package Presentacion.Command.Producto;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProdIngrs;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoPorNombreProductoCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		TProducto tProd = saProd.mostrarUnoPorNombre((String) data);
		
		Context resContext = null;
		String mensaje;
		
		if (tProd != null) {
			ArrayList<TIngrediente> ingrs = saProd.mostrarIngrsPorProd((Integer) tProd.getId());
			TProdIngrs prodIngrs = new TProdIngrs(tProd, ingrs);
			resContext = new Context(Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE_OK, prodIngrs);
		}
		else {
			mensaje = "No se ha podido leer el producto";
			resContext = new Context(Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE_KO, mensaje);
		}
		
		return resContext;
	}
}
