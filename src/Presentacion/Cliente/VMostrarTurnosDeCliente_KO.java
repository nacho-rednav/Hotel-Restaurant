package Presentacion.Cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarTurnosDeCliente_KO extends JFrame implements IGUI {

	@Override
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Error al mostrar los turnos del cliente");
		Controller.obtenerInstancia().accion(Events.ABRIR_VCLIENTE, null);
	}
}
