package Negocio.Mesa;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.FactoriaNegocio.FactoriaSA;

public class TestSAMesa {
	static SAMesa saMesa;
	static int id, capacidad;

	@BeforeClass
	public static void BeforeClass() {
		Transaction t = TransactionManager.getInstance().newTransaction();
		t.start();
		capacidad = 6;
		saMesa = FactoriaSA.getInstance().generarSAMesa();
		try {
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM supermercado.mesa WHERE numero = 1;");			
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		id = saMesa.crear(new TMesa(1, true, capacidad, true));
	}
	
	@After
	public void reactivaMesaPruebas(){
		saMesa.crear(new TMesa(1, true, capacidad, true));
	}
	
	@Test
	public void muestraCorrecto(){
		TMesa m = saMesa.mostrarUno(id);
		assertEquals(id, (int)m.getId());
		assertEquals(capacidad, (int)m.getCapacidad());
		assertTrue(m.getActivo());
	}
	
	@Test
	public void testCrearMesaNumRepetido() {
		TMesa mesa = new TMesa(1, true, 6, true);

		assertEquals(-1, (int)saMesa.crear(mesa));
	}
	
	@Test
    public void testEliminaCorrecto() {		
        assertNotEquals(-1, (int)saMesa.eliminar(id));
        assertEquals(-1, (int)saMesa.eliminar(id));
    }
	
	@Test
    public void testEliminaIdIncorrecto() {		
        assertEquals(-1, (int)saMesa.eliminar(-1));
    }
	
	@Test
	public void testModificarCorrecto() {
		int res = saMesa.editar(new TMesa(id, 1, true, 20, true));
		assertNotEquals(-1, res);
		assertEquals(20, (int)saMesa.mostrarUno(id).getCapacidad());
		capacidad = 20;
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
