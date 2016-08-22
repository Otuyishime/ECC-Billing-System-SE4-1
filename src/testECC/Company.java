package testECC;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dAO.EM;
import utility.SimpleDate;
import utility.SimpleRole;

@Entity(name = "company")
public class Company
{
	@Id
	@GeneratedValue
	@Column(name = "company_id", nullable = false)
	private long companyID;

	/**
	 * the company name
	 */
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	/**
	 * company's address. This is the post address of the company.
	 */
	@Column(name = "addressline1", nullable = false, length = 50)
	private String companyAddressline1;

	@Column(name = "addressline2", nullable = true, length = 50)
	private String companyAddressline2;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "state", nullable = false, length = 50)
	private String state;

	@Column(name = "zip", nullable = false, length = 50)
	private String zip;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="company")
	private List<Project> projects;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="company")
	private List<Employee> employees;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="company")
	private List<Client> clients;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="company")
	private List<Invoice> invoices;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="company")
	private List<TimeSheet> timesheets;

	// ------------------------- Accessors -------------------------
	public long getCompanyID() {
		return companyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String companyname) {
		this.name = companyname;
	}

	public String getCompanyAddressline1() {
		return companyAddressline1;
	}

	public void setCompanyAddressline1(String companyAddressline1) {
		this.companyAddressline1 = companyAddressline1;
	}

	public String getCompanyAddressline2() {
		return companyAddressline2;
	}

	public void setCompanyAddressline2(String companyAddressline2) {
		this.companyAddressline2 = companyAddressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public List<TimeSheet> getTimesheets() {
		return timesheets;
	}

	public Company(){
		projects = new ArrayList<Project>();
		employees = new ArrayList<Employee>();
		clients = new ArrayList<Client>();
		invoices = new ArrayList<Invoice>();
		timesheets = new ArrayList<TimeSheet>();
	}

	// --------------------------- Methods --------------------------
	public Company(String name, String companyAddressline1,
			String companyAddressline2, String city, String state, String zip) {
		this();
		this.name = name;
		this.companyAddressline1 = companyAddressline1;
		this.companyAddressline2 = companyAddressline2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public void print(){
		System.out.println("\nFound: ");
		System.out.println("ID: " + this.getCompanyID());
		System.out.println("Name: " + this.getName());
		System.out.println("Adress Line 1: " + this.getCompanyAddressline1());
		System.out.println("Adress Line 2: " + this.getCompanyAddressline2());
		System.out.println("City: " + this.getCity());
		System.out.println("State: " + this.getState());
		System.out.println("Zip: " + this.getZip());
	}

	// ------------------ Get clients by name ------------------
	public Client getClientByName(String clientname){
		Client result = null;
		if( !this.getClients().isEmpty()){
			for( Client client : this.getClients()){
				if ( client.getName().equals(clientname)){
					return client;
				}
			}
		}

		return result;
	}
	// ------------------ Enter Worked Hours ------------------
	public static boolean EnterHours(Employee loggedinemployee, String projectworkedon, String date, int workedhours){
		boolean success = false;

		// check if the employee has the "Developer" role
		if( !loggedinemployee.getRole().equals(SimpleRole.DEVELOPER)){
			return false;
		}
		else{
			// get the employee time sheet
			TimeSheet timesheet = loggedinemployee.getCurrentTimeSheet();

			// create a new time sheet line
			TimeSheetLine timesheetline = new TimeSheetLine();
			timesheetline.setDate(date);
			timesheetline.setEmployee(loggedinemployee.getName());
			timesheetline.setProject(projectworkedon);
			timesheetline.setHours(workedhours);
			timesheetline.setReviewed(false);

			// Link objects
			timesheetline.setTimesheet(timesheet);
			timesheet.getTimesheetlines().add(timesheetline);

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(timesheet);
			EM.INSTANCE.getEM().persist(timesheetline);

			userTransaction.commit();

			success = true;
		}

		return success;
	}

	// ------------------ Create a new time sheet ------------------
	public static boolean AddNewTimeSheet(Employee loggedinemployee, TimeSheet timesheet){
		boolean success = false;

		if( timesheet != null )
		{
			timesheet.setSubmitted(false);
			timesheet.setEmployee(loggedinemployee);
			timesheet.setCompany(loggedinemployee.getCompany());
			
			// add time sheet to the client
			loggedinemployee.getTimesheets().add(timesheet);
			// add time sheet to the company
			loggedinemployee.getCompany().getTimesheets().add(timesheet);
			
			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(loggedinemployee.getCompany());
			EM.INSTANCE.getEM().persist(loggedinemployee);
			EM.INSTANCE.getEM().persist(timesheet);

			userTransaction.commit();

			success = true;
		}

		return success;
	}

	// ------------------ Submit time sheet ------------------
	public static boolean SubmitTimeSheet(Employee loggedinemployee, boolean submit){
		boolean success = false;

		// check if the employee has the "Developer" role
		if( !loggedinemployee.getRole().equals(SimpleRole.DEVELOPER)){
			return false;
		}
		else{
			// get the employee's current time sheet
			TimeSheet timesheet = loggedinemployee.getCurrentTimeSheet();

			if ( timesheet != null ){

				timesheet.setSubmitted(submit);

				// Obtains Session
				EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
				userTransaction.begin();

				// persist the time sheet
				EM.INSTANCE.getEM().persist(timesheet);

				userTransaction.commit();

				success = true;
				return success;
			}
		}

		return success;
	}

	// ------------------ Add new Employee ------------------
	public static boolean AddNewEmployee(Employee loggedinemployee, Company company, Employee newemployee){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if( company != null && newemployee != null){
			// add new employee
			// Link objects
			newemployee.setCompany(company);
			company.getEmployees().add(newemployee);

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(company);
			EM.INSTANCE.getEM().persist(newemployee);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Add new Client ------------------
	public static boolean AddNewClient(Employee loggedinemployee, Company company, Client newclient){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if( company != null && newclient != null){
			// add new employee
			// Link objects
			newclient.setCompany(company);
			company.getClients().add(newclient);

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(company);
			EM.INSTANCE.getEM().persist(newclient);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Add new Project ------------------
	public static boolean AddNewProject(Employee loggedinemployee, Company company, Project newproject){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if( company != null && newproject != null){
			// add new employee
			// Link objects
			newproject.setCompany(company);
			company.getProjects().add(newproject);

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(company);
			EM.INSTANCE.getEM().persist(newproject);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Edit Company ------------------
	public static boolean EditCompany(Company company){
		boolean success = false;
		if( company != null){
			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(company);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Edit Client ------------------
	public static boolean EditClient(Employee loggedinemployee, Client client){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if(client != null){
			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(client);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Edit Project ------------------
	public static boolean EditProject(Employee loggedinemployee, Project project){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if( project != null){

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(project);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}

	// ------------------ Edit Employee ------------------
	public static boolean EditEmployee(Employee loggedinemployee, Employee employee){
		boolean success = false;
		// check if the employee has the "Accountant" role
		if( !loggedinemployee.getRole().equals(SimpleRole.ACCOUNTANT)){
			return false;
		}
		else if( employee != null){

			// Obtains Session
			EntityTransaction userTransaction = EM.INSTANCE.getEM().getTransaction();
			userTransaction.begin();

			// persist the objects
			EM.INSTANCE.getEM().persist(employee);

			userTransaction.commit();

			success = true;
			return success;
		}

		return success;
	}
}