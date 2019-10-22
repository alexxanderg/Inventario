package gui_principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;

public class Creditos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Creditos dialog = new Creditos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Creditos() {
		setTitle("Creditos");
		setResizable(false);
		setBounds(100, 100, 465, 262);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(220, 220, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("BYTE x BYTE PER\u00DA E.I.R.L");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 439, 45);
		contentPanel.add(lblNewLabel);
		
		this.lblNewLabel_1 = new JLabel("RUC:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_1.setBounds(20, 75, 95, 20);
		contentPanel.add(this.lblNewLabel_1);
		
		this.lblNewLabel_2 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_2.setBounds(20, 161, 95, 20);
		contentPanel.add(this.lblNewLabel_2);
		
		this.lblNewLabel_3 = new JLabel("Celular:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_3.setBounds(20, 122, 95, 20);
		contentPanel.add(this.lblNewLabel_3);
		
		this.lblNewLabel_4 = new JLabel("20604635447");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_4.setBounds(125, 75, 324, 20);
		contentPanel.add(this.lblNewLabel_4);
		
		this.lblNewLabel_5 = new JLabel("<html>Calle Octavio Mu\u00F1oz Najar 213<br>Interior 207</html>");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_5.setBounds(125, 159, 324, 45);
		contentPanel.add(this.lblNewLabel_5);
		
		this.lblNewLabel_6 = new JLabel("986865523");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblNewLabel_6.setBounds(125, 122, 324, 20);
		contentPanel.add(this.lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBackground(new Color(105, 105, 105));
		textField.setBounds(0, 0, 459, 45);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblBxB = new JLabel("");
		lblBxB.setHorizontalAlignment(SwingConstants.CENTER);
		Image img2 = new ImageIcon(this.getClass().getResource("/imgBXB.png")).getImage();
		lblBxB.setIcon(new ImageIcon(img2));
		lblBxB.setBounds(330, 54, 100, 100);
		contentPanel.add(lblBxB);
		
	}
}
