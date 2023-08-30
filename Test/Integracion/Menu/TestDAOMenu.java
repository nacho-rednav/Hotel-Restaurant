package Integracion.Menu;

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
import Integracion.Menu.DAOMenu;
import Negocio.Menu.TMenu;

public class TestDAOMenu {
	static DAOMenu daoMenu;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.newTransaction();
			t.start();
			
			daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
			TMenu menu = new TMenu();
			menu.setNombre("pruebaMenu");
			menu.setPrecio((float)15.50);
			menu.setStock(50);
			id = daoMenu.crear(menu);
			
			t.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerUnoCorrecto() {
		TMenu menu1 = daoMenu.leerUno(id);
		assertNotNull(menu1);
		assertEquals(menu1.getNombre(), "pruebaMenu");
		//assertEquals(menu1.getPrecio(), (Float)15.50); //TODO: no deja castear a Float
		assertEquals(menu1.getStock(),(Integer) 50);
	}
	
	@Test
	public void testLeerUnoIncorrecto() {
		assertNull(daoMenu.leerUno(-1));
	}
	
	@Test
	public void testLeerActivo() {
		TMenu menu = daoMenu.leerUno(id);
		assertTrue(menu.getActivo());
	}
	
	@Test
	public void testEliminarExistente() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t;
		try {
			t = tManager.newTransaction();
			t.start();
			daoMenu.eliminar(id);
			t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerNoActivo() {
		TMenu menu = daoMenu.leerUno(id);
		assertFalse(menu.getActivo());
	}
	
	@Test
	public void testReactivarNoActivo() {
		TransactionManager transaction = TransactionManager.getInstance();
		Transaction t = null;
		int exito = -1;
		
		try {
			t = transaction.newTransaction();
			t.start();
			TMenu menu = new TMenu(id, true, null, null, null);
			exito = daoMenu.editar(menu);
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
		Collection<TMenu> menus = daoMenu.leerTodos();
		
		assertNotNull(menus);
	}
	
	
	
	@AfterClass
	public static void AfterClass() {
		try {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		s.executeUpdate("DELETE FROM supermercado.menu WHERE idMenu = " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
