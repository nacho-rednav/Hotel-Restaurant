package Negocio.Reserva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.Cliente.Cliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Habitacion.Habitacion;
import Negocio.Habitacion.TSuite;
import Negocio.Recepcionista.Recepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Negocio.Servicio.Servicio;
import Negocio.Servicio.TServicio;
import Presentacion.Controller.Events;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSAReserva {
	
	static SAReservaImp saRes;
	static int id, idCli, idRec, idCli2;
	static ArrayList<Integer>idHabs, idServs;
	static int codigo;
	
	@BeforeClass
	public static void BeforeClass() {
		saRes = new SAReservaImp();
		idHabs = new ArrayList<Integer>();
		idServs = new ArrayList<Integer>();
		idCli = FactoriaSA.getInstance().generarSACliente().crearCliente(new TCliente(2, true, "testReserva", "w"));
		idRec = FactoriaSA.getInstance().generarSARecepcionista().crear(new TRecepcionista(19999999, "testReserva", 100f, true));
		int num =199999999;
		for(int i = 0; i < 3; i++){
			int idH = FactoriaSA.getInstance().generarSAHabitacion().crearHabitacion(new TSuite(3, num, true, 90f));
			idHabs.add(idH);
			num += 1;
		}
		String tipo = "prueba";
		for(int i = 0; i < 3; i++){
			int idS = FactoriaSA.getInstance().generarSAServicio().crear(new TServicio(tipo, "pruebaReserva", 3f, true));
			idServs.add(idS);
			tipo += "a";
		}
		codigo = 1920393;
	}
	
	@Test
	public void atestCreaCorrecto(){
		id = saRes.crearReserva(new TReserva(0, codigo, 8, idCli, idRec, 3, new Date(), true));
		TReserva res = saRes.mostrarReserva(id);
		assertEquals((int)res.getCodigo(), codigo);
		assertEquals((int)res.getId_Cliente(), idCli);
		assertEquals((int)res.getId_Recepcionista(), idRec);
		assertEquals((int)res.getNumDias(), 8);
	}
	
	@Test
	public void aztestNoRepiteCodigo(){
		int res = saRes.crearReserva(new TReserva(0, codigo, 8, idCli, idRec, 3, new Date(), true));
		assertEquals(res, Events.ERROR_RESERVA_CODIGO_YA_EXISTE);
	}
	
	@Test
	public void btestEditaCorrecto(){
		idCli2 = FactoriaSA.getInstance().generarSACliente().crearCliente(new TCliente(2, true, "testReserva2", "w"));
		int res = saRes.editarReserva(new TReserva(id, codigo, 8, idCli2, idRec, 3, new Date(), true));
		assertEquals(Events.MODIFICAR_RESERVA_OK, res);
		TReserva r = saRes.mostrarReserva(id);
		assertEquals(idCli2, (int)r.getId_Cliente());
	}
	
	@Test
	public void cdesvinculaHabitacionCorrecto(){
		TReservaCompleta tfc = saRes.mostrarReservaCompleta(id);
		int tam = tfc.getHabitaciones().size();
		int idHab = tfc.getHabitaciones().get(0).getId();
		int res = saRes.desvincularHabitacionReserva(new TLineaPedidoHabitacion(id, idHab));
		assertEquals(res, Events.DESVINCULAR_HABITACION_RESERVA_OK);
		tfc = saRes.mostrarReservaCompleta(id);
		assertEquals(tam-1, tfc.getHabitaciones().size());
	} 
	
	@Test
	public void etestCompraCorrecto(){
		ArrayList<Pair<TServicio, Date>> data = new ArrayList<Pair<TServicio, Date>>();
		for(Integer i : idServs){
			TServicio t = FactoriaSA.getInstance().generarSAServicio().mostrarPorId(i);
			data.add(new Pair<TServicio, Date>(t, new Date()));
		}
		TLineaPedidoServicios tlp = new TLineaPedidoServicios(id, data);
		int res = saRes.compraServicio(tlp);
		assertEquals(res, Events.COMPRA_SERVICIO_RESERVA_OK);
		TReservaCompleta tfc = saRes.mostrarReservaCompleta(id);
		assertEquals(tfc.getServicios().size(), idServs.size());
	}
	
	@Test
	public void ftestDevolucionCorrecto(){
		ArrayList<Pair<TServicio, Date>> data = new ArrayList<Pair<TServicio, Date>>();
		for(Integer i : idServs){
			TServicio t = FactoriaSA.getInstance().generarSAServicio().mostrarPorId(i);
			data.add(new Pair<TServicio, Date>(t, new Date()));
		}
		TLineaPedidoServicios tlp = new TLineaPedidoServicios(id, data);
		int res = saRes.devolucionServicio(tlp);
		assertEquals(res, Events.DEVOLUCION_SERVICIO_RESERVA_OK);
		TReservaCompleta tfc = saRes.mostrarReservaCompleta(id);
		assertEquals(tfc.getServicios().size(), 0);
	}
	
	@Test
	public void yeliminaCorrecto(){
		int res = saRes.eliminarReserva(id);
		assertEquals(res, Events.BAJA_RESERVA_OK);
		TReservaCompleta tfc = saRes.mostrarReservaCompleta(id);
		assertEquals(tfc.getHabitaciones().size(), 0);
		assertEquals(tfc.getServicios().size(), 0);
		assertFalse(tfc.getReserva().getActivo());
	}
	
	@Test
	public void zreactivaCorrecto(){
		int res = saRes.crearReserva(new TReserva(0, codigo, 8, idCli, idRec, 3, new Date(), true));
		assertEquals(res, id);
		TReserva t = saRes.mostrarReserva(id);
		assertTrue(t.getActivo());
	}
	
	@AfterClass
	public static void AfterClass(){
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, id);
		res.getHabitacion().clear();
		em.detach(res);
		em.getTransaction().commit();
		em.getTransaction().begin();
		Cliente cli = em.find(Cliente.class, idCli);
		Cliente cli2 = em.find(Cliente.class, idCli2);
		Recepcionista rec = em.find(Recepcionista.class, idRec);
		res = em.find(Reserva.class, id);
		em.remove(res);
		em.remove(cli);
		em.remove(cli2);
		em.remove(rec);
		for(Integer i : idHabs){
			Habitacion h = em.find(Habitacion.class, i);
			em.remove(h);
		}
		for(Integer i : idServs){
			Servicio s = em.find(Servicio.class, i);
			em.remove(s);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	
	
	
	
	
	
	
}
