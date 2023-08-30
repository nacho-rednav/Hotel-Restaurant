package Integracion.Menu;

import Negocio.Menu.TMenu;
import Negocio.Producto.TProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;

public class DAOMenuImp implements DAOMenu {

	/** 
	* (non-Javadoc)
	* @see DAOMenu#crear(Class tMenu)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class crear(Class tMenu) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMenu#eliminar(Class id)
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
	* @see DAOMenu#editar(Class tMenu)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class editar(Class tMenu) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMenu#leerUno(Class id)
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Class leerUno(Class id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer crear(TMenu tMenu) {
		int key = -1;

		try {
			String consulta = "INSERT INTO supermercado.menu (activo, nombre, precio, stock) VALUES (?, ?, ?, ?);";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

			ps.setBoolean(1, tMenu.getActivo());
			ps.setString(2, tMenu.getNombre());
			ps.setFloat(3, tMenu.getPrecio());
			ps.setInt(4, tMenu.getStock());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
				key = rs.getInt(1);

			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return key;
	}

	public Integer eliminar(Integer id) {
		int key = -1;
		try {
			String consulta = "UPDATE supermercado.menu SET activo = 0 WHERE idmenu = ?;";
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(consulta);
			s.setInt(1, id);
			key = (s.executeUpdate() == 1) ? id : -1;

			s.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public Integer editar(TMenu tMenu) {
		int ok = -1;

		try {
			String consulta = "UPDATE supermercado.menu SET activo = ?, nombre = ?, precio = ?, stock = ?   WHERE idmenu = ?;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setBoolean(1, tMenu.getActivo());
			ps.setString(2, tMenu.getNombre());
			ps.setFloat(3, tMenu.getPrecio());
			ps.setInt(4, tMenu.getStock());
			ps.setInt(5, tMenu.getId());
			if (ps.executeUpdate() == 1)
				ok = tMenu.getId();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	public TMenu leerUno(Integer id) {
		TMenu tMenu = null;

		try {
			String consulta = "SELECT * FROM supermercado.menu WHERE idmenu = ? FOR UPDATE;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, id);
			ResultSet miResult = ps.executeQuery();

			if (miResult.next())
				tMenu = new TMenu(miResult.getInt("idmenu"), miResult.getBoolean("activo"),
						miResult.getString("nombre"), miResult.getFloat("precio"), miResult.getInt("stock"));

			ps.close();
			miResult.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tMenu;

	}

	public TMenu leerPorNombre(String nombre) {
		TMenu tMenu = null;

		try {
			String consulta = "SELECT * FROM supermercado.menu WHERE nombre = ?;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(consulta);

			s.setString(1, nombre);
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				tMenu = new TMenu(rs.getInt("idmenu"), rs.getBoolean("activo"), rs.getString("nombre"),
						rs.getFloat("precio"), rs.getInt("stock"));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tMenu;
	}

	public ArrayList<TMenu> leerTodos() {
		ArrayList<TMenu> lista = new ArrayList<TMenu>();

		try {
			String consulta = "SELECT * FROM supermercado.menu WHERE activo = 1";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {
				lista.add(new TMenu(rs.getInt("idmenu"), rs.getBoolean("activo"), rs.getString("nombre"),
						rs.getFloat("precio"), rs.getInt("stock")));
			}

			statement.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}
}