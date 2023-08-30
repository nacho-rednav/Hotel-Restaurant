package Negocio.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Factura.DAOFactura;
import Integracion.FacturaMenu.DAOFacturaMenu;
import Integracion.Menu.DAOMenu;
import Integracion.Mesa.DAOMesa;
import Negocio.Empleado.TEmpleado;
import Negocio.Factura.TFactura;
import Negocio.Menu.TMenu;
import Negocio.Mesa.TMesa;
import Presentacion.Controller.Events;

public class SAFacturaImp implements SAFactura {

	/** 
	* (non-Javadoc)
	* @see SAFactura#crear(Class tFactura)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tFactura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#cerrar(Class tFactura)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class cerrar(Class tFactura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#editar(Class tFactura)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tFactura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#eliminar(Class id)
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
	* @see SAFactura#mostrarUno(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TFactura mostrarUno(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer abrir(TFactura tFactura) {

		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();
		DAOEmpleado daoEmpleado = FactoriaIntegracion.getInstance().generarDAOEmpleado();

		TEmpleado empleado = daoEmpleado.leerUno(tFactura.getIdEmpleado());
		TMesa mesa = daoMesa.leerPorId(tFactura.getIdMesa());

		int res = 0;

		if (mesa == null || !mesa.getActivo()) {
			transaction.rollback();
			res = Events.ERROR_FACTURA_MESA_INACTIVA;
		} else if (empleado == null) {
			transaction.rollback();
			res = Events.ERROR_FACTURA_POR_EMPLEADO;
		} else if (!mesa.getDisponibilidad()) {
			transaction.rollback();
			res = Events.ERROR_FACTURA_DISPONIBILIDAD_MESA;
		} else {
			res = FactoriaIntegracion.getInstance().generarDAOFactura().abrir(tFactura);
			mesa.setDisponibilidad(false);
			daoMesa.editar(mesa);
			transaction.commit();
		}

		return res;
	}

	public Integer cerrar(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();

		TFactura tFactura = daoFactura.leerUno(id);
		if (tFactura == null || !tFactura.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_ID_INEXISTENTE;
		}
		int res = 0;

		DAOMesa daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();
		TMesa mesa = daoMesa.leerPorId(tFactura.getIdMesa());
		if (mesa == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_MESA_INACTIVA;
		}
		mesa.setDisponibilidad(true);
		daoMesa.editar(mesa);

		float precio = 0;
		ArrayList<TLineaDePedido> lineas = (ArrayList<TLineaDePedido>) FactoriaIntegracion.getInstance()
				.generarDAOFacturaMenu().leerMenusEnFactura(id);
		for (TLineaDePedido p : lineas) {
			precio += p.getPrecio() * p.getCantidad();
		}
		tFactura.setImporte(precio);
		tFactura.setHoraCobro(new Date(Calendar.getInstance().getTimeInMillis()));
		tFactura.setActivo(false);

		daoFactura.cerrar(tFactura);
		transaction.commit();

		return res;
	}

	public Integer editar(TFactura tFactura) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();
		DAOMesa daoM = FactoriaIntegracion.getInstance().generarDAOMesa();

		TFactura factura = daoFactura.leerUno(tFactura.getId());

		int res = 0;

		if (factura == null || tFactura.getIdEmpleado() == null || tFactura.getIdMesa() == null
				|| !factura.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_ID_INEXISTENTE;
		}

		TMesa mesaNueva = daoM.leerPorId(tFactura.getIdMesa());
		TEmpleado emp = FactoriaIntegracion.getInstance().generarDAOEmpleado().leerUno(tFactura.getIdEmpleado());
		if (mesaNueva == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_MESA_INACTIVA;
		}
		if (emp == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_POR_EMPLEADO;
		} else {
			if (tFactura.getIdMesa() != factura.getIdMesa()) {
				if (!mesaNueva.getDisponibilidad() || !mesaNueva.getActivo()) {
					transaction.rollback();
					return Events.ERROR_FACTURA_DISPONIBILIDAD_MESA;
				} else {
					TMesa mesaVieja = daoM.leerPorId(factura.getIdMesa());
					mesaVieja.setDisponibilidad(true);
					mesaNueva.setDisponibilidad(false);
					daoM.editar(mesaVieja);
					daoM.editar(mesaNueva);
					daoFactura.editar(tFactura);
					transaction.commit();
					res = 0;
				}
			} else {
				daoFactura.editar(tFactura);
				transaction.commit();
				res = 0;
			}
		}

		return res;
	}

	public TFacturaCompleta mostrarUno(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();

		TFactura tFactura = daoFactura.leerUno(id);

		if (tFactura == null) {
			transaction.rollback();
			return null;
		}
		TMesa mesa = FactoriaIntegracion.getInstance().generarDAOMesa().leerPorId(tFactura.getIdMesa());
		TEmpleado emp = FactoriaIntegracion.getInstance().generarDAOEmpleado().leerUno(tFactura.getIdEmpleado());
		ArrayList<TLineaDePedido> lineas = (ArrayList<TLineaDePedido>) FactoriaIntegracion.getInstance()
				.generarDAOFacturaMenu().leerMenusEnFactura(tFactura.getId());
		ArrayList<TMenu> menus = new ArrayList<TMenu>();
		DAOMenu daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
		TMenu menu;
		for (TLineaDePedido l : lineas) {
			menu = daoMenu.leerUno(l.getIdMenu());
			if (menu != null) {
				menus.add(menu);
			}
		}
		TFacturaCompleta res = new TFacturaCompleta(tFactura, mesa, emp, menus, lineas);
		transaction.commit();

		return res;
	}

	public Collection<TFactura> mostrarTodos() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();

		Collection<TFactura> facturas = daoFactura.leerTodos();

		transaction.commit();

		return facturas;
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#aniadirMenus(Class info)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class aniadirMenus(Class info) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#eliminarMenus(Class id, Class cantidad)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void eliminarMenus(Class id, Class cantidad) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAFactura#devolverMenus(Class tMenEnFact, Class res)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void devolverMenus(Class tMenEnFact, Class res) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Integer aniadirMenus(TLineaDePedido info) {
		int res = -1;

		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();

		if (info.getCantidad() <= 0 || info.getIdFactura() == null || info.getIdMenu() == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_DATOS;
		}
		DAOFactura daoF = FactoriaIntegracion.getInstance().generarDAOFactura();
		DAOMenu daoM = FactoriaIntegracion.getInstance().generarDAOMenu();
		DAOFacturaMenu daoFM = FactoriaIntegracion.getInstance().generarDAOFacturaMenu();

		TFactura tf = daoF.leerUno(info.getIdFactura());
		TMenu tm = daoM.leerUno(info.getIdMenu());
		if (tf == null || !tf.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_ID_INEXISTENTE;
		}
		if (tm == null || !tm.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_MENU_INEXISTENTE;
		}
		if (tm.getStock() < info.getCantidad()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_STOCK;
		}

		tm.setStock(tm.getStock() - info.getCantidad());
		daoM.editar(tm);

		TLineaDePedido linea = daoFM.leerUno(info);
		if (linea != null) {
			info.setCantidad(linea.getCantidad() + info.getCantidad());
			daoFM.editarCantidadMenu(info);
		} else {
			info.setPrecio(tm.getPrecio());
			daoFM.vincular(info);
		}

		res = 0;
		transaction.commit();

		return res;
	}

	public Integer eliminarMenus(TLineaDePedido info) {
		int res = -1;

		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();

		if (info.getCantidad() <= 0 || info.getIdFactura() == null || info.getIdMenu() == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_DATOS;
		}

		DAOFactura daoF = FactoriaIntegracion.getInstance().generarDAOFactura();
		DAOMenu daoM = FactoriaIntegracion.getInstance().generarDAOMenu();
		DAOFacturaMenu daoFM = FactoriaIntegracion.getInstance().generarDAOFacturaMenu();

		TFactura factura = daoF.leerUno(info.getIdFactura());
		TMenu menu = daoM.leerUno(info.getIdMenu());
		TLineaDePedido linea = daoFM.leerUno(info);
		if (factura == null || !factura.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_ID_INEXISTENTE;
		}
		if (menu == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_MENU_INEXISTENTE;
		}
		if (linea == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_LINEA_INEXISTENTE;
		}

		int cantidad = info.getCantidad();
		if (linea.getCantidad() < cantidad)
			cantidad = linea.getCantidad();
		menu.setStock(menu.getStock() + cantidad);
		daoM.editar(menu);

		info.setCantidad(linea.getCantidad() - cantidad);
		if (info.getCantidad() <= 0)
			daoFM.desvincular(info);
		else
			daoFM.editarCantidadMenu(info);

		res = 0;
		transaction.commit();

		return res;
	}

	public Integer devolverMenus(TLineaDePedido info) {
		int res = -1;

		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();

		if (info.getCantidad() <= 0 || info.getIdFactura() == null || info.getIdMenu() == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_DATOS;
		}

		DAOFactura daoF = FactoriaIntegracion.getInstance().generarDAOFactura();
		DAOMenu daoM = FactoriaIntegracion.getInstance().generarDAOMenu();
		DAOFacturaMenu daoFM = FactoriaIntegracion.getInstance().generarDAOFacturaMenu();

		TFactura factura = daoF.leerUno(info.getIdFactura());
		TMenu menu = daoM.leerUno(info.getIdMenu());
		TLineaDePedido linea = daoFM.leerUno(info);
		if (factura == null || factura.getActivo()) {
			transaction.rollback();
			return Events.ERROR_FACTURA_ID_INEXISTENTE;
		}
		if (menu == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_MENU_INEXISTENTE;
		}
		if (linea == null) {
			transaction.rollback();
			return Events.ERROR_FACTURA_LINEA_INEXISTENTE;
		}

		int cantidad = info.getCantidad();
		if (linea.getCantidad() < cantidad)
			cantidad = linea.getCantidad();
		menu.setStock(menu.getStock() + cantidad);
		daoM.editar(menu);

		info.setCantidad(linea.getCantidad() - cantidad);
		if (info.getCantidad() <= 0)
			daoFM.desvincular(info);
		else
			daoFM.editarCantidadMenu(info);

		factura.setImporte(factura.getImporte() - (cantidad * linea.getPrecio()));
		daoF.cerrar(factura);

		res = 0;
		transaction.commit();

		return res;
	}

	@Override
	public Collection<TFactura> mostrarTodosPorEmpleado(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();

		TEmpleado emp = FactoriaIntegracion.getInstance().generarDAOEmpleado().leerUno(id);

		if (emp == null) {
			transaction.rollback();
			return null;
		}

		Collection<TFactura> res = daoFactura.leerTodosPorEmpleado(id);
		transaction.commit();
		return res;
	}

	@Override
	public Collection<TFactura> mostrarTodosPorMesa(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();
		DAOFactura daoFactura = FactoriaIntegracion.getInstance().generarDAOFactura();

		TMesa mesa = FactoriaIntegracion.getInstance().generarDAOMesa().leerPorId(id);

		if (mesa == null) {
			transaction.rollback();
			return null;
		}

		Collection<TFactura> res = daoFactura.leerTodosPorMesa(id);
		transaction.commit();
		return res;
	}

	@Override
	public TFactura leerUnoPorId(Integer id) {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction transaction = tManager.newTransaction();
		transaction.start();

		TFactura res = FactoriaIntegracion.getInstance().generarDAOFactura().leerUno(id);
		if (res == null) {
			transaction.rollback();
		} else {
			transaction.commit();
		}
		return res;
	}

}