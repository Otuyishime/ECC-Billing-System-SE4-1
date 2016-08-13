package testECC;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "timesheets")
public class TimeSheet 
{
	@Id
    @GeneratedValue
    @Column(name = "timesheet_id", nullable = false)
	private long id;

	@Column(name = "submitted")
	private boolean submitted;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="timesheet")
	List<TimeSheetLine> timesheetlines;
	
	@ManyToOne
	@JoinColumn(name="employee_id",referencedColumnName="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="company_id",referencedColumnName="company_id")
	private Company company;
	
	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public long getId() {
		return id;
	}

	public List<TimeSheetLine> getTimesheetlines() {
		return timesheetlines;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public TimeSheet()
	{
		timesheetlines = new ArrayList<TimeSheetLine>();
	}
	
	public void print(){
		System.out.println("\nFound:");
		System.out.println("TimeSheet with id: " + this.getId());
		System.out.println("Employee: " + this.getEmployee().getName());
	}
}