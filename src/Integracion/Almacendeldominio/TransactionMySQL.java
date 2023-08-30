package Integracion.Almacendeldominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionMySQL implements Transaction {

	private Connection connection;
	private final static String url = "jdbc:mysql://supermercado.mysql.database.azure.com";
	private final static String user = "supermercadoadmin";
	private final static String password = "MartinFowler1";

	public TransactionMySQL() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void commit() {
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
				TransactionManager.getInstance().deleteTransaction();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rollback() {
		if (connection != null) {
			try {
				connection.rollback();
				connection.close();
				TransactionManager.getInstance().deleteTransaction();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Object getResource() {
		return connection;
	}
}