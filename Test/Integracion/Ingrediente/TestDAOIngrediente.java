package Integracion.Ingrediente;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.Ingrediente.DAOIngredienteImp;
import Negocio.Ingrediente.TIngrediente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDAOIngrediente {

	static DAOIngrediente daoIngr;
	static int id;

	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.newTransaction();
			t.start();
			
			daoIngr = FactoriaIntegracion.getInstance().generarDAOIngrediente();
			TIngrediente ingr = new TIngrediente();
			ingr.setNombre("testIngrediente");
			ingr.setAlergeno(true);
			ingr.setActivo(true);

			id = daoIngr.crear(ingr);
			
			t.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void atestLeerUnoCorrecto() {
		try {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		
		t.start();
		TIngrediente ingr1 = daoIngr.leerUno(id);
		t.commit();
		
		assertNotNull(ingr1);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void btestLeerUnoPorNombre() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.newTransaction();
			
			t.start();
			TIngrediente ingr = daoIngr.leerUnoPorNombre("testIngrediente");
			t.commit();
			
			assertNotNull(ingr);
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
 	
	@Test
	public void ctestLeerUnoIncorrecto() {
		assertNull(daoIngr.leerUno(-1));
	}
	
	@Test
	public void dtestLeerActivo() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		
		try {
		t.start();
		TIngrediente ingr = daoIngr.leerUno(id);
		t.commit();
		
		assertTrue(ingr.getActivo());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void etestEliminarExistente() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t;
		
		try {
			t = tManager.newTransaction();
			t.start();
			daoIngr.eliminar(id);
			t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void ftestLeerNoActivo() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		
		try {
		t.start();
		TIngrediente ingr = daoIngr.leerUno(id);
		t.commit();
		
		assertFalse(ingr.getActivo());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void gtestReactivarNoActivo() {
		TransactionManager transaction = TransactionManager.getInstance();
		Transaction t = transaction.newTransaction();
		int exito = -1;
		
		try {
			t.start();
			TIngrediente ing = daoIngr.leerUno(id);
			ing.setActivo(true);
			exito = daoIngr.editar(ing);
			t.commit();
			assertEquals(id, exito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (exito == -1) {
			t.rollback();
		}	
	}
	
	@Test
	public void htestLeerTodos() {
		TransactionManager transaction = TransactionManager.getInstance();
		Transaction t = transaction.newTransaction();
		
		try {
			t.start();
			Collection<TIngrediente> ingredientes = daoIngr.leerTodos();
			t.commit();
		
			assertNotNull(ingredientes);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@AfterClass
	public static void AfterClass() {
		try {
			String consulta = "DELETE FROM supermercado.ingrediente WHERE idIngrediente = ?;";
			
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction t = tManager.newTransaction();
			Connection connection = (Connection) t.getResource();
			
			t.start();
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, id);
			
			if (ps.executeUpdate() == 1) {
				ps.executeUpdate("DELETE FROM supermercado.ingrediente WHERE idIngrediente = " + id + ";");
			}
			
			ps.close();
			t.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
