package Presentacion.Command.Producto;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarProducto2Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		SAProducto saProd = FactoriaSA.getInstance().generarSAProducto();
		
		int res = saProd.editar((TProducto)data);
		
		Context resContext = null; 
		String mensaje;
        if (res == -1) {
        	mensaje = "Producto no ha podido ser editado";
            resContext = new Context(Events.MODIFICAR_PRODUCTO_KO, mensaje);
        }
        else {
        	mensaje = "Producto editado con exito";
            resContext = new Context(Events.MODIFICAR_PRODUCTO_OK, mensaje);
        }

        return resContext;
	}

}
