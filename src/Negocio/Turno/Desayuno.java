package Negocio.Turno;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.Desayuno.findBytipo", query = "select obj from Desayuno obj where :tipo = obj.tipo "),
		@NamedQuery(name = "Negocio.Turno.Desayuno.findBycomplementoCafe", query = "select obj from Desayuno obj where :complementoCafe = obj.complementoCafe "),
		@NamedQuery(name = "Negocio.Turno.Desayuno.findBycosteComplemento", query = "select obj from Desayuno obj where :costeComplemento = obj.costeComplemento "),
		@NamedQuery(name = "Negocio.Turno.Desayuno.findBycomplementoZumo", query = "select obj from Desayuno obj where :complementoZumo = obj.complementoZumo "),
		@NamedQuery(name = "Negocio.Turno.Desayuno.findBycomplementoFruta", query = "select obj from Desayuno obj where :complementoFruta = obj.complementoFruta ") })
public class Desayuno extends Turno implements Serializable {

	private static final long serialVersionUID = 0;

	public Desayuno() {
	}

	private String tipo;

	private Boolean complementoCafe;

	private Float costeComplemento;

	private Boolean complementoZumo;

	private Boolean complementoFruta;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getComplementoCafe() {
		return complementoCafe;
	}
	
	public void setComplementoCafe(Boolean complementoCafe) {
		this.complementoCafe = complementoCafe;
	}

	public Float getCosteComplemento() {
		return costeComplemento;
	}

	public void setCosteComplemento(Float costeComplemento) {
		this.costeComplemento = costeComplemento;
	}

	public Boolean getComplementoZumo() {
		return complementoZumo;
	}

	public void setComplementoZumo(Boolean complementoZumo) {
		this.complementoZumo = complementoZumo;
	}

	public Boolean getComplementoFruta() {
		return complementoFruta;
	}

	public void setComplementoFruta(Boolean complementoFruta) {
		this.complementoFruta = complementoFruta;
	}
	
	@Override
	public void transferToEntity(TTurno tturno) {
		super.transferToEntity(tturno);
		TDesayuno tdesayuno = (TDesayuno) tturno;
		this.setTipo(tdesayuno.getTipo());
		this.setComplementoCafe(tdesayuno.getComplementoCafe());
		this.setCosteComplemento(tdesayuno.getCosteComplemento());
		this.setComplementoZumo(tdesayuno.getComplementoZumo());
		this.setComplementoFruta(tdesayuno.getComplementoFruta());
	}

	public Float getPrecio(){
		return super.getPrecio();
	}
	
	@Override
	public Float getCalcularPrecio(){
		return super.getPrecio() + costeComplemento;
	}
	
	@Override
	public TTurno entityToTransfer() {
		TTurno tturno = super.entityToTransfer();
		TDesayuno tDesayuno = new TDesayuno(tturno.getId(), tturno.getDia(), tturno.getCapacidad(), tturno.getPrecio(), tturno.getActivo(), tipo, complementoCafe, costeComplemento, complementoZumo, complementoFruta);
		return tDesayuno;
	}
	
}