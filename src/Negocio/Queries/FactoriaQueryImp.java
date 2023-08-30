/**
 * 
 */
package Negocio.Queries;

import Integracion.Queries.EmpDespRangoSalarial;
import Integracion.Queries.MenusRangoPrecio;
import Integracion.Queries.Query;

public class FactoriaQueryImp extends FactoriaQuery {

	@Override
	public Query<?> nuevaQuery(String nombre) {
		Query<?> query = null;

		switch (nombre) {
		case "rango_precio_empleado":
			query = new EmpDespRangoSalarial();
			break;
		case "rango_precio_menu":
			query = new MenusRangoPrecio();
			break;
		}

		return query;
	}
}