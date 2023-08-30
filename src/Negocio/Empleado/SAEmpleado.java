/**
 * 
 */
package Negocio.Empleado;

import java.util.Collection;
import Negocio.Queries.TQuery;

public interface SAEmpleado {

	public int crear(TEmpleado tEmp);

	public int eliminar(Integer id);

	public int editar(TEmpleado tEmp);

	public TEmpleado mostrarUno(Integer id);

	public Collection mostrarTodos();

	public Collection mostrarEmpDespRango(TQuery Parameter2);
}