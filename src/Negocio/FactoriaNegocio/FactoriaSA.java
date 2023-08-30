
package Negocio.FactoriaNegocio;

import Negocio.Cliente.SACliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Factura.SAFactura;
import Negocio.Habitacion.SAHabitacion;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Menu.SAMenu;
import Negocio.Mesa.SAMesa;
import Negocio.Producto.SAProducto;
import Negocio.Recepcionista.SARecepcionista;
import Negocio.Reserva.SAReserva;
import Negocio.Servicio.SAServicio;
import Negocio.Turno.SATurno;

public abstract class FactoriaSA {

	private static FactoriaSA instance;

	public static FactoriaSA getInstance() {
		if (instance == null) {
			instance = new FactoriaSAImp();
		}
		return instance;
	}

	public SAIngrediente getSAIngrediente() {

		return null;
	}

	public abstract SAEmpleado generarSAEmpleado();

	public abstract SAFactura generarSAFactura();

	public abstract SAIngrediente generarSAIngrediente();


	public abstract SACliente generarSACliente();

	public abstract SATurno generarSATurno();

	public abstract SARecepcionista generarSARecepcionista();

	public abstract SAHabitacion generarSAHabitacion();
	
	public abstract SAServicio generarSAServicio();

	public abstract SAReserva generarSAReserva();

	public abstract SAMenu generarSAMenu();

	public abstract SAMesa generarSAMesa();

	public abstract SAProducto generarSAProducto();

}