package Negocio.Menu;

public class TMenuProducto {

	private Integer id_menu;

	private Integer id_producto;

	public TMenuProducto(Integer idMenu, Integer idProducto) {
		this.id_menu = idMenu;
		this.id_producto = idProducto;
	}

	public Integer getIdProducto() {
		return this.id_producto;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idMenu
	* @param idProducto
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TMenuProducto(Class idMenu, Class idProducto) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idP
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdProducto(Class idP) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Integer getIdMenu() {
		return this.id_menu;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idM
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdMenu(Class idM) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setIdProducto(Integer idP) {
		this.id_producto = idP;
	}

	public void setIdMenu(Integer idM) {
		this.id_menu = idM;
	}
}