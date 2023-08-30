package Negocio.Turno;

public class TTurnoCliente {
	private Integer idTurno;
	private Integer idCliente;
	
	public TTurnoCliente(Integer idTurno, Integer idCliente) {
		this.idTurno = idTurno;
		this.idCliente = idCliente;
	}
	
	public Integer getIdTurno(){
		return this.idTurno;
	}
	
	public Integer getIdCliente(){
		return this.idCliente;
	}
	
	public void setIdCliente(Integer idCliente){
		this.idCliente = idCliente;
	}
	
	public void serIdTurno(Integer idTurno){
		this.idTurno = idTurno;
	}
}
