
package Negocio.Servicio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;

import Negocio.Recepcionista.TRecepcionista;
import Negocio.Reserva.Reserva;
import Negocio.ReservaServicio.CompraServicio;

import javax.persistence.ManyToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Servicio.Servicio.findByid", query = "select obj from Servicio obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Servicio.Servicio.findBytipo", query = "select obj from Servicio obj where :tipo = obj.tipo "),
		@NamedQuery(name = "Negocio.Servicio.Servicio.findBydescripcion", query = "select obj from Servicio obj where :descripcion = obj.descripcion "),
		@NamedQuery(name = "Negocio.Servicio.Servicio.findByprecio", query = "select obj from Servicio obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.Servicio.Servicio.findByactivo", query = "select obj from Servicio obj where :activo = obj.activo ") })
public class Servicio implements Serializable {

	private static final long serialVersionUID = 0;

	public Servicio() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String tipo;

	private String descripcion;

	private Float precio;
	
	private Boolean activo;
	
	@Version
	int version;

	@OneToMany(mappedBy = "servicio")
	private Set<CompraServicio> comprasservicios;


	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public Boolean getActivo() {
		return activo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Set<CompraServicio> getComprasservicios() {
		return comprasservicios;
	}

	public void setComprasservicios(Set<CompraServicio> comprasservicios) {
		this.comprasservicios = comprasservicios;
	}
	public void transferToEntity(TServicio tServicio) {
		this.setDescripcion(tServicio.getDescripcion());
		this.setTipo(tServicio.getTipo());
		this.setPrecio(tServicio.getPrecio());
		this.setActivo(tServicio.getActivo()); 
	}

}