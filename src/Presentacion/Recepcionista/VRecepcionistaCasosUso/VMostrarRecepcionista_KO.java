package Presentacion.Recepcionista.VRecepcionistaCasosUso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarRecepcionista_KO extends JFrame implements IGUI {
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, data);
		Controller.obtenerInstancia().accion(Events.ABRIR_VRECEPCIONISTA, null);
	}
}
