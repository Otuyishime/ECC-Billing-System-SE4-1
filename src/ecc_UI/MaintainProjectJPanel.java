package ecc_UI;

import javax.swing.*;

import testECC.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

public class MaintainProjectJPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public MaintainProjectJPanel(JFrame currentFrame, Employee employee) {
		setBounds(new Rectangle(0, 0, 900, 700));
		setBackground(Color.LIGHT_GRAY);
		
<<<<<<< HEAD
		setBounds(new Rectangle(0, 0, 1100, 700));
		setLayout(null);
		
		JLabel lblMaintain = new JLabel("List of projects");
		lblMaintain.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMaintain.setBounds(100, 20, 200, 40);
		add(lblMaintain);
		
		//listModel_projects = new DefaultListModel<>();
		
		ArrayList<String> projects = new ArrayList();
		for (int i = 0; i < 5; i++){
			projects.add("Project - " + (i+1));
		}
		for (String string : projects)
			listModel_projects.addElement(string);
		
		list_projects = new JList(listModel_projects);
		list_projects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
=======
		JLabel lblNewLabel = new JLabel("Maintain Projects");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		add(lblNewLabel);
>>>>>>> branch 'master' of https://github.com/Otuyishime/ECC-Billing-System-SE4-1.git
				
		for (Project p : employee.getProjects()){
			p.print();
		}
	}
}
