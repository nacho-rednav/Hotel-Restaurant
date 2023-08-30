package Presentacion.Command.Producto;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosProductosCommand implements Command {
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		
		Collection<TProducto> productos = saProd.mostrarTodos();
		Context resContext = new Context(Events.MOSTRAR_PRODUCTO_TODOS_OK, productos);
		
		return resContext;
	}
}