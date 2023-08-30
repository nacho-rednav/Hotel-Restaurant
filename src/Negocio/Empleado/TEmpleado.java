package Negocio.Empleado;

public class TEmpleado {

	private String nombre;
	private Boolean activo;
	private Float sueldo;
	private String dni;
	private String horario;
	private Integer id_empleado;
	protected String idioma;
	protected Boolean titulo;

	public TEmpleado(Integer id, String nombre, Boolean activo, Float sueldo, String dni, String horario, String idioma,
			Boolean titulo) {
		this.id_empleado = id;
		this.nombre = nombre;
		this.activo = activo;
		this.sueldo = sueldo;
		this.dni = dni;
		this.horario = horario;
		this.idioma = idioma;
		this.titulo = titulo;
	}

	public TEmpleado(String nombre, Boolean activo, Float sueldo, String dni, String horario, String idioma,
			Boolean titulo) {
		this.nombre = nombre;
		this.activo = activo;
		this.sueldo = sueldo;
		this.dni = dni;
		this.horario = horario;
		this.idioma = idioma;
		this.titulo = titulo;
	}

	public TEmpleado() {

	}

	public String getNombre() {
		return nombre;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idioma
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdioma(Class idioma) {
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

	public Float getSueldo() {
		return sueldo;
	}

	public String getDNI() {
		return dni;
	}

	public String getHorario() {
		return horario;
	}

	public Integer getId() {
		return id_empleado;
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
	* @param sueldo
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setSueldo(Class sueldo) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param dni
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setDNI(Class dni) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param horario
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setHorario(Class horario) {
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

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param idioma
	* @param titulo
	* @param nombre
	* @param activo
	* @param sueldo
	* @param dni
	* @param horario
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TEmpleado(Class id, Class idioma, Class titulo, Class nombre, Class activo, Class sueldo, Class dni,
			Class horario) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSueldo(Float sueldo) {
		this.sueldo = sueldo;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setId(Integer id) {
		id_empleado = id;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setTitulo(Boolean titulo) {
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param titulo
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setTitulo(Class titulo) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Boolean getTitulo() {
		return titulo;
	}
}