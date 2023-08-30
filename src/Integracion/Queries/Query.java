
package Integracion.Queries;

import java.util.ArrayList;

import Negocio.Queries.TQuery;

public interface Query<T> {

	public ArrayList<T> execute(TQuery param);
}