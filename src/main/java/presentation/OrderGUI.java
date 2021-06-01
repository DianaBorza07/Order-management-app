package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei pentru Order
 */
public class OrderGUI {

	private JFrame frmOrder;
	private JTextField tfQuantity;

	public OrderGUI() {
		initialize();
	}

	/**
	 * Initializarea interfetei
	 */
	private void initialize() {
		frmOrder = new JFrame();
		frmOrder.getContentPane().setBackground(new Color(175, 238, 238));
		frmOrder.setTitle("Order");
		frmOrder.setBounds(100, 100, 490, 367);
		frmOrder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOrder.getContentPane().setLayout(null);
		frmOrder.setVisible(true);
		
		JLabel lblTitle = new JLabel("Place order");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(111, 21, 250, 22);
		frmOrder.getContentPane().add(lblTitle);
		
		JLabel lblProduct = new JLabel("Select product");
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProduct.setBounds(35, 84, 112, 22);
		frmOrder.getContentPane().add(lblProduct);
		
		JComboBox comboProducts = new JComboBox();
		comboProducts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboProducts.setBounds(188, 87, 257, 21);
		frmOrder.getContentPane().add(comboProducts);
		ProductBLL productBLL = new ProductBLL();
		List<String> products = productBLL.getProductsName();
		for (String s: products
			 ) {comboProducts.addItem(s);
		}
		
		JLabel lblClient = new JLabel("Select client");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClient.setBounds(35, 137, 122, 22);
		frmOrder.getContentPane().add(lblClient);
		
		JComboBox comboClients = new JComboBox();
		comboClients.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboClients.setBounds(188, 140, 257, 21);
		frmOrder.getContentPane().add(comboClients);
		ClientBLL clientBLL = new ClientBLL();
		List<String> clients = clientBLL.getClientsName();
		for (String s: clients
			 ) {comboClients.addItem(s);
		}
		
		JLabel lblQuantity = new JLabel("Insert desired quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(35, 195, 192, 22);
		frmOrder.getContentPane().add(lblQuantity);
		
		tfQuantity = new JTextField();
		tfQuantity.setBounds(261, 199, 184, 19);
		frmOrder.getContentPane().add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JButton btnPlaceOrder = new JButton("Place order");
		btnPlaceOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlaceOrder.setBounds(170, 265, 137, 29);
		frmOrder.getContentPane().add(btnPlaceOrder);
		btnPlaceOrder.addActionListener(a->{
			if(comboClients.getSelectedItem().toString().isBlank()||comboProducts.getSelectedItem().toString().isBlank()||tfQuantity.getText().isBlank())
				JOptionPane.showMessageDialog(null,"Fields cannot be empty!");
			String combo = comboProducts.getSelectedItem().toString();
			String[] aux = combo.split(" ");
			int productId = Integer.parseInt(aux[0]);
			String combo1 = comboClients.getSelectedItem().toString();
			String[] aux1 = combo1.split(" ");
			int clientId = Integer.parseInt(aux1[0]);
			int quantity = Integer.parseInt(tfQuantity.getText());
			int price = productBLL.getProductPrice(productId);
			int actualStock = productBLL.getProductStock(productId);
			if(actualStock>= quantity) {
				Order newOrder = new Order(productId, price, clientId, quantity);
				newOrder.generateTotalPrice();
				OrderBLL orderBLL = new OrderBLL();
				int ok = orderBLL.createOrder(newOrder);
				if (ok > 0) {
					JOptionPane.showMessageDialog(null, "Order created successfully");
					Product p = productBLL.findProductWithSpecifiedID(productId);
					p.setStock(p.getStock()-quantity);
					productBLL.editProductWithSpecifiedId(p,productId);
					tfQuantity.setText("");
					try { generateBill();
					} catch (IOException e) { e.printStackTrace(); }
				}
			}
			else JOptionPane.showMessageDialog(null,"The desired quantity is not available!");
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 75, 21);
		frmOrder.getContentPane().add(btnBack);
		btnBack.addActionListener(e->{
			StartGUI start = new StartGUI();
			frmOrder.dispose();
		});
	}

	/**
	 * Metoda care genereaza un fisier txt pentru bonul comenzilor
	 * @throws IOException
	 */
	private void generateBill() throws IOException {
		FileWriter fileWriter = null;
		try { fileWriter=new FileWriter("bill.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		OrderBLL orderBLL = new OrderBLL();
		ClientBLL cLientBLL = new ClientBLL();
		ProductBLL productBLL = new ProductBLL();
		List<Object> orders = orderBLL.getOrdersList();
		for (Object o: orders
			 ) {
			StringBuilder writeToFile = new StringBuilder();
			Order order = (Order) o;
			Client client = cLientBLL.findClientWithSpecifiedID(order.getClientID());
			Product product = productBLL.findProductWithSpecifiedID(order.getProductID());
			writeToFile.append("Order number: "+order.getId()+"\n");
			writeToFile.append("Product name: "+product.getProductName()+"\n");
			writeToFile.append("Client name: "+client.getClientName()+"\n");
			writeToFile.append("Product price: "+order.getProductPrice()+"\n");
			writeToFile.append("Quantity: "+order.getProductQuantity()+"\n");
			writeToFile.append("Total price: "+order.getTotalPrice()+"\n\n");
			fileWriter.append(writeToFile);
		}
		fileWriter.close();
	}

}
