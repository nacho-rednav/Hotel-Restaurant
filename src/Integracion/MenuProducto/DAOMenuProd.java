/**
 * 
 */
package Integracion.MenuProducto;

import java.util.Collection;

import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProducto;
import Negocio.Producto.TProducto;

public interface DAOMenuProd {

	public Collection<TProducto> leerProdsPorMenu(Integer idMenu);

	public Boolean contiene(TMenuProducto tMenuProd);

	public void desvincularProdsDeMenu(Integer idMenu);

	public Collection<TMenu> leerMenusPorProd(Integer idProd);

	public void vincular(TMenuProducto tMenuProd);

	public void desvincular(TMenuProducto tMenuProd);
}