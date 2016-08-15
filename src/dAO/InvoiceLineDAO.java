package dAO;

import testECC.*;

import java.util.ArrayList;

import javax.persistence.Query;

public class InvoiceLineDAO 
{
	public static void saveInvoiceLine(InvoiceLine invoiceline) {
		EM.INSTANCE.getEM().persist(invoiceline);
	}
	
	public static int  updateInvoiceLine(String columnname, String fromvalue, String tovalue) {
		int success = 0;
		
		if ( columnname.equals("payrate") || columnname.equals("hours")  || columnname.equals("overtime") || columnname.equals("totalamount")){
			System.out.println("Can't touch this!");
			return success;
		}else{

			Query query = EM.INSTANCE.getEM().createQuery("UPDATE " + InvoiceLine.class.getName() + " SET " + 
					columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
			success = query.executeUpdate();
		}

		return success;
	}
	
	public static void addInvoiceLine(InvoiceLine invoiceline) {
		if ( findInvoiceLineById(invoiceline.getId()) == null){
			EM.INSTANCE.getEM().persist(invoiceline);
		}
	}

	public static ArrayList<InvoiceLine> listInvoiceLines() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT project FROM " + InvoiceLine.class.getName() + " project", InvoiceLine.class);
		ArrayList<InvoiceLine> list= new ArrayList<InvoiceLine>(query.getResultList());

		return list;
	}

	public static InvoiceLine findInvoiceLineById(long id) {
		InvoiceLine invoiceline = EM.INSTANCE.getEM().find(InvoiceLine.class, new Long(id));
		return invoiceline;
	}

	public static void removeInvoiceLineById(long id) {
		InvoiceLine invoiceline = findInvoiceLineById(id);
		if ( invoiceline != null){
			EM.INSTANCE.getEM().remove(invoiceline);
			return;
		}
		System.out.println("Unable to delete the invoiceline");
	}
}