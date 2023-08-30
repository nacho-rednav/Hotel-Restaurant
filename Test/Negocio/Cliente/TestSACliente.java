package Negocio.Cliente;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.Habitacion;
import Negocio.Habitacion.TSuite;
import Presentacion.Controller.Events;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSACliente {
	
	static SACliente saCli;
	static int id;
	static String nombre, email;
	
	@BeforeClass
	public static void BeforeClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		saCli = FactoriaSA.getInstance().generarSACliente();
		nombre = "SACLI24Sacli";
		email = "email1";
		List<Cliente> check_nombre = null;
		do{
			check_nombre = em.createNamedQuery("Negocio.Cliente.Cliente.findBynombre", Cliente.class)
					.setParameter("nombre", nombre).getResultList();
			nombre+= "df";
		} while(check_nombre != null && !check_nombre.isEmpty());
		em.getTransaction().rollback();
		em.close();
		id = saCli.crearCliente(new TCliente(id, true, nombre, email));
		System.out.println(id);
	}
	

	@Test
	public void aaeliminaCorrecto(){
		assertEquals((int)saCli.eliminarCliente(id), id);
	}
	@Test
    public void atestEliminaIdIncorrecto() {		
        assertEquals(Events.ERROR_CLIENTE_NO_EXISTE, (int)saCli.eliminarCliente(-1));
    }
	@Test
	public void breactivaCliente(){
		assertEquals((int)saCli.crearCliente(new TCliente(id, true, nombre, email)), id);
	}
	
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		try {
		em.getTransaction().begin();
		Cliente client = em.find(Cliente.class, id);
		em.remove(client);
		em.getTransaction().commit();
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			em.close();
		}
	}
	
}
