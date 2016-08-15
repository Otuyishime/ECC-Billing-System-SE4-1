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

import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class ProjectManagerHomePageJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectManagerHomePageJPanel(JFrame currentFrame, Employee employee) {

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

		String[] developerColumns = {"Employee", "Title", "Email"};
		DefaultTableModel developerTableModel = new DefaultTableModel(developerColumns, 0);
		JTable developersTable = new JTable(developerTableModel);
		developersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ArrayList <String> projectsNames = new ArrayList<String>();
		for (Project p : employee.getProjects()){
			projectsNames.add(p.getName());
		}
		JComboBox comboBoxProjects = new JComboBox(projectsNames.toArray());
		comboBoxProjects.setBounds(132, 122, 200, 30);
		add(comboBoxProjects);
		comboBoxProjects.setSelectedIndex(0);

		// ---- make a temporary project ---
		Project currentSelectedProject = employee.getProjectByName(comboBoxProjects.getSelectedItem().toString());

		comboBoxProjects.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				Project newProject = employee.getProjectByName(comboBoxProjects.getSelectedItem().toString());

				int numberOfRow = developerTableModel.getRowCount();
				for (int i = 0; i < numberOfRow; i++){
					developerTableModel.removeRow(0);
				}

				if (!(newProject.getEmployees().isEmpty())){
					for (Employee emp : newProject.getEmployees()){
						String employeeName = emp.getName();
						String employeeTitle = emp.getTitle();
						String employeeEmail = emp.getEmail();

						// TODO: add this field to data (setters and getters)
						Boolean verified = false;

						developerTableModel.addRow(new Object[]{employeeName, employeeTitle, employeeEmail});
					}
				}

			}
		});
		comboBoxProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JLabel lblSelectProject = new JLabel("Select Project");
		lblSelectProject.setBounds(20, 121, 100, 30);
		add(lblSelectProject);

		// ------------------------------ Developers table ------------------------------------------

		// initialize the time sheet model - if there is any time sheet line
		if (!(currentSelectedProject.getEmployees().isEmpty())){
			for (Employee emp : currentSelectedProject.getEmployees()){
				String employeeName = emp.getName();
				String employeeTitle = emp.getTitle();
				String employeeEmail = emp.getEmail();

				// TODO: add this field to data (setters and getters)
				Boolean verified = false;

				developerTableModel.addRow(new Object[]{employeeName, employeeTitle, employeeEmail});
			}
		}
		developersTable.setPreferredScrollableViewportSize(new Dimension(300, 70));
		developersTable.setFillsViewportHeight(true);

		JScrollPane developerSrollpane = new JScrollPane(developersTable);
		developerSrollpane.setSize(520, 155);
		developerSrollpane.setLocation(359, 127);
		add(developerSrollpane);
		// ------------------------------ Developers table ------------------------------------------



		// ------------------------------ TimeSheet table for a particular employee ------------------------------------------
		developersTable.getSelectionModel().setSelectionInterval(0, 0);
		String currentSelectedEmployeeName = developerTableModel.getValueAt(developersTable.getSelectedRow(), 0).toString();
		System.out.print(currentSelectedEmployeeName);
		Employee currentSelectedEmployee = currentSelectedProject.getEmployeeByName(currentSelectedEmployeeName);

		String[] timeSheetColumns = {"Date", "Employee", "Project", "Hours", "Verified"};
		DefaultTableModel timeSheetTableModel = new DefaultTableModel(timeSheetColumns, 0);
		JTable timesheetLinesTable = new JTable(timeSheetTableModel){
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};
		
		developersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.print("Selected develpper - " + developersTable.getValueAt(developersTable.getSelectedRow(), 0).toString());
			}
	    });
		
		// initialize the time sheet model - if there is any time sheet line
		if (!(currentSelectedEmployee.getSubmittedTimeSheets().isEmpty())){

			for (TimeSheet timeSheet : currentSelectedEmployee.getSubmittedTimeSheets()){
				for (TimeSheetLine timeSheetLine : timeSheet.getTimesheetlines()){
					String date = timeSheetLine.getDate();
					String employeeName = timeSheetLine.getEmployee();
					String projectName = timeSheetLine.getProject();
					int hours = timeSheetLine.getHours();

					// TODO: add this field to data (setters and getters)
					Boolean verified = false;

					timeSheetTableModel.addRow(new Object[]{date, employeeName, projectName, hours, verified});
				}
			}
		}
		timesheetLinesTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		timesheetLinesTable.setFillsViewportHeight(true);

		JScrollPane timeSheetSrollpane = new JScrollPane(timesheetLinesTable);
		timeSheetSrollpane.setSize(860, 291);
		timeSheetSrollpane.setLocation(20, 327);
		add(timeSheetSrollpane);
		// ------------------------------ TimeSheet table ------------------------------------------

		JLabel lblTimeSheet = new JLabel("Time Sheet");
		lblTimeSheet.setBounds(19, 298, 743, 30);
		add(lblTimeSheet);

		JCheckBox chckbxApprove = new JCheckBox("Approve");
		chckbxApprove.setBounds(774, 301, 105, 23);
		add(chckbxApprove);

	}
}
