package Negocio.Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Habitacion.Habitacion;
import Negocio.Recepcionista.Recepcionista;
import Negocio.Recepcionista.TRecepcionista;
import Negocio.Reserva.Reserva;
import Negocio.Reserva.TReserva;
import Negocio.ReservaServicio.CompraServicio;
import Negocio.Turno.Turno;

public class SAServicioImp implements SAServicio {

	public Integer crear(TServicio tSercicio) {
		int id = -1;
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		List<Servicio> check_tipo = em.createNamedQuery("Negocio.Servicio.Servicio.findBytipo", Servicio.class)
				.setParameter("tipo", tSercicio.getTipo()).getResultList();
		Servicio servicio = null;

		if (check_tipo == null || check_tipo.isEmpty()) { // Si no existe la
															// creamos.
			servicio = new Servicio();
			servicio.transferToEntity(tSercicio);
			servicio.setActivo(true);
			em.persist(servicio);
			try {
				transaction.commit();
				id = servicio.getId();
			} catch (Exception e) {
				transaction.rollback();
			}
		} else { // Si existe
			servicio = check_tipo.get(0);
			if (!servicio.getActivo()) { // Y no est� activa, reactivar.
				servicio.transferToEntity(tSercicio);
				servicio.setActivo(true);
				em.persist(servicio);
				try {
					transaction.commit();
					id = servicio.getId();
				} catch (Exception e) {
					transaction.rollback();
				}
			} else {
				transaction.rollback(); // Y est� activa, rollback.
			}
		}

		em.close();

		return id;
	}

	public Integer eliminar(Integer id) {
		int res = -1;
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Servicio servicio = em.find(Servicio.class, id);

		if (servicio != null && servicio.getActivo()) {
			// Bloqueamos optimista, ya que vamos a modificar el objeto por lo
			// que aumentar� el n�mero de versi�n.
			em.lock(servicio, LockModeType.OPTIMISTIC);
			servicio.setActivo(false); // Baja l�gica.
			em.persist(servicio);
			try {
				transaction.commit();
				res = 1;
			} catch (Exception e) {
				transaction.rollback();
			}
		} else { // Si no existe o no est� activo rollback.
			transaction.rollback();
		}

		em.close();

		return res;
	}

	public Integer editar(TServicio tServicio) {
		int res = -1;
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Servicio servicio = em.find(Servicio.class, tServicio.getId());

		if (servicio != null && servicio.getActivo()) {
			em.lock(servicio, LockModeType.OPTIMISTIC);
			servicio.transferToEntity(tServicio);
			em.persist(servicio);
			try {
				transaction.commit();
				res = 1;
			} catch (Exception e) {
				transaction.rollback();
			}
		}

		else {
			transaction.rollback();
		}

		em.close();

		return res;
	}

	public TServicio mostrarPorId(Integer id) {
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Servicio servicio = em.find(Servicio.class, id);

		TServicio tServicio = null;
		if(servicio != null && servicio.getActivo()){
			tServicio = castEntityToTransfer(servicio);
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();

		em.close();

		return tServicio;
	}

	public TServicio mostrarPorTipo(String tipo) {
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Servicio> check_tipo = em.createNamedQuery("Negocio.Servicio.Servicio.findBytipo", Servicio.class)
				.setParameter("tipo", tipo).getResultList();
		TServicio tServicio = null;
		if(check_tipo != null && !check_tipo.isEmpty() && check_tipo.get(0).getActivo()){
			 tServicio = castEntityToTransfer(check_tipo.get(0));
			 em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();

		em.close();

		return tServicio;
	}

	public ArrayList<TServicio> mostrarTodos() {
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Servicio> tq = em.createNamedQuery("Negocio.Servicio.Servicio.findByactivo", Servicio.class).setParameter("activo", true);
		List<Servicio> listaServicios = tq.getResultList();
		ArrayList<TServicio> listaTServicios = new ArrayList<TServicio>();

		for (Servicio r : listaServicios) {
			TServicio tServicio = castEntityToTransfer(r);
			listaTServicios.add(tServicio);

		}

		em.getTransaction().commit();
		em.close();

		return listaTServicios;

	}

	public ArrayList<TReserva> mostrarReservasServicio(Integer id) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Servicio servicio = em.find(Servicio.class, id);
		if (servicio == null) {
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		ArrayList<TReserva> listaTReservas = new ArrayList<TReserva>();
		for (CompraServicio r : servicio.getComprasservicios()) {
			listaTReservas.add(r.getReserva().entityToTransfer());
		}
		em.getTransaction().commit();
		return listaTReservas;
	}

	private TServicio castEntityToTransfer(Servicio servicio) {
		return new TServicio(servicio.getId(), servicio.getTipo(), servicio.getDescripcion(), servicio.getPrecio(),
				servicio.getActivo());
	}
}