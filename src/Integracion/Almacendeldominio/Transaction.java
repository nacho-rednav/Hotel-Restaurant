package Integracion.Almacendeldominio;

public interface Transaction {

	TransactionMySQL transactionMySQL = null;

	public void start();

	public void commit();

	public void rollback();

	public Object getResource();
}