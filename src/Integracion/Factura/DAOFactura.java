package Integracion.Factura;

import Negocio.Factura.TFactura;

import java.util.Collection;

public interface DAOFactura {

	public Integer abrir(TFactura tFactura);

	public Integer cerrar(TFactura tFactura);

	public Integer editar(TFactura tFactura);

	public TFactura leerUno(Integer id);

	public Collection<TFactura> leerTodos();

	public Collection<TFactura> leerTodosPorMesa(Integer idMesa);

	public Collection<TFactura> leerTodosPorEmpleado(Integer idEmpleado);

}