/**
 * 
 */
package Integracion.FacturaMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;
import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaDePedido;

public class DAOFacturaMenuImp implements DAOFacturaMenu {


	@Override
	public List<TLineaDePedido> leerMenusEnFactura(Integer idFactura) {
		ArrayList<TLineaDePedido> lista = new ArrayList<TLineaDePedido>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection
					.prepareStatement(("SELECT * FROM supermercado.facturamenu WHERE idfactura = ? FOR UPDATE;"));

			s.setInt(1, idFactura);
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				lista.add(new TLineaDePedido(rs.getInt("idmenu"), rs.getInt("cantidad"), rs.getInt("idfactura"),
						rs.getFloat("precio")));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return lista;
	}

	@Override
	public List<TFactura> leerFacturasPorMenu(Integer idMenu) {
		ArrayList<TFactura> lista = new ArrayList<TFactura>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(
					("SELECT * FROM supermercado.facturamenu A JOIN supermercado.factura B ON A.idfactura = B.idfactura WHERE A.idmenu = ? FOR UPDATE;"));

			s.setInt(1, idMenu);
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				lista.add(new TFactura(rs.getInt("idfactura"), rs.getInt("idmesa"), rs.getInt("idempleado"),
						rs.getFloat("importe"), rs.getDate("horaDeCobro"), rs.getBoolean("activo")));
			}

			s.close();
			rs.close();

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	public TLineaDePedido leerUno(TLineaDePedido info) {
		TLineaDePedido res = null;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			if (info.getIdFactura() == null)
				System.out.println("ñe");
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(
					("SELECT * FROM supermercado.facturamenu WHERE idmenu = ? AND  idfactura = ? FOR UPDATE;"));

			s.setInt(1, info.getIdMenu());
			s.setInt(2, info.getIdFactura());
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				res = new TLineaDePedido(rs.getInt("idmenu"), rs.getInt("cantidad"), rs.getInt("idfactura"),
						rs.getFloat("precio"));
			}
			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return res;
	}

	@Override
	public void vincular(TLineaDePedido info) {
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "INSERT INTO supermercado.facturamenu (idmenu, cantidad, idfactura, precio) VALUES (?, ?, ?, ?);";

			PreparedStatement s = connection.prepareStatement(consulta);
			s.setInt(1, info.getIdMenu());
			s.setInt(2, info.getCantidad());
			s.setInt(3, info.getIdFactura());
			s.setFloat(4, info.getPrecio());

			s.executeUpdate();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editarCantidadMenu(TLineaDePedido info) {
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(
					("UPDATE supermercado.facturamenu SET cantidad = ? WHERE idmenu = ? AND idfactura = ?;"));

			s.setInt(1, info.getCantidad());
			s.setInt(2, info.getIdMenu());
			s.setInt(3, info.getIdFactura());

			s.executeUpdate();
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void desvincular(TLineaDePedido info) {
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection
					.prepareStatement(("DELETE FROM supermercado.facturamenu WHERE idmenu = ? AND idfactura = ?;"));

			s.setInt(1, info.getIdMenu());
			s.setInt(2, info.getIdFactura());

			s.executeUpdate();
			s.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}