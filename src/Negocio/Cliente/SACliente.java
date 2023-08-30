
package Negocio.Cliente;

import java.util.ArrayList;
import java.util.List;

import Negocio.Turno.TTurno;


public interface SACliente {

	public Integer crearCliente(TCliente cliente);

	
	public Integer eliminarCliente(Integer id);

	
	public Integer editarCliente(TCliente tCliente);

	
	public TCliente buscarUnCliente(Integer id);

	
	public List<TCliente> buscarTodosCliente();

	
	public Double calcularPrecioTurnosCliente(Integer id);
	
	public ArrayList<TTurno> mostrarTurnosCliente(Integer idCliente);
}