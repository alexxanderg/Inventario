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
import clases.PintarTablaVentasBuscar;
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

public class Reportes2 extends JInternalFrame implements ActionListener {
	private JComboBox<Usuarios> cbUsuarios;
	private JComboBox cbMetodoPago;
	private JLabel label_1;
	private JDateChooser fInicial;
	private JLabel label_2;
	private JDateChooser fFinal;
	private JButton btngenerarReporteVentas;
	private JLabel lblRanking;
	private JComboBox cbxRanking;
	private JButton btnVerRanking;
	private JLabel lblCategora;
	private JButton btnInventarioCFiltros;
	private JLabel lblHistorialDeCompras;
	private JPanel panel;
	private JLabel lblVendedor;
	private JLabel lblMtodoDePago;
	private JLabel lblVentas;
	private JComboBox<Categoria> cbCategoria;
	private JComboBox<Categoria> cbCategoria_Venta;
	private JComboBox<Cliente> cbCliente;
	private JComboBox<Marcas> cbMarca;
	private JComboBox<Colores> cbColor;
	private JComboBox<Laboratorios> cbLaboratorio;
	private JComboBox<Distribuidores> cbDistribuidores;

	private TextAutoCompleter ac1;
	private TextAutoCompleter ac2;
	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JLabel lblFiltros;
	private JLabel lblMarca;
	private JLabel lblReporteDeCompras;
	private JButton btnCompras;
	private JLabel lblPorProducto;
	private JButton btnSalidas;
	private JTextField txtProductos;
	private JCheckBox chckSoloStock;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblReporteDeVentas;
	private JLabel lblProducto;
	private JLabel lblCategoria;
	private JTextField textField;
	private JTextField txtProductosVenta;
	private JLabel lblNoAfectaLas;
	private JButton btnIngresos;
	private JLabel lblNewLabel;
	private JLabel lblProductosPorVencer;
	private JButton btnProdVencer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes2 frame = new Reportes2(null);
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
	public Reportes2(VentanaPrincipal vp) {
		getContentPane().setBackground(Color.DARK_GRAY);
		this.vp = vp;

		setBounds(100, 100, 1134, 669);
		getContentPane().setLayout(null);

		this.panel = new JPanel();
		this.panel.setBackground(new Color(143, 188, 143));
		this.panel.setBounds(0, 0, 1130, 639);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.lblVendedor = new JLabel("Vendedor:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(72, 166, 165, 23);
		this.panel.add(this.lblVendedor);

		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(249, 167, 249, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));

		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"VER TODO", "EFECTIVO", "TARJETA", "TRANSFERENCIA", "DEP\u00D3SITO", "YAPE/PLIN"}));
		this.cbMetodoPago.setBounds(249, 302, 249, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));

		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(72, 302, 165, 23);
		this.panel.add(this.lblMtodoDePago);

		this.btngenerarReporteVentas = new JButton("Ver reporte ");
		btngenerarReporteVentas.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btngenerarReporteVentas.setVerticalAlignment(SwingConstants.TOP);
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(249, 341, 249, 32);
		this.panel.add(this.btngenerarReporteVentas);
		this.btngenerarReporteVentas.setForeground(Color.WHITE);
		this.btngenerarReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentas.setBackground(new Color(30, 144, 255));

		this.lblVentas = new JLabel("REPORTES");
		this.lblVentas.setBounds(355, 11, 441, 32);
		this.panel.add(this.lblVentas);
		this.lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVentas.setFont(new Font("Candara", Font.BOLD, 30));

		this.lblHistorialDeCompras = new JLabel("Cliente:");
		lblHistorialDeCompras.setBounds(72, 200, 165, 23);
		panel.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 20));

		this.cbCliente = new JComboBox();
		cbCliente.setBounds(249, 200, 249, 23);
		panel.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));

		lblPorProducto = new JLabel("MOVIMIENTOS DE PRODUCTO");
		lblPorProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPorProducto.setFont(new Font("Candara", Font.BOLD, 20));
		lblPorProducto.setBounds(72, 417, 396, 23);
		panel.add(lblPorProducto);

		btnSalidas = new JButton("Salidas");
		btnSalidas.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnPorProducto(arg0);
			}
		});
		btnSalidas.setForeground(Color.WHITE);
		btnSalidas.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSalidas.setBackground(new Color(30, 144, 255));
		btnSalidas.setBounds(320, 483, 178, 32);
		panel.add(btnSalidas);

		txtProductos = new JTextField();
		txtProductos.setHorizontalAlignment(SwingConstants.LEFT);
		txtProductos.setFont(new Font("Arial", Font.PLAIN, 16));
		txtProductos.setColumns(10);
		txtProductos.setBackground(SystemColor.controlHighlight);
		txtProductos.setBounds(72, 447, 426, 25);
		panel.add(txtProductos);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(72, 384, 426, 5);
		panel.add(textField_1);

		lblReporteDeCompras = new JLabel("REPORTE DE COMPRAS");
		lblReporteDeCompras.setBounds(619, 131, 271, 32);
		panel.add(lblReporteDeCompras);
		lblReporteDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		lblReporteDeCompras.setFont(new Font("Candara", Font.BOLD, 20));

		btnCompras = new JButton("Ver reporte");
		btnCompras.setBounds(769, 165, 249, 32);
		panel.add(btnCompras);
		btnCompras.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton(e);
			}
		});
		btnCompras.setForeground(Color.WHITE);
		btnCompras.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCompras.setBackground(new Color(30, 144, 255));

		lblFiltros = new JLabel("LISTADO DE PRODUCTOS");
		lblFiltros.setBackground(Color.DARK_GRAY);
		lblFiltros.setBounds(619, 357, 325, 32);
		panel.add(lblFiltros);
		lblFiltros.setForeground(Color.BLACK);
		lblFiltros.setFont(new Font("Candara", Font.BOLD, 20));

		this.lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(619, 398, 143, 23);
		panel.add(lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));

		lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(619, 433, 143, 23);
		panel.add(lblMarca);
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(619, 472, 143, 23);
		panel.add(lblColor);
		lblColor.setFont(new Font("Candara", Font.BOLD, 20));

		JLabel lblLaboratorio = new JLabel("Laboratorio");
		lblLaboratorio.setBounds(619, 507, 143, 23);
		panel.add(lblLaboratorio);
		lblLaboratorio.setFont(new Font("Candara", Font.BOLD, 20));

		JLabel lblDistribuidor = new JLabel("Distribuidor:");
		lblDistribuidor.setBounds(619, 542, 143, 23);
		panel.add(lblDistribuidor);
		lblDistribuidor.setFont(new Font("Candara", Font.BOLD, 20));

		this.cbCategoria = new JComboBox();
		cbCategoria.setBounds(769, 397, 249, 23);
		panel.add(cbCategoria);
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));

		cbMarca = new JComboBox();
		cbMarca.setBounds(769, 432, 249, 23);
		panel.add(cbMarca);
		cbMarca.setFont(new Font("Arial", Font.PLAIN, 16));

		cbColor = new JComboBox();
		cbColor.setBounds(769, 471, 249, 23);
		panel.add(cbColor);
		cbColor.setFont(new Font("Arial", Font.PLAIN, 16));

		cbLaboratorio = new JComboBox();
		cbLaboratorio.setBounds(769, 506, 249, 23);
		panel.add(cbLaboratorio);
		cbLaboratorio.setFont(new Font("Arial", Font.PLAIN, 16));

		cbDistribuidores = new JComboBox();
		cbDistribuidores.setBounds(769, 541, 249, 23);
		panel.add(cbDistribuidores);
		cbDistribuidores.setFont(new Font("Arial", Font.PLAIN, 16));

		this.btnInventarioCFiltros = new JButton("Ver reporte");
		btnInventarioCFiltros.setBounds(769, 591, 249, 32);
		panel.add(btnInventarioCFiltros);
		btnInventarioCFiltros.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnInventarioCFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnInventarioCFiltros.setForeground(Color.WHITE);
		this.btnInventarioCFiltros.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnInventarioCFiltros.setBackground(new Color(30, 144, 255));

		chckSoloStock = new JCheckBox("Ver solo los que tienen stock");
		chckSoloStock.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckSoloStock.setHorizontalAlignment(SwingConstants.RIGHT);
		chckSoloStock.setBounds(769, 571, 249, 23);
		panel.add(chckSoloStock);
		chckSoloStock.setBackground(new Color(143, 188, 143));

		textField_2 = new JTextField();
		textField_2.setBounds(619, 341, 399, 5);
		panel.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);

		this.lblRanking = new JLabel("RANKING DE PRODUCTOS");
		lblRanking.setBounds(619, 223, 231, 25);
		panel.add(lblRanking);
		this.lblRanking.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblRanking.setFont(new Font("Candara", Font.BOLD, 20));

		this.cbxRanking = new JComboBox();
		cbxRanking.setBounds(621, 254, 399, 28);
		panel.add(cbxRanking);
		this.cbxRanking.setFont(new Font("Candara", Font.BOLD, 20));
		this.cbxRanking.setModel(new DefaultComboBoxModel(new String[] { "M\u00E1s vendidos", "Menos vendidos" }));
		this.cbxRanking.setToolTipText("");

		this.btnVerRanking = new JButton("Ver reporte");
		btnVerRanking.setBounds(771, 293, 249, 32);
		panel.add(btnVerRanking);
		btnVerRanking.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerRanking(e);
			}
		});
		this.btnVerRanking.setForeground(Color.WHITE);
		this.btnVerRanking.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerRanking.setBackground(new Color(30, 144, 255));

		lblReporteDeVentas = new JLabel("REPORTE DE VENTAS");
		lblReporteDeVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblReporteDeVentas.setFont(new Font("Candara", Font.BOLD, 20));
		lblReporteDeVentas.setBounds(72, 131, 219, 23);
		panel.add(lblReporteDeVentas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));
		panel_1.setBounds(342, 70, 441, 49);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
				this.label_1 = new JLabel("del:");
				label_1.setBounds(32, 15, 46, 23);
				panel_1.add(label_1);
				this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
				this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
				
						this.fInicial = new JDateChooser();
						fInicial.setBounds(86, 15, 125, 23);
						panel_1.add(fInicial);
						
								this.label_2 = new JLabel("al:");
								label_2.setBounds(255, 15, 55, 23);
								panel_1.add(label_2);
								this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
								this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
								
										this.fFinal = new JDateChooser();
										fFinal.setBounds(303, 15, 125, 23);
										panel_1.add(fFinal);
										
										lblProducto = new JLabel("Producto:");
										lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
										lblProducto.setFont(new Font("Candara", Font.BOLD, 20));
										lblProducto.setBounds(72, 234, 165, 23);
										panel.add(lblProducto);
										
										lblCategoria = new JLabel("Categoria:");
										lblCategoria.setHorizontalAlignment(SwingConstants.LEFT);
										lblCategoria.setFont(new Font("Candara", Font.BOLD, 20));
										lblCategoria.setBounds(72, 268, 165, 23);
										panel.add(lblCategoria);
										
										textField = new JTextField();
										textField.setEditable(false);
										textField.setColumns(10);
										textField.setBackground(Color.WHITE);
										textField.setBounds(72, 541, 426, 5);
										panel.add(textField);
										
										txtProductosVenta = new JTextField();
										txtProductosVenta.setHorizontalAlignment(SwingConstants.LEFT);
										txtProductosVenta.setFont(new Font("Arial", Font.PLAIN, 16));
										txtProductosVenta.setColumns(10);
										txtProductosVenta.setBackground(SystemColor.controlHighlight);
										txtProductosVenta.setBounds(249, 234, 249, 23);
										panel.add(txtProductosVenta);
										
										cbCategoria_Venta = new JComboBox();
										cbCategoria_Venta.setFont(new Font("Arial", Font.PLAIN, 16));
										cbCategoria_Venta.setBounds(249, 268, 249, 23);
										panel.add(cbCategoria_Venta);
										
										lblNoAfectaLas = new JLabel("(No afecta las fechas)");
										lblNoAfectaLas.setForeground(new Color(220, 20, 60));
										lblNoAfectaLas.setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 13));
										lblNoAfectaLas.setBackground(Color.DARK_GRAY);
										lblNoAfectaLas.setBounds(846, 366, 152, 19);
										panel.add(lblNoAfectaLas);
										
										btnIngresos = new JButton("Ingresos");
										btnIngresos.addActionListener(this);
										btnIngresos.setForeground(Color.WHITE);
										btnIngresos.setFont(new Font("Tahoma", Font.BOLD, 18));
										btnIngresos.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
										btnIngresos.setBackground(new Color(30, 144, 255));
										btnIngresos.setBounds(72, 483, 178, 32);
										panel.add(btnIngresos);
										
										lblNewLabel = new JLabel("Escoja un rango de fechas y luego el reporte que necesite.");
										lblNewLabel.setForeground(new Color(0, 0, 0));
										lblNewLabel.setBackground(new Color(220, 20, 60));
										lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
										lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel.setBounds(352, 45, 426, 23);
										panel.add(lblNewLabel);
										
										lblProductosPorVencer = new JLabel("PRODUCTOS POR VENCER");
										lblProductosPorVencer.setHorizontalAlignment(SwingConstants.LEFT);
										lblProductosPorVencer.setFont(new Font("Candara", Font.BOLD, 20));
										lblProductosPorVencer.setBounds(72, 571, 249, 25);
										panel.add(lblProductosPorVencer);
										
										btnProdVencer = new JButton("Ver reporte");
										btnProdVencer.addActionListener(this);
										btnProdVencer.setForeground(Color.WHITE);
										btnProdVencer.setFont(new Font("Tahoma", Font.BOLD, 18));
										btnProdVencer.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
										btnProdVencer.setBackground(new Color(30, 144, 255));
										btnProdVencer.setBounds(320, 591, 178, 32);
										panel.add(btnProdVencer);

		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null); // QUITA LA BARRA DE TÍTULO

		cargar();
		cargarBuscador();
	}

	private void cargar() {
		Cliente cliente = new Cliente();
		Cliente todoCliente = new  Cliente("VER TODO");
		cbCliente.addItem(todoCliente);
		cliente.cargarClientes(cbCliente);

		Categoria categoria = new Categoria();
		Categoria todaCategoria = new Categoria("VER TODO");
		cbCategoria.addItem(todaCategoria);
		categoria.cargarCategorias(cbCategoria);
		
		Categoria categoria_venta = new Categoria();
		Categoria todaCategoria_venta = new Categoria("VER TODO");
		cbCategoria_Venta.addItem(todaCategoria_venta);
		categoria_venta.cargarCategorias(cbCategoria_Venta);

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
		Usuarios todos = new Usuarios(0, "TODOS", "TODOS", "VER TODO", 0);
		cbUsuarios.addItem(todos);
		usu.cargarUsuarios(cbUsuarios);
		java.util.Date date = new Date();
		date.getTime();
		fInicial.setDate(date);
		fFinal.setDate(date);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnProdVencer) {
			actionPerformedBtnProdVencer(e);
		}
		if (e.getSource() == btnIngresos) {
			actionPerformedBtnIngresos(e);
		}
	}
	
	public void cargarBuscador() {
		ac1 = new TextAutoCompleter(txtProductos);
		ac2 = new TextAutoCompleter(txtProductosVenta);
		consultas consulta = new consultas();

		ac1.setMode(0);
		ac2.setMode(0);
		consulta.iniciar();
		
		ResultSet rs = consulta.cargarProductos();
		try {
			while (rs.next()) {
				ac1.addItem(rs.getString("producto") + " " + rs.getString("detalles")
						+ " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio")
						+ " * " + rs.getString("unimedida") + "  -  (" + rs.getString("codproducto") + ")");
				
				ac2.addItem(rs.getString("producto") + " " + rs.getString("detalles")
				+ " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio")
				+ " * " + rs.getString("unimedida") + "  -  (" + rs.getString("codproducto") + ")");
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

			int añoi = fInicial.getCalendar().get(Calendar.YEAR);
			int mesi = fInicial.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fInicial.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fFinal.getCalendar().get(Calendar.YEAR);
			int mesf = fFinal.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fFinal.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechai);
			java.sql.Timestamp timeStampDateI = new Timestamp(date.getTime());
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter2.parse(fechaf);
			java.sql.Timestamp timeStampDateF = new Timestamp(date2.getTime());
			
			
			
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
				String idusuario = "" + cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String metpago = "" + (cbMetodoPago.getSelectedIndex()-1);
			String cliente = cbCliente.getSelectedItem().toString();
				String idcliente = "" + cbCliente.getItemAt(cbCliente.getSelectedIndex()).getId();
			String categoria = cbCategoria_Venta.getSelectedItem().toString();
			String nomProducto = "";
				String idProd = "";
			
			if(cbUsuarios.getSelectedItem().toString().equals("VER TODO"))
				idusuario = "%%";
			if(metpago.equals("-1"))
				metpago = "%%";
			if (cliente.equals("VER TODO"))
				idcliente = "%%";
			if (categoria.equals("VER TODO"))
				categoria = "%%";
			if(txtProductosVenta.getText().length() == 0)
				idProd =  "%%";
			else {
				nomProducto = txtProductosVenta.getText();
				idProd = "" + Integer.parseInt(nomProducto.substring(nomProducto.indexOf("(") + 1, nomProducto.indexOf(")")));
			}
			
			
			double totalVenta = 0;
			double totalGanancia = 0;
			
			try {
				consulta.iniciar();
				rs = consulta.cargarTotalVentaYGanancia(timeStampDateI, timeStampDateF, metpago, idProd, categoria, idusuario, idcliente);
				rs.next();
				totalVenta = rs.getDouble("total");
				totalGanancia = rs.getDouble("ganancia");

				totalVenta = redondearDecimales(totalVenta, 2);
				totalGanancia = redondearDecimales(totalGanancia, 2);
				
			} catch (Exception ex) {
				//JOptionPane.showMessageDialog(null, "Error al cargar Totales y ganancia");
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
			
			
			
			
			Map<String, Object> parameters = new HashMap();
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			parameters.put("metpago", metpago);
			parameters.put("totalVenta", "" + totalVenta);
			parameters.put("totalGanancia", "" + totalGanancia);
			parameters.put("codproducto", idProd);
			parameters.put("categoria", categoria);
			parameters.put("idUsuario", idusuario);
			parameters.put("cliente", idcliente);
			
			
			new AbstractJasperReports().createReport(con, "rVentasDetalladasConFiltros.jasper", parameters);
			AbstractJasperReports.showViewer();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Reporte vacio " + ex);
		}
		
	}

	protected void actionPerformedBtnVerRanking(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = fInicial.getCalendar().get(Calendar.YEAR);
			int mesi = fInicial.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fInicial.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fFinal.getCalendar().get(Calendar.YEAR);
			int mesf = fFinal.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fFinal.getCalendar().get(Calendar.DAY_OF_MONTH);
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
			
			con = MySQLConexion.getConection();

			int añoi = fInicial.getCalendar().get(Calendar.YEAR);
			int mesi = fInicial.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fInicial.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fFinal.getCalendar().get(Calendar.YEAR);
			int mesf = fFinal.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fFinal.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechai);
			java.sql.Timestamp timeStampDateI = new Timestamp(date.getTime());
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter2.parse(fechaf);
			java.sql.Timestamp timeStampDateF = new Timestamp(date2.getTime());

			String nomProducto = txtProductos.getText();
			int idProd = Integer
					.parseInt(nomProducto.substring(nomProducto.indexOf("(") + 1, nomProducto.indexOf(")")));

			
			Map parameters = new HashMap();
			parameters.put("idProd", idProd);
			parameters.put("prod", nomProducto);
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			new AbstractJasperReports().createReport(con, "rProductoSalida.jasper", parameters);
			this.txtProductos.setText(null);
			AbstractJasperReports.showViewer();
			con.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error al cargar reporte");
		}
	}
	

	protected void actionPerformedBtnIngresos(ActionEvent e) {
		
		Connection con = null;
		try {
			
			con = MySQLConexion.getConection();

			int añoi = fInicial.getCalendar().get(Calendar.YEAR);
			int mesi = fInicial.getCalendar().get(Calendar.MARCH) + 1;
			int diai = fInicial.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = fFinal.getCalendar().get(Calendar.YEAR);
			int mesf = fFinal.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = fFinal.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechai);
			java.sql.Timestamp timeStampDateI = new Timestamp(date.getTime());
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter2.parse(fechaf);
			java.sql.Timestamp timeStampDateF = new Timestamp(date2.getTime());

			String nomProducto = txtProductos.getText();
			int idProd = Integer
					.parseInt(nomProducto.substring(nomProducto.indexOf("(") + 1, nomProducto.indexOf(")")));

			
			Map parameters = new HashMap();
			parameters.put("idProd", idProd);
			parameters.put("prod", nomProducto);
			parameters.put("prtFechaI", timeStampDateI);
			parameters.put("prtFechaF", timeStampDateF);
			new AbstractJasperReports().createReport(con, "rProductoIngreso.jasper", parameters);
			this.txtProductos.setText(null);
			AbstractJasperReports.showViewer();
			con.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error al cargar reporte");
		}
	}

	protected void actionPerformedButton(ActionEvent e) {
		
		String[] opciones = { "SIMPLE", "DETALLADO" };
		int seleccion = JOptionPane.showOptionDialog(null, "REPORTE", "Seleccione una opcion", -1, 3, null, opciones,
				opciones[0]);
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int añoi = this.fInicial.getCalendar().get(1);
			int mesi = this.fInicial.getCalendar().get(2) + 1;
			int diai = this.fInicial.getCalendar().get(5);
			String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

			int añof = this.fFinal.getCalendar().get(1);
			int mesf = this.fFinal.getCalendar().get(2) + 1;
			int diaf = this.fFinal.getCalendar().get(5);
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
	
	protected void actionPerformedBtnProdVencer(ActionEvent e) {
		 Connection con = null;
		    try {
		      con = MySQLConexion.getConection();

		      int añoi = this.fInicial.getCalendar().get(1);
		      int mesi = this.fInicial.getCalendar().get(2) + 1;
		      int diai = this.fInicial.getCalendar().get(5);
		      String fechai = añoi + "-" + mesi + "-" + diai + " 00:00:00";

		      int añof = this.fFinal.getCalendar().get(1);
		      int mesf = this.fFinal.getCalendar().get(2) + 1;
		      int diaf = this.fFinal.getCalendar().get(5);
		      String fechaf = añof + "-" + mesf + "-" + diaf + " 23:59:59";
		      Map parameters = new HashMap();
		      parameters.put("prtFechaI", fechai);
		      parameters.put("prmtFechaF", fechaf);

		      new AbstractJasperReports().createReport(con, "rProdVencer.jasper", parameters);
		      AbstractJasperReports.showViewer();
		      con.close();
		    }
		    catch (Exception ex) {
		      JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		    }
	}
	
}





