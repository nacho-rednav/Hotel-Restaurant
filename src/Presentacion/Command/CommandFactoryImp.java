package Presentacion.Command;

import Presentacion.Command.Cliente.CalcularPrecioTurnosClienteCommand;
import Presentacion.Command.Cliente.CrearClienteCommand;
import Presentacion.Command.Cliente.EditarCliente1Command;
import Presentacion.Command.Cliente.EditarCliente2Command;
import Presentacion.Command.Cliente.EliminarClienteCommand;
import Presentacion.Command.Cliente.MostrarClienteCommand;
import Presentacion.Command.Cliente.MostrarTodosClienteCommand;
import Presentacion.Command.Cliente.MostrarTurnosClienteCommand;
import Presentacion.Command.Empleado.CrearEmpleadoCommand;
import Presentacion.Command.Empleado.EditarEmpleado1Command;
import Presentacion.Command.Empleado.EditarEmpleado2Command;
import Presentacion.Command.Empleado.EliminarEmpleadoCommand;
import Presentacion.Command.Empleado.MostrarEmpleadosRangoCommand;
import Presentacion.Command.Empleado.MostrarTodosEmpleadoCommand;
import Presentacion.Command.Empleado.MostrarUnoEmpleadoCommand;
import Presentacion.Command.Factura.*;
import Presentacion.Command.Habitacion.CrearHabitacionCommand;
import Presentacion.Command.Habitacion.EditarHabitacion1Command;
import Presentacion.Command.Habitacion.EditarHabitacion2Command;
import Presentacion.Command.Habitacion.EliminarHabitacionCommand;
import Presentacion.Command.Habitacion.MostrarHabitacionCommand;
import Presentacion.Command.Habitacion.MostrarHabitacionNumCommand;
import Presentacion.Command.Habitacion.MostrarTodasHabitacionCommand;
import Presentacion.Command.Ingrediente.CrearIngredienteCommand;
import Presentacion.Command.Ingrediente.EditarIngrediente1Command;
import Presentacion.Command.Ingrediente.EditarIngrediente2Command;
import Presentacion.Command.Ingrediente.EliminarIngredienteCommand;
import Presentacion.Command.Ingrediente.MostrarTodosIngredienteCommand;
import Presentacion.Command.Ingrediente.MostrarUnoIngredienteCommand;
import Presentacion.Command.Ingrediente.MostrarUnoPorNombreIngredienteCommand;
import Presentacion.Command.Mesa.CrearMesaCommand;
import Presentacion.Command.Mesa.EditarMesa1Command;
import Presentacion.Command.Mesa.EditarMesa2Command;
import Presentacion.Command.Mesa.EliminarMesaCommand;
import Presentacion.Command.Mesa.MostrarTodosMesaCommand;
import Presentacion.Command.Mesa.MostrarUnoMesaCommand;
import Presentacion.Command.Producto.CrearProductoCommand;
import Presentacion.Command.Producto.DesvincularIngredienteProducto;
import Presentacion.Command.Producto.EditarProducto2Command;
import Presentacion.Command.Producto.EditarProductoCommand;
import Presentacion.Command.Producto.EliminarProductoCommand;
import Presentacion.Command.Producto.MostrarTodosProductosCommand;
import Presentacion.Command.Producto.MostrarUnoPorNombreProductoCommand;
import Presentacion.Command.Producto.MostrarUnoProductoCommand;
import Presentacion.Command.Producto.VincularIngredienteProducto;
import Presentacion.Command.Recepcionista.CrearRecepcionistaCommand;
import Presentacion.Command.Recepcionista.EditarRecepcionista1Command;
import Presentacion.Command.Recepcionista.EditarRecepcionista2Command;
import Presentacion.Command.Recepcionista.EliminarRecepcionistaCommand;
import Presentacion.Command.Recepcionista.MostrarPorIdRecepcionistaCommand;
import Presentacion.Command.Recepcionista.MostrarPorNombreRecepcionistaCommand;
import Presentacion.Command.Recepcionista.MostrarTodosRecepcionistaCommand;
import Presentacion.Command.Reserva.CompraServiciosCommand1;
import Presentacion.Command.Reserva.CompraServiciosCommand2;
import Presentacion.Command.Reserva.CrearReservaCommand;
import Presentacion.Command.Reserva.DesvincularHabitacionCommand;
import Presentacion.Command.Reserva.DevolucionServicioCommand1;
import Presentacion.Command.Reserva.DevolucionServicioCommand2;
import Presentacion.Command.Reserva.EditarReserva1Command;
import Presentacion.Command.Reserva.EditarReserva2Command;
import Presentacion.Command.Reserva.EliminarReservaCommand;
import Presentacion.Command.Reserva.MostrarReservaCommand;
import Presentacion.Command.Reserva.MostrarTodosReservaCommand;
import Presentacion.Command.Servicio.CrearServioCommand;
import Presentacion.Command.Servicio.EditarServicioComand;
import Presentacion.Command.Servicio.EditarServicioComand2;
import Presentacion.Command.Servicio.EliminarServicioCommand;
import Presentacion.Command.Servicio.MostrarPorIdServicioCommand;
import Presentacion.Command.Servicio.MostrarPorTipoServicioCommand;
import Presentacion.Command.Servicio.MostrarReservasServicioCommand;
import Presentacion.Command.Servicio.MostrarTodosServicioCommand;
import Presentacion.Command.Turno.AnadirClienteATurnoCommand;
import Presentacion.Command.Turno.CrearTurnoCommand;
import Presentacion.Command.Turno.EditarTurno1Command;
import Presentacion.Command.Turno.EditarTurno2Command;
import Presentacion.Command.Turno.EliminarClienteATurnoCommand;
import Presentacion.Command.Turno.EliminarTurnoCommand;
import Presentacion.Command.Turno.MostrarTodosTurnoCommand;
import Presentacion.Command.Turno.MostrarTurnoClientesCommand;
import Presentacion.Command.Turno.MostrarTurnoCommand;
import Presentacion.Command.Menu.*;
import Presentacion.Controller.Events;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command getCommand(Integer event) {

		switch (event) {
		// RESTAURANTE //
		// INGREDIENTE
		case Events.ALTA_INGREDIENTE:
			return new CrearIngredienteCommand();
		case Events.BAJA_INGREDIENTE:
			return new EliminarIngredienteCommand();
		case Events.MODIFICAR_INGREDIENTE:
			return new EditarIngrediente1Command();
		case Events.MODIFICAR_INGREDIENTE2:
			return new EditarIngrediente2Command();
		case Events.MOSTRAR_INGREDIENTE_TODOS:
			return new MostrarTodosIngredienteCommand();
		case Events.MOSTRAR_UN_INGREDIENTE:
			return new MostrarUnoIngredienteCommand();
		case Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE:
			return new MostrarUnoPorNombreIngredienteCommand();

		// MESA
		case Events.ALTA_MESA:
			return new CrearMesaCommand();
		case Events.BAJA_MESA:
			return new EliminarMesaCommand();
		case Events.MOSTRAR_MESA_TODAS:
			return new MostrarTodosMesaCommand();
		case Events.MOSTRAR_UNA_MESA:
			return new MostrarUnoMesaCommand();
		case Events.MODIFICAR_MESA:
			return new EditarMesa1Command();
		case Events.MODIFICAR_MESA2:
			return new EditarMesa2Command();

		// EMPLEADO
		case Events.ALTA_EMPLEADO:
			return new CrearEmpleadoCommand();
		case Events.BAJA_EMPLEADO:
			return new EliminarEmpleadoCommand();
		case Events.MOSTRAR_EMPLEADO_TODOS:
			return new MostrarTodosEmpleadoCommand();
		case Events.MOSTRAR_UN_EMPLEADO:
			return new MostrarUnoEmpleadoCommand();
		case Events.MODIFICAR_EMPLEADO:
			return new EditarEmpleado1Command();
		case Events.MODIFICAR_EMPLEADO2:
			return new EditarEmpleado2Command();
		case Events.MOSTRAR_EMPLEADO_RANGO:
			return new MostrarEmpleadosRangoCommand();

		// FACTURA
		case Events.ALTA_FACTURA:
			return new AbrirFacturaCommand();
		case Events.CERRAR_FACTURA:
			return new CerrarFacturaCommand();
		case Events.MOSTRAR_UNA_FACTURA:
			return new MostrarFacturaCommand();
		case Events.MODIFICAR_FACTURA:
			return new EditarFactura1Command();
		case Events.MODIFICAR_FACTURA2:
			return new EditarFactura2Command();
		case Events.MOSTRAR_FACTURA_TODAS:
			return new MostrarFacturaTodasCommand();
		case Events.MOSTRAR_FACTURA_POR_EMPLEADO:
			return new MostrarFacturaPorEmpleadoCommand();
		case Events.MOSTRAR_FACTURA_POR_MESA:
			return new MostrarFacturaPorMesaCommand();
		case Events.ANIADIR_MENUS_FACTURA:
			return new AniadirMenusFacturaCommand();
		case Events.ELEGIR_MENUS_FACTURA:
			return new ElegirMenusAniadirMenusFacturaCommand();
		case Events.DEVOLVER_MENU:
			return new DevolverMenuCommand();
		case Events.ELIMINAR_MENUS_FACTURA:
			return new EliminarMenusFacturaCommand();

		// MENU
		case Events.MODIFICAR_MENU:
			return new EditarMenuCommand();
		case Events.MODIFICAR_MENU2:
			return new EditarMenuCommand2();
		case Events.ALTA_MENU:
			return new CrearMenuCommand();
		case Events.BAJA_MENU:
			return new EliminarMenuCommand();
		case Events.MOSTRAR_UN_MENU:
			return new MostrarUnoMenuCommand();
		case Events.MOSTRAR_MENU_TODOS:
			return new MostrarTodosMenusCommand();
		case Events.DESVINCULAR_PRODUCTO:
			return new DesvincularProductoMenu();
		case Events.VINCULAR_PRODUCTO:
			return new VincularProductoMenuCommand();
		case Events.MOSTRAR_MENU_RANGO:
			return new MostrarMenusRangoCommand();

		// PRODUCTO
		case Events.ALTA_PRODUCTO:
			return new CrearProductoCommand();
		case Events.BAJA_PRODUCTO:
			return new EliminarProductoCommand();
		case Events.MOSTRAR_UN_PRODUCTO:
			return new MostrarUnoProductoCommand();
		case Events.MOSTRAR_PRODUCTO_TODOS:
			return new MostrarTodosProductosCommand();
		case Events.MODIFICAR_PRODUCTO:
			return new EditarProductoCommand();
		case Events.MODIFICAR_PRODUCTO2:
			return new EditarProducto2Command();
		case Events.VINCULAR_INGREDIENTE:
			return new VincularIngredienteProducto();
		case Events.DESVINCULAR_INGREDIENTE:
			return new DesvincularIngredienteProducto();
		case Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE:
			return new MostrarUnoPorNombreProductoCommand();

		// HOTEL //
		// TURNO
		case Events.CREAR_TURNO:
			return new CrearTurnoCommand();
		case Events.ELIMINAR_TURNO:
			return new EliminarTurnoCommand();
		case Events.MOSTRAR_TURNO_ID:
			return new MostrarTurnoCommand();
		case Events.MOSTRAR_TODOS_TURNO:
			return new MostrarTodosTurnoCommand();
		case Events.MODIFICAR_TURNO1:
			return new EditarTurno1Command();
		case Events.MODIFICAR_TURNO2:
			return new EditarTurno2Command();
		case Events.VINCULAR_TURNO_CLIENTE:
			return new AnadirClienteATurnoCommand();
		case Events.DESVINCULAR_TURNO_CLIENTE:
			return new EliminarClienteATurnoCommand();
		case Events.MOSTRAR_TURNO_CLIENTES:
			return new MostrarTurnoClientesCommand();
			
		// CLIENTE
		case Events.ALTA_CLIENTE:
			return new CrearClienteCommand();
		case Events.BAJA_CLIENTE:
			return new EliminarClienteCommand();
		case Events.MOSTRAR_CLIENTE_ID:
			return new MostrarClienteCommand();
		case Events.MOSTRAR_TODOS_CLIENTE:
			return new MostrarTodosClienteCommand();
		case Events.MODIFICAR_CLIENTE:
			return new EditarCliente1Command();
		case Events.MODIFICAR_CLIENTE2:
			return new EditarCliente2Command();
		case Events.CALCULAR_PRECIO_TURNOS:
			return new CalcularPrecioTurnosClienteCommand();
		case Events.MOSTRAR_TURNOS_DE_CLIENTE:
			return new MostrarTurnosClienteCommand();
		
		//HABITACION
		case Events.CREAR_HABITACION:
			return new CrearHabitacionCommand();
		case Events.MODIFICAR_HABITACION:
			return new EditarHabitacion1Command();
		case Events.MODIFICAR_HABITACION2:
			return new EditarHabitacion2Command();
		case Events.BAJA_HABITACION:
			return new EliminarHabitacionCommand();
		case Events.MOSTRAR_HABITACION_ID:
			return new MostrarHabitacionCommand();
		case Events.MOSTRAR_HABITACION_NUMERO:
			return new MostrarHabitacionNumCommand();
		case Events.MOSTRAR_HABITACION_TODOS:
			return new MostrarTodasHabitacionCommand();
			
		//RESERVA
		case Events.COMPRA_SERVICIO_RESERVA:
			return new CompraServiciosCommand1();
		case Events.COMPRA_SERVICIO_RESERVA2:
			return new CompraServiciosCommand2();
		case Events.CREAR_RESERVA:
			return new CrearReservaCommand();
		case Events.DESVINCULAR_HABITACION_RESERVA:
			return new DesvincularHabitacionCommand();
		case Events.DEVOLUCION_SERVICIO_RESERVA:
			return new DevolucionServicioCommand1();
		case Events.DEVOLUCION_SERVICIO_RESERVA2:
			return new DevolucionServicioCommand2();
		case Events.MODIFICAR_RESERVA:
			return new EditarReserva1Command();
		case Events.MODIFICAR_RESERVA2:
			return new EditarReserva2Command();
		case Events.BAJA_RESERVA:
			return new EliminarReservaCommand();
		case Events.MOSTRAR_RESERVA_ID:
			return new MostrarReservaCommand();
		case Events.MOSTRAR_RESERVA_TODOS:
			return new MostrarTodosReservaCommand();
			
		// RECEPCIONISTA
		case Events.ALTA_RECEPCIONISTA:
			return new CrearRecepcionistaCommand();
		case Events.MODIFICAR_RECEPCIONISTA:
			return new EditarRecepcionista1Command();
		case Events.MODIFICAR_RECEPCIONISTA2:
			return new EditarRecepcionista2Command();
		case Events.BAJA_RECEPCIONISTA:
			return new EliminarRecepcionistaCommand();
		case Events.MOSTRAR_RECEPCIONISTA_ID:
			return new MostrarPorIdRecepcionistaCommand();
		case Events.MOSTRAR_RECEPCIONISTA_NOMBRE:
			return new MostrarPorNombreRecepcionistaCommand();
		case Events.MOSTRAR_TODOS_RECEPCIONISTA:
			return new MostrarTodosRecepcionistaCommand();
		
		// SERVICIO
		case Events.ALTA_SERVICIO:
			return new CrearServioCommand();
		case Events.MODIFICAR_SERVICIO:
			return new EditarServicioComand();
		case Events.MODIFICAR_SERVICIO2:
			return new EditarServicioComand2();
		case Events.BAJA_SERVICIO:
			return new EliminarServicioCommand();
		case Events.MOSTRAR_SERVICIO_ID:
			return new MostrarPorIdServicioCommand();
		case Events.MOSTRAR_SERVICIO_TIPO:
			return new MostrarPorTipoServicioCommand();
		case Events.MOSTRAR_SERVICIO_TODOS:
			return new MostrarTodosServicioCommand();
		case Events.MOSTRAR_SERVICIO_RESERVAS:
			return new MostrarReservasServicioCommand();

		default:
			return null;
		}

	}
}