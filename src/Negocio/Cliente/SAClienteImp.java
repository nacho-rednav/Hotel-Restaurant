
package Negocio.Cliente;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Turno.TTurno;
import Negocio.Turno.Turno;
import Presentacion.Controller.Events;


public class SAClienteImp implements SACliente {
	
	public  Integer crearCliente(TCliente tcliente) {
		//int id = -1;
		if(tcliente.getNombre() == null ||tcliente.getEmail() == null ){
			return Events.ERROR_CLIENTE_DATOS;
		}
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		List<Cliente> check_nombre = em.createNamedQuery("Negocio.Cliente.Cliente.findBynombre", Cliente.class)
				.setParameter("nombre", tcliente.getNombre()).getResultList();
		Cliente cli;
		
		
		if (check_nombre == null || check_nombre.isEmpty()) { // Si no existe la creamos.
			cli = new Cliente();
			cli.transferToEntity(tcliente);
			em.persist(cli);
			
		}
		else{
			cli = check_nombre.get(0);
			if(cli.getActivo()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_CLIENTE_NOMBRE_YA_EXISTE;
			}
			else{
				cli.setActivo(true);
				cli.transferToEntity(tcliente);
				em.persist(cli);
			}
		}
		em.getTransaction().commit();
		em.close();
		return cli.getId();
		
	}
	
	public Integer eliminarCliente(Integer id) {
		//int res = -1;
		
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Cliente cliente = em.find(Cliente.class, id);	
		if(cliente == null){
			em.getTransaction().rollback();
			em.close();
			return Events.ERROR_CLIENTE_NO_EXISTE;
		}
		else{
			if(!cliente.getActivo()){//Ya esta dada de baja
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_CLIENTE_YA_DADO_DE_BAJA;
			}
			else if(!cliente.getReserva().isEmpty()){
				em.getTransaction().rollback();
				em.close();
				return Events.ERROR_CLIENTE_TIENE_RESERVA;
			}
			else{//Se da de baja
				em.lock(cliente, LockModeType.OPTIMISTIC); // Bloqueamos optimista, ya que vamos a modificar el objeto por lo que aumentar� el n�mero de versi�n.
				List<Turno> turnos = cliente.getTurnos();
				for (Turno t : turnos) { // damos de bajo el cliente en los turno que tenia vinculados (Se hace asi por que el poseedor de la relacion es turno)
					t.getClientes().remove(cliente);
					t.setCapacidad(t.getCapacidad() + 1); //Se aumenta la capacidad
				}
				cliente.setActivo(false);	// Baja l�gica.
				em.persist(cliente);
				try {
					transaction.commit();
					//res = 1;
				}
				catch(Exception e) {
					transaction.rollback();
				}
			}
		}
		//em.getTransaction().commit();
		em.close();
		return cliente.getId();
		
	}

	
	public Integer editarCliente(TCliente tcliente) {
		int res = -1;
		if(tcliente.getNombre() == null ||tcliente.getEmail()== null ){
			return Events.ERROR_CLIENTE_DATOS;
		}
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		List<Cliente> check_nombre = em.createNamedQuery("Negocio.Cliente.Cliente.findBynombre", Cliente.class)
				.setParameter("nombre", tcliente.getNombre()).getResultList();
		Cliente cliente = em.find(Cliente.class, tcliente.getID());
		
		if (cliente != null && cliente.getActivo() && check_nombre.isEmpty()) {
			
			em.lock(cliente, LockModeType.OPTIMISTIC);
			cliente.transferToEntity(tcliente);
			em.persist(cliente);
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

	
	public TCliente buscarUnCliente(Integer id) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente check_id = em.find(Cliente.class, id);
		if(check_id == null || !check_id.getActivo()){//No existe cliente con ese id
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		
		em.getTransaction().commit();
		em.close();
		return check_id.entityToTransfer();
	}

	
	public List<TCliente> buscarTodosCliente() {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		List<Cliente> clientes = em.createNamedQuery("Negocio.Cliente.Cliente.findAll", Cliente.class).getResultList();
		List<TCliente> result = new ArrayList<TCliente>();
		for (Cliente c : clientes){
			if(c.getActivo())
				result.add(c.entityToTransfer());
		}
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public Double calcularPrecioTurnosCliente(Integer id) {
		double result = 0;
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, id);
		if(cliente == null){//No existe cliente con ese id
			em.getTransaction().rollback();
			em.close();
			return (double) Events.ERROR_CLIENTE_DATOS;
		}
		for(int i =0; i < cliente.getTurnos().size();i++){
			
			result += cliente.getTurnos().get(i).getCalcularPrecio();
		}
		
		
		return result;
	}

	public ArrayList<TTurno> mostrarTurnosCliente(Integer idCliente) {
		EntityManager em = FactoriaEntityManager.getInstance().createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, idCliente);
		if(cliente == null) {// || (cliente != null && cliente.getTurnos().isEmpty())) {
			em.getTransaction().rollback();
			em.close();
			return null;
		}
		ArrayList<TTurno> listaTurnos = new ArrayList<TTurno>();
		for(Turno t : cliente.getTurnos()) {
			listaTurnos.add(t.entityToTransfer());
		}
		em.getTransaction().commit();
		em.close();
		return listaTurnos;
	}
}