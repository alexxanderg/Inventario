package gui_compras;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_principal.VentanaPrincipal;
import mysql.MySQLConexion;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import clases.AbstractJasperReports;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class MantenimientoCompras extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearCompra;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	public JTable tbCompras;
	public VentanaPrincipal vp;
	private JScrollPane scrollPane_1;
	private JLabel lblDetallesDeCompras;
	private JTable tbDetallesCompra;
	

	NuevaCompra nc = new NuevaCompra(0, null);
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	int idusuario = 0;
	DefaultTableModel dtmC = new DefaultTableModel();
	DefaultTableModel dtmCD = new DefaultTableModel();
	private JLabel label;
	private JDateChooser dchDesde;
	private JDateChooser dchHasta;
	private JLabel label_1;
	private JButton btnVerCompras;
	private JButton btnGenerarReporte;
	private JLabel label_2;
	private JTextField txtSerie;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnComprobante;
	private JButton btnLote;
	private JTextField txtLote;
	private JLabel label_3;
	private JTextField txtNserie;
	private JLabel label_4;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoCompras frame = new MantenimientoCompras(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoCompras(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("USUARIOS");
		setBounds(100, 100, 780, 679);
		getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 124, 738, 200);
		getContentPane().add(this.scrollPane);
		
		tbCompras = new JTable();
		tbCompras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTbCompras(arg0);
			}
		});
		tbCompras.setAutoCreateRowSorter(true);
		tbCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCompras.setFont(new Font("Arial", Font.ITALIC, 14));
		tbCompras.setBackground(Color.WHITE);
		tbCompras.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCompras);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane_1.setAutoscrolls(true);
		scrollPane_1.setBounds(10, 390, 738, 200);
		getContentPane().add(scrollPane_1);
		
		tbDetallesCompra = new JTable();
		tbDetallesCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDetallesCompra.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		tbDetallesCompra.setAutoCreateRowSorter(true);
		tbDetallesCompra.setFont(new Font("Arial", Font.ITALIC, 14));
		tbDetallesCompra.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tbDetallesCompra);
		
		lblDetallesDeCompras = new JLabel("Detalles de Compra:");
		lblDetallesDeCompras.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetallesDeCompras.setForeground(Color.DARK_GRAY);
		lblDetallesDeCompras.setFont(new Font("Candara", Font.BOLD, 25));
		lblDetallesDeCompras.setBounds(10, 355, 331, 34);
		getContentPane().add(lblDetallesDeCompras);
		
		label = new JLabel("Desde:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBackground(new Color(50, 205, 50));
		label.setBounds(23, 10, 71, 30);
		getContentPane().add(label);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(23, 37, 130, 30);
		getContentPane().add(dchDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(163, 37, 130, 30);
		getContentPane().add(dchHasta);
		
		label_1 = new JLabel("Hasta:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1.setBackground(new Color(50, 205, 50));
		label_1.setBounds(163, 10, 71, 30);
		getContentPane().add(label_1);
		
		btnVerCompras = new JButton("Buscar");
		btnVerCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnVerCompras(arg0);
			}
		});
		btnVerCompras.setForeground(Color.WHITE);
		btnVerCompras.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVerCompras.setBackground(new Color(30, 144, 255));
		btnVerCompras.setBounds(163, 79, 130, 34);
		getContentPane().add(btnVerCompras);
		
		btnGenerarReporte = new JButton("<html><center>EXPORTAR<br>COMPRAS</center></html>");
		btnGenerarReporte.setVisible(false);
		btnGenerarReporte.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarReporte(e);
			}
		});
		btnGenerarReporte.setForeground(new Color(138, 43, 226));
		btnGenerarReporte.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGenerarReporte.setBackground(Color.WHITE);
		btnGenerarReporte.setBounds(225, 10, 71, 30);
		getContentPane().add(btnGenerarReporte);
		
		label_2 = new JLabel("Por nro comprobante:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Candara", Font.BOLD, 20));
		label_2.setBackground(new Color(50, 205, 50));
		label_2.setBounds(318, 10, 210, 30);
		getContentPane().add(label_2);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(328, 44, 71, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(10, 13, 5, 100);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(303, 10, 5, 100);
		getContentPane().add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlight);
		panel_2.setBounds(523, 10, 5, 100);
		getContentPane().add(panel_2);
		
		btnComprobante = new JButton("Buscar");
		btnComprobante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnComprobante(e);
			}
		});
		btnComprobante.setForeground(Color.WHITE);
		btnComprobante.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnComprobante.setBackground(new Color(30, 144, 255));
		btnComprobante.setBounds(353, 79, 130, 34);
		getContentPane().add(btnComprobante);
		
		btnLote = new JButton("Buscar");
		btnLote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnLote(e);
			}
		});
		btnLote.setForeground(Color.WHITE);
		btnLote.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLote.setBackground(new Color(30, 144, 255));
		btnLote.setBounds(577, 79, 130, 34);
		getContentPane().add(btnLote);
		
		txtLote = new JTextField();
		txtLote.setColumns(10);
		txtLote.setBounds(567, 44, 141, 20);
		getContentPane().add(txtLote);
		
		label_3 = new JLabel("Por lote:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("Candara", Font.BOLD, 20));
		label_3.setBackground(new Color(50, 205, 50));
		label_3.setBounds(538, 10, 210, 30);
		getContentPane().add(label_3);
		
		txtNserie = new JTextField();
		txtNserie.setColumns(10);
		txtNserie.setBounds(418, 44, 95, 20);
		getContentPane().add(txtNserie);
		
		label_4 = new JLabel("-");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Candara", Font.BOLD, 20));
		label_4.setBackground(new Color(50, 205, 50));
		label_4.setBounds(397, 44, 21, 20);
		getContentPane().add(label_4);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCompras.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		mnCrearCompra = new JMenu("|Registrar nueva compra| ");
		mnCrearCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnCrearCompra(e);
			}
		});
		mnCrearCompra.setForeground(new Color(30, 144, 255));
		mnCrearCompra.setBackground(SystemColor.control);
		mnCrearCompra.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnCrearCompra);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE T�TULO
		
		cargar();
		ajustarAnchoColumnas();		
	}
	
	public void cargar() {
		tb = this.tbCompras;
		tb.setRowHeight(30);
		tb.setModel(dtmC);
		dtmC.setColumnIdentifiers(new Object[]{"NRO", "SERIE", "DISTRIBUIDOR", "NOTA", "F EMISI�N", "TOTAL"});
		
		JTable tbCD = this.tbDetallesCompra;
		tbCD.setRowHeight(30);
		tbCD.setModel(dtmCD);
		dtmCD.setColumnIdentifiers(new Object[]{"CANTIDAD", "PRODUCTO", "PRECIO UNI COMP", "SUB TOTAL", "LOTE"});
		
		
		
		java.util.Date date = new Date();
		date.getTime();
		dchDesde.setDate(date);
		dchHasta.setDate(date);
	}
	
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {// "NRO", "SERIE", "DISTRIBUIDOR", "NOTA", "F EMISI�N", "TOTAL"
		TableColumnModel tcmC = tbCompras.getColumnModel();
		tcmC.getColumn(0).setPreferredWidth(anchoColumna(10));  // 
		tcmC.getColumn(1).setPreferredWidth(anchoColumna(20));  // 
		tcmC.getColumn(2).setPreferredWidth(anchoColumna(20));  // 
		tcmC.getColumn(3).setPreferredWidth(anchoColumna(20));  // 
		tcmC.getColumn(4).setPreferredWidth(anchoColumna(20));  //
		tcmC.getColumn(5).setPreferredWidth(anchoColumna(10));
		
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCompras.getColumnModel().getColumn(4).setCellRenderer(tcr0);
		tbCompras.getColumnModel().getColumn(5).setCellRenderer(tcr0);
		
		//"CANTIDAD", "PRODUCTO", "PRECIO UNI", "SUB TOTAL"
		TableColumnModel tcmCD = tbDetallesCompra.getColumnModel();
		tcmCD.getColumn(0).setPreferredWidth(anchoColumna(10));  // 
		tcmCD.getColumn(1).setPreferredWidth(anchoColumna(50));  // 
		tcmCD.getColumn(2).setPreferredWidth(anchoColumna(15));  // 
		tcmCD.getColumn(3).setPreferredWidth(anchoColumna(15));  //
		tcmCD.getColumn(4).setPreferredWidth(anchoColumna(10));  // 
		
		tbDetallesCompra.getColumnModel().getColumn(0).setCellRenderer(tcr0);
		tbDetallesCompra.getColumnModel().getColumn(2).setCellRenderer(tcr0);
		tbDetallesCompra.getColumnModel().getColumn(3).setCellRenderer(tcr0);
		tbDetallesCompra.getColumnModel().getColumn(4).setCellRenderer(tcr0);
	}
	
	protected void mouseClickedMnCrearCompra(MouseEvent e) {
		try {
			idusuario = Integer.parseInt(vp.lblIdusuario.getText());
			if (nc.isShowing()) { //JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nc.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nc.setVisible(true); 
			} else {
				nc = new NuevaCompra(idusuario, this);
				nc.setLocationRelativeTo(null);
				nc.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	protected void mouseClickedTbCompras(MouseEvent arg0) {
		int nroCompra = -1;
		nroCompra = Integer.parseInt( tbCompras.getValueAt(tbCompras.getSelectedRow(), 0).toString());
		
		for (int i = 0; i < tbDetallesCompra.getRowCount(); i++) {
			dtmCD.removeRow(i);
			i -= 1;
		}
		
		try {
			consulta.iniciar();
			rs = consulta.buscarCompraDetalle(nroCompra);
			while (rs.next()){
				dtmCD.addRow(new Object[]{rs.getFloat("cantidad"), rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color"), rs.getFloat("preUni"), rs.getFloat("preSubT"), rs.getString("lote")});
			}
			consulta.reset();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar compra detalle " + e);
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

	}
	
	protected void actionPerformedBtnVerCompras(ActionEvent arg0) {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			dtmC.removeRow(i);
			i -= 1;
		}
		for (int i = 0; i < tbDetallesCompra.getRowCount(); i++) {
			dtmCD.removeRow(i);
			i -= 1;
		}
		
		try {
				
			int a�oi = dchDesde.getCalendar().get(Calendar.YEAR);
			int mesi = dchDesde.getCalendar().get(Calendar.MARCH) + 1;
			int diai = dchDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaInicial = a�oi + "-" + mesi + "-" + diai + " " + "00:00:00";
	
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechaInicial);
			Object fechai = new java.sql.Timestamp(date.getTime());
			
			int a�of = dchHasta.getCalendar().get(Calendar.YEAR);
			int mesf = dchHasta.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = dchHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaFinal = a�of + "-" + mesf + "-" + diaf + " " + "23:59:59";
	
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter.parse(fechaFinal);
			Object fechaf = new java.sql.Timestamp(date2.getTime());
			
			try {
				consulta.iniciar();
				rs = consulta.cargarCompras(fechai, fechaf);
				while(rs.next()){
					dtmC.addRow(new Object[]{rs.getInt("idcompra"), rs.getString("serie")+" - " + rs.getString("nroSerie"), rs.getString("nombre"), rs.getString("nota"), rs.getString("fechaEmision"), rs.getFloat("tot")});
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR al cargar compras: " + e.getMessage());
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

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void actionPerformedBtnGenerarReporte(ActionEvent e) {
		String[] opciones = { "SIMPLE", "DETALLADO"};
		int seleccion = JOptionPane.showOptionDialog(null, "REPORTE" , "Seleccione una opcion",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
		Connection con = null;
	    try
	    {
	      con = MySQLConexion.getConection();

	      int a�oi = this.dchDesde.getCalendar().get(1);
	      int mesi = this.dchDesde.getCalendar().get(2) + 1;
	      int diai = this.dchDesde.getCalendar().get(5);
	      String fechai = a�oi + "-" + mesi + "-" + diai + " 00:00:00";

	      int a�of = this.dchHasta.getCalendar().get(1);
	      int mesf = this.dchHasta.getCalendar().get(2) + 1;
	      int diaf = this.dchHasta.getCalendar().get(5);
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
	      parameters.put("prtFechaFi", timeStampDateF);

	      if (seleccion == 0) {
	    	  new AbstractJasperReports().createReport(con, "rCompraSimple.jasper", parameters);
		      AbstractJasperReports.showViewer();
	      }
	      if (seleccion == 1) {
	    	  new AbstractJasperReports().createReport(con, "rCompraDetallada.jasper", parameters);
		      AbstractJasperReports.showViewer();
	      }
	    }
	    catch (Exception ex)
	    {
	      JOptionPane.showMessageDialog(null, "No se encontraron datos registrados en estas fechas" + ex);
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
	
	protected void actionPerformedBtnComprobante(ActionEvent e) {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			dtmC.removeRow(i);
			i -= 1;
		}
		for (int i = 0; i < tbDetallesCompra.getRowCount(); i++) {
			dtmCD.removeRow(i);
			i -= 1;
		}
		
		String serie = txtSerie.getText();
		String nSerie = txtNserie.getText();
		
		try {
			consulta.iniciar();
			rs = consulta.buscarCompraComprobante(serie, nSerie);
			while(rs.next()){
				dtmC.addRow(new Object[]{rs.getInt("idcompra"), rs.getString("serie")+" - " + rs.getString("nroSerie"), rs.getString("nombre"), rs.getString("nota"), rs.getString("fechaEmision"), rs.getFloat("tot")});
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar compra comprobante: " + ex.getMessage());
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
		
	}
	
	protected void actionPerformedBtnLote(ActionEvent e) {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			dtmC.removeRow(i);
			i -= 1;
		}
		for (int i = 0; i < tbDetallesCompra.getRowCount(); i++) {
			dtmCD.removeRow(i);
			i -= 1;
		}
		
		String lote = txtLote.getText();
		
		try {
			consulta.iniciar();
			rs = consulta.buscarCompraLote(lote);
			while(rs.next()){
				dtmC.addRow(new Object[]{rs.getInt("idcompra"), rs.getString("serie")+" - " + rs.getString("nroSerie"), rs.getString("nombre"), rs.getString("nota"), rs.getString("fechaEmision"), rs.getFloat("tot")});
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar compra lote: " + ex.getMessage());
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
	}
}
