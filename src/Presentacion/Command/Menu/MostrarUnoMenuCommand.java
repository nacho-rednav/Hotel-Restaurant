package Presentacion.Command.Menu;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProds;
import Negocio.Menu.TMenuProducto;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class MostrarUnoMenuCommand implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
		SAMenu saMenu = factoria.generarSAMenu();
		TMenu menu = saMenu.mostrarUno((Integer) data);
		ArrayList<TProducto> prods = saMenu.mostrarProdsPorMenu((Integer) data);
		TMenuProds menuProds = new TMenuProds(menu, prods);

		Context resContext = null;
		String mensaje;
		if (menu != null) {
			resContext = new Context(Events.MOSTRAR_UN_MENU_OK, menuProds);
		} else {
			resContext = new Context(Events.MOSTRAR_UN_MENU_KO, null);
		}

		return resContext;
	}

}