package Negocio.Habitacion;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;

import java.util.List;
import Negocio.Reserva.Reserva;

import javax.persistence.ManyToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
		@NamedQuery(name = "Negocio.Habitacion.Habitacion.findBynumero", query = "select obj from Habitacion obj where :numero = obj.numero "),
		@NamedQuery(name = "Negocio.Habitacion.Habitacion.findAll", query = "select obj from Habitacion obj "),
		@NamedQuery(name = "Habitacion.findReservables", query = "SELECT h FROM Habitacion h "
				+ "WHERE h NOT IN (SELECT h2 FROM Habitacion h2 JOIN h2.reserva res "
				+ "WHERE (res.fechaInicio BETWEEN :inicio AND :fin) OR "
				+ "(FUNCTION('ADDDAYS', res.fechaInicio, res.numDias) BETWEEN :inicio AND :fin)) "
				+ "AND h.activo = 1 ")})
public abstract class Habitacion implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@GeneratedValue
	@Id
	protected Integer id;
	@Version
	int version;
	protected Integer numero;
	protected Integer planta;
	protected Boolean activo;
	
	@ManyToMany(mappedBy = "habitacion")
	private List<Reserva> reserva;
	

	public Habitacion(){
		
	}
	
	public Habitacion(Integer id, Integer numero, Integer planta, Boolean activo) {
		this.id = id;
		this.numero = numero;
		this.planta = planta;
		this.activo = activo;
	}

	
	public void transferToEntity(THabitacion habitacion) {
		this.id = habitacion.getId();
		this.numero = habitacion.getNumero();
		this.planta = habitacion.getPlanta();
		this.activo = habitacion.getActivo();
	}
	
	public abstract THabitacion entityToTransfer();
	
	public List<Reserva> getReservas(){
		return reserva;
	}
	
	public Integer getID() {
		return this.id;
	}

	
	public Integer getPlanta() {
		return this.planta;
	}

	
	public Integer getNumero() {
		return this.numero;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public void setPlanta(Integer p) {
		this.planta = p;
	}

	
	public void setNumero(Integer num) {
		this.numero = num;
	}

	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public Boolean getActivo() {
		return this.activo;
	}
	
}