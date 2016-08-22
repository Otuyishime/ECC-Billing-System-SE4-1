package ecc_UI;

import javax.swing.JApplet;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import dAO.CompanyDAO;
import testECC.Company;
import testECC.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Rectangle;

public class UserAccountJPanel extends JApplet {

	private JTextField NameTField;
	private JTextField TitleTField;
	private JComboBox  roleComboBox;
	private JTextField emailTField;
	private JTextField payRateTField;
	private JTable table;
	private JButton addButton;
	private JButton btnEdit;
	private JTable userTable;
	private EmployeeTableModel tableModel;
	private JTextField passWordTField;
	private JTextField confirmPassWordTField;

	/**
	 * Create the applet.
	 */
	public UserAccountJPanel(JFrame currentFrame) {

		JLabel lblUserAccountMaintainance = new JLabel("User Account Maintainance");

		JLabel lblName = new JLabel("Name");

		NameTField = new JTextField();
		NameTField.setColumns(10);

		TitleTField = new JTextField();
		TitleTField.setColumns(10);

		JLabel lblTitle = new JLabel("Title");

		JLabel lblRole = new JLabel("Role");

		roleComboBox = new JComboBox();

		JLabel lblEmail = new JLabel("e-mail");

		emailTField = new JTextField();
		emailTField.setColumns(10);

		JLabel lblPayRate = new JLabel("Pay Rate");

		payRateTField = new JTextField();
		payRateTField.setColumns(10);

		JLabel lblStatus = new JLabel("Active");

		JRadioButton rdbtnNewRadioButton = new JRadioButton("No");

		JRadioButton rdbtnNonActive = new JRadioButton("Yes");

		table = new JTable();

		JPanel EmployeeTableJPanel = new JPanel();
		EmployeeTableJPanel.setBounds(new Rectangle(20, 0, 860, 0));

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnEdit = new JButton("Edit");

		JLabel lblPassWord = new JLabel("Pass word");

		passWordTField = new JTextField();
		passWordTField.setColumns(10);

		JLabel lblConfirmPassword = new JLabel("Confirm PassWord");

		confirmPassWordTField = new JTextField();
		confirmPassWordTField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(646, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(254))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(162)
					.addComponent(lblUserAccountMaintainance)
					.addContainerGap(567, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRole)
								.addComponent(lblPassWord)
								.addComponent(lblPayRate))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(roleComboBox, 0, 373, Short.MAX_VALUE)
										.addComponent(passWordTField, Alignment.LEADING, 373, 373, 373)
										.addComponent(NameTField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(payRateTField, Alignment.LEADING, 373, 373, 373))
									.addGap(70)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmail)
										.addComponent(lblStatus)
										.addComponent(lblConfirmPassword)
										.addComponent(lblTitle))
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnNewRadioButton)
											.addGap(18)
											.addComponent(rdbtnNonActive, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(TitleTField)
											.addComponent(emailTField)
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(4)
												.addComponent(confirmPassWordTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addGap(81))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEdit)
									.addGap(18)
									.addComponent(addButton)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addComponent(lblName))
					.addGap(31))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addComponent(EmployeeTableJPanel, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblUserAccountMaintainance)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(NameTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
						.addComponent(TitleTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassWord)
						.addComponent(passWordTField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmPassword)
						.addComponent(confirmPassWordTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRole)
						.addComponent(roleComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(emailTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(payRateTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPayRate)
								.addComponent(lblStatus)
								.addComponent(rdbtnNewRadioButton)
								.addComponent(rdbtnNonActive))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEdit)
								.addComponent(addButton))))
					.addGap(4)
					.addComponent(EmployeeTableJPanel, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		EmployeeTableJPanel.setLayout(new BorderLayout(0, 0));
		this.tableModel= new EmployeeTableModel();
		userTable = new JTable(tableModel);
		//add(new JScrollPane(userTable));
		EmployeeTableJPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
		this.refreshTableEmployeeTable();
	}

	public void setTableModel(List<Employee>employeeList){
		this.tableModel.setEmployeeList(employeeList);	
	}
	public void UpdateTable(){
		this.tableModel.updateTable();
	}

	public void refreshTableEmployeeTable(){
		List<Employee> employees = CompanyDAO.listCompanies().get(0).getEmployees();
		this.setTableModel(employees);
		this.UpdateTable();
	}
}
