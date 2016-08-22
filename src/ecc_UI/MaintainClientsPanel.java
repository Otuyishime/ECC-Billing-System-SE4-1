package ecc_UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import testECC.*;
import utility.SimpleStates;
import utility.SimpleStatus;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//MaintainProjectsPanel
public class MaintainClientsPanel extends JPanel {
	private JTextField city_TextField;
	private JTextField addressLine2_TextField;
	private JTextField name_TextField;
	private JTextField zip_TextField;
	private JTextField addressLine1_TextField;
	private JTextField contact_TextField;
	private JTextField email_TextField;
	private JTextField billingFrequency_TextField;
	private JTextField billingInvoiceGrp_TextField;
	private JTextField billingTerms_TextField;
	private Client selectedClient = null;

	/**
	 * Create the panel.
	 */
	public MaintainClientsPanel(JFrame currentFrame, Employee employee) 
	{
		setBackground(Color.LIGHT_GRAY);
		setBounds(new Rectangle(0, 0, 900, 700));
		setLayout(null);

		JLabel lblHomePage = new JLabel("Add or Edit Clients");
		lblHomePage.setOpaque(true);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setBackground(new Color(165, 42, 42));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHomePage.setBounds(0, 0, 900, 60);
		add(lblHomePage);

		JLabel lblSelectProject = new JLabel("Name:");
		lblSelectProject.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSelectProject.setBounds(43, 89, 100, 30);
		add(lblSelectProject);

		JLabel lblClient = new JLabel("Address line 1:");
		lblClient.setHorizontalAlignment(SwingConstants.TRAILING);
		lblClient.setBounds(43, 127, 100, 30);
		add(lblClient);

		JLabel lblClientContact = new JLabel("Address line 2:");
		lblClientContact.setHorizontalAlignment(SwingConstants.TRAILING);
		lblClientContact.setBounds(43, 169, 100, 30);
		add(lblClientContact);

		JLabel lblBudgetAmount = new JLabel("City:");
		lblBudgetAmount.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBudgetAmount.setBounds(43, 209, 100, 30);
		add(lblBudgetAmount);

		city_TextField = new JTextField();
		city_TextField.setBounds(155, 210, 230, 28);
		add(city_TextField);
		city_TextField.setColumns(10);

		addressLine2_TextField = new JTextField();
		addressLine2_TextField.setBounds(155, 170, 230, 28);
		add(addressLine2_TextField);
		addressLine2_TextField.setColumns(10);

		name_TextField = new JTextField();
		name_TextField.setBounds(155, 90, 230, 28);
		add(name_TextField);
		name_TextField.setColumns(10);

		JLabel lblStartDate = new JLabel("State:");
		lblStartDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStartDate.setBounds(43, 249, 100, 30);
		add(lblStartDate);

		JLabel lblEndDate = new JLabel("Zip:");
		lblEndDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEndDate.setBounds(43, 289, 100, 30);
		add(lblEndDate);

		zip_TextField = new JTextField();
		zip_TextField.setColumns(10);
		zip_TextField.setBounds(155, 290, 230, 28);
		add(zip_TextField);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 375, 900, 1);
		add(panel);

		addressLine1_TextField = new JTextField();
		addressLine1_TextField.setColumns(10);
		addressLine1_TextField.setBounds(155, 128, 230, 28);
		add(addressLine1_TextField);

		contact_TextField = new JTextField();
		contact_TextField.setColumns(10);
		contact_TextField.setBounds(155, 328, 230, 28);
		add(contact_TextField);

		JComboBox state_ComboBox = new JComboBox(SimpleStates.statesAbbreviations);
		state_ComboBox.setBounds(155, 252, 230, 27);
		add(state_ComboBox);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setBounds(469, 89, 100, 30);
		add(lblEmail);

		email_TextField = new JTextField();
		email_TextField.setColumns(10);
		email_TextField.setBounds(581, 90, 230, 28);
		add(email_TextField);

		billingFrequency_TextField = new JTextField();
		billingFrequency_TextField.setColumns(10);
		billingFrequency_TextField.setBounds(581, 128, 230, 28);
		add(billingFrequency_TextField);

		JLabel lblBillingFrequency = new JLabel("Billing Frequency:");
		lblBillingFrequency.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBillingFrequency.setBounds(441, 127, 128, 30);
		add(lblBillingFrequency);

		JLabel lblBillingInvoice = new JLabel("Billing Invoice group:");
		lblBillingInvoice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBillingInvoice.setBounds(428, 169, 141, 30);
		add(lblBillingInvoice);

		JLabel lblBillingTerms = new JLabel("Billing terms:");
		lblBillingTerms.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBillingTerms.setBounds(469, 209, 100, 30);
		add(lblBillingTerms);

		billingInvoiceGrp_TextField = new JTextField();
		billingInvoiceGrp_TextField.setColumns(10);
		billingInvoiceGrp_TextField.setBounds(581, 170, 230, 28);
		add(billingInvoiceGrp_TextField);

		billingTerms_TextField = new JTextField();
		billingTerms_TextField.setColumns(10);
		billingTerms_TextField.setBounds(581, 210, 230, 28);
		add(billingTerms_TextField);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setBounds(469, 249, 100, 30);
		add(lblStatus);

		JComboBox status_ComboBox = new JComboBox(SimpleStatus.getClientStatuses().toArray());
		status_ComboBox.setBounds(581, 252, 230, 27);
		add(status_ComboBox);

		JLabel lblProjectManager = new JLabel("Contact:");
		lblProjectManager.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProjectManager.setBounds(43, 327, 100, 30);
		add(lblProjectManager);

		String[] projectsColumns = {"Name","Address line 1","City","State","Email","Contact"};
		DefaultTableModel clientsTableModel = new DefaultTableModel(projectsColumns, 0);
		JTable clientsTable = new JTable(clientsTableModel);
		JScrollPane clients_scrollPane = new JScrollPane(clientsTable);
		clients_scrollPane.setBounds(10, 412, 880, 230);
		
		populateTimeSheetsTable(clientsTableModel, employee);
		add(clients_scrollPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(711, 328, 100, 30);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = name_TextField.getText();
				String companyAddressLine1 = addressLine1_TextField.getText();
				String companyAddressLine2 = addressLine2_TextField.getText();
				String city = city_TextField.getText();
				String state = state_ComboBox.getSelectedItem().toString();
				String zip = zip_TextField.getText();
				String contact = contact_TextField.getText();
				String clientEmail = email_TextField.getText();
				String billingfrequency = billingFrequency_TextField.getText();
				String billingterms = billingInvoiceGrp_TextField.getText();
				String billinginvoicinggroup = billingTerms_TextField.getText();
				String status = status_ComboBox.getSelectedItem().toString();

				if (!name.isEmpty() || !companyAddressLine1.isEmpty() || !companyAddressLine2.isEmpty() || !city.isEmpty() || !state.isEmpty() || !zip.isEmpty() || !contact.isEmpty() || 
						!clientEmail.isEmpty() || !billingfrequency.isEmpty() || !billingterms.isEmpty() || !billinginvoicinggroup.isEmpty() || !status.isEmpty()){
					if ( selectedClient != null){
						selectedClient.setName(name);
						selectedClient.setCompanyAddressLine1(companyAddressLine1);
						selectedClient.setCompanyAddressLine2(companyAddressLine2);
						selectedClient.setCity(city);
						selectedClient.setState(state);
						selectedClient.setZip(zip);
						selectedClient.setContact(contact);
						selectedClient.setClientEmail(clientEmail);
						selectedClient.setBillingfrequency(billingfrequency);
						selectedClient.setBillingterms(billingterms);
						selectedClient.setBillinginvoicinggroup(billinginvoicinggroup);
						selectedClient.setClientStatus(status);

						Company.EditClient(employee, selectedClient);
					}

					btnUpdate.setEnabled(false);
					btnAdd.setEnabled(true);
					
					name_TextField.setText("");
					addressLine1_TextField.setText("");
					addressLine2_TextField.setText("");
					city_TextField.setText("");
					zip_TextField.setText("");
					contact_TextField.setText("");
					email_TextField.setText("");
					billingFrequency_TextField.setText("");
					billingInvoiceGrp_TextField.setText("");
					billingTerms_TextField.setText("");
					
					int numberOfRow = clientsTableModel.getRowCount();
					for (int i = 0; i < numberOfRow; i++){
						clientsTableModel.removeRow(0);
					}
					populateTimeSheetsTable(clientsTableModel, employee);
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(592, 328, 100, 30);
		add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				name_TextField.setText("");
				addressLine1_TextField.setText("");
				addressLine2_TextField.setText("");
				city_TextField.setText("");
				zip_TextField.setText("");
				contact_TextField.setText("");
				email_TextField.setText("");
				billingFrequency_TextField.setText("");
				billingInvoiceGrp_TextField.setText("");
				billingTerms_TextField.setText("");

				btnUpdate.setEnabled(false);
				btnAdd.setEnabled(true);
			}
		});
		btnCancel.setBounds(469, 328, 100, 30);
		add(btnCancel);

		JButton btnEditClient = new JButton("Edit Client");
		btnEditClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedClientName = clientsTableModel.getValueAt(clientsTable.getSelectedRow(), 0).toString();
				selectedClient = employee.getCompany().getClientByName(selectedClientName);

				name_TextField.setText(selectedClient.getName());
				addressLine1_TextField.setText(selectedClient.getCompanyAddressLine1());
				addressLine2_TextField.setText(selectedClient.getCompanyAddressLine2());
				city_TextField.setText(selectedClient.getCity());
				state_ComboBox.setSelectedItem(selectedClient.getState());
				zip_TextField.setText(selectedClient.getZip());
				contact_TextField.setText(selectedClient.getContact());
				email_TextField.setText(selectedClient.getClientEmail());
				billingFrequency_TextField.setText(selectedClient.getBillingfrequency());
				billingInvoiceGrp_TextField.setText(selectedClient.getBillinginvoicinggroup());
				billingTerms_TextField.setText(selectedClient.getBillingterms());
				status_ComboBox.setSelectedItem(selectedClient.getClientStatus());

				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
			}
		});
		btnEditClient.setBounds(778, 380, 116, 30);
		add(btnEditClient);
	}

	private void populateTimeSheetsTable(DefaultTableModel clientsTableModel, Employee employee){
		// populate the table
		if( !employee.getCompany().getClients().isEmpty()){
			for( Client client : employee.getCompany().getClients()){
				clientsTableModel.addRow(new Object[]{client.getName(), client.getCompanyAddressLine1(), 
						client.getCity(), client.getState(), client.getClientEmail(), client.getContact()});
			}
		}
	}
}
