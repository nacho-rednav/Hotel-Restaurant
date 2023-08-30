package Negocio.Producto;

public class TProductoIngrediente {
	private Integer id_producto;
	private Integer id_ingrediente;

	public TProductoIngrediente(Integer id_producto, Integer id_ingrediente) {
		this.id_producto = id_producto;
		this.id_ingrediente = id_ingrediente;
	}

	public Integer getIdProducto() {
		return this.id_producto;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idProducto
	* @param idIngrediente
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TProductoIngrediente(Class idProducto, Class idIngrediente) {
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

	public void setIdProducto(Integer idP) {
		this.id_producto = idP;
	}

	public Integer getIdIngrediente() {
		return this.id_ingrediente;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idI
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdIngrediente(Class idI) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setIdIngrediente(Integer idI) {
		this.id_ingrediente = idI;
	}
}
