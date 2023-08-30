
package Negocio.Habitacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Reserva.Reserva;
import Negocio.Reserva.TReserva;
import Presentacion.Controller.Events;


public class SAHabitacionImp implements SAHabitacion {
	
	public synchronized Integer crearHabitacion(THabitacion habitacion) {
		if(habitacion.getNumero() <= 0 || habitacion.getPlanta() < 0)
			return Events.ERROR_HABITACION_DATOS;
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Habitacion> check_numero = em.createNamedQuery("Negocio.Habitacion.Habitacion.findBynumero", Habitacion.class).setParameter("numero", habitacion.getNumero()).getResultList();
		Habitacion hab;
		if(check_numero == null || check_numero.isEmpty()){
			if(habitacion.getClass().equals((TBasica.class))){
				hab = new Basica();
			}
			else{
				hab = new Suite();
			}
			hab.transferToEntity(habitacion);
			em.persist(hab);
		}
		else{
			hab = check_numero.get(0);
			if(hab.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_HABITACION_NUM_YA_EXISTE;
			}
			else{
				hab.transferToEntity(habitacion);
				hab.setActivo(true);
			}
		}
		em.getTransaction().commit();
		em.close();
		return hab.getID();
	}

	public Integer eliminarHabitacion(Integer id) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Habitacion check_id = em.find(Habitacion.class, id);
		Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
		if(check_id == null){//No existe habitacion con ese id
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_HABITACION_NO_EXISTE;
		}
		else{
			if(!check_id.getActivo()){//Ya esta dada de baja
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_HABITACION_YA_DADA_DE_BAJA;
			}
			else{//Se da de baja
				for(Reserva r : check_id.getReservas()){//Check no estaba en reserva activa
					em.lock(r, LockModeType.OPTIMISTIC);
					cal1.setTime(r.getFechaInicio());
			        cal1.add(Calendar.DATE, r.getNumDias());
			        cal2.setTime(new Date());
					if(cal1.after(cal2)){//Si la reserva aun no ha terminado
						em.getTransaction().rollback();
						em.close();
						return Events.ERROR_HABITACION_RESERVADA;
					}
				}
				check_id.setActivo(false);
			}
		}
		em.getTransaction().commit();
		em.close();
		return check_id.getID();
	}

	public Integer editarHabitacion(THabitacion habitacion) {
		if(habitacion.getNumero() <= 0 || habitacion.getPlanta() < 0)
			return Events.ERROR_HABITACION_DATOS;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Habitacion> check_numero = em.createNamedQuery("Negocio.Habitacion.Habitacion.findBynumero", Habitacion.class).setParameter("numero", habitacion.getNumero()).getResultList();
		if(check_numero == null || check_numero.isEmpty()){ //Le puso numero nuevo, buscamos por id
			Habitacion hab = em.find(Habitacion.class, habitacion.getId());
			hab.transferToEntity(habitacion);
		}
		else{
			if(check_numero.get(0).getID() != habitacion.getId()){//Puso numero ya utilizado
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_HABITACION_NUM_YA_EXISTE;
			}
			else{ //No cambio el n�mero, actualizamos objeto
				check_numero.get(0).transferToEntity(habitacion);
			}
		}
		em.getTransaction().commit();
		em.close();
		return Events.MODIFICAR_HABITACION_OK;
	}

	public THabitacion buscarUnaHabitacionPorId(Integer id) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Habitacion check_id = em.find(Habitacion.class, id);
		if(check_id == null){//No existe habitacion con ese id
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		
		em.getTransaction().commit();
		em.close();
		return check_id.entityToTransfer();
	}

	public List<THabitacion> buscarTodosHabitaciones() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Habitacion> habitaciones = em.createNamedQuery("Negocio.Habitacion.Habitacion.findAll", Habitacion.class).getResultList();
		List<THabitacion> result = new ArrayList<THabitacion>();
		for (Habitacion h : habitaciones){
			if(h.getActivo())
				result.add(h.entityToTransfer());
		}
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public THabitacion buscarUnaHabitacionPorNumero(Integer num) {
		if(num <= 0){
			return null;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Habitacion> check_numero = em.createNamedQuery("Negocio.Habitacion.Habitacion.findBynumero", Habitacion.class).setParameter("numero", num).getResultList();
		if(check_numero == null || check_numero.isEmpty()){//No existe habitacion con ese numero
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		em.getTransaction().commit();
		em.close();
		return check_numero.get(0).entityToTransfer();
	}
	
	public List<TReserva> mostrarReservasHabitacion(Integer id) {
		if(id == null || id <= 0){
			return null;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Habitacion hab = em.find(Habitacion.class, id);
		if(hab == null){
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		ArrayList<TReserva> res = new ArrayList<TReserva>();
		for(Reserva r : hab.getReservas()){
			res.add(r.entityToTransfer());
		}
		em.getTransaction().commit();
		em.close();
		return res;
	}
}