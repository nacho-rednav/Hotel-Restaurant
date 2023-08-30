package Presentacion.Command.Menu;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarTodosMenusCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMenu saMenu = factoria.generarSAMenu();
		Collection<TMenu> res = saMenu.mostrarTodos();

		Context resContext = new Context(Events.ABRIR_VMOSTRAR_MENU_TODOS, res);

		return resContext;
	}

}
