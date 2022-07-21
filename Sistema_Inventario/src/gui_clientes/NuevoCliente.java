package gui_clientes;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import clases.Cliente;
import gui_ventas.Ventas2;
import mysql.consultas;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NuevoCliente extends JFrame {
	private JLabel lblNombre;
	private JButton btnCrear;
	private JTextField txtAgregarUsuario;
	private JButton btnCancelar;
	private JComboBox cbTipoDoc;
	private JLabel lblNroDocumento;
	private JTextField txtNroDoc;
	private JLabel lblNombre_1;
	private JTextField txtNombre;
	private JLabel lblDireccin;
	private JTextField txtDireccion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JLabel lblCorreo;
	private JLabel label_1;
	
	ResultSet rs;
	consultas consulta = new consultas();
	MantenimientoClientes mantenimientoCliente = null;
	Ventas2 ventas = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoCliente frame = new NuevoCliente(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NuevoCliente(MantenimientoClientes mantenimientoCliente, Ventas2 ventas) {
		this.mantenimientoCliente = mantenimientoCliente;
		this.ventas = ventas; 
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		setResizable(false);
		setBounds(100, 100, 456, 419);
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Tipo Documento");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre.setBounds(10, 74, 175, 25);
		getContentPane().add(lblNombre);
		
		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrear(arg0);
			}
		});
		btnCrear.setForeground(SystemColor.menu);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(231, 332, 200, 38);
		getContentPane().add(btnCrear);
		
		txtAgregarUsuario = new JTextField();
		txtAgregarUsuario.setText("CREAR CLIENTE");
		txtAgregarUsuario.setRequestFocusEnabled(false);
		txtAgregarUsuario.setIgnoreRepaint(true);
		txtAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtAgregarUsuario.setForeground(Color.WHITE);
		txtAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtAgregarUsuario.setFocusable(false);
		txtAgregarUsuario.setFocusTraversalKeysEnabled(false);
		txtAgregarUsuario.setEditable(false);
		txtAgregarUsuario.setColumns(10);
		txtAgregarUsuario.setBackground(Color.DARK_GRAY);
		txtAgregarUsuario.setBounds(0, 0, 439, 50);
		getContentPane().add(txtAgregarUsuario);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCancelar(arg0);
			}
		});
		btnCancelar.setForeground(SystemColor.menu);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setBackground(new Color(220, 20, 60));
		btnCancelar.setBounds(10, 332, 200, 38);
		getContentPane().add(btnCancelar);
		
		cbTipoDoc = new JComboBox();
		this.cbTipoDoc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedCbTipoDoc(arg0);
			}
		});
		cbTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"SIN DOCUMENTO", "DNI", "RUC", "CE", "PASAPORTE"}));
		cbTipoDoc.setSelectedIndex(0);
		cbTipoDoc.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipoDoc.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbTipoDoc.setBackground(new Color(245, 245, 245));
		cbTipoDoc.setBounds(10, 99, 200, 25);
		getContentPane().add(cbTipoDoc);
		
		lblNroDocumento = new JLabel("Nro. Documento");
		lblNroDocumento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNroDocumento.setForeground(Color.DARK_GRAY);
		lblNroDocumento.setFont(new Font("Candara", Font.BOLD, 20));
		lblNroDocumento.setBounds(231, 73, 175, 25);
		getContentPane().add(lblNroDocumento);
		
		txtNroDoc = new JTextField();
		txtNroDoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNroDoc(e);
			}
		});
		txtNroDoc.setHorizontalAlignment(SwingConstants.LEFT);
		txtNroDoc.setForeground(Color.DARK_GRAY);
		txtNroDoc.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNroDoc.setColumns(10);
		txtNroDoc.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNroDoc.setBackground(Color.WHITE);
		txtNroDoc.setBounds(231, 99, 200, 25);
		getContentPane().add(txtNroDoc);
		
		lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre_1.setForeground(Color.DARK_GRAY);
		lblNombre_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre_1.setBounds(10, 135, 175, 25);
		getContentPane().add(lblNombre_1);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setForeground(Color.DARK_GRAY);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(10, 161, 421, 25);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombre(e);
			}
		});
		getContentPane().add(txtNombre);
		
		lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDireccin.setForeground(Color.DARK_GRAY);
		lblDireccin.setFont(new Font("Candara", Font.BOLD, 20));
		lblDireccin.setBounds(10, 197, 175, 25);
		getContentPane().add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtDireccion(e);
			}
		});
		txtDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDireccion.setForeground(Color.DARK_GRAY);
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtDireccion.setBackground(Color.WHITE);
		txtDireccion.setBounds(10, 223, 421, 25);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtDireccion(e);
			}
		});
		getContentPane().add(txtDireccion);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTelefono.setForeground(Color.DARK_GRAY);
		lblTelefono.setFont(new Font("Candara", Font.BOLD, 20));
		lblTelefono.setBounds(10, 259, 175, 25);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtTelefono(e);
			}
		});
		txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefono.setForeground(Color.DARK_GRAY);
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBounds(10, 285, 200, 25);
		getContentPane().add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCorreo(e);
			}
		});
		txtCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCorreo.setForeground(Color.DARK_GRAY);
		txtCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtCorreo.setBackground(Color.WHITE);
		txtCorreo.setBounds(231, 285, 200, 25);
		getContentPane().add(txtCorreo);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCorreo.setForeground(Color.DARK_GRAY);
		lblCorreo.setFont(new Font("Candara", Font.BOLD, 20));
		lblCorreo.setBounds(231, 259, 175, 25);
		getContentPane().add(lblCorreo);
		
		label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(87, 135, 20, 25);
		getContentPane().add(label_1);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{this.cbTipoDoc, this.txtNroDoc, this.txtNombre, this.txtDireccion, this.txtTelefono, this.txtCorreo, this.btnCrear, this.btnCancelar}));
		
		cargar();
	}
	
	private void cargar(){
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
	}
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		try {
			consulta.iniciar();
			
				String tipodoc = "";	tipodoc = cbTipoDoc.getSelectedItem().toString();
				String nrodoc = "";	nrodoc = txtNroDoc.getText();
				String nombre = "";	nombre = txtNombre.getText();
				String direccion = "";	direccion = txtDireccion.getText();
				String telefono = "";	telefono = txtTelefono.getText();
				String correo = "";	correo = txtCorreo.getText();
				rs = consulta.crearCliente(nombre, tipodoc, nrodoc, direccion, correo, telefono);
				int idcli = -1;
				try {
					ResultSet rs = consulta.cargarUltimoCliente();
					rs.next();
					idcli = rs.getInt("idcliente");
				} catch (SQLException e) {
				}
				//VERIFICAR QUIEN INVOCO A LA VENTANA
				if(mantenimientoCliente!=null){
					mantenimientoCliente.cargar();
					mantenimientoCliente.selecionarCliente("" + idcli);
				}
				else{					
					ventas.agregarCliente(idcli);
				}


				consulta.reset();
				dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear cliente: " + e);
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
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
		mantenimientoCliente.setEnabled(true);
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}

	protected void keyTypedTxtNroDoc(KeyEvent e) {
		if (txtNroDoc.getText().length() == 11)
			e.consume();
	}
	protected void keyTypedTxtNombre(KeyEvent e) {
		if (txtNombre.getText().length() == 150)
			e.consume();
	}
	protected void keyTypedTxtDireccion(KeyEvent e) {
		if (txtDireccion.getText().length() == 150)
			e.consume();
	}
	protected void keyTypedTxtTelefono(KeyEvent e) {
		if (txtTelefono.getText().length() == 15)
			e.consume();
	}
	protected void keyTypedTxtCorreo(KeyEvent e) {
		if (txtCorreo.getText().length() == 50)
			e.consume();
	}
	protected void itemStateChangedCbTipoDoc(ItemEvent arg0) {
		if(cbTipoDoc.getSelectedIndex() == 4){
			txtNroDoc.setText("99999999");
			txtNroDoc.setEditable(false);
			txtNombre.setText(".Cliente Varios");
		}
		else{
			txtNroDoc.setText("");
			txtNroDoc.setEditable(true);
			txtNombre.setText("");
		}
	}
}
