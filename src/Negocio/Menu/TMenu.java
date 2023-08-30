
package Negocio.Menu;

public class TMenu {

	private Integer id_menu;
	private Boolean activo;
	private String nombre;
	private Float precio;
	private Integer stock;

	public TMenu(Integer id_menu, Boolean activo, String nombre, Float precio, Integer stock) {
		this.id_menu = id_menu;
		this.activo = activo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public TMenu() {
	}

	//Getters
	public Integer getId() {
		return id_menu;
	}

	public Boolean getActivo() {
		return activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param activo
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setActivo(Class activo) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setId(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public String getNombre() {
		return nombre;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nombre
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNombre(Class nombre) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Float getPrecio() {
		return precio;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param precio
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPrecio(Class precio) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Integer getStock() {
		return stock;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param stock
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setStock(Class stock) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id_menu
	* @param activo
	* @param nombre
	* @param precio
	* @param stock
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TMenu(Class id_menu, Class activo, Class nombre, Class precio, Class stock) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	//Setters
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setId(Integer id) {
		this.id_menu = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}