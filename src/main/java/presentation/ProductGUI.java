package presentation;

import bll.ProductBLL;
import bll.TableBLL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei unui Produs
 */
public class ProductGUI {

	private JFrame frmProduct;
	private JScrollPane scrollPane;
	private JTable table;

	public ProductGUI() {
		initialize();
	}

	/**
	 * Initializarea interfetei
	 */
	private void initialize() {
		frmProduct = new JFrame();
		frmProduct.getContentPane().setBackground(new Color(253, 245, 230));
		frmProduct.setTitle("Product");
		frmProduct.setBounds(100, 100, 757, 474);
		frmProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProduct.getContentPane().setLayout(null);
		frmProduct.setVisible(true);
		
		JButton btnAdd = new JButton("Add new product");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBounds(145, 10, 140, 35);
		frmProduct.getContentPane().add(btnAdd);
		btnAdd.addActionListener(a->{
			NewProductGUI newProductGUI = new NewProductGUI();
		});
		
		JButton btnEdit = new JButton("Edit product");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(295, 10, 140, 35);
		frmProduct.getContentPane().add(btnEdit);
		btnEdit.addActionListener(a->{
			ModifyProductGUI modifyProductGUI = new ModifyProductGUI();
		});
		
		JButton btnDelete = new JButton("Delete product");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(445, 10, 140, 35);
		frmProduct.getContentPane().add(btnDelete);
		btnDelete.addActionListener(a->{
			int rowValue=table.getSelectedRow();
			int clientID = Integer.parseInt((String) table.getModel().getValueAt(rowValue,0));
			ProductBLL productBLL = new ProductBLL();
			int deleted = productBLL.deleteProductWithSpecifiedID(clientID);
			if(deleted>0)
				JOptionPane.showMessageDialog(null,"Product deleted!");
		});
		
		JButton btnView = new JButton("View all products");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnView.setBounds(595, 11, 140, 35);
		frmProduct.getContentPane().add(btnView);
		btnView.addActionListener(a->{
			try {
				generateTable();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		
		JLabel lblTitle = new JLabel("<html>Product operations</html>");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(10, 10, 125, 35);
		frmProduct.getContentPane().add(lblTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 215, 0));
		separator.setForeground(new Color(255, 228, 225));
		separator.setBounds(0, 65, 751, 11);
		frmProduct.getContentPane().add(separator);
		
		scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 84, 713, 318);
        frmProduct.getContentPane().add(scrollPane);
        scrollPane.setVisible(true);
        table = new JTable();
        table.setBackground(new Color(255, 218, 185));
        table.setVisible(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBounds(20, 57, 784, 352);
        scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(20, 410, 85, 21);
		frmProduct.getContentPane().add(btnBack);
		btnBack.addActionListener(e->{
			StartGUI start = new StartGUI();
			frmProduct.dispose();
		});
	}

	/**
	 * Metoda care seteaza modelul tabelului
	 * @param columnNames numele coloanelor
	 * @param rowsCount numarul de randuri
	 */
	private void setTableModel(List<String> columnNames, int rowsCount){
		String[] col = new String[6];
		int i=0;
		for (String s: columnNames
		) { col[i] =s;
			i++;
		}
		table.setModel(new DefaultTableModel(
				new Object[rowsCount][6] ,
				col
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(3);
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),30));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(20);
	}

	/**
	 * Metoda care populeaza tabelul
	 * @throws IllegalAccessException
	 */
	private void generateTable() throws IllegalAccessException {
		ProductBLL productBLL = new ProductBLL();
		List<Object> products = productBLL.getProductsList();
		TableBLL tableBLL = new TableBLL();
		List<List<String>> rows = tableBLL.generateTable(products);
		setTableModel(rows.get(0),products.size());
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
