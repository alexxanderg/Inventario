package gui_clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import gui_principal.EleccionVentanas;
import gui_principal.Login;
import gui_mantenimiento_usuarios.MantenimientoUsuarios;
import gui_mantenimiento_productos.MantenimientoProductos;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ModificarCliente extends JDialog implements ActionListener, KeyListener, WindowListener{
	private JLabel lblCdigo;
	private JTextField txtId;
	private JLabel lblProducto;
	private JTextField txtNombre;
	private JLabel lblPrecio;
	private JTextField txtDireccion;
	private JLabel lblCantdad;
	private JTextField txtNroDoc;
	private JLabel lblPrecioVenta;
	private JTextField txtCorreo;
	private JButton btnModificar;
	private JLabel lblUMedida;
	private JComboBox cbDoc;
	
	consultas model = new consultas();
	ResultSet rs;
	String id;
	String nombre;
	String documento;
	String nroDocumento;
	String direccion;
	String correo;
	String telefono;
	MantenimientoClientes mc;
	
	EleccionVentanas el;
	private JTextField txtModificarCliente;
	private JTextField txtTelefono;
	private JLabel lblTelfono;
	
	public static void main(String[] args) {
		try {
			ModificarCliente dialog = new ModificarCliente(null, null, null, null,  null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModificarCliente(String temp, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6, MantenimientoClientes temp7) {
		id = temp;
		nombre = temp1;
		documento = temp2;
		nroDocumento = temp3;
		direccion = temp4;
		correo = temp5;
		telefono = temp6;
		mc = temp7;
		
		addWindowListener(this);
		setBounds(100, 100, 588, 467);
		getContentPane().setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		lblCdigo = new JLabel("ID:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setForeground(SystemColor.desktop);
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigo.setBounds(10, 80, 138, 25);
		getContentPane().add(lblCdigo);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.addKeyListener(this);
		txtId.setForeground(SystemColor.windowBorder);
		txtId.setHorizontalAlignment(SwingConstants.LEFT);
		txtId.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtId.setColumns(10);
		txtId.setBackground(SystemColor.controlHighlight);
		txtId.setBounds(158, 82, 403, 25);
		getContentPane().add(txtId);
		
		lblProducto = new JLabel("Nombre:");
		lblProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProducto.setForeground(SystemColor.desktop);
		lblProducto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblProducto.setBounds(10, 125, 148, 23);
		getContentPane().add(lblProducto);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setForeground(SystemColor.windowBorder);
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNombre.setColumns(10);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setBounds(158, 125, 403, 25);
		getContentPane().add(txtNombre);
		
		lblPrecio = new JLabel("Direcci\u00F3n:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(12, 245, 243, 25);
		getContentPane().add(lblPrecio);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(this);
		txtDireccion.setForeground(SystemColor.windowBorder);
		txtDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDireccion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setBounds(255, 247, 306, 25);
		getContentPane().add(txtDireccion);
		
		lblCantdad = new JLabel("Nro. Documento:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(12, 209, 211, 23);
		getContentPane().add(lblCantdad);
		
		txtNroDoc = new JTextField();
		txtNroDoc.addKeyListener(this);
		txtNroDoc.setForeground(SystemColor.windowBorder);
		txtNroDoc.setHorizontalAlignment(SwingConstants.LEFT);
		txtNroDoc.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNroDoc.setColumns(10);
		txtNroDoc.setBackground(SystemColor.controlHighlight);
		txtNroDoc.setBounds(257, 209, 304, 25);
		getContentPane().add(txtNroDoc);
		
		lblPrecioVenta = new JLabel("Correo:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(10, 280, 253, 25);
		getContentPane().add(lblPrecioVenta);
		
		txtCorreo = new JTextField();
		txtCorreo.addKeyListener(this);
		txtCorreo.setForeground(SystemColor.windowBorder);
		txtCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCorreo.setColumns(10);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setBounds(255, 282, 306, 25);
		getContentPane().add(txtCorreo);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(SystemColor.menu);
		btnModificar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(0, 385, 585, 55);
		getContentPane().add(btnModificar);
		
		lblUMedida = new JLabel("Documento:");
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(12, 171, 171, 25);
		getContentPane().add(lblUMedida);
		
		cbDoc = new JComboBox();
		cbDoc.setModel(new DefaultComboBoxModel(new String[] {"DNI", "RUC"}));
		cbDoc.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbDoc.setBounds(255, 173, 306, 25);
		getContentPane().add(cbDoc);
		
		txtModificarCliente = new JTextField();
		txtModificarCliente.setText("MODIFICAR CLIENTE");
		txtModificarCliente.setRequestFocusEnabled(false);
		txtModificarCliente.setIgnoreRepaint(true);
		txtModificarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtModificarCliente.setForeground(Color.WHITE);
		txtModificarCliente.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtModificarCliente.setFocusable(false);
		txtModificarCliente.setFocusTraversalKeysEnabled(false);
		txtModificarCliente.setEditable(false);
		txtModificarCliente.setColumns(10);
		txtModificarCliente.setBackground(Color.DARK_GRAY);
		txtModificarCliente.setBounds(0, 0, 585, 58);
		getContentPane().add(txtModificarCliente);
		
		txtTelefono = new JTextField();
		txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefono.setForeground(SystemColor.windowBorder);
		txtTelefono.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setBounds(255, 318, 306, 25);
		getContentPane().add(txtTelefono);
		
		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTelfono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelfono.setForeground(SystemColor.desktop);
		lblTelfono.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblTelfono.setBounds(12, 318, 211, 25);
		getContentPane().add(lblTelfono);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtId, txtNombre, cbDoc, txtNroDoc, txtDireccion, txtCorreo, txtTelefono, btnModificar}));
		cargarDatos();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnCrear(arg0);
		}
	}
	
	public void cargarDatos(){
		this.setLocationRelativeTo(null);
		this.isAlwaysOnTop();
		txtId.setText(id);
		txtNombre.setText(nombre);
		cbDoc.setSelectedItem(documento);
		txtNroDoc.setText(nroDocumento);
		txtDireccion.setText(direccion);
		txtTelefono.setText(telefono);
		txtCorreo.setText(correo);
		
	}
	
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		try {
			if(txtNombre.getText().length() == 0 || txtNroDoc.getText().length() == 0){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene los campos Nombre y Nro de Documento correctamente");
				this.setAlwaysOnTop(true);
			}
			else{
				this.setAlwaysOnTop(false);
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String documento = cbDoc.getSelectedItem().toString();
				String nroDocumento = txtNroDoc.getText();
				String direccion = txtDireccion.getText();
				String correo = txtCorreo.getText();
				String telefono = txtTelefono.getText();
				
				model.modificarCliente(id, nombre, documento, nroDocumento, direccion, correo, telefono);
				mc.cargarDatos();
				mc.selecionarCliente(nroDocumento);
				mc.ajustarAnchoColumnas();
				mc.limpiar();
				mc.setEnabled(true);
				dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
		}
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		
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
		mc.setEnabled(true);
	}
}






















