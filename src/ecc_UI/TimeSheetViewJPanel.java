package ecc_UI;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeSheetViewJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TimeSheetViewJPanel() {
		
		setBounds(new Rectangle(0, 0, 1100, 700));
		setLayout(null);
		
		JLabel lblHomePage = new JLabel("Here is a list of hours you have worked");
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(230, 20, 600, 40);
		add(lblHomePage);

	}

}
