package ecc_UI;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import testECC.Employee;
import testECC.testMain;
import utility.Credential;
import utility.SimpleRole;
import dAO.CompanyDAO;
import dAO.EmployeeDAO;
import billingProjectModel.MainFrameQuery;
import billingProjectModel.MainFrameServiceImp;
import billingProjectModel.MainFrameServiceImp;
import testECC.Company;
import testECC.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;

public class SystemJFrame extends JFrame {
	public static Employee loggedInEmployee;
	private JFrame currentFrame;
	private MainFrameQuery mainFrameQuery;
	private MainFrameServiceImp mainFrameServiceImpl;
	private UserAccountJPanel userAcctountJpanel;
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
		setResizable(false);

		currentFrame = this;
		this.userAcctountJpanel =  new UserAccountJPanel(currentFrame);
		//this.refreshTableEmployeeTable();


		//this.mainFrameServiceImpl= new MainFrameServiceImp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eagles Consulting Company");
		setBounds(100, 0, 900, 700);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnSystem = new JMenu("System");
		mnSystem.setEnabled(false);
		menuBar.add(mnSystem);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (loggedInEmployee.getRole().equals(SimpleRole.DEVELOPER)){
					currentFrame.getContentPane().removeAll();
					EmployeeHomePageJPanel homepageJPanel = new EmployeeHomePageJPanel(currentFrame, loggedInEmployee);
					currentFrame.getContentPane().add(homepageJPanel);
					currentFrame.getContentPane().revalidate();
				}
				else if(loggedInEmployee.getRole().equals(SimpleRole.PROJECTMANAGER)){
					currentFrame.getContentPane().removeAll();
					ProjectManagerHomePanel projectManagerHomePanel = new ProjectManagerHomePanel(currentFrame, loggedInEmployee);
					currentFrame.getContentPane().add(projectManagerHomePanel);
					currentFrame.getContentPane().revalidate();
				}
				else{
					
					currentFrame.getContentPane().removeAll();
					AccountantHomePageJPanel accountantHomePageJPanel = new AccountantHomePageJPanel(currentFrame, loggedInEmployee);
					currentFrame.getContentPane().add(accountantHomePageJPanel);
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		mnSystem.add(mntmHome);

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
				MaintainCompanyJPanel maintaincompanyJPanelJPanel = new MaintainCompanyJPanel(currentFrame, loggedInEmployee);
				getContentPane().add(maintaincompanyJPanelJPanel);
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCompany);

		JMenuItem mntmClients = new JMenuItem("Clients");
		mntmClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				MaintainClientsPanel maintainClientsPanel = new MaintainClientsPanel(currentFrame, loggedInEmployee);
				getContentPane().add(maintainClientsPanel);
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmClients);

		JMenuItem mntmProjects = new JMenuItem("Projects");
		mntmProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				MaintainProjectsPanel MaintainProjectsPanel = new MaintainProjectsPanel(currentFrame, loggedInEmployee);
				getContentPane().add(MaintainProjectsPanel);
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmProjects);

		JMenuItem mntmEmployees = new JMenuItem("Employees");
		mnMaintain.add(mntmEmployees);

		mntmEmployees.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

				getContentPane().removeAll();
				userAcctountJpanel= new UserAccountJPanel(currentFrame);
				getContentPane().add(userAcctountJpanel);
				getContentPane().revalidate();
			}	
		});
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

		menuBar.add(Box.createHorizontalGlue());

		JMenu mnLogin = new JMenu("loggedInUser");
		mnLogin.setEnabled(false);
		menuBar.add(mnLogin);

		// add menu items
		JMenuItem mntmAccount = new JMenuItem("account");
		mnLogin.add(mntmAccount);

		getContentPane().removeAll();
		WelcomeJPanel welcomeJPanel = new WelcomeJPanel(currentFrame);
		welcomeJPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(welcomeJPanel);
		getContentPane().revalidate();
	}
}
