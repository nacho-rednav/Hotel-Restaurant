package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarMenusFactura_OK extends JFrame implements IGUI {
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Menu eliminado");
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}