package ecc_UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import testECC.Company;
import testECC.Employee;
import testECC.Project;
import testECC.TimeSheet;
import testECC.TimeSheetLine;
import utility.SimpleDate;
import utility.SimpleRole;

import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class VerifyEmployeeHoursPageJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public VerifyEmployeeHoursPageJPanel(JFrame currentFrame, Employee employee) {

		setBackground(Color.LIGHT_GRAY);
		setForeground(new Color(165, 42, 42));
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);

		JLabel lblHomePage = new JLabel("Welcome to the ECC. Use the Menu bar for more options");
		lblHomePage.setOpaque(true);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setBackground(new Color(165, 42, 42));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(0, 0, 900, 60);
		add(lblHomePage);

		JLabel lblNewLabel = new JLabel("Verify Employees' Timesheets");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(19, 70, 860, 30);
		add(lblNewLabel);

		JLabel lblSelectProject = new JLabel("Select Project");
		lblSelectProject.setBounds(20, 121, 100, 30);
		add(lblSelectProject);

		JLabel lblTimeSheet = new JLabel("Time Sheet");
		lblTimeSheet.setBounds(19, 298, 743, 30);
		add(lblTimeSheet);

		JCheckBox chckbxApprove = new JCheckBox("Approve");
		chckbxApprove.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxApprove.setBounds(774, 301, 105, 23);
		add(chckbxApprove);

		// ------------------------------ Developers table -----------------------------------------
		String[] developerColumns = {"Employee", "Title", "Email"};
		DefaultTableModel developerTableModel = new DefaultTableModel(developerColumns, 0);
		JTable developersTable = new JTable(developerTableModel);
		developersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ------------------------------ TimeSheet table ------------------------------------------
		String[] timeSheetColumns = {"Date", "Employee", "Project", "Hours", "Verified"};
		DefaultTableModel timeSheetTableModel = new DefaultTableModel(timeSheetColumns, 0);
		JTable timesheetLinesTable = new JTable(timeSheetTableModel){
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};

		// ------------------------------ ComboBox ------------------------------------------
		ArrayList <String> projectsNames = new ArrayList<String>();
		for (Project p : employee.getProjects()){
			projectsNames.add(p.getName());
		}
		JComboBox comboBoxProjects = new JComboBox(projectsNames.toArray());
		comboBoxProjects.setBounds(132, 122, 200, 30);
		add(comboBoxProjects);
		comboBoxProjects.setSelectedIndex(0);

		comboBoxProjects.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				Project newProject = employee.getProjectByName(comboBoxProjects.getSelectedItem().toString());

				int numberOfRow = developerTableModel.getRowCount();
				for (int i = 0; i < numberOfRow; i++){
					developerTableModel.removeRow(0);
				}

				populateDeveloperTable(developerTableModel, newProject);	// UPDATE THE TABLE
				developersTable.getSelectionModel().setSelectionInterval(0, 0);
			}
		});
		comboBoxProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		// ---- Get the currently selected project ---
		Project currentSelectedProject = employee.getProjectByName(comboBoxProjects.getSelectedItem().toString());
		// initialize the time sheet model - if there is any time sheet line
		populateDeveloperTable(developerTableModel, currentSelectedProject);	// INITIALIZE THE TABLE
		developersTable.setPreferredScrollableViewportSize(new Dimension(300, 70));
		developersTable.setFillsViewportHeight(true);
		developersTable.getSelectionModel().setSelectionInterval(0, 0);

		// ------------------------------ TimeSheet table for a particular employee ------------------------------------------
		String currentSelectedEmployeeName = developerTableModel.getValueAt(developersTable.getSelectedRow(), 0).toString();
		Employee currentSelectedEmployee = currentSelectedProject.getEmployeeByName(currentSelectedEmployeeName);

		// initialize the time sheet model - if there is any time sheet line
		populateTimeSheetsTable(timeSheetTableModel, currentSelectedEmployee);	// UPDATE TIMESHEET TABLE

		timesheetLinesTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		timesheetLinesTable.setFillsViewportHeight(true);

		JScrollPane timeSheetSrollpane = new JScrollPane(timesheetLinesTable);
		timeSheetSrollpane.setSize(860, 291);
		timeSheetSrollpane.setLocation(20, 327);
		add(timeSheetSrollpane);
		// ------------------------------ TimeSheet table ------------------------------------------

		// ------------------------------ Developers table ------------------------------------------
		// ADD A SELECTION LISTENER TO THE DEVELOPERS TABLE
		developersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if( developersTable.getSelectedRow() != -1){
					System.out.println("Selected develpper: " + developerTableModel.getValueAt(developersTable.getSelectedRow(), 0).toString());
					populateTimeSheetsTable(timeSheetTableModel, currentSelectedEmployee);
				}
			}
		});

		JScrollPane developerSrollpane = new JScrollPane(developersTable);
		developerSrollpane.setSize(520, 155);
		developerSrollpane.setLocation(359, 127);
		add(developerSrollpane);
		// ------------------------------ Developers table ------------------------------------------
	}

	private void populateDeveloperTable(DefaultTableModel developerTableModel, Project currentSelectedProject){
		if (!(currentSelectedProject.getEmployees().isEmpty())){
			for (Employee emp : currentSelectedProject.getEmployees()){

				if( !emp.getRole().equals(SimpleRole.PROJECTMANAGER)){
					String employeeName = emp.getName();
					String employeeTitle = emp.getTitle();
					String employeeEmail = emp.getEmail();

					// TODO: add this field to data (setters and getters)
					Boolean verified = false;

					developerTableModel.addRow(new Object[]{employeeName, employeeTitle, employeeEmail});
				}
			}
		}
	}

	//	private void populateTimeSheetsTable(DefaultTableModel timeSheetTableModel, Employee currentSelectedEmployee){
	//		if (!(currentSelectedEmployee.getSubmittedTimeSheets().isEmpty())){
	//
	//			for (TimeSheet timeSheet : currentSelectedEmployee.getSubmittedTimeSheets()){
	//				for (TimeSheetLine timeSheetLine : timeSheet.getTimesheetlines()){
	//					String date = timeSheetLine.getDate();
	//					String employeeName = timeSheetLine.getEmployee();
	//					String projectName = timeSheetLine.getProject();
	//					int hours = timeSheetLine.getHours();
	//
	//					// TODO: add this field to data (setters and getters)
	//					Boolean verified = false;
	//
	//					timeSheetTableModel.addRow(new Object[]{date, employeeName, projectName, hours, verified});
	//				}
	//			}
	//		}
	//	}

	private void populateTimeSheetsTable(DefaultTableModel timeSheetTableModel, Employee currentSelectedEmployee){
		if ((currentSelectedEmployee.getCurrentTimeSheet() != null)){

			//for (TimeSheet timeSheet : currentSelectedEmployee.getSubmittedTimeSheets()){
			for (TimeSheetLine timeSheetLine : currentSelectedEmployee.getCurrentTimeSheet().getTimesheetlines()){
				String date = timeSheetLine.getDate();
				String employeeName = timeSheetLine.getEmployee();
				String projectName = timeSheetLine.getProject();
				int hours = timeSheetLine.getHours();

				// TODO: add this field to data (setters and getters)
				Boolean verified = false;

				timeSheetTableModel.addRow(new Object[]{date, employeeName, projectName, hours, verified});
			}
			//}
		}
	}
}
