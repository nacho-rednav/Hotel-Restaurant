package Presentacion.Command.Menu;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class VincularProductoMenuCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
        SAMenu saMenu = factoria.generarSAMenu();        
        int res = saMenu.anadirProd((TMenuProducto) data);       
        
        Context resContext = null; 
        String mensaje = null;

        if(res == -5){
        	mensaje = "El Menú no existe o no esta activo";
        	resContext = new Context(Events.VINCULAR_PRODUCTO_NO_EXISTE_MENU, mensaje);
        }else if(res == -4){
        	mensaje = "El Producto no existe o no está acctivo";
        	resContext = new Context(Events.VINCULAR_PRODUCTO_NO_EXISTE_PRODUCTO, mensaje);
        }
        else if(res == -2){
        	mensaje = "El Producto ya está vinculado";
        	resContext = new Context(Events.VINCULAR_PRODUCTO_YA_VINCULADO, mensaje);
        }
        else if(res == -1){
        	mensaje = "No se pudo vincular producto al menu";
        	resContext = new Context(Events.VINCULAR_PRODUCTO_KO, mensaje);
        }
        else{
        	mensaje = "El producto se ha añadido con éxito al menú";
        	resContext = new Context(Events.VINCULAR_PRODUCTO_OK, mensaje);
        }
		return resContext;
	}

}

