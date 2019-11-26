package gui_mantenimiento_productos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui_mantenimiento_productos.MantenimientoProductos;
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
	String cod;
	String cantActual;
	String precioCo;
	String precioVe;
	MantenimientoProductos mp;
	consultas model = new consultas();
	ResultSet rs;
	private JTextField txtAgregarStock;
	private JLabel lblPrecioCompra;
	private JLabel lblPrecioVenta;
	private JLabel lblFechaDeIngreso;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JDateChooser fecha_ingreso;
	private JCheckBox chckbxPC;
	private JCheckBox chckbxPV;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarStock dialog = new AgregarStock(null, null,null,null, null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AgregarStock(String temp, String temp2, String temp5, String temp6, MantenimientoProductos temp3, String temp4) {
		addWindowListener(this);
		cod = temp;
		cantActual = temp2;
		mp = temp3;
		usuario = temp4;
		precioCo = temp5;
		precioVe = temp6;
		setResizable(false);
		setBounds(100, 100, 560, 481);
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
		txtStockActual.setForeground(SystemColor.windowBorder);
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
		txtCantidadAdicinal.setForeground(SystemColor.windowBorder);
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
		btnGuardar.setBounds(0, 397, 554, 55);
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
		this.lblPrecioCompra.setBounds(40, 162, 271, 38);
		getContentPane().add(this.lblPrecioCompra);
		
		this.lblPrecioVenta = new JLabel("Precio Venta:");
		this.lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioVenta.setForeground(Color.BLACK);
		this.lblPrecioVenta.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		this.lblPrecioVenta.setBounds(40, 255, 271, 38);
		getContentPane().add(this.lblPrecioVenta);
		
		this.lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
		this.lblFechaDeIngreso.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblFechaDeIngreso.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblFechaDeIngreso.setForeground(Color.BLACK);
		this.lblFechaDeIngreso.setFont(new Font("EngraversGothic BT", Font.PLAIN, 25));
		this.lblFechaDeIngreso.setBounds(40, 348, 271, 38);
		getContentPane().add(this.lblFechaDeIngreso);
		
		this.txtPrecioCompra = new JTextField();
		this.txtPrecioCompra.setText("<dynamic>");
		this.txtPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtPrecioCompra.setForeground(SystemColor.windowBorder);
		this.txtPrecioCompra.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtPrecioCompra.setEditable(false);
		this.txtPrecioCompra.setColumns(10);
		this.txtPrecioCompra.setBackground(SystemColor.controlHighlight);
		this.txtPrecioCompra.setBounds(321, 175, 166, 25);
		getContentPane().add(this.txtPrecioCompra);
		
		this.txtPrecioVenta = new JTextField();
		this.txtPrecioVenta.setText("<dynamic>");
		this.txtPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtPrecioVenta.setForeground(SystemColor.windowBorder);
		this.txtPrecioVenta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtPrecioVenta.setEditable(false);
		this.txtPrecioVenta.setColumns(10);
		this.txtPrecioVenta.setBackground(SystemColor.controlHighlight);
		this.txtPrecioVenta.setBounds(321, 267, 166, 25);
		getContentPane().add(this.txtPrecioVenta);
		
		this.fecha_ingreso = new JDateChooser();
		this.fecha_ingreso.setBounds(321, 366, 166, 20);
		getContentPane().add(this.fecha_ingreso);
		
		this.chckbxPC = new JCheckBox("<html><left>Marque la casilla si desea modificar su precio de compra.</html>");
		this.chckbxPC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedChckbxPC(arg0);
			}
		});
		this.chckbxPC.setVerticalAlignment(SwingConstants.TOP);
		this.chckbxPC.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.chckbxPC.setForeground(Color.RED);
		this.chckbxPC.setBounds(40, 198, 271, 50);
		getContentPane().add(this.chckbxPC);
		
		this.chckbxPV = new JCheckBox("<html><left>marque esta casilla si desea modificar su precio de venta.</left></html");
		this.chckbxPV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedChckbxPV(e);
			}
		});
		this.chckbxPV.setVerticalAlignment(SwingConstants.TOP);
		this.chckbxPV.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.chckbxPV.setForeground(Color.RED);
		this.chckbxPV.setBounds(40, 300, 271, 55);
		getContentPane().add(this.chckbxPV);
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
		mp.setEnabled(true);
	}
	
	public void cargar(){
		this.setLocationRelativeTo(null);
		txtStockActual.setText(cantActual);
		txtPrecioCompra.setText(precioCo);
		txtPrecioVenta.setText(precioVe);
		
		java.util.Date date = new Date();
		date.getTime();
		fecha_ingreso.setDate(date);
	}
	
	public void actualizar_ingresar(){
		try {
			float ci = Float.parseFloat(cantActual);
			float ca = Float.parseFloat(txtCantidadAdicinal.getText());
			float total = ci + ca;
			if(ca <= 0)
				JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a cero");
			else{
				float pc = Float.parseFloat(txtPrecioCompra.getText());
				float pv = Float.parseFloat(txtPrecioVenta.getText());
				
				//Cambio de utils a sql.Date para envio
				Date  date = fecha_ingreso.getDate();
				long d = date.getTime();
				Object date2 = new java.sql.Timestamp(d);
				
				//Consultas
				model.ingresarStock(cod, total);
				model.registrarFechaIngreso(cod, ca, pc, pv, date2, usuario);
				model.modificarPC_PV(cod, pc, pv);
				
				mp.setEnabled(true);
				mp.cargarDatos();
				mp.selecionarProducto(cod);
				mp.ajustarAnchoColumnas();
				mp.limpiar();
				this.dispose();
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
	protected void mouseClickedChckbxPC(MouseEvent arg0) {
		if (chckbxPC.isSelected()) {
			txtPrecioCompra.setEditable(true);
		}else {
			txtPrecioCompra.setEditable(false);
		}
	}
	protected void mouseClickedChckbxPV(MouseEvent e) {
		if (chckbxPV.isSelected()) {
			txtPrecioVenta.setEditable(true);
		}else {
			txtPrecioVenta.setEditable(false);
		}
	}
}
