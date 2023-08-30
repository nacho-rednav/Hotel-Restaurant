/**
 * 
 */
package Negocio.Mesa;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Mesa.DAOMesa;

public class SAMesaImp implements SAMesa {

	/** 
	* (non-Javadoc)
	* @see SAMesa#crear(Class mesa)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class mesa) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMesa#eliminar(Class id)
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
	* @see SAMesa#editar(Class tMesa)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tMesa) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMesa#mostrarUno(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class mostrarUno(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer crear(TMesa tMesa) {

		if (tMesa.getNumMesa() <= 0 || tMesa.getCapacidad() <= 0)
			return -1;

		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();
		TMesa mesa = daoMesa.leerPorNum(tMesa.getNumMesa());

		int res = 0;
		if (mesa == null) {
			res = daoMesa.crear(tMesa);
			transaction.commit();

		} else if (!mesa.getActivo()) {
			tMesa.setActivo(true);
			tMesa.setId(mesa.getId());
			daoMesa.editar(tMesa);
			res = mesa.getId();
			transaction.commit();
		} else {
			transaction.rollback();
			res = -1;
		}
		return res;
	}

	public Integer eliminar(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();
		int res = 0;

		if (id == null) {
			transaction.rollback();
			res = -1;
		} else {
			TMesa tMesa = daoMesa.leerPorId(id);
			if (tMesa != null && tMesa.getActivo() && tMesa.getDisponibilidad()) {
				daoMesa.eliminar(id);
				transaction.commit();
			} else {
				transaction.rollback();
				res = -1;
			}
		}

		return res;
	}

	public Integer editar(TMesa tMesa) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();

		if (tMesa.getId() == null || tMesa.getNumMesa() == null) {
			transaction.rollback();
			return -1;
		}

		TMesa mesa = daoMesa.leerPorId(tMesa.getId());

		int res = 0;

		TMesa mesaAux = daoMesa.leerPorNum(tMesa.getNumMesa());

		if (mesa == null || !mesa.getActivo() || (mesaAux != null && mesaAux.getId() != tMesa.getId())) {
			transaction.rollback();
			res = -1;
		} else {
			daoMesa.editar(tMesa);
			transaction.commit();
		}

		return res;
	}

	public TMesa mostrarUno(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();

		if (id == null) {
			transaction.rollback();
			return null;
		}
		TMesa tMesa = daoMesa.leerPorId(id);

		if (tMesa == null || !tMesa.getActivo()) {
			transaction.rollback();
		} else if (tMesa != null)
			transaction.commit();

		return tMesa;
	}

	public Collection<TMesa> mostrarTodos() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();

		Collection<TMesa> mesas = daoMesa.leerTodos();
		transaction.commit();

		return mesas;
	}
}