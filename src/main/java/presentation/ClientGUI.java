package presentation;

import bll.ClientBLL;
import bll.TableBLL;

import javax.swing.*;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei pentru client
 */
public class ClientGUI {

	private JFrame frmClient;
	private JTable table;
	private JScrollPane scrollPane;

	public ClientGUI() {
		initialize();
	}

	/**
	 * Metoda de initializare a interfetei
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.getContentPane().setBackground(new Color(230, 230, 250));
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 765, 450);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		frmClient.setVisible(true);
		
		JButton btnAdd = new JButton("Add new client");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBounds(145, 10, 140, 35);
		frmClient.getContentPane().add(btnAdd);
		btnAdd.addActionListener(a->{
			NewClientGUI newClientGUI = new NewClientGUI();
		});
		
		JButton btnEdit = new JButton("Edit client");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(295, 10, 140, 35);
		frmClient.getContentPane().add(btnEdit);
		btnEdit.addActionListener(a->{
			ModifyClientGUI modifyClientGUI = new ModifyClientGUI();
		});
		
		JButton btnDelete = new JButton("Delete client");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(445, 10, 140, 35);
		frmClient.getContentPane().add(btnDelete);
		btnDelete.addActionListener(a->{
			int rowValue=table.getSelectedRow();
			int clientID = Integer.parseInt((String) table.getModel().getValueAt(rowValue,0));
			ClientBLL clientBLL = new ClientBLL();
			int deleted = clientBLL.deleteClientWithSpecifiedID(clientID);
			if(deleted>0)
				JOptionPane.showMessageDialog(null,"Client deleted!");

		});
		
		JButton btnView = new JButton("View all clients");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnView.setBounds(595, 11, 140, 35);
		frmClient.getContentPane().add(btnView);
		btnView.addActionListener(a->{
			try {
				generateTable();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		
		JLabel lblTitle = new JLabel("<html>Client operations</html>");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(10, 10, 125, 35);
		frmClient.getContentPane().add(lblTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(221, 160, 221));
		separator.setForeground(new Color(147, 112, 219));
		separator.setBounds(0, 65, 751, 11);
		frmClient.getContentPane().add(separator);
		
		scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 86, 715, 304);
        frmClient.getContentPane().add(scrollPane);
        scrollPane.setVisible(true);
        table = new JTable();
        table.setBackground(new Color(216, 191, 216));
        table.setVisible(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBounds(20, 57, 784, 352);
        scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(20, 390, 62, 21);
		frmClient.getContentPane().add(btnBack);
		btnBack.addActionListener(e->{
			StartGUI start = new StartGUI();
			frmClient.dispose();
		});
	}

	/**
	 * Metoda care seteaza headerul tabelului si modelul acestuia
	 * @param columnNames numele coloanelor
	 * @param rowsCount numarul de linii
	 */
	private void setTableModel(List<String> columnNames,int rowsCount){
		String[] col = new String[5];
		int i=0;
		for (String s: columnNames
			 ) { col[i] =s;
			   i++;
		}
		table.setModel(new DefaultTableModel(
				new Object[rowsCount][5] ,
				col
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(3);
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),30));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(20);
	}

	/**
	 * Metoda care genereaza tabelul obtinut prin metoda reflectiei
	 * @throws IllegalAccessException
	 */
	private void generateTable() throws IllegalAccessException {
		ClientBLL clientBLL = new ClientBLL();
		List<Object> clients = clientBLL.getClientsList();
		TableBLL tableBLL = new TableBLL();
		List<List<String>> rows = tableBLL.generateTable(clients);
		setTableModel(rows.get(0),clients.size());
		int idxRow = 0;
		for (List<String> row: rows
			 ) {
			if(idxRow!=0){
			 	int idxCol=0;
			for (String column: row
				 ) {table.getModel().setValueAt(column,idxRow-1,idxCol);
				 idxCol++;
			       }
		   }
		idxRow++;
		}
	 }


}
