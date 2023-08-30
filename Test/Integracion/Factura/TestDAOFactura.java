package Integracion.Factura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Factura.DAOFactura;
import Negocio.Factura.TFactura;

public class TestDAOFactura {
	
	static DAOFactura daofactura;
	Transaction tran;
	static int id, idEmpleado, idmesa;
	static Float importe;
	static Date fecha;
	
	@BeforeClass
	public static void BeforeClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		importe = null;
		idEmpleado = 1;
		idmesa = 1;
		fecha = new Date(Calendar.getInstance().getTimeInMillis());
		try {
			daofactura = FactoriaIntegracion.getInstance().generarDAOFactura();
			TFactura factura = new TFactura(1, idmesa, idEmpleado, importe, null, true);
			id = daofactura.abrir(factura);
			
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
	}
	
	@Before
	public void startTansaction(){
		tran = TransactionManager.getInstance().newTransaction();
		tran.start();
	}
	
	@After
	public void deleteTransaction(){
		tran = null;
	}
	
	@Test
	public void cerrarCorrrecto(){
		int res;
		try{
			importe = 19.5f;
			res = daofactura.cerrar(new TFactura(id, idmesa, idEmpleado, importe, fecha, false));
			assertEquals(res, id);
			assertFalse(daofactura.leerUno(id).getActivo());
			assertEquals(daofactura.leerUno(id).getImporte(), importe, 0.5f);
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void editaCorrecto(){
		try{
			idmesa = 3;
			daofactura.editar(new TFactura(id, idmesa, idEmpleado, null, null, true));
			assertEquals((int)daofactura.leerUno(id).getIdMesa(), idmesa);
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerUnoCorrecto() {
		try{
			TFactura factura = daofactura.leerUno(id);
			assertNotNull(factura);
			assertEquals(factura.getImporte(), importe, 0.5f); // para comparar floats el ultimo valor es la diferencia amxima que puede haber entre los valores
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void LeerTodos() {
		Collection<TFactura> lista = daofactura.leerTodos();
		assertTrue((lista.size() > 0));
	}
	
	@Test
	public void LeerTodosPorMesa() {
		Collection<TFactura> lista = daofactura.leerTodosPorMesa(idmesa);
		assertTrue((lista.size() > 0));
	}
	
	@Test
	public void LeerTodosPorMEmpleado() {
		Collection<TFactura> lista = daofactura.leerTodosPorEmpleado(idEmpleado);
		assertTrue(lista.size() > 0);
	}
	
	
	@AfterClass
	public static void AfterClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		try {
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		s.executeUpdate("DELETE FROM supermercado.factura WHERE idfactura= " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
	}
	
}
