package ecc_UI;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import testECC.Company;
import testECC.Employee;
import utility.SimpleStates;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MaintainCompanyJPanel extends JPanel {
	private JTextField textField_name;
	private JTextField textField_address1;
	private JTextField textField_address2;
	private JTextField textField_city;
	private JTextField textField_zip;

	/**
	 * Create the panel.
	 */
	public MaintainCompanyJPanel(JFrame currentFrame, Employee employee) {
		setBackground(Color.LIGHT_GRAY);
		
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		
		Company company = employee.getCompany();
		
		JLabel lblWelcomeToEagles = new JLabel("Update Company Information");
		lblWelcomeToEagles.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToEagles.setForeground(Color.WHITE);
		lblWelcomeToEagles.setBackground(new Color(165, 42, 42));
		lblWelcomeToEagles.setOpaque(true);
		lblWelcomeToEagles.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblWelcomeToEagles.setBounds(0, 0, 900, 60);
		add(lblWelcomeToEagles);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(275, 120, 45, 30);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address Line 1:");
		lblAddress.setBounds(220, 160, 100, 30);
		add(lblAddress);
		
		JLabel lblCity = new JLabel("Address Line 2:");
		lblCity.setBounds(220, 200, 100, 30);
		add(lblCity);
		
		JLabel lblState = new JLabel("City:");
		lblState.setBounds(285, 240, 35, 30);
		add(lblState);
		
		JLabel lblZipCode = new JLabel("State:");
		lblZipCode.setBounds(280, 280, 40, 30);
		add(lblZipCode);
		
		textField_name = new JTextField(company.getName());
		textField_name.setBounds(320, 120, 250, 30);
		add(textField_name);
		textField_name.setColumns(10);
		
		textField_address1 = new JTextField(company.getCompanyAddressline1());
		textField_address1.setBounds(320, 160, 250, 30);
		add(textField_address1);
		textField_address1.setColumns(10);
		
		textField_address2 = new JTextField(company.getCompanyAddressline2());
		textField_address2.setBounds(320, 200, 250, 30);
		add(textField_address2);
		textField_address2.setColumns(10);
		
		textField_city = new JTextField(company.getCity());
		textField_city.setBounds(320, 240, 250, 30);
		add(textField_city);
		textField_city.setColumns(10);

		JLabel lblZipCode_1 = new JLabel("Zip Code:");
		lblZipCode_1.setBounds(255, 325, 61, 22);
		add(lblZipCode_1);
		
		textField_zip = new JTextField(company.getZip());
		textField_zip.setBounds(320, 322, 250, 30);
		add(textField_zip);
		textField_zip.setColumns(10);
		
		JComboBox comboBox_states = new JComboBox(SimpleStates.states);
		comboBox_states.setBounds(320, 283, 250, 30);
		if (company.getState().equals("OK")){
			comboBox_states.setSelectedItem("Oklahoma");
		}
		else{
			comboBox_states.setSelectedItem(company.getState());
		}
		add(comboBox_states);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				AccountantHomePageJPanel accountantHomePageJPanel = new AccountantHomePageJPanel(currentFrame, employee);
				currentFrame.getContentPane().add(accountantHomePageJPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(320, 386, 100, 30);
		add(btnCancel);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update comany's information
				String name = textField_name.getText();
				String address1 = textField_address1.getText();
				String address2 = textField_address2.getText();
				String city = textField_city.getText();
				String state = comboBox_states.getSelectedItem().toString();
				String zip = textField_zip.getText();
				
				System.out.print("Updating company with name: " + name + " address1: " + address1 + " address2: " + address2 + " city: " + city + " state: " + state + " zip: " + zip);
				
				// update company's info
				company.setName(name);
				company.setCompanyAddressline1(address1);
				company.setCompanyAddressline2(address2);
				company.setCity(city);
				company.setState(state);
				company.setZip(zip);
				Company.EditCompany(company);
				
				currentFrame.getContentPane().removeAll();
				MaintainCompanyJPanel maintainCompanyJPanel = new MaintainCompanyJPanel(currentFrame, employee);
				currentFrame.getContentPane().add(maintainCompanyJPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnUpdate.setBounds(470, 386, 100, 30);
		add(btnUpdate);
	}
}
