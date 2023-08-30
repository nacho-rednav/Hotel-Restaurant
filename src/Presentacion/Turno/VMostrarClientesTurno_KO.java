package Presentacion.Turno;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarClientesTurno_KO extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al mostrar los clientes de un turno");
		Controller.obtenerInstancia().accion(Events.ABRIR_VTURNO, null);
	}	
}
