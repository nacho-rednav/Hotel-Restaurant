package Presentacion.Mesa.VMesaCasosUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarMesa_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Mesa con id inexistente");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMESA, null);
	}
}
