/**
 * 
 */
package Integracion.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.Menu.TMenu;
import Negocio.Queries.TQuery;

public class MenusRangoPrecio implements Query<TMenu> {

	public ArrayList<TMenu> execute(TQuery param) {
		ArrayList<TMenu> lista = new ArrayList<TMenu>();
		try {
			String consulta = "SELECT * FROM supermercado.menu WHERE precio > ? AND precio < ? FOR UPDATE;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement((consulta));

			ps.setFloat(1, param.getMinimo());
			ps.setFloat(2, param.getMaximo());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new TMenu(rs.getInt("idmenu"), rs.getBoolean("activo"), rs.getString("nombre"),
						rs.getFloat("precio"), rs.getInt("stock")));
			}

			ps.close();
			rs.close();

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}