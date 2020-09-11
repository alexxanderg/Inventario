package gui_compras;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DetalleCompra extends JFrame {

	private JPanel contentPane;

	
	int idUsuario=0;
	public NuevaCompra nuevCompra;
	String nomProducto="";
	
	private JTextField txtCPaquetes;
	private JLabel lblCantidadTotalPaquetes;
	private JLabel lblUniPorPaquete;
	private JTextField txtUxPaquete;
	private JLabel lblPrecioTotal;
	private JTextField txtPrecioTotal;
	private JLabel lblTotalUnidades;
	private JTextField txtTotUnidades;
	private JLabel lblPrecioPorPaquete;
	private JTextField txtPrePorPaquete;
	private JLabel lblPrecioPorUnidad;
	private JTextField txtPrePorUnidad;
	private JButton button;
	private JButton button_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalleCompra frame = new DetalleCompra(0,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DetalleCompra(int idUsuario,	NuevaCompra nuevCompra) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				windowClosingThis(e);
			}
		});
		this.idUsuario = idUsuario;
		this.nuevCompra = nuevCompra;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 435);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.txtCPaquetes = new JTextField();
		this.txtCPaquetes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				focusGainedTxtCPaquetes(arg0);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtCPaquetes(e);
			}
		});
		this.txtCPaquetes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCPaquetes(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtCPaquetes(e);
			}
		});
		this.txtCPaquetes.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtCPaquetes.setForeground(new Color(220, 20, 60));
		this.txtCPaquetes.setFont(new Font("Arial", Font.BOLD, 16));
		this.txtCPaquetes.setColumns(10);
		this.txtCPaquetes.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtCPaquetes.setBackground(new Color(245, 245, 245));
		this.txtCPaquetes.setBounds(302, 44, 150, 25);
		this.contentPane.add(this.txtCPaquetes);
		
		this.lblCantidadTotalPaquetes = new JLabel("Cantidad Paquetes");
		this.lblCantidadTotalPaquetes.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblCantidadTotalPaquetes.setForeground(new Color(220, 20, 60));
		this.lblCantidadTotalPaquetes.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblCantidadTotalPaquetes.setBounds(10, 44, 190, 25);
		this.contentPane.add(this.lblCantidadTotalPaquetes);
		
		this.lblUniPorPaquete = new JLabel("Unidades por Paquete");
		this.lblUniPorPaquete.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblUniPorPaquete.setForeground(Color.DARK_GRAY);
		this.lblUniPorPaquete.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblUniPorPaquete.setBounds(10, 80, 190, 25);
		this.contentPane.add(this.lblUniPorPaquete);
		
		this.txtUxPaquete = new JTextField();
		this.txtUxPaquete.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtUxPaquete(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtUxPaquete(e);
			}
		});
		this.txtUxPaquete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtUxPaquete(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtUxPaquete(e);
			}
		});
		this.txtUxPaquete.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtUxPaquete.setForeground(Color.DARK_GRAY);
		this.txtUxPaquete.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtUxPaquete.setColumns(10);
		this.txtUxPaquete.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtUxPaquete.setBackground(new Color(245, 245, 245));
		this.txtUxPaquete.setBounds(302, 80, 150, 25);
		this.contentPane.add(this.txtUxPaquete);
		
		this.lblPrecioTotal = new JLabel("Precio total:");
		this.lblPrecioTotal.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioTotal.setForeground(Color.DARK_GRAY);
		this.lblPrecioTotal.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblPrecioTotal.setBounds(10, 193, 190, 25);
		this.contentPane.add(this.lblPrecioTotal);
		
		this.txtPrecioTotal = new JTextField();
		this.txtPrecioTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrecioTotal(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPrecioTotal(e);
			}
		});
		this.txtPrecioTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrecioTotal(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPrecioTotal(e);
			}
		});
		this.txtPrecioTotal.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtPrecioTotal.setForeground(Color.DARK_GRAY);
		this.txtPrecioTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtPrecioTotal.setColumns(10);
		this.txtPrecioTotal.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtPrecioTotal.setBackground(new Color(245, 245, 245));
		this.txtPrecioTotal.setBounds(302, 193, 149, 25);
		this.contentPane.add(this.txtPrecioTotal);
		
		this.lblTotalUnidades = new JLabel("Total Unidades");
		this.lblTotalUnidades.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblTotalUnidades.setForeground(Color.DARK_GRAY);
		this.lblTotalUnidades.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblTotalUnidades.setBounds(10, 116, 190, 25);
		this.contentPane.add(this.lblTotalUnidades);
		
		this.txtTotUnidades = new JTextField();
		this.txtTotUnidades.setEditable(false);
		this.txtTotUnidades.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtTotUnidades(e);
			}
//			@Override
//			public void keyReleased(KeyEvent e) {
//				keyReleasedTxtTotUnidades(e);
//			}
		});
		this.txtTotUnidades.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtTotUnidades.setForeground(Color.DARK_GRAY);
		this.txtTotUnidades.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtTotUnidades.setColumns(10);
		this.txtTotUnidades.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtTotUnidades.setBackground(new Color(245, 245, 245));
		this.txtTotUnidades.setBounds(302, 116, 150, 25);
		this.contentPane.add(this.txtTotUnidades);
		
		this.lblPrecioPorPaquete = new JLabel("Precio por Paquete:");
		this.lblPrecioPorPaquete.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioPorPaquete.setForeground(Color.DARK_GRAY);
		this.lblPrecioPorPaquete.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblPrecioPorPaquete.setBounds(10, 229, 190, 25);
		this.contentPane.add(this.lblPrecioPorPaquete);
		
		this.txtPrePorPaquete = new JTextField();
		this.txtPrePorPaquete.setEditable(false);
		this.txtPrePorPaquete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrePorPaquete(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPrePorPaquete(e);
			}
		});
		this.txtPrePorPaquete.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtPrePorPaquete.setForeground(Color.DARK_GRAY);
		this.txtPrePorPaquete.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtPrePorPaquete.setColumns(10);
		this.txtPrePorPaquete.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtPrePorPaquete.setBackground(new Color(245, 245, 245));
		this.txtPrePorPaquete.setBounds(302, 229, 149, 25);
		this.contentPane.add(this.txtPrePorPaquete);
		
		this.lblPrecioPorUnidad = new JLabel("Precio por Unidad");
		this.lblPrecioPorUnidad.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioPorUnidad.setForeground(Color.DARK_GRAY);
		this.lblPrecioPorUnidad.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblPrecioPorUnidad.setBounds(10, 265, 190, 25);
		this.contentPane.add(this.lblPrecioPorUnidad);
		
		this.txtPrePorUnidad = new JTextField();
		this.txtPrePorUnidad.setEditable(false);
		this.txtPrePorUnidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrePorUnidad(e);
			}
		});
		this.txtPrePorUnidad.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtPrePorUnidad.setForeground(Color.DARK_GRAY);
		this.txtPrePorUnidad.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtPrePorUnidad.setColumns(10);
		this.txtPrePorUnidad.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		this.txtPrePorUnidad.setBackground(new Color(245, 245, 245));
		this.txtPrePorUnidad.setBounds(302, 265, 149, 25);
		this.contentPane.add(this.txtPrePorUnidad);
		
		this.button = new JButton("CREAR");
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton(arg0);
			}
		});
		this.button.setForeground(SystemColor.menu);
		this.button.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.button.setBackground(new Color(30, 144, 255));
		this.button.setBounds(244, 327, 208, 61);
		this.contentPane.add(this.button);
		
		this.button_1 = new JButton("Cancelar");
		this.button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton_1(arg0);
			}
		});
		this.button_1.setForeground(SystemColor.menu);
		this.button_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.button_1.setBackground(new Color(220, 20, 60));
		this.button_1.setBounds(10, 327, 208, 61);
		this.contentPane.add(this.button_1);
		
		this.label = new JLabel("*");
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		this.label.setForeground(Color.RED);
		this.label.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.label.setBounds(272, 44, 20, 25);
		this.contentPane.add(this.label);
		
		this.label_1 = new JLabel("*");
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setForeground(Color.RED);
		this.label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.label_1.setBounds(272, 80, 20, 25);
		this.contentPane.add(this.label_1);
		
		this.label_2 = new JLabel("*");
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setForeground(Color.RED);
		this.label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.label_2.setBounds(272, 193, 20, 25);
		this.contentPane.add(this.label_2);
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
	private void seleccionarTexto(FocusEvent e){
		Object o = e.getSource();
        if(o instanceof javax.swing.JTextField){
            javax.swing.JTextField txt = (javax.swing.JTextField) o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
	}
	private void soloNumeros(KeyEvent e, JTextField txt){			
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txt.getText().length() == 8)
			e.consume();
		if (c == '.' && txt.getText().contains("."))
			e.consume();
	}
	private void division(JTextField num1, JTextField num2, JTextField rspt){
		double a = Float.parseFloat(num1.getText());
		double b = Float.parseFloat(num2.getText());
		double c = (a/b);
		c = redondearDecimales(c, 2);
		rspt.setText(""+c);		
	}
	
	protected void keyTypedTxtCPaquetes(KeyEvent e) {
		soloNumeros(e, txtCPaquetes);		
	}
	protected void focusGainedTxtCPaquetes(FocusEvent arg0) {
		seleccionarTexto(arg0);
		if(txtCPaquetes.getText().equals("0"))
			txtCPaquetes.setText("");
	}
	protected void focusLostTxtCPaquetes(FocusEvent e) {
		if(txtCPaquetes.getText().equals(""))
			txtCPaquetes.setText("0");
	}
	protected void keyReleasedTxtCPaquetes(KeyEvent e) {
		try {
			if((txtCPaquetes.getText().equals("0")) || txtCPaquetes.getText().length()==0){
				txtCPaquetes.setText("");
			}else{
				double a = Float.parseFloat(txtCPaquetes.getText());
				double b = Float.parseFloat(txtUxPaquete.getText());
				double rpt = a*b;
				txtTotUnidades.setText(""+rpt);
				
				division(txtPrecioTotal, txtCPaquetes, txtPrePorPaquete);
			}
		} catch (NumberFormatException e1) {
			if (!(txtCPaquetes.getText().length()==0)) {
				txtUxPaquete.setText("0");
				txtPrecioTotal.setText("0");
			}
		}
	}
	
	protected void keyTypedTxtUxPaquete(KeyEvent e) {
		soloNumeros(e, txtUxPaquete);
	}
	protected void focusGainedTxtUxPaquete(FocusEvent e) {
		seleccionarTexto(e);
		if(txtUxPaquete.getText().equals("0"))
			txtUxPaquete.setText("");
	}
	protected void focusLostTxtUxPaquete(FocusEvent e) {
		if(txtUxPaquete.getText().equals(""))
			txtUxPaquete.setText("0");
	}
	protected void keyReleasedTxtUxPaquete(KeyEvent e) {
		try {
			if(txtUxPaquete.getText().equals("0")){
				txtTotUnidades.setText("");
			}else{
				double a = Float.parseFloat(txtCPaquetes.getText());
				double b = Float.parseFloat(txtUxPaquete.getText());
				double rpt = a*b;
				txtTotUnidades.setText(""+rpt);
				
				division(txtPrePorPaquete, txtUxPaquete, txtPrePorUnidad);
			}
		} catch (NumberFormatException e1) {
			if (txtUxPaquete.getText().length()==0) {
				txtTotUnidades.setText("0");
				txtPrePorUnidad.setText("0");				
			}
		}
	}
	
	protected void keyTypedTxtTotUnidades(KeyEvent e) {
		soloNumeros(e, txtTotUnidades);
	}
	
	
	protected void keyTypedTxtPrecioTotal(KeyEvent e) {
		soloNumeros(e, txtPrecioTotal);
	}
	protected void focusGainedTxtPrecioTotal(FocusEvent e) {
		seleccionarTexto(e);
		if(txtPrecioTotal.getText().equals("0"))
			txtPrecioTotal.setText("");
	}
	protected void focusLostTxtPrecioTotal(FocusEvent e) {
		if(txtPrecioTotal.getText().equals(""))
			txtPrecioTotal.setText("0");
	}
	protected void keyReleasedTxtPrecioTotal(KeyEvent e) {
		try {
			if (txtPrecioTotal.getText().equals("")) {
				txtPrePorPaquete.setText("");
			}else{
				division(txtPrecioTotal, txtCPaquetes, txtPrePorPaquete);
				division(txtPrePorPaquete, txtUxPaquete, txtPrePorUnidad);
			}
		} catch (Exception e1) {
			if (txtPrecioTotal.getText().length()==0) {
				txtPrePorUnidad.setText("0");
				txtPrePorPaquete.setText("0");
			}
		}
	}
	
	protected void keyTypedTxtPrePorPaquete(KeyEvent e) {
		soloNumeros(e, txtPrePorPaquete);
	}
	protected void keyReleasedTxtPrePorPaquete(KeyEvent e) {
		try {
			if (txtPrePorPaquete.getText().equals("")) {
				txtPrePorUnidad.setText("");
			}else{
				division(txtPrePorPaquete, txtUxPaquete, txtPrePorUnidad);
			}
		} catch (Exception e1) {
			if (txtPrePorPaquete.getText().length()==0) {
				txtPrePorUnidad.setText("0");
			}
		}
	}
	
	protected void keyTypedTxtPrePorUnidad(KeyEvent e) {
		soloNumeros(e, txtPrePorUnidad);
	}

	protected void windowClosingThis(WindowEvent e) {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //CERRAR VENTANA
		this.dispose();
	}	
	
	protected void actionPerformedButton(ActionEvent arg0) {
		try {
			nomProducto=nuevCompra.txtBuscarProducto.getText();
			double cantidad=Double.parseDouble(txtTotUnidades.getText());
			double precioUnidad = Double.parseDouble(txtPrePorUnidad.getText());
			double precioSubTot=Double.parseDouble(txtPrecioTotal.getText());
			
			//JOptionPane.showMessageDialog(null, nomProducto + " "+cantidad + " "+precioUnidad+" "+precioSubTot);
			
			nuevCompra.dtm.addRow(new Object[]{cantidad,nomProducto,precioUnidad,precioSubTot});
			nuevCompra.sumarTotal();
			nuevCompra.txtBuscarProducto.setText(null);
			this.dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionPerformedButton_1(ActionEvent arg0) {
		this.dispose();
	}
}
