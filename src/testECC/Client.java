package testECC;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "clients")
public class Client {
	
	@Id
    @Column(name = "client_id", nullable = false)
	private long id;
		
	/**
	 * name of the customer.
	 */
	@Column(name = "name", nullable = false,length = 50)
	private String name;
	
	/**
	 * customer's address. These are the customer's mailing addresses.
	 */
	@Column(name = "addressline1", nullable = false, length = 50)
	private String companyAddressLine1;
	
	@Column(name = "addressline2", nullable = false, length = 50)
	private String companyAddressLine2;
	
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	
	@Column(name = "state", nullable = false, length = 50)
	private String state;
	
	@Column(name = "zip", nullable = false, length = 50)
	private String zip;
	
	/**
	 * determines the client's status. (The client can be active or inactive).
	 */
	@Column(name = "status") 
	private String clientStatus;
	/**
	 * customer's contacts.
	 */
	@Column(name = "phonenumber", nullable = false, length = 50)
	private String clientPhoneNumber;
	
	@Column(name = "email", nullable = false, length = 50)
	private String clientEmail;
	
	/**
	 * Terms set by the customer through which the bills will be paid. 
	 * These specifies the number of invoices the client can receive in a month.
	 */
	@Column(name = "billingfrequency")
	private String billingfrequency;
	
	@Column(name = "billingterms")
	private String billingterms;
	
	/**
	 * Check if the client wants to group all project in one invoice.
	 */
	@Column(name = "billinginvoicinggroup")
	private String billinginvoicinggroup;
	
	/**
	 * Company working on the client's project(s)
	 */
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName="company_id")
	private Company company;
	
	/**
	 * Client's projects contracted by the ECC company
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="client")
	private List<Project> projects;
	
	/**
	 * Client's invoices
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="client")
	private Collection<Invoice> invoices;
	
	/*
	 * ------------------------ Accessors ------------------------
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyAddressLine1() {
		return companyAddressLine1;
	}

	public void setCompanyAddressLine1(String companyAddressLine1) {
		this.companyAddressLine1 = companyAddressLine1;
	}

	public String getCompanyAddressLine2() {
		return companyAddressLine2;
	}

	public void setCompanyAddressLine2(String companyAddressLine2) {
		this.companyAddressLine2 = companyAddressLine2;
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

	public String getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}

	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getBillingfrequency() {
		return billingfrequency;
	}

	public void setBillingfrequency(String billingfrequency) {
		this.billingfrequency = billingfrequency;
	}

	public String getBillingterms() {
		return billingterms;
	}

	public void setBillingterms(String billingterms) {
		this.billingterms = billingterms;
	}

	public String getBillinginvoicinggroup() {
		return billinginvoicinggroup;
	}

	public void setBillinginvoicinggroup(String billinginvoicinggroup) {
		this.billinginvoicinggroup = billinginvoicinggroup;
	}
	
	public Collection<Invoice> getInvoices() {
		return invoices;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	/*
	 * ------------------------ Methods ------------------------
	 */
//	public Project findProject(String projectName)
//	{
//		for (Project project : getClientprojects())
//		{
//			if (project.getProjectName().equals(projectName)) return project;
//		}
//		return null;
//	}

	public Client() {
		projects = new ArrayList<Project>();
		invoices = new ArrayList<Invoice>();
	}

	public Client(long clientnumber, String name, 
			String companyAddressLine1, String companyAddressLine2, String city, String state, String zip, 
			String clientStatus, String clientPhoneNumber, String clientEmail,
			String billingfrequency, String billingterms, String billinginvoicinggroup) {
		this();
		this.id = clientnumber;
		this.name = name;
		this.companyAddressLine1 = companyAddressLine1;
		this.companyAddressLine2 = companyAddressLine2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.clientStatus = clientStatus;
		this.clientPhoneNumber = clientPhoneNumber;
		this.clientEmail = clientEmail;
		this.billingfrequency = billingfrequency;
		this.billingterms = billingterms;
		this.billinginvoicinggroup = billinginvoicinggroup;
	}
	
	@Override
	public String toString() {
		return "Client : " + getName();
	}
	
	public void print(){
		System.out.println("\nFound:");
		System.out.println("ID: " + this.getId());
		System.out.println("Name: " + this.getName());
		System.out.println("Adress Line 1: " + this.getCompanyAddressLine1());
		System.out.println("Adress Line 2: " + this.getCompanyAddressLine2());
		System.out.println("City: " + this.getCity());
		System.out.println("State: " + this.getState());
		System.out.println("Zip: " + this.getZip());
		System.out.println("Status:" + this.getClientStatus());
		System.out.println("Phone Number:" + this.getClientPhoneNumber());
		System.out.println("Email:" + this.getClientEmail());
		System.out.println("Frequency:" + this.billingfrequency);
		System.out.println("Billing Terms:" + this.getBillingterms());
		System.out.println("Billing Group:" + this.getBillinginvoicinggroup());
	}
}
