package presentation;

import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei pentru adaugarea unui nou client
 */
public class NewClientGUI {

	private JFrame frmNewClient;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;

	public NewClientGUI() {
		initialize();
	}

	/**
	 * Initializarea interfetei
	 */
	private void initialize() {
		frmNewClient = new JFrame();
		frmNewClient.getContentPane().setBackground(new Color(240, 255, 240));
		frmNewClient.setTitle("New Client");
		frmNewClient.setBounds(100, 100, 450, 349);
		frmNewClient.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmNewClient.getContentPane().setLayout(null);
		frmNewClient.setVisible(true);
		
		JLabel lblTitle = new JLabel("Add new client");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(145, 10, 148, 24);
		frmNewClient.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(32, 69, 74, 17);
		frmNewClient.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("Email address");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(32, 109, 112, 17);
		frmNewClient.getContentPane().add(lblEmail);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(32, 153, 74, 17);
		frmNewClient.getContentPane().add(lblGender);
		
		JLabel lblPhone = new JLabel("Phone number");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhone.setBounds(32, 195, 112, 17);
		frmNewClient.getContentPane().add(lblPhone);
		
		tfName = new JTextField();
		tfName.setBounds(171, 70, 241, 19);
		frmNewClient.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(171, 110, 241, 19);
		frmNewClient.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JComboBox comboGender = new JComboBox();
		comboGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboGender.setBounds(171, 153, 241, 21);
		frmNewClient.getContentPane().add(comboGender);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(171, 193, 241, 19);
		frmNewClient.getContentPane().add(tfPhone);
		tfPhone.setColumns(10);
		
		JButton btnAdd = new JButton("Add client");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(145, 249, 148, 21);
		frmNewClient.getContentPane().add(btnAdd);

		btnAdd.addActionListener(a->{
			if(tfName.getText().isBlank()||tfEmail.getText().isBlank()||tfPhone.getText().isBlank())
				JOptionPane.showMessageDialog(null,"Fields cannot be empty!");
			Client newClient = new Client(tfName.getText(),tfEmail.getText(),comboGender.getSelectedItem().toString(),tfPhone.getText());
			ClientBLL clientBLL = new ClientBLL();
			int check = clientBLL.insertClient(newClient);
			if(check>0) {
				JOptionPane.showMessageDialog(null, "Client added successfully");
				tfPhone.setText("");tfName.setText("");tfEmail.setText("");
			}
		});
	}
}
