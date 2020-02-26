package gui_mantenimiento_usuarios;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import gui_mantenimiento_usuarios.InternalMantenimiento;

import java.awt.Component;

public class NuevoUsuario2 extends JDialog implements ActionListener, WindowListener {
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContrasea;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTipo;
	private JComboBox cbTipo;
	private JTextField txtPass;
	private JButton btnCrear;
	
	MantenimientoUsuarios mu;	
	ResultSet rs;
	consultas model = new consultas();
	InternalMantenimiento mantenimientoUsuarios;
	
	private JTextField txtAgregarUsuario;
	
	public static void main(String[] args) {
		try {
			NuevoUsuario2 dialog = new NuevoUsuario2(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public NuevoUsuario2(InternalMantenimiento mantenimientoUsuarios) {
		this.mantenimientoUsuarios = mantenimientoUsuarios;
		
		setResizable(false);
		addWindowListener(this);
		setBounds(100, 100, 688, 356);
		getContentPane().setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblUsuario.setBounds(10, 90, 138, 38);
		getContentPane().add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsuario.setForeground(SystemColor.windowBorder);
		txtUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setBounds(10, 139, 316, 34);
		getContentPane().add(txtUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblContrasea.setBounds(344, 90, 205, 38);
		getContentPane().add(lblContrasea);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblNombre.setBounds(10, 191, 138, 38);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setForeground(SystemColor.windowBorder);
		txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtNombre.setColumns(10);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setBounds(158, 195, 502, 34);
		getContentPane().add(txtNombre);
		
		lblTipo = new JLabel("Tipo:");
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
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(SystemColor.windowBorder);
		txtPass.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPass.setColumns(10);
		txtPass.setBackground(SystemColor.controlHighlight);
		txtPass.setBounds(344, 139, 316, 34);
		getContentPane().add(txtPass);
		
		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setForeground(SystemColor.menu);
		btnCrear.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(434, 254, 226, 38);
		getContentPane().add(btnCrear);
		cbTipo.setSelectedIndex(-1);
		
		txtAgregarUsuario = new JTextField();
		txtAgregarUsuario.setText("AGREGAR USUARIO");
		txtAgregarUsuario.setRequestFocusEnabled(false);
		txtAgregarUsuario.setIgnoreRepaint(true);
		txtAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtAgregarUsuario.setForeground(Color.WHITE);
		txtAgregarUsuario.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtAgregarUsuario.setFocusable(false);
		txtAgregarUsuario.setFocusTraversalKeysEnabled(false);
		txtAgregarUsuario.setEditable(false);
		txtAgregarUsuario.setColumns(10);
		txtAgregarUsuario.setBackground(Color.DARK_GRAY);
		txtAgregarUsuario.setBounds(0, 0, 682, 58);
		getContentPane().add(txtAgregarUsuario);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsuario, txtPass, txtNombre, cbTipo, btnCrear}));
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCrear) {
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
				rs = model.ingresarUsuario(txtUsuario.getText(), txtPass.getText(), txtNombre.getText(), cbTipo.getSelectedIndex());
				mu.cargarUsuarios();
				mu.seleccionarUsuario(txtUsuario.getText());
				dispose();
				mu.setEnabled(true);
				mu.show();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
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
		mu.setEnabled(true);
	}
}
