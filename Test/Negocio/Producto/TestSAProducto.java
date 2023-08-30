package Negocio.Producto;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSAProducto {
	
	static SAProducto saProd;
	static int id;

	@BeforeClass
	public static void BeforeClass() {
		Transaction t = TransactionManager.getInstance().newTransaction();
		t.start();
		saProd = FactoriaSA.getInstance().generarSAProducto();
		
		try {
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM supermercado.producto WHERE nombre LIKE 'SAPROD';");			
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		id = saProd.crear(new TProducto(true, "SAING"));
	}
	
	@After
	public void reactivaProductoPruebas() {
		saProd.crear(new TProducto(true, "SAING"));
	}
	
	@Test
	public void aTestMuestraProductoCorrecto() {
		TProducto prod = saProd.mostrarUno(id);
		
		assertEquals(id, (int)prod.getId());
		assertTrue(prod.getActivo());
	}
	
	@Test
	public void btestCrearProductoExistente() {
		TProducto producto = new TProducto(12, true, "EnsaladaTest");	
		
		assertEquals(-1, (int) saProd.crear(producto));
	}
	
	@Test
    public void ctestEliminaActivo() {
	     assertNotEquals(-1, (int)saProd.eliminar(id));
	     assertEquals(-1, (int)saProd.eliminar(id));
    }
	
	@Test
    public void dTestEliminarIncorrecto() {
        assertEquals(-1, (int)saProd.eliminar(-1));
    }
	
	@Test
	public void eTestModificarCorrecto() {
		int res = saProd.editar(new TProducto(id, true, "SAPROD2"));
		assertNotEquals(-1, res);
		assertEquals("SAPROD2", saProd.mostrarUno(id).getNombre());
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
