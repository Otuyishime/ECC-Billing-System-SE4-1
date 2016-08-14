package ecc_UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

import testECC.*;
import utility.SimpleDate;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class HomePageJPanel extends JPanel {
	private JTextField hours_textField;

	/**
	 * Create the panel.
	 */
	public HomePageJPanel(JFrame currentFrame, Employee employee) {
		setBackground(Color.GRAY);
		setForeground(new Color(165, 42, 42));
		
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		
		JLabel lblHomePage = new JLabel("Welcome to the ECC. Use the Menu bar for more options");
		lblHomePage.setForeground(new Color(165, 42, 42));
		lblHomePage.setBackground(new Color(165, 42, 42));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(6, 6, 888, 54);
		add(lblHomePage);
		
		JLabel lblNewLabel = new JLabel("Record Hours");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(330, 70, 100, 30);
		add(lblNewLabel);
		
		
		ArrayList <String> projectsNames = new ArrayList<String>();
		for (Project p : employee.getProjects()){
			projectsNames.add(p.getName());
		}
		// String[] projects = {"project one", "project two", "project three"}; // TODO: this should be fetched from the available projects
		JComboBox comboBoxProjects = new JComboBox(projectsNames.toArray());
		comboBoxProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxProjects.setBounds(330, 100, 200, 30);
		add(comboBoxProjects);
		comboBoxProjects.setSelectedIndex(0);
		
		JLabel lblSelectProject = new JLabel("Select Project");
		lblSelectProject.setBounds(230, 100, 100, 30);
		add(lblSelectProject);
		
		JLabel lblEnterHours = new JLabel("Enter Hours:");
		lblEnterHours.setBounds(230, 140, 100, 30);
		add(lblEnterHours);
		
		hours_textField = new JTextField();
		hours_textField.setBounds(330, 140, 200, 30);
		add(hours_textField);
		hours_textField.setColumns(10);
		
		String[] columns = {"Date", "Employee", "Project", "Hours", "Verified"};
		
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		JTable timesheetLinesTable = new JTable(tableModel){
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};
		
		// initialize the timesheet model - if there is any timesheet line
		if (!(employee.getCurrentTimeSheet().getTimesheetlines().isEmpty())){
			for (TimeSheetLine timeSheetLine : employee.getCurrentTimeSheet().getTimesheetlines()){
				String date = timeSheetLine.getDate();
				String employeeName = timeSheetLine.getEmployee();
				String projectName = timeSheetLine.getProject();
				int hours = timeSheetLine.getHours();
				
				// TODO: add this field to data (accessors)
				Boolean verified = false;
	
				tableModel.addRow(new Object[]{date, employeeName, projectName, hours, verified});
			}
		}

		timesheetLinesTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		timesheetLinesTable.setFillsViewportHeight(true);
		
		JScrollPane srollpane = new JScrollPane(timesheetLinesTable);
		srollpane.setSize(860, 380);
		srollpane.setLocation(20, 267);
		
		add(srollpane);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = SimpleDate.getTodayDate();
				String employeeName = employee.getName();
				String projectName = comboBoxProjects.getSelectedItem().toString();
				int hours = Integer.parseInt(hours_textField.getText());
				
				tableModel.addRow(new Object[]{date, employeeName, projectName, hours, new Boolean(false)});
				TimeSheetLine newTimeSheetLine = new TimeSheetLine(date, hours, employeeName, projectName, false);
				newTimeSheetLine.print();
				
				Company.EnterHours(employee, projectName, hours);
			}
		});
		btnSave.setBounds(450, 180, 80, 30);
		add(btnSave);
		
	}
}
