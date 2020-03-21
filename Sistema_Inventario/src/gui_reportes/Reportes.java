package gui_reportes;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.AbstractJasperReports;
import clases.Categoria;
import clases.Cliente;
import clases.Usuarios;
import gui_principal.VentanaPrincipal;
import mysql.MySQLConexion;
import mysql.consultas;

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

public class Reportes extends JInternalFrame {
	private JComboBox <Usuarios> cbUsuarios;
	private JComboBox cbMetodoPago;
	private JLabel label_1;
	private JDateChooser calendar;
	private JLabel label_2;
	private JDateChooser calendar_1;
	private JButton btngenerarReporteVentas;
	private JButton btnGenerarRVD;
	private JLabel lblIngreseNroDe;
	private JTextField txtNVenta;
	private JButton btnGenerarRVDetallada;
	private JLabel label_5;
	private JLabel label_7;
	private JComboBox cbxRanking;
	private JLabel label_8;
	private JDateChooser calendar_6;
	private JLabel label_9;
	private JDateChooser calendar_7;
	private JButton btnVerRanking;
	private JLabel lblVerStockDe;
	private JLabel lblCategora;
	private JTextField txtMenores;
	private JLabel lblMenoresA;
	private JLabel lblMayoresA;
	private JTextField txtMayores;
	private JButton btnGenerarMayores;
	private JButton btnGenerarMenores;
	private JLabel lblHistorialDeCompras;
	private JButton btnVerComprasCliente;
	private JLabel label_15;
	private JLabel lblDel;
	private JDateChooser calendar_4;
	private JButton btnVerProductosQue;
	private JLabel label_17;
	private JDateChooser calendar_5;
	private JPanel panel;
	private JLabel lblVendedor;
	private JLabel lblMtodoDePago;
	private JPanel panel_1;
	private JLabel label_19;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblVerDetalleDe;
	private JPanel panel_4;
	private JPanel panel_6;
	private JComboBox<Categoria> cbCategoria;
	private JComboBox<Cliente> cbCliente;
	


	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JButton btnXX;
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
		this.panel.setBackground(new Color(176, 224, 230));
		this.panel.setBounds(0, 0, 557, 236);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblVendedor = new JLabel("Vendedor:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(20, 63, 117, 23);
		this.panel.add(this.lblVendedor);
		
		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(197, 64, 219, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		this.cbMetodoPago.setBounds(197, 97, 219, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(20, 97, 157, 23);
		this.panel.add(this.lblMtodoDePago);
		
		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(20, 131, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar = new JDateChooser();
		this.calendar.setBounds(74, 131, 125, 23);
		this.panel.add(this.calendar);
		
		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(243, 131, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_1 = new JDateChooser();
		this.calendar_1.setBounds(291, 131, 125, 23);
		this.panel.add(this.calendar_1);
		
		this.btnGenerarRVD = new JButton("<html>Ver solo ventas</html>");
		btnGenerarRVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnGenerarRVD(arg0);
			}
		});
		this.btnGenerarRVD.setBounds(20, 175, 260, 40);
		this.panel.add(this.btnGenerarRVD);
		this.btnGenerarRVD.setForeground(Color.WHITE);
		this.btnGenerarRVD.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarRVD.setBackground(new Color(30, 144, 255));
		
		this.btngenerarReporteVentas = new JButton("<html>Ver ventas y detalles</html>");
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(291, 175, 260, 40);
		this.panel.add(this.btngenerarReporteVentas);
		this.btngenerarReporteVentas.setForeground(Color.WHITE);
		this.btngenerarReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentas.setBackground(new Color(30, 144, 255));
		
		this.label_19 = new JLabel("VENTAS REALIZADAS");
		this.label_19.setBounds(16, 20, 535, 32);
		this.panel.add(this.label_19);
		this.label_19.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_19.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(192, 192, 192));
		this.panel_1.setBounds(561, 0, 568, 212);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);
		
		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(20, 48, 143, 31);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblMenoresA = new JLabel("Menores a:");
		this.lblMenoresA.setBounds(20, 101, 143, 23);
		this.panel_1.add(this.lblMenoresA);
		this.lblMenoresA.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblMayoresA = new JLabel("Mayores a: ");
		this.lblMayoresA.setBounds(20, 135, 143, 23);
		this.panel_1.add(this.lblMayoresA);
		this.lblMayoresA.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.txtMayores = new JTextField();
		this.txtMayores.setBounds(170, 140, 145, 23);
		this.panel_1.add(this.txtMayores);
		this.txtMayores.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtMayores.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtMayores.setColumns(10);
		this.txtMayores.setBackground(SystemColor.controlHighlight);
		
		this.txtMenores = new JTextField();
		this.txtMenores.setBounds(170, 99, 145, 23);
		this.panel_1.add(this.txtMenores);
		this.txtMenores.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtMenores.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtMenores.setColumns(10);
		this.txtMenores.setBackground(SystemColor.controlHighlight);
		
		this.btnGenerarMenores = new JButton("Crear");
		btnGenerarMenores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnGenerarMenores.setBounds(325, 98, 161, 31);
		this.panel_1.add(this.btnGenerarMenores);
		this.btnGenerarMenores.setForeground(Color.WHITE);
		this.btnGenerarMenores.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarMenores.setBackground(new Color(30, 144, 255));
		
		this.btnGenerarMayores = new JButton("Crear");
		btnGenerarMayores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMayores(e);
			}
		});
		this.btnGenerarMayores.setBounds(325, 140, 161, 31);
		this.panel_1.add(this.btnGenerarMayores);
		this.btnGenerarMayores.setForeground(Color.WHITE);
		this.btnGenerarMayores.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarMayores.setBackground(new Color(30, 144, 255));
		
		this.cbCategoria = new JComboBox();
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCategoria.setBounds(170, 52, 316, 23);
		this.panel_1.add(this.cbCategoria);
		
		this.lblVerStockDe = new JLabel("VER STOCK DE PRODUCTOS");
		this.lblVerStockDe.setBounds(52, 5, 440, 32);
		this.panel_1.add(this.lblVerStockDe);
		this.lblVerStockDe.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVerStockDe.setFont(new Font("Candara", Font.BOLD, 23));
		
		btnXX = new JButton("X");
		btnXX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnXX(arg0);
			}
		});
		btnXX.setForeground(Color.WHITE);
		btnXX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnXX.setBackground(new Color(220, 20, 60));
		btnXX.setBounds(494, 0, 63, 30);
		panel_1.add(btnXX);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBackground(new Color(192, 192, 192));
		this.panel_2.setBounds(0, 240, 557, 146);
		getContentPane().add(this.panel_2);
		this.panel_2.setLayout(null);
		
		this.lblIngreseNroDe = new JLabel("Ingrese Nro de Venta:");
		this.lblIngreseNroDe.setBounds(18, 77, 191, 23);
		this.panel_2.add(this.lblIngreseNroDe);
		this.lblIngreseNroDe.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.txtNVenta = new JTextField();
		this.txtNVenta.setBounds(211, 75, 103, 23);
		this.panel_2.add(this.txtNVenta);
		this.txtNVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtNVenta.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtNVenta.setColumns(10);
		this.txtNVenta.setBackground(SystemColor.controlHighlight);
		
		this.btnGenerarRVDetallada = new JButton("Buscar venta");
		btnGenerarRVDetallada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarRVDetallada(e);
			}
		});
		this.btnGenerarRVDetallada.setBounds(324, 71, 222, 30);
		this.panel_2.add(this.btnGenerarRVDetallada);
		this.btnGenerarRVDetallada.setForeground(Color.WHITE);
		this.btnGenerarRVDetallada.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarRVDetallada.setBackground(new Color(30, 144, 255));
		
		this.lblVerDetalleDe = new JLabel("VER  DETALLE DE VENTA ESPEC\u00CDFICA");
		this.lblVerDetalleDe.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVerDetalleDe.setFont(new Font("Candara", Font.BOLD, 23));
		this.lblVerDetalleDe.setBounds(10, 25, 535, 32);
		this.panel_2.add(this.lblVerDetalleDe);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBackground(new Color(176, 224, 230));
		this.panel_3.setBounds(561, 216, 568, 190);
		getContentPane().add(this.panel_3);
		this.panel_3.setLayout(null);
		
		this.lblHistorialDeCompras = new JLabel("HISTORIAL DE COMPRAS DE CLIENTES");
		lblHistorialDeCompras.setBounds(10, 40, 537, 32);
		panel_3.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.cbCliente = new JComboBox();
		cbCliente.setBounds(48, 83, 463, 23);
		panel_3.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.btnVerComprasCliente = new JButton("Ver compras");
		btnVerComprasCliente.setBounds(203, 124, 165, 31);
		panel_3.add(btnVerComprasCliente);
		btnVerComprasCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerComprasCliente(e);
			}
		});
		this.btnVerComprasCliente.setForeground(Color.WHITE);
		this.btnVerComprasCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerComprasCliente.setBackground(new Color(30, 144, 255));
		
		this.panel_4 = new JPanel();
		this.panel_4.setBackground(new Color(176, 224, 230));
		this.panel_4.setLayout(null);
		this.panel_4.setBounds(0, 390, 557, 249);
		getContentPane().add(this.panel_4);
		
		this.label_7 = new JLabel("RANKING DE PRODUCTOS");
		this.label_7.setBounds(10, 34, 537, 38);
		this.panel_4.add(this.label_7);
		this.label_7.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_7.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.cbxRanking = new JComboBox();
		this.cbxRanking.setFont(new Font("Candara", Font.BOLD, 20));
		this.cbxRanking.setModel(new DefaultComboBoxModel(new String[] {"M\u00E1s vendidos", "Menos vendidos"}));
		this.cbxRanking.setBounds(162, 77, 231, 33);
		this.panel_4.add(this.cbxRanking);
		this.cbxRanking.setToolTipText("");
		
		this.label_8 = new JLabel("del:");
		this.label_8.setBounds(20, 121, 55, 23);
		this.panel_4.add(this.label_8);
		this.label_8.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_8.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_6 = new JDateChooser();
		this.calendar_6.setBounds(74, 121, 125, 23);
		this.panel_4.add(this.calendar_6);
		
		this.btnVerRanking = new JButton("Ver ranking");
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerRanking(e);
			}
		});
		this.btnVerRanking.setBounds(162, 164, 231, 40);
		this.panel_4.add(this.btnVerRanking);
		this.btnVerRanking.setForeground(Color.WHITE);
		this.btnVerRanking.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerRanking.setBackground(new Color(30, 144, 255));
		
		this.label_9 = new JLabel("al:");
		this.label_9.setBounds(243, 121, 55, 23);
		this.panel_4.add(this.label_9);
		this.label_9.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_9.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_7 = new JDateChooser();
		this.calendar_7.setBounds(292, 121, 125, 23);
		this.panel_4.add(this.calendar_7);
		
		this.label_5 = new JLabel("del:");
		this.label_5.setBounds(393, 448, 55, 38);
		getContentPane().add(this.label_5);
		this.label_5.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_5.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.panel_6 = new JPanel();
		this.panel_6.setBackground(new Color(192, 192, 192));
		this.panel_6.setLayout(null);
		this.panel_6.setBounds(561, 410, 568, 229);
		getContentPane().add(this.panel_6);
		
		this.label_15 = new JLabel("PRODUCTOS POR VENCER");
		this.label_15.setBounds(10, 27, 537, 32);
		this.panel_6.add(this.label_15);
		this.label_15.setHorizontalAlignment(SwingConstants.CENTER);
		this.label_15.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.lblDel = new JLabel("desde:");
		this.lblDel.setBounds(33, 78, 109, 23);
		this.panel_6.add(this.lblDel);
		this.lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDel.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.btnVerProductosQue = new JButton("Ver productos");
		btnVerProductosQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerProductosQue(e);
			}
		});
		this.btnVerProductosQue.setBounds(174, 126, 215, 38);
		this.panel_6.add(this.btnVerProductosQue);
		this.btnVerProductosQue.setForeground(Color.WHITE);
		this.btnVerProductosQue.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerProductosQue.setBackground(new Color(30, 144, 255));
		
		this.calendar_4 = new JDateChooser();
		this.calendar_4.setBounds(101, 78, 141, 23);
		this.panel_6.add(this.calendar_4);
		
		this.label_17 = new JLabel("hasta:");
		this.label_17.setBounds(281, 78, 109, 23);
		this.panel_6.add(this.label_17);
		this.label_17.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_17.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_5 = new JDateChooser();
		this.calendar_5.setBounds(351, 78, 141, 23);
		this.panel_6.add(this.calendar_5);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
	}

		private void cargar() {
			Cliente cliente = new Cliente();
			cliente.cargarClientes(cbCliente);

			Categoria categoria = new Categoria();
			Categoria todaCategoria = new Categoria("TODAS");
			cbCategoria.addItem(todaCategoria);
			categoria.cargarCategorias(cbCategoria);

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
	}
		
	protected void actionPerformedBtnXX(ActionEvent arg0){
		this.dispose();
	}
	
	
	protected void actionPerformedBtnGenerarRVD(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
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
	protected void actionPerformedBtnGenerarRVDetallada(ActionEvent e) {
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
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "No se encontÃ³ la venta " + ex);
			}
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
			JOptionPane.showMessageDialog(null, "No se encontÃ³ la venta " + ex);
		}
	}
	protected void actionPerformedBtnGenerarMenores(ActionEvent e) {
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
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
			}
		}
	}
	protected void actionPerformedBtnGenerarMayores(ActionEvent e) {
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

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "No se encontraron productos " + ex);
			}
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
}
