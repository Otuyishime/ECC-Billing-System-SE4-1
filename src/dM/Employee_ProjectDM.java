package dM;

import java.io.*;
import java.util.*;

class Employeeproject{
	private String employeename;
	
	private long projectnumber;

	public String getEmployeename() {
		return employeename;
	}

	public long getProjectnumber() {
		return projectnumber;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public void setProjectnumber(long projectnumber) {
		this.projectnumber = projectnumber;
	}

	public Employeeproject(String employeename, long projectnumber) {
		this.employeename = employeename;
		this.projectnumber = projectnumber;
	}
	
}
public class Employee_ProjectDM 
{
	private static Set<Employeeproject> employeeprojects;
	
	private static void init()
	{
		employeeprojects = new HashSet<Employeeproject>();
	}
	
	public static Set<Employeeproject> getEmployeeProjects()
	{
		try{
			System.out.println("loading employee projects...");
			return loadEmployeeProjects("src/project_person.csv");

		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private static Set<Employeeproject> loadEmployeeProjects(String filepath) throws IOException
	{
		init();
		
		String line = null;
		String[] token;
		
		String name;
		long projectnumber;
		
		BufferedReader bufferedReader = null;
		
		try 
	    {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(filepath);

	        // Always wrap FileReader in BufferedReader.
	        bufferedReader = new BufferedReader(fileReader);
			
	        // read and discard headings in csv
	        line = bufferedReader.readLine();
	        
	        while((line = bufferedReader.readLine()) != null) 
		    {
	        	//split data by comma
	        	token = line.split(",");
	        	if ( token.length < 2)
	        		throw new IOException("Bad file format: " + filepath);
	        	else
	        	{
	        		projectnumber = Long.parseLong(token[0]);
	        		name = token[1];
	        		
	        		// Create a temporary employee
	        		Employeeproject employeeproject = new Employeeproject(name, projectnumber);
	        		
	        		// Create a temporary employee wrapper
	        		employeeprojects.add(employeeproject);
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
		
	    return employeeprojects;
	}
}