package presentation;

import bll.ProductBLL;
import model.Product;

import java.awt.Font;

import javax.swing.*;

import java.awt.Color;
import java.util.List;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei pentru modificarea unui produs existent
 */
public class ModifyProductGUI {

	private JFrame frmEditProduct;
	private JTextField tfPrice;
	private JTextField tfWeight;
	private JTextField tfStock;
	private JTextField tfNewName;

	public ModifyProductGUI() {
		initialize();
	}

	/**
	 * Intitializarea interfetei
	 */
	private void initialize() {
		frmEditProduct = new JFrame();
		frmEditProduct.getContentPane().setBackground(new Color(250, 250, 210));
		frmEditProduct.setTitle("Edit Product");
		frmEditProduct.setBounds(100, 100, 450, 460);
		frmEditProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmEditProduct.getContentPane().setLayout(null);
		frmEditProduct.setVisible(true);
		
		JLabel lblTitle = new JLabel("Edit product");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(145, 10, 148, 24);
		frmEditProduct.getContentPane().add(lblTitle);
		
		JLabel lblName = new JLabel("Product name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(32, 69, 101, 17);
		frmEditProduct.getContentPane().add(lblName);
		
		JLabel lblPrice = new JLabel("New price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(32, 202, 112, 17);
		frmEditProduct.getContentPane().add(lblPrice);
		
		JLabel lblReturnable = new JLabel("Is returnable?");
		lblReturnable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReturnable.setBounds(32, 322, 112, 17);
		frmEditProduct.getContentPane().add(lblReturnable);
		
		JLabel lblWeight = new JLabel("New weight");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeight.setBounds(32, 281, 112, 17);
		frmEditProduct.getContentPane().add(lblWeight);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(171, 203, 241, 19);
		frmEditProduct.getContentPane().add(tfPrice);
		tfPrice.setColumns(10);
		
		JComboBox comboReturnable = new JComboBox();
		comboReturnable.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
		comboReturnable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboReturnable.setBounds(171, 320, 241, 21);
		frmEditProduct.getContentPane().add(comboReturnable);

		tfWeight = new JTextField();
		tfWeight.setBounds(171, 282, 241, 19);
		frmEditProduct.getContentPane().add(tfWeight);
		tfWeight.setColumns(10);
		
		JButton btnEdit = new JButton("Edit product");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(145, 361, 148, 37);
		frmEditProduct.getContentPane().add(btnEdit);
		
		JLabel lblStock = new JLabel("New stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(32, 242, 74, 13);
		frmEditProduct.getContentPane().add(lblStock);
		
		tfStock = new JTextField();
		tfStock.setBounds(171, 241, 241, 19);
		frmEditProduct.getContentPane().add(tfStock);
		tfStock.setColumns(10);
		
		JComboBox comboName = new JComboBox();
		comboName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboName.setBounds(171, 69, 241, 21);
		frmEditProduct.getContentPane().add(comboName);
		ProductBLL productBLL = new ProductBLL();
		List<String> products = productBLL.getProductsName();
		for (String s: products
		) {comboName.addItem(s);
		}
		
		JLabel lblNewInformationsAbout = new JLabel("New informations about the product");
		lblNewInformationsAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewInformationsAbout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewInformationsAbout.setBounds(55, 114, 342, 21);
		frmEditProduct.getContentPane().add(lblNewInformationsAbout);
		
		JLabel lblNewName = new JLabel("New name");
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewName.setBounds(32, 166, 101, 13);
		frmEditProduct.getContentPane().add(lblNewName);
		
		tfNewName = new JTextField();
		tfNewName.setBounds(171, 165, 241, 19);
		frmEditProduct.getContentPane().add(tfNewName);
		tfNewName.setColumns(10);

		btnEdit.addActionListener(a->{
			String comboSelected = comboName.getSelectedItem().toString();
			if(comboSelected.isBlank()||tfPrice.getText().isBlank()||tfNewName.getText().isBlank()||tfStock.getText().isBlank()||tfWeight.getText().isBlank())
				JOptionPane.showMessageDialog(null,"Fields cannot be empty");
			String[] aux = comboSelected.split(" ");
			int id = Integer.parseInt(aux[0]);
			int price = Integer.parseInt(tfPrice.getText());
			int stock = Integer.parseInt(tfStock.getText());
			float weight = Float.parseFloat(tfWeight.getText());
			boolean isReturnable = Boolean.parseBoolean(comboReturnable.getSelectedItem().toString());
			Product product = new Product(id,tfNewName.getText(),price,stock,weight,isReturnable);
			int check = productBLL.editProductWithSpecifiedId(product,id);
			if(check>0) {
				JOptionPane.showMessageDialog(null, "Product modified successfully");
				tfWeight.setText(""); tfPrice.setText("");tfStock.setText("");tfNewName.setText("");
			}
		});
	}

}
