package billingProjectModel;

import testECC.Employee;

public class AddEmployeeQuery extends AbstractQuery{
	
	public void addEmployee(){
		
	}
	
	public void insertEmployee(Employee employee){
		open();
		EntityManagerHandler.INSTANCE.getEntityManager().persist(employee);
		EntityManagerHandler.INSTANCE.getEntityTransaction().commit();	
	}

}
