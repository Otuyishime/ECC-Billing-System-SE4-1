package ecc_UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import testECC.Employee;

public class MaintainProjectsPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public MaintainProjectsPanel(JFrame currentFrame, Employee employee) 
	{
		setBackground(Color.LIGHT_GRAY);
		setForeground(new Color(165, 42, 42));

		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);

		JLabel lblHomePage = new JLabel("Add or Edit Projects");
		lblHomePage.setOpaque(true);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setBackground(new Color(165, 42, 42));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(0, 0, 900, 60);
		add(lblHomePage);

		JLabel lblSelectProject = new JLabel("Name");
		lblSelectProject.setBounds(43, 96, 100, 30);
		add(lblSelectProject);

		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(43, 138, 100, 30);
		add(lblClient);

		JLabel lblClientContact = new JLabel("Client Contact");
		lblClientContact.setBounds(43, 180, 100, 30);
		add(lblClientContact);

		JLabel lblBudgetAmount = new JLabel("Budget Amount");
		lblBudgetAmount.setBounds(43, 222, 100, 30);
		add(lblBudgetAmount);

		textField = new JTextField();
		textField.setBounds(155, 223, 230, 28);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(155, 181, 230, 28);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(155, 97, 230, 28);
		add(textField_2);
		textField_2.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(155, 141, 230, 27);
		add(comboBox);

		JLabel lblStartDate = new JLabel("Start date");
		lblStartDate.setBounds(43, 264, 100, 30);
		add(lblStartDate);

		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(43, 306, 100, 30);
		add(lblEndDate);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(736, 349, 100, 30);
		add(btnAdd);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(155, 265, 230, 28);
		add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(155, 307, 230, 28);
		add(textField_4);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(468, 349, 100, 30);
		add(btnCancel);

		JLabel lblProjectManager = new JLabel("Project Manager");
		lblProjectManager.setBounds(43, 348, 100, 30);
		add(lblProjectManager);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(155, 351, 230, 27);
		add(comboBox_1);

		String[] drawableDevColumns = {"Name"};
		DefaultTableModel drawableDevTableModel = new DefaultTableModel(drawableDevColumns, 0);
		JTable drawableDevTable = new JTable(drawableDevTableModel);
		JScrollPane scrollPane = new JScrollPane(drawableDevTable);
		scrollPane.setBounds(412, 96, 207, 240);
		add(scrollPane);

		String[] assignedDevColumns = {"Name"};
		DefaultTableModel assignedDevTableModel = new DefaultTableModel(drawableDevColumns, 0);
		JTable assignedDevTable = new JTable(assignedDevTableModel);
		JScrollPane scrollPane_1 = new JScrollPane(assignedDevTable);
		scrollPane_1.setBounds(683, 96, 207, 239);
		add(scrollPane_1);

		String[] projectsColumns = {"Name","Client","Client contact","Budget","Start date","End date","Status","Billed amount"};
		DefaultTableModel projectsTableModel = new DefaultTableModel(projectsColumns, 0);
		JTable projectsDevTable = new JTable(projectsTableModel);
		JScrollPane scrollPane_2 = new JScrollPane(projectsDevTable);
		scrollPane_2.setBounds(10, 423, 880, 222);
		add(scrollPane_2);

		JButton btnEditProject = new JButton("Edit project");
		btnEditProject.setBounds(774, 392, 116, 30);
		add(btnEditProject);

		JLabel lblAssignDevelopersTo = new JLabel("Assign developers to the project");
		lblAssignDevelopersTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignDevelopersTo.setBounds(412, 72, 478, 16);
		add(lblAssignDevelopersTo);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(600, 349, 100, 30);
		add(btnUpdate);
		
		JButton button = new JButton("-->");
		button.setBounds(620, 182, 62, 30);
		add(button);
		
		JButton button_1 = new JButton("<--");
		button_1.setBounds(620, 222, 62, 30);
		add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 390, 900, 1);
		add(panel);

	}
}
