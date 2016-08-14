package dAO;

import testECC.*;

import java.util.ArrayList;

import javax.persistence.Query;

/**
 * Represents the person who implements solutions for the project
 */
public class EmployeeDAO
{
	public static void saveEmployee(Employee employee) {
		EM.INSTANCE.getEM().persist(employee);
	}
	
	public  int  updateEmployee(String columnname, String fromvalue, String tovalue) {
		Query query = EM.INSTANCE.getEM().createQuery("UPDATE " + Employee.class.getName() + " SET " + 
				columnname + " = " + tovalue + " WHERE " + columnname + " = " + fromvalue);
		int success = query.executeUpdate();
		return success;
	}
	
	public static void addEmployee(Employee employee) {
		if (findEmployeeById(employee.getId()) == null){
			EM.INSTANCE.getEM().persist(employee);
		}
	}

	public static ArrayList<Employee> listEmployees() {
		Query query = EM.INSTANCE.getEM().createQuery("SELECT employee FROM " + Employee.class.getName() + " employee");
		ArrayList<Employee> list= new ArrayList<Employee>(query.getResultList());

		return list;
	}

	public static Employee findEmployeeById(long id) {
		Employee employee = EM.INSTANCE.getEM().find(Employee.class, new Long(id));
		return employee;
	}

	public static void removeEmployee(long id) {
		Employee employee = findEmployeeById(id);
		if ( employee != null){
			EM.INSTANCE.getEM().remove(employee);
			return;
		}
		System.out.println("Unable to delete the employee");
	}
}
