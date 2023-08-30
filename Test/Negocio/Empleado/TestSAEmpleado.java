package Negocio.Empleado;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Empleado.SAEmpleadoImp;

public class TestSAEmpleado {
	
	static SAEmpleadoImp saEmp;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		saEmp = new SAEmpleadoImp();
	}
	
	@Test
	public void testCrearCocineroCorrecto() {
		TEmpleado tEmpTest1 = new TCocinero(null, "EmpCcTest1", true, (float) 3.69, "0000000" ,"horario1", true);

		int res = saEmp.crear(tEmpTest1);
		assertTrue(res > 0);
		TEmpleado tEmpRes = saEmp.mostrarUno(res);
		assertEquals(tEmpRes.getNombre(), tEmpTest1.getNombre());
		assertEquals(tEmpRes.getSueldo(), tEmpTest1.getSueldo());
		assertEquals(tEmpRes.getDNI(), tEmpTest1.getDNI());
		assertEquals(tEmpRes.getHorario(), tEmpTest1.getHorario());
		assertEquals(tEmpRes.getIdioma(), tEmpTest1.getIdioma());
		assertEquals(tEmpRes.getTitulo(), tEmpTest1.getTitulo());
		
        saEmp.eliminar(res);
	}
	
	@Test
	public void testCrearCamareroCorrecto() {
		TEmpleado tEmpTest2 = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");

		int res = saEmp.crear(tEmpTest2);
		assertTrue(res > 0);
		TEmpleado tEmpRes = saEmp.mostrarUno(res);
		assertEquals(tEmpRes.getNombre(), tEmpTest2.getNombre());
		assertEquals(tEmpRes.getSueldo(), tEmpTest2.getSueldo());
		assertEquals(tEmpRes.getDNI(), tEmpTest2.getDNI());
		assertEquals(tEmpRes.getHorario(), tEmpTest2.getHorario());
		assertEquals(tEmpRes.getIdioma(), tEmpTest2.getIdioma());
		assertEquals(tEmpRes.getTitulo(), tEmpTest2.getTitulo());
		
        saEmp.eliminar(res);
	}
	
	@Test
	public void testCrearCamareroExistenteNoActivo() {
		TEmpleado tEmpTest2 = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");
		
		int res1 = saEmp.crear(tEmpTest2);
		saEmp.eliminar(res1);
		tEmpTest2.setSueldo((float) 69.0); //Cambiamos sueldo, debería actualizarse
		int res2 = saEmp.crear(tEmpTest2);
		assertTrue(res1 > 0 && res2 > 0);
		TEmpleado tEmpRes = saEmp.mostrarUno(res2);
		assertEquals(tEmpRes.getSueldo(), tEmpTest2.getSueldo());
		saEmp.eliminar(res2);
	}
	
	@Test
    public void testCrearIDYaExistente() {
		TEmpleado tEmpTest3 = new TCamarero(null, "EmpCmTest3", true, (float) 3.69, "2222222" ,"horario1", "Español");
		
		int idEmpTest3 = saEmp.crear(tEmpTest3);
		int res = saEmp.crear(tEmpTest3);
		saEmp.eliminar(idEmpTest3);
        assertEquals(-1,res);
    }
	
	@Test
    public void testEliminaCocineroActivo() {
		TEmpleado empleado = new TCocinero(null, "EmpCcTest1", true, (float) 3.69, "0000000" ,"horario1", true);
		
        assertEquals(0, saEmp.eliminar(empleado.getId()));
    }
	
	@Test
    public void testEliminaCamareroActivo() {
		TEmpleado empleado = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");
		
        assertEquals(0, saEmp.eliminar(empleado.getId()));
    }
	
	@Test
    public void testEliminaCocineroNoActivo() {
		TEmpleado empleado = new TCocinero(null, "EmpCcTest1", true, (float) 3.69, "0000000" ,"horario1", true);
		
        assertEquals(-1,saEmp.eliminar(empleado.getId()));
    }
	
	@Test
    public void testEliminaCamareroNoActivo() {
		TEmpleado empleado = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");
		
        assertEquals(-1,saEmp.eliminar(empleado.getId()));
    }
	
	@Test
    public void testEliminaIdIncorrecto() {
		int res = saEmp.eliminar(17711771); //este id no tiene que existir,
		
        assertEquals(-1,res);
    }
	
	@Test
	public void testModificarCocineroCorrecto() {
		TEmpleado empleado = new TCocinero(null, "EmpCcTest1", true, (float) 3.69, "0000000" ,"horario1", true);
		empleado.setNombre("cocineroDiferente");
		empleado.setDNI("77777777A");
		
		assertEquals(0, saEmp.editar(empleado));
	}
	
	@Test
	public void testModificarCamareroCorrecto() {
		TEmpleado empleado = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");
		empleado.setNombre("camareroDiferente");
		empleado.setDNI("77777777A");
		
		assertEquals(0, saEmp.editar(empleado));
	}
	
	//TODO: no se si estos deberian ser asi o forzar el error para que sean incorrectos
	@Test
	public void testModificarCocineroIncorrecto() {
		TEmpleado empleado = new TCocinero(null, "EmpCcTest1", true, (float) 3.69, "0000000" ,"horario1", true);
		empleado.setNombre("nombre");
		empleado.setDNI("77777777A");
		
		assertEquals(-1, saEmp.editar(empleado));
	}
	
	@Test
	public void testModificarCamareroIncorrecto() {
		TEmpleado empleado = new TCamarero(null, "EmpCmTest2", true, (float) 3.69, "1111111" ,"horario1", "Español");
		empleado.setNombre("nombre");
		empleado.setDNI("77777777A");
		
		assertEquals(-1, saEmp.editar(empleado));
	}

}
