package Presentacion.Command.Menu;

import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Negocio.Queries.TQuery;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarMenusRangoCommand implements Command{

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMenu saMenu = factoria.generarSAMenu();
		Collection<TMenu> res = saMenu.mostrarMenusRangoPrecio((TQuery)data);

		Context resContext = null;
		String mensaje;
		if (res != null) {
			resContext = new Context(Events.ABRIR_VMOSTRAR_MENU_TODOS, res);
		} else {
			mensaje = "No se han podido leer los menus";
			resContext = new Context(Events.MOSTRAR_MENU_RANGO_KO, mensaje);
		}
		return resContext;
		
	}

}
