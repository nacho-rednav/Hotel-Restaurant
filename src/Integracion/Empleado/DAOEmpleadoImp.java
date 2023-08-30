/**
 * 
 */
package Integracion.Empleado;

import Negocio.Empleado.TEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;

public class DAOEmpleadoImp implements DAOEmpleado {

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#crear(Class tEmpleado)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tEmpleado) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#eliminar(Class id)
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
	* @see DAOEmpleado#editar(Class tEmpleado)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tEmpleado) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#leerUno(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer crear(TEmpleado tEmpleado) {
		int res = -1;
		try {
			String consulta = "INSERT INTO supermercado.empleado(nombre, activo, sueldo, dni,horario, idioma, titulo) VALUES (?, ?, ?, ?, ?, ?, ?);";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tEmpleado.getNombre());
			ps.setBoolean(2, tEmpleado.getActivo());
			ps.setFloat(3, tEmpleado.getSueldo());
			ps.setString(4, tEmpleado.getDNI());
			ps.setString(5, tEmpleado.getHorario());
			ps.setString(6, tEmpleado.getIdioma());
			ps.setBoolean(7, tEmpleado.getTitulo());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				res = rs.getInt(1);

			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Integer eliminar(Integer id) {
		int res = -1;
		try {
			String consulta = "UPDATE supermercado.empleado SET activo = ? WHERE idempleado = ?;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setBoolean(1, false);
			ps.setInt(2, id);

			res = (ps.executeUpdate() == 1) ? id : -1;

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Integer editar(TEmpleado tEmpleado) {
		int res = -1;
		try {
			String consulta = "UPDATE supermercado.empleado SET nombre = ?, activo = ?, sueldo= ?, dni =?, horario=?, idioma = ?, titulo = ? WHERE idempleado = ?;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setString(1, tEmpleado.getNombre());
			ps.setBoolean(2, tEmpleado.getActivo());
			ps.setFloat(3, tEmpleado.getSueldo());
			ps.setString(4, tEmpleado.getDNI());
			ps.setString(5, tEmpleado.getHorario());
			ps.setString(6, tEmpleado.getIdioma());
			ps.setBoolean(7, tEmpleado.getTitulo());
			ps.setInt(8, tEmpleado.getId());

			if (ps.executeUpdate() == 1)
				res = tEmpleado.getId();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public TEmpleado leerUno(Integer id) {
		TEmpleado tEmpleado = null;
		try {
			String consulta = "SELECT * FROM supermercado.empleado WHERE idempleado = ? FOR UPDATE;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, id);
			ResultSet miResult = ps.executeQuery();

			if (miResult.next())
				tEmpleado = new TEmpleado(miResult.getInt("idempleado"), miResult.getString("nombre"),
						miResult.getBoolean("activo"), miResult.getFloat("sueldo"), miResult.getString("dni"),
						miResult.getString("horario"), miResult.getString("idioma"), miResult.getBoolean("titulo"));

			ps.close();
			miResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tEmpleado;
	}

	public Collection<TEmpleado> leerTodos() {
		ArrayList<TEmpleado> lista = new ArrayList<TEmpleado>();
		try {

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement statement = connection
					.prepareStatement(("SELECT * FROM supermercado.empleado WHERE activo = 1 FOR UPDATE"));

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				lista.add(new TEmpleado(results.getInt("idEmpleado"), results.getString("nombre"),
						results.getBoolean("activo"), results.getFloat("sueldo"), results.getString("dni"),
						results.getString("horario"), results.getString("idioma"), results.getBoolean("titulo")));
			}

			statement.close();
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#leerPorDNI(Class dni)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerPorDNI(Class dni) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TEmpleado leerPorDNI(String dni) {
		TEmpleado tEmpleado = null;
		try {
			String consulta = "SELECT * FROM supermercado.empleado WHERE dni = ?;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				tEmpleado = new TEmpleado(rs.getInt("idempleado"), rs.getString("nombre"), rs.getBoolean("activo"),
						rs.getFloat("sueldo"), rs.getString("dni"), rs.getString("horario"), rs.getString("idioma"),
						rs.getBoolean("titulo"));

			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tEmpleado;
	}
}