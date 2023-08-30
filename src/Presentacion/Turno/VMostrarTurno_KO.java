package Presentacion.Turno;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarTurno_KO extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al mostrar turno");
		Controller.obtenerInstancia().accion(Events.ABRIR_VTURNO, null);
	}
}
