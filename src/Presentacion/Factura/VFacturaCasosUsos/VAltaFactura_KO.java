package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAltaFactura_KO extends JFrame implements IGUI {
	public void update(Object data) {
		int err = (int)data;
		String msg = "Error al crear factura";
		
		if(err == Events.ERROR_FACTURA_MESA_INACTIVA) msg = "Mesa inexistente";
		else if(err == Events.ERROR_FACTURA_DISPONIBILIDAD_MESA) msg = "Mesa no disponible";
		else if (err == Events.ERROR_FACTURA_POR_EMPLEADO) msg = "Empleado inexistente";
		
		JOptionPane.showMessageDialog(this, msg);
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}

