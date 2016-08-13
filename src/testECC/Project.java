package testECC;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "projects")
public class Project {
	@Id
    @Column(name = "project_id")
	private long id;
	
	@Column(name = "name", length = 100, nullable = false)
    private String name;
	
	@Column(name = "startdate", nullable = false)
	private String startdate;
	
	@Column(name = "enddate", nullable = false)
	private String enddate;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "clientcontact", nullable = false)
	private String clientcontact;
	
	@Column(name = "budget", nullable = false)
	private double budget;
	
	@Column(name = "billedamount", nullable = false)
	private double billedamount;
	
	@ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<Employee>();
	
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName="company_id")
	private Company company;
 
	public Project(){ 
		//employees = new ArrayList<Employee>();
	}
	
	public Project(long id, String name, String startdate, String enddate,
			String status, String clientcontact, double budget) {
		this();
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.status = status;
		this.clientcontact = clientcontact;
		this.budget = budget;
		this.billedamount = 0.0;
	}

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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientcontact() {
		return clientcontact;
	}

	public void setClientcontact(String clientcontact) {
		this.clientcontact = clientcontact;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public double getBilledamount() {
		return billedamount;
	}

	public void setBilledamount(double billedamount) {
		this.billedamount = billedamount;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void print(){
		System.out.println("\nFound:");
		System.out.println("Name: " + this.getName());
		System.out.println("Start date: " + this.getStartdate());
		System.out.println("End date: " + this.getEnddate());
		System.out.println("Status: " + this.getStatus());
		System.out.println("Client contact: " + this.getClientcontact());
		System.out.println("Budget: " + this.getBudget());
	}
}
