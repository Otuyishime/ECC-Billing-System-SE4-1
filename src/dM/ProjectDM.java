package dM;

import java.io.*;
import java.util.*;

import testECC.Project;

class ProjectWrapper{
	
	private Project project;
	
	private long clientnumber;
	
	private String projectmanager;
	
	public Project getProject() {
		return project;
	}
	
	public long getClientnumber() {
		return clientnumber;
	}
	
	public String getProjectmanager() {
		return projectmanager;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void setClientnumber(long clientnmber) {
		this.clientnumber = clientnmber;
	}
	
	public void setProjectmanager(String projectmanager) {
		this.projectmanager = projectmanager;
	}
	
	public ProjectWrapper(Project project, long clientnumber,
			String projectmanager) {
		this.project = project;
		this.clientnumber = clientnumber;
		this.projectmanager = projectmanager;
	}
}

public class ProjectDM 
{
	private static Set<ProjectWrapper> projects;

	private static void init()
	{
		projects = new HashSet<ProjectWrapper>();
	}

	public static Set<ProjectWrapper> getProjects()
	{
		try{
			System.out.println("loading projects...");
			return loadProjects("src/project_data.csv");

		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private static Set<ProjectWrapper> loadProjects(String filepath) throws IOException
	{
		init();
		
		String line = null;
		String[] token;
		
		long clientnumber;
		long projectnumber;
		String name;
		String startdate;
		String enddate;
		String status;
		String projectmanager;
		String clientcontact;
		Double budget;
		
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
	        	if ( token.length < 9)
	        		throw new IOException("Bad file format: " + filepath);
	        	else
	        	{
	        		clientnumber = Long.parseLong(token[0]);
	        		projectnumber = Long.parseLong(token[1]);
	        		name  = token[2];
	        		startdate = token[3];
	        		enddate = token[4];
	        		status = token[5];
	        		projectmanager = token[6];
	        		clientcontact = token[7];
	        		budget = Double.parseDouble(token[8]);
	        		
	        		// Create a temporary project
	        		Project project = new Project(projectnumber, name, startdate, enddate,
	        				status, clientcontact, budget) ;
	        		
	        		// create a temporary project wrapper
	        		ProjectWrapper projWrap = new ProjectWrapper(project, clientnumber, projectmanager);
	        		
	        		// Add wrapper to the set
	        		projects.add(projWrap);
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
		
		return projects;
	}
}