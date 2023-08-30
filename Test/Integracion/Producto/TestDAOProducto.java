package Integracion.Producto;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Producto.DAOProducto;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;

public class TestDAOProducto {

	static DAOProducto daoProd;
	Transaction tran;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		try {
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM supermercado.producto WHERE nombre = pruebaProd;");
			daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
			// TProducto prod = new TProducto(null, true, "pruebaProd");
			TProducto prod = new TProducto();
			prod.setActivo(true);
			prod.setNombre("pruebaProd");
			id = daoProd.crear(prod);
			
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
			TProducto prod1 = daoProd.leerUno(id);
			assertNotNull(prod1);
			assertEquals(prod1.getNombre(), "pruebaProd");
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerUnoIncorrecto() {
		try {
			assertNull(daoProd.leerUno(-1));
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerPorNombreCorrecto() {
		try{
			TProducto prod1 = daoProd.leerPorNombre("pruebaProd");
			assertNotNull(prod1);
			assertEquals((int) prod1.getId(), id);
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerPorNombreIncorrecto() {
		try{
			TProducto prod1 = daoProd.leerPorNombre("pruebaMalProd");
			assertNull(prod1);
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerTodos() {
		try{
			Collection<TProducto> productos = daoProd.leerTodos();
			assertNotNull(productos);
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testLeerActivo() { // TODO comprobar si queremos testear la lectura del activo
//		TProducto prod1 = daoProd.leerUno(id);
//		assertTrue(prod1.getActivo());
//	}
	
	@Test
	public void testEliminarExistente() {
		int res;
		try {
			res = daoProd.eliminar(id);
			assertEquals(res, id);
			assertFalse(daoProd.leerUno(id).getActivo());
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarNoExistente() {
		int res;
		try {
			res = daoProd.eliminar(-1);
			assertEquals(res, -1);
			tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEditarCorrecto() {
		try {
			daoProd.editar(new TProducto(id, true, "pruebaCambioNombre"));
			assertEquals(daoProd.leerUno(id).getNombre(), "pruebaCambioNombre");
			tran.commit();
		} catch(Exception e) {
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
		s.executeUpdate("DELETE FROM supermercado.producto WHERE idProducto = " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
