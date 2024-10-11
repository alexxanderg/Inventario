package gui_compras;

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
import clases.Distribuidores;
import gui_distribuidores.NuevoDistribuidor;
import gui_productos.NuevoProducto;
import mysql.consultas;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JCheckBox;

public class NuevaCompra extends JFrame {

	private JPanel contentPane;
	private JLabel lblCdigoIntbarras;
	private JLabel lblNombre;
	private JTextField txtSerie;
	private JLabel lblDescripcin;
	private JTextField txtNroSerie;
	private JLabel lblFechaVencimiento;
	private JDateChooser dchFeVencimiento;
	private JButton btnRegistrarCompra;
	private JButton btnCancelar;
	private JTextField txtCrearProducto;
	private JLabel lblDistribuidor;
	private JLabel lblFechaDeEmisin;
	private JComboBox cbTipoComprobante;
	private JDateChooser dchFeEmision;
	private JScrollPane scrollPane;
	private JTextField txtBuscarProducto;
	private JLabel label;
	private JTable tbCompras;
	private JButton btnAnadirDistri;
	private JLabel lblNotaDeCompra;
	private JTextField txtNota;
	private JLabel lblMtodoDePago;
	private JComboBox cbMetPago;
	public JComboBox<Distribuidores> cbDistribuidor;

	public Modelaso dtm = new Modelaso();
	private TextAutoCompleter ac;
	ResultSet rs;
	consultas consulta = new consultas();

	int idUsuario = 0;
	MantenimientoCompras mantCompras = null;
	private JButton btnNuevoProducto;
	NuevoProducto np = new NuevoProducto(null, this, null);
	private JButton btnQuitar;
	private JButton btnAyuda;
	private JLabel lblTotal;
	private JCheckBox chckActualizarPrecios;

	private JDateChooser dateFechaVenc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaCompra frame = new NuevaCompra(0, null);
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
	public NuevaCompra(int idUsuario, MantenimientoCompras mantCompras) {
		this.idUsuario = idUsuario;
		this.mantCompras = mantCompras;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		setTitle("Crear producto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1069, 652);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCdigoIntbarras = new JLabel("Tipo de comprobante:");
		lblCdigoIntbarras.setBounds(12, 105, 205, 23);
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.DARK_GRAY);
		lblCdigoIntbarras.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblCdigoIntbarras);

		lblNombre = new JLabel("Serie:");
		lblNombre.setBounds(554, 105, 60, 25);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblNombre);

		txtSerie = new JTextField();
		txtSerie.setBounds(612, 105, 132, 25);
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
		contentPane.add(txtSerie);

		lblDescripcin = new JLabel("Nro:");
		lblDescripcin.setBounds(759, 105, 60, 25);
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.DARK_GRAY);
		lblDescripcin.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblDescripcin);

		txtNroSerie = new JTextField();
		txtNroSerie.setBounds(827, 105, 214, 25);
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
		contentPane.add(txtNroSerie);

		lblFechaVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaVencimiento.setVisible(false);
		lblFechaVencimiento.setBounds(554, 232, 205, 25);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.DARK_GRAY);
		lblFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblFechaVencimiento);

		dchFeVencimiento = new JDateChooser();
		dchFeVencimiento.setVisible(false);
		dchFeVencimiento.setBounds(759, 232, 282, 25);
		dchFeVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dchFeVencimiento.setForeground(Color.DARK_GRAY);
		contentPane.add(dchFeVencimiento);

		btnRegistrarCompra = new JButton("REGISTRAR");
		btnRegistrarCompra.setBounds(262, 541, 240, 61);
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnRegistrarCompra.setForeground(SystemColor.menu);
		btnRegistrarCompra.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRegistrarCompra.setBackground(new Color(30, 144, 255));
		contentPane.add(btnRegistrarCompra);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(12, 541, 240, 61);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCancelar(arg0);
			}
		});
		btnCancelar.setForeground(SystemColor.menu);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(220, 20, 60));
		contentPane.add(btnCancelar);

		txtCrearProducto = new JTextField();
		txtCrearProducto.setBounds(0, 0, 1061, 50);
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
		contentPane.add(txtCrearProducto);

		lblDistribuidor = new JLabel("Distribuidor");
		lblDistribuidor.setBounds(12, 142, 191, 25);
		lblDistribuidor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setForeground(Color.DARK_GRAY);
		lblDistribuidor.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblDistribuidor);

		cbDistribuidor = new JComboBox();
		cbDistribuidor.setBounds(212, 142, 240, 25);
		cbDistribuidor.setForeground(Color.DARK_GRAY);
		cbDistribuidor.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDistribuidor.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbDistribuidor.setBackground(new Color(245, 245, 245));
		contentPane.add(cbDistribuidor);

		lblFechaDeEmisin = new JLabel("Fecha de emisi\u00F3n:");
		lblFechaDeEmisin.setBounds(554, 144, 177, 23);
		lblFechaDeEmisin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeEmisin.setForeground(Color.DARK_GRAY);
		lblFechaDeEmisin.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblFechaDeEmisin);

		cbTipoComprobante = new JComboBox();
		cbTipoComprobante.setBounds(212, 105, 300, 25);
		cbTipoComprobante.setModel(new DefaultComboBoxModel(new String[] { "Nota de compra", "Boleta", "Factura" }));
		cbTipoComprobante.setForeground(Color.DARK_GRAY);
		cbTipoComprobante.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipoComprobante.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbTipoComprobante.setBackground(new Color(245, 245, 245));
		contentPane.add(cbTipoComprobante);

		dchFeEmision = new JDateChooser();
		dchFeEmision.setBounds(759, 144, 282, 23);
		contentPane.add(dchFeEmision);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 337, 1029, 193);
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		contentPane.add(scrollPane);

		tbCompras = new JTable();
		tbCompras.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				propertyChangeTbCompras(evt);
			}
		});
		tbCompras.setAutoCreateRowSorter(true);
		tbCompras.setFont(new Font("Arial", Font.ITALIC, 14));
		scrollPane.setViewportView(tbCompras);

		txtBuscarProducto = new JTextField();
		txtBuscarProducto.setBounds(12, 296, 500, 34);
		txtBuscarProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtBuscarProducto(arg0);
			}
		});
		txtBuscarProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscarProducto.setFont(new Font("Arial", Font.ITALIC, 16));
		txtBuscarProducto.setColumns(10);
		txtBuscarProducto.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtBuscarProducto.setBackground(new Color(245, 245, 245));
		contentPane.add(txtBuscarProducto);

		label = new JLabel("Buscar producto:");
		label.setBounds(12, 270, 190, 23);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(label);

		btnAnadirDistri = new JButton("+");
		btnAnadirDistri.setBounds(458, 142, 54, 25);
		btnAnadirDistri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAnadirDistri(e);
			}
		});
		btnAnadirDistri.setForeground(Color.WHITE);
		btnAnadirDistri.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnadirDistri.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnAnadirDistri.setBackground(new Color(30, 144, 255));
		contentPane.add(btnAnadirDistri);

		lblNotaDeCompra = new JLabel("Nota:");
		lblNotaDeCompra.setBounds(12, 178, 190, 23);
		lblNotaDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotaDeCompra.setForeground(Color.DARK_GRAY);
		lblNotaDeCompra.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblNotaDeCompra);

		txtNota = new JTextField();
		txtNota.setBounds(212, 178, 300, 25);
		txtNota.setHorizontalAlignment(SwingConstants.LEFT);
		txtNota.setForeground(Color.DARK_GRAY);
		txtNota.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNota.setColumns(10);
		txtNota.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNota.setBackground(new Color(245, 245, 245));
		contentPane.add(txtNota);

		lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		lblMtodoDePago.setVisible(false);
		lblMtodoDePago.setBounds(554, 268, 190, 23);
		lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		contentPane.add(lblMtodoDePago);

		cbMetPago = new JComboBox();
		cbMetPago.setVisible(false);
		cbMetPago.setBounds(759, 268, 282, 25);
		cbMetPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "YAPE/PLIN"}));
		cbMetPago.setForeground(Color.DARK_GRAY);
		cbMetPago.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMetPago.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbMetPago.setBackground(new Color(245, 245, 245));
		contentPane.add(cbMetPago);

		btnNuevoProducto = new JButton("Crear nuevo");
		btnNuevoProducto.setBounds(891, 296, 150, 34);
		btnNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNuevoProducto(arg0);
			}
		});
		btnNuevoProducto.setForeground(Color.WHITE);
		btnNuevoProducto.setFont(new Font("Arial", Font.BOLD, 20));
		btnNuevoProducto.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnNuevoProducto.setBackground(new Color(60, 179, 113));
		contentPane.add(btnNuevoProducto);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(781, 296, 100, 34);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnQuitar(arg0);
			}
		});
		btnQuitar.setForeground(SystemColor.menu);
		btnQuitar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuitar.setBackground(new Color(220, 20, 60));
		contentPane.add(btnQuitar);

		btnAyuda = new JButton("AYUDA");
		btnAyuda.setBounds(298, 222, 214, 32);
		btnAyuda.setVisible(false);
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAyuda(e);
			}
		});
		btnAyuda.setForeground(Color.DARK_GRAY);
		btnAyuda.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAyuda.setBackground(new Color(255, 215, 0));
		contentPane.add(btnAyuda);

		lblTotal = new JLabel("S/");
		lblTotal.setBounds(850, 550, 191, 54);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.DARK_GRAY);
		lblTotal.setFont(new Font("Candara", Font.BOLD, 30));
		contentPane.add(lblTotal);
		
		JLabel lblEjmB = new JLabel("ejm: B001");
		lblEjmB.setBounds(612, 80, 132, 23);
		lblEjmB.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEjmB.setHorizontalAlignment(SwingConstants.CENTER);
		lblEjmB.setForeground(Color.DARK_GRAY);
		lblEjmB.setFont(new Font("Candara", Font.BOLD, 15));
		contentPane.add(lblEjmB);
		
		JLabel lblEjm = new JLabel("ejm: 135");
		lblEjm.setBounds(867, 80, 132, 23);
		lblEjm.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEjm.setHorizontalAlignment(SwingConstants.CENTER);
		lblEjm.setForeground(Color.DARK_GRAY);
		lblEjm.setFont(new Font("Candara", Font.BOLD, 15));
		contentPane.add(lblEjm);
		
		chckActualizarPrecios = new JCheckBox("<html>\u00BFActualizar precios de compra y venta, en base al porcentaje de ganancia de cada producto?</html>");
		chckActualizarPrecios.setVerticalAlignment(SwingConstants.TOP);
		chckActualizarPrecios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		chckActualizarPrecios.setBounds(531, 552, 326, 50);
		contentPane.add(chckActualizarPrecios);
		
		JLabel lblTienda = new JLabel("Tienda:");
		lblTienda.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienda.setForeground(Color.DARK_GRAY);
		lblTienda.setFont(new Font("Candara", Font.BOLD, 20));
		lblTienda.setBounds(554, 178, 205, 25);
		contentPane.add(lblTienda);
		
		JComboBox cbTienda = new JComboBox();
		cbTienda.setVisible(false);
		cbTienda.setModel(new DefaultComboBoxModel(new String[] {"Tienda 1", "Tienda 2", "Tienda 3", "Tienda 4"}));
		cbTienda.setForeground(Color.DARK_GRAY);
		cbTienda.setFont(new Font("Arial", Font.BOLD, 16));
		cbTienda.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbTienda.setBackground(new Color(245, 245, 245));
		cbTienda.setBounds(759, 177, 282, 25);
		contentPane.add(cbTienda);
		// setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{txtBuscarProducto, btnIngresar, cbTipoComprobante, txtSerie,
		// txtNroSerie, cbDistribuidor, btnAnadirDistri, cbMoneda, txtTipoCambio,
		// dchFeEmision, dchFeVencimiento, txtNota, cbMetPago, txtPagado,
		// btnRegistrarCompra, btnCancelar}));

		cargar();
		cargarBuscador();
	}

	private void cargar() {
		this.tbCompras.setModel(this.dtm);
		this.dtm.setColumnIdentifiers(
				new Object[] { "Cantidad", "Producto y detalles", "Precio indiv.", "Sub Total", "Lote", "Fe.Ve. (dd-mm-aaaa)", "Codigo" });
		ajustarAnchoColumnas();
		this.tbCompras.setRowHeight(25);

		Distribuidores distribuidor = new Distribuidores();
		distribuidor.cargarDistribuidores(this.cbDistribuidor);

		Date date = new Date();
		date.getTime();
		this.dchFeEmision.setDate(date);
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * this.scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = this.tbCompras.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(50));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(17));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(1));
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(0);

		this.tbCompras.getColumnModel().getColumn(0).setCellRenderer(tcr);
		this.tbCompras.getColumnModel().getColumn(2).setCellRenderer(tcr);
		this.tbCompras.getColumnModel().getColumn(3).setCellRenderer(tcr);
	}

	public void cargarBuscador() {
		this.ac = new TextAutoCompleter(this.txtBuscarProducto);
		this.ac.setMode(0);
		try {
			this.consulta.iniciar();
			this.rs = this.consulta.cargarProductos();
			while (this.rs.next())
				this.ac.addItem(this.rs.getString("producto") + " " + this.rs.getString("detalles") + " "
						+ this.rs.getString("marca") + " " + this.rs.getString("color") + "*"
						+ this.rs.getString("laboratorio") + " * " + this.rs.getString("unimedida") + " - "
						+ this.rs.getString("almacen") + "  -  (" + this.rs.getString("codproducto") + ")");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.consulta != null)
					this.consulta.reset();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		} finally {
			try {
				if (this.rs != null)
					this.rs.close();
				if (this.consulta != null)
					this.consulta.reset();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			}
		}
	}

	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double resultado = valorInicial;
		double parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10.0D, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = resultado / Math.pow(10.0D, numeroDecimales) + parteEntera;
		return resultado;
	}

	public void recargarCombos() {
		try {
			this.cbDistribuidor.removeAllItems();
			Distribuidores dist = new Distribuidores();
			dist.cargarDistribuidores(this.cbDistribuidor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al recargar: " + e.getCause());
		}
	}

	public void recargarCombosDist(int iddist) {
		this.cbDistribuidor.removeAllItems();
		Distribuidores dist = new Distribuidores();
		dist.cargarDistribuidores(this.cbDistribuidor);

		for (int i = 0; i < this.cbDistribuidor.getItemCount(); i++)
			if (((Distribuidores) this.cbDistribuidor.getItemAt(i)).getIddist() == iddist)
				this.cbDistribuidor.setSelectedIndex(i);
	}

	protected void keyTypedTxtNombreProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if ((this.txtSerie.getText().length() == 200) || (c == '(') || (c == ')'))
			e.consume();
	}

	protected void keyTypedTxtDescripcion(KeyEvent e) {
		char c = e.getKeyChar();
		if ((this.txtNroSerie.getText().length() == 200) || (c == '(') || (c == ')'))
			e.consume();
	}

	protected void windowClosingThis(WindowEvent arg0) {
		setDefaultCloseOperation(0);
		dispose();
	}

	protected void actionPerformedBtnCrearProducto(ActionEvent arg0) {
		try {
			if (this.tbCompras.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Ingrese sus productos comprados");
			} else {
				int opc = JOptionPane.showConfirmDialog(null,
						"�Est� seguro de registrar la compra?\nEste registro no se podr� eliminar o modificar en un futuro. Por favor verifique sus productos y precios",
						"Confirmar", 0, 3);
				if (opc == 0) {
					int tipComprobante = 0;
					tipComprobante = this.cbTipoComprobante.getSelectedIndex();
					String serie = "";
					serie = this.txtSerie.getText();
					String nroSerie = "";
					nroSerie = this.txtNroSerie.getText();
					int idDistrib = 0;
					idDistrib = ((Distribuidores) this.cbDistribuidor.getItemAt(this.cbDistribuidor.getSelectedIndex()))
							.getIddist();
					String moneda = "";
					moneda = "Soles";
					String tc = "";
					tc = "";
					Object fechaEmision = null;
					Object fechaVencimiento = null;
										
					String nota = "";
					nota = this.txtNota.getText();
					String metPago = "";
					this.cbMetPago.getSelectedItem().toString();
					double total = 0.0D;
					total = Float.parseFloat(this.lblTotal.getText());
					double pagado = 0.0D;
					double saldo = 0.0D;
					try {
						int anioe = this.dchFeEmision.getCalendar().get(1);
						int mese = this.dchFeEmision.getCalendar().get(2) + 1;
						int diae = this.dchFeEmision.getCalendar().get(5);
						String fechaE = anioe + "-" + mese + "-" + diae;

						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date date = formatter.parse(fechaE);
						fechaEmision = new Timestamp(date.getTime());
					} catch (Exception localException1) {
					}
					try {
						int aniov = this.dchFeVencimiento.getCalendar().get(1);
						int mesv = this.dchFeVencimiento.getCalendar().get(2) + 1;
						int diav = this.dchFeVencimiento.getCalendar().get(5);
						String fechaV = aniov + "-" + mesv + "-" + diav;

						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date date = formatter.parse(fechaV);
						fechaVencimiento = new Timestamp(date.getTime());
					} catch (Exception localException2) {
					}
					this.consulta.iniciar();
					this.consulta.registrarCompra(tipComprobante, serie, nroSerie, idDistrib, moneda, tc, nota, metPago,
							fechaEmision, fechaVencimiento, this.idUsuario, total, pagado, saldo);
					this.consulta.reset();

					int idCompra = 0;
					try {
						this.consulta.iniciar();
						this.rs = this.consulta.ObtenerUltimoCodigoCompra();
						while (this.rs.next())
							idCompra = this.rs.getInt("idcompra");
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo c�digo de compra: " + e3);
						try {
							if (this.rs != null)
								this.rs.close();
							if (this.consulta != null)
								this.consulta.reset();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						}
					} finally {
						try {
							if (this.rs != null)
								this.rs.close();
							if (this.consulta != null)
								this.consulta.reset();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						}
					}

					// COMPRAS DETALLES
					
					for (int i = 0; i < this.tbCompras.getRowCount(); i++) {
						
						String prod = this.tbCompras.getValueAt(i, 1).toString();
						int idProd = Integer.parseInt(this.tbCompras.getValueAt(i, 6).toString());

						double cantProd = Float.parseFloat(this.tbCompras.getValueAt(i, 0).toString());
						double preIndivProd = Float.parseFloat(this.tbCompras.getValueAt(i, 2).toString());
						preIndivProd = redondearDecimales(preIndivProd, 4);
						double preSubTotProd = Float.parseFloat(this.tbCompras.getValueAt(i, 3).toString());
						preSubTotProd = redondearDecimales(preSubTotProd, 4);
						String lote = this.tbCompras.getValueAt(i, 4).toString();
						

						Object fechaVencimientoProducto = null;
						try {
							String fechaVP = this.tbCompras.getValueAt(i, 5).toString();
							DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
							Date date = formatter.parse(fechaVP);
							fechaVencimientoProducto = new Timestamp(date.getTime());
						} catch (Exception e1) {
							//JOptionPane.showMessageDialog(null, "Fecha erronea: " + e1);
						}
						this.consulta.iniciar();
						this.consulta.registrarCompraDetalles(idCompra, idProd, cantProd, preIndivProd, preSubTotProd,
								lote, fechaVencimientoProducto);
						this.consulta.anadirStockProducto(idProd, cantProd);
						this.consulta.reset();
						
						// ACTUALIZAR PRECIOS
						try {
							if(chckActualizarPrecios.isSelected()) {
								this.consulta.iniciar();
								rs = this.consulta.buscarProductoID(idProd);
								
								rs.next();
								double ptjGanancia = rs.getDouble("ptjganancia");
								double newpreve = preIndivProd + (preIndivProd * (ptjGanancia*0.01));
								this.consulta.reset();
								
								
								this.consulta.iniciar();
								this.consulta.ActualizarPrecios(idProd, preIndivProd, newpreve);

								JOptionPane.showMessageDialog(null, "Precios actualizados");
								
								this.consulta.reset();
							}
								
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al actualizar precios: " + e);
						}						
						
					}
					JOptionPane.showMessageDialog(null, "Compra registrada correctamente");
					dispose();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error con el registro de la compra: " + e);
		}
	}

	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		dispose();
	}

	private void seleccionarTexto(FocusEvent e) {
		Object o = e.getSource();
		if ((o instanceof JTextField)) {
			JTextField txt = (JTextField) o;
			txt.setSelectionStart(0);
			txt.setSelectionEnd(txt.getText().length());
		}
	}

	protected void focusGainedTxtNombreProducto(FocusEvent e) {
		seleccionarTexto(e);
	}

	protected void focusGainedTxtDescripcion(FocusEvent e) {
		seleccionarTexto(e);
	}

	protected void actionPerformedBtnAnadirDistri(ActionEvent e) {
		NuevoDistribuidor nd = new NuevoDistribuidor(null, null, null, this);
		try {
			if (nd.isShowing()) {
				nd.setExtendedState(0);
				nd.setVisible(true);
			} else {
				nd = new NuevoDistribuidor(null, null, null, this);
				nd.setLocationRelativeTo(null);
				nd.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	
	protected void keyReleasedTxtBuscarProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c == '\n') && (this.txtBuscarProducto.getText().length() != 0)) {
			AgregarProductoATabla();
			

			bloquearCeldas();
			
			this.txtBuscarProducto.setText(null);
		}
	}

	public void bloquearCeldas() {
		dtm.isCellEditable(tbCompras.getSelectedRow(), 1);
		dtm.isCellEditable(tbCompras.getSelectedRow(), 3);
		dtm.isCellEditable(tbCompras.getSelectedRow(), 6);
	}
	
	public class Modelaso extends DefaultTableModel {

		 public boolean isCellEditable (int row, int column)
		    {
			 // Aqu� devolvemos true o false seg�n queramos que una celda
		        // identificada por fila,columna (row,column), sea o no editable
		        if (column == 1 || column == 3 || column == 6 )
		           return false;
		        return true;
		    }
	}
	
	
	public void AgregarProductoATabla() {
		try {
			String nomProducto = this.txtBuscarProducto.getText();
			int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			
			consulta.iniciar();
			rs = consulta.buscarProductoID(idProd);
						
			try {
				//rs.beforeFirst(); // "Cantidad", "Producto y detalles", "Pre Indiv Ori", "Desc tot aplicado", "SubTotal", "IDPROD", "PC", "Stock", "Pre Indiv C/Desc" 
				while (rs.next()) {
					dtm.addRow(new Object[] {
							"1", 
							rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " (" + rs.getString("unimedida") + ")",     
							rs.getFloat("precioCo"),
							rs.getFloat("precioCo"),"","",rs.getString("codproducto")
							});
					tbCompras.setRowSelectionInterval(tbCompras.getRowCount() - 1, tbCompras.getRowCount() - 1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No existe el producto: " + e);
			}
			
			

			/*String prod = this.txtBuscarProducto.getText();
			double cantidad = Double.parseDouble(JOptionPane.showInputDialog(prod
					+ "\n\nINGRESE LA CANTIDAD:\n\nEjm.: Si compro 1 saco de arroz de 50k, pero lo vende por kilos, deber�a ingresar 50\n"));
			cantidad = redondearDecimales(cantidad, 2);
			double precioUnidad = Double.parseDouble(JOptionPane.showInputDialog(prod
					+ "\n\nINGRESE PRECIO DE COMPRA INDIVIDUAL:\n\nSe colocar�a el precio de compra por kilo, no del saco\nEjm.: 3.5\n"));
			precioUnidad = redondearDecimales(precioUnidad, 2);
			double precioSubTot = precioUnidad * cantidad;
			precioSubTot = redondearDecimales(precioSubTot, 2);
			String lote = JOptionPane.showInputDialog(prod + "\n\nINGRESE NRO DE LOTE SI TUVIERA");

			this.dtm.addRow(new Object[] { Double.valueOf(cantidad), nomProducto, Double.valueOf(precioUnidad),
					Double.valueOf(precioSubTot), lote });*/
			
			
			//this.dtm.addRow(new Object[] { 1, nomProducto, 2, 2,""});
			
			sumarTotal();
		} catch (Exception e) {
			String codbarra = txtBuscarProducto.getText();
			consulta.iniciar();
			rs = consulta.buscarProductoBarras(codbarra);
			int flag = 0;
			float cantidad = 0;
			
				try {
					rs.beforeFirst();
					while (rs.next()) {
						dtm.addRow(new Object[] { 
								"1", 
								rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " (" + rs.getString("unimedida") + ")",     
								rs.getFloat("precioCo"),
								rs.getFloat("precioCo"),"","",rs.getString("codproducto")
								});
						tbCompras.setRowSelectionInterval(tbCompras.getRowCount() - 1, tbCompras.getRowCount() - 1);
					}
				} catch (Exception ex) {
				}

				sumarTotal();
		}
	}

	private void sumarTotal() {
		double total = 0.0;
		for (int i = 0; i < this.tbCompras.getRowCount(); i++) {
			double cantProd = Float.parseFloat(this.tbCompras.getValueAt(i, 0).toString());
			double precioSubTotProd = Float.parseFloat(this.tbCompras.getValueAt(i, 3).toString());
			total += precioSubTotProd;
		}
		total = redondearDecimales(total, 4);

		this.lblTotal.setText("" + total);
	}

	

	protected void actionPerformedBtnNuevoProducto(ActionEvent arg0) {
		try {
			if (this.np.isShowing()) {
				this.np.setExtendedState(0);
				this.np.setVisible(true);
			} else {
				this.np = new NuevoProducto(null, this, null);
				this.np.setLocationRelativeTo(null);
				this.np.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}

	public void cargarProducto(String prod) {
		this.txtBuscarProducto.setText(prod);
	}

	protected void actionPerformedBtnQuitar(ActionEvent arg0) {
		this.dtm.removeRow(this.tbCompras.getSelectedRow());

		sumarTotal();
	}

	protected void actionPerformedBtnAyuda(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"A continuaci�n registre los detalles y productos de su compra.\n\n"
				+ "Ingrese los datos de el recibo de la compra (todos estos campos son opcionales)\n"
				+ "En fecha de vencimiento puede colocar alguna fecha si desea que el sistema le avise cuando caducar� el pago de la compra)\n\n"
				+ "Para registrar sus productos comprados:\n"
				+ "1.- Busque el producto por su nombre o codigo de barras.\n"
				+ "2.- Ingrese la cantidad de este (seg�n la unidad de medida con la que haya registrado el producto anteriormente).\n"
				+ "3.- Ingrese su precio de compra individual.\n"
				+ "Ejm.: Si compr� un saco de arroz de 50k pero se registro por kilos. El ingreso ser�a de la siguiente forma:\n"
				+ "Producto: Arroz camanejo (kilo)  -  Cantidad: 50  -  Precio: 3.0\n\n"
				+ "Al finalizar, ver� el monto total de su compra, al costado puede indicar si la pag� completamente o si queda un saldo pendiente.\n"
				+ "Finalizar");
	}
	
	
	protected void propertyChangeTbCompras(PropertyChangeEvent evt) {
		
		try {
			calcularSubTotales();
			sumarTotal();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	void calcularSubTotales() {
		double subtotal = 0.0;
		for (int i = 0; i < this.tbCompras.getRowCount(); i++) {
			double cantProd = Float.parseFloat(this.tbCompras.getValueAt(i, 0).toString());
			double precioInd = Float.parseFloat(this.tbCompras.getValueAt(i, 2).toString());
			double precioSubTot = cantProd * precioInd;
			precioSubTot = redondearDecimales(precioSubTot, 4);
			
			this.tbCompras.setValueAt(precioSubTot, i, 3);
			
		}
		subtotal = redondearDecimales(subtotal, 4);

		this.lblTotal.setText("" + subtotal);
	}
}













