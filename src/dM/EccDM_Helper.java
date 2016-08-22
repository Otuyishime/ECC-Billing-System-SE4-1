package dM;

import java.util.*;

import javax.persistence.EntityTransaction;

import testECC.*;
import utility.Credential;
import dAO.*;

public class EccDM_Helper 
{	
	private static ArrayList<Company> companies;
	private static ArrayList<Client> clients;
	private static ArrayList<Credential> credentials;
	private static Set<Project> projects;
	private static Set<Employee> employees;
	
	private static Set<ProjectWrapper> projectWraps;
	private static Set<EmployeeWrapper> employeeWraps;
	
	private static void init(){ 
		projects = new HashSet<Project>();
		employees = new HashSet<Employee>();
		credentials = new ArrayList<Credential>();
	}
	
	public static void loadData()
	{
		init();
		persistData();
	}
	
	private static boolean getDataFromFiles()
	{
		companies = new ArrayList<Company>(CompanyDM.getCompanies());
		System.out.println("Number of companies: " + companies.size());
		
		System.out.println();
		clients = new ArrayList<Client>(ClientDM.getClients());
		System.out.println("Number of clients: " + clients.size());
		
		System.out.println();
		projectWraps = ProjectDM.getProjects();
		System.out.println("Number of projects: " + projectWraps.size());
		
		System.out.println();
		employeeWraps = EmployeeDM.getEmployees();
		System.out.println("Number of employee with projects: " + employeeWraps.size());
		
		if ( !companies.isEmpty() && !clients.isEmpty() && !projectWraps.isEmpty() && !employeeWraps.isEmpty()){
			return true;
		}
		return false;
	}
	
	private static boolean connectObjects()
	{
		boolean ondatasuccess =  getDataFromFiles();
		if ( ondatasuccess){
			
			System.out.println();
			System.out.println("Setting all employees...");
			// Set all employees --------------
			for( EmployeeWrapper ew : employeeWraps){
				// get employee
				Employee employee = ew.getEmployee();
				
				// Assign default credentials
				// user name : name of the employee
				// password : ecc
				// assign credential --> employee
				Credential credential = new Credential();
				credential.setUsername(employee.getName().toLowerCase());
				credential.setPassword("ecc");
				
				employee.setCredentials(credential);
				// assign credential <-- employee
				credential.setEmployee(employee);
				// add credential to the set
				credentials.add(credential);
				
				// check for assigned projects
				if ( !ew.getProjectnumbers().isEmpty()){
					for( Long projnum : ew.getProjectnumbers()){
						// find all projects assigned to this employee
						for ( Project proj : findProjectWithNumberFromWrapper(projnum)){
							// assign project to employee
							employee.getProjects().add(proj);
						}
					}
				}
				
				// assign company --> employee
				employee.setCompany(companies.get(0));
				
				// assign company <-- employee
				companies.get(0).getEmployees().add(employee);
										
				// assign employee to the set
				employees.add(employee);
			}
			
			System.out.println();
			System.out.println("Setting all projects...");
			// Set all projects --------------
			for ( ProjectWrapper pw : projectWraps){
				
				//get project
				Project project = pw.getProject();
				if ( !findEmployeeFromEmployeeWrapper(project.getId()).isEmpty() ){
					
					// find all employees assigned to the project
					for( Employee employee : findEmployeeFromEmployeeWrapper(project.getId())){
						// assign this employee to the project
						project.getEmployees().add(employee);
					}
				}
				
				// assign client to the project
				if ( findClientWithNumber(pw.getClientnumber()) != null){
					Client client = findClientWithNumber(pw.getClientnumber()); 
					project.setClient(client);
				}
				
				// assign company --> project
				project.setCompany(companies.get(0));
				
				// assign company <-- project
				companies.get(0).getProjects().add(project);
				
				// assign project to the set
				projects.add(project);
			}
			
			System.out.println();
			System.out.println("Setting all clients...");
			// Set all clients --------------
			for ( Client client : clients){
				// assign project to the clients using the project wraps
				for ( ProjectWrapper pw : projectWraps){
					// if the numbers are equal we have the client's project
					if ( pw.getClientnumber() == client.getId()){
						
						// assign the project --> client
						client.getProjects().add(pw.getProject());
					}
				}
				
				// assign company --> client
				client.setCompany(companies.get(0));
				
				// assign company <-- client
				companies.get(0).getClients().add(client);
			}
			
			if ( !companies.isEmpty() && !clients.isEmpty() && !projects.isEmpty() && !employees.isEmpty()){
				ondatasuccess = true;
			}
			else{
				ondatasuccess = false;
			}
		}
		
		return ondatasuccess;
	}
	
	private static void persistData(){
		if( connectObjects()){
			
			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();
			
			// persist all the data
			// persist the company ------------
			System.out.println();
			System.out.println("Persisting the company...");
			
			EM.INSTANCE.getEM().persist(companies.get(0));
			
			// persist the clients ------------
			System.out.println();
			System.out.println("Persisting all clients...");
			for( Client client : clients){
				EM.INSTANCE.getEM().persist(client);
			}
			
			// persist the projects ------------
			System.out.println();
			System.out.println("Persisting all projects...");
			for( Project project : projects){
				EM.INSTANCE.getEM().persist(project);
			}
			
			// persist the employees ------------
			System.out.println();
			System.out.println("Persisting all employees...");
			for( Employee employee : employees){
				
				EM.INSTANCE.getEM().persist(employee);
			}
			
			// persist the credentials ------------
			System.out.println();
			System.out.println("Persisting all credentials...");
			for( Credential credential : credentials){
				
				EM.INSTANCE.getEM().persist(credential);
			}
			
			userTransaction.commit();
		}
	}
	
	/**
	 * This method is used to find a project assigned to the employee
	 * It uses the project wrapper's project and compares it with a project number
	 * from the employee wrapper or client
	 * @param projectnumber
	 * @return project
	 */
	private static ArrayList<Project> findProjectWithNumberFromWrapper(long projectnumber){
		ArrayList<Project> results = new ArrayList<Project>();
		
		if ( projectWraps != null){
			for ( ProjectWrapper pw : projectWraps){
				if ( pw.getProject().getId() == projectnumber){
					results.add(pw.getProject());
				}
			}
		}
		return results;
	}
	
	private static Client findClientWithNumber(long clientnumber){
		if ( projectWraps != null){
			for ( Client cl : clients){
				if ( cl.getId() == clientnumber){
					return cl;
				}
			}
		}
		return null;
	}
	
	private static ArrayList<Employee> findEmployeeFromEmployeeWrapper(long projectnumber){
		ArrayList<Employee> results = new ArrayList<Employee>();
		
		if ( employeeWraps != null){
			for ( EmployeeWrapper ew : employeeWraps){
				if ( !ew.getProjectnumbers().isEmpty()){
					for ( Long pn : ew.getProjectnumbers()){
						if ( pn == projectnumber ){
							results.add(ew.getEmployee()); 
						}
					}
				}
			}
		}
		return results;
	}
}