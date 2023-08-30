package Presentacion.Menu.VMenuCasosDeUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VBajaMenu_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "No se ha podido eliminar el menú");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMENU, null);
	}
}
