package Presentacion.Command.Factura;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.TMenu;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class ElegirMenusAniadirMenusFacturaCommand implements Command{
	@Override
	public Context executeCommand(Object data) {
		Collection<TMenu> lista = FactoriaSA.getInstance().generarSAMenu().mostrarTodos();
		
		Context resContext = null; //Si llega null algo va mal
	
		if(lista == null){
			resContext = new Context(Events.ANIADIR_MENUS_FACTURA_KO, null);
		}
		else {
			Collection<TMenu> res = new ArrayList<TMenu>();
			for(TMenu m : lista){
				if(m.getActivo()) res.add(m);
			}
			resContext = new Context(Events.ABRIR_VANIADIR_MENUS_FACTURA, res);
		}
		
		return resContext;
	}
}
