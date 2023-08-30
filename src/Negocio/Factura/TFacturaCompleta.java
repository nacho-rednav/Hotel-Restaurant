package Negocio.Factura;

import Negocio.Mesa.TMesa;
import Negocio.Empleado.TEmpleado;
import Negocio.Menu.TMenu;

import java.util.List;
import java.util.Set;

public class TFacturaCompleta {

	private TFactura tfactura;
	private TMesa tmesa;
	private TEmpleado templeado;
	private List<TMenu> listaMenus;
	private List<TLineaDePedido> listaLineas;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private TFactura tFactura;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private Set<TLineaDePedido> tLineaDePedido;

	public TFacturaCompleta(TFactura f, TMesa m, TEmpleado e, List<TMenu> lm, List<TLineaDePedido> lp) {
		tfactura = f;
		tmesa = m;
		templeado = e;
		listaMenus = lm;
		listaLineas = lp;
	}

	public TFactura getTfactura() {
		return tfactura;
	}

	public void setTfactura(TFactura tfactura) {
		this.tfactura = tfactura;
	}

	public TMesa getTmesa() {
		return tmesa;
	}

	public void setTmesa(TMesa tmesa) {
		this.tmesa = tmesa;
	}

	public TEmpleado getTempleado() {
		return templeado;
	}

	public void setTempleado(TEmpleado templeado) {
		this.templeado = templeado;
	}

	public List<TMenu> getListaMenus() {
		return listaMenus;
	}

	public void setListaMenus(List<TMenu> listaMenus) {
		this.listaMenus = listaMenus;
	}

	public List<TLineaDePedido> getListaLineas() {
		return listaLineas;
	}

	public void setListaLineas(List<TLineaDePedido> listaLineas) {
		this.listaLineas = listaLineas;
	}

}