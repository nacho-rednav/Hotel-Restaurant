/**
 * 
 */
package Integracion.Empleado;

import Negocio.Empleado.TEmpleado;
import java.util.Collection;

public interface DAOEmpleado {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tEmpleado
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tEmpleado);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class eliminar(Class id);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tEmpleado
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tEmpleado);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id);

	public Integer crear(TEmpleado tEmpleado);

	public Integer eliminar(Integer id);

	public Integer editar(TEmpleado tEmpleado);

	public TEmpleado leerUno(Integer id);

	public Collection leerTodos();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param dni
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerPorDNI(Class dni);

	public TEmpleado leerPorDNI(String dni);
}