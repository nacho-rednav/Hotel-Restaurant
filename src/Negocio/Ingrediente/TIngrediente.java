package Negocio.Ingrediente;

public class TIngrediente {

	private Integer id_ingrediente;
	private String nombre;
	private Boolean alergeno;
	private Boolean activo;

	public int getId() {
		return id_ingrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean getAlergeno() {
		return alergeno;
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

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param alergeno
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setAlergeno(Class alergeno) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
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

	public Boolean getActivo() {
		return activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param nombre
	* @param alergeno
	* @param activo
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TIngrediente(Class id, Class nombre, Class alergeno, Class activo) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public void setId(int id) {
		this.id_ingrediente = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAlergeno(Boolean alergeno) {
		this.alergeno = alergeno;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public TIngrediente(Integer id, Boolean alergeno, String nombre, Boolean activo) {
		this.id_ingrediente = id;
		this.nombre = nombre;
		this.alergeno = alergeno;
		this.activo = activo;
	}

	public TIngrediente() {

	}

	public TIngrediente(Boolean alergeno, String nombre, Boolean activo) {
		this.nombre = nombre;
		this.alergeno = alergeno;
		this.activo = activo;
	}

}