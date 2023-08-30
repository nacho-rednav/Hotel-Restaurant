package Integracion.MenuProducto;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Menu.DAOMenu;
import Integracion.MenuProducto.DAOMenuProd;
import Integracion.Producto.DAOProducto;
import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProducto;
import Negocio.Producto.TProducto;

public class TestDAOMenuProd {
	static DAOMenu daoMenu;
	static DAOProducto daoProd;
	static DAOMenuProd daoMenuProd;
	static int idMenu, idProd;
	static TMenu tMenu;
	static TProducto tProd;
	static TMenuProducto tMenuProd;
	static Transaction t;
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			t = tManager.newTransaction();
			t.start();
			
			daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
			daoMenu = FactoriaIntegracion.getInstance().generarDAOMenu();
			daoMenuProd = FactoriaIntegracion.getInstance().generarDAOMenuProd();
			
			tProd = new TProducto(null, true, "EnsaladaTest");
			tMenu = new TMenu(null, true, "mediterraneoTest", 14.5f, 10);
			
			idProd = daoProd.crear(tProd);
			idMenu = daoMenu.crear(tMenu);
			
			tMenuProd = new TMenuProducto(idMenu, idProd);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVincularDesvincular() {
		daoMenuProd.vincular(tMenuProd);
		boolean esta = daoMenuProd.contiene(tMenuProd);
		assertTrue(esta);
		daoMenuProd.desvincular(tMenuProd);
		esta = daoMenuProd.contiene(tMenuProd);
		assertTrue(!esta);
	}
	
	@AfterClass
	public static void tearDown(){
		daoProd.eliminar(idProd);
		daoMenu.eliminar(idMenu);
		t.commit();
	}
}
