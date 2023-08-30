/**
 * 
 */
package Negocio.Queries;

import Integracion.Queries.Query;
import Presentacion.Command.CommandFactoryImp;

public abstract class FactoriaQuery {
	private static FactoriaQuery instancia;

	public static FactoriaQuery getInstancia() {
		if (instancia == null) {
			instancia = new FactoriaQueryImp();
		}
		return instancia;
	}

	abstract public Query<?> nuevaQuery(String nombre);
}