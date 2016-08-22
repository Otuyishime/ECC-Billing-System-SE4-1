package dM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityTransaction;

import dAO.EM;
import testECC.Company;
import testECC.Employee;
import testECC.TimeSheet;
import testECC.TimeSheetLine;

public class LoadTimeSheetLinesDM {

	private static Set<TimeSheetLine> timeSheetLines;
	private static Set<Employee> employees;

	private static void init()
	{
		timeSheetLines = new HashSet<TimeSheetLine>();
		employees = new HashSet<Employee>();
	}

	private static Set<TimeSheetLine> getTimeSheetLines()
	{
		try{
			System.out.println("loading timesheet lines...");
			return loadTimeSheetLines("src/timesheetline_data.csv");

		}catch(IOException ex){
			ex.printStackTrace();
			return timeSheetLines;
		}
	}

	private static Set<TimeSheetLine> loadTimeSheetLines(String filepath) throws IOException
	{
		init();

		String line = null;
		String[] token;

		String date;
		String name;
		int hours;
		boolean verified;
		String projectname;

		BufferedReader bufferedReader = null;
		try 
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(filepath);

			// Always wrap FileReader in BufferedReader.
			bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) 
			{
				//split data by comma
				token = line.split(",");
				if ( token.length < 5)
					throw new IOException("Bad file format: " + filepath);
				else
				{
					date = token[0];
					name = token[1];
					hours = Integer.parseInt(token[2]);
					verified = token[3].isEmpty()? false : true;
					projectname = token[4];

					// Create a temporary Time Sheet Line
					TimeSheetLine timeSheetLine = new TimeSheetLine();

					timeSheetLine.setDate(date);
					timeSheetLine.setEmployee(name);
					timeSheetLine.setHours(hours);
					timeSheetLine.setReviewed(verified);
					timeSheetLine.setProject(projectname);

					// add company to the list
					timeSheetLines.add(timeSheetLine);
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
		
		return timeSheetLines;
	}

	private static boolean connectObjects(Company company)
	{
		if (!getTimeSheetLines().isEmpty()){
			for(TimeSheetLine timesheetline : timeSheetLines){
				TimeSheet timesheet = company.getEmployeeByName(timesheetline.getEmployee()).getCurrentTimeSheet();
				timesheetline.setTimesheet(timesheet);
				timesheet.getTimesheetlines().add(timesheetline);
			}
		}
		return true;
	}

	private static void persistData(Company company){
		if( connectObjects(company)){
			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			System.out.println();
			System.out.println("Persisting all timesheetlines...");
			for(Employee employee : company.getEmployees()){
				EM.INSTANCE.getEM().persist(employee.getCurrentTimeSheet());
				
				for( TimeSheetLine timesheetline : employee.getCurrentTimeSheet().getTimesheetlines()){
					EM.INSTANCE.getEM().persist(timesheetline);
				}
			}
			
			userTransaction.commit();
		}
	}
	
	public static void loadTimeSheetLines(Company company){
		//System.out.println("Number of timesheet lines: " + getTimeSheetLines().size());
		
		// load the time sheet lines
		getTimeSheetLines();
		persistData(company);
	}
}
