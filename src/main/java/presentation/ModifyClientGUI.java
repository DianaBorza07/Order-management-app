package presentation;

import bll.ClientBLL;
import model.Client;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.*;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei pentru modificarea unui client existent
 */
public class ModifyClientGUI {

	private JFrame frmEditClient;
	private JTextField tfNewName;
	private JTextField tfNewEmail;
	private JTextField tfNewPhone;

	public ModifyClientGUI() {
		initialize();
	}

	/**
	 * Initializarea interfetei
	 */
	private void initialize() {
        frmEditClient = new JFrame();
		frmEditClient.getContentPane().setBackground(new Color(173, 216, 230));
		frmEditClient.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmEditClient.setTitle("Edit client");
		frmEditClient.getContentPane().setLayout(null);
		frmEditClient.setBounds(100, 100, 450, 413);
		frmEditClient.setVisible(true);
		
		JLabel lblTitle = new JLabel("Edit client");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTitle.setBounds(146, 10, 174, 21);
		frmEditClient.getContentPane().add(lblTitle);
		
		JLabel lblClientName = new JLabel("Client name");
		lblClientName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClientName.setBounds(25, 59, 105, 13);
		frmEditClient.getContentPane().add(lblClientName);
		
		tfNewName = new JTextField();
		tfNewName.setBounds(162, 142, 242, 19);
		frmEditClient.getContentPane().add(tfNewName);
		tfNewName.setColumns(10);
		
		JComboBox comboClients = new JComboBox();
		comboClients.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboClients.setBounds(162, 57, 242, 21);
		frmEditClient.getContentPane().add(comboClients);
		ClientBLL clientBLL = new ClientBLL();
		List<String> clients = clientBLL.getClientsName();
		for (String s: clients
			 ) { comboClients.addItem(s);
		}
		
		JLabel lblNewAttributes = new JLabel("New informations about the client");
		lblNewAttributes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAttributes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewAttributes.setBounds(49, 100, 342, 21);
		frmEditClient.getContentPane().add(lblNewAttributes);
		
		JLabel lblNewName = new JLabel("New name");
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewName.setBounds(31, 142, 121, 16);
		frmEditClient.getContentPane().add(lblNewName);
		
		JLabel lblNewEmail = new JLabel("New email address");
		lblNewEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewEmail.setBounds(31, 185, 121, 13);
		frmEditClient.getContentPane().add(lblNewEmail);
		
		tfNewEmail = new JTextField();
		tfNewEmail.setBounds(160, 184, 244, 19);
		frmEditClient.getContentPane().add(tfNewEmail);
		tfNewEmail.setColumns(10);
		
		JLabel lblGender = new JLabel("Modify gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(32, 227, 120, 19);
		frmEditClient.getContentPane().add(lblGender);
		
		JComboBox comboGender = new JComboBox();
		comboGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboGender.setBounds(162, 225, 242, 21);
		frmEditClient.getContentPane().add(comboGender);
		
		JLabel lblNewPhone = new JLabel("New phone");
		lblNewPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewPhone.setBounds(31, 271, 127, 21);
		frmEditClient.getContentPane().add(lblNewPhone);
		
		tfNewPhone = new JTextField();
		tfNewPhone.setBounds(162, 270, 242, 19);
		frmEditClient.getContentPane().add(tfNewPhone);
		tfNewPhone.setColumns(10);
		
		JButton btnEdit = new JButton("Edit client");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(143, 314, 152, 35);
		frmEditClient.getContentPane().add(btnEdit);
		btnEdit.addActionListener(a->{
			String comboSelected = comboClients.getSelectedItem().toString();
			if(comboSelected.isBlank()||tfNewEmail.getText().isBlank()||tfNewName.getText().isBlank()||tfNewPhone.getText().isBlank())
				JOptionPane.showMessageDialog(null,"Fields cannot be empty");
			String[] aux = comboSelected.split(" ");
			int clientID = Integer.parseInt(aux[0]);
			Client newClient = new Client(clientID,tfNewName.getText(),tfNewEmail.getText(),comboGender.getSelectedItem().toString(),tfNewPhone.getText());
			int check = clientBLL.editClientWithSpecifiedId(newClient,clientID);
			if(check>0) {
				JOptionPane.showMessageDialog(null, "Client modified successfully");
				tfNewPhone.setText("");tfNewName.setText("");tfNewEmail.setText("");
			}
		});
		
	}

}
