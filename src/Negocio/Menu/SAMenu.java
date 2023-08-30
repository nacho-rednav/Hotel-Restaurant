
package Negocio.Menu;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.Producto.TProducto;
import Negocio.Queries.TQuery;

public interface SAMenu {

	public int crear(TMenu tMenu);

	public int eliminarProd(TMenuProducto tMenuProd);

	public int anadirProd(TMenuProducto tMenuProd);

	public int editar(TMenu tMenu);

	public int eliminar(Integer idMenu);

	public TMenu mostrarUno(Integer id);

	public ArrayList<TMenu> mostrarTodos();

	public ArrayList<TProducto> mostrarProdsPorMenu(Integer idMenu);

	public ArrayList<TMenu> mostrarMenusRangoPrecio(TQuery Parameter2);
}