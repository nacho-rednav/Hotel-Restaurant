package Integracion.Mesa;

import Negocio.Mesa.TMesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;

public class DAOMesaImp implements DAOMesa {

	/** 
	* (non-Javadoc)
	* @see DAOMesa#crear(Class tMesa)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tMesa) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMesa#editar(Class tMesa)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tMesa) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMesa#eliminar(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class eliminar(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer crear(TMesa tMesa) {
		int res = -1;

		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "INSERT INTO supermercado.mesa (numero, capacidad, disponibilidad, activo) VALUES (?, ?, ?, ?);";

			PreparedStatement s = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, tMesa.getNumMesa());
			s.setInt(2, tMesa.getCapacidad());
			s.setBoolean(3, true);
			s.setBoolean(4, true);

			s.executeUpdate();

			ResultSet rs = s.getGeneratedKeys();
			if (rs.next()) {
				res = rs.getInt(1);
			}
			s.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer editar(TMesa tMesa) {
		int res = -1;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "UPDATE supermercado.mesa SET capacidad = ?, numero = ?, disponibilidad = ?, "
					+ "activo = ? WHERE idmesa = ?;";

			PreparedStatement s = connection.prepareStatement(consulta);

			s.setInt(1, tMesa.getCapacidad());
			s.setInt(2, tMesa.getNumMesa());
			s.setBoolean(3, tMesa.getDisponibilidad());
			s.setBoolean(4, tMesa.getActivo());
			s.setInt(5, tMesa.getId());

			if (s.executeUpdate() == 1)
				res = tMesa.getId();

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer eliminar(Integer id) {
		int res = -1;
		try {

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "UPDATE supermercado.mesa SET activo = 0 WHERE idmesa = ?;";
			PreparedStatement s = connection.prepareStatement(consulta);

			s.setInt(1, id);
			res = (s.executeUpdate() == 1) ? id : -1; // cambiar 0 por id si hace falta devolver id
			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return res;
	}

	public Collection<TMesa> leerTodos() {
		ArrayList<TMesa> lista = new ArrayList<TMesa>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection
					.prepareStatement(("SELECT * FROM supermercado.mesa WHERE activo = 1 FOR UPDATE;"));

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				lista.add(new TMesa(rs.getInt("idmesa"), rs.getInt("numero"), rs.getBoolean("activo"),
						rs.getInt("capacidad"), rs.getBoolean("disponibilidad")));
			}

			s.close();
			rs.close();

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

	}

	/** 
	* (non-Javadoc)
	* @see DAOMesa#leerPorId(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerPorId(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMesa#leerPorNum(Class numMesa)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerPorNum(Class numMesa) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TMesa leerPorId(Integer id) {
		TMesa res = null;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s;

			s = connection
					.prepareStatement(("SELECT * FROM supermercado.mesa WHERE idmesa = ? AND activo = 1 FOR UPDATE;"));

			s.setInt(1, id);
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				res = new TMesa(rs.getInt("idmesa"), rs.getInt("numero"), rs.getBoolean("activo"),
						rs.getInt("capacidad"), rs.getBoolean("disponibilidad"));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return res;
	}

	public TMesa leerPorNum(Integer numMesa) {
		TMesa res = null;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s;

			s = connection.prepareStatement(
					("SELECT idmesa, activo, capacidad, disponibilidad FROM supermercado.mesa WHERE numero = ? FOR UPDATE;"));

			s.setInt(1, numMesa);
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				res = new TMesa(rs.getInt("idmesa"), numMesa, rs.getBoolean("activo"), rs.getInt("capacidad"),
						rs.getBoolean("disponibilidad"));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.println();
		return res;

	}
}