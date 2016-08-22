package testECC;

import javax.persistence.*;

@Entity
@Table(name = "timesheetlines")
public class TimeSheetLine {
	@Id
    @GeneratedValue
    @Column(name = "timesheetline_id", nullable = false)
	private long id;
	
	/**
	 * The date on which the developer worked on the project
	 */
	@Column(name = "date")
	private String date;
	
	/**
	 * The number of hours the developer worked on the project
	 */
	@Column(name = "hours")
	private int hours;
	
	/**
	 * Name of the project the employee is working on
	 */
	@Column(name = "project", nullable = false, length = 50)
	private String project;
	
	/**
	 * Name of the employee
	 */
	@Column(name = "employee", nullable = false, length = 50)
	private String employee;
	
	/**
	 * Check to see if the project manger reviewed the hours 
	 */
	@Column(name = "reviewed")
	private boolean reviewed;

	@ManyToOne
	@JoinColumn(name="timesheet_id",referencedColumnName="timesheet_id")
	private TimeSheet timesheet;
	
	public long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}
	
	public TimeSheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}

	public TimeSheetLine(){ }

	public TimeSheetLine(String date, int hours, String nameofemployee, String nameofproject, boolean isreviewed) {
		this();
		this.date = date;
		this.hours = hours;
		this.employee = nameofemployee;
		this.project = nameofproject;
		this.reviewed = isreviewed;
	}
	
	public void print(){
		System.out.println("\nFound:");
		System.out.println("Date: " + this.getDate());
		System.out.println("Employee: " + this.getEmployee());
		System.out.println("Project: " + this.getProject());
		System.out.println("Hours: " + this.getHours());
		System.out.println("IsReviewed: " + this.isReviewed());
	}
}