package dAO;

import java.util.ArrayList;

import javax.persistence.Query;

import testECC.Company;

public class CompanyDAO {
	public static void saveCompany(Company company) {
		EM.INSTANCE.getEM().persist(company);
	}
	
	public static void addCompany(Company company) {
		if ( findCompanyById(company.getCompanyID()) == null){
			EM.INSTANCE.getEM().persist(company);
		}
	}

	public static ArrayList<Company> listCompanies() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT company FROM company company", Company.class);
		ArrayList<Company> list = new ArrayList<Company>(query.getResultList());

		return list;
	}

	public static Company findCompanyById(long id) {
		Company client = EM.INSTANCE.getEM().find(Company.class, new Long(id));
		return client;
	}

	public static void removeCompanyByID(long id) {
		Company company = findCompanyById(id);
		if ( company != null){
			EM.INSTANCE.getEM().remove(company);
			return;
		}
		System.out.println("Unable to delete the company");
	}
}
