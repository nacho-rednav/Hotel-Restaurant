package Negocio.Factura;

import java.sql.Date;

public class TFactura {

	private Integer id_factura;
	private Boolean activo;
	private Integer id_mesa;
	private Float importe;
	private Date hora_cobro;
	private Integer id_empleado;

	public Integer getId() {
		return this.id_factura;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id_mesa
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdMesa(Class id_mesa) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id_empleado
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setIdEmpleado(Class id_empleado) {
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

	public void setId(Integer id) {
		this.id_factura = id;
	}

	public Integer getIdMesa() {
		return this.id_mesa;
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

	public void setIdMesa(Integer id_mesa) {
		this.id_mesa = id_mesa;
	}

	public Float getImporte() {
		return this.importe;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param importe
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setImporte(Class importe) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param carrito
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setCarrito(Object carrito) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Object getCarrito() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	public Integer getIdEmpleado() {
		return this.id_empleado;
	}

	public void setIdEmpleado(Integer id_empleado) {
		this.id_empleado = id_empleado;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getHoraCobro() {
		return this.hora_cobro;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param hora
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setHoraCobro(Class hora) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setHoraCobro(Date hora) {
		this.hora_cobro = hora;
	}

	public TFactura(Integer id_mesa, Integer id_empleado, Float importe, Date hora_cobro, Boolean activo) {
		this.id_mesa = id_mesa;
		this.id_empleado = id_empleado;
		this.importe = importe;
		this.hora_cobro = hora_cobro;
		this.activo = activo;
	}

	public TFactura(Integer id_factura, Integer id_mesa, Integer id_empleado, Float importe, Date hora_cobro,
			Boolean activo) {
		this.id_factura = id_factura;
		this.id_mesa = id_mesa;
		this.id_empleado = id_empleado;
		this.importe = importe;
		this.hora_cobro = hora_cobro;
		this.activo = activo;
	}

}