package utility;

import java.util.ArrayList;

public class SimpleStatus {
	private static ArrayList<String> clientStatuses;
	private static ArrayList<String> projectStatuses;
	private static ArrayList<String> employeeStatuses;

	public static ArrayList<String> getClientStatuses() {
		clientStatuses = new ArrayList<String>();
		clientStatuses.add("active");
		clientStatuses.add("not active");
		
		return clientStatuses;
	}

	public static ArrayList<String> getProjectStatuses() {
		projectStatuses = new ArrayList<String>();
		projectStatuses.add("in progress");
		projectStatuses.add("closed");
		
		return projectStatuses;
	}

	public static ArrayList<String> getEmployeeStatuses() {
		employeeStatuses = new ArrayList<String>();
		employeeStatuses.add("active");
		employeeStatuses.add("not active");
		
		return employeeStatuses;
	}
}