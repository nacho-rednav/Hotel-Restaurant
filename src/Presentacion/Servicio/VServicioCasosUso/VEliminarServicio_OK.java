package Presentacion.Servicio.VServicioCasosUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarServicio_OK extends JFrame implements IGUI {
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Servicio eliminado correctamente");
		Controller.obtenerInstancia().accion(Events.ABRIR_VSERVICIO, null);
	}
}
