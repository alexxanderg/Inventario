package gui_productos;

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
import java.util.Iterator;
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
import gui_productos.AgregarStock;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ValorMonetarioKardex extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JTable tbValorMonetario;
	private TextAutoCompleter ac;
	private JScrollPane scrollPane;
	private JTextField txtMantenimientoDeProductos;
	private JButton btnVolver;

	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = null;
	DefaultTableModel dtm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValorMonetarioKardex frame = new ValorMonetarioKardex((JTable)null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ValorMonetarioKardex(JTable tbProductos) {
		this.tb=tbProductos;
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 1061, 546);
		contentPane.add(scrollPane);

		tbValorMonetario = new JTable();
		tbValorMonetario.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbValorMonetario.setBackground(Color.WHITE);
		tbValorMonetario.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbValorMonetario);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbValorMonetario.getTableHeader().setReorderingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.setVisible(false);
		btnVolver.setEnabled(false);
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(13, 4, 14, 13);
		contentPane.add(btnVolver);

		txtMantenimientoDeProductos = new JTextField();
		txtMantenimientoDeProductos.setForeground(new Color(255, 255, 255));
		txtMantenimientoDeProductos.setText("Valor Monetario");
		txtMantenimientoDeProductos.setRequestFocusEnabled(false);
		txtMantenimientoDeProductos.setIgnoreRepaint(true);
		txtMantenimientoDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		txtMantenimientoDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 40));
		txtMantenimientoDeProductos.setFocusable(false);
		txtMantenimientoDeProductos.setFocusTraversalKeysEnabled(false);
		txtMantenimientoDeProductos.setEditable(false);
		txtMantenimientoDeProductos.setColumns(10);
		txtMantenimientoDeProductos.setBackground(Color.DARK_GRAY);
		txtMantenimientoDeProductos.setBounds(0, 0, 1080, 58);
		contentPane.add(txtMantenimientoDeProductos);
		
		JButton btnVerReporte = new JButton((Icon) null);
		btnVerReporte.setEnabled(false);
		btnVerReporte.setVisible(false);
		btnVerReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardar(e);
			}
		});
		btnVerReporte.setText("VER REPORTE");
		btnVerReporte.setForeground(Color.WHITE);
		btnVerReporte.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnVerReporte.setBackground(new Color(30, 144, 255));
		btnVerReporte.setBounds(37, 4, 14, 13);
		contentPane.add(btnVerReporte);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnVerReporte, btnVolver}));

		//cargarDatos();
		//ajustarAnchoColumnas();
		//colortabla();
		
		System.out.println(this.tb);
		TableModel model = this.tb.getModel();
		((DefaultTableModel) model).addColumn("Valor");
		tbValorMonetario.setModel(model);
		
		for (int i = 0; i<tbValorMonetario.getRowCount();i++) {
			String var1 = tbValorMonetario.getValueAt(i, 9).toString();
			String var2 = tbValorMonetario.getValueAt(i, 10).toString();
			double resp = Double.parseDouble(var1) - Double.parseDouble(var2);
			tbValorMonetario.setValueAt(resp, i, 11);
			System.out.println(tbValorMonetario.getValueAt(i, 11).toString());
			//for (int j = 0; j < tbValorMonetario.getColumnCount(); j++) {
			//	if (rootPaneCheckingEnabled) {
			//		System.out.println(tbValorMonetario.getValueAt(i, j));
			//	}
			//}
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
	}

	public void colortabla() {
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(239, 198, 46));

		for (int i = 0; i < tbValorMonetario.getModel().getColumnCount(); i++) {
			tbValorMonetario.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	}

	public void cargarDatos() {
		
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbValorMonetario.getColumnModel();
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
		int cantProductos = tbValorMonetario.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (cod.equals(tbValorMonetario.getValueAt(i, 0))) {
				tbValorMonetario.setRowSelectionInterval(i, i);
				int registro = Integer.parseInt(tbValorMonetario.getValueAt(i, 7).toString());
				// JOptionPane.showMessageDialog(null, registro);
				registro++;
				// JOptionPane.showMessageDialog(null, registro);
				tbValorMonetario.setValueAt(registro, i, 7);

				break;
			}
		}
	}

	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		/*EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();*/
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		
		
	}
}
