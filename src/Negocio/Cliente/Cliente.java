package Negocio.Cliente;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import Negocio.Habitacion.THabitacion;
import Negocio.Reserva.Reserva;
import Negocio.Turno.Turno;

import javax.persistence.OneToMany;
import javax.persistence.Version;


@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Cliente.Cliente.findByid", query = "select obj from Cliente obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Cliente.Cliente.findBynombre", query = "select obj from Cliente obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Cliente.Cliente.findByemail", query = "select obj from Cliente obj where :email = obj.email "),
		@NamedQuery(name = "Negocio.Cliente.Cliente.findByreserva", query = "select obj from Cliente obj where :reserva MEMBER OF obj.reserva "),
		@NamedQuery(name = "Negocio.Cliente.Cliente.findAll", query = "select obj from Cliente obj ") })

public  class Cliente implements Serializable {

	private static final long serialVersionUID = 0;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Version
	int version;
	@ManyToMany(mappedBy = "clientes", fetch=FetchType.LAZY)			// Turno es el poseedor de la relacion porque tenemos una referencia a la lista de clientes en Turno.
	private List<Turno> turnos;
	
	public Cliente() {
		
	}
	
	private String nombre;
	private String email;
	private Boolean activo;

	@OneToMany(mappedBy = "cliente")
	private Set<Reserva> reserva;

	public Integer getId() {
		return id;
	}
	//public  TCliente entityToTransfer();
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getActivo() {
		return activo;
	}
	
	public void setId(Integer id_Cliente) {
		this.id = id_Cliente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void transferToEntity(TCliente cliente) {
		this.setNombre(cliente.getNombre());
		this.setEmail(cliente.getEmail());
		this.setActivo(cliente.getActivo());
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Set<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(Set<Reserva> reserva) {
		this.reserva = reserva;
	}
	public TCliente entityToTransfer() {
		return new TCliente( id,  activo,  nombre, email);
	}
}