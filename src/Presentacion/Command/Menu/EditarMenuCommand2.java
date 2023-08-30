package Presentacion.Command.Menu;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarMenuCommand2 implements Command{

	@Override
	public Context executeCommand(Object data) {
		SAMenu saMenu = FactoriaSA.getInstance().generarSAMenu();
		
		int res = saMenu.editar((TMenu)data);
		
		Context resContext = null; 
		String mensaje;
        if(res == -1){
        	mensaje = "Menú no ha podido ser editado";
            resContext = new Context(Events.MODIFICAR_MENU_KO, mensaje);
        }
        else {
        	mensaje = "Menú editado con éxito";
            resContext = new Context(Events.MODIFICAR_MENU_OK, mensaje);
        }

        return resContext;
	}

}

