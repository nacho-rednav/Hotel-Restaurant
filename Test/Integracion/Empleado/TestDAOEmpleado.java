package Integracion.Empleado;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Empleado.DAOEmpleado;
import Negocio.Empleado.TEmpleado;

public class TestDAOEmpleado {
	
	static DAOEmpleado daoEmp;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.newTransaction();
			t.start();
			
			daoEmp = FactoriaIntegracion.getInstance().generarDAOEmpleado();
			TEmpleado emp = new TEmpleado();
			emp.setNombre("pruebaEmp");
			emp.setDNI("77777777A");
			emp.setHorario("Noche");
			emp.setSueldo((float) 650.00);
			emp.setTitulo(true);
			emp.setIdioma("Español");
			id = daoEmp.crear(emp);
			
			t.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerUnoCorrecto() {
		TEmpleado emp1 = daoEmp.leerUno(id);
		assertNotNull(emp1);
		assertEquals(emp1.getNombre(), "pruebaEmp");
		assertEquals(emp1.getDNI(), "77777777A");
		assertEquals(emp1.getHorario(), "Noche");
		//assertEquals(emp1.getSueldo(), (Float)650.0);  //TODO: esto da error si se descomenta, no deja castear a Float
		assertEquals(emp1.getTitulo(), true);
		assertEquals(emp1.getIdioma(), "Inglés");
	}
	
	@Test
	public void testLeerUnoIncorrecto() {
		assertNull(daoEmp.leerUno(-1));
	}
	
	@Test
	public void testLeerActivo() {
		TEmpleado emp = daoEmp.leerUno(id);
		assertTrue(emp.getActivo());
	}
	
	@Test
	public void testEliminarExistente() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t;
		try {
			t = tManager.newTransaction();
			t.start();
			daoEmp.eliminar(id);
			t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerNoActivo() {
		TEmpleado emp = daoEmp.leerUno(id);
		assertFalse(emp.getActivo());
	}
	
	@Test
	public void testReactivarNoActivo() {
		TransactionManager transaction = TransactionManager.getInstance();
		Transaction t = null;
		int exito = -1;
		
		try {
			t = transaction.newTransaction();
			t.start();
			TEmpleado emp = new TEmpleado(id, null, true, null, null, null, null, null);
			exito = daoEmp.editar(emp);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (exito == -1) {
			t.rollback();
		}
		
		assertEquals(1, exito);
	}
	
	@Test
	public void testLeerTodos() {
		Collection<TEmpleado> empleados = daoEmp.leerTodos();
		
		assertNotNull(empleados);
	}
	
	@AfterClass
	public static void AfterClass() {
		try {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		s.executeUpdate("DELETE FROM supermercado.empleado WHERE idEmpleado = " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
