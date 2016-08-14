package ecc_UI;

import javax.swing.*;

import testECC.*;
import java.awt.Color;
import java.awt.Rectangle;

public class MaintainProjectJPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public MaintainProjectJPanel(JFrame currentFrame, Employee employee) {
		setBounds(new Rectangle(0, 0, 900, 700));
		setBackground(Color.LIGHT_GRAY);
				
		for (Project p : employee.getProjects()){
			p.print();
		}
	}
}
