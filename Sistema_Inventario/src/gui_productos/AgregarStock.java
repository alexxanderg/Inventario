package gui_productos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mysql.consultas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.WindowEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgregarStock extends JDialog implements ActionListener, WindowListener {
	private JLabel lblStockActual;
	private JTextField txtStockActual;
	private JLabel lblCantidadAIngresar;
	private JTextField txtCantidadAdicinal;
	private JButton btnGuardar;

	String usuario;
	int idProducto;
	float cantActual;
	float precioCoOld;
	float precioVeOld;
	Date fv;
	
	
	private JTextField txtAgregarStock;
	private JLabel lblPrecioCompra;
	private JLabel lblPrecioVenta;
	private JLabel lblFechaDeIngreso;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JDateChooser fecha_ingreso;
	
	MantenimientoProd mp = null;
	consultas model = new consultas();
	ResultSet rs;
	private JDateChooser fecha_vencimiento;
	private JLabel lblFechaDeVencimiento;
	private JLabel lblNewLabel;
	private JLabel lblEstaOpcinSolo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarStock dialog = new AgregarStock(0, 0, 0, 0, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AgregarStock(int idProducto, float cantActual, float precioCoOld, float precioVeOld, Date fv, String usuario, MantenimientoProd mp) {
		
		this.idProducto = idProducto;
		this.cantActual = cantActual;
		this.usuario = usuario;
		this.precioCoOld = precioCoOld;
		this.precioVeOld = precioVeOld;
		this.fv = fv;
		this.mp = mp;
		
		addWindowListener(this);
		setResizable(false);
		setBounds(100, 100, 560, 527);
		getContentPane().setLayout(null);
		
		lblStockActual = new JLabel("Stock actual:");
		lblStockActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStockActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockActual.setForeground(Color.BLACK);
		lblStockActual.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		lblStockActual.setBounds(40, 64, 211, 38);
		getContentPane().add(lblStockActual);
		
		txtStockActual = new JTextField();
		txtStockActual.setEditable(false);
		txtStockActual.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStockActual.setForeground(new Color(220, 20, 60));
		txtStockActual.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtStockActual.setColumns(10);
		txtStockActual.setBackground(SystemColor.controlHighlight);
		txtStockActual.setBounds(321, 79, 166, 25);
		getContentPane().add(txtStockActual);
		
		lblCantidadAIngresar = new JLabel("Cantidad a adicionar:");
		lblCantidadAIngresar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadAIngresar.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadAIngresar.setForeground(Color.BLACK);
		lblCantidadAIngresar.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		lblCantidadAIngresar.setBounds(40, 113, 271, 38);
		getContentPane().add(lblCantidadAIngresar);
		
		txtCantidadAdicinal = new JTextField();
		txtCantidadAdicinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtCantidadAdicinal(arg0);
			}
		});
		txtCantidadAdicinal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCantidadAdicinal.setForeground(new Color(220, 20, 60));
		txtCantidadAdicinal.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidadAdicinal.setColumns(10);
		txtCantidadAdicinal.setBackground(SystemColor.controlHighlight);
		txtCantidadAdicinal.setBounds(321, 126, 166, 25);
		getContentPane().add(txtCantidadAdicinal);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(SystemColor.menu);
		btnGuardar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setBounds(0, 433, 554, 55);
		getContentPane().add(btnGuardar);
		
		txtAgregarStock = new JTextField();
		txtAgregarStock.setText("AGREGAR STOCK");
		txtAgregarStock.setRequestFocusEnabled(false);
		txtAgregarStock.setIgnoreRepaint(true);
		txtAgregarStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtAgregarStock.setForeground(Color.WHITE);
		txtAgregarStock.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtAgregarStock.setFocusable(false);
		txtAgregarStock.setFocusTraversalKeysEnabled(false);
		txtAgregarStock.setEditable(false);
		txtAgregarStock.setColumns(10);
		txtAgregarStock.setBackground(Color.DARK_GRAY);
		txtAgregarStock.setBounds(0, 0, 554, 58);
		getContentPane().add(txtAgregarStock);
		
		this.lblPrecioCompra = new JLabel("Precio Compra:");
		this.lblPrecioCompra.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblPrecioCompra.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioCompra.setForeground(Color.BLACK);
		this.lblPrecioCompra.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		this.lblPrecioCompra.setBounds(40, 250, 271, 38);
		getContentPane().add(this.lblPrecioCompra);
		
		this.lblPrecioVenta = new JLabel("Precio Venta:");
		this.lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioVenta.setForeground(Color.BLACK);
		this.lblPrecioVenta.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		this.lblPrecioVenta.setBounds(40, 299, 271, 38);
		getContentPane().add(this.lblPrecioVenta);
		
		this.lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
		this.lblFechaDeIngreso.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblFechaDeIngreso.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblFechaDeIngreso.setForeground(Color.BLACK);
		this.lblFechaDeIngreso.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		this.lblFechaDeIngreso.setBounds(40, 162, 271, 38);
		getContentPane().add(this.lblFechaDeIngreso);
		
		this.txtPrecioCompra = new JTextField();
		this.txtPrecioCompra.setText("<dynamic>");
		this.txtPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtPrecioCompra.setForeground(SystemColor.windowBorder);
		this.txtPrecioCompra.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtPrecioCompra.setColumns(10);
		this.txtPrecioCompra.setBackground(SystemColor.controlHighlight);
		this.txtPrecioCompra.setBounds(321, 263, 166, 25);
		getContentPane().add(this.txtPrecioCompra);
		
		this.txtPrecioVenta = new JTextField();
		this.txtPrecioVenta.setText("<dynamic>");
		this.txtPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtPrecioVenta.setForeground(SystemColor.windowBorder);
		this.txtPrecioVenta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtPrecioVenta.setColumns(10);
		this.txtPrecioVenta.setBackground(SystemColor.controlHighlight);
		this.txtPrecioVenta.setBounds(321, 311, 166, 25);
		getContentPane().add(this.txtPrecioVenta);
		
		this.fecha_ingreso = new JDateChooser();
		this.fecha_ingreso.setBounds(321, 175, 166, 25);
		getContentPane().add(this.fecha_ingreso);
		
		fecha_vencimiento = new JDateChooser();
		fecha_vencimiento.setBounds(321, 361, 166, 25);
		getContentPane().add(fecha_vencimiento);
		
		lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento:");
		lblFechaDeVencimiento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaDeVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeVencimiento.setForeground(Color.BLACK);
		lblFechaDeVencimiento.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		lblFechaDeVencimiento.setBounds(40, 348, 271, 38);
		getContentPane().add(lblFechaDeVencimiento);
		
		lblNewLabel = new JLabel("Modifique los siguientes campos si desea actualizarlos.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(40, 224, 447, 28);
		getContentPane().add(lblNewLabel);
		
		lblEstaOpcinSolo = new JLabel("<html>Esta opci\u00F3n, solo se sumar\u00E1 a su stock, mas no dejar\u00E1 un registro. <br>Para hacerlo, debe hacerlo desde el m\u00F3dulo de compras.</html>");
		lblEstaOpcinSolo.setForeground(new Color(220, 20, 60));
		lblEstaOpcinSolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstaOpcinSolo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstaOpcinSolo.setBounds(40, 397, 447, 28);
		getContentPane().add(lblEstaOpcinSolo);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCantidadAdicinal, btnGuardar}));
		cargar();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
	}
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosingThis(arg0);
		}
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent arg0) {
	}
	public void windowIconified(WindowEvent arg0) {
	}
	public void windowOpened(WindowEvent arg0) {
	}
	protected void windowClosingThis(WindowEvent arg0) {
		//mp.setEnabled(true);
	}
	
	public void cargar(){
		this.setLocationRelativeTo(null);
		txtStockActual.setText(""+cantActual);
		txtPrecioCompra.setText(""+precioCoOld);
		txtPrecioVenta.setText(""+precioVeOld);
		fecha_vencimiento.setDate(fv);
		
		java.util.Date date = new Date();
		date.getTime();
		fecha_ingreso.setDate(date);
	}
	
	public void actualizar_ingresar(){
		try {
			float ci = cantActual;
			float ca = Float.parseFloat(txtCantidadAdicinal.getText());
			float total = ci + ca;
			if(ca <= 0)
				JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a cero");
			else{
				
				float precioCoNew = Float.parseFloat(txtPrecioCompra.getText());
				float precioVeNew = Float.parseFloat(txtPrecioVenta.getText());
				
				java.sql.Date fechaVencimiento = null;
				try { 
					Date datevencimiento = fecha_vencimiento.getDate();
					long d = datevencimiento.getTime();
					fechaVencimiento = new java.sql.Date(d);
				} catch (Exception ex) {
				}	
				
				//Cambio de utils a sql.Date para envio
				Date  date = fecha_ingreso.getDate();
				long d = date.getTime();
				Object date2 = new java.sql.Timestamp(d);
				
				//Consultas
				model.iniciar();
				model.ingresarStock(idProducto, total);
				model.registrarFechaIngreso(idProducto, ca, precioCoOld, precioVeOld, precioCoNew, precioVeNew, date2, usuario);
				model.modificarPC_PV(idProducto, precioCoNew, fechaVencimiento, precioVeNew);
				
				//mp.setEnabled(true);
				mp.cargar();
				mp.selecionarProducto(""+idProducto);
				mp.ajustarAnchoColumnas();
				//mp.limpiar();
				this.dispose();
				
				model.reset();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
		}
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		actualizar_ingresar();
		
	}
	protected void keyTypedTxtCantidadAdicinal(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)
				&& (c != (char) KeyEvent.VK_ENTER) && (c != '.')) {
			arg0.consume();
		}
		if (txtCantidadAdicinal.getText().length() == 10) {
			arg0.consume();
		}
		if (c == '.' && txtCantidadAdicinal.getText().contains(".")) {
			arg0.consume();
		}
	}
}
