package gui_compras;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_principal.VentanaPrincipal;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class MantenimientoCompras extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearCompra;
	private JMenu mnAgregarPago;
	private JButton btnX;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	public JTable tbCompras;
	public VentanaPrincipal vp;
	private JScrollPane scrollPane_1;
	private JLabel lblHistorialDeCompras;
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
		setBounds(100, 100, 1134, 679);
		getContentPane().setLayout(null);
		
		btnX = new JButton("X");
		this.btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnX(arg0);
			}
		});
		btnX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setBounds(1030, 0, 63, 30);
		getContentPane().add(btnX);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 74, 1083, 250);
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
		scrollPane_1.setBounds(10, 390, 1083, 200);
		getContentPane().add(scrollPane_1);
		
		tbDetallesCompra = new JTable();
		tbDetallesCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDetallesCompra.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		tbDetallesCompra.setAutoCreateRowSorter(true);
		tbDetallesCompra.setFont(new Font("Arial", Font.ITALIC, 14));
		tbDetallesCompra.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tbDetallesCompra);
		
		lblHistorialDeCompras = new JLabel("Historial de Compras:");
		lblHistorialDeCompras.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHistorialDeCompras.setForeground(Color.DARK_GRAY);
		lblHistorialDeCompras.setFont(new Font("Candara", Font.BOLD, 30));
		lblHistorialDeCompras.setBounds(10, 35, 331, 34);
		getContentPane().add(lblHistorialDeCompras);
		
		lblDetallesDeCompras = new JLabel("Detalles de Compra:");
		lblDetallesDeCompras.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetallesDeCompras.setForeground(Color.DARK_GRAY);
		lblDetallesDeCompras.setFont(new Font("Candara", Font.BOLD, 30));
		lblDetallesDeCompras.setBounds(10, 355, 331, 34);
		getContentPane().add(lblDetallesDeCompras);
		
		label = new JLabel("Desde:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(102, 205, 170));
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBackground(new Color(50, 205, 50));
		label.setBounds(313, 12, 71, 30);
		getContentPane().add(label);
		
		dchDesde = new JDateChooser();
		dchDesde.setBounds(313, 39, 130, 30);
		getContentPane().add(dchDesde);
		
		dchHasta = new JDateChooser();
		dchHasta.setBounds(453, 39, 130, 30);
		getContentPane().add(dchHasta);
		
		label_1 = new JLabel("Hasta:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(new Color(102, 205, 170));
		label_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1.setBackground(new Color(50, 205, 50));
		label_1.setBounds(453, 12, 71, 30);
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
		btnVerCompras.setBounds(598, 12, 110, 57);
		getContentPane().add(btnVerCompras);
		
		btnGenerarReporte = new JButton("<html><center>Ver reporte<br>detallado</center></html>");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGenerarReporte(e);
			}
		});
		btnGenerarReporte.setForeground(Color.WHITE);
		btnGenerarReporte.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGenerarReporte.setBackground(new Color(102, 205, 170));
		btnGenerarReporte.setBounds(718, 12, 135, 57);
		getContentPane().add(btnGenerarReporte);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCompras.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
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
		
		mnAgregarPago = new JMenu("|Agregar pago| ");
		mnAgregarPago.setForeground(new Color(220, 20, 60));
		mnAgregarPago.setBackground(SystemColor.control);
		mnAgregarPago.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnAgregarPago);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		ajustarAnchoColumnas();		
	}
	
	public void cargar() {
		tb = this.tbCompras;
		tb.setRowHeight(30);
		tb.setModel(dtmC);
		dtmC.setColumnIdentifiers(new Object[]{"NRO", "SERIE", "DISTRIBUIDOR", "NOTA", "F EMISIÓN", "F VENCIMIENTO", "TOTAL", "SALDO"});
		
		JTable tbCD = this.tbDetallesCompra;
		tbCD.setRowHeight(30);
		tbCD.setModel(dtmCD);
		dtmCD.setColumnIdentifiers(new Object[]{"CANTIDAD", "PRODUCTO", "PRECIO UNI COMP", "SUB TOTAL"});
		
		
		
		java.util.Date date = new Date();
		date.getTime();
		dchDesde.setDate(date);
		dchHasta.setDate(date);
	}
	
		
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {// "NRO", "SERIE", "DISTRIBUIDOR", "NOTA", "F EMISIÓN", "F VENCIMIENTO", "TOTAL", "SALDO"
		TableColumnModel tcmC = tbCompras.getColumnModel();
		tcmC.getColumn(0).setPreferredWidth(anchoColumna(5));  // 
		tcmC.getColumn(1).setPreferredWidth(anchoColumna(10));  // 
		tcmC.getColumn(2).setPreferredWidth(anchoColumna(15));  // 
		tcmC.getColumn(3).setPreferredWidth(anchoColumna(20));  // 
		tcmC.getColumn(4).setPreferredWidth(anchoColumna(15));  // 
		tcmC.getColumn(5).setPreferredWidth(anchoColumna(15));
		tcmC.getColumn(6).setPreferredWidth(anchoColumna(10));
		tcmC.getColumn(7).setPreferredWidth(anchoColumna(10));
		
		DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCompras.getColumnModel().getColumn(4).setCellRenderer(tcr0);
		tbCompras.getColumnModel().getColumn(5).setCellRenderer(tcr0);
		tbCompras.getColumnModel().getColumn(6).setCellRenderer(tcr0);
		tbCompras.getColumnModel().getColumn(7).setCellRenderer(tcr0);
		
		//"CANTIDAD", "PRODUCTO", "PRECIO UNI", "SUB TOTAL"
		TableColumnModel tcmCD = tbDetallesCompra.getColumnModel();
		tcmCD.getColumn(0).setPreferredWidth(anchoColumna(10));  // 
		tcmCD.getColumn(1).setPreferredWidth(anchoColumna(50));  // 
		tcmCD.getColumn(2).setPreferredWidth(anchoColumna(20));  // 
		tcmCD.getColumn(3).setPreferredWidth(anchoColumna(20));  // 
		
		tbDetallesCompra.getColumnModel().getColumn(0).setCellRenderer(tcr0);
		tbDetallesCompra.getColumnModel().getColumn(2).setCellRenderer(tcr0);
		tbDetallesCompra.getColumnModel().getColumn(3).setCellRenderer(tcr0);
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		this.dispose();
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
				dtmCD.addRow(new Object[]{rs.getFloat("cantidad"), rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color"), rs.getFloat("preUni"), rs.getFloat("preSubT")});
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
				
			int añoi = dchDesde.getCalendar().get(Calendar.YEAR);
			int mesi = dchDesde.getCalendar().get(Calendar.MARCH) + 1;
			int diai = dchDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaInicial = añoi + "-" + mesi + "-" + diai + " " + "00:00:00";
	
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = (Date) formatter.parse(fechaInicial);
			Object fechai = new java.sql.Timestamp(date.getTime());
			
			int añof = dchHasta.getCalendar().get(Calendar.YEAR);
			int mesf = dchHasta.getCalendar().get(Calendar.MARCH) + 1;
			int diaf = dchHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaFinal = añof + "-" + mesf + "-" + diaf + " " + "23:59:59";
	
			DateFormat formatter2;
			formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date2 = (Date) formatter.parse(fechaFinal);
			Object fechaf = new java.sql.Timestamp(date2.getTime());
			
			try {
				consulta.iniciar();
				rs = consulta.cargarCompras(fechai, fechaf);
				while(rs.next()){
					dtmC.addRow(new Object[]{rs.getInt("idcompra"), rs.getString("serie")+" - " + rs.getString("nroSerie"), rs.getString("nombre"), rs.getString("nota"), rs.getString("fechaEmision"), rs.getString("fechaVencimiento"), rs.getFloat("tot"), rs.getFloat("saldo")});
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
		
	}
}
