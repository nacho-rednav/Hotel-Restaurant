package Integracion.ProductoIngrediente;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Ingrediente.DAOIngrediente;
import Integracion.Producto.DAOProducto;
import Integracion.ProductoIngrediente.DAOProdIngr;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;
import Negocio.Producto.TProductoIngrediente;

public class TestDAOProductoIngrediente {
	static DAOIngrediente daoIngr;
	static DAOProducto daoProd;
	static DAOProdIngr daoProdIngr;
	static int idIngr, idProd, idIngr1, idIngr2, idIngr3, idProd1;
	static TIngrediente tIngr,tIngr1,tIngr2,tIngr3;
	static TProducto tProd,tProd1;
	static TProductoIngrediente tProdIngr, tProdIngr1;
	static Transaction t;
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager tManager = TransactionManager.getInstance();
			t = tManager.newTransaction();
			t.start();
			
			daoIngr = FactoriaIntegracion.getInstance().generarDAOIngrediente();
			daoProd = FactoriaIntegracion.getInstance().generarDAOProducto();
			daoProdIngr = FactoriaIntegracion.getInstance().generarDAOProdIngr();
			
			tIngr = new TIngrediente(null, true, "SalTest", true);
			tProd = new TProducto(null, true, "JamonSerranoTest");
			
			idIngr = daoIngr.crear(tIngr);
			idProd = daoProd.crear(tProd);
			tProdIngr = new TProductoIngrediente(idProd, idIngr);
			
			tIngr1 = new TIngrediente(null, false, "ingrTest1", true);
			tIngr2 = new TIngrediente(null, true, "ingrTest2", true);
			tIngr3 = new TIngrediente(null, true, "ingrTest3", true);
			tProd1 = new TProducto(null, true , "ProdTest1");
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVincularDesvincular() {
		daoProdIngr.vincular(tProdIngr);
		boolean esta = daoProdIngr.contiene(tProdIngr);
		assertTrue(esta);
		daoProdIngr.desvincular(tProdIngr);
		esta = daoProdIngr.contiene(tProdIngr);
		assertTrue(!esta);
	}
	
	@Test
	public void desvincularIngrsDeProd(){
		idProd1 = daoProd.crear(tProd1);

		idIngr1 = daoIngr.crear(tIngr1);
		tProdIngr1 = new TProductoIngrediente(idProd1, idIngr1);
		daoProdIngr.vincular(tProdIngr1);
		boolean esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(esta);
		
		idIngr2 = daoIngr.crear(tIngr2);
		tProdIngr1.setIdIngrediente(idIngr2);
		daoProdIngr.vincular(tProdIngr1);
		esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(esta);
		
		idIngr3 = daoIngr.crear(tIngr3);
		tProdIngr1.setIdIngrediente(idIngr3);
		daoProdIngr.vincular(tProdIngr1);
		esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(esta);
		
		daoProdIngr.desvincularIngrsDeProd(idProd1);
		
		tProdIngr1 = new TProductoIngrediente(idProd1, idIngr1);
		esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(!esta);
		
		tProdIngr1.setIdIngrediente(idIngr2);
		esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(!esta);
		
		tProdIngr1.setIdIngrediente(idIngr3);
		esta = daoProdIngr.contiene(tProdIngr1);
		assertTrue(!esta);
		
	}
	
	
	@AfterClass
	public static void tearDown(){
		daoProd.eliminar(idProd);
		daoProd.eliminar(idProd1);
		daoIngr.eliminar(idIngr);
		daoIngr.eliminar(idIngr1);
		daoIngr.eliminar(idIngr2);
		daoIngr.eliminar(idIngr3);
		t.commit();
	}
}
