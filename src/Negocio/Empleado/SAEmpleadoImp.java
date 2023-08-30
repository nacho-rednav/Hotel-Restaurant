package Negocio.Empleado;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Queries.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Negocio.Queries.FactoriaQuery;
import Negocio.Queries.TQuery;

public class SAEmpleadoImp implements SAEmpleado {

	public int crear(TEmpleado tEmp) {
		int res;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		TEmpleado tEmpLeido = daoEmp.leerPorDNI(tEmp.getDNI());

		if (tEmpLeido != null && tEmpLeido.getActivo()) {
			res = -1;
			transaction.rollback();
		} else if (tEmpLeido != null && !tEmpLeido.getActivo()) {
			tEmp.setActivo(true);
			tEmp.setId(tEmpLeido.getId());
			daoEmp.editar(tEmp);

			res = tEmp.getId();
			transaction.commit();
		}
		//tEmpLeido == null
		else {
			res = daoEmp.crear(tEmp);
			transaction.commit();
		}

		return res;
	}

	public int eliminar(Integer id) {
		int res = 0;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		TEmpleado tEmpLeido = daoEmp.leerUno(id);

		if (tEmpLeido != null && tEmpLeido.getActivo()) {
			//?TODO preguntar qué pasa si hay factura abierta con el empleado asignado
			res = daoEmp.eliminar(id);

			transaction.commit();
		} else {
			res = -1;
			transaction.rollback();
		}

		return res;
	}

	public int editar(TEmpleado tEmp) {
		int res = 0;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		TEmpleado tEmpLeido = daoEmp.leerPorDNI(tEmp.getDNI());

		if (tEmpLeido != null && tEmpLeido.getId() != tEmp.getId()) {
			res = -1;
			transaction.rollback();
		}
		//TODO No entiendo muy bien esta condicion (Ye)
		else if (tEmpLeido == null || tEmpLeido.getId() == tEmp.getId()) {
			res = daoEmp.editar(tEmp);
			transaction.commit();
		}
		return res;

	}

	public TEmpleado mostrarUno(Integer id) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		TEmpleado tEmpLeido = daoEmp.leerUno(id);

		if (tEmpLeido == null || !tEmpLeido.getActivo()) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return tEmpLeido;

	}

	public ArrayList mostrarTodos() {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		ArrayList<TEmpleado> lista = new ArrayList<TEmpleado>();
		lista = (ArrayList<TEmpleado>) daoEmp.leerTodos();

		if (lista == null) {
			transaction.rollback();
			return null;
		}

		transaction.commit();
		return lista;
	}

	//?TODO No estoy seguro de que esté bien (West)
	public ArrayList<TEmpleado> mostrarEmpDespRango(TQuery rango) {
		int res = 0;
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		Query<TEmpleado> qry = (Query<TEmpleado>) FactoriaQuery.getInstancia().nuevaQuery("rango_precio_empleado");
		if (qry != null) {
			ArrayList<TEmpleado> listEmp = qry.execute(rango);
			transaction.commit();
			return listEmp;
		} else {
			transaction.rollback();
			return null;
		}
	}

	public TEmpleado leerPorDNI(String dni) {
		Transaction transaction = TransactionManager.getInstance().newTransaction();
		transaction.start();

		DAOEmpleado daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
		TEmpleado tEmp = daoEmp.leerPorDNI(dni);

		if (tEmp == null) {
			transaction.rollback();
			return null;
		} else {
			transaction.commit();
			return tEmp;
		}
	}
}