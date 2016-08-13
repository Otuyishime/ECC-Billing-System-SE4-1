package utility;

import java.util.ArrayList;

public class SimpleRole {
	private static ArrayList<String> employeeroles;
	public static final String DEVELOPER = "Developer";
	public static final String PROJECTMANAGER = "Project Manager";
	public static final String ACCOUNTANT = "Accountant";
	
	public static ArrayList<String> getEmployeeRoles() {
		employeeroles = new ArrayList<String>();
		employeeroles.add(ACCOUNTANT);
		employeeroles.add(PROJECTMANAGER);
		employeeroles.add(DEVELOPER);
		
		return employeeroles;
	}
}