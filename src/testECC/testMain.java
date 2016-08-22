package testECC;

import java.awt.EventQueue;
import java.util.List;
import java.util.Scanner;

import billingProjectModel.MainFrameService;
import billingProjectModel.MainFrameServiceImp;
import utility.Credential;
import utility.Settings;
import utility.SimpleDate;
import dAO.*;
import dM.EccDM_Helper;
import dM.LoadTimeSheetLinesDM;
import ecc_UI.SystemJFrame;

public class testMain {

	private static List<Credential> credentials;
	private static List<Company> companies;
	private static MainFrameService mainFrameService;
	private static List<Employee> employees;

	public static void initSystem(){
		credentials = CredentialDAO.listCredentials();
		companies = CompanyDAO.listCompanies();
		employees=EmployeeDAO.listEmployees();

		if( companies.isEmpty()){
			EccDM_Helper.loadData();
		}

		// load the time sheet lines for testing
		if(companies.get(0).getTimesheets().isEmpty()){
			LoadTimeSheetLinesDM.loadTimeSheetLines(companies.get(0));
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