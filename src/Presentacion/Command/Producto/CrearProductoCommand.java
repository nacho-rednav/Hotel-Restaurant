package Presentacion.Command.Producto;

import javax.swing.JOptionPane;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.SAProducto;
import Negocio.Producto.TProducto;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;
import Presentacion.Empleado.VEmpleado;
import Presentacion.Empleado.VCasosDeUso.VAltaEmpleado;
import Presentacion.Empleado.VCasosDeUso.VBajaEmpleado;
import Presentacion.Empleado.VCasosDeUso.VModificarEmpleado;
import Presentacion.Empleado.VCasosDeUso.VModificarEmpleado2;
import Presentacion.Empleado.VCasosDeUso.VMostrarEmpleado;
import Presentacion.Empleado.VCasosDeUso.VMostrarEmpleado_OK;
import Presentacion.Empleado.VCasosDeUso.VMostrarTodosEmpleados;
import Presentacion.Launcher.VDialogo;

public class CrearProductoCommand implements Command {
	
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAProducto saProd = fsa.generarSAProducto();
		int res = saProd.crear((TProducto) data);
		
		Context resContext = null;
		String mensaje = null;
		if (res == 0) {
			mensaje = "Producto creado con id" + res;
			resContext = new Context(Events.ALTA_PRODUCTO_OK, mensaje);
		}
		else {
			mensaje = "Producto no se ha podido crear";
			resContext = new Context(Events.ALTA_PRODUCTO_KO, mensaje);
		}
		
		return resContext;
	}
}