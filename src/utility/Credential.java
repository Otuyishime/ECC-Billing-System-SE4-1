package utility;

import javax.persistence.*;

import testECC.Employee;

@Entity  
@Table(name = "credentials")
public class Credential {
	@Id
    @GeneratedValue
    @Column(name = "credential_id", nullable = false)
	private long id;
	
	@Column(name = "username", length = 100)
    private String username;
	
	@Column(name = "password", length = 100)
    private String password;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	Employee employee;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Credential(){ }

	public Credential(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	public void print(){
		System.out.println("\nFound: ");
		System.out.println("Credential name: " + this.getUsername());
		System.out.println("Password: " + this.getPassword());
	}
}