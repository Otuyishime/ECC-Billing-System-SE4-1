package testECC;

import java.awt.EventQueue;
import java.util.List;
import java.util.Scanner;

import utility.Credential;
import utility.Settings;
import dAO.*;
import dM.EccDM_Helper;
import ecc_UI.SystemJFrame;

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
		
		// start the main system frame
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemJFrame frame = new SystemJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}