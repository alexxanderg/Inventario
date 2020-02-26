package gui_principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Picture;

import gui_configuracion.Configuraciones;
import gui_mantenimiento_productos.InternalMantenimiento;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnInventario;
	private JButton button2;
	private JPanel panel;
	private JDesktopPane desktopPane;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JButton btnUsuario;
	private JButton btnClientes;
	private JButton btnReportes;
	private JButton btnConfiguraciones;
	public JLabel lblUsuario;

	InternalMantenimiento vmantenimiento = new InternalMantenimiento(null);
	Configuraciones config = new Configuraciones();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1380, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 230, 729);
		contentPane.add(panel);

		button2 = new JButton("Ventas");
		button2.setHorizontalAlignment(SwingConstants.RIGHT);
		button2.setFont(new Font("Tahoma", Font.BOLD, 16));
		button2.setForeground(Color.WHITE);
		button2.setBackground(new Color(220, 20, 60));
		button2.setBounds(0, 174, 230, 50);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton2(arg0);
			}
		});
		panel.setLayout(null);

		btnInventario = new JButton("Inventario");
		btnInventario.setHorizontalAlignment(SwingConstants.RIGHT);
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setBackground(new Color(220, 20, 60));
		btnInventario.setBounds(0, 113, 230, 50);
		panel.add(btnInventario);
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNewButton(arg0);
			}
		});
		panel.add(button2);

		btnUsuario = new JButton("Usuarios");
		btnUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUsuario.setBackground(new Color(220, 20, 60));
		btnUsuario.setBounds(0, 235, 230, 50);
		panel.add(btnUsuario);

		btnClientes = new JButton("Clientes");
		btnClientes.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClientes.setBackground(new Color(220, 20, 60));
		btnClientes.setBounds(0, 296, 230, 50);
		panel.add(btnClientes);

		btnReportes = new JButton("Reportes");
		btnReportes.setHorizontalAlignment(SwingConstants.RIGHT);
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReportes.setBackground(new Color(220, 20, 60));
		btnReportes.setBounds(0, 357, 230, 50);
		panel.add(btnReportes);

		btnConfiguraciones = new JButton("Configuraciones");
		btnConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnConfiguraciones(arg0);
			}
		});
		btnConfiguraciones.setHorizontalAlignment(SwingConstants.RIGHT);
		btnConfiguraciones.setForeground(Color.WHITE);
		btnConfiguraciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfiguraciones.setBackground(new Color(220, 20, 60));
		btnConfiguraciones.setBounds(0, 418, 230, 50);
		panel.add(btnConfiguraciones);
		
		lblUsuario = new JLabel("Aqui Va El Nombre del Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(0, 76, 230, 36);
		panel.add(lblUsuario);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(231, 50, 1134, 679);
		contentPane.add(desktopPane);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setForeground(Color.GRAY);
		panel_1.setBounds(230, 0, 1134, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("SISTEMA DE INVENTARIO");
		lblNewLabel.setBounds(413, 0, 293, 50);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		cargar();
	}
	
	private void cargar(){
		this.setLocationRelativeTo(null);
	}

	protected void actionPerformedBtnNewButton(ActionEvent arg0) {
		try {
			if (vmantenimiento.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
			} else {
					vmantenimiento = new InternalMantenimiento(this);
					desktopPane.add(vmantenimiento);
					vmantenimiento.show();
					vmantenimiento.setMaximum(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}			
	}

	protected void actionPerformedButton2(ActionEvent arg0) {
	}
	
	protected void actionPerformedBtnConfiguraciones(ActionEvent arg0) {
		try {
			if (config.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
			} else {
				config = new Configuraciones();
					desktopPane.add(config);
					config.show();
					config.setMaximum(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}	
	}
}
