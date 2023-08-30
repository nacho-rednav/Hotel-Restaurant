package Presentacion.Habitacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarHabitacion_OK extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Habitacion modificada");
		Controller.obtenerInstancia().accion(Events.ABRIR_VHABITACION, null);
	}
}
