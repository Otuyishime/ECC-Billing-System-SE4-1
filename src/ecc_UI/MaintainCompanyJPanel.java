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

public class MaintainCompanyJPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public MaintainCompanyJPanel(JFrame currentFrame) {
		setBackground(Color.LIGHT_GRAY);
		
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		
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
		
		textField = new JTextField();
		textField.setBounds(320, 120, 250, 30);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(320, 160, 250, 30);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(320, 200, 250, 30);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(320, 240, 250, 30);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(320, 280, 250, 30);
		add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(320, 386, 100, 30);
		add(btnCancel);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(470, 386, 100, 30);
		add(btnUpdate);
		
		textField_5 = new JTextField();
		textField_5.setBounds(320, 322, 250, 28);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblZipCode_1 = new JLabel("Zip Code:");
		lblZipCode_1.setBounds(255, 325, 61, 22);
		add(lblZipCode_1);
	}
}
