package Presentacion.Servicio.VServicioCasosUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarServicio_KO extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "No se ha podido eliminar el servicio");
		Controller.obtenerInstancia().accion(Events.ABRIR_VSERVICIO, null);
	}
}
