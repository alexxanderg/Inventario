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
import javax.swing.JCheckBox;

public class Reportes extends JInternalFrame {
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
	private JButton btnGenerarMenores;
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
	private JComboBox<Cliente> cbCliente;
	


	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JLabel lblProductos;
	private JLabel lblFiltros;
	private JCheckBox chckbxMayor;
	private JCheckBox chckbxMenorA;
	private JLabel lblMarca;
	private JComboBox comboBox;
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
		this.panel.setBackground(new Color(102, 205, 170));
		this.panel.setBounds(0, 0, 557, 639);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblVendedor = new JLabel("POR VENDEDOR:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(72, 85, 157, 23);
		this.panel.add(this.lblVendedor);
		
		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(249, 86, 219, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		this.cbMetodoPago.setBounds(249, 119, 219, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(72, 119, 157, 23);
		this.panel.add(this.lblMtodoDePago);
		
		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(72, 153, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar = new JDateChooser();
		this.calendar.setBounds(126, 153, 125, 23);
		this.panel.add(this.calendar);
		
		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(295, 153, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_1 = new JDateChooser();
		this.calendar_1.setBounds(343, 153, 125, 23);
		this.panel.add(this.calendar_1);
		
		this.btngenerarReporteVentas = new JButton("Ver reporte");
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(249, 194, 219, 40);
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
		lblHistorialDeCompras.setBounds(72, 259, 137, 32);
		panel.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.cbCliente = new JComboBox();
		cbCliente.setBounds(72, 291, 396, 23);
		panel.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.btnVerComprasCliente = new JButton("Ver reporte");
		btnVerComprasCliente.setBounds(249, 325, 219, 40);
		panel.add(btnVerComprasCliente);
		btnVerComprasCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerComprasCliente(e);
			}
		});
		this.btnVerComprasCliente.setForeground(Color.WHITE);
		this.btnVerComprasCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerComprasCliente.setBackground(new Color(30, 144, 255));
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(169, 169, 169));
		this.panel_1.setBounds(561, 0, 568, 639);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);
		
		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(16, 115, 143, 23);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.txtMenores = new JTextField();
		this.txtMenores.setBounds(166, 181, 145, 49);
		this.panel_1.add(this.txtMenores);
		this.txtMenores.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtMenores.setFont(new Font("Arial", Font.PLAIN, 16));
		this.txtMenores.setColumns(10);
		this.txtMenores.setBackground(SystemColor.controlHighlight);
		
		this.btnGenerarMenores = new JButton("Ver reporte");
		btnGenerarMenores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnGenerarMenores.setBounds(321, 181, 161, 49);
		this.panel_1.add(this.btnGenerarMenores);
		this.btnGenerarMenores.setForeground(Color.WHITE);
		this.btnGenerarMenores.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarMenores.setBackground(new Color(30, 144, 255));
		
		this.cbCategoria = new JComboBox();
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCategoria.setBounds(166, 114, 316, 23);
		this.panel_1.add(this.cbCategoria);
		
		this.lblDel = new JLabel("desde:");
		lblDel.setBounds(16, 312, 77, 23);
		panel_1.add(lblDel);
		this.lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDel.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_4 = new JDateChooser();
		calendar_4.setBounds(84, 312, 141, 23);
		panel_1.add(calendar_4);
		
		this.lblPorVencer = new JLabel("Con fecha de vencimiento:");
		lblPorVencer.setBounds(16, 273, 295, 23);
		panel_1.add(lblPorVencer);
		this.lblPorVencer.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPorVencer.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.label_17 = new JLabel("hasta:");
		label_17.setBounds(264, 312, 77, 23);
		panel_1.add(label_17);
		this.label_17.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_17.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_5 = new JDateChooser();
		calendar_5.setBounds(334, 312, 141, 23);
		panel_1.add(calendar_5);
		
		this.btnVerProductosQue = new JButton("Ver reporte");
		btnVerProductosQue.setBounds(166, 346, 215, 38);
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
		lblFiltros.setBounds(16, 86, 143, 23);
		panel_1.add(lblFiltros);
		
		chckbxMayor = new JCheckBox("Stock > a:");
		chckbxMayor.setFont(new Font("Candara", Font.BOLD, 20));
		chckbxMayor.setBackground(new Color(169, 169, 169));
		chckbxMayor.setBounds(16, 181, 143, 23);
		panel_1.add(chckbxMayor);
		
		chckbxMenorA = new JCheckBox("Stock < a:");
		chckbxMenorA.setFont(new Font("Candara", Font.BOLD, 20));
		chckbxMenorA.setBackground(new Color(169, 169, 169));
		chckbxMenorA.setBounds(16, 207, 143, 23);
		panel_1.add(chckbxMenorA);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(16, 147, 143, 23);
		panel_1.add(lblMarca);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox.setBounds(166, 146, 316, 23);
		panel_1.add(comboBox);
		
		this.lblRanking = new JLabel("RANKING");
		lblRanking.setBounds(10, 423, 537, 38);
		panel_1.add(lblRanking);
		this.lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRanking.setFont(new Font("Candara", Font.BOLD, 23));
		
		this.cbxRanking = new JComboBox();
		cbxRanking.setBounds(168, 466, 231, 33);
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
		calendar_6.setBounds(86, 510, 141, 23);
		panel_1.add(calendar_6);
		
		this.btnVerRanking = new JButton("Ver reporte");
		btnVerRanking.setBounds(166, 544, 231, 40);
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
		calendar_7.setBounds(336, 510, 148, 23);
		panel_1.add(calendar_7);
		
		this.lblHasta = new JLabel("hasta:");
		lblHasta.setBounds(266, 510, 77, 23);
		panel_1.add(lblHasta);
		this.lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHasta.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblDesde = new JLabel("desde:");
		lblDesde.setBounds(22, 502, 73, 38);
		panel_1.add(lblDesde);
		this.lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDesde.setFont(new Font("Candara", Font.BOLD, 20));

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
