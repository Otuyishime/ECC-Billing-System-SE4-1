package dAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// entity manger 
public enum EM {
	INSTANCE;
	private  EntityManagerFactory entityManagerFactory;
	private EntityManager em;
	
	public  void initEM()
	{
	this.entityManagerFactory =  Persistence.createEntityManagerFactory("testECC");
    this.em = entityManagerFactory.createEntityManager();
	}
	
	public  EntityManager getEM()
	{
		if (this.em==null)
		{ 
			initEM();
		
		}
		return em;
	}
	
	public  void close()
	{
		this.em.close();
	    this.entityManagerFactory.close();
	}
}
