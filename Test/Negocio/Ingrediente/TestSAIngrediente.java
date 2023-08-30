package Negocio.Ingrediente;

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
import Negocio.Ingrediente.TIngrediente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSAIngrediente {
	static SAIngrediente saIngr;
	static int id;

	@BeforeClass
	public static void BeforeClass() {
		Transaction t = TransactionManager.getInstance().newTransaction();
		t.start();
		saIngr = FactoriaSA.getInstance().generarSAIngrediente();
		
		try {
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM supermercado.ingrediente WHERE nombre LIKE 'SAING';");			
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		id = saIngr.crear(new TIngrediente(false, "SAING",
				true));
	}
	
	@After
	public void reactivaIngredientePruebas() {
		saIngr.crear(new TIngrediente(false, "SAING", true));
	}
	
	@Test
	public void aTestMuestraIngredienteCorrecto() {
		TIngrediente ing = saIngr.mostrarUno(id);
		assertEquals(id, (int)ing.getId());
		assertTrue(ing.getActivo());
	}
	
	@Test
	public void bTestCrearIngredienteExistente() {
		TIngrediente ingrediente = new TIngrediente(55, false, "SAING", true);

		assertEquals(-1, (int) saIngr.crear(ingrediente));
	}
	
	@Test
    public void cTestEliminaActivo() {
	       assertNotEquals(-1, (int)saIngr.eliminar(id));
	       assertEquals(-1, (int)saIngr.eliminar(id));
    }
	
	@Test
    public void dTestEliminarIncorrecto() {
		
        assertEquals(-1,saIngr.eliminar(-1));
    }
	
	@Test
	public void eTestModificarCorrecto() {
		int res = saIngr.editar(new TIngrediente(id, true, "SAING2", true));
		assertNotEquals(-1, res);
		assertEquals("SAING2", saIngr.mostrarUno(id).getNombre());
	}
	
	@AfterClass
	public static void AfterClass() {
		try {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		s.executeUpdate("DELETE FROM supermercado.ingrediente WHERE idIngrediente = " + id + ";");
		t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
