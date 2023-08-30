package Presentacion.Menu.VMenuCasosDeUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarMenu_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "No se pudo mostrar menú");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMENU, null);
	}
}
