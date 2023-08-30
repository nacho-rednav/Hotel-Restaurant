/**
 * 
 */
package Negocio.Mesa;

import java.util.Collection;

public interface SAMesa {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param mesa
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class mesa);

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
	* @param tMesa
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tMesa);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class mostrarUno(Class id);

	public Integer crear(TMesa mesa);

	public Integer eliminar(Integer id);

	public Integer editar(TMesa tMesa);

	public TMesa mostrarUno(Integer id);

	public Collection<TMesa> mostrarTodos();
}