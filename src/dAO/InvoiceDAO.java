package dAO;

import java.util.ArrayList;

import javax.persistence.*;

import testECC.*;

public class InvoiceDAO {
	public static void saveInvoice(Invoice invoice) {
		EM.INSTANCE.getEM().persist(invoice);
	}
	
	// ----------------------------------
	// NOTE: DO NOT NEED NO UPDATE
	// ----------------------------------
	
	public static void addInvoice(Invoice invoice) {
		if ( findInvoiceById(invoice.getId()) == null){
			EM.INSTANCE.getEM().persist(invoice);
		}
	}

	public static ArrayList<Invoice> listInvoices() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT project FROM " + Invoice.class.getName() + " project", Invoice.class);
		ArrayList<Invoice> list= new ArrayList<Invoice>(query.getResultList());

		return list;
	}

	public static Invoice findInvoiceById(long id) {
		Invoice invoice = EM.INSTANCE.getEM().find(Invoice.class, new Long(id));
		return invoice;
	}

	public static void removeInvoiceById(long id) {
		Invoice invoice = findInvoiceById(id);
		if ( invoice != null){
			EM.INSTANCE.getEM().remove(invoice);
			return;
		}
		System.out.println("Unable to delete the invoice");
	}
}