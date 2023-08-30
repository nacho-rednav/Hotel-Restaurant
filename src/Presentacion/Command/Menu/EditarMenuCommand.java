package Presentacion.Command.Menu;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarMenuCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
        SAMenu saMenu = factoria.generarSAMenu();
        TMenu res = saMenu.mostrarUno((Integer)data);

        Context resContext = null; 

        if(res == null){
            resContext = new Context(Events.MODIFICAR_MENU_KO, "Menu no encontrado");
        }
        else {
            resContext = new Context(Events.ABRIR_VMODIFICAR_MENU2, res);
        }

        return resContext;
	}

}
