package testECC;

import javax.persistence.*;

@Entity
@Table(name = "invoicelines")
public class InvoiceLine 
{
	@Id
	@GeneratedValue
    @Column(name = "invoicelineitem_id", nullable = false)
	private long id;
	
	/**
	 * The date on which the employee worked
	 */
	@Column(name = "fromdate", nullable = false, length = 50)
	private String fromdate;
	
	@Column(name = "todate", nullable = false, length = 50)
	private String todate;
	
	/**
	 * Employee working on the project
	 */
	@Column(name = "employee", nullable = false, length = 50)
	private String employee;
	
	@Column(name = "payrate")
	private double payrate;
	
	/**
	 * Number of hours spent on the project
	 */
	@Column(name = "hours", nullable = false)
	private int hours;
	
	/**
	 * Overtime hours
	 */
	@Column(name = "overtime", nullable = true)
	private int overtime;
	
	/**
	 * Total amount of line on invoice
	 */
	@Column(name = "totalamount")
	private Double totalAmount;
	
	@ManyToOne
	@JoinColumn(name="invoice_id",referencedColumnName="invoice_id")
	private Invoice invoice;
//	
//	@OneToOne
//	@JoinColumn(name="project_id")
//	private Project project;

	public long getId() {
		return id;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public double getPayrate() {
		return payrate;
	}

	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceLine(){ }
	
	public InvoiceLine(String fromdate, String todate, String employee,
			double payrate, int hours, int overtime) {
		this();
		this.fromdate = fromdate;
		this.todate = todate;
		this.employee = employee;
		this.payrate = payrate;
		this.hours = hours;
		this.overtime = overtime;
		this.totalAmount = computeAmount(this.payrate, this.hours, this.overtime);
	}
	
	private Double computeAmount(double payrate, int hours, int overtime){
		return ((payrate * hours) + (overtime * (payrate * 1.5)));
	}
	
	public void refreshAmount(){
		this.totalAmount = computeAmount(this.payrate, this.hours, this.overtime);
	}
	
	public void print(){
		System.out.println("\nFound:");
		System.out.println("FromDate: " + this.getFromdate());
		System.out.println("ToDate: " + this.getTodate());
		System.out.println("Employee: " + this.getEmployee());
		System.out.println("Payrate: " + this.getPayrate());
		System.out.println("Hours: " + this.getHours());
		System.out.println("Overtime: " + this.getOvertime());
		System.out.println("Amount: " + this.getTotalAmount());
	}
}