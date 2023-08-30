package Presentacion.Habitacion;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VCrearHabitacion_OK extends JFrame implements IGUI{	
	
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Creada habitacion con ID: " + Integer.toString((int)data));
		Controller.obtenerInstancia().accion(Events.ABRIR_VHABITACION, null);
	}
}
