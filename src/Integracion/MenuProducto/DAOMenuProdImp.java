/**
 * 
 */
package Integracion.MenuProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProducto;
import Negocio.Producto.TProducto;

public class DAOMenuProdImp implements DAOMenuProd {

	public Collection<TProducto> leerProdsPorMenu(Integer idMenu) {
		ArrayList<TProducto> lista = new ArrayList<TProducto>();
		try {
			String consulta = "SELECT * FROM supermercado.menuproducto A JOIN supermercado.producto B ON A.idProducto = B.idProducto WHERE A.idMenu = ? FOR UPDATE;";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();
			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, idMenu);
			ResultSet miResult = ps.executeQuery();

			while (miResult.next()) {
				lista.add(new TProducto(miResult.getInt("idProducto"), miResult.getBoolean("activo"),
						miResult.getString("nombre")));
			}

			ps.close();
			miResult.close();

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();

			return lista;
		}
	}

	public Boolean contiene(TMenuProducto tMenuProd) {
		boolean contiene = false;
		try {
			String consulta = "SELECT COUNT(*) FROM supermercado.menuproducto WHERE idProducto = ? AND idMenu = ? FOR UPDATE";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, tMenuProd.getIdProducto());
			ps.setInt(2, tMenuProd.getIdMenu());
			ResultSet miResult = ps.executeQuery();
			if (miResult.next()) {
				if (miResult.getInt(1) != 0)
					contiene = true;
			}

			ps.close();
			miResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contiene;
	}

	public void desvincularProdsDeMenu(Integer idMenu) {
		try {
			String consulta = "DELETE FROM supermercado.menuproducto WHERE idMenu = ?";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, idMenu);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Collection<TMenu> leerMenusPorProd(Integer idProd) {
		ArrayList<TMenu> lista = new ArrayList<TMenu>();
		try {
			String consulta = "SELECT * FROM supermercado.menuproducto A JOIN supermercado.menu B ON  A.idMenu = B.idmenu WHERE A.idProducto = ? FOR UPDATE";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, idProd);
			ResultSet miResult = ps.executeQuery();
			while (miResult.next()) {
				lista.add(new TMenu(miResult.getInt("idmenu"), miResult.getBoolean("activo"),
						miResult.getString("nombre"), miResult.getFloat("precio"), miResult.getInt("stock")));
			}

			ps.close();
			miResult.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void vincular(TMenuProducto tMenuProd) {
		try {
			String consulta = "INSERT INTO supermercado.menuproducto(idMenu,idProducto) VALUES (?,?);";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, tMenuProd.getIdMenu());
			ps.setInt(2, tMenuProd.getIdProducto());
			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void desvincular(TMenuProducto tMenuProd) {
		try {
			String consulta = "DELETE FROM supermercado.menuproducto WHERE idProducto = ? AND idMenu = ?";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setInt(1, tMenuProd.getIdProducto());
			ps.setInt(2, tMenuProd.getIdMenu());
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}