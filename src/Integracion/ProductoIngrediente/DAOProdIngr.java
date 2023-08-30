package Integracion.ProductoIngrediente;

import java.util.Collection;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;
import Negocio.Producto.TProductoIngrediente;

public interface DAOProdIngr {

	public Collection<TProducto> leerProdsPorIngr(Integer idIngrediente);

	public void desvincularIngrsDeProd(Integer idProducto);

	public Collection<TIngrediente> leerIngrsPorProd(Integer idProducto);

	public void vincular(TProductoIngrediente tProdIngr);

	public Boolean contiene(TProductoIngrediente tProdIngr);

	public void desvincular(TProductoIngrediente tProdIngr);
}