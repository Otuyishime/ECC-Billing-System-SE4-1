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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;

import testECC.*;
import utility.SimpleDate;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class DeveloperHomePageJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField hours_textField;
	private JTextField date_textField;

	/**
	 * Create the panel.
	 */
	public DeveloperHomePageJPanel(JFrame currentFrame, Employee employee) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(new Color(165, 42, 42));

		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);

		JLabel lblHomePage = new JLabel("Welcome to the ECC! Record Worked Hours");
		lblHomePage.setOpaque(true);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setBackground(new Color(165, 42, 42));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(0, 0, 900, 60);
		add(lblHomePage);


		ArrayList <String> projectsNames = new ArrayList<String>();
		for (Project p : employee.getProjects()){
			projectsNames.add(p.getName());
		}
		// String[] projects = {"project one", "project two", "project three"}; // TODO: this should be fetched from the available projects
		JComboBox comboBoxProjects = new JComboBox(projectsNames.toArray());
		comboBoxProjects.setBounds(121, 100, 200, 30);
		add(comboBoxProjects);
		comboBoxProjects.setSelectedIndex(0);

		JLabel lblSelectProject = new JLabel("Select Project:");
		lblSelectProject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectProject.setBounds(20, 99, 89, 30);
		add(lblSelectProject);

		JLabel lblEnterHours = new JLabel("Enter Hours:");
		lblEnterHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterHours.setBounds(20, 141, 89, 30);
		add(lblEnterHours);

		hours_textField = new JTextField();
		hours_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		hours_textField.setBounds(121, 141, 200, 30);
		add(hours_textField);
		hours_textField.setColumns(10);

		JLabel lblFromDateTimeSheet = new JLabel("Date");
		lblFromDateTimeSheet.setHorizontalAlignment(SwingConstants.CENTER);
		lblFromDateTimeSheet.setBounds(647, 265, 100, 30);
		add(lblFromDateTimeSheet);

		JLabel lblToDateTimeSheet = new JLabel("Date");
		lblToDateTimeSheet.setHorizontalAlignment(SwingConstants.CENTER);
		lblToDateTimeSheet.setBounds(791, 265, 89, 30);
		add(lblToDateTimeSheet);

		JLabel lblTo = new JLabel("To:");
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(759, 265, 20, 30);
		add(lblTo);

		JLabel lblFrom = new JLabel("From:");
		lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom.setBounds(595, 265, 40, 30);
		add(lblFrom);

		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener(
				new PropertyChangeListener(){
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						date_textField.setText(SimpleDate.getTodayDate(calendar.getDate()));
						if( employee.getCurrentTimeSheet() == null ){
							Calendar tmp_calendar = Calendar.getInstance();
							tmp_calendar.setTime(calendar.getDate());
							tmp_calendar.set(calendar.getCalendar().DAY_OF_WEEK, calendar.getCalendar().MONDAY);
							lblFromDateTimeSheet.setText(SimpleDate.getTodayDate(tmp_calendar.getTime()));

							tmp_calendar.set(calendar.getCalendar().DAY_OF_WEEK, calendar.getCalendar().SUNDAY);
							lblToDateTimeSheet.setText(SimpleDate.getTodayDate(tmp_calendar.getTime()));
						}
					}
				});
		calendar.setBounds(360, 100, 520, 155);
		add(calendar);

		// ----------------------- add and populate the time sheet line table -------------------------------
		String[] columns = {"Date", "Employee", "Project", "Hours", "Verified"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		JTable timesheetLinesTable = new JTable(tableModel){
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};

		if ( employee.getCurrentTimeSheet() != null){
			// update the from and to labels
			Calendar tmp_calendar = Calendar.getInstance();
			tmp_calendar.setTime(calendar.getDate());
			tmp_calendar.set(calendar.getCalendar().DAY_OF_WEEK, calendar.getCalendar().MONDAY);
			lblFromDateTimeSheet.setText(SimpleDate.getTodayDate(tmp_calendar.getTime()));
			
			tmp_calendar.add(tmp_calendar.DATE, 6);
			lblToDateTimeSheet.setText(SimpleDate.getTodayDate(tmp_calendar.getTime()));

			// initialize the time sheet model - if there is any time sheet line
			if (!(employee.getCurrentTimeSheet().getTimesheetlines().isEmpty())){
				for (TimeSheetLine timeSheetLine : employee.getCurrentTimeSheet().getTimesheetlines()){
					String date = timeSheetLine.getDate();
					String employeeName = timeSheetLine.getEmployee();
					String projectName = timeSheetLine.getProject();
					int hours = timeSheetLine.getHours();

					// TODO: add this field to data (setters and getters)
					Boolean verified = false;
					tableModel.addRow(new Object[]{date, employeeName, projectName, hours, verified});
				}
			}
		}else{
			// create a new time sheet
			createNewTimeSheet(employee);
		}

		timesheetLinesTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		timesheetLinesTable.setFillsViewportHeight(true);

		JScrollPane srollpane = new JScrollPane(timesheetLinesTable);
		srollpane.setSize(860, 350);
		srollpane.setLocation(20, 295);
		add(srollpane);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = date_textField.getText();
				String employeeName = employee.getName();
				String projectName = comboBoxProjects.getSelectedItem().toString();
				int hours = Integer.parseInt(hours_textField.getText());

				tableModel.addRow(new Object[]{date, employeeName, projectName, hours, new Boolean(false)});

				// add hours to the time sheet
				Company.EnterHours(employee, projectName, date, hours);

				// clean the hours text field
				hours_textField.setText("");
			}
		});
		btnSave.setBounds(184, 223, 80, 30);
		add(btnSave);

		JLabel lblTimeSheet = new JLabel("Time Sheet");
		lblTimeSheet.setBounds(20, 265, 563, 30);
		add(lblTimeSheet);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 260, 900, 1);
		add(panel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(20, 183, 89, 30);
		add(lblDate);

		date_textField = new JTextField();
		date_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		date_textField.setText(SimpleDate.getTodayDate(calendar.getDate()));
		date_textField.setColumns(10);
		date_textField.setBounds(121, 184, 200, 30);
		add(date_textField);
	}

	private void createNewTimeSheet(Employee loggedinemployee){

		// create a new time sheet
		TimeSheet timesheet = new TimeSheet();
		timesheet.setCompany(loggedinemployee.getCompany());
		timesheet.setEmployee(loggedinemployee);
		timesheet.setSubmitted(false);

		if (!Company.AddNewTimeSheet(loggedinemployee, timesheet)){
			System.out.println("Failed to add the timesheet!!!");
		}
	}
}
