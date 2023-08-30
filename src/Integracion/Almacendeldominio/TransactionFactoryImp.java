package Integracion.Almacendeldominio;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction newTransaction() {
		return new TransactionMySQL();
	}
}