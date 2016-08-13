package dM;

import java.io.*;
import java.util.*;

import testECC.Company;

public class CompanyDM 
{
	private static Set<Company> companies;
	
	private static void init()
	{
		companies = new HashSet<Company>();
	}
	
	public static Set<Company> getCompanies()
	{
		try{
			System.out.println("loading companies...");
			return loadCompanies("src/company_data.csv");
			
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private static Set<Company> loadCompanies(String filepath) throws IOException
	{
		init();
		
		String line = null;
		String[] token;
		
		String name;
		String addressLine1;
		String addressLine2;
		String city;
		String state;
		String zip;
		
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
	        	if ( token.length < 6)
	        		throw new IOException("Bad file format: " + filepath);
	        	else
	        	{
	        		name = token[0];
	        		addressLine1 = token[1];
	        		addressLine2 = token[2];
	        		city = token[3];
	        		state = token[4];
	        		zip = token[5];
	        		
	        		// Create a temporary company
	        		Company company = new Company();
	        		
	        		company.setName(name);
		        	company.setCompanyAddressline1(addressLine1);
		        	company.setCompanyAddressline2(addressLine2);
		        	company.setCity(city);
		        	company.setState(state);
		        	company.setZip(zip);
		        	
		        	// add company to the list
		        	companies.add(company);
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
		
		return companies;
	}
}