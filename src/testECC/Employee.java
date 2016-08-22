package testECC;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import utility.Credential;

@Entity  
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue
	@Column(name = "employee_id", nullable = false)
	private long id;

	@Column(name = "role")
	private String role;

	@Column(name = "name")
	private String name;

	@Column(name = "email", length = 100, nullable = true)
	private String email;

	@Column(name = "title", length = 50)
	private String title;

	@Column(name = "payrate")
	private Double payrate;

	@Column(name = "status", length = 50)
	private String status;

	@OneToOne(mappedBy = "employee")
	private Credential credentials;

	@ManyToMany
	@JoinTable(
			name = "project_employee",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			)
	private List<Project> projects = new ArrayList<Project>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="employee")
	List<TimeSheet> timesheets;

	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName="company_id")
	private Company company;

	public Employee() { 
		timesheets = new ArrayList<TimeSheet>();
	}

	public Employee(String role, String name, String email, String title, Double payrate, String status) {
		this();
		this.role = role;
		this.name = name;
		this.email = email;
		this.title = title;
		this.payrate = payrate;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPayrate() {
		return payrate;
	}

	public void setPayrate(Double payrate) {
		this.payrate = payrate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Credential getCredentials() {
		return credentials;
	}

	public void setCredentials(Credential credentials) {
		this.credentials = credentials;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<TimeSheet> getTimesheets() {
		return timesheets;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void print() {
		System.out.println("\nFound");
		System.out.println("Name: " + this.getName());
		System.out.println("SimpleRole: " + this.getRole());
		System.out.println("Title: " + this.getTitle());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Payrate: " + this.getPayrate());
		System.out.println("Status: " + this.getStatus());
	}
	
	public ArrayList<TimeSheetLine> getNonReviewedTimeSheetLinesForProject(String projName){
		ArrayList<TimeSheetLine> results = new ArrayList<TimeSheetLine>();

		if( !this.getTimesheets().isEmpty()){
			for( TimeSheetLine timesheetline : this.getCurrentTimeSheet().getTimesheetlines()){
				if( timesheetline.getProject().equals(projName) && !timesheetline.isReviewed()){
					results.add(timesheetline);
				}
			}
		}
		
		return results;
	}
	
	public TimeSheet getCurrentTimeSheet(){
		if( !this.getTimesheets().isEmpty()){
			return this.getTimesheets().get(0);
		}
		else{
			// create a new time sheet
			TimeSheet timesheet = new TimeSheet();
			timesheet.setCompany(this.getCompany());
			timesheet.setEmployee(this);
			timesheet.setSubmitted(false);
			this.getTimesheets().add(timesheet);
			
			// return the time sheet
			return this.getTimesheets().get(0);
		}
	}

	public Project getProjectByName(String projectName) {
		Project p = null;
		for( Project proj : this.getProjects()){
			if(proj.getName().equals(projectName)){
				return proj;
			}
		}
		
		return p;
	}
}