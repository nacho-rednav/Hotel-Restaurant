package Presentacion.Habitacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarHabitacion_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al eliminar habitacion");
		Controller.obtenerInstancia().accion(Events.ABRIR_VHABITACION, null);
	}
}
