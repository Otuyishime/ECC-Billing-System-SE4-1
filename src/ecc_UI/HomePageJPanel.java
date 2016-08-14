package ecc_UI;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

import testECC.*;

public class HomePageJPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public HomePageJPanel(JFrame currentFrame, Employee employee) {
		
		setBounds(new Rectangle(0, 0, 1100, 700));
		setLayout(null);
		
		JLabel lblHomePage = new JLabel("Welcome to the ECC. Use the Menu bar for more options");
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(230, 20, 600, 40);
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
		
		String[] days = {"Monday", "Tuesday", "Wednsday", "Thursday", "Friday", "Satuday", "Sunday"};
		
		JLabel lblEnterHours = new JLabel("Enter Hours:");
		lblEnterHours.setBounds(230, 140, 100, 30);
		add(lblEnterHours);
		
		textField = new JTextField();
		textField.setBounds(330, 140, 200, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(450, 180, 80, 30);
		add(btnSave);
		
		// build 

	}
}
