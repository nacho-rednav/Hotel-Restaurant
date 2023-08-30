package Presentacion.Mesa.VMesaCasosUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VBajaMesa_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al eliminar mesa");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMESA, null);
	}
}
