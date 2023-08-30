package Integracion.Mesa;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Mesa.DAOMesa;
import Negocio.Mesa.TMesa;


public class TestDAOMesa {

	static DAOMesa daoMesa;
	Transaction tran;
	static int id, capacidad;
	
	@BeforeClass
	public static void BeforeClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		capacidad = 6;
		try {
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM supermercado.mesa WHERE numero = 1;");
			daoMesa = FactoriaIntegracion.getInstance().generarDAOMesa();
			TMesa mesa = new TMesa();
			mesa.setNumMesa(1);
			mesa.setCapacidad(capacidad);
			id = daoMesa.crear(mesa);
			
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
	public void testLeerUnoCorrecto() {
		try{
			TMesa mesa1 = daoMesa.leerPorId(id);
			assertNotNull(mesa1);
			assertEquals((int)mesa1.getNumMesa(), 1);
			assertEquals((int)mesa1.getCapacidad(), capacidad);
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLeerUnoCorrectoPorNum() {
		try{
			TMesa mesa1 = daoMesa.leerPorNum(1);
			assertNotNull(mesa1);
			assertEquals((int)mesa1.getId(), id);
			assertEquals((int)mesa1.getCapacidad(), capacidad);
			assertTrue(mesa1.getDisponibilidad());
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLeerUnoIncorrectoPorNum() {
		try{
			assertNull(daoMesa.leerPorNum(-1));
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerUnoIncorrecto() {
		try{
			assertNull(daoMesa.leerPorId(-1));
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void eliminaCorrrecto(){
		int res;
		try{
			res = daoMesa.eliminar(id);
			assertEquals(res, id);
			assertFalse(daoMesa.leerPorId(id).getActivo());
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void eliminaIncorrecto(){
		int res;
		try{
			res = daoMesa.eliminar(-1);
			assertEquals(res, -1);
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void editaCorrecto(){
		try{
			daoMesa.editar(new TMesa(id, 1, true, 20, false));
			assertEquals((int)daoMesa.leerPorId(id).getCapacidad(), 20);
			assertFalse(daoMesa.leerPorId(id).getDisponibilidad());
			capacidad = 20;
			tran.commit();
		}catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void AfterClass() {
		try {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		s.executeUpdate("DELETE FROM supermercado.mesa WHERE idmesa = " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
