package Negocio.Mesa;

public class TMesa {

	private Integer id_mesa;
	private Integer num_mesa;
	private Integer capacidad;
	private Boolean disponibilidad;
	private Boolean activo;

	public Integer getId() {
		return id_mesa;
	}

	public Integer getNumMesa() {
		return num_mesa;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public Boolean getActivo() {
		return activo;
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
	* @param numMesa
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNumMesa(Class numMesa) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param capacidad
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setCapacidad(Class capacidad) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param disponibilidad
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setDisponibilidad(Class disponibilidad) {
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

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param num
	* @param activo
	* @param capacidad
	* @param disponibilidad
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TMesa(Class id, Class num, Class activo, Class capacidad, Class disponibilidad) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public void setId(Integer id) {
		this.id_mesa = id;
	}

	public void setNumMesa(Integer numMesa) {
		this.num_mesa = numMesa;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public TMesa(Integer num, Boolean activo, Integer capacidad, Boolean disponibilidad) {
		this.num_mesa = num;
		this.activo = activo;
		this.capacidad = capacidad;
		this.disponibilidad = disponibilidad;
	}

	public TMesa(Integer id, Integer num, Boolean activo, Integer capacidad, Boolean disponibilidad) {
		this.id_mesa = id;
		this.num_mesa = num;
		this.activo = activo;
		this.capacidad = capacidad;
		this.disponibilidad = disponibilidad;
	}

	public TMesa() {
	}
}