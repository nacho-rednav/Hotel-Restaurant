/**
 * 
 */
package Integracion.FactoriaIntegracion;

import Integracion.MenuProducto.DAOMenuProd;
import Integracion.MenuProducto.DAOMenuProdImp;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Integracion.ProductoIngrediente.DAOProdIngrImp;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Empleado.DAOEmpleadoImp;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.Ingrediente.DAOIngredienteImp;
import Integracion.Mesa.DAOMesa;
import Integracion.Mesa.DAOMesaImp;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOFacturaImp;
import Integracion.FacturaMenu.DAOFacturaMenu;
import Integracion.FacturaMenu.DAOFacturaMenuImp;
import Integracion.Menu.DAOMenu;
import Integracion.Menu.DAOMenuImp;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	public DAOMenuProd generarDAOMenuProd() {
		return new DAOMenuProdImp();
	}

	public DAOProdIngr generarDAOProdIngr() {
		return new DAOProdIngrImp();
	}

	public DAOEmpleado generarDAOEmpleado() {
		return new DAOEmpleadoImp();
	}

	public DAOIngrediente generarDAOIngrediente() {
		return new DAOIngredienteImp();
	}

	public DAOMesa generarDAOMesa() {
		return new DAOMesaImp();
	}

	public DAOFactura generarDAOFactura() {
		return new DAOFacturaImp();
	}

	public DAOMenu generarDAOMenu() {
		return new DAOMenuImp();

	}

	public DAOProducto generarDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOFacturaMenu generarDAOFacturaMenu() {
		return new DAOFacturaMenuImp();
	}
}