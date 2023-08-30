package Presentacion.Controller;

import Presentacion.Command.Command;
import Presentacion.Command.CommandFactory;
import Presentacion.Command.Context;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class ControllerImp extends Controller {

	public void accion(int event, Object data) {
		Command c = CommandFactory.getInstance().getCommand(event);

		if (c == null) {
			Context context = new Context(event, data);
			FactoriaVistas.getInstance().createView(context);
		} else {
			Context r = c.executeCommand(data);
			IGUI view = FactoriaVistas.getInstance().createView(r);
			view.update(r.getData());
		}
	}

}