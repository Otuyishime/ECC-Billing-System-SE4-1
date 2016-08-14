package billingProjectModel;

import java.util.List;

import javax.persistence.Query;

import testECC.Employee;

public class RemoveEmployeeQuery extends AbstractQuery{
	
	public List<Employee>getAllEmployee(){
		
		open();
		Query query=EntityManagerHandler.INSTANCE.getEntityManager().createQuery("SELECT empl FROM employees empl");
		List<Employee> employeeList =query.getResultList();
		return employeeList;	
	}
	
	public void updateEmployee(Employee employee, String email,String name,Double payrate,String role,String title){
		open();
		employee.setEmail(email);
		employee.setName(name);
		employee.setPayrate(payrate);
		employee.setRole(role);
		employee.setTitle(title);
	}

}
