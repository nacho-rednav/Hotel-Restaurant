package Negocio.Habitacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.SAHabitacion;
import Presentacion.Controller.Events;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSAHabitacion {
	
	static SAHabitacion saHabitacion;
	static int id, id2, planta, numero;

	@BeforeClass
	public static void BeforeClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		planta = 3;
		numero = 5000;
		saHabitacion = FactoriaSA.getInstance().generarSAHabitacion();
		List<Habitacion> check_numero = null;
		do{
			check_numero = em.createNamedQuery("Negocio.Habitacion.Habitacion.findBynumero", Habitacion.class).setParameter("numero", numero).getResultList();
			numero++;
		} while(check_numero != null && !check_numero.isEmpty());
		numero--;
		em.getTransaction().rollback();
		em.close();
		id = saHabitacion.crearHabitacion(new TSuite(planta, numero, true, 90f));
	}
	
	@Test
	public void aaeliminaCorrecto(){
		assertEquals((int)saHabitacion.eliminarHabitacion(id), id);
        assertEquals(Events.ERROR_HABITACION_YA_DADA_DE_BAJA, (int)saHabitacion.eliminarHabitacion(id));
	}
	@Test
    public void atestEliminaIdIncorrecto() {		
        assertEquals(Events.ERROR_HABITACION_NO_EXISTE, (int)saHabitacion.eliminarHabitacion(-1));
    }
	@Test
	public void breactivaMesa(){
		assertEquals((int)saHabitacion.crearHabitacion(new TSuite(planta, numero, true, 90f)), id);
	}
	
	@Test
	public void cmuestraCorrecto(){
		THabitacion h = saHabitacion.buscarUnaHabitacionPorId(id);
		assertEquals(id, (int)h.getId());
		assertEquals(planta, (int)h.getPlanta());
		assertEquals(numero, (int)h.getNumero());
		assertTrue(h.getActivo());
	}
	
	@Test
	public void dtestCrearMesaNumRepetido() {
		THabitacion habitacion = new TSuite(4, numero, true, 33f);
		assertEquals(Events.ERROR_HABITACION_NUM_YA_EXISTE, (int)saHabitacion.crearHabitacion(habitacion));
	}
	
	
	
	@Test
	public void etestModificarCorrecto() {
		int res = saHabitacion.editarHabitacion(new TSuite(id, 7, 122, true, 56f));
		assertEquals(Events.MODIFICAR_HABITACION_OK, res);
		assertEquals(7, (int)saHabitacion.buscarUnaHabitacionPorId(id).getPlanta());
		assertEquals(122, (int)saHabitacion.buscarUnaHabitacionPorId(id).getNumero());
		numero = 122;
		planta = 7;
	}
	
	
	@Test
	public void ftestModificarNumExiste(){
		int num2 = 60001;
		id2 = saHabitacion.crearHabitacion(new TBasica(8, num2, true, 43f));
		assertEquals((int)saHabitacion.editarHabitacion(new TSuite(id, 4, num2, true, 33f)), Events.ERROR_HABITACION_NUM_YA_EXISTE);
	}
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		try {
		em.getTransaction().begin();
		Habitacion habi = em.find(Habitacion.class, id);
		Habitacion hab2 = em.find(Habitacion.class, id2);
		em.remove(habi);
		em.remove(hab2);
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
