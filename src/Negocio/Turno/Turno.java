package Negocio.Turno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import Negocio.Cliente.Cliente;
import Negocio.Cliente.TCliente;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import java.sql.Time;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.Turno.findAll", query = "select obj from Turno obj"),
		@NamedQuery(name = "Negocio.Turno.Turno.findByid", query = "select obj from Turno obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Turno.Turno.findBydia", query = "select obj from Turno obj where :dia = obj.dia AND TYPE(obj) = :type"),
		@NamedQuery(name = "Negocio.Turno.Turno.findByprecio", query = "select obj from Turno obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.Turno.Turno.findBycapacidad", query = "select obj from Turno obj where :capacidad = obj.capacidad "),
		@NamedQuery(name = "Negocio.Turno.Turno.findByactivo", query = "select obj from Turno obj where :activo = obj.activo ") })
public class Turno implements Serializable {
	
	private static final long serialVersionUID = 0;

	public Turno() {
		
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "turnoID")			// Para indicar el nombre de la columna en la bd (Mejor que sea distinto al atributo).
	private Integer id;
	@Version
	int version;
	@ManyToMany(fetch = FetchType.LAZY)			
	List<Cliente> clientes;
	
	@Temporal(TemporalType.DATE)
	private Date dia;

	private Float precio;
	
	private Integer capacidad;

	private Boolean activo;
	
	public Integer getCapacidad(){
		return capacidad;
	}
	
	public void setCapacidad(Integer capacidad){
		this.capacidad= capacidad;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Boolean getActivo() {
		return activo;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void transferToEntity(TTurno tturno) {
		this.setDia(tturno.getDia());
		this.setCapacidad(tturno.getCapacidad());
		this.setPrecio(tturno.getPrecio());
		this.setActivo(tturno.getActivo());
	}
	
	public TTurno entityToTransfer() {
		return new TTurno( id,  dia, precio, activo, capacidad);
	}

	public Float getCalcularPrecio() {
		return precio;
	}
}