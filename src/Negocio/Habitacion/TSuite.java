package Negocio.Habitacion;


public class TSuite extends THabitacion {
	protected Float sobrecoste;

	public TSuite(Integer planta, Integer numero, Boolean activo, Float sobrecoste) {
		super(planta, numero, activo);
		this.sobrecoste = sobrecoste;
	}
	
	public TSuite(Integer id, Integer planta, Integer numero, Boolean activo, Float sobrecoste) {
		super(id, planta, numero, activo);
		this.sobrecoste = sobrecoste;
	}
	
	public TSuite() {
	}

	public Float getSobrecoste() {
		return this.sobrecoste;
	}

	
	public void setSobrecoste(Float sc) {
		this.sobrecoste = sc;
	}
}