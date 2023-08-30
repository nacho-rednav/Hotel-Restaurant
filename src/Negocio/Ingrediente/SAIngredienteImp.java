package Negocio.Ingrediente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Factura.DAOFactura;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Negocio.Factura.TFactura;
import Negocio.Mesa.TMesa;
import Negocio.Producto.TProducto;

public class SAIngredienteImp implements SAIngrediente {

	/** 
	* (non-Javadoc)
	* @see SAIngrediente#crear(Class tIng)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tIng) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngrediente#editar(Class tIngr)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tIngr) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngrediente#eliminar(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class eliminar(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngrediente#mostrarUno(Class idIngr)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class mostrarUno(Class idIngr) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public int crear(TIngrediente tIngr) {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.newTransaction();
			transaction.start();
			DAOIngrediente daoIngrediente = FactoriaIntegracion.getInstance().generarDAOIngrediente();
			TIngrediente ingrediente = daoIngrediente.leerUnoPorNombre(tIngr.getNombre());

			int res;
			if (ingrediente == null) {
				res = daoIngrediente.crear(tIngr);
				transaction.commit();
			} else if (!ingrediente.getActivo()) {
				tIngr.setActivo(true);
				tIngr.setId(ingrediente.getId());
				daoIngrediente.editar(tIngr);
				res = 0;
				transaction.commit();
			} else {

				transaction.rollback();
				res = -1;
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int editar(TIngrediente tIngr) {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.newTransaction();
			transaction.start();

			int res = -1;

			DAOIngrediente daoIngrediente = FactoriaIntegracion.getInstance().generarDAOIngrediente();
			TIngrediente ingrediente = daoIngrediente.leerUno(tIngr.getId());

			if (ingrediente != null && ingrediente.getId() != tIngr.getId()) {
				transaction.rollback();
				res = -1;
			} else if (ingrediente == null || ingrediente.getId() == tIngr.getId()) {
				res = daoIngrediente.editar(tIngr);
				transaction.commit();
			}

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public int eliminar(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		FactoriaIntegracion facInt = FactoriaIntegracion.getInstance();
		DAOIngrediente daoIng = facInt.generarDAOIngrediente();

		TIngrediente tIngrediente = daoIng.leerUno(id);
		int res = 0;
		if (tIngrediente != null && tIngrediente.getActivo()) {
			DAOProdIngr daoProdIng = facInt.generarDAOProdIngr();
			Collection<TProducto> productos = daoProdIng.leerProdsPorIngr(id);
			if (productos.size() == 0) {
				daoIng.eliminar(id);
				transaction.commit();
				//res = 0;
			} else {
				transaction.rollback();
				res = -1;
			}
		} else if (tIngrediente == null || !tIngrediente.getActivo()) {
			transaction.rollback();
			res = -1;
		}

		return res;
	}

	public TIngrediente mostrarUno(Integer idIngr) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOIngrediente daoIng = FactoriaIntegracion.getInstance().generarDAOIngrediente();
		TIngrediente tIngrediente = daoIng.leerUno(idIngr);

		if (tIngrediente == null) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return tIngrediente;
	}

	public TIngrediente mostrarUnoPorNombre(String nombre) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOIngrediente daoIng = FactoriaIntegracion.getInstance().generarDAOIngrediente();
		TIngrediente tIngrediente = daoIng.leerUnoPorNombre(nombre);

		if (tIngrediente == null || !tIngrediente.getActivo()) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return tIngrediente;
	}

	public Collection<TIngrediente> mostrarTodos() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		FactoriaIntegracion facInt = FactoriaIntegracion.getInstance();
		DAOIngrediente daoIng = facInt.generarDAOIngrediente();

		Collection<TIngrediente> allIngr = daoIng.leerTodos();
		transaction.commit();
		//		Collection<TIngrediente> ingrActivos = new ArrayList<TIngrediente>(); // Descomentar si queremos mostrar todos los activos y no los inactivos
		//		for (TIngrediente tIngr : allIngr) {
		//			if(tIngr.getActivo())
		//				ingrActivos.add(tIngr);
		//		}
		//		return ingrActivos;
		//		if(allIngr != null) // No compruebo si es null o no
		return allIngr;
		//		return null;
	}

	public Collection<TProducto> mostrarProdsPorIngr(Integer idIngr) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		Collection<TProducto> prodsLeidos = FactoriaIntegracion.getInstance().generarDAOProdIngr()
				.leerProdsPorIngr(idIngr), prodsActivos = new ArrayList<TProducto>();

		for (TProducto p : prodsLeidos)
			if (p.getActivo())
				prodsActivos.add(p);

		transaction.commit();
		return prodsActivos;

	}
}