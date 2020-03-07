package gui_compras;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;

import clases.Almacen;
import clases.Categoria;
import clases.Distribuidores;
import clases.UnidadMed;
import gui_mantenimiento_distribuidores.NuevoDistribuidor;
import mysql.consultas;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;

public class NuevaCompra extends JFrame {

	private JPanel contentPane;
	private JLabel lblCdigoIntbarras;
	private JLabel lblNombre;
	private JTextField txtSerie;
	private JLabel lblDescripcin;
	private JTextField txtNroSerie;
	private JLabel lblMarca;
	private JLabel lblColor;
	private JLabel lblFechaVencimiento;
	private JDateChooser dchFeVencimiento;
	private JLabel lblPrecioDeVenta;
	private JTextField txtTotal;
	private JButton btnRegistrarCompra;
	private JButton btnCancelar;
	private JTextField txtCrearProducto;
	private JLabel lblDistribuidor;
	private JLabel lblFechaDeEmisin;
	private JComboBox cbTipoComprobante;
	private JComboBox cbMoneda;
	private JTextField txtTipoCambio;
	private JDateChooser dchFeEmision;
	private JScrollPane scrollPane;
	private JTextField txtBuscarProducto;
	private JLabel label;
	private JTable tbCompras;
	private JButton btnAnadirDistri;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnIngresar;
	private JLabel lblNotaDeCompra;
	private JTextField txtNota;
	private JLabel lblMtodoDePago;
	private JComboBox cbMetPago;
	private JLabel lblPrecioUni;
	private JTextField txtPrecioUni;
	public JComboBox <Distribuidores> cbDistribuidor;
	

	public DefaultTableModel dtm = new DefaultTableModel();
	private TextAutoCompleter ac;
	ResultSet rs;
	consultas model = new consultas();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaCompra frame = new NuevaCompra();
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
	public NuevaCompra() {
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		setTitle("Crear producto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 618);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCdigoIntbarras = new JLabel("Tipo de comprobante:");
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.DARK_GRAY);
		lblCdigoIntbarras.setFont(new Font("Candara", Font.BOLD, 20));
		lblCdigoIntbarras.setBounds(12, 70, 190, 23);
		contentPane.add(lblCdigoIntbarras);
		
		lblNombre = new JLabel("Serie:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre.setBounds(554, 70, 60, 25);
		contentPane.add(lblNombre);
		
		txtSerie = new JTextField();
		txtSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtNombreProducto(e);
			}
		});
		txtSerie.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtSerie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombreProducto(e);
			}
		});
		txtSerie.setHorizontalAlignment(SwingConstants.LEFT);
		txtSerie.setForeground(Color.DARK_GRAY);
		txtSerie.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSerie.setColumns(10);
		txtSerie.setBackground(new Color(245, 245, 245));
		txtSerie.setBounds(614, 70, 130, 25);
		contentPane.add(txtSerie);
		
		lblDescripcin = new JLabel("Nro:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.DARK_GRAY);
		lblDescripcin.setFont(new Font("Candara", Font.BOLD, 20));
		lblDescripcin.setBounds(759, 70, 60, 25);
		contentPane.add(lblDescripcin);
		
		txtNroSerie = new JTextField();
		txtNroSerie.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtDescripcion(e);
			}
		});
		txtNroSerie.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNroSerie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtDescripcion(e);
			}
		});
		txtNroSerie.setHorizontalAlignment(SwingConstants.LEFT);
		txtNroSerie.setForeground(Color.DARK_GRAY);
		txtNroSerie.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNroSerie.setColumns(10);
		txtNroSerie.setBackground(new Color(245, 245, 245));
		txtNroSerie.setBounds(827, 70, 214, 25);
		contentPane.add(txtNroSerie);
		
		lblMarca = new JLabel("Moneda:");
		lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setForeground(Color.DARK_GRAY);
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(554, 107, 88, 25);
		contentPane.add(lblMarca);
		
		lblColor = new JLabel("Tipo de cambio:");
		lblColor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setForeground(Color.DARK_GRAY);
		lblColor.setFont(new Font("Candara", Font.BOLD, 20));
		lblColor.setBounds(759, 107, 149, 25);
		contentPane.add(lblColor);
		
		lblFechaVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.DARK_GRAY);
		lblFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaVencimiento.setBounds(554, 143, 193, 25);
		contentPane.add(lblFechaVencimiento);
		
		dchFeVencimiento = new JDateChooser();
		dchFeVencimiento.setFont(new Font("Arial", Font.PLAIN, 16));
		dchFeVencimiento.setForeground(Color.DARK_GRAY);
		dchFeVencimiento.setBounds(759, 143, 282, 25);
		contentPane.add(dchFeVencimiento);
		
		lblPrecioDeVenta = new JLabel("TOTAL DE COMPRA:");
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeVenta.setForeground(Color.DARK_GRAY);
		lblPrecioDeVenta.setFont(new Font("Candara", Font.BOLD, 25));
		lblPrecioDeVenta.setBounds(22, 516, 240, 49);
		contentPane.add(lblPrecioDeVenta);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrecioVenta(e);
			}
		});
		txtTotal.setText("0");
		txtTotal.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPrecioVenta(e);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrecioVenta(e);
			}
		});
		txtTotal.setHorizontalAlignment(SwingConstants.LEFT);
		txtTotal.setForeground(Color.DARK_GRAY);
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 25));
		txtTotal.setColumns(10);
		txtTotal.setBackground(new Color(245, 245, 245));
		txtTotal.setBounds(267, 516, 240, 49);
		contentPane.add(txtTotal);
		
		btnRegistrarCompra = new JButton("CREAR");
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnRegistrarCompra.setForeground(SystemColor.menu);
		btnRegistrarCompra.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRegistrarCompra.setBackground(new Color(30, 144, 255));
		btnRegistrarCompra.setBounds(801, 517, 240, 61);
		contentPane.add(btnRegistrarCompra);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCancelar(arg0);
			}
		});
		btnCancelar.setForeground(SystemColor.menu);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(220, 20, 60));
		btnCancelar.setBounds(536, 517, 240, 61);
		contentPane.add(btnCancelar);
		
		txtCrearProducto = new JTextField();
		txtCrearProducto.setText("REGISTRAR COMPRA");
		txtCrearProducto.setRequestFocusEnabled(false);
		txtCrearProducto.setIgnoreRepaint(true);
		txtCrearProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCrearProducto.setForeground(Color.WHITE);
		txtCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtCrearProducto.setFocusable(false);
		txtCrearProducto.setFocusTraversalKeysEnabled(false);
		txtCrearProducto.setEditable(false);
		txtCrearProducto.setColumns(10);
		txtCrearProducto.setBackground(Color.DARK_GRAY);
		txtCrearProducto.setBounds(0, 0, 1061, 50);
		contentPane.add(txtCrearProducto);
		
		lblDistribuidor = new JLabel("Distribuidor");
		lblDistribuidor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setForeground(Color.DARK_GRAY);
		lblDistribuidor.setFont(new Font("Candara", Font.BOLD, 20));
		lblDistribuidor.setBounds(12, 107, 191, 25);
		contentPane.add(lblDistribuidor);
		
		cbDistribuidor = new JComboBox();
		cbDistribuidor.setForeground(Color.DARK_GRAY);
		cbDistribuidor.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDistribuidor.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbDistribuidor.setBackground(new Color(245, 245, 245));
		cbDistribuidor.setBounds(212, 107, 240, 25);
		contentPane.add(cbDistribuidor);
		
		lblFechaDeEmisin = new JLabel("Fecha de emisi\u00F3n:");
		lblFechaDeEmisin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeEmisin.setForeground(Color.DARK_GRAY);
		lblFechaDeEmisin.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaDeEmisin.setBounds(12, 143, 190, 23);
		contentPane.add(lblFechaDeEmisin);
		
		cbTipoComprobante = new JComboBox();
		cbTipoComprobante.setModel(new DefaultComboBoxModel(new String[] {"Nota de compra", "Boleta", "Factura"}));
		cbTipoComprobante.setForeground(Color.DARK_GRAY);
		cbTipoComprobante.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipoComprobante.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbTipoComprobante.setBackground(new Color(245, 245, 245));
		cbTipoComprobante.setBounds(212, 70, 300, 25);
		contentPane.add(cbTipoComprobante);
		
		cbMoneda = new JComboBox();
		cbMoneda.setModel(new DefaultComboBoxModel(new String[] {"Soles", "Dolares"}));
		cbMoneda.setForeground(Color.DARK_GRAY);
		cbMoneda.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMoneda.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbMoneda.setBackground(new Color(245, 245, 245));
		cbMoneda.setBounds(644, 107, 100, 25);
		contentPane.add(cbMoneda);
		
		txtTipoCambio = new JTextField();
		txtTipoCambio.setHorizontalAlignment(SwingConstants.LEFT);
		txtTipoCambio.setForeground(Color.DARK_GRAY);
		txtTipoCambio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTipoCambio.setColumns(10);
		txtTipoCambio.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtTipoCambio.setBackground(new Color(245, 245, 245));
		txtTipoCambio.setBounds(911, 107, 130, 25);
		contentPane.add(txtTipoCambio);
		
		dchFeEmision = new JDateChooser();
		dchFeEmision.setBounds(212, 143, 300, 23);
		contentPane.add(dchFeEmision);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(12, 311, 1029, 193);
		contentPane.add(scrollPane);
		
		tbCompras = new JTable();
		tbCompras.setAutoCreateRowSorter(true);
		tbCompras.setFont(new Font("Arial", Font.ITALIC, 14));
		scrollPane.setViewportView(tbCompras);
		
		txtBuscarProducto = new JTextField();
		txtBuscarProducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtBuscarProducto(e);
			}
		});
		txtBuscarProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtBuscarProducto(e);
			}
		});
		txtBuscarProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscarProducto.setFont(new Font("Arial", Font.ITALIC, 20));
		txtBuscarProducto.setColumns(10);
		txtBuscarProducto.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtBuscarProducto.setBackground(new Color(245, 245, 245));
		txtBuscarProducto.setBounds(12, 270, 500, 34);
		contentPane.add(txtBuscarProducto);
		
		label = new JLabel("Buscar producto:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBounds(12, 244, 495, 23);
		contentPane.add(label);
		
		btnAnadirDistri = new JButton("+");
		btnAnadirDistri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAnadirDistri(e);
			}
		});
		btnAnadirDistri.setForeground(Color.WHITE);
		btnAnadirDistri.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnadirDistri.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnAnadirDistri.setBackground(new Color(30, 144, 255));
		btnAnadirDistri.setBounds(458, 107, 54, 25);
		contentPane.add(btnAnadirDistri);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setForeground(Color.DARK_GRAY);
		lblCantidad.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidad.setBounds(522, 244, 120, 23);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				focusGainedTxtCantidad(arg0);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtCantidad(e);
			}
		});
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtCantidad(arg0);
			}
		});
		txtCantidad.setText("1");
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setFont(new Font("Arial", Font.ITALIC, 20));
		txtCantidad.setColumns(10);
		txtCantidad.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtCantidad.setBackground(new Color(245, 245, 245));
		txtCantidad.setBounds(522, 270, 120, 34);
		contentPane.add(txtCantidad);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setForeground(SystemColor.menu);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnIngresar.setBackground(new Color(60, 179, 113));
		btnIngresar.setBounds(898, 266, 143, 34);
		contentPane.add(btnIngresar);
		
		lblNotaDeCompra = new JLabel("Nota de compra:");
		lblNotaDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotaDeCompra.setForeground(Color.DARK_GRAY);
		lblNotaDeCompra.setFont(new Font("Candara", Font.BOLD, 20));
		lblNotaDeCompra.setBounds(12, 177, 190, 23);
		contentPane.add(lblNotaDeCompra);
		
		txtNota = new JTextField();
		txtNota.setHorizontalAlignment(SwingConstants.LEFT);
		txtNota.setForeground(Color.DARK_GRAY);
		txtNota.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNota.setColumns(10);
		txtNota.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNota.setBackground(new Color(245, 245, 245));
		txtNota.setBounds(212, 177, 295, 25);
		contentPane.add(txtNota);
		
		lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago.setBounds(554, 177, 190, 23);
		contentPane.add(lblMtodoDePago);
		
		cbMetPago = new JComboBox();
		cbMetPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbMetPago.setForeground(Color.DARK_GRAY);
		cbMetPago.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMetPago.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbMetPago.setBackground(new Color(245, 245, 245));
		cbMetPago.setBounds(759, 177, 282, 25);
		contentPane.add(cbMetPago);
		
		lblPrecioUni = new JLabel("Precio Uni.");
		lblPrecioUni.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUni.setForeground(Color.DARK_GRAY);
		lblPrecioUni.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrecioUni.setBounds(656, 244, 120, 23);
		contentPane.add(lblPrecioUni);
		
		txtPrecioUni = new JTextField();
		txtPrecioUni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrecioUni(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPrecioUni(e);
			}
		});
		txtPrecioUni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrecioUni(e);
			}
		});
		txtPrecioUni.setText("1");
		txtPrecioUni.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecioUni.setFont(new Font("Arial", Font.ITALIC, 20));
		txtPrecioUni.setColumns(10);
		txtPrecioUni.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtPrecioUni.setBackground(new Color(245, 245, 245));
		txtPrecioUni.setBounds(656, 270, 120, 34);
		contentPane.add(txtPrecioUni);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBuscarProducto, txtCantidad, txtPrecioUni, btnIngresar, cbTipoComprobante, txtSerie, txtNroSerie, cbDistribuidor, btnAnadirDistri, cbMoneda, txtTipoCambio, dchFeEmision, dchFeVencimiento, txtNota, cbMetPago, btnRegistrarCompra, btnCancelar}));
		
		cargar();
		cargarBuscador();
	}
	
	private void cargar(){
		tbCompras.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Precio indiv.", "Sub Total"});
		ajustarAnchoColumnas();
		tbCompras.setRowHeight(25);
		
		
		// COMBO DISTRIBUIDOR
		Distribuidores distribuidor = new Distribuidores();
		distribuidor.cargarDistribuidores(cbDistribuidor);
	}
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCompras.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));  // 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(60));  // 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));  // 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));  // 
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCompras.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tbCompras.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tbCompras.getColumnModel().getColumn(3).setCellRenderer(tcr);		
	}
	
	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtBuscarProducto);
		consultas model = new consultas();
		ResultSet rs = model.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) 
				ac.addItem(rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " * " + rs.getString("unimedida") + 
						" - " + rs.getString("almacen") + "  -  (" + rs.getString("codproducto") + ")");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
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
	
	public void recargarCombos(){
		try {
			cbDistribuidor.removeAllItems();
			Distribuidores dist = new Distribuidores();
			dist.cargarDistribuidores(cbDistribuidor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al recargar: " + e.getCause());
		}
	}
	
	public void recargarCombosDist(int iddist){
		cbDistribuidor.removeAllItems();
		Distribuidores dist = new Distribuidores();
		dist.cargarDistribuidores(cbDistribuidor);
		
		for(int i = 0; i<cbDistribuidor.getItemCount(); i++){
			if(cbDistribuidor.getItemAt(i).getIddist() == iddist)
				cbDistribuidor.setSelectedIndex(i);
		}
	}
	
	protected void keyReleasedTxtPrecioVenta(KeyEvent e) {
		
	}
	protected void keyTypedTxtPrecioVenta(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtTotal.getText().length() == 8)
			e.consume();
		if (c == '.' && txtTotal.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtNombreProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtSerie.getText().length() == 200 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtDescripcion(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtNroSerie.getText().length() == 200 || c=='(' || c==')')
			e.consume();
	}
	protected void windowClosingThis(WindowEvent arg0) {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //CERRAR VENTANA
		this.dispose();
	}
	
	protected void actionPerformedBtnCrearProducto(ActionEvent arg0) {
		
		int tipComprobante =0;		tipComprobante = cbTipoComprobante.getSelectedIndex();
		String serie = "";			serie = txtSerie.getText();
		String nroSerie = "";		nroSerie = txtNroSerie.getText();
		int idDistrib = 0;			idDistrib = cbDistribuidor.getItemAt(cbDistribuidor.getSelectedIndex()).getIddist();
		String moneda = "";			moneda = cbMoneda.getSelectedItem().toString();
		String tc = "";				tc = txtTipoCambio.getText();
		Object fechaEmision = null;
		Object fechaVencimiento = null;
		String nota = "";			nota = txtNota.getText();
		String metPago = "";		cbMetPago.getSelectedItem().toString();
		
		try {
			int añoe = dchFeEmision.getCalendar().get(Calendar.YEAR);
			int mese = dchFeEmision.getCalendar().get(Calendar.MARCH) + 1;
			int diae = dchFeEmision.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaE = añoe + "-" + mese + "-" + diae;

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) formatter.parse(fechaE);
			fechaEmision = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
		}
		
		try {
			int añov = dchFeVencimiento.getCalendar().get(Calendar.YEAR);
			int mesv = dchFeVencimiento.getCalendar().get(Calendar.MARCH) + 1;
			int diav = dchFeVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
			String fechaV = añov + "-" + mesv + "-" + diav;

			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) formatter.parse(fechaV);
			fechaVencimiento = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
		}
		
		model.registrarCompra(tipComprobante, serie, nroSerie, idDistrib, moneda, tc, nota, metPago, fechaEmision, fechaVencimiento);

		int idCompra = 0;
		rs = model.ObtenerUltimoCodigoCompra();
		try {
			while (rs.next())
				idCompra = rs.getInt("idcompra");
		} catch (Exception e3) {
			JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo código: " + e3);
		}
		
		
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			String prod = tbCompras.getValueAt(i, 1).toString();
			int idProd = Integer.parseInt( prod.substring(prod.indexOf("(")+1, prod.indexOf(")")));
			
			double cantProd = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
			double preIndivProd = Float.parseFloat(tbCompras.getValueAt(i, 2).toString());
				preIndivProd = redondearDecimales(preIndivProd, 2);
			double preSubTotProd = Float.parseFloat(tbCompras.getValueAt(i, 3).toString());
				preSubTotProd = redondearDecimales(preSubTotProd, 2);
			
			model.registrarCompraDetalles(idCompra, idProd, cantProd, preIndivProd, preSubTotProd);	
				
		}
		JOptionPane.showMessageDialog(null, "Registrado correctamente");
		this.dispose();
		
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}
	private void seleccionarTexto(FocusEvent e){
		Object o = e.getSource();
        if(o instanceof javax.swing.JTextField){
            javax.swing.JTextField txt = (javax.swing.JTextField) o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
	}
	protected void focusGainedTxtPrecioVenta(FocusEvent e) {
		seleccionarTexto(e);
		if(txtTotal.getText().equals("0"))
			txtTotal.setText("");
	}
	protected void focusGainedTxtNombreProducto(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtDescripcion(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void actionPerformedBtnAnadirDistri(ActionEvent e) {
		/*NuevoDistribuidor nd = new NuevoDistribuidor(null, this, null);
		try {
			if (nd.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nd.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nd.setVisible(true); 
			} else {
				nd = new NuevoDistribuidor(null, this, null);
				nd.setLocationRelativeTo(null);
				nd.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}*/
	}
	
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		AgregarProductoATabla();
		txtBuscarProducto.requestFocus();
		txtBuscarProducto.setText("");
		txtCantidad.setText("1");
		txtPrecioUni.setText("1");
	}
	public void AgregarProductoATabla() {
		try { // SI LO QUE SE INGRESA ES UN NOMBRE DE PRODUCTO
			String nomProducto = txtBuscarProducto.getText();
			//int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			double cantidad = Double.parseDouble(txtCantidad.getText());
				cantidad = redondearDecimales(cantidad, 2);
			double precioUnidad = Double.parseDouble(txtPrecioUni.getText());
				precioUnidad = redondearDecimales(precioUnidad, 2);
			double precioSubTot = precioUnidad*cantidad;
				precioSubTot = redondearDecimales(precioSubTot, 2);
				
			dtm.addRow(new Object[]{cantidad, nomProducto, precioUnidad, precioSubTot});
			
			sumarTotal();

		} catch (Exception e) { 
		}
	}
	private void sumarTotal(){
		double total = 0;
		for (int i = 0; i < tbCompras.getRowCount(); i++) {
			double cantProd = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
			double precioSubTotProd = Float.parseFloat(tbCompras.getValueAt(i, 3).toString());
			total = total + precioSubTotProd;
		}
		total = redondearDecimales(total, 2);
		txtTotal.setText(""+total);
	}

	
	protected void keyTypedTxtBuscarProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER)
			txtCantidad.requestFocus();
	}
	
	protected void keyTypedTxtCantidad(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER)
			txtPrecioUni.requestFocus();
	}
	protected void keyTypedTxtPrecioUni(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			AgregarProductoATabla();
			txtBuscarProducto.requestFocus();
			txtBuscarProducto.setText("");
			txtCantidad.setText("1");
			txtPrecioUni.setText("1");
		}
	}
	protected void focusGainedTxtCantidad(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtPrecioUni(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtBuscarProducto(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusLostTxtCantidad(FocusEvent e) {
		if(txtCantidad.getText().length()==0)
			txtCantidad.setText("0");
	}
	protected void focusLostTxtPrecioUni(FocusEvent e) {
		if(txtPrecioUni.getText().length()==0)
			txtPrecioUni.setText("0");
	}
}
