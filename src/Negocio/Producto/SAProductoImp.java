/**
 * 
 */
package Negocio.Producto;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Producto.DAOProducto;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.MenuProducto.DAOMenuProd;
import Integracion.Queries.Query;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.Ingrediente.TIngrediente;
import Negocio.Menu.TMenu;
import Negocio.Producto.TProducto;
import Negocio.Queries.FactoriaQuery;
import Negocio.Queries.TQuery;

public class SAProductoImp implements SAProducto {

	public Integer crear(TProducto tProd) {
		int res;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TProducto tProdLeido = daoProd.leerPorNombre(tProd.getNombre());

		if (tProdLeido == null) {
			res = daoProd.crear(tProd);
			transaction.commit();
		} else if (!tProdLeido.getActivo()) {
			tProd.setActivo(true);
			tProd.setId(tProdLeido.getId());
			daoProd.editar(tProd);
			res = 0;
			transaction.commit();
		} else {
			transaction.rollback();
			res = -1;
		}

		return res;
	}

	public Integer editar(TProducto tProd) {
		int res = -1;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TProducto tProdLeido = daoProd.leerPorNombre(tProd.getNombre());

		if (tProdLeido != null && tProdLeido.getId() != tProd.getId()) {
			res = -1;
			transaction.rollback();
		} else if (tProdLeido == null || tProdLeido.getId() == tProd.getId()) {
			res = daoProd.editar(tProd);
			transaction.commit();
		}

		return res;

	}

	public Integer eliminar(Integer id) {
		int res = 0;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TProducto tProdLeido = daoProd.leerUno(id);

		if (tProdLeido != null && tProdLeido.getActivo()) {
			DAOMenuProd daoMenuProd = FactoriaIntegracion.getInstance().generarDAOMenuProd();
			Collection<TMenu> menus = daoMenuProd.leerMenusPorProd(tProdLeido.getId());
			if (menus.size() == 0) {
				DAOProdIngr daoIngProd = FactoriaIntegracion.getInstance().generarDAOProdIngr();
				daoIngProd.desvincularIngrsDeProd(tProdLeido.getId());
				res = daoProd.eliminar(id);
				transaction.commit();
			} else {
				res = -1;
				transaction.rollback();
			}
		} else {
			res = -1;
			transaction.rollback();
		}

		return res;
	}

	public TProducto mostrarUno(Integer id) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TProducto tProdLeido = daoProd.leerUno(id);

		if (tProdLeido == null || !tProdLeido.getActivo())
			transaction.rollback();

		transaction.commit();
		return tProdLeido;
	}

	public TProducto mostrarUnoPorNombre(String nombre) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		TProducto tProducto = daoProd.leerPorNombre(nombre);

		if (tProducto == null) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return tProducto;
	}

	public Collection<TProducto> mostrarTodos() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		FactoriaIntegracion facInt = FactoriaIntegracion.getInstance();
		DAOProducto daoProd = facInt.generarDAOProducto();

		Collection<TProducto> lista = daoProd.leerTodos();
		transaction.commit();

		return lista;
	}

	public int vincular(TProductoIngrediente tProdIngr) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		int res = 1, idProd = tProdIngr.getIdProducto(), idIngr = tProdIngr.getIdIngrediente();
		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		DAOIngrediente daoIngr = FactoriaIntegracion.getInstance().generarDAOIngrediente();
		DAOProdIngr daoProdIngr = FactoriaIntegracion.getInstance().generarDAOProdIngr();
		TProducto tProd = daoProd.leerUno(idProd);
		TIngrediente tIngr = daoIngr.leerUno(idIngr);

		if (tProd != null && tProd.getActivo() && tIngr != null && tIngr.getActivo()
				&& !daoProdIngr.contiene(tProdIngr)) {
			daoProdIngr.vincular(tProdIngr);
			transaction.commit();
		} else if (tProd == null || !tProd.getActivo()) {
			res = -5;
			transaction.rollback();
		} else if (tIngr == null || !tIngr.getActivo()) {
			res = -4;
			transaction.rollback();
		} else if (daoProdIngr.contiene(tProdIngr)) {
			res = -2;
			transaction.rollback();
		} else {
			res = -1;
			transaction.rollback();
		}

		return res;
	}

	public int desvincular(TProductoIngrediente tProdIngr) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		int res = 1, idProd = tProdIngr.getIdProducto(), idIngr = tProdIngr.getIdIngrediente();
		DAOProducto daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
		DAOIngrediente daoIngr = FactoriaIntegracion.getInstance().generarDAOIngrediente();
		DAOProdIngr daoProdIngr = FactoriaIntegracion.getInstance().generarDAOProdIngr();
		TProducto tProd = daoProd.leerUno(idProd);
		TIngrediente tIngr = daoIngr.leerUno(idIngr);

		if (daoProdIngr.contiene(tProdIngr)) {
			daoProdIngr.desvincular(tProdIngr);
			transaction.commit();
		} else if (tProd == null || tIngr == null) {
			res = -3;
			transaction.rollback();
		} else if (!daoProdIngr.contiene(tProdIngr)) {
			res = -2;
			transaction.rollback();
		} else {
			res = -1;
			transaction.rollback();
		}

		return res;
	}

	public ArrayList<TIngrediente> mostrarIngrsPorProd(Integer idProd) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		Collection<TIngrediente> ingrsLeidos = FactoriaIntegracion.getInstance().generarDAOProdIngr()
				.leerIngrsPorProd(idProd), ingrsActivos = new ArrayList<TIngrediente>();

		for (TIngrediente p : ingrsLeidos)
			if (p.getActivo())
				ingrsActivos.add(p);

		transaction.commit();
		return (ArrayList<TIngrediente>) ingrsActivos;

	}
}