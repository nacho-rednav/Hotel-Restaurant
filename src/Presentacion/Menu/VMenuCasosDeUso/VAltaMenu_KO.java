package Presentacion.Menu.VMenuCasosDeUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAltaMenu_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Menu no se ha podido dar de alta");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMENU, null);
	}
}