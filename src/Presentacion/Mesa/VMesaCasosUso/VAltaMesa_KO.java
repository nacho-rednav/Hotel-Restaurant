package Presentacion.Mesa.VMesaCasosUso;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAltaMesa_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al crear mesa");
		Controller.obtenerInstancia().accion(Events.ABRIR_VMESA, null);
	}
}
