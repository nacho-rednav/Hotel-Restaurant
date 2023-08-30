/**
 * 
 */
package Negocio.Reserva;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import Negocio.Cliente.Cliente;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Habitacion.Habitacion;
import Negocio.Habitacion.THabitacion;
import Negocio.Recepcionista.Recepcionista;
import Negocio.ReservaServicio.CompraServicio;
import Negocio.ReservaServicio.ReservaServicioId;
import Negocio.ReservaServicio.TCompraServicio;
import Negocio.Servicio.Servicio;
import Negocio.Servicio.TServicio;
import Presentacion.Controller.Events;


public class SAReservaImp implements SAReserva {
	
	public synchronized Integer crearReserva(TReserva data) {
		if(data.getNumDias() == null || data.getId_Cliente() == null || data.getId_Recepcionista() == null
				|| data.getFechaInicio() == null || data.getNum_Habitaciones() == null ||
				data.getNumDias() <= 0 || data.getId_Cliente() <= 0 || data.getId_Recepcionista() <= 0
				|| data.getNum_Habitaciones() <= 0){
			return Events.ERROR_RESERVA_DATOS;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente check_cli = em.find(Cliente.class, data.getId_Cliente(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Recepcionista check_rec = em.find(Recepcionista.class, data.getId_Recepcionista(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		List<Reserva> check_cod = em.createNamedQuery("Negocio.Reserva.Reserva.findBycodigo", Reserva.class).setParameter("codigo", data.getCodigo()).getResultList();
		
		if(check_cod == null || check_cod.isEmpty() || !check_cod.get(0).getActivo()){
			if(check_cli == null || check_rec == null 
					|| !check_cli.getActivo() || !check_rec.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_ENTITDADES;
			}
			
			//Buscar habitaciones
			java.sql.Date inicio = new java.sql.Date(data.getFechaInicio().getTime());
			Calendar cal = Calendar.getInstance();
	        cal.setTime(data.getFechaInicio());
	        cal.add(Calendar.DATE, data.getNumDias());
	        java.sql.Date fin = new java.sql.Date(cal.getTimeInMillis());
			List<Habitacion> habs = em.createNamedQuery("Habitacion.findReservables", Habitacion.class).setParameter("inicio", inicio).setParameter("fin", fin).getResultList();
			
	        if(habs == null || habs.size() < data.getNum_Habitaciones()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_HABS;
			}
			List<Habitacion> selected_habs = new ArrayList<Habitacion>();
			for(int i = 0; i < data.getNum_Habitaciones(); i++){
				em.lock(habs.get(i), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				selected_habs.add(habs.get(i));
			}
			
			Reserva res = new Reserva();
			if(check_cod != null && !check_cod.isEmpty()){
				res = check_cod.get(0); //Si estoy reactivando reserva ya creada
				data.setId(res.getId());
			}
			res.transferToEntity(data);
			res.setHabitacion(selected_habs);
			res.setCliente(check_cli);
			res.setRecepcionista(check_rec);
			if(check_cod == null || check_cod.isEmpty()) em.persist(res);//Solo persist si es una reserva creada de cero
			em.getTransaction().commit();
			em.close();
			return res.getId();
		}
		else{
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_CODIGO_YA_EXISTE;
		}
	}
	
	public Integer eliminarReserva(Integer id) {
		
		if(id == null || id <= 0){
			return Events.ERROR_RESERVA_DATOS;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, id, LockModeType.OPTIMISTIC);
		
		if(res == null || !res.getActivo()){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_ENTITDADES;
		}
		
		/*for(Habitacion h : res.getHabitacion()){
			em.lock(h, LockModeType.OPTIMISTIC_FORCE_INCREMENT); //No haría falta pk la baja reserva es independiente de lo que ocurra con sus habitaciones
		}*/ 
		res.getHabitacion().clear();//Eliminar relaciones con habitaciones
		
		for(CompraServicio c : res.getComprasservicios()){ //Eliminar relacion compra por parte servicio
			em.remove(c);
		}
		res.setActivo(false);
		
		em.getTransaction().commit();
		em.close();
		return Events.BAJA_RESERVA_OK;
	}

	//Se puede editar cliente, recepcionista, codigo, numDias y fecha
	public Integer editarReserva(TReserva data) {
		
		if(data == null || data.getId() <= 0 || 
				data.getId_Cliente() <= 0 || data.getId_Recepcionista() <= 0
				|| data.getCodigo() <= 0 || data.getFechaInicio() == null){
			return Events.ERROR_RESERVA_DATOS;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Reserva res = em.find(Reserva.class, data.getId());
		if(!res.getActivo()){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_DATOS;
		}
		if(res.getCodigo() != data.getCodigo()){
			List<Reserva> check_cod = em.createNamedQuery("Negocio.Reserva.Reserva.findBycodigo", Reserva.class).setParameter("codigo", data.getCodigo()).getResultList();
			if(check_cod != null && !check_cod.isEmpty() && check_cod.get(0).getId() != res.getId()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_CODIGO_YA_EXISTE;
			}
		}
		if(data.getId_Cliente() != res.getCliente().getId()){
			Cliente nuevo_cli = em.find(Cliente.class, data.getId_Cliente(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if(nuevo_cli == null || !nuevo_cli.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_ENTITDADES;
			}
			res.setCliente(nuevo_cli);
		}
		if(data.getId_Recepcionista() != res.getRecepcionista().getId()){
			Recepcionista nuevo_rec = em.find(Recepcionista.class, data.getId_Recepcionista(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if(nuevo_rec == null || !nuevo_rec.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_ENTITDADES;
			}
			res.setRecepcionista(nuevo_rec);
		}
		res.transferToEntity(data);
		
		em.getTransaction().commit();
		em.close();
		return Events.MODIFICAR_RESERVA_OK;	
	}
	
	public TReserva mostrarReserva(Integer id){
		if(id == null || id <= 0){
			return null;
		}
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, id);
		if(res == null || !res.getActivo()){
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		return res.entityToTransfer();
	}
	
	public TReservaCompleta mostrarReservaCompleta(Integer id) {
		if(id == null || id <= 0){
			return null;
		}
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, id);
		if(res == null){
			em.getTransaction().rollback();
			em.close();
			return null;
		}	
		
		ArrayList<THabitacion> habs = new ArrayList<THabitacion>();
		ArrayList<TCompraServicio> servs = new ArrayList<TCompraServicio>();
		
		for(Habitacion h : res.getHabitacion()){
			habs.add(h.entityToTransfer());
		}
		for(CompraServicio s : res.getComprasservicios()){
			servs.add(s.entityToTransfer());
		}
		TReservaCompleta result = new TReservaCompleta(res.entityToTransfer(), servs, habs);
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public Collection<TReserva> mostrarTodasReservas() {
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Reserva> reserva = em.createNamedQuery("Negocio.Reserva.Reserva.findAll", Reserva.class).getResultList();
		List<TReserva> res = new ArrayList<TReserva>();
		for (Reserva r : reserva){
			if(r.getActivo())
				res.add(r.entityToTransfer());
		}
		em.getTransaction().commit();
		em.close();
		return res;
	}

	
	public Integer compraServicio(TLineaPedidoServicios data) {
		if(data.getIdReserva() <= 0){
			return Events.ERROR_RESERVA_DATOS;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, data.getIdReserva(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(res == null || !res.getActivo()){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_ENTITDADES;
		}
		
		for(Pair<TServicio, Date> p : data.getIdsServicios()){
			if(p.getFirst() == null || p.getSecond() == null || p.getFirst().getId() <= 0){
				if(p.getSecond() == null) System.out.println(10);
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_DATOS;
			}
			Servicio serv = em.find(Servicio.class, p.getFirst().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if(serv == null || !serv.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_ENTITDADES;
			}
			CompraServicio compra = new CompraServicio(res, serv, p.getSecond());
			if(res.getComprasservicios().contains(compra)){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_SERVICIO_COMPRADO;
			}
			compra.setReserva(res);
			compra.setServicio(serv);
			em.persist(compra);
		}
		
		em.getTransaction().commit();
		em.close();
		return Events.COMPRA_SERVICIO_RESERVA_OK;
	}

	
	public Integer devolucionServicio(TLineaPedidoServicios data) {
		int id_res = data.getIdReserva();
		int id_serv;
		
		if(id_res <= 0 || data.getIdsServicios().isEmpty()){
			return Events.ERROR_RESERVA_DATOS;
		}
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Reserva res = em.find(Reserva.class, id_res, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

		for(Pair<TServicio, Date> p : data.getIdsServicios()){
			id_serv = p.getFirst().getId();
			Servicio serv = em.find(Servicio.class, id_serv, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if(res == null || serv == null || !res.getActivo() || !serv.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_ENTITDADES;
			}
			
			CompraServicio compra = em.find(CompraServicio.class, new ReservaServicioId(id_res, id_serv));
			if(compra == null){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_RESERVA_COMPRA_INEXISTENTE;
			}
			
			em.remove(compra);
		}
		
		
		em.getTransaction().commit();
		em.close();
		return Events.DEVOLUCION_SERVICIO_RESERVA_OK;
	}

	
	public Integer desvincularHabitacionReserva(TLineaPedidoHabitacion data) {
		if(data.getIdReserva() == null || data.getIdHab() == null
				|| data.getIdReserva() <= 0 || data.getIdHab() <= 0)
			return Events.ERROR_RESERVA_DATOS;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Reserva res = em.find(Reserva.class, data.getIdReserva(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Habitacion hab = em.find(Habitacion.class, data.getIdHab(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(res == null || hab == null){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_ENTITDADES;
		}
		
		if(!res.getHabitacion().contains(hab)){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_RESERVA_VINCULACION;
		}
		
		//hab.getReservas().remove(res);
		res.getHabitacion().remove(hab);
		em.getTransaction().commit();
		em.close();
		return Events.DESVINCULAR_HABITACION_RESERVA_OK;
	}	
	
}