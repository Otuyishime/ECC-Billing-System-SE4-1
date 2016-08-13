package dAO;

import java.util.ArrayList;

import javax.persistence.Query;

import testECC.Client;

public class ClientDAO {
	public static void saveClient(Client client) {
		EM.getEM().persist(client);
	}
	
	public static int  updateClient(String columnname, String fromvalue, String tovalue) {
		Query query = EM.getEM().createQuery("UPDATE " + Client.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addClient(Client client) {
		if ( findClientById(client.getId()) == null){
			EM.getEM().persist(client);
		}
	}

	public static ArrayList<Client> listClients() {
		Query query = EM.getEM().createQuery("SELECT client FROM "+ Client.class.getName() + " client", Client.class);
		ArrayList<Client> list= new ArrayList<Client>(query.getResultList());

		return list;
	}

	public static Client findClientById(long id) {
		Client client = EM.getEM().find(Client.class, new Long(id));
		return client;
	}

	public static void removeClientByID(long id) {
		Client client = findClientById(id);
		if ( client != null){
			EM.getEM().remove(client);
			return;
		}
		System.out.println("Unable to delete the client");
	}
}
