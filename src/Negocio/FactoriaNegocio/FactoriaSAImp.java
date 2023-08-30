
package Negocio.FactoriaNegocio;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoImp;
import Negocio.Factura.SAFactura;
import Negocio.Factura.SAFacturaImp;
import Negocio.Habitacion.SAHabitacion;
import Negocio.Habitacion.SAHabitacionImp;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Ingrediente.SAIngredienteImp;
import Negocio.Menu.SAMenu;
import Negocio.Menu.SAMenuImp;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.SAMesaImp;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoImp;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Recepcionista.SARecepcionistaImp;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.SAReservaImp;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.SAServicioImp;
import Negocio.Turno.SATurno;
import Negocio.Turno.SATurnoImp;

public class FactoriaSAImp extends FactoriaSA {

	public SAEmpleado generarSAEmpleado() {
		return new SAEmpleadoImp();
	}

	public SAFactura generarSAFactura() {
		return new SAFacturaImp();
	}

	public SAIngrediente generarSAIngrediente() {
		return new SAIngredienteImp();
	}

	public SAMenu generarSAMenu() {
		return new SAMenuImp();
	}

	public SAMesa generarSAMesa() {
		return new SAMesaImp();
	}

	public SAProducto generarSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SACliente generarSACliente() {
		return new SAClienteImp();
	}

	@Override
	public SATurno generarSATurno() {
		return new SATurnoImp();
	}

	@Override
	public SARecepcionista generarSARecepcionista() {
		return new SARecepcionistaImp();
	}

	@Override
	public SAHabitacion generarSAHabitacion() {
		return new SAHabitacionImp();
	}

	@Override
	public SAServicio generarSAServicio() {
		return new SAServicioImp();
	}

	@Override
	public SAReserva generarSAReserva() {
		return new SAReservaImp();
	}

}