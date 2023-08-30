package Negocio.Turno;

import java.util.ArrayList;
import java.util.List;

import Negocio.Cliente.TCliente;

public interface SATurno {

	public Integer crearTurno(TTurno turno);

	public Integer eliminarTurno(Integer id);

	public Integer editarTurno(TTurno turno);

	public TTurno buscarTurno(Integer id);

	public List<TTurno> buscarTodosTurno();

	public Integer aniadirClienteATurno(TTurnoCliente turnoCliente);

	public Integer eliminarClienteATurno(TTurnoCliente turnoCliente);

	public ArrayList<TCliente> mostrarClientesTurno(Integer idTurno);
}