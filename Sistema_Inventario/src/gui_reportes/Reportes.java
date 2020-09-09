package gui_reportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;

import clases.AbstractJasperReports;
import clases.Categoria;
import clases.Cliente;
import clases.Distribuidores;
import clases.Marcas;
import clases.Usuarios;
import gui_principal.VentanaPrincipal;
import mysql.MySQLConexion;
import mysql.consultas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Box;

public class Reportes extends JInternalFrame implements ActionListener {
	private JComboBox <Usuarios> cbUsuarios;
	private JComboBox cbMetodoPago;
	private JLabel label_1;
	private JDateChooser calendar;
	private JLabel label_2;
	private JDateChooser calendar_1;
	private JButton btngenerarReporteVentas;
	private JLabel lblDesde;
	private JLabel lblRanking;
	private JComboBox cbxRanking;
	private JLabel label_8;
	private JDateChooser calendar_6;
	private JLabel lblHasta;
	private JDateChooser calendar_7;
	private JButton btnVerRanking;
	private JLabel lblCategora;
	private JTextField txtMenores;
	private JButton btnGenerarMenoresMayores;
	private JLabel lblHistorialDeCompras;
	private JButton btnVerComprasCliente;
	private JLabel lblPorVencer;
	private JLabel lblDel;
	private JDateChooser calendar_4;
	private JButton btnVerProductosQue;
	private JLabel label_17;
	private JDateChooser calendar_5;
	private JPanel panel;
	private JLabel lblVendedor;
	private JLabel lblMtodoDePago;
	private JPanel panel_1;
	private JLabel lblVentas;
	private JComboBox<Categoria> cbCategoria;
	private JComboBox<Categoria> cbStockCategoria;
	private JComboBox<Cliente> cbCliente;
	private JComboBox<Marcas> cbMarca;
	private JComboBox<Distribuidores> cbStockProveedor;
	private JComboBox<Categoria> cbVentaCategoria;
	private JComboBox<Categoria> cbCompraCategoria;
	private JComboBox<Distribuidores> cbCompraProveedor;
	


	private TextAutoCompleter ac1;
	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JLabel lblProductos;
	private JLabel lblFiltros;
	private JCheckBox chckbxRestriccionCantidad;
	private JCheckBox chckbxMenorA;
	private JLabel lblMarca;
	private JPanel panel_2;
	private JLabel lblReporteDeCompras;
	private JLabel label;
	private JDateChooser calRI01;
	private JLabel label_3;
	private JDateChooser calRI02;
	private JButton button;
	private JLabel lblPorProducto;
	private JButton btnPorProducto;
	private JTextField txtProductos;
	private JPanel panel_3;
	private JLabel lblImprimirTicket;
	private JLabel lblTicketNro;
	private JButton btnImprimirCopia;
	private JTextField txtNroTicket;
	private JButton btnGenerarStockCategoriaProveedor;
	private JTextField txtStockMenores;
	private JPanel panel_5;
	private JLabel lblPorCategoria;
	
	private JLabel label_7;
	private JDateChooser calendar_8;
	private JLabel label_9;
	private JDateChooser calendar_9;
	private JButton btngenerarReporteVentaCategoria;
	private JPanel panel_6;
	private JLabel lblReporteCompra;
	private JLabel lblProveedor_1;
	private JLabel label_11;
	private JDateChooser calendar_10;
	private JLabel label_12;
	private JDateChooser calendar_11;
	private JButton btnReporteCompraCategoriaProveedor;
	private JLabel lblCategoria;
	private JPanel panel_7;
	private JLabel lblMejorVendedor;
	private JLabel label_6;
	private JDateChooser calRI03;
	private JLabel label_10;
	private JDateChooser calRI04;
	private JButton btnMejorVendedor;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes(null);
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
	public Reportes(VentanaPrincipal vp) {
		getContentPane().setBackground(Color.DARK_GRAY);
		this.vp = vp;
		
		setBounds(100, 100, 1134, 669);
		getContentPane().setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setBackground(new Color(219, 112, 147));
		this.panel.setBounds(0, 0, 557, 502);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblVendedor = new JLabel("POR VENDEDOR:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(16, 85, 157, 23);
		this.panel.add(this.lblVendedor);
		
		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(183, 85, 109, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		this.cbMetodoPago.setBounds(183, 118, 109, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(16, 119, 157, 23);
		this.panel.add(this.lblMtodoDePago);
		
		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(102, 153, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar = new JDateChooser();
		this.calendar.setBounds(167, 153, 125, 23);
		this.panel.add(this.calendar);
		
		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(102, 187, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_1 = new JDateChooser();
		this.calendar_1.setBounds(167, 187, 125, 23);
		this.panel.add(this.calendar_1);
		
		this.btngenerarReporteVentas = new JButton("Ver reporte");
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(70, 221, 179, 32);
		this.panel.add(this.btngenerarReporteVentas);
		this.btngenerarReporteVentas.setForeground(Color.WHITE);
		this.btngenerarReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentas.setBackground(new Color(30, 144, 255));
		
		this.lblVentas = new JLabel("REPORTE DE VENTAS");
		this.lblVentas.setBounds(16, 20, 535, 32);
		this.panel.add(this.lblVentas);
		this.lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVentas.setFont(new Font("Candara", Font.BOLD, 30));
		
		this.lblHistorialDeCompras = new JLabel("POR CLIENTE:");
		lblHistorialDeCompras.setBounds(70, 282, 144, 23);
		panel.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.cbCliente = new JComboBox();
		cbCliente.setBounds(16, 305, 255, 23);
		panel.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.btnVerComprasCliente = new JButton("Ver reporte");
		btnVerComprasCliente.setBounds(16, 339, 255, 32);
		panel.add(btnVerComprasCliente);
		btnVerComprasCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerComprasCliente(e);
			}
		});
		this.btnVerComprasCliente.setForeground(Color.WHITE);
		this.btnVerComprasCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerComprasCliente.setBackground(new Color(30, 144, 255));
		
		lblPorProducto = new JLabel("POR PRODUCTO:");
		lblPorProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPorProducto.setFont(new Font("Candara", Font.BOLD, 20));
		lblPorProducto.setBounds(70, 402, 166, 23);
		panel.add(lblPorProducto);
		
		btnPorProducto = new JButton("Ver reporte");
		btnPorProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnPorProducto(arg0);
			}
		});
		btnPorProducto.setForeground(Color.WHITE);
		btnPorProducto.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPorProducto.setBackground(new Color(30, 144, 255));
		btnPorProducto.setBounds(16, 459, 255, 32);
		panel.add(btnPorProducto);
		
		txtProductos = new JTextField();
		txtProductos.setHorizontalAlignment(SwingConstants.LEFT);
		txtProductos.setFont(new Font("Arial", Font.PLAIN, 16));
		txtProductos.setColumns(10);
		txtProductos.setBackground(SystemColor.controlHighlight);
		txtProductos.setBounds(16, 425, 255, 23);
		panel.add(txtProductos);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBackground(Color.BLACK);
		this.panel_5.setBounds(302, 84, 2, 177);
		this.panel.add(this.panel_5);
		
		this.lblPorCategoria = new JLabel("POR CATEGORIA:");
		this.lblPorCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPorCategoria.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblPorCategoria.setBounds(314, 85, 157, 23);
		this.panel.add(this.lblPorCategoria);
		
		this.cbVentaCategoria = new JComboBox();
		this.cbVentaCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbVentaCategoria.setBounds(314, 118, 152, 23);
		this.panel.add(this.cbVentaCategoria);
		
		this.label_7 = new JLabel("del:");
		this.label_7.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_7.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_7.setBounds(314, 153, 46, 23);
		this.panel.add(this.label_7);
		
		this.calendar_8 = new JDateChooser();
		this.calendar_8.setBounds(379, 153, 125, 23);
		this.panel.add(this.calendar_8);
		
		this.label_9 = new JLabel("al:");
		this.label_9.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_9.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_9.setBounds(314, 187, 55, 23);
		this.panel.add(this.label_9);
		
		this.calendar_9 = new JDateChooser();
		this.calendar_9.setBounds(379, 187, 125, 23);
		this.panel.add(this.calendar_9);
		
		this.btngenerarReporteVentaCategoria = new JButton("Ver reporte");
		this.btngenerarReporteVentaCategoria.addActionListener(this);
		this.btngenerarReporteVentaCategoria.setForeground(Color.WHITE);
		this.btngenerarReporteVentaCategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentaCategoria.setBackground(new Color(30, 144, 255));
		this.btngenerarReporteVentaCategoria.setBounds(339, 221, 179, 32);
		this.panel.add(this.btngenerarReporteVentaCategoria);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBackground(Color.BLACK);
		this.panel_6.setBounds(281, 268, 2, 223);
		this.panel.add(this.panel_6);
		
		this.lblReporteCompra = new JLabel("REPORTE Compra");
		this.lblReporteCompra.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblReporteCompra.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblReporteCompra.setBounds(361, 271, 157, 23);
		this.panel.add(this.lblReporteCompra);
		
		this.cbCompraCategoria = new JComboBox();
		this.cbCompraCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCompraCategoria.setBounds(442, 305, 109, 23);
		this.panel.add(this.cbCompraCategoria);
		
		this.lblProveedor_1 = new JLabel("Proveedor");
		this.lblProveedor_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblProveedor_1.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblProveedor_1.setBounds(289, 339, 100, 23);
		this.panel.add(this.lblProveedor_1);
		
		this.cbCompraProveedor = new JComboBox();
		this.cbCompraProveedor.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCompraProveedor.setBounds(442, 338, 109, 23);
		this.panel.add(this.cbCompraProveedor);
		
		this.label_11 = new JLabel("del:");
		this.label_11.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_11.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_11.setBounds(329, 383, 46, 23);
		this.panel.add(this.label_11);
		
		this.calendar_10 = new JDateChooser();
		this.calendar_10.setBounds(394, 383, 125, 23);
		this.panel.add(this.calendar_10);
		
		this.label_12 = new JLabel("al:");
		this.label_12.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_12.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_12.setBounds(329, 417, 55, 23);
		this.panel.add(this.label_12);
		
		this.calendar_11 = new JDateChooser();
		this.calendar_11.setBounds(394, 417, 125, 23);
		this.panel.add(this.calendar_11);
		
		this.btnReporteCompraCategoriaProveedor = new JButton("Ver reporte");
		this.btnReporteCompraCategoriaProveedor.addActionListener(this);
		this.btnReporteCompraCategoriaProveedor.setForeground(Color.WHITE);
		this.btnReporteCompraCategoriaProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnReporteCompraCategoriaProveedor.setBackground(new Color(30, 144, 255));
		this.btnReporteCompraCategoriaProveedor.setBounds(329, 459, 179, 32);
		this.panel.add(this.btnReporteCompraCategoriaProveedor);
		
		this.lblCategoria = new JLabel("Categoria");
		this.lblCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblCategoria.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblCategoria.setBounds(289, 305, 100, 23);
		this.panel.add(this.lblCategoria);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(255, 222, 173));
		this.panel_1.setBounds(561, 0, 568, 502);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(280, 64, 2, 187);
		panel_1.add(panel_4);
		
		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(16, 93, 99, 23);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.txtMenores = new JTextField();
		txtMenores.setEnabled(false);
		this.txtMenores.setBounds(134, 185, 136, 23);
		this.panel_1.add(this.txtMenores);
		this.txtMenores.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtMenores.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtMenores.setColumns(10);
		this.txtMenores.setBackground(SystemColor.controlHighlight);
		
		this.btnGenerarMenoresMayores = new JButton("Ver reporte");
		btnGenerarMenoresMayores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnGenerarMenoresMayores.setBounds(134, 219, 136, 32);
		this.panel_1.add(this.btnGenerarMenoresMayores);
		this.btnGenerarMenoresMayores.setForeground(Color.WHITE);
		this.btnGenerarMenoresMayores.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarMenoresMayores.setBackground(new Color(30, 144, 255));
		
		this.cbCategoria = new JComboBox();
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCategoria.setBounds(134, 92, 136, 23);
		this.panel_1.add(this.cbCategoria);
		
		this.lblDel = new JLabel("desde:");
		lblDel.setBounds(16, 295, 77, 23);
		panel_1.add(lblDel);
		this.lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDel.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_4 = new JDateChooser();
		calendar_4.setBounds(84, 295, 141, 23);
		panel_1.add(calendar_4);
		
		this.lblPorVencer = new JLabel("CON FECHA DE VENCIMIENTO:");
		lblPorVencer.setBounds(16, 273, 308, 23);
		panel_1.add(lblPorVencer);
		this.lblPorVencer.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPorVencer.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.label_17 = new JLabel("hasta:");
		label_17.setBounds(264, 295, 77, 23);
		panel_1.add(label_17);
		this.label_17.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_17.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_5 = new JDateChooser();
		calendar_5.setBounds(334, 295, 141, 23);
		panel_1.add(calendar_5);
		
		this.btnVerProductosQue = new JButton("Ver reporte");
		btnVerProductosQue.setBounds(16, 329, 466, 32);
		panel_1.add(btnVerProductosQue);
		btnVerProductosQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerProductosQue(e);
			}
		});
		this.btnVerProductosQue.setForeground(Color.WHITE);
		this.btnVerProductosQue.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerProductosQue.setBackground(new Color(30, 144, 255));
		
		lblProductos = new JLabel("REPORTE DE PRODUCTOS");
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setFont(new Font("Candara", Font.BOLD, 30));
		lblProductos.setBounds(10, 21, 535, 32);
		panel_1.add(lblProductos);
		
		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setForeground(new Color(0, 0, 205));
		lblFiltros.setFont(new Font("Candara", Font.BOLD, 25));
		lblFiltros.setBounds(16, 64, 143, 23);
		panel_1.add(lblFiltros);
		
		chckbxRestriccionCantidad = new JCheckBox(" Sin restricci\u00F3n de cantidad");
		chckbxRestriccionCantidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedChckbxRestriccionCantidad(e);
			}
		});
		chckbxRestriccionCantidad.setSelected(true);
		chckbxRestriccionCantidad.setFont(new Font("Candara", Font.BOLD, 20));
		chckbxRestriccionCantidad.setBackground(new Color(255, 222, 173));
		chckbxRestriccionCantidad.setBounds(16, 159, 295, 23);
		panel_1.add(chckbxRestriccionCantidad);
		
		chckbxMenorA = new JCheckBox("Stock < a:");
		chckbxMenorA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedChckbxMenorA(e);
			}
		});
		chckbxMenorA.setFont(new Font("Candara", Font.BOLD, 20));
		chckbxMenorA.setBackground(new Color(255, 222, 173));
		chckbxMenorA.setBounds(16, 185, 115, 23);
		panel_1.add(chckbxMenorA);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(16, 125, 99, 23);
		panel_1.add(lblMarca);
		
		cbMarca = new JComboBox();
		cbMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMarca.setBounds(135, 124, 135, 23);
		panel_1.add(cbMarca);
		
		this.lblRanking = new JLabel("RANKING de productos:");
		lblRanking.setBounds(16, 379, 231, 32);
		panel_1.add(lblRanking);
		this.lblRanking.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblRanking.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.cbxRanking = new JComboBox();
		cbxRanking.setBounds(251, 379, 231, 32);
		panel_1.add(cbxRanking);
		this.cbxRanking.setFont(new Font("Candara", Font.BOLD, 20));
		this.cbxRanking.setModel(new DefaultComboBoxModel(new String[] {"M\u00E1s vendidos", "Menos vendidos"}));
		this.cbxRanking.setToolTipText("");
		
		this.label_8 = new JLabel("del:");
		label_8.setBounds(-45, 488, 55, 23);
		panel_1.add(label_8);
		this.label_8.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_8.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_6 = new JDateChooser();
		calendar_6.setBounds(84, 418, 141, 23);
		panel_1.add(calendar_6);
		
		this.btnVerRanking = new JButton("Ver reporte");
		btnVerRanking.setBounds(16, 452, 473, 32);
		panel_1.add(btnVerRanking);
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerRanking(e);
			}
		});
		this.btnVerRanking.setForeground(Color.WHITE);
		this.btnVerRanking.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerRanking.setBackground(new Color(30, 144, 255));
		
		this.calendar_7 = new JDateChooser();
		calendar_7.setBounds(334, 418, 148, 23);
		panel_1.add(calendar_7);
		
		this.lblHasta = new JLabel("hasta:");
		lblHasta.setBounds(264, 425, 77, 16);
		panel_1.add(lblHasta);
		this.lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHasta.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblDesde = new JLabel("desde:");
		lblDesde.setBounds(20, 417, 73, 24);
		panel_1.add(lblDesde);
		this.lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDesde.setFont(new Font("Candara", Font.BOLD, 20));
		
		JLabel label_4 = new JLabel("FILTROS:");
		label_4.setForeground(new Color(0, 0, 205));
		label_4.setFont(new Font("Candara", Font.BOLD, 25));
		label_4.setBounds(292, 64, 143, 23);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Categor\u00EDa:");
		label_5.setFont(new Font("Candara", Font.BOLD, 20));
		label_5.setBounds(292, 93, 99, 23);
		panel_1.add(label_5);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setFont(new Font("Candara", Font.BOLD, 20));
		lblProveedor.setBounds(292, 125, 99, 23);
		panel_1.add(lblProveedor);
		
		btnGenerarStockCategoriaProveedor = new JButton("Ver reporte");
		btnGenerarStockCategoriaProveedor.addActionListener(this);
		btnGenerarStockCategoriaProveedor.setForeground(Color.WHITE);
		btnGenerarStockCategoriaProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGenerarStockCategoriaProveedor.setBackground(new Color(30, 144, 255));
		btnGenerarStockCategoriaProveedor.setBounds(410, 219, 136, 32);
		panel_1.add(btnGenerarStockCategoriaProveedor);
		
		JLabel lblStock = new JLabel("Stock < a:");
		lblStock.setFont(new Font("Candara", Font.BOLD, 20));
		lblStock.setBounds(292, 186, 99, 23);
		panel_1.add(lblStock);
		
		this.cbStockCategoria = new JComboBox();
		this.cbStockCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbStockCategoria.setBounds(410, 92, 135, 23);
		this.panel_1.add(this.cbStockCategoria);
		
		this.cbStockProveedor = new JComboBox();
		this.cbStockProveedor.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbStockProveedor.setBounds(410, 127, 135, 23);
		this.panel_1.add(this.cbStockProveedor);
		
		this.txtStockMenores = new JTextField();
		this.txtStockMenores.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtStockMenores.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtStockMenores.setColumns(10);
		this.txtStockMenores.setBackground(SystemColor.controlHighlight);
		this.txtStockMenores.setBounds(409, 188, 136, 23);
		this.panel_1.add(this.txtStockMenores);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(147, 112, 219));
		panel_2.setBounds(0, 508, 734, 131);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblReporteDeCompras = new JLabel("REPORTE DE INGRESOS");
		lblReporteDeCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeCompras.setFont(new Font("Candara", Font.BOLD, 30));
		lblReporteDeCompras.setBounds(10, 11, 319, 32);
		panel_2.add(lblReporteDeCompras);
		
		label = new JLabel("del:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBounds(10, 50, 46, 23);
		panel_2.add(label);
		
		calRI01 = new JDateChooser();
		calRI01.setBounds(47, 50, 125, 23);
		panel_2.add(calRI01);
		
		label_3 = new JLabel("al:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Candara", Font.BOLD, 20));
		label_3.setBounds(182, 50, 55, 23);
		panel_2.add(label_3);
		
		calRI02 = new JDateChooser();
		calRI02.setBounds(213, 50, 125, 23);
		panel_2.add(calRI02);
		
		button = new JButton("Ver reporte");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton(e);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(10, 84, 329, 32);
		panel_2.add(button);
		
		this.panel_7 = new JPanel();
		this.panel_7.setBackground(Color.BLACK);
		this.panel_7.setBounds(348, 11, 2, 109);
		this.panel_2.add(this.panel_7);
		
		this.lblMejorVendedor = new JLabel("MEJOR VENDEDOR");
		this.lblMejorVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblMejorVendedor.setFont(new Font("Candara", Font.BOLD, 30));
		this.lblMejorVendedor.setBounds(380, 11, 319, 32);
		this.panel_2.add(this.lblMejorVendedor);
		
		this.label_6 = new JLabel("del:");
		this.label_6.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_6.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_6.setBounds(370, 50, 46, 23);
		this.panel_2.add(this.label_6);
		
		this.calRI03 = new JDateChooser();
		this.calRI03.setBounds(407, 50, 125, 23);
		this.panel_2.add(this.calRI03);
		
		this.label_10 = new JLabel("al:");
		this.label_10.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_10.setFont(new Font("Candara", Font.BOLD, 20));
		this.label_10.setBounds(542, 50, 55, 23);
		this.panel_2.add(this.label_10);
		
		this.calRI04 = new JDateChooser();
		this.calRI04.setBounds(573, 50, 125, 23);
		this.panel_2.add(this.calRI04);
		
		this.btnMejorVendedor = new JButton("Ver reporte");
		this.btnMejorVendedor.addActionListener(this);
		this.btnMejorVendedor.setForeground(Color.WHITE);
		this.btnMejorVendedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnMejorVendedor.setBackground(new Color(30, 144, 255));
		this.btnMejorVendedor.setBounds(370, 84, 329, 32);
		this.panel_2.add(this.btnMejorVendedor);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(147, 112, 219));
		panel_3.setBounds(736, 508, 382, 131);
		getContentPane().add(panel_3);
		
		lblImprimirTicket = new JLabel("IMPRIMIR COPIA DE TICKET");
		lblImprimirTicket.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimirTicket.setFont(new Font("Candara", Font.BOLD, 30));
		lblImprimirTicket.setBounds(0, 11, 380, 32);
		panel_3.add(lblImprimirTicket);
		
		lblTicketNro = new JLabel("Ticket Nro: ");
		lblTicketNro.setHorizontalAlignment(SwingConstants.LEFT);
		lblTicketNro.setFont(new Font("Candara", Font.BOLD, 20));
		lblTicketNro.setBounds(65, 55, 112, 23);
		panel_3.add(lblTicketNro);
		
		btnImprimirCopia = new JButton("IMPRIMIR");
		btnImprimirCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnImprimirCopia(e);
			}
		});
		btnImprimirCopia.setForeground(Color.WHITE);
		btnImprimirCopia.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnImprimirCopia.setBackground(new Color(30, 144, 255));
		btnImprimirCopia.setBounds(4, 84, 361, 32);
		panel_3.add(btnImprimirCopia);
		
		txtNroTicket = new JTextField();
		txtNroTicket.setHorizontalAlignment(SwingConstants.LEFT);
		txtNroTicket.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNroTicket.setColumns(10);
		txtNroTicket.setBackground(SystemColor.controlHighlight);
		txtNroTicket.setBounds(185, 54, 136, 23);
		panel_3.add(txtNroTicket);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
	}

		private void cargar() {
			Cliente cliente = new Cliente();
			cliente.cargarClientes(cbCliente);

			Categoria categoria = new Categoria();
			Categoria todaCategoria = new Categoria("TODAS");
			cbCategoria.addItem(todaCategoria);
			categoria.cargarCategorias(cbCategoria);
			
			Categoria categoria2 = new Categoria();
			Categoria todaCategoria2 = new Categoria("TODAS");
			cbStockCategoria.addItem(todaCategoria2);
			categoria2.cargarCategorias(cbStockCategoria);
			
			Categoria categoria3 = new Categoria();
			Categoria todaCategoria3 = new Categoria("TODAS");
			cbVentaCategoria.addItem(todaCategoria3);
			categoria3.cargarCategorias(cbVentaCategoria);
			
			Categoria categoria4 = new Categoria();
			Categoria todaCategoria4 = new Categoria("TODAS");
			cbCompraCategoria.addItem(todaCategoria4);
			categoria4.cargarCategorias(cbCompraCategoria);
			
			Distribuidores distribuidor0 = new Distribuidores();
			Distribuidores distribuidor1 = new Distribuidores(-1, "0", "0", "TODAS", "0", "0", "0", "0");
			cbStockProveedor.addItem(distribuidor1);
			distribuidor0.cargarDistribuidores(cbStockProveedor);
			
			Distribuidores distribuidor2 = new Distribuidores();
			Distribuidores distribuidor3 = new Distribuidores(-1, "0", "0", "TODAS", "0", "0", "0", "0");
			cbCompraProveedor.addItem(distribuidor3);
			distribuidor2.cargarDistribuidores(cbCompraProveedor);
			
			Marcas marca = new Marcas();
			Marcas todaMarca = new Marcas("TODAS");
			cbMarca.addItem(todaMarca);
			marca.cargarMarcas(cbMarca);

			
			Usuarios usu = new Usuarios();
			Usuarios todos = new Usuarios(0, "TODOS", "TODOS", "TODOS", 0);
			cbUsuarios.addItem(todos);
			usu.cargarUsuarios(cbUsuarios);
			java.util.Date date = new Date();
			date.getTime();
			calendar.setDate(date);
			calendar_1.setDate(date);
			calendar_4.setDate(date);
			calendar_5.setDate(date);
			calendar_6.setDate(date);
			calendar_7.setDate(date);
			calendar_8.setDate(date);
			calendar_9.setDate(date);
			calendar_10.setDate(date);
			calendar_11.setDate(date);
			
			
			calRI01.setDate(date);
			calRI02.setDate(date);
			calRI03.setDate(date);
			calRI04.setDate(date);
	}
		
	public void cargarBuscador()
	  {
	    ac1 = new TextAutoCompleter(txtProductos);
	    consultas consulta = new consultas();

	    ac1.setMode(0);
	    consulta.iniciar();
	    ResultSet rs = consulta.cargarProductos();
	    try {
	    	while (rs.next()){
		    	  ac1.addItem(rs.getString("cantidad") + " " + rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
						"  -  (" + rs.getString("codproducto") + ")");
		    	  }
	    } catch (Exception e) {
	      JOptionPane.showMessageDialog(null, "ERROR al cargar txtProductos: " + e);
	    }
	    consulta.reset();
	  }
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}
	
	
	protected void actionPerformedBtngenerarReporteVentas(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex()- 1;
			
			
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
			double totalVenta = 0;
			
			try {
				consulta.iniciar();
				if(cbUsuarios.getSelectedIndex() == 0)
					rs = consulta.cargarVentasUsuarioTodos(fechai, fechaf);
				else
					rs = consulta.cargarVentasUsuario(idusuario, fechai, fechaf);
				
				while(rs.next()){
					totalVenta = totalVenta + rs.getFloat("totventa");
				}
				
				totalVenta = redondearDecimales(totalVenta, 2);
				
			
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
			Map parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			parameters.put("metpago", metpago);

			if (usu.equals("TODOS")) {
				if (metpago == - 1) {
					parameters.put("totalVenta", ""+totalVenta);
					new AbstractJasperReports().createReport(con, "rVentasDetalladasTodos.jasper", parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedorTodosXMpago.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			} else {
				parameters.put("prmtVendedor", usu);
				if (metpago == - 1) {
					parameters.put("totalVenta", ""+totalVenta);
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();
					
				} else {
					new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedorTodoMetodoxUsuario.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}
	
	protected void actionPerformedBtngenerarReporteVentaCategoria(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();			
			String categoria = cbVentaCategoria.getSelectedItem().toString();
			
			int añoi = calendar_8.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_8.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_8.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_9.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_9.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_9.getCalendar().get(Calendar.DAY_OF_MONTH);
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
			//parameters.put("prtFechaI", timeStampDateI);
			//parameters.put("prtFechaF", timeStampDateF);
			
			if ( categoria.equals("TODAS")) {				
				new AbstractJasperReports().createReport(con, "rVentasDetalladasCategoryAll.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else {
				parameters.put("prmtCategoria", categoria);
				
				new AbstractJasperReports().createReport(con, "rVentasDetalladasCategory.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			
			con.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
		}
	}
	
	protected void actionPerformedBtnVerRanking(ActionEvent e) {
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
	protected void actionPerformedBtnVerComprasCliente(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			String nrodoc = cbCliente.getItemAt(cbCliente.getSelectedIndex()).getNrodoc();

			Map<String, Object> parameters = new HashMap();
			parameters.put("prtNroDoc", nrodoc);
			new AbstractJasperReports().createReport(con, "rComprasCliente.jasper", parameters);
			AbstractJasperReports.showViewer();
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se enconta la venta " + ex);
		}
	}
	protected void actionPerformedBtnGenerarMenores(ActionEvent e) {
		
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			
			String categoria = cbCategoria.getSelectedItem().toString();
			String marca = cbMarca.getSelectedItem().toString();
			float cantidad = 9999999;
			
			if(chckbxMenorA.isSelected())
				cantidad = Float.parseFloat(txtMenores.getText());
			else
				cantidad = 9999999;
			
			if ( categoria.equals("TODAS") && marca.equals("TODAS")) {
				
				Map<String, Object> parameters = new HashMap();
				parameters.put("cantidad", cantidad);
				
				new AbstractJasperReports().createReport(con, "rCardexTodos.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if( categoria.equals("TODAS") && !(marca.equals("TODAS")) ){
				Map<String, Object> parameters = new HashMap();
				
				parameters.put("marca", marca);
				parameters.put("cantidad", cantidad);
				
				new AbstractJasperReports().createReport(con, "rCardexMarca.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if ( !(categoria.equals("TODAS")) && marca.equals("TODAS") ){
				Map<String, Object> parameters = new HashMap();
				
				parameters.put("categoria", categoria);
				parameters.put("cantidad", cantidad);
				
				new AbstractJasperReports().createReport(con, "rCardexCategoria.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if ( !(categoria.equals("TODAS")) && !(marca.equals("TODAS")) ){
				Map<String, Object> parameters = new HashMap();
				
				parameters.put("categoria", categoria);
				parameters.put("marca", marca);
				parameters.put("cantidad", cantidad);
				
				new AbstractJasperReports().createReport(con, "rCardexCategoriaMarca.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			
			con.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
		}
	
		
		/*
		  if (txtMayoresMenores.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
		else {
			Connection con = null;
			try {
				if (!(cbCategoria.getSelectedItem().toString().equals("TODAS"))) {
					String categoria = cbCategoria.getSelectedItem().toString();

					con = MySQLConexion.getConection();
					float menoresmayores = Float.parseFloat(txtMayoresMenores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", menoresmayores);
					parameters.put("categoria", categoria);
					new AbstractJasperReports().createReport(con, "rCardexMayoresCategoria.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMayoresMenores.setText(null);
				} else {
					con = MySQLConexion.getConection();
					float menoresmayores = Float.parseFloat(txtMayoresMenores.getText());
					Map<String, Object> parameters = new HashMap();
					parameters.put("prtcantidad", menoresmayores);
					new AbstractJasperReports().createReport(con, "rCardexmayores.jasper", parameters);
					AbstractJasperReports.showViewer();
					con.close();
					txtMayoresMenores.setText(null);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
			}
		}
		 */
	}

	protected void actionPerformedBtnGenerarStockCategoriaProveedor(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			
			String categoria = cbStockCategoria.getSelectedItem().toString();
			String proveedor = cbStockProveedor.getSelectedItem().toString();
			float cantidad = Float.parseFloat(txtStockMenores.getText());
			
			if(txtStockMenores.equals(null))
				JOptionPane.showMessageDialog(null, "Seleccione una cantidad");
			else{
			
				if ( categoria.equals("TODAS") && proveedor.equals("TODAS")) {
					
					Map<String, Object> parameters = new HashMap();
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rStockAllTodas.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if( categoria.equals("TODAS") && !(proveedor.equals("TODAS")) ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("marca", proveedor);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rStockProvider.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if ( !(categoria.equals("TODAS")) && proveedor.equals("TODAS") ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("categoria", categoria);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rStockCategory.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if ( !(categoria.equals("TODAS")) && !(proveedor.equals("TODAS")) ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("categoria", categoria);
					parameters.put("marca", proveedor);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rStockAll.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				
				con.close();
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
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
	
	protected void mouseClickedChckbxRestriccionCantidad(MouseEvent e) {
		try {
			if( chckbxMenorA.isSelected() ){
				chckbxMenorA.setSelected(false);
				txtMenores.setEnabled(false);
				chckbxRestriccionCantidad.setSelected(true);				
			}
			else{
				chckbxMenorA.setSelected(true);
				txtMenores.setEnabled(true);
				chckbxRestriccionCantidad.setSelected(false);	
			}
		} catch (Exception ez) {
			JOptionPane.showMessageDialog(null, "Error en combos1: " + ez);
		}
	}
	protected void mouseClickedChckbxMenorA(MouseEvent e) {
		try {
			if( chckbxRestriccionCantidad.isSelected() ){
				chckbxMenorA.setSelected(true);
				txtMenores.setEnabled(true);
				chckbxRestriccionCantidad.setSelected(false);
			}
			else{
				chckbxMenorA.setSelected(false);
				txtMenores.setEnabled(false);
				chckbxRestriccionCantidad.setSelected(true);
			}
		} catch (Exception ez) {
			JOptionPane.showMessageDialog(null, "Error en combos2: " + ez);
		}
	}
	
	protected void actionPerformedBtnPorProducto(ActionEvent arg0) {
		 Connection con = null;
	      try {

			String nomProducto = txtProductos.getText();
	    	int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			
	        con = MySQLConexion.getConection();
	        Map parameters = new HashMap();
	        parameters.put("idProd", idProd);
	        parameters.put("prod", nomProducto);
	        new AbstractJasperReports().createReport(con, "rProductoEspecifico.jasper", parameters);
	        this.txtProductos.setText(null);
	        AbstractJasperReports.showViewer();
	        con.close();
	      } catch (Exception e1) {
	        JOptionPane.showMessageDialog(null, "Error al cargar reporte");
	      }
	}
	
	protected void actionPerformedButton(ActionEvent e) {
		
		Connection con = null;
	    try {
	      con = MySQLConexion.getConection();
	      String usu = this.cbUsuarios.getSelectedItem().toString();

	      int añoi = this.calRI02.getCalendar().get(1);
	      int mesi = this.calRI02.getCalendar().get(2) + 1;
	      int diai = this.calRI02.getCalendar().get(5);
	      String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

	      int añof = this.calRI01.getCalendar().get(1);
	      int mesf = this.calRI01.getCalendar().get(2) + 1;
	      int diaf = this.calRI01.getCalendar().get(5);
	      String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
	      Map parameters = new HashMap();
	      parameters.put("prtFechaI", fechai);
	      parameters.put("prmtFechaF", fechaf);

	      new AbstractJasperReports().createReport(con, "rFechaIngreso.jasper", parameters);
	      AbstractJasperReports.showViewer();
	      con.close();
	    }
	    catch (Exception ex) {
	      JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
	    }
		
	}
	
	protected void actionPerformedBtnImprimirCopia(ActionEvent e) {
		/*
		try {
			int copias = Integer.parseInt(this.txtNroTicket.getText());
      		Connection con = null;
            con = MySQLConexion.getConection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("prtNVenta", Integer.valueOf(Integer.parseInt(this.txtNroTicket.getText())));
            
            try {
              JasperReport reporte = 
                (JasperReport)JRLoader.loadObjectFromFile("D:\\\\rComprobante.jasper");
              JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, con);
              JasperPrintManager.printReport(jasperPrint, false);
            } catch (JRException ex) {
              JOptionPane.showMessageDialog(null, "ERROR al imprimir: " + ex.getMessage());
            } 
            
            con.close();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Llene los campos correctamente: " + e2);
		}*/
		
		try {

			int txtNroTicket = Integer.parseInt(this.txtNroTicket.getText());
			Map<String, Object> parameters = new HashMap();
			parameters.put("prtNVenta", txtNroTicket);
			try {
				Connection con = null;
	            con = MySQLConexion.getConection();
				JasperPrint impressao = JasperFillManager.fillReport(
						getClass().getClassLoader().getResourceAsStream("rComprobante.jasper"),
						parameters, con);

				// AbstractJasperReports.showViewer();
				JasperPrintManager.printReport(impressao, true);
			} catch (JRException ex) {
				 JOptionPane.showMessageDialog(null,
				 "ERROR " + ex.getMessage());
				 System.err.println("Error iReport: " +
				 ex.getMessage());
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR " + ex);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnMejorVendedor) {
			actionPerformedBtnMejorVendedor(arg0);
		}
		if (arg0.getSource() == this.btnReporteCompraCategoriaProveedor) {
			actionPerformedBtnReporteCompraCategoriaProveedor(arg0);
		}
		if (arg0.getSource() == this.btngenerarReporteVentaCategoria) {
			actionPerformedBtngenerarReporteVentaCategoria(arg0);
		}
		if (arg0.getSource() == this.btnGenerarStockCategoriaProveedor) {
			actionPerformedBtnGenerarStockCategoriaProveedor(arg0);
		}
	}
	protected void actionPerformedBtnReporteCompraCategoriaProveedor(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			
			String categoria = cbCompraCategoria.getSelectedItem().toString();
			String proveedor = cbCompraProveedor.getSelectedItem().toString();
			
			int añoi = calendar_10.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_10.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_10.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calendar_11.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_11.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_11.getCalendar().get(Calendar.DAY_OF_MONTH);
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
			
			
			if ( categoria.equals("TODAS") && proveedor.equals("TODAS")) {
				
				new AbstractJasperReports().createReport(con, "rComprasDetalladasAll.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if( categoria.equals("TODAS") && !(proveedor.equals("TODAS")) ){
				
				parameters.put("prmtProveedor", proveedor);
				
				new AbstractJasperReports().createReport(con, "rComprasDetalladasProvider.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if ( !(categoria.equals("TODAS")) && proveedor.equals("TODAS") ){
				
				parameters.put("prmtCategoria", categoria);
				
				new AbstractJasperReports().createReport(con, "rComprasDetalladasCategory.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			else if ( !(categoria.equals("TODAS")) && !(proveedor.equals("TODAS")) ){
				
				parameters.put("prmtCategoria", categoria);
				parameters.put("prmtProveedor", proveedor);
				
				new AbstractJasperReports().createReport(con, "rComprasDetalladas.jasper", parameters);
				AbstractJasperReports.showViewer();	
			}
			
			con.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
		}
	}
	protected void actionPerformedBtnMejorVendedor(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			
			int añoi = calRI03.getCalendar().get(Calendar.YEAR);
			int mesi = calRI03.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calRI03.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = calRI04.getCalendar().get(Calendar.YEAR);
			int mesf = calRI04.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calRI04.getCalendar().get(Calendar.DAY_OF_MONTH);
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
				
			new AbstractJasperReports().createReport(con, "rMejorVendedor.jasper", parameters);
			AbstractJasperReports.showViewer();	
			
			con.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron vendedores " + ex);
		}
	}
}
