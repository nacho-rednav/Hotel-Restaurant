/**
 * 
 */
package Negocio.Recepcionista;

import java.util.ArrayList;

public interface SARecepcionista {

	public Integer crear(TRecepcionista tRecepcionista);

	public Integer eliminar(Integer id);

	public Integer editar(TRecepcionista tRecepcionista);

	public TRecepcionista mostrarPorId(Integer id);

	public TRecepcionista mostrarPorNombre(String nombre);

	public ArrayList mostrarTodos();
}