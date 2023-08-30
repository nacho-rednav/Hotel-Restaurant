package Presentacion.Reserva;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VDevolucionServicio_OK extends JFrame implements IGUI{	
	
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Devoluci�n realizada con �xito");
		Controller.obtenerInstancia().accion(Events.ABRIR_VRESERVA, null);
	}
}
