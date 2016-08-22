package ecc_UI;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import testECC.Employee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjectManagerHomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProjectManagerHomePanel(JFrame currentFrame, Employee employee) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(new Color(165, 42, 42));
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Welcome to ECC!");
		lblTitle.setBounds(new Rectangle(0, 0, 900, 60));
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBackground(new Color(165, 42, 42));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTitle.setBounds(0, 0, 900, 60);
		add(lblTitle);
		
		JButton btnEnterHours = new JButton("Enter Work Hours");
		btnEnterHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				EmployeeHomePageJPanel homepageJPanel = new EmployeeHomePageJPanel(currentFrame, employee);
				currentFrame.getContentPane().add(homepageJPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEnterHours.setForeground(new Color(139, 0, 0));
		btnEnterHours.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnEnterHours.setBounds(192, 285, 200, 200);
		add(btnEnterHours);
		
		JButton btnVerifyDevHours = new JButton("Verify Dev Work Hours");
		btnVerifyDevHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				VerifyEmployeeHoursPageJPanel verifyEmployeeHoursPageJPanel = new VerifyEmployeeHoursPageJPanel(currentFrame, employee);
				currentFrame.getContentPane().add(verifyEmployeeHoursPageJPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnVerifyDevHours.setForeground(new Color(128, 0, 0));
		btnVerifyDevHours.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnVerifyDevHours.setBounds(538, 285, 200, 200);
		add(btnVerifyDevHours);
		
		JLabel lblNewLabel = new JLabel("Choose what to do:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 196, 900, 30);
		add(lblNewLabel);

	}
}
