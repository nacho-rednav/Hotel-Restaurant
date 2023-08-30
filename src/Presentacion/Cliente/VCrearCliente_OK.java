package Presentacion.Cliente;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VCrearCliente_OK extends JFrame implements IGUI{	
	
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Creado Cliente con ID: " + Integer.toString((int)data));
		Controller.obtenerInstancia().accion(Events.ABRIR_VCLIENTE, null);
	}
}
