package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VCerrarFactura_OK extends JFrame implements IGUI {
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Factura Cerrada");
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}