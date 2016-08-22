package dM;

import java.io.*;
import java.util.*;

import testECC.Client;

public class ClientDM 
{
	private static Set<Client> clients;
	
	private static void init()
	{
		clients = new HashSet<Client>();
	}
	
	public static Set<Client> getClients()
	{
		try{
			System.out.println("loading clients...");
			return loadClients("src/client_data.csv");
			
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Set<Client> loadClients(String filepath) throws IOException
	{
		init();
		
		String line = null;
		String[] token;
		
		long number;
		String name;
		String addressLine1;
		String addressLine2;
		String city;
		String state;
		String zip;
		String email;
		String contact;
		String invoicefrequency;
		String billingterms;
		String invoicegrouping;
		
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
	        	if ( token.length < 12)
	        		throw new IOException("Bad file format: " + filepath);
	        	else
	        	{
	        		number = Long.parseLong(token[0]);
	        		name = token[1];
	        		addressLine1 = token[2];
	        		addressLine2 = token[3];
	        		city = token[4];
	        		state = token[5];
	        		zip = token[6];
	        		email = token[7];
	        		contact = token[8];
	        		invoicefrequency = token[9];
	        		billingterms = token[10];
	        		invoicegrouping = token[11];
	        	}
	    	
	        	// Create a temporary client
	        	Client client = new Client();
	        	
	        	client.setId(number);
	        	client.setName(name);
	        	client.setCompanyAddressLine1(addressLine1);
	        	client.setCompanyAddressLine2(addressLine2);
	        	client.setCity(city);
	        	client.setState(state);
	        	client.setZip(zip);
	        	client.setClientEmail(email);
	        	client.setContact(contact);
	        	client.setBillingfrequency(invoicefrequency);
	        	client.setBillingterms(billingterms);
	        	client.setBillinginvoicinggroup(invoicegrouping);
	        	client.setClientStatus("active");
	        	
	        	// Add client to the set
	        	clients.add(client);
	        }
	    }
	    catch(FileNotFoundException ex) 
	    {
	        System.out.println(
	            "Unable to open file '" + 
	            		filepath + "'" + " at cur dir: " + System.getProperty("user.dir"));
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file '" 
	            + filepath + "'");
		}
	    finally
	    {
	    	// Always close files.
	    	if ( bufferedReader != null ) {
	    		bufferedReader.close();
	    	}
	    }
		
		return clients;
	}
}