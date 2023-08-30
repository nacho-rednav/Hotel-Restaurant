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
import Negocio.Empleado.TEmpleado;
import Negocio.Queries.TQuery;

public class EmpDespRangoSalarial implements Query<TEmpleado> {

	public ArrayList<TEmpleado> execute(TQuery param) {
		ArrayList<TEmpleado> lista = new ArrayList<TEmpleado>();
		try {
			String consulta = "SELECT * FROM supermercado.empleado WHERE sueldo > ? AND sueldo < ? AND activo = 0 FOR UPDATE;";

			Transaction transaction = TransactionManager.getInstance().getTransaction();
			Connection connection = (Connection) transaction.getResource();

			PreparedStatement ps = connection.prepareStatement((consulta));

			ps.setFloat(1, param.getMinimo());
			ps.setFloat(2, param.getMaximo());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new TEmpleado(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getBoolean("activo"),
						rs.getFloat("sueldo"), rs.getString("dni"), rs.getString("horario"), rs.getString("idioma"),
						rs.getBoolean("titulo")));
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