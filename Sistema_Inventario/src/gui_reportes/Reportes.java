package gui_reportes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import clases.AbstractJasperReports;
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

public class Reportes extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JButton btnGenerarRVD;
	private JButton btnCerrar;
	private JTextField txtCrearReportes;
	private JLabel lblVer;
	private JCalendar calendar;
	private JCalendar calendar_1;
	private JLabel lblAl;
	private JTextField textField;
	private JLabel lblVerDetalleDe;
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
	private JLabel lblImprimirCopiaDe;
	private JLabel lblNCopias;
	private JTextField txtNCopias;
	private JButton btnImprimir;
	private JTextField txtNVentaCopia;
	private JLabel lblNVenta;

	String usuario;
	Ventas v;
	private JButton btngenerarReporteVentas;
	private JComboBox <Usuarios> cbUsuarios;
	private JButton btnVerProductosIngresados;

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
		setBounds(100, 100, 1259, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnGenerarRVD = new JButton("<html>VER SOLO VENTAS</html>");
		btnGenerarRVD.setBounds(25, 363, 260, 62);
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

		calendar = new JCalendar();
		calendar.setBounds(25, 152, 260, 200);

		calendar_1 = new JCalendar();
		calendar_1.setBounds(339, 152, 260, 200);

		lblAl = new JLabel("al:");
		lblAl.setBounds(286, 294, 55, 38);
		lblAl.setHorizontalAlignment(SwingConstants.CENTER);
		lblAl.setFont(new Font("Tw Cen MT", Font.BOLD, 28));

		lblVerDetalleDe = new JLabel("DETALLE DE VENTA");
		lblVerDetalleDe.setBounds(25, 520, 574, 38);
		lblVerDetalleDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerDetalleDe.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));

		txtNVenta = new JTextField();
		txtNVenta.addKeyListener(this);
		txtNVenta.setBounds(251, 572, 161, 34);
		txtNVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNVenta.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtNVenta.setColumns(10);
		txtNVenta.setBackground(SystemColor.controlHighlight);

		btnGenerarRVDetallada = new JButton("Crear");
		btnGenerarRVDetallada.setBounds(417, 572, 141, 35);
		btnGenerarRVDetallada.addActionListener(this);
		btnGenerarRVDetallada.setForeground(new Color(255, 255, 255));
		btnGenerarRVDetallada.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarRVDetallada.setBackground(new Color(30, 144, 255));

		lblVerTotalDe = new JLabel("KARDEX");
		lblVerTotalDe.setBounds(770, 93, 360, 38);
		lblVerTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerTotalDe.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));

		btnGenerarMenores = new JButton("Crear");
		btnGenerarMenores.setBounds(1033, 174, 146, 35);
		btnGenerarMenores.addActionListener(this);
		btnGenerarMenores.setForeground(new Color(255, 255, 255));
		btnGenerarMenores.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarMenores.setBackground(new Color(30, 144, 255));
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(618, 63, 18, 549);
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
						textField_1.setBounds(0, 491, 619, 18);
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
				textField_3.setBounds(635, 314, 618, 18);
				contentPane.add(textField_3);
		contentPane.add(btnCerrar);
		contentPane.add(lblVer);
		contentPane.add(calendar);
		contentPane.add(lblAl);
		contentPane.add(calendar_1);
		contentPane.add(btnGenerarRVD);
		contentPane.add(lblVerDetalleDe);
		contentPane.add(btnGenerarRVDetallada);
		contentPane.add(txtNVenta);
		contentPane.add(lblVerTotalDe);
		contentPane.add(btnGenerarMenores);

		lblVerProductos = new JLabel("<html>Ver productos con<br> stock menor a: </html>");
		lblVerProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblVerProductos.setBounds(680, 158, 242, 51);
		contentPane.add(lblVerProductos);

		lblVerProductosMayores = new JLabel("<html>Ver productos con<br>stock mayor a: </html>");
		lblVerProductosMayores.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblVerProductosMayores.setBounds(680, 220, 242, 51);
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
		txtMenores.setBounds(933, 175, 95, 34);
		contentPane.add(txtMenores);
		txtMenores.setColumns(10);

		txtMayores = new JTextField();
		txtMayores.addKeyListener(this);
		txtMayores.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMayores.setBackground(SystemColor.controlHighlight);
		txtMayores.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtMayores.setColumns(10);
		txtMayores.setBounds(933, 236, 95, 35);
		contentPane.add(txtMayores);

		btnGenerarMayores = new JButton("Crear");
		btnGenerarMayores.addActionListener(this);
		btnGenerarMayores.setForeground(Color.WHITE);
		btnGenerarMayores.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnGenerarMayores.setBackground(new Color(30, 144, 255));
		btnGenerarMayores.setBounds(1033, 236, 146, 35);
		contentPane.add(btnGenerarMayores);

		lblNmeroDeVenta = new JLabel("N\u00FAmero de venta: ");
		lblNmeroDeVenta.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblNmeroDeVenta.setBounds(49, 572, 192, 30);
		contentPane.add(lblNmeroDeVenta);

		lblImprimirCopiaDe = new JLabel("<html>\u00A0Imprimir copia<br>de ticket de venta</html>");
		lblImprimirCopiaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimirCopiaDe.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		lblImprimirCopiaDe.setBounds(736, 365, 507, 78);
		contentPane.add(lblImprimirCopiaDe);

		lblNCopias = new JLabel("Cantidad de copias:");
		lblNCopias.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblNCopias.setBounds(680, 475, 192, 30);
		contentPane.add(lblNCopias);

		txtNCopias = new JTextField();
		txtNCopias.setText("1");
		txtNCopias.addKeyListener(this);
		txtNCopias.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNCopias.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtNCopias.setColumns(10);
		txtNCopias.setBackground(SystemColor.controlHighlight);
		txtNCopias.setBounds(882, 470, 146, 35);
		contentPane.add(txtNCopias);

		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(this);
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		btnImprimir.setBackground(new Color(30, 144, 255));
		btnImprimir.setBounds(1033, 467, 166, 91);
		contentPane.add(btnImprimir);

		txtNVentaCopia = new JTextField();
		txtNVentaCopia.addKeyListener(this);
		txtNVentaCopia.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNVentaCopia.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtNVentaCopia.setColumns(10);
		txtNVentaCopia.setBackground(SystemColor.controlHighlight);
		txtNVentaCopia.setBounds(882, 523, 146, 35);
		contentPane.add(txtNVentaCopia);

		lblNVenta = new JLabel("Ticket de venta N\u00B0:");
		lblNVenta.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblNVenta.setBounds(680, 528, 192, 30);
		contentPane.add(lblNVenta);
		
		btngenerarReporteVentas = new JButton("<html>VER\u00A0VENTAS<br>\u00A0Y DETALLES</html>");
		btngenerarReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtngenerarReporteVentas(e);
			}
		});
		btngenerarReporteVentas.setForeground(Color.WHITE);
		btngenerarReporteVentas.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btngenerarReporteVentas.setBackground(new Color(30, 144, 255));
		btngenerarReporteVentas.setBounds(339, 363, 260, 62);
		contentPane.add(btngenerarReporteVentas);
		
		cbUsuarios = new JComboBox();
		cbUsuarios.setBounds(167, 106, 285, 33);
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
		this.btnVerProductosIngresados.setBounds(25, 436, 574, 38);
		this.contentPane.add(this.btnVerProductosIngresados);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cbUsuarios, btnGenerarRVD, btngenerarReporteVentas, txtNVenta, btnGenerarRVDetallada, txtMenores, btnGenerarMenores, txtMayores, btnGenerarMayores, txtNCopias, txtNVentaCopia, btnImprimir, btnCerrar}));
		
		
		cargar();
	}

	private void cargar() {
		this.setLocationRelativeTo(null);
		
		Usuarios usu = new Usuarios();
		Usuarios todos = new Usuarios("TODOS", "TODOS", "TODOS", 0);
		cbUsuarios.addItem(todos);
		usu.cargarUsuarios(cbUsuarios);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnImprimir) {
			actionPerformedBtnImprimir(arg0);
		}
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

			/*new AbstractJasperReports().createReport(con, "rVentas.jasper", parameters);
			AbstractJasperReports.showViewer();*/
			
			if(usu.equals("TODOS")){
				new AbstractJasperReports().createReport(con, "rVentasTodos.jasper", parameters);
				AbstractJasperReports.showViewer();
			}
			else{
				parameters.put("prmtVendedor", usu);
				new AbstractJasperReports().createReport(con, "rVentasVendedor.jasper", parameters);
				AbstractJasperReports.showViewer();
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

			if(usu.equals("TODOS")){
				new AbstractJasperReports().createReport(con, "rVentasDetalladasTodos.jasper", parameters);
				AbstractJasperReports.showViewer();
			}
			else{
				parameters.put("prmtVendedor", usu);
				new AbstractJasperReports().createReport(con, "rVentasDetalladasVendedor.jasper", parameters);
				AbstractJasperReports.showViewer();
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
		if (opc == 0){
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
			System.exit(0);
		}
		else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	protected void btnGenerarCardexActionPerformed(ActionEvent arg0) {
		if (txtMenores.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene el campo correctamente");
		else {
			Connection con = null;
			try {

				con = MySQLConexion.getConection();
				float menores = Float.parseFloat(txtMenores.getText());
				Map<String, Object> parameters = new HashMap();
				parameters.put("prtcantidad", menores);
				new AbstractJasperReports().createReport(con, "rCardexMenores.jasper", parameters);
				AbstractJasperReports.showViewer();
				con.close();
				txtMenores.setText(null);
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
				con = MySQLConexion.getConection();
				float mayores = Float.parseFloat(txtMayores.getText());
				Map<String, Object> parameters = new HashMap();
				parameters.put("prtcantidad", mayores);
				new AbstractJasperReports().createReport(con, "rCardexMayores.jasper", parameters);
				AbstractJasperReports.showViewer();
				con.close();
				txtMayores.setText(null);
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
		if (arg0.getSource() == txtNCopias) {
			keyTypedTxtNCopias(arg0);
		}
		if (arg0.getSource() == txtNVentaCopia) {
			keyTypedTxtNVentaCopia(arg0);
		}
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

	protected void keyTypedTxtNVentaCopia(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)) {
			e.consume();
			if (c == (char) KeyEvent.VK_ENTER) {
				actionPerformedBtnImprimir(null);
			}
		}
	}

	protected void keyTypedTxtNCopias(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)) {
			e.consume();
			if (c == (char) KeyEvent.VK_ENTER) {
				actionPerformedBtnImprimir(null);
			}
		}
	}

	protected void actionPerformedBtnImprimir(ActionEvent arg0) {
		if (txtNVentaCopia.getText().equals("") || txtNCopias.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Llene los campos correctamente");
		else {
			int copias = Integer.parseInt(txtNCopias.getText());
			int confirmacion = JOptionPane.showConfirmDialog(null, "Imprimirá: " + copias + " copias.\n ¿Continuar?",
					"Alerta", JOptionPane.YES_NO_OPTION);
			if (confirmacion == 0) {
				for (int i = 0; i < copias; i++) {
					Connection con = null;
					try {
						con = MySQLConexion.getConection();
						Map<String, Object> parameters = new HashMap();
						parameters.put("prtNVenta", Integer.parseInt(txtNVentaCopia.getText()));
						/*
						 * new AbstractJasperReports().createReport(
						 * con.getConn(), "rPrueba.jasper", null);
						 * AbstractJasperReports.showViewer();
						 */
						try {
							JasperReport reporte = (JasperReport) JRLoader
									.loadObjectFromFile("bin/rComprobante.jasper");
							JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, con);
							JasperPrintManager.printReport(jasperPrint, false);
						} catch (JRException ex) {
							System.err.println("Error iReport: " + ex.getMessage());
						}
						con.close();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "ERROR " + e);
					}
				}
				txtNCopias.setText("1");
				txtNCopias.requestFocusInWindow();
				txtNVentaCopia.setText(null);
			}
		}
	}
	protected void actionPerformedBtnVerProductosIngresados(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String usu = cbUsuarios.getSelectedItem().toString();

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
			parameters.put("prmtFechaF", fechaf);

			/*new AbstractJasperReports().createReport(con, "rVentas.jasper", parameters);
			AbstractJasperReports.showViewer();*/
			new AbstractJasperReports().createReport(con, "rFechaIngreso.jasper", parameters);
			AbstractJasperReports.showViewer();
			con.close();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
		}
	}
}
