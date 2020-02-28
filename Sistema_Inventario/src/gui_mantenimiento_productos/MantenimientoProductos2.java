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
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import clases.ColorearFilas;
import gui_mantenimiento_productos.AgregarStock;
import gui_principal.EleccionVentanas;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MantenimientoProductos2 extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JLabel lblCdigo;
	private JTable tbProductos;
	private TextAutoCompleter ac;
	private JScrollPane scrollPane;
	private JTextField txtMantenimientoDeProductos;
	private JButton btnVolver;

	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();
	private JButton btnBuscarProducto0;
	private JTextField txtNota;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoProductos2 frame = new MantenimientoProductos2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoProductos2(String temp2) {
		usuario = temp2;
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Swis721 LtEx BT", Font.BOLD | Font.ITALIC, 20));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(SystemColor.controlHighlight);
		txtCodigo.setBounds(158, 135, 913, 34);
		contentPane.add(txtCodigo);

		lblCdigo = new JLabel("Buscar:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblCdigo.setBounds(10, 131, 138, 38);
		contentPane.add(lblCdigo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 1061, 501);
		contentPane.add(scrollPane);

		tbProductos = new JTable();
		tbProductos.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbProductos);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(0, 0, 132, 75);
		contentPane.add(btnVolver);

		txtMantenimientoDeProductos = new JTextField();
		txtMantenimientoDeProductos.setForeground(new Color(255, 255, 255));
		txtMantenimientoDeProductos.setText("VERIFICACI\u00D3N DE INVENTARIO");
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

		this.btnBuscarProducto0 = new JButton((Icon) null);
		btnBuscarProducto0.setForeground(new Color(255, 255, 255));
		btnBuscarProducto0.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnBuscarProducto0.setText("Buscar producto con registro 0");
		this.btnBuscarProducto0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedButton(arg0);
			}
		});
		this.btnBuscarProducto0.setBackground(new Color(30, 144, 255));
		this.btnBuscarProducto0.setBounds(158, 86, 320, 38);
		this.contentPane.add(this.btnBuscarProducto0);
		
		JButton btnGuardar = new JButton((Icon) null);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGuardar(e);
			}
		});
		btnGuardar.setText("GUARDAR");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setBounds(840, 86, 231, 38);
		contentPane.add(btnGuardar);
		
		txtNota = new JTextField();
		txtNota.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		txtNota.setText("Si desea, deje una nota aqu\u00ED.");
		txtNota.setBounds(482, 86, 348, 38);
		contentPane.add(txtNota);
		txtNota.setColumns(10);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, btnBuscarProducto0, txtNota, btnGuardar, btnVolver}));

		cargarDatos();
		//cargarBuscador();
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

		for (int i = 0; i < tbProductos.getModel().getColumnCount(); i++) {
			tbProductos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	}

	public void cargarDatos() {
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbProductos;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle", "Marca", "Color", "U. Med.",
				"Cantidad", "Registros" });
		consultas model = new consultas();
		rs = model.cargarProductosSinStock();
		try {
			while (rs.next())
				dtm.addRow(new Object[] { rs.getString("codproducto"), rs.getString("producto"),
						rs.getString("detalles"), rs.getString("marca"), rs.getString("color"),
						rs.getString("unimedida"), rs.getFloat("cantidad"), "0" });
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtCodigo);
		consultas model = new consultas();
		ResultSet rs = model.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto"));
				ac.addItem(rs.getString("producto") + "_" + rs.getString("detalles"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10)); // Codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(24)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(24)); // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Uni Med
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // Stock
		tcm.getColumn(5).setPreferredWidth(anchoColumna(7)); // PrecioCompra
		tcm.getColumn(6).setPreferredWidth(anchoColumna(7)); // PrecioVenta
		tcm.getColumn(7).setPreferredWidth(anchoColumna(8)); // Marca

		// DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
		// tcr0.setHorizontalAlignment(SwingConstants.CENTER);
		// tbProductos.getColumnModel().getColumn(3).setCellRenderer(tcr0);

		// DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		// tcr.setHorizontalAlignment(SwingConstants.CENTER);
		// tbProductos.getColumnModel().getColumn(4).setCellRenderer(tcr);
		//
		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(6).setCellRenderer(tcr2);

		DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(7).setCellRenderer(tcr3);
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
		if (arg0.getSource() == txtCodigo) {
			keyPressedTxtCodigo(arg0);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		int posi = -1;
		if ((c == (char) KeyEvent.VK_ENTER)) {
			try {
				String pcompleto = txtCodigo.getText();
				String[] parts = pcompleto.split("_");
				String prd = parts[0]; // 123
				String dtll = parts[1]; // 654321
				rs = model.buscarProductoDetalle(prd, dtll);
				rs.next();
				if (rs.equals(null)) {
					JOptionPane.showMessageDialog(null, "Producto no registrado");
					limpiar();
				} else {
					String codigoProducto = rs.getString("codproducto");

					selecionarProducto(codigoProducto);

					txtCodigo.setText("");

					String nombreProducto = rs.getString("producto");
					String detalleProducto = rs.getString("detalles");
					String uniMedidaProducto = rs.getString("unimedida");
					String cantidadProducto = rs.getString("cantidad");
					String preciocoProducto = rs.getString("precioCo");
					String preciovePoducto = rs.getString("precioVe");
					String marcaProducto = rs.getString("marca");
					String colorProducto = rs.getString("color");
				}
			} catch (Exception e) {
				try {
					String pcompleto = txtCodigo.getText();
					rs = model.buscarProducto(pcompleto);
					rs.next();
					if (rs.equals(null)) {
						JOptionPane.showMessageDialog(null, "Producto no registrado");
						limpiar();
					} else {
						String codigoProducto = rs.getString("codproducto");

						selecionarProducto(codigoProducto);

						txtCodigo.setText("");
						String nombreProducto = rs.getString("producto");
						String detalleProducto = rs.getString("detalles");
						String uniMedidaProducto = rs.getString("unimedida");
						String cantidadProducto = rs.getString("cantidad");
						String preciocoProducto = rs.getString("precioCo");
						String preciovePoducto = rs.getString("precioVe");
						String marcaProducto = rs.getString("marca");
						String colorProducto = rs.getString("color");
					
					}

				} catch (Exception e2) {
					limpiar();
				}

			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	protected void keyPressedTxtCodigo(KeyEvent arg0) {

	}

	public void limpiar() {
		txtCodigo.setText(null);
	}

	public void selecionarProducto(String cod) {
		int cantProductos = tbProductos.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (cod.equals(tbProductos.getValueAt(i, 0))) {
				tbProductos.setRowSelectionInterval(i, i);
				int registro = Integer.parseInt(tbProductos.getValueAt(i, 7).toString());
				// JOptionPane.showMessageDialog(null, registro);
				registro++;
				// JOptionPane.showMessageDialog(null, registro);
				tbProductos.setValueAt(registro, i, 7);

				break;
			}
		}
	}

	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}

	protected void actionPerformedButton(ActionEvent arg0) {
		int cantProductos = tbProductos.getRowCount();
		int selec = tbProductos.getSelectedRow();
		//JOptionPane.showMessageDialog(null, selec + " " + cantProductos);
		if(selec == cantProductos-1)
			selec = -1;
		for (int i = selec + 1; i < cantProductos; i++) {
			if (Integer.parseInt(tbProductos.getValueAt(i, 7).toString()) == 0) {
				tbProductos.setRowSelectionInterval(i, i);
				i = cantProductos;
			}
		}
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent e) {
		String nota = txtNota.getText();
		if(nota.equals("Si desea, deje una nota aquí."))
			nota = "";
		java.util.Date date = new Date();
		Object date2 = new java.sql.Timestamp(date.getTime());
		//Date date2 = new java.sql.Date(date.getTime());
		model.registrarKardex(date2, nota);
		
		rs = model.ObtenerUltimoKardex();
		int idkardex = -1;
		try {
			rs.next();
			idkardex = rs.getInt("idkardex");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo kardex: " + ex);
		}
		
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		try {
			int cantProductos = tbProductos.getRowCount();
			for (int i = 0; i < cantProductos; i++) {
			String codigoProducto = String.valueOf(tm.getValueAt(i, 0));
			/*String nombreProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 1));
			String detalleProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 2));
			String marcaProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 3));
			String colorProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 4));
			String uniMedidaProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 5));
			String cantidadProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 6));*/
			int registros = Integer.parseInt(String.valueOf(tm.getValueAt(i, 7)));
			
			model.registrarDetallesKardex(idkardex, codigoProducto, registros);
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}
}
