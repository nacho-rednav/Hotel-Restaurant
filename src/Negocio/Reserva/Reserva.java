package Negocio.Reserva;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import Negocio.Cliente.Cliente;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import java.util.Set;
import Negocio.Servicio.Servicio;
import javax.persistence.ManyToMany;
import Negocio.Recepcionista.Recepcionista;
import Negocio.ReservaServicio.CompraServicio;
import Negocio.Habitacion.Habitacion;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Reserva.Reserva.findByid", query = "select obj from Reserva obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Reserva.Reserva.findBycliente", query = "select obj from Reserva obj where :cliente = obj.cliente "),
		@NamedQuery(name = "Negocio.Reserva.Reserva.findByrecepcionista", query = "select obj from Reserva obj where :recepcionista = obj.recepcionista "),
		@NamedQuery(name = "Negocio.Reserva.Reserva.findByhabitacion", query = "select obj from Reserva obj where :habitacion MEMBER OF obj.habitacion "),
		@NamedQuery(name = "Negocio.Reserva.Reserva.findBycodigo", query = "select obj from Reserva obj where :codigo = obj.codigo "),
		@NamedQuery(name = "Negocio.Reserva.Reserva.findAll", query = "select obj from Reserva obj ")
})
public class Reserva implements Serializable {
	
	private static final long serialVersionUID = 0;

	
	public Reserva() {
	}

	
	@GeneratedValue
	@Id
	private Integer id;
	@Version
	int version;
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "reserva")
	private List<CompraServicio> comprasservicios;
	
	@ManyToOne
	private Recepcionista recepcionista;
	
	@ManyToMany
	private List<Habitacion> habitacion;
	
	private Integer codigo;
	private Integer numDias;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	private Boolean activo;

	
	public Integer getID() {
		return this.id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getCodigo() {
		return this.codigo;
	}

	
	public void setCodigo(Integer cod) {
		this.codigo = cod;
	}

	
	public Integer getNumDias() {
		return this.numDias;
	}

	
	public void setNumDias(Integer num) {
		this.numDias = num;
	}

	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	
	public void setFechaInicio(Date fecha) {
		this.fechaInicio = fecha;
	}

	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
	
	/*public void setNumHabitaciones(Integer num) {
		this.numHabitaciones = num;
	}
	
	public Integer getNumHabitaciones() {
		return this.numHabitaciones;
	}*/

	
	public void transferToEntity(TReserva reserva) {
		this.id = reserva.getId();
		this.codigo = reserva.getCodigo();
		this.numDias = reserva.getNumDias();
		this.fechaInicio = reserva.getFechaInicio();
		this.activo = reserva.getActivo();
		//this.numHabitaciones = reserva.getNum_Habitaciones();
	}


	public TReserva entityToTransfer() {
		return new TReserva(id, codigo, numDias, cliente.getId(), recepcionista.getId(), getHabitacion().size(), fechaInicio, activo);
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}


	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}


	public List<Habitacion> getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(List<Habitacion> habitacion) {
		this.habitacion = habitacion;
	}


	public Integer getId() {
		return id;
	}


	public List<CompraServicio> getComprasservicios() {
		return comprasservicios;
	}


	public void setComprasservicios(List<CompraServicio> comprasservicios) {
		this.comprasservicios = comprasservicios;
	}
	
	public void addHabitacion(Habitacion h){
		habitacion.add(h);
	}
	
}