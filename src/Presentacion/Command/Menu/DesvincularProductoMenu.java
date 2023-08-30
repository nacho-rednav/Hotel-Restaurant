package Presentacion.Command.Menu;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenuProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class DesvincularProductoMenu implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
        SAMenu saMenu = factoria.generarSAMenu();        
        int res = saMenu.eliminarProd((TMenuProducto) data);       
        Context resContext = null; 
        String mensaje = null;

        if(res == -3){
        	mensaje = "El Menú no existe y el Producto no existen";
        	resContext = new Context(Events.DESVINCULAR_PRODUCTO_NO_EXISTE_MENU_NI_PRODUCTO, mensaje);
        }else if(res == -2){
        	mensaje = "El menú no contiene al producto";
        	resContext = new Context(Events.DESVINCULAR_PRODUCTO_NO_VINCULADO, mensaje);
        }
        else if(res == -1){
        	mensaje = "No se pudo desvincular producto del menu";
        	resContext = new Context(Events.DESVINCULAR_PRODUCTO_KO, mensaje);
        }
        else{
        	mensaje = "Producto desvinculado con exito";
        	resContext = new Context(Events.DESVINCULAR_PRODUCTO_OK, mensaje);
        }
		return resContext;
	}

}
