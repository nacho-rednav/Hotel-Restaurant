
package Negocio.Empleado;

public class TCocinero extends TEmpleado {

	public TCocinero(Integer id, String nombre, Boolean activo, Float sueldo, String dni, String horario,
			Boolean titulo) {
		super(id, nombre, activo, sueldo, dni, horario, null, titulo);
	}

	public Boolean getTitulo() {
		return titulo;
	}

	public void setTitulo(Boolean titulo) {
		this.titulo = titulo;
	}
}