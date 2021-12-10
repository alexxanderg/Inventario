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
	private JButton btnIngresar;
	private JLabel lblNotaDeCompra;
	private JTextField txtNota;
	private JLabel lblMtodoDePago;
	private JComboBox cbMetPago;
	public JComboBox <Distribuidores> cbDistribuidor;
	

	public DefaultTableModel dtm = new DefaultTableModel();
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
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.DARK_GRAY);
		lblCdigoIntbarras.setFont(new Font("Candara", Font.BOLD, 20));
		lblCdigoIntbarras.setBounds(12, 105, 205, 23);
		contentPane.add(lblCdigoIntbarras);
		
		lblNombre = new JLabel("Serie:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre.setBounds(554, 105, 60, 25);
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
		txtSerie.setBounds(614, 105, 130, 25);
		contentPane.add(txtSerie);
		
		lblDescripcin = new JLabel("Nro:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.DARK_GRAY);
		lblDescripcin.setFont(new Font("Candara", Font.BOLD, 20));
		lblDescripcin.setBounds(759, 105, 60, 25);
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
		txtNroSerie.setBounds(827, 105, 214, 25);
		contentPane.add(txtNroSerie);
		
		lblMarca = new JLabel("Moneda:");
		lblMarca.setVisible(false);
		lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setForeground(Color.DARK_GRAY);
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(554, 142, 88, 25);
		contentPane.add(lblMarca);
		
		lblColor = new JLabel("Tipo de cambio:");
		lblColor.setVisible(false);
		lblColor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setForeground(Color.DARK_GRAY);
		lblColor.setFont(new Font("Candara", Font.BOLD, 20));
		lblColor.setBounds(759, 142, 149, 25);
		contentPane.add(lblColor);
		
		lblFechaVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaVencimiento.setVisible(false);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.DARK_GRAY);
		lblFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaVencimiento.setBounds(554, 247, 205, 25);
		contentPane.add(lblFechaVencimiento);
		
		dchFeVencimiento = new JDateChooser();
		dchFeVencimiento.setVisible(false);
		dchFeVencimiento.setFont(new Font("Arial", Font.PLAIN, 16));
		dchFeVencimiento.setForeground(Color.DARK_GRAY);
		dchFeVencimiento.setBounds(759, 247, 282, 25);
		contentPane.add(dchFeVencimiento);
		
		btnRegistrarCompra = new JButton("REGISTRAR");
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnRegistrarCompra.setForeground(SystemColor.menu);
		btnRegistrarCompra.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRegistrarCompra.setBackground(new Color(30, 144, 255));
		btnRegistrarCompra.setBounds(262, 541, 240, 61);
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
		btnCancelar.setBounds(12, 541, 240, 61);
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
		lblDistribuidor.setBounds(12, 142, 191, 25);
		contentPane.add(lblDistribuidor);
		
		cbDistribuidor = new JComboBox();
		cbDistribuidor.setForeground(Color.DARK_GRAY);
		cbDistribuidor.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDistribuidor.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbDistribuidor.setBackground(new Color(245, 245, 245));
		cbDistribuidor.setBounds(212, 142, 240, 25);
		contentPane.add(cbDistribuidor);
		
		lblFechaDeEmisin = new JLabel("Fecha de emisi\u00F3n:");
		lblFechaDeEmisin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeEmisin.setForeground(Color.DARK_GRAY);
		lblFechaDeEmisin.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaDeEmisin.setBounds(12, 178, 190, 23);
		contentPane.add(lblFechaDeEmisin);
		
		cbTipoComprobante = new JComboBox();
		cbTipoComprobante.setModel(new DefaultComboBoxModel(new String[] {"Nota de compra", "Boleta", "Factura"}));
		cbTipoComprobante.setForeground(Color.DARK_GRAY);
		cbTipoComprobante.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipoComprobante.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbTipoComprobante.setBackground(new Color(245, 245, 245));
		cbTipoComprobante.setBounds(212, 105, 300, 25);
		contentPane.add(cbTipoComprobante);
		
		cbMoneda = new JComboBox();
		cbMoneda.setVisible(false);
		cbMoneda.setModel(new DefaultComboBoxModel(new String[] {"Soles", "Dolares"}));
		cbMoneda.setForeground(Color.DARK_GRAY);
		cbMoneda.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMoneda.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbMoneda.setBackground(new Color(245, 245, 245));
		cbMoneda.setBounds(644, 142, 100, 25);
		contentPane.add(cbMoneda);
		
		txtTipoCambio = new JTextField();
		txtTipoCambio.setVisible(false);
		txtTipoCambio.setHorizontalAlignment(SwingConstants.LEFT);
		txtTipoCambio.setForeground(Color.DARK_GRAY);
		txtTipoCambio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTipoCambio.setColumns(10);
		txtTipoCambio.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtTipoCambio.setBackground(new Color(245, 245, 245));
		txtTipoCambio.setBounds(911, 142, 130, 25);
		contentPane.add(txtTipoCambio);
		
		dchFeEmision = new JDateChooser();
		dchFeEmision.setBounds(212, 178, 300, 23);
		contentPane.add(dchFeEmision);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(12, 337, 1029, 193);
		contentPane.add(scrollPane);
		
		tbCompras = new JTable();
		tbCompras.setAutoCreateRowSorter(true);
		tbCompras.setFont(new Font("Arial", Font.ITALIC, 14));
		scrollPane.setViewportView(tbCompras);
		
		txtBuscarProducto = new JTextField();
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
		txtBuscarProducto.setBounds(12, 296, 544, 34);
		contentPane.add(txtBuscarProducto);
		
		label = new JLabel("Buscar producto:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setBounds(12, 270, 190, 23);
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
		btnAnadirDistri.setBounds(458, 142, 54, 25);
		contentPane.add(btnAnadirDistri);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setForeground(SystemColor.menu);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnIngresar.setBackground(new Color(60, 179, 113));
		btnIngresar.setBounds(788, 296, 143, 34);
		contentPane.add(btnIngresar);
		
		lblNotaDeCompra = new JLabel("Nota: ");
		lblNotaDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotaDeCompra.setForeground(Color.DARK_GRAY);
		lblNotaDeCompra.setFont(new Font("Candara", Font.BOLD, 20));
		lblNotaDeCompra.setBounds(12, 212, 190, 23);
		contentPane.add(lblNotaDeCompra);
		
		txtNota = new JTextField();
		txtNota.setHorizontalAlignment(SwingConstants.LEFT);
		txtNota.setForeground(Color.DARK_GRAY);
		txtNota.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNota.setColumns(10);
		txtNota.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNota.setBackground(new Color(245, 245, 245));
		txtNota.setBounds(212, 212, 829, 25);
		contentPane.add(txtNota);
		
		lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		lblMtodoDePago.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago.setBounds(554, 178, 190, 23);
		contentPane.add(lblMtodoDePago);
		
		cbMetPago = new JComboBox();
		cbMetPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbMetPago.setForeground(Color.DARK_GRAY);
		cbMetPago.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMetPago.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbMetPago.setBackground(new Color(245, 245, 245));
		cbMetPago.setBounds(759, 178, 282, 25);
		contentPane.add(cbMetPago);
		
		btnNuevoProducto = new JButton("+");
		btnNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNuevoProducto(arg0);
			}
		});
		btnNuevoProducto.setForeground(Color.WHITE);
		btnNuevoProducto.setFont(new Font("Arial", Font.BOLD, 20));
		btnNuevoProducto.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnNuevoProducto.setBackground(new Color(30, 144, 255));
		btnNuevoProducto.setBounds(560, 301, 54, 25);
		contentPane.add(btnNuevoProducto);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnQuitar(arg0);
			}
		});
		btnQuitar.setForeground(SystemColor.menu);
		btnQuitar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuitar.setBackground(new Color(220, 20, 60));
		btnQuitar.setBounds(941, 296, 100, 34);
		contentPane.add(btnQuitar);
		
		btnAyuda = new JButton("AYUDA");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnAyuda(e);
			}
		});
		btnAyuda.setForeground(Color.DARK_GRAY);
		btnAyuda.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAyuda.setBackground(new Color(255, 215, 0));
		btnAyuda.setBounds(827, 61, 214, 32);
		contentPane.add(btnAyuda);
		
		lblTotal = new JLabel("S/");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.DARK_GRAY);
		lblTotal.setFont(new Font("Candara", Font.BOLD, 30));
		lblTotal.setBounds(827, 550, 214, 54);
		contentPane.add(lblTotal);
		//setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBuscarProducto, btnIngresar, cbTipoComprobante, txtSerie, txtNroSerie, cbDistribuidor, btnAnadirDistri, cbMoneda, txtTipoCambio, dchFeEmision, dchFeVencimiento, txtNota, cbMetPago, txtPagado, btnRegistrarCompra, btnCancelar}));
		
		cargar();
		cargarBuscador();
	}
	
	private void cargar(){
		tbCompras.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Precio indiv.", "Sub Total", "Lote"});
		ajustarAnchoColumnas();
		tbCompras.setRowHeight(25);
		
		
		// COMBO DISTRIBUIDOR
		Distribuidores distribuidor = new Distribuidores();
		distribuidor.cargarDistribuidores(cbDistribuidor);
		
		java.util.Date date = new Date();
		date.getTime();
		dchFeEmision.setDate(date);
		//dchFeVencimiento.setDate(date);
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
		ac.setMode(0);
		try {
			consulta.iniciar();
			rs = consulta.cargarProductos();
			while (rs.next()) 
				ac.addItem(rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + "*" + rs.getString("laboratorio") + " * " + rs.getString("unimedida") + 
						" - " + rs.getString("almacen") + "  -  (" + rs.getString("codproducto") + ")");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
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
		try {
			if(tbCompras.getRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Ingrese sus productos comprados");
			else{
				int opc = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar la compra?\nEste registro no se podrá eliminar o modificar en un futuro, le pedimos por favor verifique sus productos y precios", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (opc == 0) {
					
					int tipComprobante = 0;		tipComprobante = cbTipoComprobante.getSelectedIndex();
					String serie = "";			serie = txtSerie.getText();
					String nroSerie = "";		nroSerie = txtNroSerie.getText();
					int idDistrib = 0;			idDistrib = cbDistribuidor.getItemAt(cbDistribuidor.getSelectedIndex()).getIddist();
					String moneda = "";			moneda = cbMoneda.getSelectedItem().toString();
					String tc = "";				tc = txtTipoCambio.getText();
					Object fechaEmision = null;
					Object fechaVencimiento = null;
					
					String nota = "";			nota = txtNota.getText();
					String metPago = "";		cbMetPago.getSelectedItem().toString();
					double total = 0;
					total = Float.parseFloat(lblTotal.getText());
					double pagado = 0;
					double saldo = 0;					
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
			
					consulta.iniciar();
					consulta.registrarCompra(tipComprobante, serie, nroSerie, idDistrib, moneda, tc, nota, metPago, fechaEmision, fechaVencimiento, idUsuario, total, pagado, saldo);
					consulta.reset();
					
					int idCompra = 0;
					try {
						consulta.iniciar();
						rs = consulta.ObtenerUltimoCodigoCompra();
						while (rs.next())
							idCompra = rs.getInt("idcompra");
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo código de compra: " + e3);
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
					
					for (int i = 0; i < tbCompras.getRowCount(); i++) {
						String prod = tbCompras.getValueAt(i, 1).toString();
						int idProd = Integer.parseInt( prod.substring(prod.indexOf("(")+1, prod.indexOf(")")));
						
						double cantProd = Float.parseFloat(tbCompras.getValueAt(i, 0).toString());
						double preIndivProd = Float.parseFloat(tbCompras.getValueAt(i, 2).toString());
							preIndivProd = redondearDecimales(preIndivProd, 2);
						double preSubTotProd = Float.parseFloat(tbCompras.getValueAt(i, 3).toString());
							preSubTotProd = redondearDecimales(preSubTotProd, 2);
						String lote = tbCompras.getValueAt(i, 4).toString();
							
							
						consulta.iniciar();
						consulta.registrarCompraDetalles(idCompra, idProd, cantProd, preIndivProd, preSubTotProd, lote);	
						consulta.anadirStockProducto(idProd, cantProd);
						consulta.reset();
					}
					JOptionPane.showMessageDialog(null, "Compra registrada correctamente");
					this.dispose();
				}
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error con el registro de la compra: "+ e);
		}
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
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nd.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
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
	
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		AgregarProductoATabla();
		txtBuscarProducto.requestFocus();
		txtBuscarProducto.setText(null);
	}
	public void AgregarProductoATabla() {
		try { // SI LO QUE SE INGRESA ES UN NOMBRE DE PRODUCTO
			String nomProducto = txtBuscarProducto.getText();
			//int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			/*double cantidad = Double.parseDouble(txtCantidad.getText());
				cantidad = redondearDecimales(cantidad, 2);
			double precioUnidad = Double.parseDouble(txtPrecioUni.getText());
				precioUnidad = redondearDecimales(precioUnidad, 2);
			double precioSubTot = precioUnidad*cantidad;
				precioSubTot = redondearDecimales(precioSubTot, 2);*/
			String  prod = txtBuscarProducto.getText();
			double cantidad = Double.parseDouble( JOptionPane.showInputDialog(prod + "\n\nINGRESE LA CANTIDAD:\n\nEjm.: Si compro 1 saco de arroz de 50k, pero lo vende por kilos, debería ingresar 50\n"));
				cantidad = redondearDecimales(cantidad, 2);
			double precioUnidad = Double.parseDouble( JOptionPane.showInputDialog(prod + "\n\nINGRESE PRECIO DE COMPRA INDIVIDUAL:\n\nSe colocaría el precio de compra por kilo, no del saco\nEjm.: 3.5\n"));
				precioUnidad = redondearDecimales(precioUnidad, 2);
			double precioSubTot = precioUnidad*cantidad;
				precioSubTot = redondearDecimales(precioSubTot, 2);
			String lote = JOptionPane.showInputDialog(prod + "\n\nINGRESE NRO DE LOTE SI TUVIERA");
			
			dtm.addRow(new Object[]{cantidad, nomProducto, precioUnidad, precioSubTot, lote});
			
			sumarTotal();

		} catch (Exception e) { 
			JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
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
		
		lblTotal.setText(""+total);
	}

	protected void keyReleasedTxtBuscarProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			if(txtBuscarProducto.getText().length()!=0){
				AgregarProductoATabla();
				txtBuscarProducto.setText(null);
			}
		}
	}
	
	protected void actionPerformedBtnNuevoProducto(ActionEvent arg0) {
		try {
			if (np.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				np.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				np.setVisible(true); 
			} else {
				np = new NuevoProducto(null, this, null);
				np.setLocationRelativeTo(null);
				np.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	public void cargarProducto (String prod){
		txtBuscarProducto.setText(prod);
	}
	
	protected void actionPerformedBtnQuitar(ActionEvent arg0) {
		dtm.removeRow(tbCompras.getSelectedRow());
	}
	
	protected void actionPerformedBtnAyuda(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "A continuación registre los detalles y productos de su compra.\n\n"
				+ "Ingrese los datos de el recibo de la compra (todos estos campos son opcionales)\n"
				+ "En fecha de vencimiento puede colocar alguna fecha si desea que el sistema le avise cuando caducará el pago de la compra)\n\n"
				+ "Para registrar sus productos comprados:\n"
				+ "1.- Busque el producto por su nombre o codigo de barras.\n"
				+ "2.- Ingrese la cantidad de este (según la unidad de medida con la que haya registrado el producto anteriormente).\n"
				+ "3.- Ingrese su precio de compra individual.\n"
				+ "Ejm.: Si compró un saco de arroz de 50k pero se registro por kilos. El ingreso sería de la siguiente forma:\n"
				+ "Producto: Arroz camanejo (kilo)  -  Cantidad: 50  -  Precio: 3.0\n\n"
				+ "Al finalizar, verá el monto total de su compra, al costado puede indicar si la pagó completamente o si queda un saldo pendiente.\n"
				+ "Finalizar");
	}
}







