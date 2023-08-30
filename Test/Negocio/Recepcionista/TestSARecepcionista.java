package Negocio.Recepcionista;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.Habitacion;
import Negocio.Habitacion.THabitacion;
import Negocio.Habitacion.TSuite;
import Negocio.Recepcionista.SARecepcionistaImp;
import Negocio.Servicio.Servicio;
import Negocio.Servicio.TServicio;
import Presentacion.Controller.Events;
public class TestSARecepcionista {
	
	static SARecepcionista saRec;
	static int id, id2, numRecepcionista;
	static String nombre;
	static float salario;
	
	@BeforeClass
	public static void BeforeClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		nombre = "Manolo";
		salario = (float) 777.50;
		numRecepcionista = 68;
		saRec = FactoriaSA.getInstance().generarSARecepcionista();
		List<Recepcionista> check_numero = null;
		do{
			check_numero = em.createNamedQuery("Negocio.Recepcionista.Recepcionista.findBynumRecepcionista", Recepcionista.class).setParameter("numRecepcionista", numRecepcionista).getResultList();
			numRecepcionista++;
		} while(check_numero != null && !check_numero.isEmpty());
		numRecepcionista--;
		em.getTransaction().rollback();
		em.close();
		id = saRec.crear(new TRecepcionista(numRecepcionista, nombre, salario, true));
	}
	
	@Test
	public void aaeliminaCorrecto(){
		assertEquals((int)saRec.eliminar(id), id);
        assertEquals(Events.ERROR_RECEPCIONISTA_YA_DADO_DE_BAJA, (int)saRec.eliminar(id));
	}
	@Test
    public void atestEliminaIdIncorrecto() {		
        assertEquals(Events.ERROR_RECEPCIONISTA_NO_EXISTE, (int)saRec.eliminar(-1));
    }
	@Test
	public void breactivaRecepcionista(){
		assertEquals((int)saRec.crear(new TRecepcionista(numRecepcionista, nombre, salario, true)), id);
	}
	
	@Test
	public void cmuestraCorrecto(){
		TRecepcionista r = saRec.mostrarPorId(id);
		assertEquals(id, (int)r.getId());
		assertEquals(numRecepcionista, (int)r.getNumRecepcionista());
		assertEquals(nombre, (String)r.getNombre());
		assertEquals(salario, (float)r.getSalario()); 
		assertTrue(r.getActivo());
	}
	
	@Test
	public void dtestCrearRecepcionistaNumRepetido() {
		TRecepcionista recepcionista = new TRecepcionista(4, numRecepcionista, "Juan", (float)555.59, true);
		assertEquals(Events.ERROR_RECEPCIONISTA_NUM_YA_EXISTE, (int)saRec.crear(recepcionista));
	}
	
	@Test
	public void etestModificarCorrecto() {
		int res = saRec.editar(new TRecepcionista(id, 54, "Pedro", (float)999.99, true));
		assertEquals(Events.MODIFICAR_RECEPCIONISTA_OK, res);
		assertEquals(54, (int)saRec.mostrarPorId(id).getNumRecepcionista());
		assertEquals("Pedro", saRec.mostrarPorId(id).getNombre());
		assertEquals((float)999.99, (float)saRec.mostrarPorId(id).getSalario());
		numRecepcionista = 54;
		nombre = "Pedro";
		salario = (float)999.99;
	}
	
	@Test
	public void ftestModificarNumExiste(){
		int numRecepcionista2 = 7;
		id2 = saRec.crear(new TRecepcionista(numRecepcionista2, "Pablo", (float)80.75, true));
		assertEquals((int)saRec.editar(new TRecepcionista(id, numRecepcionista2, "ejemplo2", (float)15.00, true)), Events.ERROR_RECEPCIONISTA_NUM_YA_EXISTE);
	}
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		try {
		em.getTransaction().begin();
		Recepcionista rec1 = em.find(Recepcionista.class, id);
		Recepcionista rec2 = em.find(Recepcionista.class, id2);
		em.remove(rec1);
		em.remove(rec2);
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
