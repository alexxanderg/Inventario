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
import java.awt.event.WindowEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AgregarStock extends JDialog implements ActionListener, WindowListener {
	private JLabel lblStockActual;
	private JTextField txtStockActual;
	private JLabel lblCantidadAIngresar;
	private JTextField txtCantidadAdicinal;
	private JButton btnGuardar;

	String cod;
	String cantActual;
	MantenimientoProductos mp;
	consultas model = new consultas();
	ResultSet rs;
	private JTextField txtAgregarStock;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarStock dialog = new AgregarStock(null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarStock(String temp, String temp2, MantenimientoProductos temp3) {
		addWindowListener(this);
		cod = temp;
		cantActual = temp2;
		mp = temp3;
		setResizable(false);
		setBounds(100, 100, 560, 265);
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
		btnGuardar.setBounds(0, 181, 554, 55);
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
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		try {
			float ci = Float.parseFloat(cantActual);
			float ca = Float.parseFloat(txtCantidadAdicinal.getText());
			float total = ci + ca;
			if(ca <= 0)
				JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a cero");
			else{
				model.ingresarStock(cod, total);
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
