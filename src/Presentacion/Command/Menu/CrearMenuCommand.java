package Presentacion.Command.Menu;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class CrearMenuCommand implements Command{
	
	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAMenu saMenu = fsa.generarSAMenu();
		int res = saMenu.crear((TMenu) data);
				
		Context resContext = null;
		if (res > 0) {
			resContext = new Context(Events.ALTA_MENU_OK, res);
		}
		else {
			resContext = new Context(Events.ALTA_MENU_KO, null);
		}
		
		return resContext;
	}
}
