
package Negocio.Servicio;

import java.util.ArrayList;

public interface SAServicio {
	
	public Integer crear(TServicio tSercicio);

	public Integer eliminar(Integer id);

	public Integer editar(TServicio tServicio);

	public TServicio mostrarPorId(Integer id);

	public TServicio mostrarPorTipo(String tipo);

	public ArrayList mostrarTodos();

	public ArrayList mostrarReservasServicio(Integer id);
}