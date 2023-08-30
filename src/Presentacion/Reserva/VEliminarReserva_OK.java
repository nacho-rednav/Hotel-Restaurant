package Presentacion.Reserva;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarReserva_OK extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Reserva eliminada");
		Controller.obtenerInstancia().accion(Events.ABRIR_VRESERVA, null);
	}
}
