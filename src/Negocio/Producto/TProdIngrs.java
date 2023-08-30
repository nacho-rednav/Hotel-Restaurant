package Negocio.Producto;

import java.util.ArrayList;

import Negocio.Ingrediente.TIngrediente;

public class TProdIngrs {

	TProducto producto;
	ArrayList<TIngrediente> ingrs;

	public TProdIngrs(TProducto producto, ArrayList<TIngrediente> ingredientes) {
		this.producto = producto;
		this.ingrs = ingredientes;
	}

	public TProducto getProducto() {
		return producto;
	}

	public void setProducto(TProducto producto) {
		this.producto = producto;
	}

	public ArrayList<TIngrediente> getIngrs() {
		return ingrs;
	}

	public void setProds(ArrayList<TIngrediente> ingrs) {
		this.ingrs = ingrs;
	}
	
}
