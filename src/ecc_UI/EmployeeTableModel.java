package ecc_UI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testECC.Employee;
import utility.ConstantNumber;
public class EmployeeTableModel extends AbstractTableModel {
	private List<Employee> employeeList;
	private String []columnName ={"NAME","ROLE","TITLE","PAY RATE","E-MAIL","STATUS"};
   
	
	 @Override
	 public String getColumnName(int Column){
		 return this.columnName[Column];
	 };
	
	public EmployeeTableModel(){
		this.employeeList = new ArrayList();
	}

	public int getColumnCount() {
		return ConstantNumber.NUMBER_OF_COLUMNS_EMPLOYEE_TABLE;
	}

	public int getRowCount() {
		return employeeList.size();
	}

	public Object getValueAt(int rowIndex, int columIndex) {
		
      Employee employee = employeeList.get(rowIndex);
      switch(columIndex){
      case 0:
    	  return employee.getName();
      case 1:
    	  return employee.getRole();
      case 2:
    	 return employee.getTitle();
      case 3:
    	 return employee.getPayrate();
      case 4:
    	 return employee.getEmail();
      case 5:
    	  return employee.getStatus();
      default:
   		return null;

      }
	}
	
	public void setEmployeeList(List <Employee> employeeList ){
		this.employeeList=employeeList;
		
	}
	
	public void updateTable(){
		
	fireTableDataChanged();
	}

}
