package Integracion.Ingrediente;

import Negocio.Ingrediente.TIngrediente;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOIngrediente {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tIngrediente
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tIngrediente);

	public Integer crear(TIngrediente tIngrediente);

	public int editar(TIngrediente tIngrediente) throws SQLException;

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
	* @param id
	* @return
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id);

	public Integer eliminar(Integer id);

	public TIngrediente leerUno(Integer id);

	public Collection<TIngrediente> leerTodos();

	public TIngrediente leerUnoPorNombre(String nombre);

}