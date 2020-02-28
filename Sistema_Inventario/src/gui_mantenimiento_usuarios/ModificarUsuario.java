 package gui_mantenimiento_usuarios;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.Normalizer;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.LineBorder;

public class ModificarUsuario extends JDialog implements ActionListener, WindowListener, KeyListener {
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContrasea;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	private JTextField txtPass;
	private JButton btnModificar;
	private JTextField txtAgregarUsuario;
	private JTextField txtModificarUsuarios;
	private JButton btnCancelar;
	private JButton btnModificarr;
	
	
	MantenimientoUsuarios mantenimientoUsuarios;
	int idusuario;
	ResultSet rs;
	consultas consulta = new consultas();
	
	public static void main(String[] args) {
		try {
			ModificarUsuario dialog = new ModificarUsuario(0, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ModificarUsuario(int idusuario, MantenimientoUsuarios mantenimientoUsuarios) {
		this.mantenimientoUsuarios = mantenimientoUsuarios;
		this.idusuario = idusuario;
		
		setResizable(false);
		addWindowListener(this);
		setBounds(100, 100, 400, 453);
		getContentPane().setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUsuario.setForeground(Color.DARK_GRAY);
		lblUsuario.setFont(new Font("Candara", Font.BOLD, 20));
		lblUsuario.setBounds(10, 131, 138, 38);
		getContentPane().add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setForeground(SystemColor.windowBorder);
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setBounds(10, 167, 370, 34);
		getContentPane().add(txtUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContrasea.setForeground(Color.DARK_GRAY);
		lblContrasea.setFont(new Font("Candara", Font.BOLD, 20));
		lblContrasea.setBounds(10, 201, 205, 34);
		getContentPane().add(lblContrasea);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre.setBounds(10, 61, 138, 38);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setForeground(SystemColor.windowBorder);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(10, 99, 370, 34);
		getContentPane().add(txtNombre);
		
		lblTipo = new JLabel("Tipo de usuario:");
		lblTipo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipo.setForeground(Color.DARK_GRAY);
		lblTipo.setFont(new Font("Candara", Font.BOLD, 20));
		lblTipo.setBounds(10, 290, 150, 38);
		getContentPane().add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setBackground(Color.WHITE);
		cbTipo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Vendedor"}));
		cbTipo.setBounds(158, 290, 222, 36);
		getContentPane().add(cbTipo);
		
		txtPass = new JTextField();
		txtPass.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(SystemColor.windowBorder);
		txtPass.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBackground(Color.WHITE);
		txtPass.setBounds(10, 233, 370, 34);
		getContentPane().add(txtPass);
		
		btnModificarr = new JButton("MODIFICAR");
		btnModificarr.addActionListener(this);
		btnModificarr.setForeground(SystemColor.menu);
		btnModificarr.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnModificarr.setBackground(new Color(30, 144, 255));
		btnModificarr.setBounds(205, 360, 175, 38);
		getContentPane().add(btnModificarr);
		cbTipo.setSelectedIndex(-1);
		
		txtAgregarUsuario = new JTextField();
		txtAgregarUsuario.setText("MODIFICAR USUARIO");
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
		txtAgregarUsuario.setBounds(0, 0, 394, 50);
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
		btnCancelar.setBounds(10, 360, 175, 38);
		getContentPane().add(btnCancelar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNombre, txtUsuario, txtPass, cbTipo}));
		
		cargar();
	}
	public void cargar(){
		try {
			ResultSet rs = consulta.cargarUsu(idusuario);
			rs.next();
			txtUsuario.setText(rs.getString("usuario"));
			txtNombre.setText(rs.getString("nombre"));
			cbTipo.setSelectedIndex(rs.getInt("tipo"));	//0ADMIN 1VENDEDOR		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnModificarr) {
			actionPerformedBtnModificar(arg0);
		}
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		try {
			if(txtUsuario.getText().length() == 0 || txtPass.getText().length() == 0 || txtNombre.getText().length() == 0 ||cbTipo.getSelectedIndex() == -1 )
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
			else{
				int opc = JOptionPane.showConfirmDialog(null, "¿Desea modificar el usuario?", "Confirmar cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (opc == 0){
					this.setAlwaysOnTop(false);
					consulta.modificarUsuario(idusuario, txtUsuario.getText(), txtPass.getText(), txtNombre.getText(), cbTipo.getSelectedIndex());
					mantenimientoUsuarios.cargar();
					mantenimientoUsuarios.selecionarUsuario(""+idusuario);
					dispose();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente" +e );
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
		mantenimientoUsuarios.setEnabled(true);
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyTypedTxtNombre(arg0);
		}
		if (arg0.getSource() == txtPass) {
			keyTypedTxtPass(arg0);
		}
		if (arg0.getSource() == txtUsuario) {
			keyTypedTxtUsuario(arg0);
		}
	}
	protected void keyTypedTxtUsuario(KeyEvent arg0) {
		if (txtUsuario.getText().length() == 20){
			arg0.consume();
		}
	}
	protected void keyTypedTxtPass(KeyEvent arg0) {
		if (txtPass.getText().length() == 20){
			arg0.consume();
		}
	}
	protected void keyTypedTxtNombre(KeyEvent arg0) {
		if (txtNombre.getText().length() == 50){
			arg0.consume();
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}
}
