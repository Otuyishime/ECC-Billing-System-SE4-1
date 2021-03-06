package ecc_UI;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import testECC.Employee;
import testECC.testMain;
import utility.Credential;
import utility.SimpleRole;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.SwingConstants;

public class WelcomeJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JTextField usernameField;
	private JPasswordField passwordField;
	//private static Employee loggedInEmployee;

	/**
	 * Create the panel.
	 */
	public WelcomeJPanel(final JFrame currentFrame) {
		setBackground(Color.GRAY);
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		
		JLabel lblWelcomeToEagles = new JLabel("Welcome to Eagles Consulting Company!");
		lblWelcomeToEagles.setBackground(new Color(165, 42, 42));
		lblWelcomeToEagles.setOpaque(true);
		lblWelcomeToEagles.setForeground(Color.WHITE);
		lblWelcomeToEagles.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToEagles.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblWelcomeToEagles.setBounds(0, 0, 900, 269);
		add(lblWelcomeToEagles);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(300, 300, 70, 30);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(300, 340, 70, 30);
		add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(380, 300, 200, 30);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(380, 340, 200, 30);
		add(passwordField);
		
		JLabel lbl_loginfail = new JLabel("");
		lbl_loginfail.setForeground(Color.RED);
		lbl_loginfail.setBounds(542, 382, 212, 30);
		add(lbl_loginfail);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get login info
				String username = usernameField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				
				Credential cred = new Credential();
				cred.setUsername(username);
				cred.setPassword(password);
				
				Employee employee = testMain.logIn(cred);
			
				if (!(employee == null))
				{
					currentFrame.getJMenuBar().getMenu(0).setEnabled(true);
					currentFrame.getJMenuBar().getMenu(1).setEnabled(true);
					currentFrame.getJMenuBar().getMenu(2).setEnabled(true);
					currentFrame.getJMenuBar().getMenu(3).setEnabled(true);
					
					System.out.println("Successfully logged in - username: " + username + " - pwd: " + password);
					
					((SystemJFrame)currentFrame).loggedInEmployee = testMain.logIn(cred);
					
					if (employee.getRole().equals(SimpleRole.DEVELOPER)){
						currentFrame.getContentPane().removeAll();
						EmployeeHomePageJPanel homepageJPanel = new EmployeeHomePageJPanel(currentFrame, employee);
						currentFrame.getContentPane().add(homepageJPanel);
						currentFrame.getContentPane().revalidate();
					}
					else if(employee.getRole().equals(SimpleRole.PROJECTMANAGER)){
						currentFrame.getContentPane().removeAll();
						ProjectManagerHomePanel projectManagerHomePanel = new ProjectManagerHomePanel(currentFrame, employee);
						currentFrame.getContentPane().add(projectManagerHomePanel);
						currentFrame.getContentPane().revalidate();
					}
					else{
						
						currentFrame.getContentPane().removeAll();
						AccountantHomePageJPanel accountantHomePageJPanel = new AccountantHomePageJPanel(currentFrame, employee);
						currentFrame.getContentPane().add(accountantHomePageJPanel);
						currentFrame.getContentPane().revalidate();
					}
				}
				else
				{
					lbl_loginfail.setText("Login failed. Wrong credentials!");
				}
			}
		});
		btnLogin.setBounds(430, 380, 100, 30);
		add(btnLogin);
	}
}
