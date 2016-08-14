package ecc_UI;

import javax.swing.*;

import testECC.*;

public class MaintainProjectJPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public MaintainProjectJPanel(JFrame currentFrame, Employee employee) {
				
		for (Project p : employee.getProjects()){
			p.print();
		}
	}
}
