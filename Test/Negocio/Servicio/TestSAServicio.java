package Negocio.Servicio;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.Habitacion;
import Negocio.Habitacion.TBasica;
import Negocio.Habitacion.THabitacion;
import Negocio.Habitacion.TSuite;
import Presentacion.Controller.Events;

public class TestSAServicio {
	
	static SAServicio saServ;
	static int id, id2;
	static String tipo, descripcion;
	static float precio;
	
	@BeforeClass
	public static void BeforeClass() {
		//TODO: falta por hacer, no supe cómo hacerlo bien
	}
	
	@Test
	public void aaeliminaCorrecto(){
		assertEquals((int)saServ.eliminar(id), id);
        assertEquals(Events.ERROR_SERVICIO_YA_DADO_DE_BAJA, (int)saServ.eliminar(id));
	}
	@Test
    public void atestEliminaIdIncorrecto() {		
        assertEquals(Events.ERROR_SERVICIO_NO_EXISTE, (int)saServ.eliminar(-1));
    }
	@Test
	public void breactivaServicio(){
		assertEquals((int)saServ.crear(new TServicio(tipo, descripcion, precio, true)), id);
	}
	
	@Test
	public void cmuestraCorrecto(){
		TServicio s = saServ.mostrarPorId(id);
		assertEquals(id, (int)s.getId());
		assertEquals(tipo, s.getTipo());
		assertEquals(descripcion, s.getDescripcion());
		assertEquals(precio, (float)s.getPrecio());
		assertTrue(s.getActivo());
	}
	
	@Test
	public void dtestCrearServicioTipoRepetido() { //TODO: no puede haber tipos repetidos?
		TServicio servicio = new TServicio(4, tipo, descripcion, precio, true);
		assertEquals(Events.ERROR_SERVICIO_TIPO_YA_EXISTE, (int)saServ.crear(servicio));
	}
	
	@Test
	public void etestModificarCorrecto() {
		int res = saServ.editar(new TServicio(id, "piscina", "entrada a la piscina", (float)99.99, true));
		assertEquals(Events.MODIFICAR_SERVICIO_OK, res);
		assertEquals("piscina", saServ.mostrarPorId(id).getTipo());
		assertEquals("entrada a la piscina", saServ.mostrarPorId(id).getDescripcion());
		assertEquals((float)99.99, (float)saServ.mostrarPorId(id).getPrecio());
		tipo = "piscina";
		descripcion = "entrada a la piscina";
		precio = (float)99.99;
	}
	
	@Test
	public void ftestModificarNumExiste(){
		String tipo2 = "tenis";
		id2 = saServ.crear(new TServicio(tipo2, "ejemplo", (float)80.75, true));
		assertEquals((int)saServ.editar(new TServicio(id, tipo2, "ejemplo2", (float)15.00, true)), Events.ERROR_SERVICIO_TIPO_YA_EXISTE);
	}
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		try {
		em.getTransaction().begin();
		Servicio serv1 = em.find(Servicio.class, id);
		Servicio serv2 = em.find(Servicio.class, id2);
		em.remove(serv1);
		em.remove(serv2);
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
