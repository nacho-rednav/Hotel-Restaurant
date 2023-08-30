package Negocio.Factura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Factura.DAOFactura;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.SAMenu;
import Negocio.Menu.TMenu;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSAFactura {
	
	static SAFactura saFactura;
	static SAMesa saMesa;
	static SAEmpleado saEmpleado;
	static DAOFactura daoFactura;
	static SAMenu saMenu;
	static int id, idEmpleado, idmesa, idmesa2, idmenu;
	static int capacidad, cantidad;
	static float importe, precio;

	@BeforeClass
	public static void BeforeClass() {
		Transaction t = TransactionManager.getInstance().newTransaction();
		t.start();
		importe = 0f;
		capacidad = 6;
		saFactura = FactoriaSA.getInstance().generarSAFactura();
		saMesa = FactoriaSA.getInstance().generarSAMesa();
		saEmpleado = FactoriaSA.getInstance().generarSAEmpleado();
		saMenu = FactoriaSA.getInstance().generarSAMenu();
		precio = 10.5f;
		cantidad = 3;
		
		TMenu menu = new TMenu(77, true, "pruebaMenu", precio, 25);
		idmenu = saMenu.crear(menu);
		TMesa mesa = new TMesa(550, true, capacidad, true);
		idmesa = saMesa.crear(mesa); 
		idEmpleado = saEmpleado.crear(new TEmpleado("XXXXXX", true, (float) 3.69, "222222222" ,"horario1", "Español", true));
		id = saFactura.abrir(new TFactura(idmesa, idEmpleado, importe, null, true));
	}
	
	@Test
	public void amuestraCorrecto(){
		TFactura m = saFactura.leerUnoPorId(id);
		assertEquals(id, (int)m.getId());
		assertEquals(idEmpleado, (int)m.getIdEmpleado());
		assertEquals(idmesa, (int)m.getIdMesa());
		assertTrue(m.getActivo());
	}
	
	@Test
	public void btestCrearFacturaYaActiva() {
		TFactura f = new TFactura(idmesa, idEmpleado, importe, null, true);

		assertNotEquals(0, (int)saFactura.abrir(f));
	}
	
	@Test
	public void ctestModificarCorrecto() {
		idmesa2 = saMesa.crear(new TMesa(551, true, capacidad, true)); 
		int res = saFactura.editar(new TFactura(id, idmesa2, idEmpleado, importe, null, true));
		assertNotEquals(-1, res);
		assertEquals(idmesa2, (int)saFactura.leerUnoPorId(id).getIdMesa());
	}
	
	@Test
	public void dtestMesasBienModificadas(){
		assertTrue(saMesa.mostrarUno(idmesa).getDisponibilidad());
		assertFalse(saMesa.mostrarUno(idmesa2).getDisponibilidad());
	}
	
	@Test
	public void etestAniadirMenu(){
		int res = saFactura.aniadirMenus(new TLineaDePedido(idmenu, cantidad, id, precio));
		assertEquals(0, res);
	}
	
	@Test
	public void eztestAniadirMenuSinStock(){
		int res = saFactura.aniadirMenus(new TLineaDePedido(idmenu, 1000, id, precio));
		assertNotEquals(0, res);
	}
	
	@Test
	public void ftestDevolverMenusFacturaNoCerrada(){
		int res = saFactura.devolverMenus(new TLineaDePedido(idmenu, 1, id, precio));
		assertNotEquals(0, res);
	}
	
	@Test 
	public void gtestEliminarMenu(){
		int res = saFactura.eliminarMenus(new TLineaDePedido(idmenu, 1, id, precio));
		assertEquals(0, res);
		cantidad--;
	}
	
	@Test
    public void htestEliminaCorrecto() {		
        assertNotEquals(-1, (int)saFactura.cerrar(id));
        TFactura f = saFactura.leerUnoPorId(id);
        assertEquals(cantidad * precio, (float)f.getImporte(), 1f);
        assertFalse(f.getActivo());
        assertNotNull(f.getHoraCobro());
    }
	
	@Test
	public void itestDevolverMenus(){
		int res = saFactura.devolverMenus(new TLineaDePedido(idmenu, 1, id, precio));
		assertEquals(0, res);
	}
	
	@Test
	public void jtestDevolverMenuInexistente(){
		int res = saFactura.devolverMenus(new TLineaDePedido(-1, 1, id, precio));
		assertNotEquals(0, res);
	}
	
	@AfterClass
	public static void AfterClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		try {
		Connection connection = (Connection) t.getResource();
		Statement s = connection.createStatement();
		//Tenemos que borrar todas las entidades que se crean
		s.executeUpdate("DELETE FROM supermercado.factura WHERE idfactura = " + id + ";");
		s.executeUpdate("DELETE FROM supermercado.mesa WHERE idmesa = " + idmesa + ";");
		s.executeUpdate("DELETE FROM supermercado.mesa WHERE idmesa = " + idmesa2 + ";");
		s.executeUpdate("DELETE FROM supermercado.menu WHERE idmenu = " + idmenu + ";");
		s.executeUpdate("DELETE FROM supermercado.empleado WHERE idempleado = " + idEmpleado + ";");
		s.executeUpdate("DELETE FROM supermercado.facturamenu WHERE idmenu = " + idmenu + " AND idfactura = "+ id + ";");
		t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
	}
}
