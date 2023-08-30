/**
 * 
 */
package Negocio.Queries;

public class TQuery {

	private Float minimo;

	private Float maximo;

	public TQuery(float minimo, float maximo) {
		this.minimo = minimo;
		this.maximo = maximo;
	}

	public Float getMinimo() {
		return minimo;
	}

	public Float getMaximo() {
		return maximo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param _9
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setMinimo(Object _9) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setMaximo() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TQuery() {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

	public void setMaximo(float maximo) {
		this.maximo = maximo;
	}

}