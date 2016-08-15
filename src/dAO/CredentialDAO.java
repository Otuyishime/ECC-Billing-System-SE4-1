package dAO;

import java.util.ArrayList;

import javax.persistence.Query;

import utility.Credential;

public class CredentialDAO {
	public static void saveCredential(Credential credential) {
		EM.INSTANCE.getEM().persist(credential);
	}
	
	public static int  updateCredential(String columnname, String fromvalue, String tovalue) {
		Query query = EM.INSTANCE.getEM().createQuery("UPDATE " + Credential.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addCredential(Credential credential) {
		if (findCredentialById(credential.getId()) == null){
			EM.INSTANCE.getEM().persist(credential);
		}
	}

	public static ArrayList<Credential> listCredentials() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT employee FROM " + Credential.class.getName() + " employee");
		ArrayList<Credential> list= new ArrayList<Credential>(query.getResultList());

		return list;
	}

	public static Credential findCredentialById(long id) {
		Credential credential = EM.INSTANCE.getEM().find(Credential.class, new Long(id));
		return credential;
	}

	public static void removeCredential(long id) {
		Credential credential = findCredentialById(id);
		if ( credential != null){
			EM.INSTANCE.getEM().remove(credential);
			return;
		}
		System.out.println("Unable to delete the employee");
	}
}
