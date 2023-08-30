package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAnyadirMenusFactura_OK extends JFrame implements IGUI {
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Menu anyadido");
		Controller.obtenerInstancia().accion(Events.ELEGIR_MENUS_FACTURA, null);
	}
}