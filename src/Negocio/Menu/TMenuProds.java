package Negocio.Menu;

import java.util.ArrayList;

import Negocio.Producto.TProducto;

public class TMenuProds {

	TMenu menu;
	ArrayList<TProducto> prods;

	public TMenuProds(TMenu menu, ArrayList<TProducto> prods) {
		this.menu = menu;
		this.prods = prods;
	}

	public TMenu getMenu() {
		return menu;
	}

	public void setMenu(TMenu menu) {
		this.menu = menu;
	}

	public ArrayList<TProducto> getProds() {
		return prods;
	}

	public void setProds(ArrayList<TProducto> prods) {
		this.prods = prods;
	}
	
	

}
