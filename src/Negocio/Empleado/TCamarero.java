
package Negocio.Empleado;

public class TCamarero extends TEmpleado {

	public TCamarero(Integer id, String nombre, Boolean activo, Float sueldo, String dni, String horario,
			String idioma) {
		super(id, nombre, activo, sueldo, dni, horario, idioma, new Boolean(null));
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
	}
}