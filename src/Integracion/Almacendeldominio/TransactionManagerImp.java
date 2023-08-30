package Integracion.Almacendeldominio;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private ConcurrentHashMap concurrentHashMap;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private ConcurrentHashMap concurrentHashMap2;
	private ConcurrentHashMap<Thread, Transaction> transactions;

	public TransactionManagerImp() {
		transactions = new ConcurrentHashMap<Thread, Transaction>();
	}

	@Override
	public Transaction newTransaction() {
		Transaction t = transactions.get(Thread.currentThread());
		if (t == null) {
			TransactionFactory tf = TransactionFactory.getInstance();
			t = tf.newTransaction();
			transactions.put(Thread.currentThread(), t);
		}
		return t;
	}

	@Override
	public Transaction getTransaction() {
		return transactions.get(Thread.currentThread());
	}

	@Override
	public void deleteTransaction() {
		Transaction transaction = transactions.get(Thread.currentThread());
		if (transaction != null) {
			transactions.remove(Thread.currentThread());
		}
	}
}