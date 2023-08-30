package Presentacion.Factura.VFacturaCasosUsos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VDevolverMenusFactura_KO extends JFrame implements IGUI {
	public void update(Object data) {
		int err = (int)data;
		String msg = "Error al eliminar menu";
		if(err == Events.ERROR_FACTURA_DATOS) msg = "Datos introducidos incorrectos";
		else if(err == Events.ERROR_FACTURA_ID_INEXISTENTE) msg = "Factura inexistente o abierta";
		else if(err == Events.ERROR_FACTURA_MENU_INEXISTENTE) msg = "Menu inexistente";
		else if(err == Events.ERROR_FACTURA_LINEA_INEXISTENTE) msg = "Ese menu no se encuentra en la factura";

		
		JOptionPane.showMessageDialog(this, msg);
		Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);
	}
}