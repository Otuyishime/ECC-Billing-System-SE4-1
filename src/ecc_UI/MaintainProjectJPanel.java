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
		
		JLabel lblNewLabel = new JLabel("Maintain Projects");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		add(lblNewLabel);
				
		for (Project p : employee.getProjects()){
			p.print();
		}
	}
}
