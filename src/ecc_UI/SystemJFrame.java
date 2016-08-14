package ecc_UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemJFrame extends JFrame {
	private JFrame currentFrame;
	JMenuBar menuBar;
	JMenu mnSystem;
	JMenu mnMaintain;
	JMenu mnInvoice;
	JMenu mnReports;
	JMenu mnHelp;

	/**
	 * Create the frame.
	 */
	public SystemJFrame() {
		currentFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eagles Consulting Company");
		setBounds(100, 0, 900, 700);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSystem = new JMenu("System");
		mnSystem.setEnabled(false);
		menuBar.add(mnSystem);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "are you sure you want to logout?", "Confirm Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == JOptionPane.YES_OPTION){
					mnSystem.setEnabled(false);
					mnMaintain.setEnabled(false);
					mnInvoice.setEnabled(false);
					mnReports.setEnabled(false);
					
					getContentPane().removeAll();
					WelcomeJPanel welcomeJPanel = new WelcomeJPanel(currentFrame);
					getContentPane().add(welcomeJPanel);
					getContentPane().revalidate();
				    System.out.println("No button clicked");	
				}
			}
		});
		mnSystem.add(mntmLogout);
		
		mnMaintain = new JMenu("Maintain");
		mnMaintain.setEnabled(false);
		menuBar.add(mnMaintain);
		
		JMenuItem mntmCompany = new JMenuItem("Company");
		mntmCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO - this is to be completed
				
				getContentPane().removeAll();
				MaintainCompanyJPanel maintaincompanyJPanelJPanel = new MaintainCompanyJPanel(currentFrame);
				getContentPane().add(maintaincompanyJPanelJPanel);
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCompany);
		
		JMenuItem mntmClients = new JMenuItem("Clients");
		mnMaintain.add(mntmClients);
		
		JMenuItem mntmProjects = new JMenuItem("Projects");
		mntmProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				MaintainProjectJPanel maintainProjectJPanel = new MaintainProjectJPanel(currentFrame);
				getContentPane().add(maintainProjectJPanel);
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmProjects);
		
		JMenuItem mntmEmployees = new JMenuItem("Employees");
		mnMaintain.add(mntmEmployees);
		
		mnInvoice = new JMenu("Invoice");
		mnInvoice.setEnabled(false);
		menuBar.add(mnInvoice);
		
		JMenuItem mntmGenerateInvoice = new JMenuItem("Generate Invoice");
		mnInvoice.add(mntmGenerateInvoice);
		
		JMenuItem mntmSendInvoice = new JMenuItem("Send Invoice");
		mnInvoice.add(mntmSendInvoice);
		
		mnReports = new JMenu("Reports");
		mnReports.setEnabled(false);
		menuBar.add(mnReports);
		
		JMenuItem mntmTimesheets = new JMenuItem("Timesheets");
		mnReports.add(mntmTimesheets);
		
		JMenuItem mntmProjectProgress = new JMenuItem("Project Progress");
		mnReports.add(mntmProjectProgress);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		getContentPane().removeAll();
		WelcomeJPanel welcomeJPanel = new WelcomeJPanel(currentFrame);
		getContentPane().add(welcomeJPanel);
		getContentPane().revalidate();
	}

}
