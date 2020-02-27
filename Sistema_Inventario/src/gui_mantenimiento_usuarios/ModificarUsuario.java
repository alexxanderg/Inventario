 package gui_mantenimiento_usuarios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui_mantenimiento_usuarios.MantenimientoUsuarios;
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
	
	String usu;
	String pass;
	String nom;
	String tip;
	MantenimientoUsuarios mu;
	ResultSet rs;
	consultas model = new consultas();
	private JTextField txtModificarUsuarios;
	
	public static void main(String[] args) {
		try {
			ModificarUsuario dialog = new ModificarUsuario(null, null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ModificarUsuario(String temp, String temp2, String temp3, String temp4, MantenimientoUsuarios temp5) {
		setResizable(false);
		usu = temp;
		pass = temp2;
		nom = temp3;
		tip = temp4;
		mu = temp5;
		
		addWindowListener(this);
		setBounds(100, 100, 688, 356);
		getContentPane().setLayout(null);
		
		lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblUsuario.setBounds(10, 90, 138, 38);
		getContentPane().add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(this);
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setForeground(SystemColor.windowBorder);
		txtUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setBounds(10, 139, 316, 34);
		getContentPane().add(txtUsuario);
		
		lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblContrasea.setBounds(344, 90, 205, 38);
		getContentPane().add(lblContrasea);
		
		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblNombre.setBounds(10, 191, 138, 38);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setForeground(SystemColor.windowBorder);
		txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNombre.setColumns(10);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setBounds(158, 195, 502, 34);
		getContentPane().add(txtNombre);
		
		lblTipo = new JLabel("TIPO:");
		lblTipo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipo.setForeground(Color.BLACK);
		lblTipo.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblTipo.setBounds(10, 249, 138, 38);
		getContentPane().add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Vendedor"}));
		cbTipo.setBounds(158, 256, 205, 36);
		getContentPane().add(cbTipo);
		
		txtPass = new JTextField();
		txtPass.addKeyListener(this);
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(SystemColor.windowBorder);
		txtPass.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPass.setColumns(10);
		txtPass.setBackground(SystemColor.controlHighlight);
		txtPass.setBounds(344, 139, 316, 34);
		getContentPane().add(txtPass);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(SystemColor.menu);
		btnModificar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(394, 254, 266, 38);
		getContentPane().add(btnModificar);
		cbTipo.setSelectedIndex(-1);
		
		txtModificarUsuarios = new JTextField();
		txtModificarUsuarios.setText("MODIFICAR USUARIO");
		txtModificarUsuarios.setRequestFocusEnabled(false);
		txtModificarUsuarios.setIgnoreRepaint(true);
		txtModificarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		txtModificarUsuarios.setForeground(Color.WHITE);
		txtModificarUsuarios.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtModificarUsuarios.setFocusable(false);
		txtModificarUsuarios.setFocusTraversalKeysEnabled(false);
		txtModificarUsuarios.setEditable(false);
		txtModificarUsuarios.setColumns(10);
		txtModificarUsuarios.setBackground(Color.DARK_GRAY);
		txtModificarUsuarios.setBounds(0, 0, 682, 58);
		getContentPane().add(txtModificarUsuarios);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsuario, txtPass, txtNombre, cbTipo, btnModificar}));
		cargarDatos();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnCrear(arg0);
		}
	}
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		try {
			if(txtUsuario.getText().length() == 0 || txtPass.getText().length() == 0 || txtNombre.getText().length() == 0 ||cbTipo.getSelectedIndex() == -1 ){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
				this.setAlwaysOnTop(true);
			}
			
			else{
				this.setAlwaysOnTop(false);
				int opc = JOptionPane.showConfirmDialog(null, "¿Desea aplicar los cambios?", "Confirmar cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				this.setAlwaysOnTop(true);
				if (opc == 0){
					this.setAlwaysOnTop(false);
//					model.modificarUsuario(usu, txtUsuario.getText(), txtPass.getText(), txtNombre.getText(), cbTipo.getSelectedIndex());
					mu.cargarUsuarios();
					mu.seleccionarUsuario(txtUsuario.getText());
					mu.setEnabled(true);
					dispose();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente" +e );
		}
		
		
		
	}
	
	public void cargarDatos(){
		txtUsuario.setText(usu);
		txtNombre.setText(nom);
		if(tip.equals("Administrador"))
			cbTipo.setSelectedIndex(0);
		if(tip.equals("Vendedor"))
			cbTipo.setSelectedIndex(1);		
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
		mu.setEnabled(true);
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
}
