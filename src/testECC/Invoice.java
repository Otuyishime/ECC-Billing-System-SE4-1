package testECC;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "invoices")
public class Invoice 
{
	@Id
	@GeneratedValue
    @Column(name = "invoice_id", nullable = false)
	private long id;
	
	/**
	 * the date on which the invoice is created.
	 */
	@Column(name = "date", nullable = false)
	private String date;
	
	/**
	 * Terms set by the customer through which the bills will be paid. 
	 * These specifies the number of invoices the client can receive in a month.
	 */
	@Column(name = "billingterms", nullable = false)
	private String billingterms;
	
	@Column(name = "billingfrequency", nullable = false)
	private String billingfrequency;
	
	/**
	 * Total amount
	 */
	@Column(name = "totalamount")
	private Double totalamount;
	
	/**
	 * name of the customer.
	 */
	@Column(name = "clientname", nullable = false,length = 50)
	private String clientname;
	
	/**
	 * customer's address. These are the customer's mailing addresses.
	 */
	@Column(name = "clientaddressline1", nullable = false, length = 50)
	private String clientaddressline1;
	
	@Column(name = "clientaddressline2", nullable = true, length = 50)
	private String clientaddressline2;
	
	/**
	 * name of the company.
	 */
	@Column(name = "companyname", nullable = false,length = 50)
	private String companyname;
	
	/**
	 * company's address. These are the company's mailing addresses.
	 */
	@Column(name = "companyAddressline1", nullable = false, length = 50)
	private String companyAddressline1;
	
	@Column(name = "companyAddressline2", nullable = true, length = 50)
	private String companyAddressline2;
	
	/**
	 * Client Receiving the invoice
	 */
	@ManyToOne
	@JoinColumn(name="client_id",referencedColumnName="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="company_id",referencedColumnName="company_id")
	private Company company;
	
//	/**
//	 * The projects the invoice is issued for
//	 */
//	@OneToMany
//	@JoinColumn(name="project_id")
//	private Collection<Project> projects;
//	
	/**
	 * Invoice lines that make up the invoice
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="invoice")
	private List<InvoiceLine> invoicelines;

	public long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getBillingterms() {
		return billingterms;
	}

	public String getBillingfrequency() {
		return billingfrequency;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public String getClientname() {
		return clientname;
	}

	public String getClientaddressline1() {
		return clientaddressline1;
	}

	public String getClientaddressline2() {
		return clientaddressline2;
	}

	public String getCompanyname() {
		return companyname;
	}

	public String getCompanyAddressline1() {
		return companyAddressline1;
	}

	public String getCompanyAddressline2() {
		return companyAddressline2;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		
		this.billingterms = client.getBillingterms();
		this.billingfrequency = client.getBillingfrequency();
		this.clientname = client.getName();
		this.clientaddressline1 = client.getCompanyAddressLine1();
		this.clientaddressline2 = client.getCompanyAddressLine2();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
		
		this.companyname = company.getName();
		this.companyAddressline1 = company.getCompanyAddressline1();
		this.companyAddressline2 = company.getCompanyAddressline2();
	}

	public List<InvoiceLine> getInvoicelines() {
		return invoicelines;
	}

	public Invoice(){ 
		invoicelines = new ArrayList<InvoiceLine>();
	}

	public Invoice(String date, String billingterms, String billingfrequency,
			Double totalamount, String clientname, String clientaddressline1,
			String clientaddressline2, String companyname,
			String companyAddressline1, String companyAddressline2) {
		this();
		this.date = date;
		this.billingterms = billingterms;
		this.billingfrequency = billingfrequency;
		this.totalamount = totalamount;
		this.clientname = clientname;
		this.clientaddressline1 = clientaddressline1;
		this.clientaddressline2 = clientaddressline2;
		this.companyname = companyname;
		this.companyAddressline1 = companyAddressline1;
		this.companyAddressline2 = companyAddressline2;
	}
	
	public void print()
	{
		System.out.println("\nFound:");
		System.out.println("Invoice id: " + this.getId());
	}
}