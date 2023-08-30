package Presentacion.Turno;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarClienteATurno_KO extends JFrame implements IGUI{

	@Override
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al eliminar");
		Controller.obtenerInstancia().accion(Events.ABRIR_VTURNO, null);
	}

}
