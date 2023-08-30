package Turno;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.AfterClass;

import Negocio.Cliente.SACliente;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.SATurno;
import Negocio.Turno.TComida;
import Negocio.Turno.TTurno;
import Negocio.Turno.Turno;
import Presentacion.Controller.Events;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSATurno {
	public static SATurno saTurno;
	public static SACliente saCliente;
	public static Integer idTurno, idTurno2, capacidad;
	public static Date dia;
	public static Float precio, costeServicio, costeComplemento;
	public static String menuDia, tipo;
	public static Boolean complementoCafe, complementoZumo, complementoFruta;
	
	@BeforeClass
	public static void Beforeclass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		dia = new Date();
		precio = (float) 30;
		capacidad = 10;
		menuDia = "Menu Navidad";
		costeServicio = (float) 10.5;
		saTurno = FactoriaSA.getInstance().generarSATurno();
		List<Turno> check_dia = null;
		do {
			check_dia = em.createNamedQuery("Negocio.Habitacion.Habitacion.findBydia", Turno.class).setParameter("dia", dia).getResultList();
		} while (check_dia != null && !check_dia.isEmpty());
		em.getTransaction().rollback();
		em.close();
		idTurno = saTurno.crearTurno(new TComida(dia, capacidad, precio, true, costeServicio, menuDia));
	}
	
	@Test
	public void aEliminarTurnoCorrecto() {
		assertEquals((Integer)saTurno.eliminarTurno(idTurno), idTurno);
	}
	
	@Test
	public void bReactivarTurnoCorrecto() {
		assertNotEquals(saTurno.crearTurno(new TComida(idTurno, dia, capacidad, precio, true, costeServicio, menuDia)), idTurno);
	}

	@Test
	public void cBuscarTurnoCorrecto() {
		TTurno tturno = saTurno.buscarTurno(idTurno);
		assertEquals(idTurno, (Integer)tturno.getId());
		assertTrue(tturno.getActivo());
	}
	
	@Test
	public void dEditarTurnoCorrecto() {
		Float p = (float) 15;
		int res = saTurno.editarTurno(new TComida(idTurno, dia, 11, p, true, (float) 12, "nuevoMenu"));
		assertEquals(Events.MODIFICAR_TURNO_OK, res);
	}
	
	@Test
	public void gBuscarTodosTurnos() {
	    List<TTurno> turno = saTurno.buscarTodosTurno();
	    assertTrue(turno.size() > 0);
	}

	
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		try {
		em.getTransaction().begin();
		Turno tur1 = em.find(Turno.class, idTurno);
		Turno tur2 = em.find(Turno.class, idTurno2);
		em.remove(tur1);
		em.remove(tur2);
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
