package gui_mantenimiento_productos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import mysql.MySQLConexion;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;

import clases.AbstractJasperReports;
import clases.ColorearFilas;
import gui_mantenimiento_productos.AgregarStock;
import gui_mantenimiento_productos.ModificarProducto;
import gui_mantenimiento_productos.NuevoProducto;
import gui_principal.EleccionVentanas;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class HistorialKardex extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JTable tbHistorial;
	private TextAutoCompleter ac;
	private JScrollPane scrollPane;
	private JTextField txtMantenimientoDeProductos;
	private JButton btnVolver;

	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialKardex frame = new HistorialKardex(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HistorialKardex(String temp2) {
		usuario = temp2;
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 1061, 546);
		contentPane.add(scrollPane);

		tbHistorial = new JTable();
		tbHistorial.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbHistorial.setBackground(Color.WHITE);
		tbHistorial.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbHistorial);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbHistorial.getTableHeader().setReorderingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(0, 0, 132, 75);
		contentPane.add(btnVolver);

		txtMantenimientoDeProductos = new JTextField();
		txtMantenimientoDeProductos.setForeground(new Color(255, 255, 255));
		txtMantenimientoDeProductos.setText("HISTORIAL DE KARDEX");
		txtMantenimientoDeProductos.setRequestFocusEnabled(false);
		txtMantenimientoDeProductos.setIgnoreRepaint(true);
		txtMantenimientoDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		txtMantenimientoDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 40));
		txtMantenimientoDeProductos.setFocusable(false);
		txtMantenimientoDeProductos.setFocusTraversalKeysEnabled(false);
		txtMantenimientoDeProductos.setEditable(false);
		txtMantenimientoDeProductos.setColumns(10);
		txtMantenimientoDeProductos.setBackground(Color.DARK_GRAY);
		txtMantenimientoDeProductos.setBounds(0, 0, 1080, 75);
		contentPane.add(txtMantenimientoDeProductos);
		
		JButton btnVerReporte = new JButton((Icon) null);
		btnVerReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardar(e);
			}
		});
		btnVerReporte.setText("VER REPORTE");
		btnVerReporte.setForeground(Color.WHITE);
		btnVerReporte.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnVerReporte.setBackground(new Color(30, 144, 255));
		btnVerReporte.setBounds(711, 86, 360, 38);
		contentPane.add(btnVerReporte);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnVerReporte, btnVolver}));

		cargarDatos();
		ajustarAnchoColumnas();
		colortabla();

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
	}

	public void colortabla() {
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(239, 198, 46));

		for (int i = 0; i < tbHistorial.getModel().getColumnCount(); i++) {
			tbHistorial.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	}

	public void cargarDatos() {
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbHistorial;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "NRO DE KARDEX", "FECHA", "NOTA"});
		
		consultas model = new consultas();
		rs = model.cargarHistorialKardex();
		
		try {
			while (rs.next())
				dtm.addRow(new Object[] { rs.getInt("idkardex"), rs.getString("fecha"), rs.getString("nota")});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbHistorial.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(15)); // Codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(35)); // fecha
		tcm.getColumn(2).setPreferredWidth(anchoColumna(50)); // nota


	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosingThis(arg0);
		}
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	protected void windowClosingThis(WindowEvent arg0) {
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
				fos.close();
			} catch (IOException e1) {
				// JOptionPane.showMessageDialog(null, e1);
			}
			System.exit(0);
		} else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
	}


	public void selecionarProducto(String cod) {
		int cantProductos = tbHistorial.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (cod.equals(tbHistorial.getValueAt(i, 0))) {
				tbHistorial.setRowSelectionInterval(i, i);
				int registro = Integer.parseInt(tbHistorial.getValueAt(i, 7).toString());
				// JOptionPane.showMessageDialog(null, registro);
				registro++;
				// JOptionPane.showMessageDialog(null, registro);
				tbHistorial.setValueAt(registro, i, 7);

				break;
			}
		}
	}

	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();

			DefaultTableModel tm = (DefaultTableModel) tbHistorial.getModel();
			int idKardex = Integer.parseInt( String.valueOf(tm.getValueAt(tbHistorial.getSelectedRow(), 0)));
			
			Map parameters = new HashMap();
			parameters.put("prtIdKardex", idKardex);

			new AbstractJasperReports().createReport(con, "rKardexDetallado.jasper", parameters);
			AbstractJasperReports.showViewer();
			
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Seleccione una fila");
		}
		
	}
}
