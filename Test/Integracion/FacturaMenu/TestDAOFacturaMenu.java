package Integracion.FacturaMenu;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Factura.DAOFactura;
import Integracion.FacturaMenu.DAOFacturaMenu;
import Integracion.Menu.DAOMenu;
import Integracion.Mesa.DAOMesa;
import Negocio.Empleado.TEmpleado;
import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaDePedido;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Menu.TMenu;
import Negocio.Mesa.TMesa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDAOFacturaMenu {
	
	static DAOFacturaMenu daofm;
	static int idfactura, idmenu, cantidad;
	static float precio;
	static TLineaDePedido info;
	Transaction tran;
	
	@BeforeClass
	public static void BeforeClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		t.start();
		
		try {
			
			idfactura = 1;
			idmenu = 1;
			cantidad = 10;
			precio = 15.5f;
			info = new TLineaDePedido(idfactura, null, idmenu, null);
			daofm = FactoriaIntegracion.getInstance().generarDAOFacturaMenu();
			daofm.vincular(new TLineaDePedido(idmenu, cantidad, idfactura, precio));
			
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
			e.printStackTrace();
		}
	}
	@Before
	public void startTansaction(){
		tran = TransactionManager.getInstance().newTransaction();
		tran.start();
	}
	
	@After
	public void deleteTransaction(){
		tran = null;
	}
	
	@Test
	public void testeditarCantidadMenuCorrecto() {
		try {
			
		cantidad = 5;
		daofm.editarCantidadMenu(new TLineaDePedido(idmenu, cantidad, idfactura, precio));
		assertEquals((int)daofm.leerUno(info).getCantidad(), cantidad);
		tran.commit();
		
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}	
	}
	

	@Test
	public void testleerMenusEnFacturaCorrecto() {
		try {
		
		Collection<TLineaDePedido> lpedido = daofm.leerMenusEnFactura(idfactura);
		assertTrue(lpedido.size() > 0);
		tran.commit();
		
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testleerFacturasPorMenuCorrecto() {
		try {
			
			Collection<TFactura> lpedido = daofm.leerFacturasPorMenu(idmenu);
			assertTrue(lpedido.size() > 0);
			tran.commit();
			
			} catch(Exception e) {
				tran.rollback();
				e.printStackTrace();
			}	
	}
	
	@Test
	public void testleerUnoCorrecto() {
		try {
		TLineaDePedido p = daofm.leerUno(info);
		assertNotNull(p);
		assertEquals(idmenu, (int)p.getIdMenu());
		assertEquals(idfactura, (int)p.getIdFactura());
		assertEquals(cantidad, (int)p.getCantidad());
		assertEquals(precio, (float)p.getPrecio(), 1f);

		tran.commit();
		} catch(Exception e) {
			tran.rollback();
			e.printStackTrace();
		}	
	}
	
	@AfterClass
	public static void AfterClass() {
		TransactionManager tManager = TransactionManager.getInstance();
		Transaction t = tManager.newTransaction();
		Connection connection = (Connection) t.getResource();
		try {
			String consulta = "DELETE FROM supermercado.facturamenu WHERE idmenu = ? AND idfactura = ?;";
			
			t.start();
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, idmenu);
			ps.setInt(2, idfactura);
			
			ps.executeUpdate();
			
			ps.close();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
	}
	
}
