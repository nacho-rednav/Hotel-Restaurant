package Integracion.Ingrediente;

import Negocio.Ingrediente.TIngrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;

public class DAOIngredienteImp implements DAOIngrediente {

	/** 
	* (non-Javadoc)
	* @see DAOIngrediente#crear(Class tIngrediente)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tIngrediente) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer crear(TIngrediente tingrediente) {
		int exito = -1;

		try {
			String consulta = "INSERT INTO supermercado.ingrediente(alergeno, nombre, activo) VALUES (?, ?, ?);";

			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

			ps.setBoolean(1, tingrediente.getAlergeno());
			ps.setString(2, tingrediente.getNombre());
			ps.setBoolean(3, tingrediente.getActivo());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				exito = rs.getInt(1);
			}

			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public int editar(TIngrediente tingrediente) throws SQLException {
		int exito = -1;

		try {
			String consulta = "UPDATE supermercado.ingrediente SET alergeno = ?, nombre = ?, activo = ? WHERE idIngrediente = ?;";

			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setBoolean(1, tingrediente.getAlergeno());
			ps.setString(2, tingrediente.getNombre());
			ps.setBoolean(3, tingrediente.getActivo());
			ps.setInt(4, tingrediente.getId());

			if (ps.executeUpdate() == 1) {
				exito = tingrediente.getId();
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngrediente#eliminar(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class eliminar(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngrediente#leerUno(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer eliminar(Integer id) {
		int exito = -1;
		try {
			String consulta = "UPDATE supermercado.ingrediente SET activo = ? WHERE idIngrediente = ?;";

			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setBoolean(1, false);
			ps.setInt(2, id);
			exito = (ps.executeUpdate() == 1) ? 0 : -1; // cambiar 0 por id si hace falta devolver id

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public TIngrediente leerUno(Integer id) {
		TIngrediente tIngrediente = null;
		try {
			String consulta = "SELECT * FROM supermercado.ingrediente WHERE idIngrediente = ? and activo = 1 FOR UPDATE;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, id);
			ResultSet myResult = ps.executeQuery();

			if (myResult.next()) {
				tIngrediente = new TIngrediente(myResult.getInt("idIngrediente"), myResult.getBoolean("alergeno"),
						myResult.getString("nombre"), myResult.getBoolean("activo"));
			}

			ps.close();
			myResult.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tIngrediente;
	}

	public Collection<TIngrediente> leerTodos() {
		ArrayList<TIngrediente> listaIngredientes = new ArrayList<TIngrediente>();

		try {
			String consulta = "SELECT * FROM supermercado.ingrediente WHERE activo = 1 FOR UPDATE";

			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaIngredientes.add(new TIngrediente(rs.getInt("idIngrediente"), rs.getBoolean("alergeno"),
						rs.getString("nombre"), rs.getBoolean("activo")));
			}

			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaIngredientes;
	}

	public TIngrediente leerUnoPorNombre(String nombre) {
		TIngrediente tIngrediente = null;
		try {
			String consulta = "SELECT * FROM supermercado.ingrediente WHERE nombre = ? FOR UPDATE;"; // necesitamos los inactivos para reactivarlos

			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setString(1, nombre);
			ResultSet myResult = ps.executeQuery();

			if (myResult.next()) {
				tIngrediente = new TIngrediente(myResult.getInt("idIngrediente"), myResult.getBoolean("alergeno"),
						myResult.getString("nombre"), myResult.getBoolean("activo"));
			}

			ps.close();
			myResult.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tIngrediente;
	}

}