package gui_reportes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import clases.AbstractJasperReports;
import clases.Categoria;
import clases.Cliente;
import clases.Usuarios;
import gui_principal.EleccionVentanas;
import gui_ventas.Ventas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import mysql.MySQLConexion;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Window.Type;
import javax.swing.JLabel;
import com.toedter.calendar.JCalendar;
import java.awt.SystemColor;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;

public class Reportes extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JButton btnGenerarRVD;
	private JButton btnCerrar;
	private JTextField txtCrearReportes;
	private JLabel lblVer;
	private JLabel lblAl;
	private JTextField textField;
	private JTextField txtNVenta;
	private JButton btnGenerarRVDetallada;
	private JLabel lblVerTotalDe;
	private JButton btnGenerarMenores;
	private JLabel lblVerProductos;
	private JLabel lblVerProductosMayores;
	private JTextField txtMenores;
	private JTextField txtMayores;
	private JButton btnGenerarMayores;
	private JLabel lblNmeroDeVenta;
	private JTextField textField_3;
	private JTextField textField_1;

	String usuario;
	Ventas v;
	private JButton btngenerarReporteVentas;
	private JComboBox<Usuarios> cbUsuarios;
	private JButton btnVerProductosIngresados;
	private JDateChooser calendar;
	private JDateChooser calendar_1;
	private JDateChooser calendar_2;
	private JDateChooser calendar_3;
	private JLabel label;
	private JLabel lblRegistroDeIngresos;
	private JTextField textField_4;
	private JLabel lblDel;
	private JLabel lblDel_1;
	private JComboBox cbMetodoPago;
	private JLabel lblComprasDeCliente;
	private JButton btnVerComprasCliente;
	private JComboBox<Cliente> cbCliente;
	private JLabel lblProductosAVencer;
	private JLabel lblDesde;
	private JButton btnVerProductosQue;
	private JDateChooser calendar_4;
	private JLabel lblHasta;
	private JDateChooser calendar_5;
	private JTextField textField_5;
	private JComboBox<Categoria> cbCategoria;
	private JLabel lblcategoria;
	private JTextField textField_2;
	private JDateChooser calendar_7;
	private JButton btnVerRanking;
	private JLabel label_1;
	private JLabel lblRankingDeProductos;
	private JDateChooser calendar_6;
	private JLabel label_3;
	private JComboBox<Cliente> cbxRanking;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Reportes(String temp2, Ventas temp3) {
		usuario = temp2;
		v = temp3;

		addWindowListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1259, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnGenerarRVD = new JButton("<html>VER SOLO VENTAS</html>");
		btnGenerarRVD.setBounds(25, 220, 260, 62);
		btnGenerarRVD.setForeground(new Color(255, 255, 255));
		btnGenerarRVD.setBackground(new Color(30, 144, 255));
		btnGenerarRVD.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnGenerarRVD.addActionListener(this);

		btnCerrar = new JButton("VOLVER");
		btnCerrar.setBounds(0, 0, 166, 63);
		btnCerrar.setForeground(new Color(0, 255, 0));
		btnCerrar.setBackground(Color.DARK_GRAY);
		btnCerrar.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnCerrar.addActionListener(this);

		lblVer = new JLabel("VENTAS REALIZADAS POR:");
		lblVer.setBounds(25, 63, 574, 32);
		lblVer.setHorizontalAlignment(SwingConstants.CENTER);
		lblVer.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));

		lblAl = new JLabel("al:");
		lblAl.setBounds(339, 160, 55, 38);
		lblAl.setHorizontalAlignment(SwingConstants.LEFT);
		lblAl.setFont(new Font("Tw Cen MT", Font.BOLD, 28));

		txtNVenta = new JTextField();
		txtNVenta.addKeyListener(this);
		txtNVenta.setBounds(345, 313, 103, 34);
		txtNVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNVenta.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtNVenta.setColumns(10);
		txtNVenta.setBackground(SystemColor.controlHighlight);

		btnGenerarRVDetallada = new JButton("Crear");
		btnGenerarRVDetallada.setBounds(458, 311, 141, 35);
		btnGenerarRVDetallada.addActionListener(this);
		btnGenerarRVDetallada.setForeground(new Color(255, 255, 255));
		btnGenerarRVDetallada.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarRVDetallada.setBackground(new Color(30, 144, 255));

		lblVerTotalDe = new JLabel("KARDEX");
		lblVerTotalDe.setBounds(801, 74, 360, 32);
		lblVerTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerTotalDe.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));

		btnGenerarMenores = new JButton("Crear");
		btnGenerarMenores.setBounds(1068, 185, 146, 35);
		btnGenerarMenores.addActionListener(this);
		btnGenerarMenores.setForeground(new Color(255, 255, 255));
		btnGenerarMenores.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarMenores.setBackground(new Color(30, 144, 255));
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(618, 63, 18, 614);
		textField.setRequestFocusEnabled(false);
		textField.setIgnoreRepaint(true);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.ORANGE);
		textField.setFont(new Font("USAngel", Font.BOLD, 28));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.DARK_GRAY);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setRequestFocusEnabled(false);
		textField_1.setIgnoreRepaint(true);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.ORANGE);
		textField_1.setFont(new Font("USAngel", Font.BOLD, 28));
		textField_1.setFocusable(false);
		textField_1.setFocusTraversalKeysEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setBounds(0, 355, 628, 18);
		contentPane.add(textField_1);

		textField_3 = new JTextField();
		textField_3.setRequestFocusEnabled(false);
		textField_3.setIgnoreRepaint(true);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.ORANGE);
		textField_3.setFont(new Font("USAngel", Font.BOLD, 28));
		textField_3.setFocusable(false);
		textField_3.setFocusTraversalKeysEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.DARK_GRAY);
		textField_3.setBounds(635, 298, 618, 18);
		contentPane.add(textField_3);
		contentPane.add(btnCerrar);
		contentPane.add(lblVer);
		contentPane.add(lblAl);
		contentPane.add(btnGenerarRVD);
		contentPane.add(btnGenerarRVDetallada);
		contentPane.add(txtNVenta);
		contentPane.add(lblVerTotalDe);
		contentPane.add(btnGenerarMenores);

		lblVerProductos = new JLabel("<html>Ver productos con<br> stock menor a: </html>");
		lblVerProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblVerProductos.setBounds(715, 169, 242, 51);
		contentPane.add(lblVerProductos);

		lblVerProductosMayores = new JLabel("<html>Ver productos con<br>stock mayor a: </html>");
		lblVerProductosMayores.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblVerProductosMayores.setBounds(715, 231, 242, 51);
		contentPane.add(lblVerProductosMayores);

		txtCrearReportes = new JTextField();
		txtCrearReportes.setBounds(0, 0, 1253, 63);
		txtCrearReportes.setText("REPORTES");
		txtCrearReportes.setRequestFocusEnabled(false);
		txtCrearReportes.setIgnoreRepaint(true);
		txtCrearReportes.setHorizontalAlignment(SwingConstants.CENTER);
		txtCrearReportes.setForeground(new Color(255, 255, 255));
		txtCrearReportes.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		txtCrearReportes.setFocusable(false);
		txtCrearReportes.setFocusTraversalKeysEnabled(false);
		txtCrearReportes.setEditable(false);
		txtCrearReportes.setColumns(10);
		txtCrearReportes.setBackground(Color.DARK_GRAY);
		contentPane.add(txtCrearReportes);

		txtMenores = new JTextField();
		txtMenores.addKeyListener(this);
		txtMenores.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMenores.setBackground(SystemColor.controlHighlight);
		txtMenores.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtMenores.setBounds(968, 186, 95, 34);
		contentPane.add(txtMenores);
		txtMenores.setColumns(10);

		txtMayores = new JTextField();
		txtMayores.addKeyListener(this);
		txtMayores.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMayores.setBackground(SystemColor.controlHighlight);
		txtMayores.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtMayores.setColumns(10);
		txtMayores.setBounds(968, 247, 95, 35);
		contentPane.add(txtMayores);

		btnGenerarMayores = new JButton("Crear");
		btnGenerarMayores.addActionListener(this);
		btnGenerarMayores.setForeground(Color.WHITE);
		btnGenerarMayores.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarMayores.setBackground(new Color(30, 144, 255));
		btnGenerarMayores.setBounds(1068, 247, 146, 35);
		contentPane.add(btnGenerarMayores);

		lblNmeroDeVenta = new JLabel("<html>Ver detalle de Venta espec\u00EDfica<br>Ingrese Nro de Venta:</html>");
		lblNmeroDeVenta.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblNmeroDeVenta.setBounds(10, 298, 325, 63);
		contentPane.add(lblNmeroDeVenta);

		btngenerarReporteVentas = new JButton("<html>VER\u00A0VENTAS<br>\u00A0Y DETALLES</html>");
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		btngenerarReporteVentas.setForeground(Color.WHITE);
		btngenerarReporteVentas.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btngenerarReporteVentas.setBackground(new Color(30, 144, 255));
		btngenerarReporteVentas.setBounds(339, 220, 260, 62);
		contentPane.add(btngenerarReporteVentas);

		cbUsuarios = new JComboBox();
		cbUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 15));
		cbUsuarios.setBounds(22, 106, 260, 33);
		contentPane.add(cbUsuarios);

		this.btnVerProductosIngresados = new JButton("Ver productos ingresados en esas fechas");
		this.btnVerProductosIngresados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerProductosIngresados(arg0);
			}
		});
		this.btnVerProductosIngresados.setForeground(Color.WHITE);
		this.btnVerProductosIngresados.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		this.btnVerProductosIngresados.setBackground(new Color(30, 144, 255));
		this.btnVerProductosIngresados.setBounds(25, 450, 574, 38);
		this.contentPane.add(this.btnVerProductosIngresados);

		this.calendar = new JDateChooser();
		this.calendar.setBounds(144, 160, 141, 32);
		this.contentPane.add(this.calendar);

		this.calendar_1 = new JDateChooser();
		this.calendar_1.setBounds(458, 160, 141, 32);
		this.contentPane.add(this.calendar_1);

		this.calendar_2 = new JDateChooser();
		this.calendar_2.setBounds(144, 411, 141, 32);
		this.contentPane.add(this.calendar_2);

		this.calendar_3 = new JDateChooser();
		this.calendar_3.setBounds(458, 411, 141, 32);
		this.contentPane.add(this.calendar_3);

		this.label = new JLabel("al:");
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		this.label.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		this.label.setBounds(339, 408, 55, 38);
		this.contentPane.add(this.label);

		this.lblRegistroDeIngresos = new JLabel("INGRESO DE PRODUCTOS");
		this.lblRegistroDeIngresos.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRegistroDeIngresos.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		this.lblRegistroDeIngresos.setBounds(10, 375, 598, 38);
		this.contentPane.add(this.lblRegistroDeIngresos);

		this.textField_4 = new JTextField();
		this.textField_4.setRequestFocusEnabled(false);
		this.textField_4.setIgnoreRepaint(true);
		this.textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField_4.setForeground(Color.ORANGE);
		this.textField_4.setFont(new Font("Dialog", Font.BOLD, 28));
		this.textField_4.setFocusable(false);
		this.textField_4.setFocusTraversalKeysEnabled(false);
		this.textField_4.setEditable(false);
		this.textField_4.setColumns(10);
		this.textField_4.setBackground(Color.DARK_GRAY);
		this.textField_4.setBounds(0, 659, 1253, 18);
		this.contentPane.add(this.textField_4);

		lblDel = new JLabel("del:");
		lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		lblDel.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		lblDel.setBounds(25, 160, 55, 38);
		contentPane.add(lblDel);

		lblDel_1 = new JLabel("del:");
		lblDel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		lblDel_1.setBounds(25, 408, 55, 38);
		contentPane.add(lblDel_1);

		cbMetodoPago = new JComboBox();
		cbMetodoPago.setFont(new Font("Segoe UI", Font.BOLD, 15));
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] { "Todos los metodos de pago", "Efectivo",
				"Dep\u00F3sito BCP", "Dep\u00F3sito BBVA", "Dep\u00F3sito INTERBANK", "Transferencia BCP",
				"Transferencia BBVA", "Transferencia INTERBANK", "Pago con tarjeta BCP", "Pago con tarjeta BBVA",
				"Pago con tarjeta INTERBANK", "CR\u00C9DITO" }));
		cbMetodoPago.setBounds(339, 106, 260, 33);
		contentPane.add(cbMetodoPago);

		lblComprasDeCliente = new JLabel("COMPRAS DE CLIENTE");
		lblComprasDeCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblComprasDeCliente.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblComprasDeCliente.setBounds(782, 327, 347, 38);
		contentPane.add(lblComprasDeCliente);

		btnVerComprasCliente = new JButton("Ver compras del cliente");
		btnVerComprasCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerComprasCliente(arg0);
			}
		});
		btnVerComprasCliente.setForeground(Color.WHITE);
		btnVerComprasCliente.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnVerComprasCliente.setBackground(new Color(30, 144, 255));
		btnVerComprasCliente.setBounds(786, 420, 314, 38);
		contentPane.add(btnVerComprasCliente);

		cbCliente = new JComboBox();
		cbCliente.setBounds(819, 373, 242, 33);
		contentPane.add(cbCliente);

		this.lblProductosAVencer = new JLabel("PRODUCTOS POR VENCER");
		this.lblProductosAVencer.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblProductosAVencer.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		this.lblProductosAVencer.setBounds(646, 505, 598, 38);
		this.contentPane.add(this.lblProductosAVencer);

		this.lblDesde = new JLabel("desde:");
		this.lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDesde.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		this.lblDesde.setBounds(661, 554, 109, 38);
		this.contentPane.add(this.lblDesde);

		this.btnVerProductosQue = new JButton("Ver productos que venceran en esas fechas");
		this.btnVerProductosQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerProductosQue(e);
			}
		});
		this.btnVerProductosQue.setForeground(Color.WHITE);
		this.btnVerProductosQue.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		this.btnVerProductosQue.setBackground(new Color(30, 144, 255));
		this.btnVerProductosQue.setBounds(661, 603, 574, 38);
		this.contentPane.add(this.btnVerProductosQue);

		this.calendar_4 = new JDateChooser();
		this.calendar_4.setBounds(780, 560, 141, 32);
		this.contentPane.add(this.calendar_4);

		this.lblHasta = new JLabel("hasta:");
		this.lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHasta.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		this.lblHasta.setBounds(975, 554, 109, 38);
		this.contentPane.add(this.lblHasta);

		this.calendar_5 = new JDateChooser();
		this.calendar_5.setBounds(1094, 560, 141, 32);
		this.contentPane.add(this.calendar_5);

		this.textField_5 = new JTextField();
		this.textField_5.setRequestFocusEnabled(false);
		this.textField_5.setIgnoreRepaint(true);
		this.textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField_5.setForeground(Color.ORANGE);
		this.textField_5.setFont(new Font("Dialog", Font.BOLD, 28));
		this.textField_5.setFocusable(false);
		this.textField_5.setFocusTraversalKeysEnabled(false);
		this.textField_5.setEditable(false);
		this.textField_5.setColumns(10);
		this.textField_5.setBackground(Color.DARK_GRAY);
		this.textField_5.setBounds(635, 487, 618, 18);
		this.contentPane.add(this.textField_5);

		cbCategoria = new JComboBox();
		cbCategoria.setBounds(972, 125, 242, 33);
		contentPane.add(cbCategoria);

		lblcategoria = new JLabel("<html>Categoria:</html>");
		lblcategoria.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblcategoria.setBounds(715, 125, 242, 31);
		contentPane.add(lblcategoria);

		this.textField_2 = new JTextField();
		this.textField_2.setRequestFocusEnabled(false);
		this.textField_2.setIgnoreRepaint(true);
		this.textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.textField_2.setForeground(Color.ORANGE);
		this.textField_2.setFont(new Font("Dialog", Font.BOLD, 28));
		this.textField_2.setFocusable(false);
		this.textField_2.setFocusTraversalKeysEnabled(false);
		this.textField_2.setEditable(false);
		this.textField_2.setColumns(10);
		this.textField_2.setBackground(Color.DARK_GRAY);
		this.textField_2.setBounds(0, 487, 618, 18);
		this.contentPane.add(this.textField_2);

		this.calendar_7 = new JDateChooser();
		this.calendar_7.setBounds(458, 577, 141, 32);
		this.contentPane.add(this.calendar_7);

		this.btnVerRanking = new JButton("Ver ranking");
		this.btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerRanking(arg0);
			}
		});
		this.btnVerRanking.setForeground(Color.WHITE);
		this.btnVerRanking.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		this.btnVerRanking.setBackground(new Color(30, 144, 255));
		this.btnVerRanking.setBounds(235, 615, 166, 38);
		this.contentPane.add(this.btnVerRanking);

		this.label_1 = new JLabel("al:");
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		this.label_1.setBounds(339, 574, 55, 38);
		this.contentPane.add(this.label_1);

		this.lblRankingDeProductos = new JLabel("RANKING DE PRODUCTOS");
		this.lblRankingDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRankingDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		this.lblRankingDeProductos.setBounds(10, 505, 598, 38);
		this.contentPane.add(this.lblRankingDeProductos);

		this.calendar_6 = new JDateChooser();
		this.calendar_6.setBounds(144, 577, 141, 32);
		this.contentPane.add(this.calendar_6);

		this.label_3 = new JLabel("del:");
		this.label_3.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_3.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		this.label_3.setBounds(25, 574, 55, 38);
		this.contentPane.add(this.label_3);

		this.cbxRanking = new JComboBox();
		this.cbxRanking.setModel(new DefaultComboBoxModel(new String[] {"M\u00C1S VENDIDOS", "MENOS VENDIDOS"}));
		this.cbxRanking.setToolTipText("");
		this.cbxRanking.setBounds(177, 539, 242, 33);
		this.contentPane.add(this.cbxRanking);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { this.cbUsuarios, this.btnGenerarRVD,
				this.btngenerarReporteVentas, this.txtNVenta, this.btnGenerarRVDetallada, this.txtMenores,
				this.btnGenerarMenores, this.txtMayores, this.btnGenerarMayores, this.btnCerrar }));

		cargar();
	}

	private void cargar() {

		if (v != null) {
			btnGenerarRVD.setEnabled(false);
			btnGenerarRVDetallada.setEnabled(false);
			btnVerProductosIngresados.setEnabled(false);
			btnGenerarMenores.setEnabled(false);
			btnGenerarMayores.setEnabled(false);
		}

		this.setLocationRelativeTo(null);

		Cliente cliente = new Cliente();
		cliente.cargarClientes(cbCliente);

		Categoria categoria = new Categoria();
		Categoria todaCategoria = new Categoria("TODAS");
		cbCategoria.addItem(todaCategoria);
		categoria.cargarCategorias(cbCategoria);

		Usuarios usu = new Usuarios();
		//Usuarios todos = new Usuarios("TODOS", "TODOS", "TODOS", 0);
		//cbUsuarios.addItem(todos);
		usu.cargarUsuarios(cbUsuarios);
		java.util.Date date = new Date();
		date.getTime();
		calendar.setDate(date);
		calendar_1.setDate(date);
		calendar_2.setDate(date);
		calendar_3.setDate(date);
		calendar_4.setDate(date);
		calendar_5.setDate(date);
		calendar_6.setDate(date);
		calendar_7.setDate(date);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnGenerarMayores) {
			actionPerformedBtnGenerarMayores(arg0);
		}
		if (arg0.getSource() == btnGenerarMenores) {
			btnGenerarCardexActionPerformed(arg0);
		}
		if (arg0.getSource() == btnGenerarRVDetallada) {
			btnGenerarRVDetalladaActionPerformed(arg0);
		}
		if (arg0.getSource() == btnCerrar) {
			btnCerrarActionPerformed(arg0);
		}
		if (arg0.getSource() == btnGenerarRVD) {
			btnGenerarActionPerformed(arg0);
		}
	}

	protected void btnGenerarActionPerformed(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getSelectedItem().toString();
			int metpago = cbMetodoPago.getSelectedIndex() - 1;

			int añoi = calendar.getCalendar().get(Calendar.YEAR);
			int mesi = calendar.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_1.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_1.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_1.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
			Map parameters = new HashMap();
			parameters.put("prtFechaI", fechai);
			parameters.put("prtFechaF", fechaf);
			parameters.put("metpago", metpago);

			/*
			 * new AbstractJasperReports().createReport(con, "rVentas.jasper",
			 * parameters); AbstractJasperReports.showViewer();
			 */

			if (usu.equals("TODOS")) {
				if (metpago == -1) {
					new AbstractJasperReports().createReport(con, "rVentasTodos.jasper", parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasVendedorTodosXMpago.jasper", parameters);
					AbstractJasperReports.showViewer();
				}
			} else {
				parameters.put("prmtVendedor", usu);
				if (metpago == -1) {
					new AbstractJasperReports().createReport(con, "rVentasVendedorTodoMetodoxUsuario.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();
				}
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + e);
		}
	}

	protected void actionPerformedBtngenerarReporteVentas(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getSelectedItem().toString();
			int metpago = cbMetodoPago.getSelectedIndex() - 1;

			int añoi = calendar.getCalendar().get(Calendar.YEAR);
			int mesi = calendar.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_1.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_1.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_1.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechai);
			java.sql.Timestamp timeStampDateI = new Timestamp(date.getTime());
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter2.parse(fechaf);
			java.sql.Timestamp timeStampDateF = new Timestamp(date2.getTime());

			Map parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			parameters.put("metpago", metpago);
			parameters.put("usu", usu);

			if (usu.equals("TODOS")) {
				if (metpago == -1) {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasTodos.jasper", parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedorTodosXMpago.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			} else {
				parameters.put("prmtVendedor", usu);
				if (metpago == -1) {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedorTodoMetodoxUsuario.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();
				}
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}

	protected void btnGenerarRVDetalladaActionPerformed(ActionEvent arg0) {
		if (txtNVenta.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
		else {
			Connection con = null;
			try {
				con = MySQLConexion.getConection();
				String nventa = txtNVenta.getText();
				Map<String, Object> parameters = new HashMap();
				parameters.put("prtCodVen", Integer.parseInt(nventa));
				new AbstractJasperReports().createReport(con, "rVentaDetalle.jasper", parameters);
				AbstractJasperReports.showViewer();
				con.close();
				txtNVenta.setText(null);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se encontó la venta " + e);
			}
		}
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			windowClosingThis(e);
		}
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	protected void windowClosingThis(WindowEvent e) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			try {
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy  HH.mm.ss");
				Date today = Calendar.getInstance().getTime();
				String reportDate = df.format(today);
				File directorio = new File("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA");
				directorio.mkdirs();
				Process p;
				p = Runtime.getRuntime().exec("mysqldump -u root -pAa123 db_inventario");
				InputStream is = p.getInputStream();
				FileOutputStream fos = new FileOutputStream(
						"D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA\\backup_inventario  " + reportDate + ".sql");
				byte[] buffer = new byte[1000];
				int leido = is.read(buffer);
				while (leido > 0) {
					fos.write(buffer, 0, leido);
					leido = is.read(buffer);
				}
				// JOptionPane.showMessageDialog(null, "Copia de segudidad
				// creada en: \n D:/ INFORMACION DEL SISTEMA / BACKUP_SISTEMA /
				// ");
				// JOptionPane.showMessageDialog(null, "Copia de segudidad
				// realizada correctamente");
				fos.close();
			} catch (IOException e1) {
				// JOptionPane.showMessageDialog(null, e1);
			}
			System.exit(0);
		} else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	protected void btnGenerarCardexActionPerformed(ActionEvent arg0) {
		if (txtMenores.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
		else {
			Connection con = null;
			try {
				if (!(cbCategoria.getSelectedItem().toString().equals("TODAS"))) {
					String categoria = cbCategoria.getSelectedItem().toString();

					con = MySQLConexion.getConection();
					float menores = Float.parseFloat(txtMenores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", menores);
					parameters.put("categoria", categoria);
					new AbstractJasperReports().createReport(con, "rCardexMenoresCategoria.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMenores.setText(null);
				} else {
					con = MySQLConexion.getConection();
					float menores = Float.parseFloat(txtMenores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", menores);
					new AbstractJasperReports().createReport(con, "rCardexMenores.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMenores.setText(null);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se encontraron productos " + e);
			}
		}
	}

	protected void actionPerformedBtnGenerarMayores(ActionEvent arg0) {
		if (txtMayores.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
		else {
			Connection con = null;
			try {
				if (!(cbCategoria.getSelectedItem().toString().equals("TODAS"))) {
					String categoria = cbCategoria.getSelectedItem().toString();
					con = MySQLConexion.getConection();
					float mayores = Float.parseFloat(txtMayores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", mayores);
					parameters.put("categoria", categoria);
					new AbstractJasperReports().createReport(con, "rCardexMayoresCategoria.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMayores.setText(null);
				} else {
					con = MySQLConexion.getConection();
					float mayores = Float.parseFloat(txtMayores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", mayores);
					new AbstractJasperReports().createReport(con, "rCardexMayores.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMayores.setText(null);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se encontraron productos " + e);
			}
		}
	}

	protected void btnCerrarActionPerformed(ActionEvent arg0) {
		if (v != null)
			v.setVisible(true);
		else {
			EleccionVentanas el = new EleccionVentanas(usuario);
			el.setVisible(true);
		}
		dispose();
	}

	private void cortar(PrintWriter ps) {

		try {
			char[] ESC_CUT_PAPER = new char[] { 0x1B, 'm' };
			ps.write(ESC_CUT_PAPER);

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtNVenta) {
			keyTypedTxtNVenta(arg0);
		}
		if (arg0.getSource() == txtMayores) {
			keyTypedTxtMayores(arg0);
		}
		if (arg0.getSource() == txtMenores) {
			keyTypedTxtMenores(arg0);
		}
	}

	protected void keyTypedTxtMenores(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)) {
			e.consume();
			if (c == (char) KeyEvent.VK_ENTER) {
				btnGenerarCardexActionPerformed(null);
			}
		}
	}

	protected void keyTypedTxtMayores(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)) {
			e.consume();
			if (c == (char) KeyEvent.VK_ENTER) {
				actionPerformedBtnGenerarMayores(null);
			}
		}
	}

	protected void keyTypedTxtNVenta(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)) {
			e.consume();
			if (c == (char) KeyEvent.VK_ENTER) {
				btnGenerarRVDetalladaActionPerformed(null);
			}
		}
	}

	/*
	 * protected void actionPerformedBtnImprimir(ActionEvent arg0) { if
	 * (txtNVentaCopia.getText().equals("") || txtNCopias.getText().equals(""))
	 * JOptionPane.showMessageDialog(null, "Llene los campos correctamente");
	 * else { int copias = Integer.parseInt(txtNCopias.getText()); int
	 * confirmacion = JOptionPane.showConfirmDialog(null, "Imprimirá: " + copias
	 * + " copias.\n ¿Continuar?", "Alerta", JOptionPane.YES_NO_OPTION); if
	 * (confirmacion == 0) { for (int i = 0; i < copias; i++) { Connection con =
	 * null; try { con = MySQLConexion.getConection(); Map<String, Object>
	 * parameters = new HashMap(); parameters.put("prtNVenta",
	 * Integer.parseInt(txtNVentaCopia.getText()));
	 * 
	 * new AbstractJasperReports().createReport( con.getConn(),
	 * "rPrueba.jasper", null); AbstractJasperReports.showViewer();
	 * 
	 * try { JasperReport reporte = (JasperReport) JRLoader
	 * .loadObjectFromFile("bin/rComprobante.jasper"); JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(reporte, parameters, con);
	 * JasperPrintManager.printReport(jasperPrint, false); } catch (JRException
	 * ex) { System.err.println("Error iReport: " + ex.getMessage()); }
	 * con.close();
	 * 
	 * } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR " +
	 * e); } } txtNCopias.setText("1"); txtNCopias.requestFocusInWindow();
	 * txtNVentaCopia.setText(null); } } }
	 */
	protected void actionPerformedBtnVerProductosIngresados(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getSelectedItem().toString();

			int añoi = calendar_2.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_2.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_2.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_3.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_3.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_3.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
			Map parameters = new HashMap();
			parameters.put("prtFechaI", fechai);
			parameters.put("prmtFechaF", fechaf);

			/*
			 * new AbstractJasperReports().createReport(con, "rVentas.jasper",
			 * parameters); AbstractJasperReports.showViewer();
			 */
			new AbstractJasperReports().createReport(con, "rFechaIngreso.jasper", parameters);
			AbstractJasperReports.showViewer();
			con.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}

	protected void actionPerformedBtnVerProductosQue(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = calendar_4.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_4.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_4.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_5.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_5.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_5.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
			Map parameters = new HashMap();
			parameters.put("prtFechaI", fechai);
			parameters.put("prmtFechaF", fechaf);

			new AbstractJasperReports().createReport(con, "rFechaVencer.jasper", parameters);
			AbstractJasperReports.showViewer();
			con.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}

	protected void actionPerformedBtnVerComprasCliente(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			String nrodoc = cbCliente.getItemAt(cbCliente.getSelectedIndex()).getNrodoc();

			Map<String, Object> parameters = new HashMap();
			parameters.put("prtNroDoc", nrodoc);
			new AbstractJasperReports().createReport(con, "rComprasCliente.jasper", parameters);
			AbstractJasperReports.showViewer();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontó la venta " + e);
		}

	}

	protected void actionPerformedBtnVerRanking(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = calendar_6.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_6.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_6.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_7.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_7.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_7.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
			Map parameters = new HashMap();
			parameters.put("prtFechaI", fechai);
			parameters.put("prmtFechaFI", fechaf);

			if (cbxRanking.getSelectedIndex() == 0) {
				new AbstractJasperReports().createReport(con, "rProductoMasVendidos.jasper", parameters);
				AbstractJasperReports.showViewer();
			} else {
				new AbstractJasperReports().createReport(con, "rProductoMenosVendidos.jasper", parameters);
				AbstractJasperReports.showViewer();
			}

			con.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}
}
