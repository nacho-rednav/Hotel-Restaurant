package Negocio.FactoriaEntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriaEntityManager {
	private static EntityManagerFactory instance;
	private final static String MAIN_PERSISTENCE_UNIT_NAME = "cod";
	public static EntityManagerFactory getInstance(){
		if(instance == null)
			instance = Persistence.createEntityManagerFactory(MAIN_PERSISTENCE_UNIT_NAME);
		return instance;
	}
}
