package ecc_UI;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MaintainCompanyJPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public MaintainCompanyJPanel(JFrame currentFrame) {
		
		setBounds(new Rectangle(0, 0, 1100, 700));
		setLayout(null);
		
		JLabel lblWelcomeToEagles = new JLabel("Maintian Compnay Data - You can edit and save the uptodate information about the compnay");
		lblWelcomeToEagles.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblWelcomeToEagles.setBounds(250, 50, 600, 40);
		add(lblWelcomeToEagles);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(250, 120, 70, 30);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(250, 160, 70, 30);
		add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(250, 200, 70, 30);
		add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(250, 240, 70, 30);
		add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip code:");
		lblZipCode.setBounds(250, 280, 70, 30);
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
		btnCancel.setBounds(320, 350, 100, 30);
		add(btnCancel);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(431, 350, 100, 30);
		add(btnUpdate);
	}
}
