package Presentacion.FactoriaVistas;

import Presentacion.Command.Context;
import Presentacion.Controller.IGUI;

public abstract class FactoriaVistas {

	private static FactoriaVistas instancia;

	public static FactoriaVistas getInstance() {
		if (instancia == null) {
			instancia = new FactoriaVistasImp();
		}
		return instancia;

	}

	public abstract IGUI createView(Context context);
}