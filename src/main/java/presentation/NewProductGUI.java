package presentation;

import bll.ProductBLL;
import model.Product;

import java.awt.Font;

import javax.swing.*;
import java.awt.Color;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica intefetei de adaugare a unui nou produs
 */
public class NewProductGUI {

	private JFrame frmNewProduct;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfWeight;
	private JTextField tfStock;

	public NewProductGUI() {
		initialize();
	}

	/**
	 * Intitializarea interfetei
	 */
	private void initialize() {
		frmNewProduct = new JFrame();
		frmNewProduct.getContentPane().setBackground(new Color(255, 228, 225));
		frmNewProduct.setTitle("New Product");
		frmNewProduct.setBounds(100, 100, 450, 371);
		frmNewProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmNewProduct.getContentPane().setLayout(null);
		frmNewProduct.setVisible(true);
		
		JLabel lblTitle = new JLabel("Add new product");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(145, 10, 148, 24);
		frmNewProduct.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(32, 69, 74, 17);
		frmNewProduct.getContentPane().add(lblName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(32, 109, 112, 17);
		frmNewProduct.getContentPane().add(lblPrice);
		
		JLabel lblReturnable = new JLabel("Is returnable?");
		lblReturnable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReturnable.setBounds(32, 223, 112, 17);
		frmNewProduct.getContentPane().add(lblReturnable);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeight.setBounds(32, 184, 112, 17);
		frmNewProduct.getContentPane().add(lblWeight);
		
		tfName = new JTextField();
		tfName.setBounds(171, 70, 241, 19);
		frmNewProduct.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(171, 110, 241, 19);
		frmNewProduct.getContentPane().add(tfPrice);
		tfPrice.setColumns(10);
		
		JComboBox comboReturnable = new JComboBox();
		comboReturnable.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
		comboReturnable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboReturnable.setBounds(171, 221, 241, 21);
		frmNewProduct.getContentPane().add(comboReturnable);
		
		tfWeight = new JTextField();
		tfWeight.setBounds(171, 185, 241, 19);
		frmNewProduct.getContentPane().add(tfWeight);
		tfWeight.setColumns(10);
		
		JButton btnAdd = new JButton("Add product");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(145, 272, 148, 37);
		frmNewProduct.getContentPane().add(btnAdd);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(32, 146, 74, 13);
		frmNewProduct.getContentPane().add(lblStock);
		
		tfStock = new JTextField();
		tfStock.setBounds(171, 145, 241, 19);
		frmNewProduct.getContentPane().add(tfStock);
		tfStock.setColumns(10);

		btnAdd.addActionListener(a->{
			if(tfName.getText().isBlank()||tfStock.getText().isBlank()||tfPrice.getText().isBlank()||tfWeight.getText().isBlank())
				JOptionPane.showMessageDialog(null,"Fields cannot be empty!");
			int price = Integer.parseInt(tfPrice.getText());
			int stock = Integer.parseInt(tfStock.getText());
			Float weight = Float.parseFloat(tfWeight.getText());
			boolean isReturnable = Boolean.parseBoolean(comboReturnable.getSelectedItem().toString());
			Product newProduct= new Product(tfName.getText(),price,stock,weight,isReturnable);
			ProductBLL productBLL = new ProductBLL();
			int check = productBLL.insertProduct(newProduct);
			if(check>0) {
				JOptionPane.showMessageDialog(null, "Product added successfully");
				tfWeight.setText(""); tfPrice.setText("");tfStock.setText("");tfName.setText("");
			}
		});
	}

}
