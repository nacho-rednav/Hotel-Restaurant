package Presentacion.Command.Menu;

import Negocio.Menu.SAMenu;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EliminarMenuCommand implements Command{

	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMenu saMenu = factoria.generarSAMenu();
		int res = saMenu.eliminar((Integer)data);
		
		Context resContext = null;

		if(res == -1){
			resContext = new Context(Events.BAJA_MENU_KO, res);
		}
		else {
			resContext = new Context(Events.BAJA_MENU_OK, res);
		}
		
		return resContext;
	}
}
