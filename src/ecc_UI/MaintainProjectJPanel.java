package ecc_UI;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MaintainProjectJPanel extends JPanel {
	
	private JScrollPane scrollpane_projects;
	private DefaultListModel <String> listModel_projects;
	private JList list_projects;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public MaintainProjectJPanel(JFrame currentFrame) {
		
		setBounds(new Rectangle(0, 0, 1100, 700));
		setLayout(null);
		
		JLabel lblMaintain = new JLabel("List of projects");
		lblMaintain.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMaintain.setBounds(100, 20, 200, 40);
		add(lblMaintain);
		
		listModel_projects = new DefaultListModel<>();
		
		ArrayList<String> projects = new ArrayList();
		for (int i = 0; i < 5; i++){
			projects.add("Project - " + (i+1));
		}
		for (String string : projects)
			listModel_projects.addElement(string);
		
		list_projects = new JList(listModel_projects);
		list_projects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (list_projects.getSelectedValue() != null)
				{
					//btn_editFacultyInfo.setEnabled(true);
					//btn_deleteFaculty.setEnabled(true);
				}
				else
				{
					//btn_editFacultyInfo.setEnabled(false);
					//btn_deleteFaculty.setEnabled(false);
				}
			}
		});
		scrollpane_projects = new JScrollPane(list_projects);
		scrollpane_projects.setBounds(100, 80, 250, 400);
		add(scrollpane_projects);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(100, 493, 80, 30);
		add(btnEdit);
		
		JButton btnAdd = new JButton("Add New");
		btnAdd.setBounds(183, 494, 117, 30);
		add(btnAdd);
		
		JLabel lblChooseClue = new JLabel("Choose Client:");
		lblChooseClue.setBounds(400, 80, 100, 30);
		add(lblChooseClue);
		
		JLabel lblProjectNumber = new JLabel("Project Number:");
		lblProjectNumber.setBounds(400, 120, 110, 30);
		add(lblProjectNumber);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(400, 160, 100, 30);
		add(lblProjectName);
		
		JLabel lblStartData = new JLabel("Start Data:");
		lblStartData.setBounds(400, 200, 100, 30);
		add(lblStartData);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(400, 280, 100, 30);
		add(lblStatus);
		
		JLabel lblProjectManager = new JLabel("Project Manager:");
		lblProjectManager.setBounds(400, 320, 110, 30);
		add(lblProjectManager);
		
		JLabel lblBudget = new JLabel("Budget:");
		lblBudget.setBounds(400, 360, 100, 30);
		add(lblBudget);
		
		String[] clients = {"client one", "client two", "client three", "client four", "client five"};	// TODO: this should be fetched from the available clients
		JComboBox comboBoxClients = new JComboBox(clients);
		comboBoxClients.setBounds(510, 80, 150, 30);
		add(comboBoxClients);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(510, 320, 150, 30);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(510, 280, 150, 30);
		add(comboBox_1);
		
		String[] months = {"January", "February", "March", "May", "June", "July", "August", "September", "October", "Novermber", "December"};
		JComboBox comboBoxMonths_start = new JComboBox(months);
		comboBoxMonths_start.setBounds(510, 200, 150, 30);
		add(comboBoxMonths_start);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(400, 240, 100, 30);
		add(lblEndDate);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(510, 240, 150, 30);
		add(comboBox_3);
		
		JComboBox comboBoxDays_start = new JComboBox();
		comboBoxDays_start.setBounds(670, 200, 100, 30);
		add(comboBoxDays_start);
		
		JComboBox comboBoxYears_start = new JComboBox();
		comboBoxYears_start.setBounds(780, 200, 100, 30);
		add(comboBoxYears_start);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(670, 240, 100, 30);
		add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(780, 240, 100, 30);
		add(comboBox_7);
		
		textField = new JTextField();
		textField.setBounds(510, 120, 150, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(510, 160, 150, 30);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(510, 360, 150, 30);
		add(textField_2);
		textField_2.setColumns(10);
	}
}
