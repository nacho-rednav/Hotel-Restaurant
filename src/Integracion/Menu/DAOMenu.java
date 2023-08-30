/**
 * 
 */
package Integracion.Menu;

import Negocio.Menu.TMenu;

import java.util.ArrayList;
import java.util.Collection;

public interface DAOMenu {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tMenu
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tMenu);

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
	* @param tMenu
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tMenu);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id);

	public Integer crear(TMenu tMenu);

	public Integer eliminar(Integer id);

	public Integer editar(TMenu tMenu);

	public TMenu leerUno(Integer id);

	public TMenu leerPorNombre(String s);

	public ArrayList<TMenu> leerTodos();
}