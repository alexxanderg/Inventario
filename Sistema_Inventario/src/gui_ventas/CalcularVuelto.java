package gui_ventas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CalcularVuelto extends JDialog {
	private JLabel lblMtodoDePago;
	private JTextField txtPaga;
	private JLabel lblElVueltoDe;
	private JTextField txtVuelto;
	private JButton btnCerrar;
	private JButton btnUno;
	private JButton btnDos;
	private JButton btnTres;
	private JButton btnCuatro;
	private JButton btnCinco;
	private JButton btnSeis;
	private JButton btnSiete;
	private JButton btnOcho;
	private JButton btnNueve;
	private JButton btnPunto;
	private JButton btnCero;
	String total;
	private JLabel lblS_1;
	private JLabel lblTotal;
	private JButton btnBorrar;
	private JTextField txtAgregarModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalcularVuelto dialog = new CalcularVuelto(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CalcularVuelto(String total) {
		this.total = total;
		setBounds(100, 100, 448, 353);
		getContentPane().setLayout(null);
		
		lblMtodoDePago = new JLabel("Paga con:");
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago.setBounds(20, 95, 176, 23);
		getContentPane().add(lblMtodoDePago);
		
		txtPaga = new JTextField();
		txtPaga.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				focusGainedTxtPaga(e);
			}
		});
		txtPaga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPaga(e);
			}
		});
		txtPaga.setText("0");
		txtPaga.setHorizontalAlignment(SwingConstants.CENTER);
		txtPaga.setForeground(SystemColor.windowBorder);
		txtPaga.setFont(new Font("Arial", Font.ITALIC, 18));
		txtPaga.setColumns(10);
		txtPaga.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPaga.setBackground(new Color(245, 245, 245));
		txtPaga.setBounds(95, 123, 101, 34);
		getContentPane().add(txtPaga);
		
		lblElVueltoDe = new JLabel("SU VUELTO ES:");
		lblElVueltoDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblElVueltoDe.setForeground(new Color(30, 144, 255));
		lblElVueltoDe.setFont(new Font("Candara", Font.BOLD, 20));
		lblElVueltoDe.setBounds(20, 168, 176, 34);
		getContentPane().add(lblElVueltoDe);
		
		txtVuelto = new JTextField();
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(new Color(30, 144, 255));
		txtVuelto.setFont(new Font("Arial", Font.ITALIC, 20));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtVuelto.setBackground(new Color(245, 245, 245));
		txtVuelto.setBounds(95, 199, 101, 37);
		getContentPane().add(txtVuelto);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCerrar(e);
			}
		});
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCerrar.setBackground(new Color(30, 144, 255));
		btnCerrar.setBounds(123, 257, 192, 35);
		getContentPane().add(btnCerrar);
		
		btnUno = new JButton("1");
		btnUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnUno(e);
			}
		});
		btnUno.setForeground(Color.WHITE);
		btnUno.setFont(new Font("Arial", Font.BOLD, 16));
		btnUno.setBackground(new Color(220, 20, 60));
		btnUno.setBounds(218, 35, 55, 43);
		getContentPane().add(btnUno);
		
		btnDos = new JButton("2");
		btnDos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnDos(e);
			}
		});
		btnDos.setForeground(Color.WHITE);
		btnDos.setFont(new Font("Arial", Font.BOLD, 16));
		btnDos.setBackground(new Color(220, 20, 60));
		btnDos.setBounds(287, 35, 55, 43);
		getContentPane().add(btnDos);
		
		btnTres = new JButton("3");
		btnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnTres(e);
			}
		});
		btnTres.setForeground(Color.WHITE);
		btnTres.setFont(new Font("Arial", Font.BOLD, 16));
		btnTres.setBackground(new Color(220, 20, 60));
		btnTres.setBounds(359, 35, 55, 43);
		getContentPane().add(btnTres);
		
		btnCuatro = new JButton("4");
		btnCuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCuatro(e);
			}
		});
		btnCuatro.setForeground(Color.WHITE);
		btnCuatro.setFont(new Font("Arial", Font.BOLD, 16));
		btnCuatro.setBackground(new Color(220, 20, 60));
		btnCuatro.setBounds(218, 89, 55, 43);
		getContentPane().add(btnCuatro);
		
		btnCinco = new JButton("5");
		btnCinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCinco(e);
			}
		});
		btnCinco.setForeground(Color.WHITE);
		btnCinco.setFont(new Font("Arial", Font.BOLD, 16));
		btnCinco.setBackground(new Color(220, 20, 60));
		btnCinco.setBounds(287, 89, 55, 43);
		getContentPane().add(btnCinco);
		
		btnSeis = new JButton("6");
		btnSeis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnSeis(e);
			}
		});
		btnSeis.setForeground(Color.WHITE);
		btnSeis.setFont(new Font("Arial", Font.BOLD, 16));
		btnSeis.setBackground(new Color(220, 20, 60));
		btnSeis.setBounds(359, 89, 55, 43);
		getContentPane().add(btnSeis);
		
		btnSiete = new JButton("7");
		btnSiete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnSiete(e);
			}
		});
		btnSiete.setForeground(Color.WHITE);
		btnSiete.setFont(new Font("Arial", Font.BOLD, 16));
		btnSiete.setBackground(new Color(220, 20, 60));
		btnSiete.setBounds(218, 143, 55, 43);
		getContentPane().add(btnSiete);
		
		btnOcho = new JButton("8");
		btnOcho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnOcho(e);
			}
		});
		btnOcho.setForeground(Color.WHITE);
		btnOcho.setFont(new Font("Arial", Font.BOLD, 16));
		btnOcho.setBackground(new Color(220, 20, 60));
		btnOcho.setBounds(287, 143, 55, 43);
		getContentPane().add(btnOcho);
		
		btnNueve = new JButton("9");
		btnNueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNueve(e);
			}
		});
		btnNueve.setForeground(Color.WHITE);
		btnNueve.setFont(new Font("Arial", Font.BOLD, 16));
		btnNueve.setBackground(new Color(220, 20, 60));
		btnNueve.setBounds(359, 143, 55, 43);
		getContentPane().add(btnNueve);
		
		btnPunto = new JButton(".");
		btnPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnPunto(e);
			}
		});
		btnPunto.setForeground(Color.WHITE);
		btnPunto.setFont(new Font("Arial", Font.BOLD, 16));
		btnPunto.setBackground(new Color(220, 20, 60));
		btnPunto.setBounds(218, 193, 55, 43);
		getContentPane().add(btnPunto);
		
		btnCero = new JButton("0");
		btnCero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCero(e);
			}
		});
		btnCero.setForeground(Color.WHITE);
		btnCero.setFont(new Font("Arial", Font.BOLD, 16));
		btnCero.setBackground(new Color(220, 20, 60));
		btnCero.setBounds(287, 193, 55, 43);
		getContentPane().add(btnCero);
		
		lblS_1 = new JLabel("TOTAL S/");
		lblS_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_1.setForeground(new Color(220, 20, 60));
		lblS_1.setFont(new Font("Candara", Font.BOLD, 23));
		lblS_1.setBounds(20, 20, 108, 34);
		getContentPane().add(lblS_1);
		
		lblTotal = new JLabel(total);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(220, 20, 60));
		lblTotal.setFont(new Font("Candara", Font.BOLD, 23));
		lblTotal.setBounds(20, 50, 170, 39);
		getContentPane().add(lblTotal);
		
		btnBorrar = new JButton("<=");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBorrar(e);
			}
		});
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 16));
		btnBorrar.setBackground(new Color(220, 20, 60));
		btnBorrar.setBounds(359, 194, 55, 43);
		getContentPane().add(btnBorrar);
		
		txtAgregarModificar = new JTextField();
		txtAgregarModificar.setVisible(false);
		txtAgregarModificar.setText("0");
		txtAgregarModificar.setColumns(10);
		txtAgregarModificar.setBounds(10, 25, 42, 20);
		getContentPane().add(txtAgregarModificar);
		
		this.setLocationRelativeTo(null);
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		this.dispose();
	}
	
	protected void keyReleasedTxtPaga(KeyEvent e) {
		try {
			double pagacon1 = Float.parseFloat(txtPaga.getText());
			double sumaPagos = pagacon1;
				sumaPagos = redondearDecimales(sumaPagos, 2);
			double tot = Float.parseFloat(lblTotal.getText());
			double vuelto = redondearDecimales(sumaPagos - tot, 2);
			
			if (vuelto < 0){
				txtVuelto.setText("0.00");
			}
			else{
				txtVuelto.setText("" + vuelto + "0");
			}
		} catch (Exception e2) {
			txtVuelto.setText("0.00");
		}
	}
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}
	
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		txtPaga.setText("");
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR
		keyReleasedTxtPaga(null);	
	}
	protected void actionPerformedBtnUno(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
		if(txtAgregarModificar.getText().equals("0"))
			txtPaga.setText("1");
		else
			txtPaga.setText(paga+"1");
		
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR
		keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnDos(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("2");
			else
				txtPaga.setText(paga+"2");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnTres(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("3");
			else
				txtPaga.setText(paga+"3");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnCuatro(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("4");
			else
				txtPaga.setText(paga+"4");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnCinco(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("5");
			else
				txtPaga.setText(paga+"5");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnSeis(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("6");
			else
				txtPaga.setText(paga+"6");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnSiete(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("7");
			else
				txtPaga.setText(paga+"7");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnOcho(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("8");
			else
				txtPaga.setText(paga+"8");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnNueve(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("9");
			else
				txtPaga.setText(paga+"9");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void actionPerformedBtnCero(ActionEvent e) {
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText("0");
			else
				txtPaga.setText(paga+"0");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR
			keyReleasedTxtPaga(null);	
	}
	protected void actionPerformedBtnPunto(ActionEvent e) {	
		 String paga = txtPaga.getText().toString();
		 
			if(txtAgregarModificar.getText().equals("0"))
				txtPaga.setText(".");
			else
				txtPaga.setText(paga+".");
			
			txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
			keyReleasedTxtPaga(null);
	}
	protected void focusGainedTxtPaga(FocusEvent e) {
		 txtAgregarModificar.setText("0");
		 
		 Object o = e.getSource();
		    if (o instanceof JTextField) {
		      JTextField txt = (JTextField)o;
		      txt.setSelectionStart(0);
		      txt.setSelectionEnd(txt.getText().length());
		    } 
	}
}
