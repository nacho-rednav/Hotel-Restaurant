package Presentacion.Turno;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VEliminarTurnoOK extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Eliminado Turno");
	}
}