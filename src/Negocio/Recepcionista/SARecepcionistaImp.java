package Negocio.Recepcionista;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;

public class SARecepcionistaImp implements SARecepcionista {

	public Integer crear(TRecepcionista tRecepcionista) {
		int id = -1;
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		TypedQuery<Recepcionista> tq = em.createNamedQuery("Negocio.Recepcionista.Recepcionista.findBynombre",
				Recepcionista.class);// Llamamos a la query jpql en entidad
										// turno de buscar por id.
		tq.setParameter("nombre", tRecepcionista.getNombre());
		Recepcionista recepcionista = null;
		List<Recepcionista> check_recepcionista = tq.getResultList();

		if (check_recepcionista == null || check_recepcionista.isEmpty()) { // Si no existe la creamos.
			Recepcionista r = new Recepcionista();
			r.transferToEntity(tRecepcionista);
			em.persist(r);
			try {
				transaction.commit();
				id = r.getId();
			} catch (Exception e) {
				transaction.rollback();
			}
		} else { // Si existe
			recepcionista = check_recepcionista.get(0);
			if (!recepcionista.getActivo()) { // Y no está activa, reactivar.
				recepcionista.setActivo(true);
				recepcionista.transferToEntity(tRecepcionista);
				em.persist(recepcionista);
				try {
					transaction.commit();
					id = recepcionista.getId();
				} catch (Exception e) {
					transaction.rollback();
				}
			} else {
				transaction.rollback(); // Y está activa, rollback.
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
		Recepcionista recepcionista = em.find(Recepcionista.class, id); 
		if (recepcionista != null && recepcionista.getActivo()) { 
			em.lock(recepcionista, LockModeType.OPTIMISTIC); 
			recepcionista.setActivo(false); // Baja lógica.
			em.persist(recepcionista);
			try {
				transaction.commit();
				res = 1;
			} catch (Exception e) {
				transaction.rollback();
			}
		} else { // Si no existe o no está activo rollback.
			transaction.rollback();
		}

		em.close();

		return res;
	}

	public Integer editar(TRecepcionista tRecepcionista) {
		int res = -1;
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		// Buscamos el recepcionista a editar.
		Recepcionista recepcionista = em.find(Recepcionista.class, tRecepcionista.getId());

		if (recepcionista != null && recepcionista.getActivo()) {
			// Bloqueamos optimista, ya que vamos a modificar el objeto por lo
			// que aumentará el número de versión.
			em.lock(recepcionista, LockModeType.OPTIMISTIC);
			recepcionista.transferToEntity(tRecepcionista);
			em.persist(recepcionista);
			try {
				transaction.commit();
				res = recepcionista.getId();
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

	public TRecepcionista mostrarPorId(Integer id) {
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Recepcionista recepcionista = em.find(Recepcionista.class, id);
		TRecepcionista tRecepcionista = null;
		if(recepcionista != null && recepcionista.getActivo()){
			tRecepcionista = castEntityToTransfer(recepcionista);
			em.getTransaction().commit();
		}
		else
			em.getTransaction().rollback();
		em.close();

		return tRecepcionista;
	}

	public TRecepcionista mostrarPorNombre(String nombre) {
		EntityManagerFactory emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Recepcionista> tq = em.createNamedQuery("Negocio.Recepcionista.Recepcionista.findBynombre",
				Recepcionista.class).setParameter("nombre", nombre);
		List<Recepcionista> lista = tq.getResultList();
		
		TRecepcionista tRecepcionista = null;
		
		if(lista != null && !lista.isEmpty() && lista.get(0).getActivo()){
			tRecepcionista = castEntityToTransfer(lista.get(0));
			em.getTransaction().commit();
		}	
		else
			em.getTransaction().rollback();
		em.close();

		return tRecepcionista;
	}

	public ArrayList<TRecepcionista> mostrarTodos() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Recepcionista> tq = em.createNamedQuery("Negocio.Recepcionista.Recepcionista.findByactivo", Recepcionista.class).setParameter("activo", true);
		List<Recepcionista> listaRecepcionistas = tq.getResultList();
		ArrayList<TRecepcionista> listaTRecepcionistas = new ArrayList<TRecepcionista>();

		for (Recepcionista r : listaRecepcionistas) {
			TRecepcionista tRecepcionista = castEntityToTransfer(r);
			listaTRecepcionistas.add(tRecepcionista);
		}

		em.close();

		return listaTRecepcionistas;
	}

	private TRecepcionista castEntityToTransfer(Recepcionista recepcionista) {
		return new TRecepcionista(recepcionista.getId(), recepcionista.getNumRecepcionista(), recepcionista.getNombre(), recepcionista.getSalario(),
				recepcionista.getActivo());
	}
}