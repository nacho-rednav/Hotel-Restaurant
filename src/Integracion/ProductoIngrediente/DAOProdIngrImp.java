package Integracion.ProductoIngrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;
import Negocio.Producto.TProductoIngrediente;

public class DAOProdIngrImp implements DAOProdIngr {

	public Collection<TProducto> leerProdsPorIngr(Integer idIngrediente) {
		ArrayList<TProducto> lista = new ArrayList<TProducto>();
		try {
			String consulta = "SELECT * FROM supermercado.productoingrediente A JOIN supermercado.producto B ON A.idProducto = B.idProducto WHERE A.idIngrediente = ? FOR UPDATE;";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();
			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, idIngrediente);
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

	public void desvincularIngrsDeProd(Integer idProducto) {
		try {
			String consulta = "DELETE FROM supermercado.productoingrediente WHERE idProducto = ?;";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();
			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, idProducto);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Collection<TIngrediente> leerIngrsPorProd(Integer idProducto) {
		ArrayList<TIngrediente> lista = new ArrayList<TIngrediente>();
		try {
			String consulta = "SELECT * FROM supermercado.productoingrediente A JOIN supermercado.ingrediente B ON  A.idIngrediente = B.idIngrediente WHERE A.idProducto = ? FOR UPDATE;";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();
			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, idProducto);
			ResultSet miResult = ps.executeQuery();

			while (miResult.next()) {
				lista.add(new TIngrediente(miResult.getInt("idIngrediente"), miResult.getBoolean("alergeno"),
						miResult.getString("nombre"), miResult.getBoolean("activo")));
			}

			ps.close();
			miResult.close();
			return lista;

		} catch (SQLException e) {
			e.printStackTrace();

			return lista;
		}
	}

	public void vincular(TProductoIngrediente tProdIngr) {
		try {
			String consulta = "INSERT INTO supermercado.productoingrediente(idProducto,idIngrediente) VALUES (?,?);";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, tProdIngr.getIdProducto());
			ps.setInt(2, tProdIngr.getIdIngrediente());
			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Boolean contiene(TProductoIngrediente tProdIngr) {
		boolean contiene = false;

		try {
			String consulta = "SELECT COUNT(*) FROM supermercado.productoingrediente WHERE idProducto = ? AND idIngrediente = ? FOR UPDATE;";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();
			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, tProdIngr.getIdProducto());
			ps.setInt(2, tProdIngr.getIdIngrediente());
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

	public void desvincular(TProductoIngrediente tProdIngr) {
		try {
			String consulta = "DELETE FROM supermercado.productoingrediente WHERE idProducto = ? AND idIngrediente = ?";
			TransactionManager tManager = TransactionManager.getInstance();
			Transaction transaction = tManager.getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement(consulta);

			ps.setInt(1, tProdIngr.getIdProducto());
			ps.setInt(2, tProdIngr.getIdIngrediente());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}