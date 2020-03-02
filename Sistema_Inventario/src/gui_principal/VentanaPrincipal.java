package gui_principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Picture;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import clases.Cliente;
import gui_clientes.MantenimientoClientes;
import gui_configuracion.Configuraciones;
import gui_mantenimiento_distribuidores.MantenimientoDistribuidores;
import gui_mantenimiento_productos.MantenimientoProd;
import gui_mantenimiento_usuarios.MantenimientoUsuarios;
import gui_ventas.Ventas2;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Cursor;
import javax.swing.border.LineBorder;

public class VentanaPrincipal extends JFrame {


	private JPanel contentPane;
	private JButton btnInventario;
	private JButton btnVentas;
	private JPanel panel;
	private JDesktopPane desktopPane;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JButton btnUsuario;
	private JButton btnClientes;
	private JButton btnReportes;
	private JButton btnConfiguraciones;
	public JLabel lblUsuario;
    public JLabel lblTipo;
    private JLabel lblLogo;
    private JButton btnDistribuidores;
    private JButton btnCompras;
    private JTextField txtPrueba;
    public JLabel lblIdusuario;

    Ventas2 ventas = new Ventas2(null);
	MantenimientoProd vProductos = new MantenimientoProd(null);
	MantenimientoDistribuidores vdistribuidores = new MantenimientoDistribuidores(null);
	MantenimientoUsuarios vUsuarios = new MantenimientoUsuarios(null);
	MantenimientoClientes vCliente = new MantenimientoClientes(null);
	Configuraciones config = new Configuraciones();

    //Color colorSelec = new Color(242, 136, 113);
    //Color colorDeselec = new Color(220, 20, 60);
	Color colorSelec = new Color(82, 229, 151 );
    Color colorDeselec = new Color(74, 192, 244);
    int anchoImgBtn = 45;
    int altoImgBtn = 45;
	
	
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
		
		btnVentas = new JButton("Ventas ");
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgVentas = new ImageIcon(this.getClass().getResource("/imgMenuventas.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnVentas.setIcon(new ImageIcon(imgVentas));
		btnVentas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setBackground(colorDeselec);
		btnVentas.setBounds(0, 170, 230, 50);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVentas(arg0);
			}
		});
		panel.setLayout(null);
		panel.add(btnVentas);
		
		btnInventario = new JButton("Inventario ");
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgInventario = new ImageIcon(this.getClass().getResource("/imgMenuinventario.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnInventario.setIcon(new ImageIcon(imgInventario));
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setBackground(colorDeselec);
		btnInventario.setBounds(0, 292, 230, 50);
		panel.add(btnInventario);
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnInventario(arg0);
			}
		});

		btnUsuario = new JButton("Usuarios ");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnUsuario(e);
			}
		});
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgUsuarios = new ImageIcon(this.getClass().getResource("/imgMenuusuarios.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnUsuario.setIcon(new ImageIcon(imgUsuarios));
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUsuario.setBackground(colorDeselec);
		btnUsuario.setBounds(0, 475, 230, 50);
		panel.add(btnUsuario);

		btnClientes = new JButton("Clientes ");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnClientes(arg0);
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgClientes = new ImageIcon(this.getClass().getResource("/imgMenuclientes.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnClientes.setIcon(new ImageIcon(imgClientes));
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClientes.setBackground(colorDeselec);
		btnClientes.setBounds(0, 414, 230, 50);
		panel.add(btnClientes);

		btnReportes = new JButton("Reportes ");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnReportes(e);
			}
		});
		btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReportes.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgReportes = new ImageIcon(this.getClass().getResource("/imgMenureportes.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnReportes.setIcon(new ImageIcon(imgReportes));
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReportes.setBackground(colorDeselec);
		btnReportes.setBounds(0, 536, 230, 50);
		panel.add(btnReportes);

		btnConfiguraciones = new JButton("Configuraciones ");
		btnConfiguraciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnConfiguraciones(arg0);
			}
		});
		btnConfiguraciones.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgConfig = new ImageIcon(this.getClass().getResource("/imgMenuconfiguraciones.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnConfiguraciones.setIcon(new ImageIcon(imgConfig));
		btnConfiguraciones.setForeground(Color.WHITE);
		btnConfiguraciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfiguraciones.setBackground(colorDeselec);
		btnConfiguraciones.setBounds(0, 597, 230, 50);
		panel.add(btnConfiguraciones);
		
		btnDistribuidores = new JButton("Distribuidores");
		btnDistribuidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnDistribuidores(arg0);
			}
		});
		btnDistribuidores.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgDist = new ImageIcon(this.getClass().getResource("/imgMenudistribuidor.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnDistribuidores.setIcon(new ImageIcon(imgDist));
		btnDistribuidores.setForeground(Color.WHITE);
		btnDistribuidores.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDistribuidores.setBackground(colorDeselec);
		btnDistribuidores.setBounds(0, 353, 230, 50);
		panel.add(btnDistribuidores);
		
		btnCompras = new JButton("Compras");
		btnCompras.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgCompras = new ImageIcon(this.getClass().getResource("/imgMenucompras.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnCompras.setIcon(new ImageIcon(imgCompras));
		btnCompras.setForeground(Color.WHITE);
		btnCompras.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCompras.setBackground(colorDeselec);
		btnCompras.setBounds(0, 231, 230, 50);
		panel.add(btnCompras);
		
		lblUsuario = new JLabel("Aqui Va El Nombre del Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(0, 110, 230, 36);
		panel.add(lblUsuario);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgLogo = new ImageIcon(this.getClass().getResource("/imgBXB.png")).getImage();
		lblLogo.setIcon(new ImageIcon(imgLogo));
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogo.setBounds(10, 10, 210, 100);
		panel.add(lblLogo);
		
		lblTipo = new JLabel("Aqui Va El Nombre del Usuario");
		lblTipo.setVerticalAlignment(SwingConstants.TOP);
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblTipo.setBounds(0, 144, 230, 29);
		panel.add(lblTipo);
		
		lblIdusuario = new JLabel("");
		lblIdusuario.setForeground(Color.WHITE);
		lblIdusuario.setBounds(0, 96, 67, 14);
		panel.add(lblIdusuario);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(230, 50, 1134, 679);
		contentPane.add(desktopPane);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setForeground(Color.GRAY);
		panel_1.setBounds(230, 0, 1134, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("SISTEMA DE INVENTARIO");
		lblNewLabel.setBounds(10, 0, 1124, 50);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		txtPrueba = new JTextField();
		txtPrueba.setVisible(false);
		txtPrueba.setBounds(0, 0, 33, 20);
		panel_1.add(txtPrueba);
		txtPrueba.setColumns(10);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtPrueba, btnVentas, btnCompras, btnInventario, btnDistribuidores, btnClientes, btnUsuario, btnReportes, btnConfiguraciones}));
		
		cargar();
	}
	
	private void cargar(){
		this.setLocationRelativeTo(null);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void activarOpciones(int tipo){
		switch (tipo) {
		case 0:
			
			break;
		case 1:
			btnClientes.setEnabled(false);
			btnConfiguraciones.setEnabled(false);
			btnInventario.setEnabled(false);
			btnReportes.setEnabled(false);
			btnUsuario.setEnabled(false);
			btnVentas.setEnabled(true);
			btnDistribuidores.setEnabled(true);
			btnCompras.setEnabled(true);
			break;
		}
	}
	
	private void pintarBotones(){
		btnConfiguraciones.setBackground(colorDeselec);
		btnInventario.setBackground(colorDeselec);
		btnReportes.setBackground(colorDeselec);
		btnUsuario.setBackground(colorDeselec);
		btnConfiguraciones.setBackground(colorDeselec);
		btnVentas.setBackground(colorDeselec);
		btnDistribuidores.setBackground(colorDeselec);
		btnCompras.setBackground(colorDeselec);
		btnClientes.setBackground(colorDeselec);
	}

	protected void actionPerformedBtnVentas(ActionEvent arg0) {
		try {
			if (ventas.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				ventas.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnVentas.setBackground(colorSelec);
			} else {
					ventas = new Ventas2(this);
					desktopPane.add(ventas);
					ventas.show();
					ventas.setMaximum(true);
					pintarBotones();
					btnVentas.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}		
	}
	protected void actionPerformedBtnInventario(ActionEvent arg0) {
		try {
			if (vProductos.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				vProductos.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnInventario.setBackground(colorSelec);
			} else {
					vProductos = new MantenimientoProd(this);
					desktopPane.add(vProductos);
					vProductos.show();
					vProductos.setMaximum(true);
					pintarBotones();
					btnInventario.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}			
	}
	protected void actionPerformedBtnDistribuidores(ActionEvent arg0) {
		try {
			if (vdistribuidores.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				vdistribuidores.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnDistribuidores.setBackground(colorSelec);
			} else {
					vdistribuidores = new MantenimientoDistribuidores(this);
					desktopPane.add(vdistribuidores);
					vdistribuidores.show();
					vdistribuidores.setMaximum(true);
					pintarBotones();
					btnDistribuidores.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}	
	}
	protected void actionPerformedBtnClientes(ActionEvent arg0) {
		try {
			if (vCliente.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				vCliente.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnClientes.setBackground(colorSelec);
			} else {
				vCliente = new MantenimientoClientes(this);
					desktopPane.add(vCliente);
					vCliente.show();
					vCliente.setMaximum(true);
					pintarBotones();
					btnClientes.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}	
	}
	protected void actionPerformedBtnUsuario(ActionEvent e) {
		try {
			if (vUsuarios.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				vUsuarios.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnUsuario.setBackground(colorSelec);
			} else {
				vUsuarios = new gui_mantenimiento_usuarios.MantenimientoUsuarios(this);
					desktopPane.add(vUsuarios);
					vUsuarios.show();
					vUsuarios.setMaximum(true);
					pintarBotones();
					btnUsuario.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	protected void actionPerformedBtnReportes(ActionEvent e) {
	}
	protected void actionPerformedBtnConfiguraciones(ActionEvent arg0) {
		try {
			if (config.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya está abierto");
				config.setSelected(true); // PONER JINTERNALFRAME DELANTE
				pintarBotones();
				btnConfiguraciones.setBackground(colorSelec);
			} else {
				config = new Configuraciones();
					desktopPane.add(config);
					config.show();
					config.setMaximum(true);
					pintarBotones();
					btnConfiguraciones.setBackground(colorSelec);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}	
	}
}
