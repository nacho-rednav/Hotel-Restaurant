package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VCerrarFactura_KO extends JFrame implements IGUI {
	public void update(Object data) {
		int err = (int) data;
		String msg = "Error al cerrar factura";
		if(err == Events.ERROR_FACTURA_MESA_INACTIVA)msg = "Hubo un problema con la mesa asignada a esta factura";
		else if(err == Events.ERROR_FACTURA_ID_INEXISTENTE) msg = "Factura inexistente o ya cerrada";

		JOptionPane.showMessageDialog(this, msg);
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}
