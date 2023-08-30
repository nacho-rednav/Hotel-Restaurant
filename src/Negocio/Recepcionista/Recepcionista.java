package Negocio.Recepcionista;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import Negocio.Reserva.Reserva;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findByid", query = "select obj from Recepcionista obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findBynumRecepcionista", query = "select obj from Recepcionista obj where :numRecepcionista = obj.numRecepcionista "),
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findBynombre", query = "select obj from Recepcionista obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findBysalario", query = "select obj from Recepcionista obj where :salario = obj.salario "),
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findByreserva", query = "select obj from Recepcionista obj where :reserva MEMBER OF obj.reserva "),
		@NamedQuery(name = "Negocio.Recepcionista.Recepcionista.findByactivo", query = "select obj from Recepcionista obj where :activo = obj.activo ") })
public class Recepcionista implements Serializable {

	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "recepcionistaID") // Para indicar el nombre de la columna en la bd (Mejor que sea distinto al atributo).
	private Integer id;
	
	private Integer numRecepcionista;
	
	private String nombre;
	
	private Float salario;
	
	private Boolean activo;
	
	@Version
	int version;
	
	@OneToMany(mappedBy = "recepcionista")
	private Set<Reserva> reserva;


	
	public Integer getId() {
		return id;
	}

	
	public Integer getNumRecepcionista() {
		return numRecepcionista;
	}

	
	public String getNombre() {
		return nombre;
	}

	public Float getSalario() {
		return salario;
	}

	
	public Boolean getActivo() {
		return activo;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public void setNumRecepcionista(Integer numRecepcionista) {
		this.numRecepcionista = numRecepcionista;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setSalario(Float salario) {
		this.salario = salario;
	}

	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Recepcionista() {
		
	}

	public void transferToEntity(TRecepcionista tRecepcionista) {
		this.setNumRecepcionista(tRecepcionista.getNumRecepcionista());
		this.setNombre(tRecepcionista.getNombre());
		this.setSalario(tRecepcionista.getSalario());
		this.setActivo(tRecepcionista.getActivo());
	}


	public Set<Reserva> getReserva() {
		return reserva;
	}


	public void setReserva(Set<Reserva> reserva) {
		this.reserva = reserva;
	}
}