package gui_clientes;

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
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;

import clases.Cliente;
import gui_principal.Login;
import gui_mantenimiento_productos.MantenimientoProductos;
import gui_ventas.Ventas;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class NuevoCliente extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JLabel lblProducto;
	private JTextField txtNombre;
	private JLabel lblPrecio;
	private JTextField txtDireccion;
	private JLabel lblCantdad;
	private JTextField txtNroDocumento;
	private JLabel lblPrecioVenta;
	private JTextField txtCorreo;
	private JButton btnCrear;
	private JLabel lblUMedida;
	private JComboBox cbDocumento;
	private TextAutoCompleter ac;
	private TextAutoCompleter ac1;

	MantenimientoClientes cli;
	consultas model = new consultas();
	Ventas v;
	String usuario;
	private JTextField txtNuevoProducto;
	private JLabel lblPromocion;
	private JTextField txtTelefono;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblCamposObligatorios;

	public static void main(String[] args) {
		try {
			NuevoCliente dialog = new NuevoCliente(null, null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NuevoCliente(MantenimientoClientes temp2, Ventas temp3, String temp4) {
		setTitle("Crear cliente");
		cli = temp2;
		v = temp3;
		usuario = temp4;
		setAlwaysOnTop(true);
		setBounds(100, 100, 591, 408);
		getContentPane().setLayout(null);
		setResizable(false);
		addWindowListener(this);

		txtNroDocumento = new JTextField();
		txtNroDocumento.addKeyListener(this);
		txtNroDocumento.setForeground(SystemColor.windowBorder);
		txtNroDocumento.setHorizontalAlignment(SwingConstants.LEFT);
		txtNroDocumento.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNroDocumento.setColumns(10);
		txtNroDocumento.setBackground(SystemColor.controlHighlight);
		txtNroDocumento.setBounds(253, 158, 321, 25);
		getContentPane().add(txtNroDocumento);

		cbDocumento = new JComboBox();
		cbDocumento.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbDocumento.setModel(new DefaultComboBoxModel(new String[] {"DNI", "RUC"}));
		cbDocumento.setBounds(253, 120, 321, 25);
		getContentPane().add(cbDocumento);
		cbDocumento.setSelectedIndex(0);

		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(this);
		txtDireccion.setForeground(SystemColor.windowBorder);
		txtDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDireccion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtDireccion.setColumns(10);
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setBounds(253, 196, 321, 25);
		getContentPane().add(txtDireccion);

		txtCorreo = new JTextField();
		txtCorreo.addKeyListener(this);
		txtCorreo.setForeground(SystemColor.windowBorder);
		txtCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCorreo.setColumns(10);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setBounds(253, 232, 321, 25);
		getContentPane().add(txtCorreo);

		lblProducto = new JLabel("Nombre:");
		lblProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setForeground(SystemColor.desktop);
		lblProducto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblProducto.setBounds(10, 76, 178, 25);
		getContentPane().add(lblProducto);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setForeground(SystemColor.windowBorder);
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNombre.setColumns(10);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setBounds(158, 76, 416, 25);
		getContentPane().add(txtNombre);

		lblPrecio = new JLabel("Direcci\u00F3n: ");
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(10, 194, 235, 25);
		getContentPane().add(lblPrecio);

		lblCantdad = new JLabel("Nro. Documento:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(10, 156, 211, 25);
		getContentPane().add(lblCantdad);

		lblPrecioVenta = new JLabel("Correo:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(10, 232, 211, 25);
		getContentPane().add(lblPrecioVenta);

		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setForeground(SystemColor.menu);
		btnCrear.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(0, 332, 585, 47);
		getContentPane().add(btnCrear);

		lblUMedida = new JLabel("Documento:");
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(10, 120, 171, 25);
		getContentPane().add(lblUMedida);

		txtNuevoProducto = new JTextField();
		txtNuevoProducto.setText("NUEVO CLIENTE");
		txtNuevoProducto.setRequestFocusEnabled(false);
		txtNuevoProducto.setIgnoreRepaint(true);
		txtNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNuevoProducto.setForeground(Color.WHITE);
		txtNuevoProducto.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtNuevoProducto.setFocusable(false);
		txtNuevoProducto.setFocusTraversalKeysEnabled(false);
		txtNuevoProducto.setEditable(false);
		txtNuevoProducto.setColumns(10);
		txtNuevoProducto.setBackground(Color.DARK_GRAY);
		txtNuevoProducto.setBounds(0, 0, 585, 58);
		getContentPane().add(txtNuevoProducto);
		
		lblPromocion = new JLabel("Tel\u00E9fono:");
		lblPromocion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPromocion.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromocion.setForeground(SystemColor.desktop);
		lblPromocion.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPromocion.setBounds(10, 268, 211, 25);
		getContentPane().add(lblPromocion);
		
		txtTelefono = new JTextField();
		txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefono.setForeground(SystemColor.windowBorder);
		txtTelefono.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setBounds(253, 268, 321, 25);
		getContentPane().add(txtTelefono);
		
		label = new JLabel("*");
		label.setForeground(new Color(220, 20, 60));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(102, 85, 46, 14);
		getContentPane().add(label);
		
		label_1 = new JLabel("*");
		label_1.setForeground(new Color(220, 20, 60));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(184, 158, 46, 14);
		getContentPane().add(label_1);
		
		lblCamposObligatorios = new JLabel("* Campos obligatorios");
		lblCamposObligatorios.setForeground(new Color(220, 20, 60));
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCamposObligatorios.setBounds(10, 304, 252, 25);
		getContentPane().add(lblCamposObligatorios);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNombre, cbDocumento, txtNroDocumento, txtDireccion, txtCorreo, txtTelefono, btnCrear}));
		
		cargar();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
	}

	public void cargar() {
		
	}

	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		int rs = 0;
		try {
			if (txtNombre.getText().length() == 0 || txtNroDocumento.getText().length() == 0) {
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene los campos Nombre y Nro de Documento correctamente");
				this.setAlwaysOnTop(true);
			} else {
				this.setAlwaysOnTop(false);
				
				String nombre = txtNombre.getText();
				String documento = cbDocumento.getSelectedItem().toString();
				String nroDocumento = txtNroDocumento.getText();
				String direccion = txtDireccion.getText();
				String correo = txtCorreo.getText();
				String telefono = txtTelefono.getText();
				
				rs = model.crearCliente(nombre, documento, nroDocumento, direccion, correo, telefono);
				
				if (rs == 0) {
					if (cli != null) {
						cli.cargarDatos();
						cli.selecionarCliente(txtNroDocumento.getText());
						cli.ajustarAnchoColumnas();
						cli.setEnabled(true);
						this.dispose();
					}
					if (v != null) {
						consultas model = new consultas();
						ResultSet rs2 = model.cargarUltimoCliente();
						int id = -1;
						try {
							while (rs2.next())
								id = rs2.getInt("id");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "ERROR: " + e);
						}
						
						
						Cliente c = new Cliente(id, documento, nroDocumento, nombre, direccion, telefono, correo);
						v.anadirClienteCombpo(c);
						v.setVisible(true);
						v.setEnabled(true);
						this.dispose();
					}
				} else
					JOptionPane.showMessageDialog(null, "Ya existe cliente con este Documento");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear cliente: "+e);
		}
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	protected void keyTypedTxtProducto(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (txtNombre.getText().length() == 40 || c == '_') {
			arg0.consume();
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
		if (cli != null)
			cli.setEnabled(true);
		if (v != null)
			v.setEnabled(true);
	}
}
