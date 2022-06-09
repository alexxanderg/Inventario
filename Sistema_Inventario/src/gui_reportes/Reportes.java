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
import clases.Marcas;
import clases.Colores;
import clases.Distribuidores;
import clases.Laboratorios;

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
import javax.swing.border.LineBorder;

public class Reportes extends JInternalFrame {
	private JComboBox<Usuarios> cbUsuarios;
	private JComboBox cbMetodoPago;
	private JLabel label_1;
	private JDateChooser fRVI01;
	private JLabel label_2;
	private JDateChooser fRVF01;
	private JButton btngenerarReporteVentas;
	private JLabel lblDesde;
	private JLabel lblRanking;
	private JComboBox cbxRanking;
	private JLabel label_8;
	private JDateChooser fRPROI01;
	private JDateChooser fRCLII01;
	private JDateChooser fRCLIF01;
	private JLabel lblHasta;
	private JDateChooser fRPF01;
	private JButton btnVerRanking;
	private JLabel lblCategora;
	private JButton btnInventarioCFiltros;
	private JLabel lblHistorialDeCompras;
	private JButton btnVerComprasCliente;
	private JPanel panel;
	private JLabel lblVendedor;
	private JLabel lblMtodoDePago;
	private JPanel panel_1;
	private JLabel lblVentas;
	private JComboBox<Categoria> cbCategoria;
	private JComboBox<Cliente> cbCliente;
	private JComboBox<Marcas> cbMarca;
	private JComboBox<Colores> cbColor;
	private JComboBox<Laboratorios> cbLaboratorio;
	private JComboBox<Distribuidores> cbDistribuidores;

	private TextAutoCompleter ac1;
	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JLabel lblProductos;
	private JLabel lblFiltros;
	private JLabel lblMarca;
	private JPanel panel_2;
	private JLabel lblReporteDeCompras;
	private JLabel label;
	private JDateChooser fRCI01;
	private JLabel label_3;
	private JDateChooser fRCF01;
	private JButton btnCompras;
	private JLabel lblPorProducto;
	private JButton btnPorProducto;
	private JTextField txtProductos;
	private JButton btnVerReporteSimple;
	private JCheckBox chckSoloStock;

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
		this.panel.setBackground(new Color(143, 188, 143));
		this.panel.setBounds(0, 0, 557, 498);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.lblVendedor = new JLabel("Vendedor:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(72, 76, 157, 23);
		this.panel.add(this.lblVendedor);

		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(249, 77, 219, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));

		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Efectivo",
				"Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "YAPE/PLIN" }));
		this.cbMetodoPago.setBounds(249, 110, 219, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));

		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(72, 110, 157, 23);
		this.panel.add(this.lblMtodoDePago);

		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(72, 144, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));

		this.fRVI01 = new JDateChooser();
		this.fRVI01.setBounds(126, 144, 125, 23);
		this.panel.add(this.fRVI01);

		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(295, 144, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));

		this.fRVF01 = new JDateChooser();
		this.fRVF01.setBounds(343, 144, 125, 23);
		this.panel.add(this.fRVF01);

		this.btngenerarReporteVentas = new JButton("<html><center>Ver reporte detallado</center></html>");
		btngenerarReporteVentas.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btngenerarReporteVentas.setVerticalAlignment(SwingConstants.TOP);
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(277, 178, 191, 57);
		this.panel.add(this.btngenerarReporteVentas);
		this.btngenerarReporteVentas.setForeground(Color.WHITE);
		this.btngenerarReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentas.setBackground(new Color(30, 144, 255));

		this.lblVentas = new JLabel("REPORTE DE SALIDAS");
		this.lblVentas.setBounds(74, 20, 394, 32);
		this.panel.add(this.lblVentas);
		this.lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVentas.setFont(new Font("Candara", Font.BOLD, 30));

		this.lblHistorialDeCompras = new JLabel("POR CLIENTE:");
		lblHistorialDeCompras.setBounds(72, 273, 219, 23);
		panel.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 20));

		this.cbCliente = new JComboBox();
		cbCliente.setBounds(229, 270, 239, 23);
		panel.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));

		this.btnVerComprasCliente = new JButton("Ver reporte");
		btnVerComprasCliente.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnVerComprasCliente.setBounds(229, 341, 239, 32);
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
		lblPorProducto.setBounds(72, 407, 232, 23);
		panel.add(lblPorProducto);

		btnPorProducto = new JButton("Ver reporte");
		btnPorProducto.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnPorProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnPorProducto(arg0);
			}
		});
		btnPorProducto.setForeground(Color.WHITE);
		btnPorProducto.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPorProducto.setBackground(new Color(30, 144, 255));
		btnPorProducto.setBounds(229, 438, 239, 32);
		panel.add(btnPorProducto);

		txtProductos = new JTextField();
		txtProductos.setHorizontalAlignment(SwingConstants.LEFT);
		txtProductos.setFont(new Font("Arial", Font.PLAIN, 16));
		txtProductos.setColumns(10);
		txtProductos.setBackground(SystemColor.controlHighlight);
		txtProductos.setBounds(229, 404, 239, 23);
		panel.add(txtProductos);

		btnVerReporteSimple = new JButton("<html><center>Ver reporte<br>simple</center></html>");
		btnVerReporteSimple.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnVerReporteSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerReporteSimple(e);
			}
		});
		btnVerReporteSimple.setVerticalAlignment(SwingConstants.TOP);
		btnVerReporteSimple.setForeground(Color.WHITE);
		btnVerReporteSimple.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnVerReporteSimple.setBackground(new Color(30, 144, 255));
		btnVerReporteSimple.setBounds(72, 178, 191, 57);
		panel.add(btnVerReporteSimple);

		JLabel label_1_1 = new JLabel("del:");
		label_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1_1.setBounds(72, 307, 46, 23);
		panel.add(label_1_1);

		fRCLII01 = new JDateChooser();
		fRCLII01.setBounds(126, 307, 125, 23);
		panel.add(fRCLII01);

		fRCLIF01 = new JDateChooser();
		fRCLIF01.setBounds(343, 307, 125, 23);
		panel.add(fRCLIF01);

		JLabel label_2_1 = new JLabel("al:");
		label_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_2_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_2_1.setBounds(295, 307, 55, 23);
		panel.add(label_2_1);

		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(70, 130, 180));
		this.panel_1.setBounds(561, 0, 568, 498);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);

		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(16, 115, 143, 23);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));

		this.btnInventarioCFiltros = new JButton("Ver reporte");
		btnInventarioCFiltros.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnInventarioCFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnInventarioCFiltros.setBounds(166, 292, 161, 49);
		this.panel_1.add(this.btnInventarioCFiltros);
		this.btnInventarioCFiltros.setForeground(Color.WHITE);
		this.btnInventarioCFiltros.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnInventarioCFiltros.setBackground(new Color(30, 144, 255));

		this.cbCategoria = new JComboBox();
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCategoria.setBounds(166, 114, 316, 23);
		this.panel_1.add(this.cbCategoria);

		lblProductos = new JLabel("REPORTE DE PRODUCTOS");
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setFont(new Font("Candara", Font.BOLD, 30));
		lblProductos.setBounds(10, 21, 535, 32);
		panel_1.add(lblProductos);

		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setForeground(new Color(255, 255, 255));
		lblFiltros.setFont(new Font("Candara", Font.BOLD, 25));
		lblFiltros.setBounds(16, 77, 143, 32);
		panel_1.add(lblFiltros);

		lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(16, 150, 143, 23);
		panel_1.add(lblMarca);

		cbMarca = new JComboBox();
		cbMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMarca.setBounds(166, 149, 316, 23);
		panel_1.add(cbMarca);

		cbColor = new JComboBox();
		cbColor.setFont(new Font("Arial", Font.PLAIN, 16));
		cbColor.setBounds(166, 188, 316, 23);
		panel_1.add(cbColor);

		cbLaboratorio = new JComboBox();
		cbLaboratorio.setFont(new Font("Arial", Font.PLAIN, 16));
		cbLaboratorio.setBounds(166, 223, 316, 23);
		panel_1.add(cbLaboratorio);

		this.lblRanking = new JLabel("RANKING de productos:");
		lblRanking.setBounds(16, 380, 231, 32);
		panel_1.add(lblRanking);
		this.lblRanking.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblRanking.setFont(new Font("Candara", Font.BOLD, 20));

		this.cbxRanking = new JComboBox();
		cbxRanking.setBounds(251, 380, 231, 32);
		panel_1.add(cbxRanking);
		this.cbxRanking.setFont(new Font("Candara", Font.BOLD, 20));
		this.cbxRanking.setModel(new DefaultComboBoxModel(new String[] { "M\u00E1s vendidos", "Menos vendidos" }));
		this.cbxRanking.setToolTipText("");

		this.label_8 = new JLabel("del:");
		label_8.setBounds(-45, 488, 55, 23);
		panel_1.add(label_8);
		this.label_8.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_8.setFont(new Font("Candara", Font.BOLD, 20));

		this.fRPROI01 = new JDateChooser();
		fRPROI01.setBounds(84, 419, 141, 23);
		panel_1.add(fRPROI01);

		this.btnVerRanking = new JButton("Ver reporte");
		btnVerRanking.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnVerRanking.setBounds(166, 453, 161, 32);
		panel_1.add(btnVerRanking);
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerRanking(e);
			}
		});
		this.btnVerRanking.setForeground(Color.WHITE);
		this.btnVerRanking.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerRanking.setBackground(new Color(30, 144, 255));

		this.fRPF01 = new JDateChooser();
		fRPF01.setBounds(334, 419, 148, 23);
		panel_1.add(fRPF01);

		this.lblHasta = new JLabel("hasta:");
		lblHasta.setBounds(264, 426, 77, 16);
		panel_1.add(lblHasta);
		this.lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHasta.setFont(new Font("Candara", Font.BOLD, 20));

		this.lblDesde = new JLabel("desde:");
		lblDesde.setBounds(20, 418, 73, 24);
		panel_1.add(lblDesde);
		this.lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDesde.setFont(new Font("Candara", Font.BOLD, 20));

		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Candara", Font.BOLD, 20));
		lblColor.setBounds(16, 189, 143, 23);
		panel_1.add(lblColor);

		JLabel lblLaboratorio = new JLabel("Laboratorio");
		lblLaboratorio.setFont(new Font("Candara", Font.BOLD, 20));
		lblLaboratorio.setBounds(16, 224, 143, 23);
		panel_1.add(lblLaboratorio);

		JLabel lblDistribuidor = new JLabel("Distribuidor:");
		lblDistribuidor.setFont(new Font("Candara", Font.BOLD, 20));
		lblDistribuidor.setBounds(16, 259, 143, 23);
		panel_1.add(lblDistribuidor);

		cbDistribuidores = new JComboBox();
		cbDistribuidores.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDistribuidores.setBounds(166, 258, 316, 23);
		panel_1.add(cbDistribuidores);

		chckSoloStock = new JCheckBox("Ver solo los que tienen stock");
		chckSoloStock.setBackground(new Color(70, 130, 180));
		chckSoloStock.setBounds(333, 292, 195, 23);
		panel_1.add(chckSoloStock);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(147, 112, 219));
		panel_2.setBounds(0, 504, 1129, 135);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lblReporteDeCompras = new JLabel("REPORTE DE COMPRAS");
		lblReporteDeCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeCompras.setFont(new Font("Candara", Font.BOLD, 30));
		lblReporteDeCompras.setBounds(297, 11, 535, 32);
		panel_2.add(lblReporteDeCompras);

		label = new JLabel("del:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBounds(196, 77, 46, 23);
		panel_2.add(label);

		fRCI01 = new JDateChooser();
		fRCI01.setBounds(250, 77, 125, 23);
		panel_2.add(fRCI01);

		label_3 = new JLabel("al:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Candara", Font.BOLD, 20));
		label_3.setBounds(419, 77, 55, 23);
		panel_2.add(label_3);

		fRCF01 = new JDateChooser();
		fRCF01.setBounds(467, 77, 125, 23);
		panel_2.add(fRCF01);

		btnCompras = new JButton("Ver reporte");
		btnCompras.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton(e);
			}
		});
		btnCompras.setForeground(Color.WHITE);
		btnCompras.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCompras.setBackground(new Color(30, 144, 255));
		btnCompras.setBounds(639, 68, 177, 32);
		panel_2.add(btnCompras);

		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // QUITA LA BARRA DE TÍTULO

		cargar();
		cargarBuscador();
	}

	private void cargar() {
		Cliente cliente = new Cliente();
		cliente.cargarClientes(cbCliente);

		Categoria categoria = new Categoria();
		Categoria todaCategoria = new Categoria("VER TODO");
		cbCategoria.addItem(todaCategoria);
		categoria.cargarCategorias(cbCategoria);

		Marcas marca = new Marcas();
		Marcas todaMarca = new Marcas("VER TODO");
		cbMarca.addItem(todaMarca);
		marca.cargarMarcas(cbMarca);

		Colores color = new Colores();
		Colores todoColor = new Colores("VER TODO");
		cbColor.addItem(todoColor);
		color.cargarColores(cbColor);

		Laboratorios lasbs = new Laboratorios();
		Laboratorios todoLab = new Laboratorios("VER TODO");
		cbLaboratorio.addItem(todoLab);
		lasbs.cargarLaboratorios(cbLaboratorio);

		Distribuidores distri = new Distribuidores();
		Distribuidores todoDist = new Distribuidores("VER TODO");
		cbDistribuidores.addItem(todoDist);
		distri.cargarDistribuidores(cbDistribuidores);

		Usuarios usu = new Usuarios();
		Usuarios todos = new Usuarios(0, "TODOS", "TODOS", "TODOS", 0);
		cbUsuarios.addItem(todos);
		usu.cargarUsuarios(cbUsuarios);
		java.util.Date date = new Date();
		date.getTime();
		fRVI01.setDate(date);
		fRVF01.setDate(date);
		fRPROI01.setDate(date);
		fRPF01.setDate(date);

		fRCI01.setDate(date);
		fRCF01.setDate(date);
		
		fRCLII01.setDate(date);
		fRCLIF01.setDate(date);
	}

	public void cargarBuscador() {
		ac1 = new TextAutoCompleter(txtProductos);
		consultas consulta = new consultas();

		ac1.setMode(0);
		consulta.iniciar();
		ResultSet rs = consulta.cargarProductos();
		try {
			while (rs.next()) {
				ac1.addItem(rs.getString("cantidad") + " " + rs.getString("producto") + " " + rs.getString("detalles")
						+ " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio")
						+ " " + rs.getString("lote") + " * " + rs.getString("unimedida") + "  -  ("
						+ rs.getString("codproducto") + ")");
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

	protected void actionPerformedBtnVerReporteSimple(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex() - 1;

			int añoi = fRVI01.getCalendar().get(Calendar.YEAR);
			int mesi = fRVI01.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fRVI01.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fRVF01.getCalendar().get(Calendar.YEAR);
			int mesf = fRVF01.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fRVF01.getCalendar().get(Calendar.DAY_OF_MONTH);
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
					new AbstractJasperReports().createReport(con, "rVentasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();

				} else {
					new AbstractJasperReports().createReport(con, "rVentasVendedorTodoMetodoxUsuario.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}

	protected void actionPerformedBtngenerarReporteVentas(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex() - 1;

			int añoi = fRVI01.getCalendar().get(Calendar.YEAR);
			int mesi = fRVI01.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fRVI01.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fRVF01.getCalendar().get(Calendar.YEAR);
			int mesf = fRVF01.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fRVF01.getCalendar().get(Calendar.DAY_OF_MONTH);
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
				if (cbUsuarios.getSelectedIndex() == 0)
					rs = consulta.cargarVentasUsuarioTodos(fechai, fechaf);
				else
					rs = consulta.cargarVentasUsuario(idusuario, fechai, fechaf);

				while (rs.next()) {
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
				if (metpago == -1) {
					parameters.put("totalVenta", "" + totalVenta);
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
					parameters.put("totalVenta", "" + totalVenta);
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

	protected void actionPerformedBtnVerRanking(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = fRPROI01.getCalendar().get(Calendar.YEAR);
			int mesi = fRPROI01.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fRPROI01.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fRPF01.getCalendar().get(Calendar.YEAR);
			int mesf = fRPF01.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fRPF01.getCalendar().get(Calendar.DAY_OF_MONTH);
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
		/*Connection con = null;
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
		}*/

		
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			
			
			String nrodoc = "" + cbCliente.getItemAt(cbCliente.getSelectedIndex()).getId();
			
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex() - 1;

			int añoi = fRCLII01.getCalendar().get(Calendar.YEAR);
			int mesi = fRCLII01.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fRCLII01.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fRCLIF01.getCalendar().get(Calendar.YEAR);
			int mesf = fRCLIF01.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fRCLIF01.getCalendar().get(Calendar.DAY_OF_MONTH);
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

			Map parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);

			parameters.put("totalVenta", "" + totalVenta);
			parameters.put("prmtIdCliente", "" + nrodoc);
			
			new AbstractJasperReports().createReport(con, "rVentasDetalladasXCliente.jasper", parameters);
			AbstractJasperReports.showViewer();

			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}

	}

	protected void actionPerformedBtnGenerarMenores(ActionEvent e) {

		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			String categoria = cbCategoria.getSelectedItem().toString();
			String marca = cbMarca.getSelectedItem().toString();
			String color = cbColor.getSelectedItem().toString();
			String laboratorio = cbLaboratorio.getSelectedItem().toString();
			String distribuidor = cbDistribuidores.getSelectedItem().toString();

			if (categoria.equals("VER TODO"))
				categoria = "%%";
			if (marca.equals("VER TODO"))
				marca = "%%";
			if (color.equals("VER TODO"))
				color = "%%";
			if (laboratorio.equals("VER TODO"))
				laboratorio = "%%";
			if (distribuidor.equals("VER TODO"))
				distribuidor = "%%";
			else
				distribuidor = "" + cbDistribuidores.getItemAt(cbDistribuidores.getSelectedIndex()).getIddist();

			Map<String, Object> parameters = new HashMap();
			parameters.put("marca", marca);
			parameters.put("color", color);
			parameters.put("laboratorio", laboratorio);
			parameters.put("categoria", categoria);
			parameters.put("dist", distribuidor);

			if (chckSoloStock.isSelected()) {
				new AbstractJasperReports().createReport(con, "rInventarioConFiltrosSoloStock.jasper", parameters);
				AbstractJasperReports.showViewer();
			} else {
				new AbstractJasperReports().createReport(con, "rInventarioConFiltros.jasper", parameters);
				AbstractJasperReports.showViewer();
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Reporte vacio " + ex);
		}

//			if (chckbxMenorA.isSelected()) {
//				if (categoria.equals("TODAS") && marca.equals("TODAS")) {
//
//					Map<String, Object> parameters = new HashMap();
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCompraTodos.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (categoria.equals("TODAS") && !(marca.equals("TODAS"))) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("marca", marca);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCompraMarca.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (!(categoria.equals("TODAS")) && marca.equals("TODAS")) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("categoria", categoria);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCompraCategoria.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (!(categoria.equals("TODAS")) && !(marca.equals("TODAS"))) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("categoria", categoria);
//					parameters.put("marca", marca);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCompraCategoriaMarca.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				}
//
//				con.close();
//			} else {
//				if (categoria.equals("TODAS") && marca.equals("TODAS")) {
//
//					Map<String, Object> parameters = new HashMap();
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCardexTodos.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (categoria.equals("TODAS") && !(marca.equals("TODAS"))) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("marca", marca);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCardexMarca.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (!(categoria.equals("TODAS")) && marca.equals("TODAS")) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("categoria", categoria);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCardexCategoria.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				} else if (!(categoria.equals("TODAS")) && !(marca.equals("TODAS"))) {
//					Map<String, Object> parameters = new HashMap();
//
//					parameters.put("categoria", categoria);
//					parameters.put("marca", marca);
//					parameters.put("cantidad", cantidad);
//
//					new AbstractJasperReports().createReport(con, "rCardexCategoriaMarca.jasper", parameters);
//					AbstractJasperReports.showViewer();
//				}
//
//				con.close();
//			}
//
//		} catch (Exception ex) {
//			JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
//		}
//
//		if (txtMayoresMenores.getText().equals(""))
//			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
//		else {
//			Connection con = null;
//			try {
//				if (!(cbCategoria.getSelectedItem().toString().equals("TODAS"))) {
//					String categoria = cbCategoria.getSelectedItem().toString();
//
//					con = MySQLConexion.getConection();
//					float menoresmayores = Float.parseFloat(txtMayoresMenores.getText());
//					Map<String, Object> parameters = new HashMap();
//					parameters.put("prtcantidad", menoresmayores);
//					parameters.put("categoria", categoria);
//					new AbstractJasperReports().createReport(con, "rCardexMayoresCategoria.jasper", parameters);
//					AbstractJasperReports.showViewer();
//					con.close();
//					txtMayoresMenores.setText(null);
//				} else {
//					con = MySQLConexion.getConection();
//					float menoresmayores = Float.parseFloat(txtMayoresMenores.getText());
//					Map<String, Object> parameters = new HashMap();
//					parameters.put("prtcantidad", menoresmayores);
//					new AbstractJasperReports().createReport(con, "rCardexmayores.jasper", parameters);
//					AbstractJasperReports.showViewer();
//					con.close();
//					txtMayoresMenores.setText(null);
//				}
//			} catch (Exception ex) {
//				JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
//			}
//		}

	}

	protected void actionPerformedBtnPorProducto(ActionEvent arg0) {
		Connection con = null;
		try {

			String nomProducto = txtProductos.getText();
			int idProd = Integer
					.parseInt(nomProducto.substring(nomProducto.indexOf("(") + 1, nomProducto.indexOf(")")));

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
		/*
		 * 
		 * JOptionPane.showMessageDialog(null,
		 * "Se mostrará los productos creado e ingresos realizados mediante el boton * AÑADIR STOCK * de la ventana Inventario"
		 * );
		 * 
		 * Connection con = null; try { con = MySQLConexion.getConection(); String usu =
		 * this.cbUsuarios.getSelectedItem().toString();
		 * 
		 * int añoi = this.fRCI01.getCalendar().get(1); int mesi =
		 * this.fRCI01.getCalendar().get(2) + 1; int diai =
		 * this.fRCI01.getCalendar().get(5); String fechai = añoi + "-" + mesi + "-" +
		 * diai + " 00:00:00";
		 * 
		 * int añof = this.fRCF01.getCalendar().get(1); int mesf =
		 * this.fRCF01.getCalendar().get(2) + 1; int diaf =
		 * this.fRCF01.getCalendar().get(5); String fechaf = añof + "-" + mesf + "-" +
		 * diaf + " 23:59:59"; Map parameters = new HashMap();
		 * parameters.put("prtFechaI", fechai); parameters.put("prmtFechaF", fechaf);
		 * 
		 * new AbstractJasperReports().createReport(con, "rFechaIngreso.jasper",
		 * parameters); AbstractJasperReports.showViewer(); con.close(); } catch
		 * (Exception ex) { JOptionPane.showMessageDialog(null,
		 * "No se encontraron datos registrados en estas fechas" + ex); }
		 */

		String[] opciones = { "SIMPLE", "DETALLADO" };
		int seleccion = JOptionPane.showOptionDialog(null, "REPORTE", "Seleccione una opcion", -1, 3, null, opciones,
				opciones[0]);
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = this.fRCI01.getCalendar().get(1);
			int mesi = this.fRCI01.getCalendar().get(2) + 1;
			int diai = this.fRCI01.getCalendar().get(5);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = this.fRCF01.getCalendar().get(1);
			int mesf = this.fRCF01.getCalendar().get(2) + 1;
			int diaf = this.fRCF01.getCalendar().get(5);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = formatter.parse(fechai);
			Timestamp timeStampDateI = new Timestamp(date.getTime());

			DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = formatter2.parse(fechaf);
			Timestamp timeStampDateF = new Timestamp(date2.getTime());

			Map parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaFi", timeStampDateF);

			if (seleccion == 0) {
				new AbstractJasperReports().createReport(con, "rCompraSimple.jasper", parameters);
				AbstractJasperReports.showViewer();
			}
			if (seleccion == 1) {
				new AbstractJasperReports().createReport(con, "rCompraDetallada.jasper", parameters);
				AbstractJasperReports.showViewer();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}

	}
}
