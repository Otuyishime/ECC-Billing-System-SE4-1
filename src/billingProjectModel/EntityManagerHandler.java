package billingProjectModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum EntityManagerHandler {
	INSTANCE;
	private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("testECC");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction entityTransaction=entityManager.getTransaction();
    
    
    
    public EntityManagerFactory getEnitityManagerFactory() {
		return entityManagerFactory;
	}



	public void setEnitityManagerFactory(EntityManagerFactory enitityManagerFactory) {
		this.entityManagerFactory = enitityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public EntityTransaction getEntityTransaction() {
		return entityTransaction;
	}

	public void setEntityTransaction(EntityTransaction entityTransaction) {
		this.entityTransaction = entityTransaction;
	}

	public void open(){
    	if(!entityTransaction.isActive()){
    		entityTransaction.begin();
    	}
    	
    }
	
	public void shutdown(){
		entityManager.close();
		entityManagerFactory.close();
		
	}
}
