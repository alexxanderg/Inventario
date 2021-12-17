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
	private JComboBox<Cliente> cbCliente;
	private JComboBox<Marcas> cbMarca;
	


	private TextAutoCompleter ac1;
	ResultSet rs;
	consultas consulta = new consultas();
	VentanaPrincipal vp;
	private JButton btnX;
	private JLabel lblProductos;
	private JLabel lblFiltros;
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
	private JButton btnVerReporteSimple;
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
		
		setBounds(100, 100, 780, 669);
		getContentPane().setLayout(null);
		
		this.panel = new JPanel();
		this.panel.setBackground(new Color(219, 112, 147));
		this.panel.setBounds(0, 0, 421, 502);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);
		
		this.lblVendedor = new JLabel("POR VENDEDOR:");
		this.lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblVendedor.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblVendedor.setBounds(16, 80, 157, 23);
		this.panel.add(this.lblVendedor);
		
		this.cbUsuarios = new JComboBox();
		this.cbUsuarios.setBounds(193, 81, 219, 23);
		this.panel.add(this.cbUsuarios);
		this.cbUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.cbMetodoPago = new JComboBox();
		cbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		this.cbMetodoPago.setBounds(193, 114, 219, 23);
		this.panel.add(this.cbMetodoPago);
		this.cbMetodoPago.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		this.lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblMtodoDePago.setBounds(16, 114, 157, 23);
		this.panel.add(this.lblMtodoDePago);
		
		this.label_1 = new JLabel("del:");
		this.label_1.setBounds(16, 148, 46, 23);
		this.panel.add(this.label_1);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar = new JDateChooser();
		this.calendar.setBounds(70, 148, 125, 23);
		this.panel.add(this.calendar);
		
		this.label_2 = new JLabel("al:");
		this.label_2.setBounds(239, 148, 55, 23);
		this.panel.add(this.label_2);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_1 = new JDateChooser();
		this.calendar_1.setBounds(287, 148, 125, 23);
		this.panel.add(this.calendar_1);
		
		this.btngenerarReporteVentas = new JButton("<html><center>Ver reporte detallado</center></html>");
		btngenerarReporteVentas.setVerticalAlignment(SwingConstants.TOP);
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		this.btngenerarReporteVentas.setBounds(221, 182, 191, 57);
		this.panel.add(this.btngenerarReporteVentas);
		this.btngenerarReporteVentas.setForeground(Color.WHITE);
		this.btngenerarReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btngenerarReporteVentas.setBackground(new Color(30, 144, 255));
		
		this.lblVentas = new JLabel("REPORTE DE VENTAS");
		this.lblVentas.setBounds(16, 21, 396, 32);
		this.panel.add(this.lblVentas);
		this.lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVentas.setFont(new Font("Candara", Font.BOLD, 30));
		
		this.lblHistorialDeCompras = new JLabel("POR CLIENTE:");
		lblHistorialDeCompras.setBounds(16, 263, 219, 23);
		panel.add(lblHistorialDeCompras);
		this.lblHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.cbCliente = new JComboBox();
		cbCliente.setBounds(16, 286, 396, 23);
		panel.add(cbCliente);
		this.cbCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.btnVerComprasCliente = new JButton("Ver reporte");
		btnVerComprasCliente.setBounds(16, 320, 396, 32);
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
		lblPorProducto.setBounds(16, 383, 232, 23);
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
		btnPorProducto.setBounds(16, 440, 396, 32);
		panel.add(btnPorProducto);
		
		txtProductos = new JTextField();
		txtProductos.setHorizontalAlignment(SwingConstants.LEFT);
		txtProductos.setFont(new Font("Arial", Font.PLAIN, 16));
		txtProductos.setColumns(10);
		txtProductos.setBackground(SystemColor.controlHighlight);
		txtProductos.setBounds(16, 406, 396, 23);
		panel.add(txtProductos);
		
		btnVerReporteSimple = new JButton("<html><center>Ver reporte simple</center></html>");
		btnVerReporteSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerReporteSimple(e);
			}
		});
		btnVerReporteSimple.setVerticalAlignment(SwingConstants.TOP);
		btnVerReporteSimple.setForeground(Color.WHITE);
		btnVerReporteSimple.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnVerReporteSimple.setBackground(new Color(30, 144, 255));
		btnVerReporteSimple.setBounds(16, 182, 191, 57);
		panel.add(btnVerReporteSimple);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(255, 222, 173));
		this.panel_1.setBounds(423, 0, 341, 639);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);
		
		this.lblCategora = new JLabel("Categor\u00EDa:");
		this.lblCategora.setBounds(16, 115, 110, 23);
		this.panel_1.add(this.lblCategora);
		this.lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.btnGenerarMenoresMayores = new JButton("Ver reporte");
		btnGenerarMenoresMayores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarMenores(e);
			}
		});
		this.btnGenerarMenoresMayores.setBounds(16, 199, 308, 32);
		this.panel_1.add(this.btnGenerarMenoresMayores);
		this.btnGenerarMenoresMayores.setForeground(Color.WHITE);
		this.btnGenerarMenoresMayores.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnGenerarMenoresMayores.setBackground(new Color(30, 144, 255));
		
		this.cbCategoria = new JComboBox();
		this.cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		this.cbCategoria.setBounds(166, 114, 158, 23);
		this.panel_1.add(this.cbCategoria);
		
		this.lblDel = new JLabel("desde:");
		lblDel.setBounds(16, 296, 77, 23);
		panel_1.add(lblDel);
		this.lblDel.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDel.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_4 = new JDateChooser();
		calendar_4.setBounds(84, 296, 141, 23);
		panel_1.add(calendar_4);
		
		this.lblPorVencer = new JLabel("CON FECHA DE VENCIMIENTO:");
		lblPorVencer.setBounds(16, 274, 308, 23);
		panel_1.add(lblPorVencer);
		this.lblPorVencer.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPorVencer.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.label_17 = new JLabel("hasta:");
		label_17.setBounds(16, 330, 77, 23);
		panel_1.add(label_17);
		this.label_17.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_17.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.calendar_5 = new JDateChooser();
		calendar_5.setBounds(84, 330, 141, 23);
		panel_1.add(calendar_5);
		
		this.btnVerProductosQue = new JButton("Ver reporte");
		btnVerProductosQue.setBounds(16, 361, 308, 32);
		panel_1.add(btnVerProductosQue);
		btnVerProductosQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVerProductosQue(e);
			}
		});
		this.btnVerProductosQue.setForeground(Color.WHITE);
		this.btnVerProductosQue.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.btnVerProductosQue.setBackground(new Color(30, 144, 255));
		
		lblProductos = new JLabel("RE. DE PRODUCTOS");
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setFont(new Font("Candara", Font.BOLD, 30));
		lblProductos.setBounds(10, 21, 314, 32);
		panel_1.add(lblProductos);
		
		lblFiltros = new JLabel("FILTROS:");
		lblFiltros.setForeground(new Color(0, 0, 205));
		lblFiltros.setFont(new Font("Candara", Font.BOLD, 25));
		lblFiltros.setBounds(16, 77, 143, 32);
		panel_1.add(lblFiltros);
		
		chckbxMenorA = new JCheckBox("Mostrar valor de inventario");
		chckbxMenorA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedChckbxMenorA(e);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mouseEnteredChckbxMenorA(arg0);
			}
		});
		chckbxMenorA.setFont(new Font("Candara", Font.BOLD, 20));
		chckbxMenorA.setBackground(new Color(255, 222, 173));
		chckbxMenorA.setBounds(16, 177, 295, 23);
		panel_1.add(chckbxMenorA);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(16, 147, 110, 23);
		panel_1.add(lblMarca);
		
		cbMarca = new JComboBox();
		cbMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMarca.setBounds(166, 146, 158, 23);
		panel_1.add(cbMarca);
		
		this.lblRanking = new JLabel("RANKING de productos:");
		lblRanking.setBounds(16, 404, 231, 32);
		panel_1.add(lblRanking);
		this.lblRanking.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblRanking.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.cbxRanking = new JComboBox();
		cbxRanking.setBounds(16, 436, 231, 32);
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
		calendar_6.setBounds(84, 479, 141, 23);
		panel_1.add(calendar_6);
		
		this.btnVerRanking = new JButton("Ver reporte");
		btnVerRanking.setBounds(16, 549, 315, 32);
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
		calendar_7.setBounds(86, 513, 148, 23);
		panel_1.add(calendar_7);
		
		this.lblHasta = new JLabel("hasta:");
		lblHasta.setBounds(16, 520, 77, 16);
		panel_1.add(lblHasta);
		this.lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHasta.setFont(new Font("Candara", Font.BOLD, 20));
		
		this.lblDesde = new JLabel("desde:");
		lblDesde.setBounds(20, 478, 73, 24);
		panel_1.add(lblDesde);
		this.lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblDesde.setFont(new Font("Candara", Font.BOLD, 20));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(147, 112, 219));
		panel_2.setBounds(0, 508, 421, 131);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblReporteDeCompras = new JLabel("REPORTE DE INGRESOS");
		lblReporteDeCompras.setVisible(false);
		lblReporteDeCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeCompras.setFont(new Font("Candara", Font.BOLD, 30));
		lblReporteDeCompras.setBounds(10, 11, 410, 32);
		panel_2.add(lblReporteDeCompras);
		
		label = new JLabel("del:");
		label.setVisible(false);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBounds(20, 54, 46, 23);
		panel_2.add(label);
		
		calRI01 = new JDateChooser();
		calRI01.setVisible(false);
		calRI01.setBounds(74, 54, 125, 23);
		panel_2.add(calRI01);
		
		label_3 = new JLabel("al:");
		label_3.setVisible(false);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Candara", Font.BOLD, 20));
		label_3.setBounds(243, 54, 55, 23);
		panel_2.add(label_3);
		
		calRI02 = new JDateChooser();
		calRI02.setVisible(false);
		calRI02.setBounds(291, 54, 97, 23);
		panel_2.add(calRI02);
		
		button = new JButton("Ver reporte");
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedButton(e);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(20, 88, 368, 32);
		panel_2.add(button);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE T�TULO
		
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
			
			calRI01.setDate(date);
			calRI02.setDate(date);
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
	

	protected void actionPerformedBtnVerReporteSimple(ActionEvent e) {
		Connection con = null;
	    try{
			con = MySQLConexion.getConection();
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex()- 1;
	      
	      
			int a�oi = calendar.getCalendar().get(Calendar.YEAR);
			int mesi = calendar.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";
			
			int a�of = calendar_1.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_1.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_1.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = a�of + "-" + mesf + "-" + diaf + " 23:59:59";
			
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
				if (metpago == - 1) {
					new AbstractJasperReports().createReport(con, "rVentasTodos.jasper", parameters);
					AbstractJasperReports.showViewer();
				} else {
					new AbstractJasperReports().createReport(con, "rVentasVendedorTodosXMpago.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			} else {
				parameters.put("prmtVendedor", usu);
				if (metpago == - 1) {
					new AbstractJasperReports().createReport(con, "rVentasVendedor.jasper", parameters);
					AbstractJasperReports.showViewer();
					
				} else {
					new AbstractJasperReports().createReport(con, "rVentasVendedorTodoMetodoxUsuario.jasper",
							parameters);
					AbstractJasperReports.showViewer();
				}
			}
			con.close();
	    }
	    catch (Exception ex)
	    {
	      JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
	    }
	}
	
	protected void actionPerformedBtngenerarReporteVentas(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			int idusuario = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getIdusuario();
			String usu = cbUsuarios.getItemAt(cbUsuarios.getSelectedIndex()).getUsuario();
			int metpago = cbMetodoPago.getSelectedIndex()- 1;
			
			
			int a�oi = calendar.getCalendar().get(Calendar.YEAR);
			int mesi = calendar.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";

			int a�of = calendar_1.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_1.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_1.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = a�of + "-" + mesf + "-" + diaf + " 23:59:59";

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

			int a�oi = calendar_6.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_6.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_6.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";

			int a�of = calendar_7.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_7.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_7.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = a�of + "-" + mesf + "-" + diaf + " 23:59:59";
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
			
			if(chckbxMenorA.isSelected()){
				if ( categoria.equals("TODAS") && marca.equals("TODAS")) {
					
					Map<String, Object> parameters = new HashMap();
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rCompraTodos.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if( categoria.equals("TODAS") && !(marca.equals("TODAS")) ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("marca", marca);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rCompraMarca.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if ( !(categoria.equals("TODAS")) && marca.equals("TODAS") ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("categoria", categoria);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rCompraCategoria.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				else if ( !(categoria.equals("TODAS")) && !(marca.equals("TODAS")) ){
					Map<String, Object> parameters = new HashMap();
					
					parameters.put("categoria", categoria);
					parameters.put("marca", marca);
					parameters.put("cantidad", cantidad);
					
					new AbstractJasperReports().createReport(con, "rCompraCategoriaMarca.jasper", parameters);
					AbstractJasperReports.showViewer();	
				}
				
				con.close();
			}
			else{
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
			}
			
			
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

	protected void actionPerformedBtnVerProductosQue(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			int a�oi = calendar_4.getCalendar().get(Calendar.YEAR);
			int mesi = calendar_4.getCalendar().get(Calendar.MARCH) + 1;
			int diai = calendar_4.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";

			int a�of = calendar_5.getCalendar().get(Calendar.YEAR);
			int mesf = calendar_5.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = calendar_5.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaf = a�of + "-" + mesf + "-" + diaf + " 23:59:59";
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
	protected void mouseClickedChckbxMenorA(MouseEvent e) {
		
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

	      int a�oi = this.calRI01.getCalendar().get(1);
	      int mesi = this.calRI01.getCalendar().get(2) + 1;
	      int diai = this.calRI01.getCalendar().get(5);
	      String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";

	      int a�of = this.calRI02.getCalendar().get(1);
	      int mesf = this.calRI02.getCalendar().get(2) + 1;
	      int diaf = this.calRI02.getCalendar().get(5);
	      String fechaf = a�of + "-" + mesf + "-" + diaf + " 23:59:59";
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
	protected void mouseEnteredChckbxMenorA(MouseEvent arg0) {
	}
}
