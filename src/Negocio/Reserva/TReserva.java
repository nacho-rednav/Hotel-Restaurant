package Negocio.Reserva;

import java.util.Date;


public class TReserva {
	
	private Integer id;
	private Integer codigo;
	private Integer numDias;
	private Integer id_Cliente;
	private Integer id_Recepcionista;
	private Integer num_Habitaciones;
	private Date fechaInicio;
	private Boolean activo;

	
	public TReserva(){}
	
	public TReserva(Integer id, Integer codigo, Integer numDias, Integer id_Cliente, Integer id_Recepcionista, Integer num_Habitaciones, Date fechaInicio, Boolean activo){
		this.id = id;
		this.codigo = codigo;
		this.numDias = numDias;
		this.id_Cliente = id_Cliente;
		this.id_Recepcionista = id_Recepcionista;
		this.num_Habitaciones = num_Habitaciones;
		this.fechaInicio = fechaInicio;
		this.activo = activo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getId() {
		return this.id;
	}

	
	public void setCodigo(Integer cod) {
		this.codigo = cod;
	}

	
	public Integer getCodigo() {
		return this.codigo;
	}

	
	public void setNumDias(Integer num) {
		this.numDias = num;
	}

	
	public Integer getNumDias() {
		return this.numDias;
	}

	
	public void setFechaInicio(Date fecha) {
		this.fechaInicio = fecha;
	}

	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public Boolean getActivo() {
		return this.activo;
	}


	public Integer getId_Cliente() {
		return id_Cliente;
	}


	public void setId_Cliente(Integer id_Cliente) {
		this.id_Cliente = id_Cliente;
	}


	public Integer getId_Recepcionista() {
		return id_Recepcionista;
	}


	public void setId_Recepcionista(Integer id_Recepcionista) {
		this.id_Recepcionista = id_Recepcionista;
	}

	public Integer getNum_Habitaciones() {
		return num_Habitaciones;
	}

	public void setNum_Habitaciones(Integer num_Habitaciones) {
		this.num_Habitaciones = num_Habitaciones;
	}
	
}