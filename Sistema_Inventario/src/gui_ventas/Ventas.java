package gui_ventas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import clases.AbstractJasperReports;
import clases.Cliente;
import clases.Productos;
import gui_clientes.NuevoCliente;
import gui_ventas.ListaDeProductos;
import gui_mantenimiento_productos.NuevoProducto;
import gui_principal.EleccionVentanas;
import gui_principal.Login;
import gui_reportes.Reportes;
import gui_ventas.ModificarPrecioVenta;
import mysql.consultas;
import mysql.MySQLConexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;

public class Ventas extends JFrame implements WindowListener, ActionListener, KeyListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtProductos;
	private JButton btnLista;
	private JLabel lblBuscarProducto;
	private JButton btnVender;
	private JScrollPane scrollPane;
	private JTable tbCompras;
	private TextAutoCompleter ac;
	public DefaultTableModel dtm = new DefaultTableModel();
	private JTextField txtVentaDeProductos;
	private JButton btnVolver;
	private JButton btnDevolucion;
	private JButton btnLimpiarTabla;
	private JButton btnNuevaVentana;
	private JButton btnNuevoProducto;
	private JLabel lblTotal;
	private JLabel lblPaga;
	private JLabel label_1;
	private JTextField txtPaga;
	private JLabel lblVuelto;
	private JTextField txtVuelto;
	private JLabel label_3;
	private JLabel lblNombreDeCliente;
	private JTextField txtCopias;
	private JLabel lblNImpresiones;
	private JLabel lblTotalS;

	EleccionVentanas el = null;
	String usuario;
	ResultSet rs;
	consultas model = new consultas();
	String cliente = null;
	int nventana = 0;
	private JLabel lblLogo;
	private JButton btnReportes;
	private JComboBox<Cliente> cbClientes;
	private JButton btnAnadirCliente;
	private JTextField txtInfoAdicional;
	private JLabel lblInfomacinAdicional;
	private JLabel lblMetodoDePago;
	private JComboBox<Cliente> cbMetodoPago;
	private JButton btnalterarInformacion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventas frame = new Ventas(0, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventas(int temp, EleccionVentanas temp2, String temp3) {
		nventana = temp;
		el = temp2;
		usuario = temp3;

		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1386, 747);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtProductos = new JTextField();
		txtProductos.addKeyListener(this);

		btnNuevaVentana = new JButton("+");
		btnNuevaVentana.addActionListener(this);

		lblTotal = new JLabel("");
		lblTotal.setForeground(new Color(30, 144, 255));
		lblTotal.setBackground(new Color(50, 205, 50));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblTotal.setBounds(1123, 556, 202, 50);
		contentPane.add(lblTotal);
		btnNuevaVentana.setForeground(new Color(0, 255, 0));
		btnNuevaVentana.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 39));
		btnNuevaVentana.setBackground(Color.BLACK);
		btnNuevaVentana.setBounds(1269, 0, 103, 58);
		contentPane.add(btnNuevaVentana);
		txtProductos.setHorizontalAlignment(SwingConstants.LEFT);
		txtProductos.setBackground(SystemColor.controlHighlight);
		txtProductos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		txtProductos.setBounds(334, 277, 606, 34);
		contentPane.add(txtProductos);
		txtProductos.setColumns(10);

		btnLista = new JButton("<html><center>Lista<br>completa</center></html>");
		btnLista.addActionListener(this);
		btnLista.setBackground(new Color(30, 144, 255));
		btnLista.setForeground(new Color(255, 255, 255));
		btnLista.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnLista.setBounds(642, 166, 139, 86);
		contentPane.add(btnLista);

		lblBuscarProducto = new JLabel("BUSCAR PRODUCTO:");
		lblBuscarProducto.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblBuscarProducto.setBounds(36, 277, 271, 34);
		contentPane.add(lblBuscarProducto);

		btnVender = new JButton("CULMINAR VENTA");
		btnVender.addActionListener(this);
		btnVender.setForeground(new Color(30, 144, 255));
		btnVender.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		btnVender.setBackground(Color.BLACK);
		btnVender.setBounds(979, 638, 346, 50);
		contentPane.add(btnVender);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 322, 904, 366);
		contentPane.add(scrollPane);

		tbCompras = new JTable();
		tbCompras.addMouseListener(this);
		tbCompras.addKeyListener(this);
		tbCompras.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 17));
		scrollPane.setViewportView(tbCompras);
		tbCompras.getTableHeader().setReorderingAllowed(false);
		tbCompras.getTableHeader().setResizingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(0, 0, 143, 58);
		contentPane.add(btnVolver);

		btnDevolucion = new JButton("<html><center>Eliminar / modificar<br>venta</center></html>");
		btnDevolucion.addActionListener(this);
		btnDevolucion.setForeground(Color.WHITE);
		btnDevolucion.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnDevolucion.setBackground(new Color(30, 144, 255));
		btnDevolucion.setBounds(334, 166, 298, 86);
		contentPane.add(btnDevolucion);

		txtVentaDeProductos = new JTextField();
		txtVentaDeProductos.setText("VENTA DE PRODUCTOS");
		txtVentaDeProductos.setRequestFocusEnabled(false);
		txtVentaDeProductos.setIgnoreRepaint(true);
		txtVentaDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		txtVentaDeProductos.setForeground(new Color(255, 255, 255));
		txtVentaDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 40));
		txtVentaDeProductos.setFocusable(false);
		txtVentaDeProductos.setFocusTraversalKeysEnabled(false);
		txtVentaDeProductos.setEditable(false);
		txtVentaDeProductos.setColumns(10);
		txtVentaDeProductos.setBackground(Color.DARK_GRAY);
		txtVentaDeProductos.setBounds(122, 0, 1269, 58);
		contentPane.add(txtVentaDeProductos);

		btnLimpiarTabla = new JButton("<html>Limpiar<br>ventana</html>");
		btnLimpiarTabla.addActionListener(this);
		btnLimpiarTabla.setForeground(Color.WHITE);
		btnLimpiarTabla.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnLimpiarTabla.setBackground(new Color(220, 20, 60));
		btnLimpiarTabla.setBounds(791, 166, 149, 86);
		contentPane.add(btnLimpiarTabla);

		btnNuevoProducto = new JButton("<html><center>Nuevo<br>producto</center></html>");
		btnNuevoProducto.addActionListener(this);
		btnNuevoProducto.setForeground(Color.WHITE);
		btnNuevoProducto.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnNuevoProducto.setBackground(new Color(30, 144, 255));
		btnNuevoProducto.setBounds(642, 69, 139, 86);
		contentPane.add(btnNuevoProducto);

		lblPaga = new JLabel("Paga con:");
		lblPaga.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPaga.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaga.setForeground(Color.BLACK);
		lblPaga.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblPaga.setBounds(979, 347, 346, 38);
		contentPane.add(lblPaga);

		label_1 = new JLabel("S/.");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		label_1.setBounds(979, 381, 76, 38);
		contentPane.add(label_1);

		txtPaga = new JTextField();
		txtPaga.addKeyListener(this);
		txtPaga.setHorizontalAlignment(SwingConstants.CENTER);
		txtPaga.setForeground(SystemColor.windowBorder);
		txtPaga.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPaga.setColumns(10);
		txtPaga.setBackground(SystemColor.controlHighlight);
		txtPaga.setBounds(1022, 389, 181, 34);
		contentPane.add(txtPaga);

		lblVuelto = new JLabel("Su vuelto es:");
		lblVuelto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblVuelto.setHorizontalAlignment(SwingConstants.LEFT);
		lblVuelto.setForeground(Color.BLACK);
		lblVuelto.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblVuelto.setBounds(979, 440, 257, 38);
		contentPane.add(lblVuelto);

		txtVuelto = new JTextField();
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(SystemColor.windowBorder);
		txtVuelto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBackground(SystemColor.controlHighlight);
		txtVuelto.setBounds(1022, 479, 181, 34);
		contentPane.add(txtVuelto);

		label_3 = new JLabel("S/.");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		label_3.setBounds(979, 475, 64, 38);
		contentPane.add(label_3);

		lblNombreDeCliente = new JLabel("Nombre de Cliente:");
		lblNombreDeCliente.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblNombreDeCliente.setBounds(979, 69, 311, 34);
		contentPane.add(lblNombreDeCliente);

		txtCopias = new JTextField();
		txtCopias.addKeyListener(this);
		txtCopias.setText("1");
		txtCopias.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCopias.setForeground(Color.BLACK);
		txtCopias.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtCopias.setColumns(10);
		txtCopias.setBackground(Color.ORANGE);
		txtCopias.setBounds(1288, 617, 35, 20);
		contentPane.add(txtCopias);

		lblNImpresiones = new JLabel("N\u00B0 impresiones:");
		lblNImpresiones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNImpresiones.setForeground(Color.BLACK);
		lblNImpresiones.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNImpresiones.setBounds(1127, 617, 149, 20);
		contentPane.add(lblNImpresiones);

		lblTotalS = new JLabel("Total: S/");
		lblTotalS.setForeground(new Color(30, 144, 255));
		lblTotalS.setBackground(new Color(50, 205, 50));
		lblTotalS.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalS.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblTotalS.setBounds(982, 556, 143, 50);
		contentPane.add(lblTotalS);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(36, 80, 224, 182);
		Image img = new ImageIcon(this.getClass().getResource("/imgalmacen.png")).getImage();
		lblLogo.setIcon(new ImageIcon(img));
		contentPane.add(lblLogo);

		btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnReportes(arg0);
			}
		});
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnReportes.setBackground(new Color(30, 144, 255));
		btnReportes.setBounds(791, 69, 149, 86);
		contentPane.add(btnReportes);

		cbClientes = new JComboBox();
		// cbClientes.setSelectedIndex(0);
		cbClientes.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		cbClientes.setBounds(979, 103, 346, 38);
		contentPane.add(cbClientes);

		btnAnadirCliente = new JButton("+");
		btnAnadirCliente.addActionListener(this);
		btnAnadirCliente.setBackground(new Color(50, 205, 50));
		btnAnadirCliente.setForeground(new Color(255, 255, 255));
		btnAnadirCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAnadirCliente.setBounds(1269, 141, 56, 38);
		contentPane.add(btnAnadirCliente);

		txtInfoAdicional = new JTextField();
		txtInfoAdicional.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtInfoAdicional(arg0);
			}
		});
		txtInfoAdicional.setHorizontalAlignment(SwingConstants.LEFT);
		txtInfoAdicional.setForeground(SystemColor.windowBorder);
		txtInfoAdicional.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtInfoAdicional.setColumns(10);
		txtInfoAdicional.setBackground(SystemColor.controlHighlight);
		txtInfoAdicional.setBounds(979, 213, 346, 34);
		contentPane.add(txtInfoAdicional);

		lblInfomacinAdicional = new JLabel("Informaci\u00F3n adicional:");
		lblInfomacinAdicional.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblInfomacinAdicional.setBounds(979, 179, 311, 34);
		contentPane.add(lblInfomacinAdicional);

		lblMetodoDePago = new JLabel("Metodo de pago:");
		lblMetodoDePago.setFont(new Font("EngraversGothic BT", Font.BOLD, 25));
		lblMetodoDePago.setBounds(979, 263, 311, 34);
		contentPane.add(lblMetodoDePago);

		cbMetodoPago = new JComboBox();
		cbMetodoPago.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedCbMetodoPago(arg0);
			}
		});
		cbMetodoPago.setModel(new DefaultComboBoxModel(
				new String[] { "Efectivo", "Dep\u00F3sito BCP", "Dep\u00F3sito BBVA", "Dep\u00F3sito INTERBANK",
						"Transferencia BCP", "Transferencia BBVA", "Transferencia INTERBANK", "Pago con tarjeta BCP",
						"Pago con tarjeta BBVA", "Pago con tarjeta INTERBANK", "CR\u00C9DITO" }));
		cbMetodoPago.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		cbMetodoPago.setBounds(979, 298, 346, 38);
		contentPane.add(cbMetodoPago);

		this.btnalterarInformacion = new JButton("<html><center>Modificar informaci\u00F3n<br>de venta realizada</center></html>");
		this.btnalterarInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnalterarInformacion(e);
			}
		});
		this.btnalterarInformacion.setForeground(Color.WHITE);
		this.btnalterarInformacion.setFont(new Font("Century Gothic", Font.BOLD, 20));
		this.btnalterarInformacion.setBackground(new Color(30, 144, 255));
		this.btnalterarInformacion.setBounds(334, 69, 298, 86);
		this.contentPane.add(this.btnalterarInformacion);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtProductos, txtPaga, txtCopias, btnVender,
				btnLista, btnNuevoProducto, btnDevolucion, btnReportes, btnLimpiarTabla, btnVolver, btnNuevaVentana }));
		cargar();
		// cargarBuscadorCliente();
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowClosedThis(arg0);
		}
	}

	public void windowClosing(WindowEvent arg0) {
		if (nventana != 1)
			this.dispose();
		else {
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
					// JOptionPane.showMessageDialog(null, "Copia de segudidad
					// creada en: \n D:/ INFORMACION DEL SISTEMA /
					// BACKUP_SISTEMA / ");
					// JOptionPane.showMessageDialog(null, "Copia de segudidad
					// realizada correctamente");
					fos.close();
				} catch (IOException e1) {
					// JOptionPane.showMessageDialog(null, e1);
				}
				System.exit(0);
			} else
				this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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

	protected void windowClosedThis(WindowEvent arg0) {
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAnadirCliente) {
			actionPerformedBtnAnadirCliente(arg0);
		}
		if (arg0.getSource() == btnNuevoProducto) {
			actionPerformedBtnNuevoProducto(arg0);
		}
		if (arg0.getSource() == btnNuevaVentana) {
			actionPerformedBtnNuevaVentana(arg0);
		}
		if (arg0.getSource() == btnLimpiarTabla) {
			actionPerformedBtnLimpiarTabla(arg0);
		}
		if (arg0.getSource() == btnDevolucion) {
			actionPerformedBtnDevolucion(arg0);
		}
		if (arg0.getSource() == btnVolver) {
			btnVolverActionPerformed(arg0);
		}
		if (arg0.getSource() == btnVender) {
			actionPerformedBtnVender(arg0);
		}
		if (arg0.getSource() == btnLista) {
			actionPerformedBtnLista(arg0);
		}
	}

	public void keyPressed(KeyEvent e) {
		// De alguna manera el txtproductos y txtpaga comparten este
		// keypressed(Arreglar)
		if (e.getSource() == tbCompras) {
			keyPressedTbCompras(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtPaga) {
			keyReleasedTxtPaga(e);
		}
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		/*
		 * if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c !=
		 * (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) &&
		 * (c != '.')) { e.consume(); }
		 */
		if (txtPaga.getText().length() == 10) {
			e.consume();
		}
		if (c == '.' && txtPaga.getText().contains(".")) {
			e.consume();
		}

		if (e.getSource() == txtCopias) {
			keyTypedTxtCopias(e);
		}
		if (e.getSource() == txtProductos) {
			keyTypedTxtProductos(e);
		}
	}

	public void sumarSubTotales() {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			try {
				float cant = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
				float preU = Float.parseFloat(tbCompras.getValueAt(i, 4).toString());
				double subT = cant * preU;
				subT = redondearDecimales(subT, 2);
				tbCompras.setValueAt(subT, i, 5);
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
		}
	}

	public void sumarTotal() {
		double Total = 0;
		if (tbCompras.getRowCount() < 1)
			lblTotal.setText("");
		else {
			for (int i = 0; i < tbCompras.getRowCount(); i++) {
				try {
					Total = Total + Float.parseFloat(tbCompras.getValueAt(i, 5).toString());
					Total = redondearDecimales(Total, 2);
					lblTotal.setText("" + Total + "0");
					txtPaga.setText(null);
					txtVuelto.setText(null);
				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
			}
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

	public void cargar() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);

		Cliente cliente = new Cliente();
		cliente.cargarEmpresas(cbClientes);

		if (nventana == 1)
			txtVentaDeProductos.setText("VENTA DE PRODUCTOS");
		else
			txtVentaDeProductos.setText("VENTA DE PRODUCTOS (Ventana " + nventana + ")");
		ac = new TextAutoCompleter(txtProductos);
		consultas model = new consultas();
		tbCompras.setRowHeight(30);
		ResultSet rs = model.cargarProductos();
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto")); //codigo de barras
				// ac.addItem(rs.getString("producto") + "("+
				// rs.getString("cantidad") + ")");
				ac.addItem(rs.getString("producto") + "_" + rs.getString("detalles"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}

		ac.setMode(0);
		tbCompras.setModel(dtm);
		dtm.setColumnIdentifiers(
				new Object[] { "Cant.", "Producto", "Detalle", "Stock", "Precio Uni", "SubTotal", "Cod.", "PC" });
		ajustarAnchoColumnas();
	}

	public void cargarBuscadorCliente() {
		consultas model = new consultas();
		ResultSet rs = model.cargarClientes();
		ac.setMode(0);
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto"));
				ac.addItem(rs.getString("id") + "_" + rs.getString("nombre"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCompras.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4)); // Cantidad
		tcm.getColumn(1).setPreferredWidth(anchoColumna(37)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25)); // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Stock
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // Precio
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10)); // SubTotal
		tcm.getColumn(6).setPreferredWidth(anchoColumna(1));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(1));// Cod

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tbCompras.getColumnModel().getColumn(4).setCellRenderer(tcr);

		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbCompras.getColumnModel().getColumn(5).setCellRenderer(tcr2);

	}

	protected void keyTypedTxtProductos(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER)
			cargarTabla();
	}

	public void cargarTabla() {
		try { // SI LO QUE SE INGRESA ES UN NOMBRE DE PRODUCTO
			String pcompleto = txtProductos.getText();
			String[] parts = pcompleto.split("_");
			String prd = parts[0]; // 123
			String dtll = parts[1]; // 654321
			rs = model.buscarProductoDetalle(prd, dtll);

			int flag = 0;
			float cantidad = 0;
			for (int i = 0; i < tbCompras.getRowCount(); i++) { // AQUÍ ENTRA SI
																// YA EXISTE EL
																// PRODUCTO EN
																// LA TABLA
				try {
					rs.beforeFirst();
					while (rs.next()) {
						if (rs.getString("codproducto").equals(tbCompras.getValueAt(i, 6))) {
							cantidad = (Float.parseFloat(tbCompras.getValueAt(i, 0).toString()) + 1);
							tbCompras.setValueAt(cantidad, i, 0);
							flag = 1;
							txtProductos.setText(null);
							txtProductos.requestFocus();
							tbCompras.setRowSelectionInterval(i, i);
						}
					}
				} catch (SQLException e) {
					// JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
			}

			if (flag == 0) { // AQUÍ ENTRA SI EL PRODUCTO AGREGADO ES NUEVO
				try {
					rs.beforeFirst();
					while (rs.next()) {
						dtm.addRow(new Object[] { "1", rs.getString("producto"), rs.getString("detalles"),
								rs.getString("cantidad"), rs.getFloat("precioVe"), "", rs.getString("codproducto"),
								rs.getFloat("precioCo") });
						tbCompras.setRowSelectionInterval(tbCompras.getRowCount() - 1, tbCompras.getRowCount() - 1);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
				txtProductos.setText(null);
				txtProductos.requestFocus();
			}
			sumarSubTotales();
			sumarTotal();

		} catch (Exception e) { // AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO
			try {
				String pcompleto = txtProductos.getText();
				rs = model.buscarProducto(pcompleto);
				int flag = 0;
				float cantidad = 0;
				for (int i = 0; i < tbCompras.getRowCount(); i++) {
					try {// AQUÍ ENTRA SI YA EXISTE EL PRODUCTO EN LA TABLA
						rs.beforeFirst();
						while (rs.next()) {
							if (rs.getString("codproducto").equals(tbCompras.getValueAt(i, 6))) {
								cantidad = (Float.parseFloat(tbCompras.getValueAt(i, 0).toString()) + 1);
								tbCompras.setValueAt(cantidad, i, 0);
								flag = 1;
								txtProductos.setText(null);
								txtProductos.requestFocus();
								tbCompras.setRowSelectionInterval(i, i);
							}
						}
					} catch (SQLException ex) {
					}
				}
				if (flag == 0) { // AQUÍ ENTRA SI EL PRODUCTO AGREGADO ES NUEVO
					try {
						rs.beforeFirst();
						while (rs.next()) {
							dtm.addRow(new Object[] { "1", rs.getString("producto"), rs.getString("detalles"),
									rs.getString("cantidad"), rs.getFloat("precioVe"), "", rs.getString("codproducto"),
									rs.getFloat("precioCo") });
							tbCompras.setRowSelectionInterval(tbCompras.getRowCount() - 1, tbCompras.getRowCount() - 1);
						}
					} catch (Exception ex) {
					}
					txtProductos.setText(null);
					txtProductos.requestFocus();
				}
				sumarSubTotales();
				sumarTotal();

			} catch (Exception e2) {
				txtProductos.setText(null);
			}
		}

	}

	protected void actionPerformedBtnLista(ActionEvent arg0) {
		ListaDeProductos lp = new ListaDeProductos(this);
		lp.setLocationRelativeTo(null);
		lp.setVisible(true);
		this.setEnabled(false);
	}

	public int AnadirProductosdeListaCompleta(String codigo, String cantidad) {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			if (codigo.equals(tbCompras.getValueAt(i, 6))) {
				float temp = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
				float temp2 = Float.parseFloat(cantidad);
				float suma = temp + temp2;
				tbCompras.setValueAt(suma, i, 0);
				txtProductos.requestFocus();
				tbCompras.setRowSelectionInterval(i, i);
				return 0;
			}
		}
		return 1; // 1 = NO ENCONTRÓ COINCIDENCIA
	}

	public void eliminarFila() {
		try {
			dtm.removeRow(tbCompras.getSelectedRow());
			sumarSubTotales();
			sumarTotal();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione producto a eliminar");
		}
	}

	protected void actionPerformedBtnLimpiarTabla(ActionEvent arg0) {
		limpiar();
	}

	protected void actionPerformedBtnVender(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realizar venta?", "Confirmar", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			// int rpt = verificarTabla();
			int rpt = 1;
			String cli = null;
			String nrodoc = null;
			if (rpt == 1) {
				double pretotC = 0;
				double pretotV = 0;
				ArrayList<Productos> listprod = new ArrayList<>();
				double totalOriginalV = 0;
				if (tbCompras.getRowCount() < 1) {
					JOptionPane.showMessageDialog(null, "Agregue algún producto a la lista");
				} else {
					int flag = verificarStock();
					if (flag == 0) {
						Productos p = null;
						for (int i = 0; i < tbCompras.getRowCount(); i++) {
							String cod = tbCompras.getValueAt(i, 6).toString();
							rs = model.buscarProducto(cod);
							try {
								while (rs.next()) {
									String prod = rs.getString("producto");
									String det = rs.getString("detalles");
									String cat = rs.getString("categoria");
									String lab = rs.getString("laboratorio");
									Date fecVen = rs.getDate("fechaVenc");
									float nrolote = rs.getFloat("nrolote");
									String umed = rs.getString("unimedida");
									float cant = rs.getFloat("cantidad");
									float cantventa = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
									float pCo = rs.getFloat("precioCo");
									float pVe = rs.getFloat("precioVe");
									String marca = rs.getString("marca");
									String color = rs.getString("color");
									p = new Productos(cod, prod, det, cat, lab, fecVen, nrolote, umed, cant, cantventa,
											pCo, pVe, marca, color);
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "ERROR: " + e);
							}
							listprod.add(p);
						}

						// CALCULO DE PRECIOS
						for (int i = 0; i < listprod.size(); i++) {
							pretotV = pretotV + (listprod.get(i).getCantventa() * listprod.get(i).getPrecioVe());
							pretotV = redondearDecimales(pretotV, 2);
							pretotC = pretotC + (listprod.get(i).getCantventa() * listprod.get(i).getPrecioCo());
							pretotC = redondearDecimales(pretotC, 2);
							totalOriginalV = totalOriginalV + redondearDecimales(listprod.get(i).getPrecioVe(), 2);
						}

						int codVenta = 0;
						try {
							cli = cbClientes.getSelectedItem().toString();
							int idcliente = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getId();
							nrodoc = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getNrodoc();

							float preTotalVentaFinal = Float.parseFloat(lblTotal.getText());
							double gananciaOriginal = pretotV - pretotC;
							gananciaOriginal = redondearDecimales(gananciaOriginal, 2);
							double gananciaFinal = preTotalVentaFinal - pretotC;
							gananciaFinal = redondearDecimales(gananciaFinal, 2);
							String nota = txtInfoAdicional.getText();
							int metpago = cbMetodoPago.getSelectedIndex();

							model.Vender(cli, usuario, pretotC, preTotalVentaFinal, gananciaFinal, idcliente, nota,
									metpago);
							rs = model.ObtenerUltimoCodigo();
							try {
								while (rs.next())
									codVenta = rs.getInt("codventa");
							} catch (Exception e) {
								setAlwaysOnTop(false);
								JOptionPane.showMessageDialog(null, "ERROR: " + e);
								setAlwaysOnTop(true);
							}

							for (int i = 0; i < tbCompras.getRowCount(); i++) {
								String codProducto = tbCompras.getValueAt(i, 6).toString();
								float cantventa = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());

								double preVeUnidadOriginal = 0;

								for (int y = 0; y < listprod.size(); y++) {
									if (listprod.get(y).getCodigo().equals(codProducto)) {
										preVeUnidadOriginal = listprod.get(y).getPrecioVe();
										y = listprod.size();
									}
								}
								preVeUnidadOriginal = redondearDecimales(preVeUnidadOriginal, 2);

								double preTotalUnidadOriginal = cantventa * preVeUnidadOriginal;
								preTotalUnidadOriginal = redondearDecimales(preTotalUnidadOriginal, 2);

								double preUnidadFinal = Float.parseFloat(tbCompras.getValueAt(i, 4).toString());
								preUnidadFinal = redondearDecimales(preUnidadFinal, 2);
								double preTotalUnidadFinal = Float.parseFloat(tbCompras.getValueAt(i, 5).toString());
								preTotalUnidadFinal = redondearDecimales(preTotalUnidadFinal, 2);

								model.RegistarDetalleVenta(codVenta, codProducto, cantventa, preVeUnidadOriginal,
										preTotalUnidadOriginal, preUnidadFinal, preTotalUnidadFinal);
								model.RealizarDescuentoStock(codProducto, cantventa);
							}

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "ERROR: " + e);
						}

						// IMPRIMIR TICKET
						int copias = Integer.parseInt(txtCopias.getText());
						String nota = txtInfoAdicional.getText();
						Connection con = null;

						for (int i = 0; i < copias; i++) {
							try {
								Map<String, Object> parameters = new HashMap();
								parameters.put("prtNVenta", codVenta);
								parameters.put("prtCliente", cli);
								parameters.put("prtNroDoc", nrodoc);
								parameters.put("prtVendedor", usuario);
								parameters.put("prtNota", nota);
								/*
								 * new AbstractJasperReports().createReport(
								 * con.getConn(), "rPrueba.jasper", null);
								 * AbstractJasperReports.showViewer();
								 */
								try {
									con = MySQLConexion.getConection();
									/*
									 * JasperReport reporte = (JasperReport)
									 * JRLoader.loadObjectFromFile(
									 * "bin/rComprobante.jasper"); JasperPrint
									 * jasperPrint =
									 * JasperFillManager.fillReport(reporte,
									 * parameters, con);
									 * AbstractJasperReports.showViewer();
									 * JasperPrintManager.printReport(
									 * jasperPrint, false);
									 * 
									 */
									JasperPrint impressao = JasperFillManager.fillReport(
											getClass().getClassLoader().getResourceAsStream("rComprobante.jasper"),
											parameters, con);

									// AbstractJasperReports.showViewer();
									JasperPrintManager.printReport(impressao, false);
									/*
									 * this.setAlwaysOnTop(false);
									 * //JOptionPane.showMessageDialog(null,
									 * "VENTA CORRECTA");
									 * this.setAlwaysOnTop(true);
									 */

								} catch (JRException ex) {
									// JOptionPane.showMessageDialog(null,
									// "ERROR " + ex.getMessage());
									// System.err.println("Error iReport: " +
									// ex.getMessage());
								}

							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "ERROR " + e);
							}
						}
						JOptionPane.showMessageDialog(null, "VENTA CORRECTA :)");
						limpiar();

						/*
						 * lblPaga.setText("Paga con: "); lblVuelto.setText(
						 * "Su vuelto es: ");
						 */
					}
				}
			}
		}
	}

	/*
	 * try { con = MySQLConexion.getConection(); String nventa =
	 * txtNVenta.getText(); Map<String, Object> parameters = new HashMap();
	 * parameters.put("prtCodVen", Integer.parseInt(nventa)); new
	 * AbstractJasperReports().createReport(con, "rVentaDetalle.jasper",
	 * parameters); AbstractJasperReports.showViewer(); con.close();
	 * txtNVenta.setText(null); } catch (Exception e) {
	 * JOptionPane.showMessageDialog(null, "No se encontó la venta " + e); } } }
	 */

	public int verificarStock() {
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			String cod = tbCompras.getValueAt(i, 6).toString();
			float cantV = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
			float stock = 0;
			rs = model.buscarProducto(cod);
			try {
				rs.next();
				stock = rs.getFloat("cantidad");
				String producto = rs.getString("producto");
				String detalle = rs.getString("detalles");
				if (cantV > stock) {
					tbCompras.setRowSelectionInterval(i, i);
					JOptionPane.showMessageDialog(null,
							"Stock insuficiente de: " + producto + " " + detalle + ". \nMaximo: " + stock);
					return 1;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
		}
		return 0;
	}

	public int verificarTabla() {
		double cantidad = 0;
		double preUnidad = 0;
		double preSubTotal = 0;
		double temp = 0;
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			cantidad = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
			preUnidad = Float.parseFloat(tbCompras.getValueAt(i, 4).toString());
			preSubTotal = Float.parseFloat(tbCompras.getValueAt(i, 5).toString());
			temp = cantidad * preUnidad;
			/*
			 * cantidad = redondearDecimales(cantidad, 2); preUnidad =
			 * redondearDecimales(preUnidad, 2); preSubTotal =
			 * redondearDecimales(preSubTotal, 2);
			 */
			temp = redondearDecimales(temp, 2);
			// JOptionPane.showMessageDialog(null, cantidad + " - " + preUnidad
			// + " - " + preSubTotal + " - " + temp);

			if (temp != preSubTotal) {
				JOptionPane.showMessageDialog(null, "Error de precios en el producto: " + tbCompras.getValueAt(i, 1));
				return 0;
			}
		}
		return 1;
	}

	public void limpiar() {
		try {
			for (int i = 0; i < tbCompras.getRowCount(); i++) {
				dtm.removeRow(i);
				i -= 1;
			}
			sumarSubTotales();
			sumarTotal();
			txtProductos.requestFocus();
			txtPaga.setText(null);
			txtVuelto.setText(null);
			txtInfoAdicional.setText(null);
			cbClientes.setSelectedIndex(0);
			cbMetodoPago.setSelectedIndex(0);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione producto a eliminar");
		}
	}

	public void seleccionarRow() {
		int cant = tbCompras.getRowCount() - 1;
		tbCompras.setRowSelectionInterval(cant, cant);
	}

	protected void btnVolverActionPerformed(ActionEvent arg0) {
		if (el == null) {
			int opc = JOptionPane.showConfirmDialog(null, "¿Ud. está cerrando sesión. ¿Continuar?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == 0) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		} else {
			el.setVisible(true);
			dispose();

		}
	}

	protected void actionPerformedBtnalterarInformacion(ActionEvent e) {
		try {
			int numVenta = Integer.parseInt(JOptionPane.showInputDialog(
					"Ingrese el número de la venta que desea modificar su información"));
			alterarInformacion(numVenta);	
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	public void alterarInformacion(int numVenta) {
		rs = model.VerificarVenta(numVenta);
		String codventa = null;
		String nota = null;
		String vendedor = null;
		String fecha = null;
		try {
			rs.next();
			codventa = rs.getString("codventa");
			nota = rs.getString("nota");
			vendedor = rs.getString("usuario");
			fecha = rs.getDate("fecha").toString();
			/*SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date dateStr = formatter.parse(fecha);
	        fecha = formatter.format(dateStr);*/
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		if (codventa != null) {
			String info = JOptionPane.showInputDialog(
					"N° de Venta: " + codventa + 
					"\nVendedor: " + vendedor + 
					"\nFecha: " + fecha + 
					"\nInformación registrada actualmente:\n"+nota);
			if ((info != null) && (info .length() > 0)) {
				rs = model.modificarInformacion(info, numVenta);
			}
			else{
				JOptionPane.showMessageDialog(null, "Cancelo la petición");
			}
		} else
			JOptionPane.showMessageDialog(null, "NO EXISTE LA VENTA");
	}

	protected void actionPerformedBtnDevolucion(ActionEvent arg0) {
		String[] opciones = { "ELIMINAR", "MODIFICAR", "CANCELAR" };
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccione una opcion",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (seleccion == 0) {// ELIMINAR
			int numVenta = Integer.parseInt(JOptionPane.showInputDialog(
					"Alerta!\nEsta operación eliminará la venta y devolverá los productos a stock\nSi está seguro de seguir ingrese el número de venta."));
			limpiar();
			eliminarVenta(numVenta, seleccion);
		}
		if (seleccion == 1) {// MODIFICAR
			int numVenta = Integer.parseInt(JOptionPane.showInputDialog(
					"ALERTA!\nEsta operación devolverá los productos a stock y luego podrá modificar la venta.\nSi está seguro de seguir ingrese el número de venta."));
			limpiar();
			eliminarVenta(numVenta, seleccion);
		}
	}

	

	public void eliminarVenta(int numVenta, int seleccion) {
		rs = model.VerificarVenta(numVenta);
		String codventa = null;
		try {
			rs.next();
			codventa = rs.getString("codventa");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		if (codventa != null) {
			rs = model.ProductosVendidos(numVenta);
			try {
				rs.beforeFirst();
				while (rs.next()) {
					// new Object[] { "Cant.", "Producto", "Detalle", "Stock",
					// "Precio Uni", "SubTotal", "Cod.", "PC" });
					ResultSet rs2 = model.buscarProducto(rs.getString("codproducto"));
					rs2.next();
					dtm.addRow(new Object[] { rs.getString("cantidad"), //
							rs2.getString("producto"), rs2.getString("detalles"),
							Float.parseFloat(rs2.getString("cantidad")) + Float.parseFloat(rs.getString("cantidad")), // Stock
																														// de
																														// la
																														// tabla
							rs.getFloat("prevenFin"), // Precio al que se ha
														// vendido si hay
														// modificacion
							"0", // Sub total temporal
							rs.getString("codproducto"), rs2.getFloat("precioCo") }); // Codigo
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
			model.eliminarVenta("" + numVenta);
			for (int i = 0; i < tbCompras.getRowCount(); i++) {
				model.eliminarVentaDetalle(tbCompras.getValueAt(i, 6).toString(), "" + numVenta);
				model.reingresarProductos(tbCompras.getValueAt(i, 6).toString(),
						Float.parseFloat(tbCompras.getValueAt(i, 0).toString()));
			}
			if (seleccion == 0) {
				JOptionPane.showMessageDialog(null, "SE eliminará");
				limpiar();
				JOptionPane.showMessageDialog(null, "OPERACIÓN REALIZADA CORRECTAMENTE");
			}
			sumarSubTotales();
			sumarTotal();
		} else
			JOptionPane.showMessageDialog(null, "NO EXISTE LA VENTA");
	}

	protected void actionPerformedBtnNuevaVentana(ActionEvent arg0) {
		txtProductos.requestFocus();
		Ventas v2 = new Ventas((nventana + 1), el, usuario);
		v2.setVisible(true);
	}

	protected void actionPerformedBtnNuevoProducto(ActionEvent arg0) {
		NuevoProducto np = new NuevoProducto(null, this, null);
		np.setVisible(true);
		np.setLocationRelativeTo(null);
		this.setEnabled(false);
	}

	protected void keyPressedTbCompras(KeyEvent e) {
		String producto = tbCompras.getValueAt(tbCompras.getSelectedRow(), 1).toString() + " "
				+ tbCompras.getValueAt(tbCompras.getSelectedRow(), 2).toString();
		;
		String cantidad = tbCompras.getValueAt(tbCompras.getSelectedRow(), 0).toString();
		String preUnidad = tbCompras.getValueAt(tbCompras.getSelectedRow(), 4).toString();
		String preSubTotal = tbCompras.getValueAt(tbCompras.getSelectedRow(), 5).toString();
		String preOrigen = tbCompras.getValueAt(tbCompras.getSelectedRow(), 7).toString();
		String cod = tbCompras.getValueAt(tbCompras.getSelectedRow(), 6).toString();
		ModificarPrecioVenta cp = new ModificarPrecioVenta(this, producto, cantidad, preUnidad, preSubTotal, preOrigen,
				cod);
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER) {
			cp.setVisible(true);
			this.setEnabled(false);
			int fila = tbCompras.getSelectedRow() - 1;
			tbCompras.setRowSelectionInterval(fila, fila);
		}
	}

	public void actualizartabla(float cant, float preu, float preo, float pret) {
		tbCompras.setValueAt(redondearDecimales(cant, 2), tbCompras.getSelectedRow(), 0);
		tbCompras.setValueAt(redondearDecimales(preu, 2), tbCompras.getSelectedRow(), 4);
		tbCompras.setValueAt(redondearDecimales(preo, 2), tbCompras.getSelectedRow(), 7);
		tbCompras.setValueAt(redondearDecimales(pret, 2), tbCompras.getSelectedRow(), 5);
		// sumarSubTotales();
		sumarTotal();
	}

	protected void keyReleasedTxtPaga(KeyEvent e) {
		try {
			double pagacon = Float.parseFloat(txtPaga.getText());
			double tot = Float.parseFloat(lblTotal.getText());
			double vuelto = redondearDecimales(pagacon - tot, 2);
			if (vuelto < 0)
				txtVuelto.setText("0.00");
			else
				txtVuelto.setText("" + vuelto + "0");
		} catch (Exception e2) {
			txtVuelto.setText("0.00");
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tbCompras) {
			mouseClickedTbCompras(arg0);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void mouseClickedTbCompras(MouseEvent arg0) {
		try {
			String producto = tbCompras.getValueAt(tbCompras.getSelectedRow(), 1).toString() + " "
					+ tbCompras.getValueAt(tbCompras.getSelectedRow(), 2).toString();
			;
			String cantidad = tbCompras.getValueAt(tbCompras.getSelectedRow(), 0).toString();
			String preUnidad = tbCompras.getValueAt(tbCompras.getSelectedRow(), 4).toString();
			String preSubTotal = tbCompras.getValueAt(tbCompras.getSelectedRow(), 5).toString();
			String preOrigen = tbCompras.getValueAt(tbCompras.getSelectedRow(), 7).toString();
			String cod = tbCompras.getValueAt(tbCompras.getSelectedRow(), 6).toString();
			ModificarPrecioVenta cp = new ModificarPrecioVenta(this, producto, cantidad, preUnidad, preSubTotal,
					preOrigen, cod);

			cp.setVisible(true);
			this.setEnabled(false);
			int fila = tbCompras.getSelectedRow();
			tbCompras.setRowSelectionInterval(fila, fila);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	protected void keyTypedTxtCopias(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)
				&& (c != (char) KeyEvent.VK_ENTER)) {
			arg0.consume();
		}
		if (txtCopias.getText().length() == 2) {
			arg0.consume();
		}
	}

	protected void actionPerformedBtnReportes(ActionEvent arg0) {
		Reportes r = new Reportes(usuario, this);
		r.setVisible(true);
		dispose();
	}

	protected void actionPerformedBtnAnadirCliente(ActionEvent arg0) {
		NuevoCliente nc = new NuevoCliente(null, this, usuario);
		nc.setVisible(true);
		nc.setLocationRelativeTo(null);
		this.setEnabled(false);
	}

	public void anadirClienteCombpo(Cliente c) {
		cbClientes.addItem(c);
	}

	protected void itemStateChangedCbMetodoPago(ItemEvent arg0) {
		/*
		 * if(cbMetodoPago.getSelectedItem().equals("CRÉDITO")){
		 * lblPaga.setText("Deja a cuenta: "); lblVuelto.setText("Debe: "); }
		 * 
		 * else{ lblPaga.setText("Paga con: "); lblVuelto.setText(
		 * "Su vuelto es: "); }
		 */
	}

	protected void keyTypedTxtInfoAdicional(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (txtInfoAdicional.getText().length() == 199) {
			arg0.consume();
		}
	}
}
