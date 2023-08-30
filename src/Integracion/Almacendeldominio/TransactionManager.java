package Integracion.Almacendeldominio;

public abstract class TransactionManager {

	private static TransactionManager instance;

	public static TransactionManager getInstance() {
		if (instance == null)
			instance = new TransactionManagerImp();

		return instance;
	}

	public abstract Transaction newTransaction();

	public abstract Transaction getTransaction();

	public abstract void deleteTransaction();

}