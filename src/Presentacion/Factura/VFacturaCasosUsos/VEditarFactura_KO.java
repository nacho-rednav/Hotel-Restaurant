package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarFactura_KO extends JFrame implements IGUI {
	public void update(Object data) {
		int err = (int)data;
		String msg = "Error al modificar factura";
		if(err == Events.ERROR_FACTURA_DISPONIBILIDAD_MESA) msg = "Mesa elegida no disponible";
		else if(err == Events.ERROR_FACTURA_MESA_INACTIVA) msg = "Mesa elegida inexistente";
		else if(err == Events.ERROR_FACTURA_POR_EMPLEADO) msg = "Empleado elegido inexistente";
		else if(err == Events.ERROR_FACTURA_ID_INEXISTENTE) msg = "Factura inexistente";

		JOptionPane.showMessageDialog(this, msg);
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}
