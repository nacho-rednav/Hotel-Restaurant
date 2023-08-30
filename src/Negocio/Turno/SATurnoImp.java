package Negocio.Turno;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Cliente.Cliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Habitacion.Habitacion;
import Negocio.Reserva.Reserva;
import Presentacion.Controller.Events;

public class SATurnoImp implements SATurno {

	public Integer crearTurno(TTurno tturno) {
		int id = -1;
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		TypedQuery<Turno> tq = em.createNamedQuery("Negocio.Turno.Turno.findBydia", Turno.class);			// Llamamos a la query jpql en entidad turno de buscar por dia.
		tq.setParameter("dia", tturno.getDia());
		if (tturno instanceof TDesayuno) {
			tq.setParameter("type", Desayuno.class);
		}
		else{
			tq.setParameter("type", Comida.class);	
		}
		List<Turno> check_dia = tq.getResultList();															// Obtenemos el resultado de la tq.
		
		Turno t = null;
		if (check_dia == null || check_dia.isEmpty()) {																				// Si no existe la creamos.
			if (tturno instanceof TDesayuno) {
				Desayuno desayuno = new Desayuno();
				desayuno.transferToEntity(tturno);
				t = desayuno;
			}
			else if (tturno instanceof TComida) {
				Comida comida = new Comida();
				comida.transferToEntity(tturno);
				t = comida;
			}
			
			em.persist(t);
			try {
				transaction.commit();
				id = t.getId();
			}
			catch(Exception e) {
				transaction.rollback();
			}
		}
		else {																			// Si existe
			t = check_dia.get(0);
			if (!t.getActivo()) {																		// Y no est� activa, reactivar.
				t.setActivo(true);
				t.transferToEntity(tturno);
				em.persist(t);
				try {
					transaction.commit();
					id = t.getId();
				}
				catch(Exception e) {
					transaction.rollback();
				}
			}
			else {
				transaction.rollback();																		// Y est� activa, rollback.
				id = -1;
			}
		}
		
		em.close();
	
		return id;
	}

	public Integer eliminarTurno(Integer id) {
		int res = -1;
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Turno turno = em.find(Turno.class, id);																// Buscamos el tuno a eliminar.
		
		if (turno != null && turno.getActivo() && turno.getClientes().size() == 0) {															// Si el turno existe y est� activo y no tiene clientes.
			em.lock(turno, LockModeType.OPTIMISTIC);														// Bloqueamos optimista, ya que vamos a modificar el objeto por lo que aumentar� el n�mero de versi�n.
			turno.setActivo(false);																			// Baja l�gica.
			em.persist(turno);
			try {
				transaction.commit();
				res = 1;
			}
			catch(Exception e) {
				transaction.rollback();
			}
		}
		else {																								// Si no existe o no est� activo rollback.
			transaction.rollback();
		}
		
		em.close();
		
		return res;
	}

	public Integer editarTurno(TTurno tturno) {
		Integer res = -1;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Turno turno = em.find(Turno.class, tturno.getId());
		List<Turno> check_dia = null;
		
		if (!tturno.getDia().equals(turno.getDia())) {
			TypedQuery<Turno> tq = em.createNamedQuery("Negocio.Turno.Turno.findBydia", Turno.class);			// Llamamos a la query jpql en entidad turno de buscar por dia.
			tq.setParameter("dia", tturno.getDia());
			if (tturno instanceof TDesayuno) {
				tq.setParameter("type", Desayuno.class);
			}
			else{
				tq.setParameter("type", Comida.class);	
			}
			check_dia = tq.getResultList();	
		}
		
	
		if (check_dia == null || check_dia.isEmpty()) {	// Comprobramos que el dia al que queremos cambiar no tengo un desayuno o una comida
			if (turno != null && turno.getActivo()) { 
				turno.transferToEntity(tturno);
				try {
					transaction.commit();
					res = 1;
				}
				catch(Exception e) {
					transaction.rollback();
				}
			}
			else {
				transaction.rollback();
			}
		}
		else{
			transaction.rollback();
		}
		
		em.close();
				
		return res;
	}

	public TTurno buscarTurno(Integer id) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Turno turno = em.find(Turno.class, id);
		if (turno == null || !turno.getActivo()) {
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		TTurno tturno = castEntityToTransfer(turno);
		
		em.getTransaction().commit();
		em.close();

		return tturno;
	}

	public List<TTurno> buscarTodosTurno() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Turno> turnos = em.createNamedQuery("Negocio.Turno.Turno.findAll", Turno.class).getResultList();
		List<TTurno> tturnos = new ArrayList<TTurno>();
		
		for (Turno t : turnos) {
			if (t.getActivo()) {
			TTurno tturno = castEntityToTransfer(t);
			tturnos.add(tturno);
			}
		}
		
		em.getTransaction().commit();
		em.close();
		
		return tturnos;
	}

	public ArrayList<TCliente> mostrarClientesTurno(Integer idTurno) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Turno turno = em.find(Turno.class, idTurno);
		
		if (turno == null) {
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		
		ArrayList<TCliente> listaClientes = new ArrayList<TCliente>();
		for (Cliente c : turno.getClientes()) {
			listaClientes.add(c.entityToTransfer());
		}
		
		em.getTransaction().commit();
		em.close();
		
		return listaClientes;
	}
	
	private TTurno castEntityToTransfer(Turno turno) {
		TTurno tturno = null;
		
		if (turno instanceof Desayuno) {
			Desayuno desayuno = (Desayuno) turno;
			tturno = new TDesayuno(turno.getId(), turno.getDia(), turno.getCapacidad(), turno.getPrecio(), turno.getActivo(), desayuno.getTipo(), desayuno.getComplementoCafe(), desayuno.getCosteComplemento(), 
								   desayuno.getComplementoZumo(), desayuno.getComplementoZumo());
		}
		else if(turno instanceof Comida) {
			Comida comida = (Comida) turno;
			tturno = new TComida(turno.getId(), turno.getDia(), turno.getCapacidad(), turno.getPrecio(), turno.getActivo(), comida.getCosteServicio(), comida.getMenuDia());
		}
		return tturno;
	}
	
	public Integer aniadirClienteATurno(TTurnoCliente turnoCliente) {
		int res = -1;
		if(turnoCliente.getIdCliente() == null || turnoCliente.getIdTurno() == null)
			return res;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Cliente cli = em.find(Cliente.class, turnoCliente.getIdCliente(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Turno tur = em.find(Turno.class, turnoCliente.getIdTurno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(cli == null || tur == null || tur.getCapacidad() == 0){ // Si no existen, o no hay capacidad rollback
			em.getTransaction().rollback();
			em.close();
			return res;
		}
		
		// comprobamos si están vinculados. De estarlo se hace rollback
		if(cli.getTurnos().contains(tur)){
			em.getTransaction().rollback();
			em.close();
			return res;
		}
		
		tur.getClientes().add(cli);
		tur.setCapacidad(tur.getCapacidad() -1);
		em.getTransaction().commit();
		em.close();
		return 1;	
	}
	
	public Integer eliminarClienteATurno(TTurnoCliente turnoCliente) {
		int res = -1;
		if(turnoCliente.getIdCliente() == null || turnoCliente.getIdTurno() == null)
			return res;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		
		Cliente cli = em.find(Cliente.class, turnoCliente.getIdCliente(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Turno tur = em.find(Turno.class, turnoCliente.getIdTurno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(cli == null || tur == null || !cli.getTurnos().contains(tur)){
			em.getTransaction().rollback();
			em.close();
			return res;
		}
		
		tur.getClientes().remove(cli);
		tur.setCapacidad(tur.getCapacidad() +1); // Al desvincular, hay mas capacidad
		em.getTransaction().commit();
		em.close();
		return 1;
	}	
	
}