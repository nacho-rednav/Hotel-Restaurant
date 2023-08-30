package Integracion.Factura;

import Negocio.Factura.TFactura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.Almacendeldominio.Transaction;
import Integracion.Almacendeldominio.TransactionManager;

public class DAOFacturaImp implements DAOFactura {


	public Integer abrir(TFactura tFactura) {
		int res = -1;

		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "INSERT INTO supermercado.factura (activo, idmesa, idempleado) VALUES (?, ?, ?);";

			PreparedStatement s = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			s.setBoolean(1, true);
			s.setInt(2, tFactura.getIdMesa());
			s.setInt(3, tFactura.getIdEmpleado());

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

	public Integer cerrar(TFactura tFactura) {
		int res = -1;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "UPDATE supermercado.factura SET activo = ?, idmesa = ?, importe = ?, horaDeCobro = ?, idempleado= ? WHERE idfactura = ?;";

			PreparedStatement s = connection.prepareStatement(consulta);

			s.setBoolean(1, tFactura.getActivo());
			s.setInt(2, tFactura.getIdMesa());
			s.setFloat(3, tFactura.getImporte());
			s.setDate(4, tFactura.getHoraCobro());
			s.setInt(5, tFactura.getIdEmpleado());
			s.setInt(6, tFactura.getId());

			if (s.executeUpdate() == 1)
				res = tFactura.getId();

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer editar(TFactura tFactura) {
		int res = -1;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();
			String consulta = "UPDATE supermercado.factura SET idmesa = ?, idempleado= ? WHERE idfactura = ?;";

			PreparedStatement s = connection.prepareStatement(consulta);

			s.setInt(1, tFactura.getIdMesa());
			s.setInt(2, tFactura.getIdEmpleado());
			s.setInt(3, tFactura.getId());

			if (s.executeUpdate() == 1)
				res = tFactura.getId();

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public TFactura leerUno(Integer id) {
		TFactura res = null;
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s;

			s = connection.prepareStatement(("SELECT * FROM supermercado.factura WHERE idfactura = ? FOR UPDATE;"));

			s.setInt(1, id);
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				res = new TFactura(rs.getInt("idfactura"), rs.getInt("idmesa"), rs.getInt("idempleado"),
						rs.getFloat("importe"), rs.getDate("horaDeCobro"), rs.getBoolean("activo"));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Collection<TFactura> leerTodos() {
		ArrayList<TFactura> lista = new ArrayList<TFactura>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection.prepareStatement(("SELECT * FROM supermercado.factura FOR UPDATE;"));

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				lista.add(new TFactura(rs.getInt("idfactura"), rs.getInt("idmesa"), rs.getInt("idempleado"),
						rs.getFloat("importe"), rs.getDate("horaDeCobro"), rs.getBoolean("activo")));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}


	public Collection<TFactura> leerTodosPorMesa(Integer idMesa) {
		ArrayList<TFactura> lista = new ArrayList<TFactura>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection
					.prepareStatement(("SELECT * FROM supermercado.factura WHERE idmesa = ? FOR UPDATE;"));

			s.setInt(1, idMesa);
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

	public Collection<TFactura> leerTodosPorEmpleado(Integer idEmpleado) {
		ArrayList<TFactura> lista = new ArrayList<TFactura>();
		try {
			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement s = connection
					.prepareStatement(("SELECT * FROM supermercado.factura WHERE idEmpleado = ? FOR UPDATE;"));

			s.setInt(1, idEmpleado);
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				lista.add(new TFactura(rs.getInt("idfactura"), rs.getInt("idmesa"), rs.getInt("idempleado"),
						rs.getFloat("importe"), rs.getDate("horaDeCobro"), rs.getBoolean("activo")));
			}

			s.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return lista;
	}

}