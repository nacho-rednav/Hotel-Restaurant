package Negocio.Menu;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Menu.SAMenuImp;
import Negocio.Menu.TMenu;

public class TestSAMenu {
	static SAMenuImp saMenu;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		saMenu = new SAMenuImp();
	}
	
	@Test
	public void testCrearCorrecto() {
		TMenu menu = new TMenu(77, true, "pruebaMenu", (float)6.00, 25);

		assertNotEquals(-1, saMenu.crear(menu));
	}
	
	@Test
	public void testCrearExistente() {
		TMenu menu = new TMenu(77, true, "pruebaMenu", (float)6.00, 25);

		assertEquals(-1, saMenu.crear(menu));
	}
	
	@Test
    public void testEliminaActivo() {
		TMenu menu = new TMenu(79, true, "pruebaMenu2", (float)5.99, 10);
		
        assertEquals(0, saMenu.eliminar(menu.getId()));
    }
	
	@Test
    public void testEliminaNoActivo() {
		TMenu menu = new TMenu(80, false, "pruebaMenu3", (float)4.75, 30);
		
        assertEquals(-1,saMenu.eliminar(menu.getId()));
    }
	
	@Test
    public void testEliminaIdIncorrecto() {
		int res = saMenu.eliminar(17711771); //este id no tiene que existir,
		
        assertEquals(-1,res);
    }
	
	@Test
	public void testModificarCorrecto() {
		TMenu menu = new TMenu(132, true, "pruebaMenu4", (float)8.99, 50);
		menu.setNombre("otroNombre");
		menu.setPrecio((float)2.99);
		menu.setStock(35);
		
		assertEquals(0, saMenu.editar(menu));
	}
	
	@Test
	public void testModificarIncorrecto() { //TODO: no habria que forzar fallo?
		TMenu menu = new TMenu(232, true, "pruebaMenu5", (float)5.79, 23);
		menu.setNombre("otroNombre2");
		menu.setPrecio((float)2.99);
		menu.setStock(35);
		
		assertEquals(-1, saMenu.editar(menu));
	}

}
