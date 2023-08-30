package Presentacion.Launcher;


import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;

public class Main {

	public static void main(String[] args) {
		Controller.obtenerInstancia().accion(Events.ABRIR_VPRINCIPAL, null);
		
	}

}
