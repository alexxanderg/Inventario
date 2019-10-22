package gui_principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Creditos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblReferencia;
	private JLabel lblCostadoDeNova;

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
		setBounds(100, 100, 342, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("BYTE x BYTE PER\u00DA E.I.R.L");
		lblNewLabel.setBounds(10, 11, 306, 14);
		contentPanel.add(lblNewLabel);
		
		this.lblNewLabel_1 = new JLabel("RUC:");
		this.lblNewLabel_1.setBounds(10, 36, 63, 14);
		contentPanel.add(this.lblNewLabel_1);
		
		this.lblNewLabel_2 = new JLabel("Direcci\u00F3n:");
		this.lblNewLabel_2.setBounds(10, 61, 63, 14);
		contentPanel.add(this.lblNewLabel_2);
		
		this.lblNewLabel_3 = new JLabel("Celular:");
		this.lblNewLabel_3.setBounds(10, 114, 63, 14);
		contentPanel.add(this.lblNewLabel_3);
		
		this.lblNewLabel_4 = new JLabel("20604635447");
		this.lblNewLabel_4.setBounds(83, 36, 160, 14);
		contentPanel.add(this.lblNewLabel_4);
		
		this.lblNewLabel_5 = new JLabel("Calle Octavio Mu\u00F1oz Najar 20B 213 Interior 207");
		this.lblNewLabel_5.setBounds(83, 61, 233, 14);
		contentPanel.add(this.lblNewLabel_5);
		
		this.lblNewLabel_6 = new JLabel("986865523");
		this.lblNewLabel_6.setBounds(83, 114, 95, 14);
		contentPanel.add(this.lblNewLabel_6);
		
		this.lblReferencia = new JLabel("Referencia:");
		this.lblReferencia.setBounds(10, 86, 62, 14);
		contentPanel.add(this.lblReferencia);
		
		this.lblCostadoDeNova = new JLabel("Costado de Nova Center");
		this.lblCostadoDeNova.setBounds(77, 86, 150, 14);
		contentPanel.add(this.lblCostadoDeNova);
	}
}
