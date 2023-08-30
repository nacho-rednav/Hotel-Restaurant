package Negocio.Producto;

public class TProducto {
	private Integer id_producto;
	private Boolean activo;
	private String nombre;

	public TProducto(Integer id, Boolean activo, String nombre) {
		id_producto = id;
		this.activo = activo;
		this.nombre = nombre;
	}

	public TProducto(Boolean activo, String nombre) {
		this.activo = activo;
		this.nombre = nombre;
	}

	public TProducto() {
	}

	public Integer getId() {
		return id_producto;
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

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param activo
	* @param nombre
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TProducto(Class id, Class activo, Class nombre) {
		// begin-user-code
		// TODO Auto-generated constructor stub
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

	public void setId(Integer id) {
		id_producto = id;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}