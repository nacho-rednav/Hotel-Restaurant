package Integracion.FactoriaIntegracion;

import Integracion.MenuProducto.DAOMenuProd;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.Mesa.DAOMesa;
import Integracion.Factura.DAOFactura;
import Integracion.Menu.DAOMenu;
import Integracion.FacturaMenu.DAOFacturaMenu;
import Integracion.Producto.DAOProducto;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instancia;

	public static FactoriaIntegracion getInstance() {
		if (instancia == null) {
			instancia = new FactoriaIntegracionImp();
		}
		return instancia;
	}

	public abstract DAOMenuProd generarDAOMenuProd();

	public abstract DAOProdIngr generarDAOProdIngr();

	public abstract DAOEmpleado generarDAOEmpleado();

	public abstract DAOIngrediente generarDAOIngrediente();

	public abstract DAOMesa generarDAOMesa();

	public abstract DAOFactura generarDAOFactura();

	public abstract DAOMenu generarDAOMenu();

	public abstract DAOFacturaMenu generarDAOFacturaMenu();

	public abstract DAOProducto generarDAOProducto();
}