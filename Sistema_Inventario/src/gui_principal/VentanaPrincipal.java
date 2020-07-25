package gui_principal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import gui_clientes.MantenimientoClientes;
import gui_compras.MantenimientoCompras;
import gui_configuracion.Configuraciones;
import gui_distribuidores.MantenimientoDistribuidores;
import gui_notificaciones.notificaciones;
import gui_productos.Kardex;
import gui_productos.MantenimientoProd;
import gui_reportes.Reportes;
import gui_usuarios.MantenimientoUsuarios;
import gui_ventas.BuscarVentas;
import gui_ventas.Ventas;
import mysql.consultas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    public JLabel lblTipo;
    private JLabel lblLogo;
    private JButton btnDistribuidores;
    private JButton btnCompras;
    private JTextField txtPrueba;
    public JLabel lblIdusuario;
    private JLabel lblCerrarSesion;
    private JButton btnBuscarVentas;
	public JLabel lblUsuario;
	public Ventas ventas = null;
	public BuscarVentas buscarV = null;
	public MantenimientoCompras vCompras = null;
	public MantenimientoProd vProductos = null;
	public Kardex vKardex = null;
	public MantenimientoDistribuidores vdistribuidores = null;
	public MantenimientoUsuarios vUsuarios = null;
	public MantenimientoClientes vCliente = null;
	public Reportes vReportes = null;
	public Configuraciones config = null;
	public notificaciones notifica = null;

	ResultSet rs;
	consultas consulta = new consultas();
	
	
	
	/*Color colorSelec = new Color(57, 192, 38 ); // KAT
    Color colorDeselec = new Color(133, 207, 122 );*/
	
	/*Color colorSelec = new Color(203, 71, 131); //NAVITAL
    Color colorDeselec = new Color(248, 163, 202);*/
	
    Color colorSelec = new Color(240, 67, 85);  //BXB
    Color colorDeselec = new Color(30, 144, 255);
   
    /*Color colorSelec = new Color(255, 177, 70 );
    Color colorDeselec = new Color(243, 112, 112);*/
    int anchoImgBtn = 45;
    int altoImgBtn = 45;
    private JLabel lblLogoBxB;
    private JButton btnNotificaciones;
	
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

	public VentanaPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		setTitle("Sistema de Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1382, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 230, 707);
		contentPane.add(panel);
		
		btnVentas = new JButton("Vender");
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgVentas = new ImageIcon(this.getClass().getResource("/imgMenuventas.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnVentas.setIcon(new ImageIcon(imgVentas));
		btnVentas.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setBackground(colorDeselec);
		btnVentas.setBounds(0, 219, 177, 50);
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
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setBackground(colorDeselec);
		btnInventario.setBounds(0, 280, 230, 50);
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
		btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUsuario.setBackground(colorDeselec);
		btnUsuario.setBounds(0, 524, 230, 50);
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
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClientes.setBackground(colorDeselec);
		btnClientes.setBounds(0, 463, 230, 50);
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
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReportes.setBackground(colorDeselec);
		btnReportes.setBounds(0, 585, 230, 50);
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
		btnConfiguraciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfiguraciones.setBackground(colorDeselec);
		btnConfiguraciones.setBounds(0, 646, 230, 50);
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
		btnDistribuidores.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDistribuidores.setBackground(colorDeselec);
		btnDistribuidores.setBounds(0, 402, 230, 50);
		panel.add(btnDistribuidores);
		
		btnCompras = new JButton("Compras");
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCompras(e);
			}
		});
		btnCompras.setHorizontalAlignment(SwingConstants.LEFT);
		Image imgCompras = new ImageIcon(this.getClass().getResource("/imgMenucompras.png")).getImage().getScaledInstance(anchoImgBtn, altoImgBtn, Image.SCALE_AREA_AVERAGING);
		btnCompras.setIcon(new ImageIcon(imgCompras));
		btnCompras.setForeground(Color.WHITE);
		btnCompras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCompras.setBackground(colorDeselec);
		btnCompras.setBounds(0, 341, 230, 50);
		panel.add(btnCompras);
		
		lblUsuario = new JLabel("Aqui Va El Nombre del Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(0, 132, 230, 36);
		panel.add(lblUsuario);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgLogo = new ImageIcon(this.getClass().getResource("/imgLogo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		lblLogo.setIcon(new ImageIcon(imgLogo));
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogo.setBounds(10, 31, 210, 100);
		panel.add(lblLogo);
		
		lblTipo = new JLabel("Aqui Va El Nombre del Usuario");
		lblTipo.setVerticalAlignment(SwingConstants.TOP);
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setForeground(new Color(233, 150, 122));
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(0, 166, 230, 29);
		panel.add(lblTipo);
		
		lblIdusuario = new JLabel("1");
		lblIdusuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdusuario.setVisible(false);
		lblIdusuario.setForeground(Color.WHITE);
		lblIdusuario.setBounds(108, 194, 26, 14);
		panel.add(lblIdusuario);
		
		btnBuscarVentas = new JButton("");
		btnBuscarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnBuscarVentas(arg0);
			}
		});
		Image imgBuscar = new ImageIcon(this.getClass().getResource("/imgbuscar.png")).getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
		btnBuscarVentas.setIcon(new ImageIcon(imgBuscar));
		btnBuscarVentas.setForeground(Color.WHITE);
		btnBuscarVentas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscarVentas.setBackground(colorDeselec);
		btnBuscarVentas.setBounds(179, 219, 51, 50);
		panel.add(btnBuscarVentas);
		
		lblCerrarSesion = new JLabel("salir");
		lblCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrarSesion.setBounds(0, 0, 67, 29);
		panel.add(lblCerrarSesion);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedLblCerrarSesion(arg0);
			}
		});
		lblCerrarSesion.setForeground(new Color(250, 128, 114));
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(230, 50, 1134, 657);
		contentPane.add(desktopPane);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setForeground(Color.GRAY);
		panel_1.setBounds(230, 0, 1134, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("MINIMARKET LA DOLORES");
		lblNewLabel.setBounds(242, 0, 642, 50);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		txtPrueba = new JTextField();
		txtPrueba.setVisible(false);
		txtPrueba.setBounds(906, 0, 18, 20);
		panel_1.add(txtPrueba);
		txtPrueba.setColumns(10); 
		lblLogoBxB = new JLabel("");
		lblLogoBxB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedLblLogoBxB(e);
			}
		});
		lblLogoBxB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogoBxB.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		lblLogoBxB.setBounds(1023, 0, 111, 50);
		panel_1.add(lblLogoBxB);
		lblLogoBxB.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgLogoBxB = new ImageIcon(this.getClass().getResource("/imgBxBhrztl.png")).getImage().getScaledInstance(110, 47, Image.SCALE_AREA_AVERAGING);
		lblLogoBxB.setIcon(new ImageIcon(imgLogoBxB));
		lblLogoBxB.setForeground(Color.WHITE);
		lblLogoBxB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnNotificaciones = new JButton("! ");
		btnNotificaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNotificaciones.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNotificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNotificaciones(arg0);
			}
		});
		btnNotificaciones.setForeground(new Color(220, 20, 60));
		btnNotificaciones.setBackground(Color.DARK_GRAY);
		btnNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNotificaciones.setBounds(966, 0, 57, 50);
		panel_1.add(btnNotificaciones);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtPrueba, btnVentas, btnCompras, btnInventario, btnDistribuidores, btnClientes, btnUsuario, btnReportes, btnConfiguraciones}));
		
		cargar();
	}
	
	private void cargar(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		verificarNotificaciones();
	}
	
	public void verificarNotificaciones(){
		/*try {
			consulta.iniciar();
			rs = consulta.cargarProductos();
			rs.next();
			Float cantmin = rs.getFloat("cantmin");
			Float cant = rs.getFloat("cantidad");
			
			if(cant <= cantmin)
				btnNotificaciones.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR NOTIFCACIONES: " + e);
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
		
		try {
			consulta.iniciar();
			rs = consulta.buscarProductosPorVencer();
			rs.next();	
			Float cantmin = rs.getFloat("cantmin");
			btnNotificaciones.setVisible(true);
		} catch (Exception e) {
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
*/
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
			btnBuscarVentas.setEnabled(true);
			btnDistribuidores.setEnabled(false);
			btnCompras.setEnabled(false);
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
		btnBuscarVentas.setBackground(colorDeselec);
		btnDistribuidores.setBackground(colorDeselec);
		btnCompras.setBackground(colorDeselec);
		btnClientes.setBackground(colorDeselec);
	}

	public void actionPerformedBtnVentas(ActionEvent arg0) {
		try {
			cerrarVentanas();
			ventas = new Ventas(this, -1);
			desktopPane.add(ventas);
			ventas.show();
			ventas.setMaximum(true);
			pintarBotones();
			btnVentas.setBackground(colorSelec);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Ventas: " + e);
		}
		/*try {
			if (ventas.isShowing()) { // VERIFICA SI LA VENTANA YA ESTÁ ABIERTA
				ventas.setSelected(true); // PONER JINTERNALFRAME DELANTE DE LOS OTROS
				pintarBotones();
				btnVentas.setBackground(colorSelec);
			}
		} catch (Exception f) { // Aqui entrará si no se ha inicializado la ventana
			try {
				abrirVentanaVentas();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear ventana Ventas: " + e);
			}
		}*/		
	}
	
	public void cargarVentas(int nroCompra){
		try {
			cerrarVentanas();
			ventas = new Ventas(this, nroCompra);
			desktopPane.add(ventas);
			ventas.show();
			ventas.setMaximum(true);
			pintarBotones();
			btnVentas.setBackground(colorSelec);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Ventas: " + e);
		}
	}
	
	
	protected void actionPerformedBtnBuscarVentas(ActionEvent arg0) {
		try {
			cerrarVentanas();
			buscarV = new BuscarVentas(this);
			desktopPane.add(buscarV);
			buscarV.show();
			buscarV.setMaximum(true);
			pintarBotones();
			btnBuscarVentas.setBackground(colorSelec);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Buscar Ventas: " + e);
		}
	}
	protected void actionPerformedBtnCompras(ActionEvent e) {
		try {
			JOptionPane.showMessageDialog(null, "Apartado en actualización, se están implementando mejoras.");
			cerrarVentanas();
			vCompras = new MantenimientoCompras(this);
			desktopPane.add(vCompras);
			vCompras.show();
			vCompras.setMaximum(true);
			pintarBotones();
			btnCompras.setBackground(colorSelec);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Compras: " + e2);
		}
	}
	protected void actionPerformedBtnInventario(ActionEvent arg0) {
		
		String[] options = {"Ver lista de productos", "Ver/Realizar Kardex"};
		int seleccion = JOptionPane.showOptionDialog(null, "Por favor, seleccione una opción", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  options, options[0]);
		
		if(seleccion == 0){
			try {
				cerrarVentanas();
				vProductos = new MantenimientoProd(this);
				desktopPane.add(vProductos);
				vProductos.show();
				vProductos.setMaximum(true);
				pintarBotones();
				btnInventario.setBackground(colorSelec);
			} catch (PropertyVetoException e) {
				JOptionPane.showMessageDialog(null, "Error al crear ventana Inventario: " + e);
			}	
		}
		if(seleccion == 1){
			try {
				cerrarVentanas();
				vKardex = new Kardex(this);
				desktopPane.add(vKardex);
				vKardex.show();
				vKardex.setMaximum(true);
				pintarBotones();
				btnInventario.setBackground(colorSelec);
			} catch (PropertyVetoException e) {
				JOptionPane.showMessageDialog(null, "Error al crear ventana Inventario: " + e);
			}	
		}
		
	}
	
	protected void actionPerformedBtnDistribuidores(ActionEvent arg0) {
		try {
			cerrarVentanas();
			vdistribuidores = new MantenimientoDistribuidores(this);
			desktopPane.add(vdistribuidores);
			vdistribuidores.show();
			vdistribuidores.setMaximum(true);
			pintarBotones();
			btnDistribuidores.setBackground(colorSelec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Distribuidores: " + e);
		}
	}
	protected void actionPerformedBtnClientes(ActionEvent arg0) {
		try {
			
			cerrarVentanas();
			vCliente = new MantenimientoClientes(this);
			desktopPane.add(vCliente);
			vCliente.show();
			vCliente.setMaximum(true);
			pintarBotones();
			btnClientes.setBackground(colorSelec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Clientes: " + e);
		}
	}
	protected void actionPerformedBtnUsuario(ActionEvent e) {
		try {
			cerrarVentanas();
			vUsuarios = new gui_usuarios.MantenimientoUsuarios(this);
			desktopPane.add(vUsuarios);
			vUsuarios.show();
			vUsuarios.setMaximum(true);
			pintarBotones();
			btnUsuario.setBackground(colorSelec);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Usuarios: " + e2);
		}
	}
	
	protected void actionPerformedBtnReportes(ActionEvent e) {
		try {
			cerrarVentanas();
			vReportes = new Reportes(this);
			desktopPane.add(vReportes);
			vReportes.show();
			vReportes.setMaximum(true);
			pintarBotones();
			btnReportes.setBackground(colorSelec);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Reportes: " + e2);
		}
	}
	
	protected void actionPerformedBtnConfiguraciones(ActionEvent arg0) {
		try {
			cerrarVentanas();
			config = new Configuraciones();
			desktopPane.add(config);
			config.show();
			config.setMaximum(true);
			pintarBotones();
			btnConfiguraciones.setBackground(colorSelec);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Configuraciones: " + e);
		}
	}
	
	protected void actionPerformedBtnNotificaciones(ActionEvent arg0) {
		try {
			cerrarVentanas();
			notifica = new notificaciones(this);
			desktopPane.add(notifica);
			notifica.show();
			notifica.setMaximum(true);
			pintarBotones();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al crear ventana Notificaciones: " + e);
		}
	}
	
	protected void mouseClickedLblLogoBxB(MouseEvent e) {
		goToURL("https://www.bytexbyte.com.pe");
		goToURL("https://www.fb.com/bytexbyte");
	}
	
	public void cerrarVentanas(){
		if(ventas != null)
			ventas.dispose();
		ventas = null;
	
		if(buscarV != null) 
			buscarV.dispose();
		buscarV = null;

		if(vCompras != null) 
			vCompras.dispose();
		vCompras = null;
		
		if(vProductos != null) 
			vProductos.dispose();
		vProductos = null;
		
		if(vdistribuidores != null) 
			vdistribuidores.dispose();
		vdistribuidores = null;
		
		if(vUsuarios != null) 
			vUsuarios.dispose();
		vUsuarios = null;
		
		if(vCliente != null) 
			vCliente.dispose();
		vCliente = null;
		
		if(vReportes != null) 
			vReportes.dispose();
		vReportes = null;
		
		if(config != null) 
			config.dispose();
		config = null;
		
		if(notifica != null) 
			notifica.dispose();
		notifica = null;
	}
	
	protected void mouseClickedLblCerrarSesion(MouseEvent arg0) {
		Login log = new Login();
		log.setVisible(true);
		this.dispose();
	}
	public void goToURL(String URL){
        if (java.awt.Desktop.isDesktopSupported()) {
         java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

         if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
             try {
                 java.net.URI uri = new java.net.URI(URL);
                 desktop.browse(uri);
             } catch (Exception ex) {
             }
         }
     }
 }
	protected void windowClosingThis(WindowEvent arg0) {
		try {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy  HH.mm.ss");
			Date today = Calendar.getInstance().getTime();       
			String reportDate = df.format(today);
			File directorio=new File("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA"); 
			directorio.mkdirs(); 
			Process p;
			p = Runtime.getRuntime().exec("mysqldump -u root -pAa123 db_inventario");
			InputStream is = p.getInputStream();
			FileOutputStream fos = new FileOutputStream("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA\\backup_inventario  "+reportDate+".sql");
			byte[] buffer = new byte[1000];
			int leido = is.read(buffer);
			while(leido>0){
				fos.write(buffer, 0, leido);
				leido = is.read(buffer);
			}
			//JOptionPane.showMessageDialog(null, "Copia de segudidad creada en: \n D:/ INFORMACION DEL SISTEMA / BACKUP_SISTEMA / ");
			//JOptionPane.showMessageDialog(null, "Copia de segudidad realizada correctamente");
			fos.close();
		} catch (IOException e1) {
			//JOptionPane.showMessageDialog(null, e1);
		}
	}
}
