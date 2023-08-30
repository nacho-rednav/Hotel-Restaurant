package Presentacion.Habitacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarHabitacion_OK extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Habitacion eliminada");
		Controller.obtenerInstancia().accion(Events.ABRIR_VHABITACION, null);
	}
}