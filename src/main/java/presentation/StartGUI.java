package presentation;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica interfetei de pornire a aplicatiei
 */
public class StartGUI {

	private JFrame frmStart;

	public StartGUI() {
		initialize();
	}

	/**
	 * Initializarea interfetei
	 */
	private void initialize() {
		frmStart = new JFrame();
		frmStart.getContentPane().setBackground(new Color(216, 191, 216));
		frmStart.getContentPane().setLayout(null);
		frmStart.setVisible(true);
		
		JLabel lblTitle = new JLabel("Select the desired operation");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(92, 10, 219, 45);
		frmStart.getContentPane().add(lblTitle);
		
		JButton btnClient = new JButton("Client operations");
		btnClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClient.setBounds(41, 96, 170, 50);
		frmStart.getContentPane().add(btnClient);
		btnClient.addActionListener(a->{ClientGUI clientGUI=new ClientGUI();
		frmStart.dispose();});
		
		JButton btnProduct = new JButton("Product operations");
		btnProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProduct.setBounds(164, 172, 170, 50);
		frmStart.getContentPane().add(btnProduct);
		btnProduct.addActionListener(a->{
			ProductGUI productGUI = new ProductGUI();
			frmStart.dispose();
		});
		
		JButton btnOrder = new JButton("Order operations");
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOrder.setBounds(41, 251, 170, 50);
		frmStart.getContentPane().add(btnOrder);
		btnOrder.addActionListener(a->{
			OrderGUI orderGUI = new OrderGUI();
			frmStart.dispose();
		});

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 0, 255));
		separator.setBounds(0, 75, 379, 11);
		frmStart.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.MAGENTA);
		separator_1.setBounds(0, 156, 379, 11);
		frmStart.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.MAGENTA);
		separator_2.setBounds(0, 232, 379, 11);
		frmStart.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.MAGENTA);
		separator_3.setBounds(0, 311, 379, 11);
		frmStart.getContentPane().add(separator_3);
		frmStart.setTitle("Start");
		frmStart.setBounds(100, 100, 393, 382);
		frmStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
