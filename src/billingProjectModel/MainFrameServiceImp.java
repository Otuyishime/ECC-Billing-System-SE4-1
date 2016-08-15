package billingProjectModel;

import java.util.List;

import testECC.Company;
import testECC.Employee;
import dAO.CompanyDAO;

public class MainFrameServiceImp implements MainFrameService {
	private MainFrameQuery mainFrameQuery;
	
    public void MainFrameServiceImpl(){
    this.mainFrameQuery=new MainFrameQuery();		
	}
	public List<Employee> getAllEmployee() {
		return this.mainFrameQuery.getEmployees();
		
	} 
	public void shutdown() {
    this.mainFrameQuery.shutdown();		
	}

}
