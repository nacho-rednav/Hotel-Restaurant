package Presentacion.FactoriaVistas;

import javax.swing.JOptionPane;

import Presentacion.Cliente.VCalcularPrecioTurnos;
import Presentacion.Cliente.VCalcularPrecioTurnos_KO;
import Presentacion.Cliente.VCalcularPrecioTurnos_OK;
import Presentacion.Cliente.VCliente;
import Presentacion.Cliente.VCrearCliente;
import Presentacion.Cliente.VCrearCliente_KO;
import Presentacion.Cliente.VCrearCliente_OK;
import Presentacion.Cliente.VEditarCliente;
import Presentacion.Cliente.VEditarCliente2;
import Presentacion.Cliente.VEditarCliente_KO;
import Presentacion.Cliente.VEditarCliente_OK;
import Presentacion.Cliente.VEliminarCliente;
import Presentacion.Cliente.VEliminarCliente_KO;
import Presentacion.Cliente.VEliminarCliente_OK;
import Presentacion.Cliente.VMostrarCliente;
import Presentacion.Cliente.VMostrarCliente_KO;
import Presentacion.Cliente.VMostrarCliente_OK;
import Presentacion.Cliente.VMostrarTodosCliente;
import Presentacion.Cliente.VMostrarTurnosDeCliente;
import Presentacion.Cliente.VMostrarTurnosDeCliente_KO;
import Presentacion.Cliente.VMostrarTurnosDeCliente_OK;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Empleado.VEmpleado;
import Presentacion.Empleado.VCasosDeUso.VAltaEmpleado;
import Presentacion.Empleado.VCasosDeUso.VBajaEmpleado;
import Presentacion.Empleado.VCasosDeUso.VModificarEmpleado;
import Presentacion.Empleado.VCasosDeUso.VModificarEmpleado2;
import Presentacion.Empleado.VCasosDeUso.VMostrarEmpleado;
import Presentacion.Empleado.VCasosDeUso.VMostrarEmpleadoRango;
import Presentacion.Empleado.VCasosDeUso.VMostrarEmpleado_OK;
import Presentacion.Empleado.VCasosDeUso.VMostrarTodosEmpleados;
import Presentacion.Factura.VFactura;
import Presentacion.Factura.VFacturaCasosUsos.VAltaFactura;
import Presentacion.Factura.VFacturaCasosUsos.VAltaFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VAltaFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VAnyadirMenusFactura;
import Presentacion.Factura.VFacturaCasosUsos.VAnyadirMenusFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VAnyadirMenusFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VCerrarFactura;
import Presentacion.Factura.VFacturaCasosUsos.VCerrarFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VCerrarFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VDevolverMenusFactura;
import Presentacion.Factura.VFacturaCasosUsos.VDevolverMenusFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VDevolverMenusFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VEditarFactura;
import Presentacion.Factura.VFacturaCasosUsos.VEditarFactura2;
import Presentacion.Factura.VFacturaCasosUsos.VEditarFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VEditarFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VEliminarMenusFactura;
import Presentacion.Factura.VFacturaCasosUsos.VEliminarMenusFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VEliminarMenusFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarFactura;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarFactura_KO;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarFactura_OK;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarPorEmpleado;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarPorMesa;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarPor_KO;
import Presentacion.Factura.VFacturaCasosUsos.VMostrarTodasFacturas;
import Presentacion.Habitacion.VCrearHabitacion;
import Presentacion.Habitacion.VCrearHabitacion_KO;
import Presentacion.Habitacion.VCrearHabitacion_OK;
import Presentacion.Habitacion.VEditarHabitacion;
import Presentacion.Habitacion.VEditarHabitacion2;
import Presentacion.Habitacion.VEditarHabitacion_KO;
import Presentacion.Habitacion.VEditarHabitacion_OK;
import Presentacion.Habitacion.VEliminarHabitacion;
import Presentacion.Habitacion.VEliminarHabitacion_KO;
import Presentacion.Habitacion.VEliminarHabitacion_OK;
import Presentacion.Habitacion.VHabitacion;
import Presentacion.Habitacion.VMostrarHabitacion_KO;
import Presentacion.Habitacion.VMostrarHabitacion_OK;
import Presentacion.Habitacion.VMostrarPorIdHabitacion;
import Presentacion.Habitacion.VMostrarPorNumeroHabitacion;
import Presentacion.Habitacion.VMostrarTodasHabitaciones;
import Presentacion.Ingrediente.VIngrediente;
import Presentacion.Ingrediente.VIngredienteCasosUso.VAltaIngrediente;
import Presentacion.Ingrediente.VIngredienteCasosUso.VBajaIngrediente;
import Presentacion.Ingrediente.VIngredienteCasosUso.VModificarIngrediente;
import Presentacion.Ingrediente.VIngredienteCasosUso.VModificarIngrediente2;
import Presentacion.Ingrediente.VIngredienteCasosUso.VMostrarIngrediente;
import Presentacion.Ingrediente.VIngredienteCasosUso.VMostrarIngredientePorNombre;
import Presentacion.Ingrediente.VIngredienteCasosUso.VMostrarIngrediente_OK;
import Presentacion.Ingrediente.VIngredienteCasosUso.VMostrarTodosIngredientes;
import Presentacion.Launcher.VComidas;
import Presentacion.Launcher.VDialogo;
import Presentacion.Launcher.VHotel;
import Presentacion.Launcher.VPrincipal;
import Presentacion.Launcher.VRestaurante;
import Presentacion.Menu.VMenu;
import Presentacion.Menu.VMenuCasosDeUso.VAltaMenu;
import Presentacion.Menu.VMenuCasosDeUso.VAltaMenu_KO;
import Presentacion.Menu.VMenuCasosDeUso.VAltaMenu_OK;
import Presentacion.Menu.VMenuCasosDeUso.VAniadirProducto;
import Presentacion.Menu.VMenuCasosDeUso.VBajaMenu;
import Presentacion.Menu.VMenuCasosDeUso.VBajaMenu_KO;
import Presentacion.Menu.VMenuCasosDeUso.VBajaMenu_OK;
import Presentacion.Menu.VMenuCasosDeUso.VModificarMenu;
import Presentacion.Menu.VMenuCasosDeUso.VModificarMenu2;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenu;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenuRango;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenu_KO;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenu_OK;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarTodosMenu;
import Presentacion.Menu.VMenuCasosDeUso.VQuitarProducto;
import Presentacion.Mesa.VMesa;
import Presentacion.Mesa.VMesaCasosUso.VAltaMesa;
import Presentacion.Mesa.VMesaCasosUso.VAltaMesa_KO;
import Presentacion.Mesa.VMesaCasosUso.VAltaMesa_OK;
import Presentacion.Mesa.VMesaCasosUso.VBajaMesa;
import Presentacion.Mesa.VMesaCasosUso.VBajaMesa_KO;
import Presentacion.Mesa.VMesaCasosUso.VBajaMesa_OK;
import Presentacion.Mesa.VMesaCasosUso.VModificarMesa;
import Presentacion.Mesa.VMesaCasosUso.VModificarMesa2;
import Presentacion.Mesa.VMesaCasosUso.VModificarMesa_KO;
import Presentacion.Mesa.VMesaCasosUso.VModificarMesa_OK;
import Presentacion.Mesa.VMesaCasosUso.VMostrarMesa;
import Presentacion.Mesa.VMesaCasosUso.VMostrarMesa_KO;
import Presentacion.Mesa.VMesaCasosUso.VMostrarMesa_OK;
import Presentacion.Mesa.VMesaCasosUso.VMostrarTodasMesas;
import Presentacion.Producto.VProducto;
import Presentacion.Producto.VProductoCasosDeUso.VAltaProducto;
import Presentacion.Producto.VProductoCasosDeUso.VAniadirIngrediente;
import Presentacion.Producto.VProductoCasosDeUso.VBajaProducto;
import Presentacion.Producto.VProductoCasosDeUso.VModificarProducto;
import Presentacion.Producto.VProductoCasosDeUso.VModificarProducto2;
import Presentacion.Producto.VProductoCasosDeUso.VMostrarProducto;
import Presentacion.Producto.VProductoCasosDeUso.VMostrarProductoPorNombre;
import Presentacion.Producto.VProductoCasosDeUso.VMostrarProducto_OK;
import Presentacion.Producto.VProductoCasosDeUso.VMostrarTodosProductos;
import Presentacion.Producto.VProductoCasosDeUso.VQuitarIngrediente;
import Presentacion.Recepcionista.VRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VCrearRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VCrearRecepcionista_KO;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VCrearRecepcionista_OK;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEditarRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEditarRecepcionista2;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEditarRecepcionista_KO;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEditarRecepcionista_OK;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEliminarRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VEliminarRecepcionista_OK;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VMostrarPorIdRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VMostrarPorNombreRecepcionista;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VMostrarRecepcionista_KO;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VMostrarRecepcionista_OK;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VMostrarTodosRecepcionista;
import Presentacion.Reserva.VCompraServicio;
import Presentacion.Reserva.VCompraServicio2;
import Presentacion.Reserva.VCompraServicio_KO;
import Presentacion.Reserva.VCompraServicio_OK;
import Presentacion.Reserva.VCrearReserva;
import Presentacion.Reserva.VCrearReserva_KO;
import Presentacion.Reserva.VCrearReserva_OK;
import Presentacion.Reserva.VDesvincularHabitacionReserva;
import Presentacion.Reserva.VDesvincularHabitacionReserva_KO;
import Presentacion.Reserva.VDesvincularHabitacionReserva_OK;
import Presentacion.Reserva.VDevolucionServicio;
import Presentacion.Reserva.VDevolucionServicio2;
import Presentacion.Reserva.VDevolucionServicio_KO;
import Presentacion.Reserva.VDevolucionServicio_OK;
import Presentacion.Reserva.VEditarReserva;
import Presentacion.Reserva.VEditarReserva2;
import Presentacion.Reserva.VEditarReserva_KO;
import Presentacion.Reserva.VEditarReserva_OK;
import Presentacion.Reserva.VEliminarReserva;
import Presentacion.Reserva.VEliminarReserva_KO;
import Presentacion.Reserva.VEliminarReserva_OK;
import Presentacion.Reserva.VMostrarReserva;
import Presentacion.Reserva.VMostrarReserva_KO;
import Presentacion.Reserva.VMostrarReserva_OK;
import Presentacion.Reserva.VMostrarTodasReserva;
import Presentacion.Reserva.VReserva;
import Presentacion.Servicio.VServicio;
import Presentacion.Servicio.VServicioCasosUso.VCrearServicio;
import Presentacion.Servicio.VServicioCasosUso.VCrearServicio_KO;
import Presentacion.Servicio.VServicioCasosUso.VCrearServicio_OK;
import Presentacion.Servicio.VServicioCasosUso.VEditarServicio;
import Presentacion.Servicio.VServicioCasosUso.VEditarServicio2;
import Presentacion.Servicio.VServicioCasosUso.VEditarServicio_KO;
import Presentacion.Servicio.VServicioCasosUso.VEditarServicio_OK;
import Presentacion.Servicio.VServicioCasosUso.VEliminarServicio;
import Presentacion.Servicio.VServicioCasosUso.VEliminarServicio_KO;
import Presentacion.Servicio.VServicioCasosUso.VEliminarServicio_OK;
import Presentacion.Servicio.VServicioCasosUso.VMostrarReservasPorServicio;
import Presentacion.Servicio.VServicioCasosUso.VMostrarReservasPorServicio_OK;
import Presentacion.Servicio.VServicioCasosUso.VMostrarServicioPorId;
import Presentacion.Servicio.VServicioCasosUso.VMostrarServicioPorTipo;
import Presentacion.Servicio.VServicioCasosUso.VMostrarServicio_KO;
import Presentacion.Servicio.VServicioCasosUso.VMostrarServicio_OK;
import Presentacion.Servicio.VServicioCasosUso.VMostrarTodosServicio;
import Presentacion.Turno.VAnyadirClienteATurno;
import Presentacion.Turno.VAnyadirClienteATurno_KO;
import Presentacion.Turno.VAnyadirClienteATurno_OK;
import Presentacion.Turno.VCrearTurno;
import Presentacion.Turno.VCrearTurno_KO;
import Presentacion.Turno.VCrearTurno_OK;
import Presentacion.Turno.VEditarTurno;
import Presentacion.Turno.VEditarTurno2;
import Presentacion.Turno.VEditarTurno_KO;
import Presentacion.Turno.VEditarTurno_OK;
import Presentacion.Turno.VElimiarTurnoKO;
import Presentacion.Turno.VEliminarClienteATurno;
import Presentacion.Turno.VEliminarClienteATurno_KO;
import Presentacion.Turno.VEliminarClienteATurno_OK;
import Presentacion.Turno.VEliminarTurno;
import Presentacion.Turno.VEliminarTurnoOK;
import Presentacion.Turno.VMostrarClientesTurno;
import Presentacion.Turno.VMostrarClientesTurno_KO;
import Presentacion.Turno.VMostrarClientesTurno_OK;
import Presentacion.Turno.VMostrarTodosTurno;
import Presentacion.Turno.VMostrarTurno;
import Presentacion.Turno.VMostrarTurno_KO;
import Presentacion.Turno.VMostrarTurno_OK;
import Presentacion.Turno.VTurno;

public class FactoriaVistasImp extends FactoriaVistas {

	public IGUI createView(Context context) {
		switch (context.getEvento()) {
		// Principal
		case Events.ABRIR_VPRINCIPAL:
			return new VPrincipal();
		case Events.ABRIR_VHOTEL:
			return new VHotel();
		case Events.ABRIR_VRESTAURANTE:
			return new VRestaurante();

		// COMIDA
		case Events.ABRIR_VCOMIDA:
			return new VComidas();

		// Empleado
		case Events.ABRIR_VEMPLEADO:
			return new VEmpleado();
		case Events.ABRIR_VMOSTRAR_UN_EMPLEADO:
			return new VMostrarEmpleado();
		case Events.MOSTRAR_UN_EMPLEADO_OK:
			return new VMostrarEmpleado_OK();
		case Events.MOSTRAR_UN_EMPLEADO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_EMPLEADO_TODOS:
			return new VMostrarTodosEmpleados();
		case Events.ABRIR_VALTA_EMPLEADO:
			return new VAltaEmpleado();
		case Events.ALTA_EMPLEADO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.ALTA_EMPLEADO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VBAJA_EMPLEADO:
			return new VBajaEmpleado();
		case Events.BAJA_EMPLEADO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.BAJA_EMPLEADO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMODIFICAR_EMPLEADO:
			return new VModificarEmpleado();
		case Events.ABRIR_VMODIFICAR_EMPLEADO2:
			return new VModificarEmpleado2();
		case Events.MODIFICAR_EMPLEADO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.MODIFICAR_EMPLEADO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_EMPLEADO_RANGO:
			return new VMostrarEmpleadoRango();

		// Mesa
		case Events.ABRIR_VMESA:
			return new VMesa();

		case Events.ABRIR_VALTA_MESA:
			return new VAltaMesa();
		case Events.ALTA_MESA_OK:
			return new VAltaMesa_OK();
		case Events.ALTA_MESA_KO:
			return new VAltaMesa_KO();

		case Events.ABRIR_VBAJA_MESA:
			return new VBajaMesa();
		case Events.BAJA_MESA_OK:
			return new VBajaMesa_OK();
		case Events.BAJA_MESA_KO:
			return new VBajaMesa_KO();

		case Events.ABRIR_VMODIFICAR_MESA:
			return new VModificarMesa();
		case Events.ABRIR_VMODIFICAR_MESA2:
			return new VModificarMesa2();
		case Events.MODIFICAR_MESA_OK:
			return new VModificarMesa_OK();
		case Events.MODIFICAR_MESA_KO:
			return new VModificarMesa_KO();

		case Events.ABRIR_VMOSTRAR_UNA_MESA:
			return new VMostrarMesa();
		case Events.MOSTRAR_UNA_MESA_OK:
			return new VMostrarMesa_OK();
		case Events.MOSTRAR_UNA_MESA_KO:
			return new VMostrarMesa_KO();

		case Events.ABRIR_VMOSTRAR_MESA_TODAS:
			return new VMostrarTodasMesas();

		// Factura
		case Events.ABRIR_VFACTURA:
			return new VFactura();

		case Events.ABRIR_VALTA_FACTURA:
			return new VAltaFactura();
		case Events.ALTA_FACTURA_OK:
			return new VAltaFactura_OK();
		case Events.ALTA_FACTURA_KO:
			return new VAltaFactura_KO();

		case Events.ABRIR_VCERRAR_FACTURA:
			return new VCerrarFactura();
		case Events.CERRAR_FACTURA_OK:
			return new VCerrarFactura_OK();
		case Events.CERRAR_FACTURA_KO:
			return new VCerrarFactura_KO();

		case Events.ABRIR_VMOSTRAR_UNA_FACTURA:
			return new VMostrarFactura();
		case Events.MOSTRAR_UNA_FACTURA_OK:
			return new VMostrarFactura_OK();
		case Events.MOSTRAR_UNA_FACTURA_KO:
			return new VMostrarFactura_KO();

		case Events.ABRIR_VMODIFICAR_FACTURA:
			return new VEditarFactura();
		case Events.ABRIR_VMODIFICAR_FACTURA2:
			return new VEditarFactura2();
		case Events.MODIFICAR_FACTURA_OK:
			return new VEditarFactura_OK();
		case Events.MODIFICAR_FACTURA_KO:
			return new VEditarFactura_KO();

		case Events.ABRIR_VMOSTRAR_FACTURA_TODAS:
			return new VMostrarTodasFacturas();

		case Events.ABRIR_VMOSTRAR_POR_EMPLEADO:
			return new VMostrarPorEmpleado();
		case Events.ABRIR_VMOSTRAR_POR_MESA:
			return new VMostrarPorMesa();
		case Events.MOSTRAR_FACTURA_POR_KO:
			return new VMostrarPor_KO();

		case Events.ABRIR_VANIADIR_MENUS_FACTURA:
			return new VAnyadirMenusFactura();
		case Events.ANIADIR_MENUS_FACTURA_OK:
			return new VAnyadirMenusFactura_OK();
		case Events.ANIADIR_MENUS_FACTURA_KO:
			return new VAnyadirMenusFactura_KO();

		case Events.ABRIR_VELIMINAR_MENUS_FACTURA:
			return new VEliminarMenusFactura();
		case Events.ELIMINAR_MENUS_FACTURA_OK:
			return new VEliminarMenusFactura_OK();
		case Events.ELIMINAR_MENUS_FACTURA_KO:
			return new VEliminarMenusFactura_KO();

		case Events.ABRIR_VDEVOLVER_MENU:
			return new VDevolverMenusFactura();
		case Events.DEVOLVER_MENU_OK:
			return new VDevolverMenusFactura_OK();
		case Events.DEVOLVER_MENU_KO:
			return new VDevolverMenusFactura_KO();

		// MENU
		case Events.ABRIR_VMENU:
			return new VMenu();

		case Events.ABRIR_VALTA_MENU:
			return new VAltaMenu();
		case Events.ALTA_MENU_OK:
			return new VAltaMenu_OK();
		case Events.ALTA_MENU_KO:
			return new VAltaMenu_KO();

		case Events.ABRIR_VBAJA_MENU:
			return new VBajaMenu();
		case Events.BAJA_MENU_OK:
			return new VBajaMenu_OK();
		case Events.BAJA_MENU_KO:
			return new VBajaMenu_KO();

		case Events.ABRIR_VMODIFICAR_MENU:
			return new VModificarMenu();
		case Events.ABRIR_VMODIFICAR_MENU2:
			return new VModificarMenu2();
		case Events.MODIFICAR_MENU_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.MODIFICAR_MENU_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");

		case Events.ABRIR_VMOSTRAR_UN_MENU:
			return new VMostrarMenu();
		case Events.MOSTRAR_UN_MENU_OK:
			return new VMostrarMenu_OK();
		case Events.MOSTRAR_UN_MENU_KO:
			return new VMostrarMenu_KO();

		case Events.ABRIR_VMOSTRAR_MENU_TODOS:
			return new VMostrarTodosMenu();

		case Events.ABRIR_VMOSTRAR_MENU_RANGO:
			return new VMostrarMenuRango();
		case Events.MOSTRAR_MENU_RANGO_OK:
			return new VMostrarTodosMenu();

		case Events.ABRIR_VVINCULAR_PRODUCTO:
			return new VAniadirProducto();
		case Events.VINCULAR_PRODUCTO_NO_EXISTE_MENU:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_PRODUCTO_NO_EXISTE_PRODUCTO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_PRODUCTO_YA_VINCULADO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_PRODUCTO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");

		case Events.ABRIR_VDESVINCULAR_PRODUCTO:
			return new VQuitarProducto();
		case Events.DESVINCULAR_PRODUCTO_NO_EXISTE_MENU_NI_PRODUCTO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_PRODUCTO_NO_VINCULADO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_PRODUCTO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");

		// Producto
		case Events.ABRIR_VPRODUCTO:
			return new VProducto();
		case Events.ABRIR_VMOSTRAR_UN_PRODUCTO:
			return new VMostrarProducto();
		case Events.MOSTRAR_UN_PRODUCTO_OK:
			return new VMostrarProducto_OK();
		case Events.MOSTRAR_UN_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_PRODUCTO_TODOS:
			return new VMostrarTodosProductos();
		case Events.MOSTRAR_PRODUCTO_TODOS_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.MOSTRAR_PRODUCTO_TODOS_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VALTA_PRODUCTO:
			return new VAltaProducto();
		case Events.ALTA_PRODUCTO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.ALTA_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VBAJA_PRODUCTO:
			return new VBajaProducto();
		case Events.BAJA_PRODUCTO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.BAJA_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_UN_PRODUCTO_POR_NOMBRE:
			return new VMostrarProductoPorNombre();
		case Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE_OK:
			return new VMostrarProducto_OK();
		case Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMODIFICAR_PRODUCTO:
			return new VModificarProducto();
		case Events.ABRIR_VMODIFICAR_PRODUCTO2:
			return new VModificarProducto2();
		case Events.MODIFICAR_PRODUCTO_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.MODIFICAR_PRODUCTO_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VVINCULAR_INGREDIENTE:
			return new VAniadirIngrediente();
		case Events.VINCULAR_INGREDIENTE_NO_EXISTE_PRODUCTO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_INGREDIENTE_NO_EXISTE_INGREDIENTE:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_INGREDIENTE_YA_VINCULADO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.VINCULAR_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_INGREDIENTE_NO_EXISTE_PRODUCTO_NI_INGREDIENTE:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_INGREDIENTE_NO_VINCULADO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.DESVINCULAR_INGREDIENTE_OK:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VDESVINCULAR_INGREDIENTE:
			return new VQuitarIngrediente();

		// Ingrediente
		case Events.ABRIR_VINGREDIENTE:
			return new VIngrediente();
		case Events.ABRIR_VMODIFICAR_INGREDIENTE:
			return new VModificarIngrediente();
		case Events.ABRIR_VMODIFICAR_INGREDIENTE2:
			return new VModificarIngrediente2();
		case Events.MODIFICAR_INGREDIENTE_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.MODIFICAR_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VALTA_INGREDIENTE:
			return new VAltaIngrediente();
		case Events.ALTA_INGREDIENTE_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.ALTA_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VBAJA_INGREDIENTE:
			return new VBajaIngrediente();
		case Events.BAJA_INGREDIENTE_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.BAJA_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_UN_INGREDIENTE:
			return new VMostrarIngrediente();
		case Events.MOSTRAR_UN_INGREDIENTE_OK:
			return new VMostrarIngrediente_OK();
		case Events.MOSTRAR_UN_INGREDIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_UN_INGREDIENTE_POR_NOMBRE:
			return new VMostrarIngredientePorNombre();
		case Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE_OK:
			return new VMostrarIngrediente_OK();
		case Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");
		case Events.ABRIR_VMOSTRAR_INGREDIENTE_TODOS:
			return new VMostrarTodosIngredientes();
		case Events.MOSTRAR_INGREDIENTE_TODOS_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");

		// Habitacion
		case Events.ABRIR_VHABITACION:
			return new VHabitacion();

		case Events.ABRIR_VCREAR_HABITACION:
			return new VCrearHabitacion();
		case Events.CREAR_HABITACION_OK:
			return new VCrearHabitacion_OK();
		case Events.CREAR_HABITACION_KO:
			return new VCrearHabitacion_KO();

		case Events.ABRIR_VBAJA_HABITACION:
			return new VEliminarHabitacion();
		case Events.BAJA_HABITACION_OK:
			return new VEliminarHabitacion_OK();
		case Events.BAJA_HABITACION_KO:
			return new VEliminarHabitacion_KO();

		case Events.ABRIR_VMODIFICAR_HABITACION:
			return new VEditarHabitacion();
		case Events.ABRIR_VMODIFICAR_HABITACION2:
			return new VEditarHabitacion2();
		case Events.MODIFICAR_HABITACION_OK:
			return new VEditarHabitacion_OK();
		case Events.MODIFICAR_HABITACION_KO:
			return new VEditarHabitacion_KO();

		case Events.ABRIR_VMOSTRAR_HABITACION_ID:
			return new VMostrarPorIdHabitacion();
		case Events.ABRIR_VMOSTRAR_HABITACION_NUMERO:
			return new VMostrarPorNumeroHabitacion();
		case Events.MOSTRAR_HABITACION_ID_OK:
			return new VMostrarHabitacion_OK();
		case Events.MOSTRAR_HABITACION_ID_KO:
			return new VMostrarHabitacion_KO();
		case Events.MOSTRAR_HABITACION_NUMERO_OK:
			return new VMostrarHabitacion_OK();
		case Events.MOSTRAR_HABITACION_NUMERO_KO:
			return new VMostrarHabitacion_KO();

		case Events.ABRIR_VMOSTRAR_HABITACION_TODOS:
			return new VMostrarTodasHabitaciones();

		// RECEPCIONISTA
		case Events.ABRIR_VRECEPCIONISTA:
			return new VRecepcionista();
		case Events.ABRIR_VALTA_RECEPCIONISTA:
			return new VCrearRecepcionista();
		case Events.ALTA_RECEPCIONISTA_OK:
			return new VCrearRecepcionista_OK();
		case Events.ALTA_RECEPCIONISTA_KO:
			return new VCrearRecepcionista_KO();
			
		case Events.ABRIR_VBAJA_RECEPCIONISTA:
			return new VEliminarRecepcionista();
		case Events.BAJA_RECEPCIONISTA_OK:
			return new VEliminarRecepcionista_OK();
		case Events.BAJA_RECEPCIONISTA_KO:
			return new VEliminarRecepcionista_OK();
			
		case Events.ABRIR_VMODIFICAR_RECEPCIONISTA:
			return new VEditarRecepcionista();
		case Events.ABRIR_VMODIFICAR_RECEPCIONISTA2:
			return new VEditarRecepcionista2();
		case Events.MODIFICAR_RECEPCIONISTA_OK:
			return new VEditarRecepcionista_OK();
		case Events.MODIFICAR_RECEPCIONISTA_KO:
			return new VEditarRecepcionista_KO();
			
		case Events.ABRIR_VMOSTRAR_POR_ID_RECEPCIONISTA:
			return new VMostrarPorIdRecepcionista();
		case Events.MOSTRAR_RECEPCIONISTA_ID_OK:
			return new VMostrarRecepcionista_OK();
		case Events.MOSTRAR_RECEPCIONISTA_ID_KO:
			return new VMostrarRecepcionista_KO();
			
		case Events.ABRIR_VMOSTRAR_POR_NOMBRE_RECEPCIONISTA:
			return new VMostrarPorNombreRecepcionista();
		case Events.MOSTRAR_RECEPCIONISTA_NOMBRE_OK:
			return new VMostrarRecepcionista_OK();
		case Events.MOSTRAR_RECEPCIONISTA_NOMBRE_KO:
			return new VMostrarRecepcionista_KO();
		case Events.ABRIR_VMOSTRAR_TODOS_RECEPCIONISTA:
			return new VMostrarTodosRecepcionista();

		// SERVICIO
		case Events.ABRIR_VSERVICIO:
			return new VServicio();
		case Events.ABRIR_VALTA_SERVICIO:
			return new VCrearServicio();
		case Events.ALTA_SERVICIO_OK:
			return new VCrearServicio_OK();
		case Events.ALTA_SERVICIO_KO:
			return new VCrearServicio_KO();
			
		case Events.ABRIR_VBAJA_SERVICIO:
			return new VEliminarServicio();
		case Events.BAJA_SERVICIO_OK:
			return new VEliminarServicio_OK();
		case Events.BAJA_SERVICIO_KO:
			return new VEliminarServicio_KO();
		
		case Events.ABRIR_VMODIFICAR_SERVICIO:
			return new VEditarServicio();
		case Events.ABRIR_VMODIFICAR_SERVICIO2:
			return new VEditarServicio2();
		case Events.MODIFICAR_SERVICIO_OK:
			return new VEditarServicio_OK();
		case Events.MODIFICAR_SERVICIO_KO:
			return new VEditarServicio_KO();
			
			
		case Events.ABRIR_VMOSTRAR_POR_ID_SERVICIO:
			return new VMostrarServicioPorId();
		case Events.ABRIR_VMOSTRAR_POR_TIPO_SERVICIO:
			return new VMostrarServicioPorTipo();
		case Events.MOSTRAR_SERVICIO_ID_OK:
			return new VMostrarServicio_OK();
		case Events.MOSTRAR_SERVICIO_ID_KO:
			return new VMostrarServicio_KO();
		case Events.MOSTRAR_SERVICIO_TIPO_OK:
			return new VMostrarServicio_OK();
		case Events.ABRIR_VMOSTRAR_SERVICIO_RESERVAS:
			return new VMostrarReservasPorServicio();
		case Events.MOSTRAR_SERVICIO_RESERVAS_OK:
			return new VMostrarReservasPorServicio_OK();
		case Events.MOSTRAR_SERVICIO_TIPO_KO:
			return new VMostrarServicio_KO();
		case Events.MOSTRAR_TODOS_SERVICIOS_OK:
			return new VMostrarTodosServicio();

		// CLIENTE
		case Events.ABRIR_VCLIENTE:
			return new VCliente();
			
		case Events.ABRIR_VALTA_CLIENTE:
			return new VCrearCliente();
		/*case Events.ALTA_CLIENTE_OK:
			return new VDialogo(JOptionPane.INFORMATION_MESSAGE, "Exito");
		case Events.ALTA_CLIENTE_KO:
			return new VDialogo(JOptionPane.ERROR_MESSAGE, "Error");*/
		case Events.ALTA_CLIENTE_OK:
			return new VCrearCliente_OK();
		case Events.ALTA_CLIENTE_KO:
			return new VCrearCliente_KO();
			
		case Events.ABRIR_VBAJA_CLIENTE:
			return new VEliminarCliente();
		case Events.BAJA_CLIENTE_OK:
			return new VEliminarCliente_OK();
		case Events.BAJA_CLIENTE_KO:
			return new VEliminarCliente_KO();
			
		case Events.ABRIR_VMODIFICAR_CLIENTE:
			return new VEditarCliente();
		case Events.ABRIR_VMODIFICAR_CLIENTE2:
			return new VEditarCliente2();
		case Events.MODIFICAR_CLIENTE_OK:
			return new VEditarCliente_OK();
		case Events.MODIFICAR_CLIENTE_KO:
			return new VEditarCliente_KO();
			
		case Events.ABRIR_VMOSTRAR_UN_CLIENTE:
			return new VMostrarCliente();
		case Events.MOSTRAR_CLIENTE_KO:
			return new VMostrarCliente_KO();
		case Events.MOSTRAR_CLIENTE_OK:
			return new VMostrarCliente_OK();	
		case Events.ABRIR_VMOSTRAR_CLIENTE_TODOS:
			return new VMostrarTodosCliente();
		case Events.ABRIR_VCALCULAR_PRECIO_TURNOS:
			return new VCalcularPrecioTurnos();
		case Events.CALCULAR_PRECIO_TURNOS_OK:
			return new VCalcularPrecioTurnos_OK();
		case Events.CALCULAR_PRECIO_TURNOS_KO:
			return new VCalcularPrecioTurnos_KO();
			
		case Events.ABRIR_VMOSTRAR_TURNOS_DE_CLIENTE:
			return new VMostrarTurnosDeCliente();
		case Events.ABRIR_VMOSTRAR_TURNOS_DE_CLIENTE_OK:
			return new VMostrarTurnosDeCliente_OK();
		case Events.MOSTRAR_TURNOS_DE_CLIENTE_KO:
			return new VMostrarTurnosDeCliente_KO();
			
		
		
		//TURNO
		case Events.ABRIR_VTURNO:
			return new VTurno();
		
		case Events.ABRIR_VALTA_TURNO:
			return new VCrearTurno();
		case Events.CREAR_TURNO_OK:
			return new VCrearTurno_OK();
		case Events.CREAR_TURNO_KO:
			return new VCrearTurno_KO();
				
		case Events.ABRIR_VBAJA_TURNO:
			return new VEliminarTurno();
		case Events.ELIMINAR_TURNO_OK:
			return new VEliminarTurnoOK();
		case Events.ELIMINAR_TURNO_KO:
			return new VElimiarTurnoKO();
			
		case Events.ABRIR_VMODIFICAR_TURNO:
			return new VEditarTurno();
		case Events.ABRIR_VMODIFICAR_TURNO2:
			return new VEditarTurno2();
		case Events.MODIFICAR_TURNO_OK:
			return new VEditarTurno_OK();
		case Events.MODIFICAR_TURNO_KO:
			return new VEditarTurno_KO();
			
		case Events.ABRIR_VMOSTRAR_TURNO_ID:
			return new VMostrarTurno();
		case Events.MOSTRAR_TURNO_ID_OK:
			return new VMostrarTurno_OK();
		case Events.MOSTRAR_TURNO_ID_KO:
			return new VMostrarTurno_KO();
			
		case Events.ABRIR_VMOSTRAR_TODOS_TURNO:
			return new VMostrarTodosTurno();
		
		case Events.ABRIR_VANYADIR_CLIENTE_TURNO_:
			return new VAnyadirClienteATurno();
		case Events.VINCULAR_TURNO_CLIENTE_OK:
			return new VAnyadirClienteATurno_OK();
		case Events.VINCULAR_TURNO_CLIENTE_KO:
			return new VAnyadirClienteATurno_KO();
			
		case Events.ABRIR_VELIMINAR_CLIENTE_TURNO_:
			return new VEliminarClienteATurno();
		case Events.DESVINCULAR_TURNO_CLIENTE_OK:
			return new VEliminarClienteATurno_OK();
		case Events.DESVINCULAR_TURNO_CLIENTE_KO:
			return new VEliminarClienteATurno_KO();
			
		case Events.ABRIR_VMOSTRAR_TURNO_CLIENTES:
			return new VMostrarClientesTurno();
		case Events.MOSTRAR_TURNO_CLIENTES_OK:
			return new VMostrarClientesTurno_OK();
		case Events.MOSTRAR_TURNO_CLIENTES_KO:
			return new VMostrarClientesTurno_KO();
			
			
		// RESERVA
		case Events.ABRIR_VRESERVA:
			return new VReserva();
			
		case Events.ABRIR_VCREAR_RESERVA:
			return new VCrearReserva();
		case Events.CREAR_RESERVA_OK:
			return new VCrearReserva_OK();
		case Events.CREAR_RESERVA_KO:
			return new VCrearReserva_KO();
			
		case Events.ABRIR_VBAJA_RESERVA:
			return new VEliminarReserva();
		case Events.BAJA_RESERVA_OK:
			return new VEliminarReserva_OK();
		case Events.BAJA_RESERVA_KO:
			return new VEliminarReserva_KO();
			
		case Events.ABRIR_VMODIFICAR_RESERVA:
			return new VEditarReserva();
		case Events.ABRIR_VMODIFICAR_RESERVA2:
			return new VEditarReserva2();
		case Events.MODIFICAR_RESERVA_OK:
			return new VEditarReserva_OK();
		case Events.MODIFICAR_RESERVA_KO:
			return new VEditarReserva_KO();
			
		case Events.ABRIR_VMOSTRAR_RESERVA_ID:
			return new VMostrarReserva();
		case Events.MOSTRAR_RESERVA_ID_OK:
			return new VMostrarReserva_OK();
		case Events.MOSTRAR_RESERVA_ID_KO:
			return new VMostrarReserva_KO();
			
		case Events.ABRIR_VMOSTRAR_RESERVA_TODOS:
			return new VMostrarTodasReserva();
			
		case Events.ABRIR_VCOMPRA_SERVICIO:
			return new VCompraServicio();
		case Events.ABRIR_VCOMPRA_SERVICIO2:
			return new VCompraServicio2();
		case Events.COMPRA_SERVICIO_RESERVA_OK:
			return new VCompraServicio_OK();
		case Events.COMPRA_SERVICIO_RESERVA_KO:
			return new VCompraServicio_KO();
			
		case Events.ABRIR_VDEVOLUCION_SERVICIO_RESERVA:
			return new VDevolucionServicio();
		case Events.ABRIR_VDEVOLUCION_SERVICIO_RESERVA2:
			return new VDevolucionServicio2();
		case Events.DEVOLUCION_SERVICIO_RESERVA_OK:
			return new VDevolucionServicio_OK();
		case Events.DEVOLUCION_SERVICIO_RESERVA_KO:
			return new VDevolucionServicio_KO();
			
		case Events.ABRIR_VDESVINCULAR_HABITACION_RESERVA:
			return new VDesvincularHabitacionReserva();
		case Events.DESVINCULAR_HABITACION_RESERVA_OK:
			return new VDesvincularHabitacionReserva_OK();
		case Events.DESVINCULAR_HABITACION_RESERVA_KO:
			return new VDesvincularHabitacionReserva_KO();

		}

		return null;
	}
}