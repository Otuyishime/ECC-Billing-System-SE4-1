package billingProjectModel;

import java.util.List;

import javax.persistence.Query;

import dAO.CompanyDAO;
import dAO.CredentialDAO;
import dAO.EmployeeDAO;
import dM.EccDM_Helper;
import testECC.Company;
import testECC.Employee;
import utility.Credential;

public class MainFrameQuery extends AbstractQuery{

	
	public MainFrameQuery(){
		
	}
	
	public List<Employee> getEmployees(){
		open();
		Query query=EntityManagerHandler.INSTANCE.getEntityManager().createQuery("SELECT employee FROM " + Employee.class.getName() + " employee");
		List<Employee> employeeList =(List<Employee>)query.getResultList();
		return employeeList;
	}
	
	}
	
	
	
	

	


