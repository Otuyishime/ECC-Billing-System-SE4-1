package dM;

import java.io.*;
import java.util.*;

import testECC.Employee;

class EmployeeWrapper
{
	private Employee employee;
	
	private ArrayList<Long> projectnumbers;

	public Employee getEmployee() {
		return employee;
	}


	public ArrayList<Long> getProjectnumbers() {
		return projectnumbers;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public void setProjectnumbers(ArrayList<Long> projectnumbers) {
		this.projectnumbers = projectnumbers;
	}


	public EmployeeWrapper(Employee employee) {
		this.projectnumbers = new ArrayList<Long>();
		this.employee = employee;
	}
}

public class EmployeeDM 
{
	private static Set<EmployeeWrapper> employees;

	private static void init()
	{
		employees = new HashSet<EmployeeWrapper>();
	}

	public static Set<EmployeeWrapper> getEmployees()
	{
		try{
			System.out.println("loading employees...");
			return loadEmployees("src/people_data.csv");

		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private static Set<EmployeeWrapper> loadEmployees(String filepath) throws IOException
	{
		init();
		
		String line = null;
		String[] token;
		
		String name;
		String title;
		Double payrate;
		String role;
		
		BufferedReader bufferedReader = null;
		
		try 
	    {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(filepath);

	        // Always wrap FileReader in BufferedReader.
	        bufferedReader = new BufferedReader(fileReader);
			
	        // read and discard headings in csv
	        line = bufferedReader.readLine();
	        
	        // ---------------- Read the project employee file -------------
	        Set<Employeeproject> employeeprojects = Employee_ProjectDM.getEmployeeProjects();
	        
	        while((line = bufferedReader.readLine()) != null) 
		    {
	        	//split data by comma
	        	token = line.split(",");
	        	if ( token.length < 4)
	        		throw new IOException("Bad file format: " + filepath);
	        	else
	        	{
	        		name = token[0];
	        		title = token[1];
	        		payrate = Double.parseDouble(token[2]);
	        		role = token[3];
	        		
	        		// Create a temporary employee
	        		Employee employee = new Employee(role, name, "email@ecc.com", title, payrate, "Active");
	        		
	        		// Create a temporary employee wrapper
	        		ArrayList<Long> projnumbers = findProjectForEmployee(employeeprojects, employee.getName());
	        		EmployeeWrapper employeewrapper = new EmployeeWrapper(employee);
	        		employeewrapper.setProjectnumbers(projnumbers);
	        		
	        		// Add the wrapper to the set
	        		employees.add(employeewrapper);
	        	}
	        }
	    }
	    catch(FileNotFoundException ex) 
	    {
	        System.out.println(
	            "Unable to open file '" + 
	            		filepath + "'" + " at cur dir: " + System.getProperty("user.dir"));    
	        throw ex;
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file '" 
	            + filepath + "'");  
	        throw ex;
		}
	    finally
	    {
	    	// Always close files.
	    	if ( bufferedReader != null ) {
	    		bufferedReader.close();
	    	}
	    }
		
	    return employees;
	}
	
	private static ArrayList<Long> findProjectForEmployee(Set<Employeeproject> employeesprojs, String employeename){
		ArrayList<Long> results = new ArrayList<Long>();
		for ( Employeeproject ep : employeesprojs){
			if ( ep.getEmployeename().equals(employeename)){
				results.add(ep.getProjectnumber());
			}
		}
		return results;
	}
}