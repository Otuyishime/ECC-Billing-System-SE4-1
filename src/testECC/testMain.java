package testECC;

import java.util.List;
import java.util.Scanner;

import utility.Credential;
import utility.Settings;
import dAO.*;
import dM.EccDM_Helper;

public class testMain {

	private static List<Credential> credentials;
	private static List<Company> companies;

	public static void initSystem(){
		credentials = CredentialDAO.listCredentials();
		companies = CompanyDAO.listCompanies();

		if( companies.isEmpty()){
			EccDM_Helper.loadData();
		}
	}

	public static Employee logIn(Credential credential){
		for( Credential cred : credentials){
			if ( cred.getUsername().equals(credential.getUsername()) &&
					cred.getPassword().equals(credential.getPassword())){
				return cred.getEmployee();
			}
		}
		return null;
	}

	public static void main(String[] args) {

		// Initialize the system
		initSystem();

		System.out.println("Login:");

		String username, password; 
		Scanner input = new Scanner(System.in);

		System.out.print("Enter user name: ");
		username = input.nextLine();
		System.out.print("Enter password: ");
		password = input.nextLine();

		Credential cred = new Credential();
		cred.setUsername(username);
		cred.setPassword(password);

		Employee e = logIn(cred);
		if ( e != null ){
			System.out.println("Successfully logged in! ...");
			System.out.println("User: ");
			e.print();
			System.out.println("Logged in ...\n");

			// test settings
			// Write start session
			Settings.writeStartSession(e.getName());
			
			// test add worked hours
			if (Company.EnterHours(e, e.getProjects().get(0).getName(), 7)){
				System.out.println("Worked hours successfully recorded for project 1! ...");
			}
			
			// Write end session
			Settings.writeEndSession();

			System.out.println("Working on these projects ...");
			for( Project p : e.getProjects()){
				p.print();
			}

		}
		else{
			System.out.println("Loggin failed! ...");
		}
	}
}