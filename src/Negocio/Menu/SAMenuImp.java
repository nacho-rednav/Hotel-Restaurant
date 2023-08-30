/**
 * 
 */
package Negocio.Menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.Menu.DAOMenu;
import Integracion.MenuProducto.DAOMenuProd;
import Integracion.Producto.DAOProducto;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Integracion.Queries.Query;
import Negocio.Empleado.TEmpleado;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;
import Negocio.Queries.FactoriaQuery;
import Negocio.Queries.TQuery;

public class SAMenuImp implements SAMenu { //TODO: revisar si esto esta bien

	public int crear(TMenu tMenu) {
		int res;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		TMenu tMenuLeido = daoMenu.leerPorNombre(tMenu.getNombre());

		if (tMenuLeido != null && tMenuLeido.getActivo()) {
			res = -1;
			transaction.rollback();
		} else if (tMenuLeido != null && !tMenuLeido.getActivo()) {
			tMenuLeido.setActivo(true);
			daoMenu.editar(tMenuLeido);

			res = tMenuLeido.getId();
			transaction.commit();
		}
		//tEmpLeido == null
		else {
			res = daoMenu.crear(tMenu);
			transaction.commit();
		}

		return res;
	}

	public int eliminarProd(TMenuProducto tMenuProd) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();
		int res = 1, idMenu = tMenuProd.getIdMenu(), idProd = tMenuProd.getIdProducto();
		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TMenu auxMenu = daoMenu.leerUno(idMenu);
		TProducto auxProd = daoProd.leerUno(idProd);
		DAOMenuProd daoMenuProd = FactoriaIntegracion.getInstance().generarDAOMenuProd();

		if (daoMenuProd.contiene(tMenuProd)) {
			daoMenuProd.desvincular(tMenuProd);
			transaction.commit();
		} else if (auxMenu == null || auxProd == null) {
			res = -3;
			transaction.rollback();
		} else if (!daoMenuProd.contiene(tMenuProd)) {
			res = -2;
			transaction.rollback();
		} else {
			res = -1;
			transaction.rollback();
		}
		return res;
	}

	public int anadirProd(TMenuProducto tMenuProd) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		int res = 1, idMenu = tMenuProd.getIdMenu(), idProd = tMenuProd.getIdProducto();
		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		DAOMenuProd daoMenuProd = FactoriaIntegracion.getInstance().generarDAOMenuProd();
		TMenu auxMenu = daoMenu.leerUno(idMenu);
		TProducto auxProd = daoProd.leerUno(idProd);

		if (auxMenu != null && auxMenu.getActivo() && auxProd != null && auxProd.getActivo()
				&& !daoMenuProd.contiene(tMenuProd)) {
			daoMenuProd.vincular(tMenuProd);
			transaction.commit();
		} else if (auxMenu == null || !auxMenu.getActivo()) {
			res = -5;
			transaction.rollback();
		} else if (auxProd == null || !auxProd.getActivo()) {
			res = -4;
			transaction.rollback();
		} else if (daoMenuProd.contiene(tMenuProd)) {
			res = -2;
			transaction.rollback();
		} else {
			res = -1;
			transaction.rollback();
		}
		return res;
	}

	public int editar(TMenu tMenu) {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.newTransaction();
			transaction.start();

			int res = 0;

			DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
			TMenu menu = daoMenu.leerPorNombre(tMenu.getNombre());

			if (menu != null && (menu.getId() != tMenu.getId())) {
				transaction.rollback();
				res = -1;
			} else if (menu == null || menu.getId() == tMenu.getId()) {
				res = daoMenu.editar(tMenu);
				transaction.commit();
			}

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int eliminar(Integer idMenu) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		FactoriaIntegracion facInt = FactoriaIntegracion.getInstance();
		DAOMenu daoMenu = facInt.generarDAOMenu();

		TMenu tMenu = daoMenu.leerUno(idMenu);
		int res = 0;
		if (tMenu != null && tMenu.getActivo()) {
			DAOMenuProd daoMenuProd = facInt.generarDAOMenuProd();
			Collection<TProducto> productos = daoMenuProd.leerProdsPorMenu(idMenu);
			if (productos.size() == 0) {
				res = daoMenu.eliminar(idMenu);
				transaction.commit();
			} else {
				transaction.rollback();
				res = -1;
			}
		} else if (tMenu == null || !tMenu.getActivo()) {
			transaction.rollback();
			res = -1;
		}

		return res;
	}

	public TMenu mostrarUno(Integer id) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		TMenu tMenu = daoMenu.leerUno(id);

		if (tMenu == null || tMenu.getActivo() == false) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return tMenu;
	}

	public ArrayList<TMenu> mostrarTodos() {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		ArrayList<TMenu> lista = new ArrayList<TMenu>();
		lista = (ArrayList<TMenu>) daoMenu.leerTodos();

		if (lista == null) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return lista;
	}

	public ArrayList<TProducto> mostrarProdsPorMenu(Integer idMenu) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		Collection<TProducto> prodsLeidos = FactoriaIntegracion.getInstance().generarDAOMenuProd()
				.leerProdsPorMenu(idMenu), prodsActivos = new ArrayList<TProducto>();

		for (TProducto p : prodsLeidos)
			if (p.getActivo())
				prodsActivos.add(p);
		transaction.commit();

		return (ArrayList<TProducto>) prodsActivos;
	}

	//TODO Nose si esta bien, esta copiado del mostrarEmpDespRango() del SAEmpleado
	public ArrayList<TMenu> mostrarMenusRangoPrecio(TQuery Parameter2) {
		int res = 0;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		Query<TMenu> qry = (Query<TMenu>) FactoriaQuery.getInstancia().nuevaQuery("rango_precio_menu");
		if (qry != null) {
			ArrayList<TMenu> listMenu = qry.execute(Parameter2);
			transaction.commit();
			return listMenu;
		} else {
			transaction.rollback();
			return null;
		}
	}
}