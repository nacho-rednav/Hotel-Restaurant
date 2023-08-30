package Negocio.Producto;

import java.util.ArrayList;
import java.util.Collection;

import Negocio.Ingrediente.TIngrediente;

public interface SAProducto {

	public Integer crear(TProducto tProd);

	public Integer editar(TProducto tProd);

	public Integer eliminar(Integer id);

	public TProducto mostrarUno(Integer id);

	public Collection<TProducto> mostrarTodos();

	public int vincular(TProductoIngrediente tProdIngr);

	public int desvincular(TProductoIngrediente tProdIngr);

	public TProducto mostrarUnoPorNombre(String nombre);

	public ArrayList<TIngrediente> mostrarIngrsPorProd(Integer idProd);
}