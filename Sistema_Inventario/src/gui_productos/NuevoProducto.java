package gui_productos;

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
import gui_compras.NuevaCompra;
import gui_distribuidores.NuevoDistribuidor;
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
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NuevoProducto extends JFrame {

	private JPanel contentPane;
	private JLabel lblCdigoIntbarras;
	private JTextField txtCodbarras;
	private JLabel lblNombre;
	private JTextField txtNombreProducto;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JLabel lblCategora;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JLabel lblColor;
	private JTextField txtColor;
	private JLabel lblFechaVencimiento;
	private JDateChooser dateFechaVenc;
	private JLabel lblUniMedida;
	private JComboBox <UnidadMed> cbUnidadMedida;
	private JLabel lblCantidadActual;
	private JLabel lblPrecioDeCompra;
	private JTextField txtPrecioCompra;
	private JLabel lblPrecioDeVenta;
	private JTextField txtPrecioVenta;
	private JTextField txtStockInicial;
	private JLabel lblNombrePromo1;
	private JTextField txtNombrePromo1;
	private JLabel lblCantidadPromo1;
	private JTextField txtCantPromo1;
	private JLabel lblPrePromo1;
	private JTextField txtPrePromo1;
	private JLabel lblNombrePromo2;
	private JTextField txtNombrePromo2;
	private JLabel lblCantidadPromo2;
	private JTextField txtCantPromo2;
	private JLabel lblPrePromo2;
	private JTextField txtPrePromo2;
	private JTextField txtID;
	private JLabel lblLote;
	private JTextField txtLote;
	private JLabel lblLaboratorio;
	private JTextField txtLaboratorio;
	private JLabel lblAlmacn;
	private JButton btnCrearProducto;
	private JLabel lblNota;
	private JLabel lblCantidadMnima;
	private JTextField txtStockMinimo;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel lblDeGanancia;
	private JTextField txtPtjGanancia;
	private JButton btnCancelar;
	private JComboBox <Categoria> cbCategoria;
	private JComboBox <Almacen> cbAlmacen;
	private JTextField txtCrearProducto;
	private JLabel lblDistribuidor;
	private JLabel label_9;
	public JComboBox <Distribuidores> cbDistribuidor;

	private TextAutoCompleter acCB;
	private TextAutoCompleter acProd;
	private TextAutoCompleter acDesc;
	private TextAutoCompleter acMarc;
	private TextAutoCompleter acCol;
	private TextAutoCompleter acLab;
	
	ResultSet rs;
	consultas consulta = new consultas();
	MantenimientoProd mantenimientoProductos;
	NuevaCompra nc = null;
	int primeravez = 0; //0=NO. VERIFICA SI ES LA PRIMERA VEZ EN INGRESAR AL SISTEMA, PARA CREAR AUTOMATICAMENTE EL PRODUCTO EJEMPLO DE LOS COMBOS  
	String usuario;
	
	private JButton btnAnadirDistri;
	
	private JTextField txtPrePromo3;
	private JTextField txtCantPromo3;
	
	private JTextField txtNombrePromo3;
	private JLabel lblStockMximo;
	private JLabel label;
	private JTextField txtStockMaximo;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoProducto frame = new NuevoProducto(null, null, null);
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
	public NuevoProducto(MantenimientoProd mantenimientoProductos, NuevaCompra nc, String usuario) {
		this.mantenimientoProductos = mantenimientoProductos;
		this.nc = nc;
		this.usuario = usuario;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		setTitle("Crear producto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 612);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCdigoIntbarras = new JLabel("Cod. Interno/Barras:");
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.DARK_GRAY);
		lblCdigoIntbarras.setFont(new Font("Candara", Font.BOLD, 20));
		lblCdigoIntbarras.setBounds(9, 61, 190, 23);
		contentPane.add(lblCdigoIntbarras);
		
		txtCodbarras = new JTextField();
		txtCodbarras.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtCodbarras(e);
			}
		});
		txtCodbarras.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtCodbarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCodbarras(e);
			}
		});
		txtCodbarras.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodbarras.setForeground(Color.DARK_GRAY);
		txtCodbarras.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCodbarras.setColumns(10);
		txtCodbarras.setBackground(new Color(245, 245, 245));
		txtCodbarras.setBounds(209, 61, 300, 25);
		contentPane.add(txtCodbarras);
		
		lblNombre = new JLabel("Nombre Producto:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombre.setBounds(9, 95, 190, 25);
		contentPane.add(lblNombre);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtNombreProducto(e);
			}
		});
		txtNombreProducto.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNombreProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombreProducto(e);
			}
		});
		txtNombreProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreProducto.setForeground(Color.DARK_GRAY);
		txtNombreProducto.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreProducto.setColumns(10);
		txtNombreProducto.setBackground(new Color(245, 245, 245));
		txtNombreProducto.setBounds(209, 95, 300, 25);
		contentPane.add(txtNombreProducto);
		
		lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.DARK_GRAY);
		lblDescripcin.setFont(new Font("Candara", Font.BOLD, 20));
		lblDescripcin.setBounds(9, 131, 190, 25);
		contentPane.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtDescripcion(e);
			}
		});
		txtDescripcion.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtDescripcion(e);
			}
		});
		txtDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescripcion.setForeground(Color.DARK_GRAY);
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBackground(new Color(245, 245, 245));
		txtDescripcion.setBounds(209, 131, 300, 25);
		contentPane.add(txtDescripcion);
		
		lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora.setForeground(Color.DARK_GRAY);
		lblCategora.setFont(new Font("Candara", Font.BOLD, 20));
		lblCategora.setBounds(9, 275, 190, 25);
		contentPane.add(lblCategora);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setVisible(false);
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setForeground(Color.DARK_GRAY);
		lblMarca.setFont(new Font("Candara", Font.BOLD, 20));
		lblMarca.setBounds(9, 167, 191, 25);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtMarca(e);
			}
		});
		txtMarca.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtMarca(e);
			}
		});
		txtMarca.setVisible(false);
		txtMarca.setHorizontalAlignment(SwingConstants.LEFT);
		txtMarca.setForeground(Color.DARK_GRAY);
		txtMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMarca.setColumns(10);
		txtMarca.setBackground(new Color(245, 245, 245));
		txtMarca.setBounds(209, 167, 301, 25);
		contentPane.add(txtMarca);
		
		lblColor = new JLabel("Color:");
		lblColor.setVisible(false);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setForeground(Color.DARK_GRAY);
		lblColor.setFont(new Font("Candara", Font.BOLD, 20));
		lblColor.setBounds(9, 203, 191, 25);
		contentPane.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtColor(e);
			}
		});
		txtColor.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtColor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtColor(e);
			}
		});
		txtColor.setVisible(false);
		txtColor.setHorizontalAlignment(SwingConstants.LEFT);
		txtColor.setForeground(Color.DARK_GRAY);
		txtColor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtColor.setColumns(10);
		txtColor.setBackground(new Color(245, 245, 245));
		txtColor.setBounds(209, 203, 301, 25);
		contentPane.add(txtColor);
		
		lblFechaVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaVencimiento.setVisible(false);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.DARK_GRAY);
		lblFechaVencimiento.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaVencimiento.setBounds(532, 61, 193, 25);
		contentPane.add(lblFechaVencimiento);
		
		dateFechaVenc = new JDateChooser();
		dateFechaVenc.setFont(new Font("Arial", Font.PLAIN, 16));
		dateFechaVenc.setForeground(Color.DARK_GRAY);
		dateFechaVenc.setVisible(false);
		dateFechaVenc.setBounds(737, 61, 300, 25);
		contentPane.add(dateFechaVenc);
		
		lblCantidadActual = new JLabel("Stock actual:");
		lblCantidadActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadActual.setForeground(new Color(220, 20, 60));
		lblCantidadActual.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadActual.setBounds(9, 385, 190, 25);
		contentPane.add(lblCantidadActual);
		
		txtStockInicial = new JTextField();
		txtStockInicial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				focusGainedTxtStockInicial(arg0);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtStockInicial(e);
			}
		});
		txtStockInicial.setText("0");
		txtStockInicial.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtStockInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtStockInicial(e);
			}
		});
		txtStockInicial.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockInicial.setForeground(new Color(220, 20, 60));
		txtStockInicial.setFont(new Font("Arial", Font.BOLD, 16));
		txtStockInicial.setColumns(10);
		txtStockInicial.setBackground(new Color(245, 245, 245));
		txtStockInicial.setBounds(210, 385, 150, 25);
		contentPane.add(txtStockInicial);
		
		lblUniMedida = new JLabel("Uni. Medida");
		lblUniMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniMedida.setForeground(Color.DARK_GRAY);
		lblUniMedida.setFont(new Font("Candara", Font.BOLD, 20));
		lblUniMedida.setBounds(9, 239, 190, 25);
		contentPane.add(lblUniMedida);
		
		lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeCompra.setForeground(Color.DARK_GRAY);
		lblPrecioDeCompra.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrecioDeCompra.setBounds(10, 499, 190, 25);
		contentPane.add(lblPrecioDeCompra);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrecioCompra(e);
			}
		});
		txtPrecioCompra.setText("0");
		txtPrecioCompra.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPrecioCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPrecioCompra(e);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrecioCompra(e);
			}
		});
		txtPrecioCompra.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioCompra.setForeground(Color.DARK_GRAY);
		txtPrecioCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecioCompra.setColumns(10);
		txtPrecioCompra.setBackground(new Color(245, 245, 245));
		txtPrecioCompra.setBounds(211, 499, 149, 25);
		contentPane.add(txtPrecioCompra);
		
		lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeVenta.setForeground(Color.DARK_GRAY);
		lblPrecioDeVenta.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrecioDeVenta.setBounds(10, 537, 190, 25);
		contentPane.add(lblPrecioDeVenta);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrecioVenta(e);
			}
		});
		txtPrecioVenta.setText("0");
		txtPrecioVenta.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPrecioVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPrecioVenta(e);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrecioVenta(e);
			}
		});
		txtPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenta.setForeground(Color.DARK_GRAY);
		txtPrecioVenta.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBackground(new Color(245, 245, 245));
		txtPrecioVenta.setBounds(211, 535, 149, 25);
		contentPane.add(txtPrecioVenta);
		
		lblNombrePromo1 = new JLabel("PROMOCI\u00D3N 1:");
		lblNombrePromo1.setVisible(false);
		lblNombrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo1.setForeground(new Color(50, 205, 50));
		lblNombrePromo1.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombrePromo1.setBounds(532, 131, 193, 25);
		contentPane.add(lblNombrePromo1);
		
		txtNombrePromo1 = new JTextField();
		txtNombrePromo1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtNombrePromo1(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtNombrePromo1(e);
			}
		});
		txtNombrePromo1.setBorder(new LineBorder(new Color(32, 178, 170), 1, true));
		txtNombrePromo1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombrePromo1(e);
			}
		});
		txtNombrePromo1.setVisible(false);
		txtNombrePromo1.setText("0");
		txtNombrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombrePromo1.setForeground(Color.DARK_GRAY);
		txtNombrePromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombrePromo1.setColumns(10);
		txtNombrePromo1.setBackground(new Color(245, 245, 245));
		txtNombrePromo1.setBounds(737, 131, 300, 25);
		contentPane.add(txtNombrePromo1);
		
		lblCantidadPromo1 = new JLabel("CANTIDAD:");
		lblCantidadPromo1.setVisible(false);
		lblCantidadPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo1.setForeground(new Color(50, 205, 50));
		lblCantidadPromo1.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadPromo1.setBounds(532, 167, 193, 25);
		contentPane.add(lblCantidadPromo1);
		
		txtCantPromo1 = new JTextField();
		txtCantPromo1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtCantPromo1(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtCantPromo1(e);
			}
		});
		txtCantPromo1.setBorder(new LineBorder(new Color(32, 178, 170), 1, true));
		txtCantPromo1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCantPromo1(e);
			}
		});
		txtCantPromo1.setVisible(false);
		txtCantPromo1.setText("0");
		txtCantPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo1.setForeground(Color.DARK_GRAY);
		txtCantPromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantPromo1.setColumns(10);
		txtCantPromo1.setBackground(new Color(245, 245, 245));
		txtCantPromo1.setBounds(737, 167, 154, 25);
		contentPane.add(txtCantPromo1);
		
		lblPrePromo1 = new JLabel("PRECIO:");
		lblPrePromo1.setVisible(false);
		lblPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo1.setForeground(new Color(50, 205, 50));
		lblPrePromo1.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrePromo1.setBounds(532, 203, 193, 25);
		contentPane.add(lblPrePromo1);
		
		txtPrePromo1 = new JTextField();
		txtPrePromo1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrePromo1(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPrePromo1(e);
			}
		});
		txtPrePromo1.setBorder(new LineBorder(new Color(32, 178, 170), 1, true));
		txtPrePromo1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrePromo1(e);
			}
		});
		txtPrePromo1.setVisible(false);
		txtPrePromo1.setText("0");
		txtPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo1.setForeground(Color.DARK_GRAY);
		txtPrePromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrePromo1.setColumns(10);
		txtPrePromo1.setBackground(new Color(245, 245, 245));
		txtPrePromo1.setBounds(737, 203, 154, 25);
		contentPane.add(txtPrePromo1);
		
		lblNombrePromo2 = new JLabel("PROMOCI\u00D3N 2:");
		lblNombrePromo2.setVisible(false);
		lblNombrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo2.setForeground(new Color(220, 20, 60));
		lblNombrePromo2.setFont(new Font("Candara", Font.BOLD, 20));
		lblNombrePromo2.setBounds(532, 277, 193, 25);
		contentPane.add(lblNombrePromo2);
		
		txtNombrePromo2 = new JTextField();
		txtNombrePromo2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtNombrePromo2(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtNombrePromo2(e);
			}
		});
		txtNombrePromo2.setBorder(new LineBorder(new Color(147, 112, 219), 1, true));
		txtNombrePromo2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtNombrePromo2(e);
			}
		});
		txtNombrePromo2.setVisible(false);
		txtNombrePromo2.setText("0");
		txtNombrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombrePromo2.setForeground(Color.DARK_GRAY);
		txtNombrePromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombrePromo2.setColumns(10);
		txtNombrePromo2.setBackground(new Color(245, 245, 245));
		txtNombrePromo2.setBounds(737, 277, 300, 25);
		contentPane.add(txtNombrePromo2);
		
		lblCantidadPromo2 = new JLabel("CANTIDAD:");
		lblCantidadPromo2.setVisible(false);
		lblCantidadPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo2.setForeground(new Color(220, 20, 60));
		lblCantidadPromo2.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadPromo2.setBounds(532, 313, 193, 25);
		contentPane.add(lblCantidadPromo2);
		
		txtCantPromo2 = new JTextField();
		txtCantPromo2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtCantPromo2(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtCantPromo2(e);
			}
		});
		txtCantPromo2.setBorder(new LineBorder(new Color(147, 112, 219), 1, true));
		txtCantPromo2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCantPromo2(e);
			}
		});
		txtCantPromo2.setVisible(false);
		txtCantPromo2.setText("0");
		txtCantPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo2.setForeground(Color.DARK_GRAY);
		txtCantPromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantPromo2.setColumns(10);
		txtCantPromo2.setBackground(new Color(245, 245, 245));
		txtCantPromo2.setBounds(737, 313, 150, 25);
		contentPane.add(txtCantPromo2);
		
		lblPrePromo2 = new JLabel("PRECIO:");
		lblPrePromo2.setVisible(false);
		lblPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo2.setForeground(new Color(220, 20, 60));
		lblPrePromo2.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrePromo2.setBounds(532, 349, 193, 25);
		contentPane.add(lblPrePromo2);
		
		txtPrePromo2 = new JTextField();
		txtPrePromo2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPrePromo2(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPrePromo2(e);
			}
		});
		txtPrePromo2.setBorder(new LineBorder(new Color(147, 112, 219), 1, true));
		txtPrePromo2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtPrePromo2(e);
			}
		});
		txtPrePromo2.setVisible(false);
		txtPrePromo2.setText("0");
		txtPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo2.setForeground(Color.DARK_GRAY);
		txtPrePromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrePromo2.setColumns(10);
		txtPrePromo2.setBackground(new Color(245, 245, 245));
		txtPrePromo2.setBounds(737, 349, 150, 25);
		contentPane.add(txtPrePromo2);
		
		txtID = new JTextField();
		txtID.setVisible(false);
		txtID.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtID.setEditable(false);
		txtID.setText("0");
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setForeground(Color.DARK_GRAY);
		txtID.setFont(new Font("Arial", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBackground(new Color(245, 245, 245));
		txtID.setBounds(59, 16, 44, 25);
		contentPane.add(txtID);
		
		lblLote = new JLabel("Lote:");
		lblLote.setVisible(false);
		lblLote.setHorizontalAlignment(SwingConstants.LEFT);
		lblLote.setForeground(Color.DARK_GRAY);
		lblLote.setFont(new Font("Candara", Font.BOLD, 20));
		lblLote.setBounds(897, 349, 54, 25);
		contentPane.add(lblLote);
		
		txtLote = new JTextField();
		txtLote.setVisible(false);
		txtLote.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtLote(e);
			}
		});
		txtLote.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtLote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtLote(e);
			}
		});
		txtLote.setHorizontalAlignment(SwingConstants.LEFT);
		txtLote.setForeground(Color.DARK_GRAY);
		txtLote.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLote.setColumns(10);
		txtLote.setBackground(new Color(245, 245, 245));
		txtLote.setBounds(961, 349, 85, 25);
		contentPane.add(txtLote);
		
		lblLaboratorio = new JLabel("Laboratorio:");
		lblLaboratorio.setVisible(false);
		lblLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaboratorio.setForeground(Color.DARK_GRAY);
		lblLaboratorio.setFont(new Font("Candara", Font.BOLD, 20));
		lblLaboratorio.setBounds(531, 97, 194, 25);
		contentPane.add(lblLaboratorio);
		
		txtLaboratorio = new JTextField();
		txtLaboratorio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtLaboratorio(e);
			}
		});
		txtLaboratorio.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtLaboratorio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtLaboratorio(e);
			}
		});
		txtLaboratorio.setVisible(false);
		txtLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		txtLaboratorio.setForeground(Color.DARK_GRAY);
		txtLaboratorio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLaboratorio.setColumns(10);
		txtLaboratorio.setBackground(new Color(245, 245, 245));
		txtLaboratorio.setBounds(737, 97, 300, 25);
		contentPane.add(txtLaboratorio);
		
		lblAlmacn = new JLabel("Almac\u00E9n");
		lblAlmacn.setVisible(false);
		lblAlmacn.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlmacn.setForeground(Color.DARK_GRAY);
		lblAlmacn.setFont(new Font("Candara", Font.BOLD, 20));
		lblAlmacn.setBounds(9, 349, 191, 25);
		contentPane.add(lblAlmacn);
		
		btnCrearProducto = new JButton("CREAR");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnCrearProducto.setForeground(SystemColor.menu);
		btnCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCrearProducto.setBackground(new Color(30, 144, 255));
		btnCrearProducto.setBounds(797, 501, 240, 61);
		contentPane.add(btnCrearProducto);
		
		lblNota = new JLabel("<html>-  No utilice el signo par\u00E9ntesis \"( )\" en los datos del producto a registrarse. <br>- Los * son campos obligatorios.<br>- Si no desea a\u00F1adir promociones, deje los campos con \" 0 \"</html>");
		lblNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblNota.setForeground(new Color(220, 20, 60));
		lblNota.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNota.setBounds(532, 427, 505, 61);
		contentPane.add(lblNota);
		
		lblCantidadMnima = new JLabel("Stock m\u00EDnimo:");
		lblCantidadMnima.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadMnima.setForeground(Color.DARK_GRAY);
		lblCantidadMnima.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadMnima.setBounds(9, 463, 190, 25);
		contentPane.add(lblCantidadMnima);
		
		txtStockMinimo = new JTextField();
		txtStockMinimo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtStockMinimo(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtStockMinimo(e);
			}
		});
		txtStockMinimo.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtStockMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtStockMinimo(e);
			}
		});
		txtStockMinimo.setText("1");
		txtStockMinimo.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockMinimo.setForeground(Color.DARK_GRAY);
		txtStockMinimo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtStockMinimo.setColumns(10);
		txtStockMinimo.setBackground(new Color(245, 245, 245));
		txtStockMinimo.setBounds(210, 463, 150, 25);
		contentPane.add(txtStockMinimo);
		
		label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(191, 94, 20, 25);
		contentPane.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setVisible(false);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(191, 349, 20, 25);
		contentPane.add(label_2);
		
		label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(191, 239, 20, 25);
		contentPane.add(label_3);
		
		label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(191, 383, 20, 25);
		contentPane.add(label_4);
		
		label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(191, 419, 20, 25);
		contentPane.add(label_5);
		
		label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(191, 499, 20, 25);
		contentPane.add(label_6);
		
		label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(191, 533, 20, 25);
		contentPane.add(label_7);
		
		label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_8.setBounds(191, 275, 20, 25);
		contentPane.add(label_8);
		
		lblDeGanancia = new JLabel("% de ganancia:");
		lblDeGanancia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDeGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeGanancia.setForeground(new Color(30, 144, 255));
		lblDeGanancia.setFont(new Font("Arial", Font.BOLD, 16));
		lblDeGanancia.setBounds(370, 499, 139, 36);
		contentPane.add(lblDeGanancia);
		
		txtPtjGanancia = new JTextField();
		txtPtjGanancia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPtjGanancia(e);
			}
		});
		txtPtjGanancia.setText("0");
		txtPtjGanancia.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtPtjGanancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtPtjGanancia(arg0);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPtjGanancia(e);
			}
		});
		txtPtjGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		txtPtjGanancia.setForeground(Color.DARK_GRAY);
		txtPtjGanancia.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPtjGanancia.setColumns(10);
		txtPtjGanancia.setBackground(new Color(245, 245, 245));
		txtPtjGanancia.setBounds(380, 535, 121, 25);
		contentPane.add(txtPtjGanancia);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCancelar(arg0);
			}
		});
		btnCancelar.setForeground(SystemColor.menu);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancelar.setBackground(new Color(220, 20, 60));
		btnCancelar.setBounds(532, 501, 240, 61);
		contentPane.add(btnCancelar);
		
		cbCategoria = new JComboBox();
		cbCategoria.setForeground(Color.DARK_GRAY);
		cbCategoria.setEditable(true);
		cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		cbCategoria.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbCategoria.setBackground(new Color(245, 245, 245));
		cbCategoria.setBounds(208, 275, 301, 25);
		contentPane.add(cbCategoria);
		
		cbAlmacen = new JComboBox();
		cbAlmacen.setVisible(false);
		cbAlmacen.setForeground(Color.DARK_GRAY);
		cbAlmacen.setEditable(true);
		cbAlmacen.setFont(new Font("Arial", Font.PLAIN, 16));
		cbAlmacen.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbAlmacen.setBackground(new Color(245, 245, 245));
		cbAlmacen.setBounds(209, 349, 300, 25);
		contentPane.add(cbAlmacen);
		
		cbUnidadMedida = new JComboBox();
		cbUnidadMedida.setForeground(Color.DARK_GRAY);
		cbUnidadMedida.setEditable(true);
		cbUnidadMedida.setBackground(new Color(245, 245, 245));
		cbUnidadMedida.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbUnidadMedida.setFont(new Font("Arial", Font.PLAIN, 16));
		cbUnidadMedida.setBounds(210, 239, 299, 25);
		cbUnidadMedida.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedCbUnidadMedida(arg0);
			}
		});
		contentPane.add(cbUnidadMedida);
		
		txtCrearProducto = new JTextField();
		txtCrearProducto.setText("CREAR PRODUCTO");
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
		lblDistribuidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistribuidor.setForeground(Color.DARK_GRAY);
		lblDistribuidor.setFont(new Font("Candara", Font.BOLD, 20));
		lblDistribuidor.setBounds(9, 311, 191, 25);
		contentPane.add(lblDistribuidor);
		
		label_9 = new JLabel("*");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_9.setBounds(191, 311, 20, 25);
		contentPane.add(label_9);
		
		cbDistribuidor = new JComboBox();
		cbDistribuidor.setForeground(Color.DARK_GRAY);
		cbDistribuidor.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDistribuidor.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbDistribuidor.setBackground(new Color(245, 245, 245));
		cbDistribuidor.setBounds(209, 311, 240, 25);
		contentPane.add(cbDistribuidor);
		
		btnAnadirDistri = new JButton("+");
		btnAnadirDistri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnAnadirDistri(arg0);
			}
		});
		btnAnadirDistri.setForeground(Color.WHITE);
		btnAnadirDistri.setFont(new Font("Arial", Font.BOLD, 20));
		btnAnadirDistri.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnAnadirDistri.setBackground(new Color(30, 144, 255));
		btnAnadirDistri.setBounds(456, 311, 54, 25);
		contentPane.add(btnAnadirDistri);
		
		JLabel lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setVisible(false);
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setForeground(new Color(123, 104, 238));
		lblPrecio.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrecio.setBounds(852, 254, 190, 25);
		contentPane.add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setVisible(false);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setForeground(new Color(123, 104, 238));
		lblCantidad.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidad.setBounds(852, 232, 190, 25);
		contentPane.add(lblCantidad);
		
		JLabel lblPromocion = new JLabel("PROMOCION 3:");
		lblPromocion.setVisible(false);
		lblPromocion.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromocion.setForeground(new Color(123, 104, 238));
		lblPromocion.setFont(new Font("Candara", Font.BOLD, 20));
		lblPromocion.setBounds(852, 220, 190, 25);
		contentPane.add(lblPromocion);
		
		txtPrePromo3 = new JTextField();
		txtPrePromo3.setVisible(false);
		txtPrePromo3.setText("0");
		txtPrePromo3.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo3.setForeground(Color.DARK_GRAY);
		txtPrePromo3.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrePromo3.setColumns(10);
		txtPrePromo3.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPrePromo3.setBackground(new Color(245, 245, 245));
		txtPrePromo3.setBounds(894, 254, 149, 25);
		contentPane.add(txtPrePromo3);
		
		txtCantPromo3 = new JTextField();
		txtCantPromo3.setVisible(false);
		txtCantPromo3.setText("0");
		txtCantPromo3.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo3.setForeground(Color.DARK_GRAY);
		txtCantPromo3.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantPromo3.setColumns(10);
		txtCantPromo3.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtCantPromo3.setBackground(new Color(245, 245, 245));
		txtCantPromo3.setBounds(895, 232, 161, 25);
		contentPane.add(txtCantPromo3);
		
		txtNombrePromo3 = new JTextField();
		txtNombrePromo3.setVisible(false);
		txtNombrePromo3.setText("0");
		txtNombrePromo3.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombrePromo3.setForeground(Color.DARK_GRAY);
		txtNombrePromo3.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombrePromo3.setColumns(10);
		txtNombrePromo3.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtNombrePromo3.setBackground(new Color(245, 245, 245));
		txtNombrePromo3.setBounds(894, 220, 178, 25);
		contentPane.add(txtNombrePromo3);
		
		lblStockMximo = new JLabel("Stock almacén:");
		lblStockMximo.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockMximo.setForeground(new Color(46, 139, 87));
		lblStockMximo.setFont(new Font("Candara", Font.BOLD, 20));
		lblStockMximo.setBounds(9, 421, 190, 25);
		contentPane.add(lblStockMximo);
		
		label = new JLabel("*");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(191, 463, 20, 25);
		contentPane.add(label);
		
		txtStockMaximo = new JTextField();
		txtStockMaximo.setText("0");
		txtStockMaximo.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockMaximo.setForeground(new Color(46, 139, 87));
		txtStockMaximo.setFont(new Font("Arial", Font.BOLD, 16));
		txtStockMaximo.setColumns(10);
		txtStockMaximo.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtStockMaximo.setBackground(new Color(245, 245, 245));
		txtStockMaximo.setBounds(210, 421, 150, 25);
		contentPane.add(txtStockMaximo);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodbarras, txtNombreProducto, txtDescripcion, txtMarca, txtColor, cbUnidadMedida, cbCategoria, cbAlmacen, cbDistribuidor, btnAnadirDistri, txtStockInicial, txtStockMinimo, txtPrecioCompra, txtPtjGanancia, txtPrecioVenta, dateFechaVenc, dateFechaVenc.getCalendarButton(), txtLaboratorio, txtLote, txtNombrePromo1, txtCantPromo1, txtPrePromo1, txtNombrePromo2, txtCantPromo2, txtPrePromo2, btnCrearProducto, btnCancelar}));
		
		cargar();
		cargarBuscador();
	}
	
	private void cargar(){
		// CARGAR ID CORRESPONDIENTE
		try {
			consulta.iniciar();
			rs = consulta.cargarID();
			rs.next();
			int idSiguiente = rs.getInt("codproducto")+1;
			txtID.setText(""+idSiguiente);
		} catch (Exception e) {
			txtID.setText("1");
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
		
		// CARGAR ATRIBUTOS
		String atribTodos = "";
		try {
			consulta.iniciar();
			ResultSet rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
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

		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca")){
				lblMarca.setVisible(true);
				txtMarca.setVisible(true);
			}
			if(parts[x].equals("color")){
				lblColor.setVisible(true);
				txtColor.setVisible(true);
			}
			if(parts[x].equals("lote")){
				lblLote.setVisible(true);
				txtLote.setVisible(true);
			}
			if(parts[x].equals("laboratorio")){
				lblLaboratorio.setVisible(true);
				txtLaboratorio.setVisible(true);
			}	
			if(parts[x].equals("fvencimiento")){
				lblFechaVencimiento.setVisible(true);
				dateFechaVenc.setVisible(true);
			}
			if(parts[x].equals("promo1")){
				lblNombrePromo1.setVisible(true);
				lblCantidadPromo1.setVisible(true);
				lblPrePromo1.setVisible(true);
				txtNombrePromo1.setVisible(true);
				txtCantPromo1.setVisible(true);
				txtPrePromo1.setVisible(true);
			}
			if(parts[x].equals("promo2")){
				lblNombrePromo2.setVisible(true);
				lblCantidadPromo2.setVisible(true);
				lblPrePromo2.setVisible(true);
				txtNombrePromo2.setVisible(true);
				txtCantPromo2.setVisible(true);
				txtPrePromo2.setVisible(true);
			}
		}

		// COMBO UNIDADES DE MEDIDA
		UnidadMed unidades = new UnidadMed();
		unidades.cargarUnidadesMed(cbUnidadMedida);
		AutoCompleteDecorator.decorate(cbUnidadMedida);
		
		// COMBO CATEGORIA
		Categoria categoria = new Categoria();
		categoria.cargarCategorias(cbCategoria);
		AutoCompleteDecorator.decorate(cbCategoria);
		
		// COMBO ALMACEN
		Almacen almacen = new Almacen();
		almacen.cargarAlmacenes(cbAlmacen);
		AutoCompleteDecorator.decorate(cbAlmacen);
		
		// COMBO DISTRIBUIDOR
		Distribuidores distribuidor = new Distribuidores();
		distribuidor.cargarDistribuidores(cbDistribuidor);
		
		cbUnidadMedida.setSelectedItem("Unidad");
		primeravez = 0;
		

		if(nc!=null){
			lblCantidadActual.setVisible(false);
			txtStockInicial.setVisible(false);
			lblStockMximo.setVisible(false);
			txtStockMaximo.setVisible(false);
		}		
	}
	
	public void cargarBuscador() {
		
		acProd = new TextAutoCompleter(txtNombreProducto);
		acLab = new TextAutoCompleter(txtLaboratorio);

		acProd.setMode(0);
		acLab.setMode(0);
		
		
		consulta.iniciar();
		/*rs = consulta.cargarProductos();
		try {
			while (rs.next())
				acCB.addItem(rs.getString("codbarra"));
		}catch(Exception e){}*/
		
		rs = consulta.cargarProductosProd();
		try {
			while (rs.next())
				acProd.addItem(rs.getString("producto"));
		}catch(Exception e){}
		
		rs = consulta.cargarProductosDeta();
		try {
			while (rs.next())
				acDesc.addItem(rs.getString("detalles"));
		}catch(Exception e){}
		
		rs = consulta.cargarProductosMarca();
		try {
			while (rs.next())
				acMarc.addItem(rs.getString("marca"));
		}catch(Exception e){}
		
		rs = consulta.cargarProductosColor();
		try {
			while (rs.next())
				acCol.addItem(rs.getString("color"));
		}catch(Exception e){}
		
		rs = consulta.cargarProductosLab();
		try {
			while (rs.next())
				acLab.addItem(rs.getString("laboratorio"));
		}catch(Exception e){
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
	public void limpiar() {
		txtID.setText("" + (Integer.parseInt(txtID.getText())+1));
		txtCodbarras.setText(null);
		txtNombreProducto.setText(null);
		txtDescripcion.setText(null);
		txtMarca.setText(null);
		txtColor.setText(null);
		txtStockInicial.setText("0");
		txtStockMinimo.setText("1");
		txtPrecioCompra.setText("0");
		txtPrecioVenta.setText("0");
		txtPtjGanancia.setText("0");
		dateFechaVenc.setDate(null);
		txtLaboratorio.setText(null);
		txtLote.setText(null);
		txtNombrePromo1.setText("0");
		txtCantPromo1.setText("0");
		txtPrePromo1.setText("0");
		txtNombrePromo2.setText("0");
		txtCantPromo2.setText("0");
		txtPrePromo2.setText("0");
		cbDistribuidor.setSelectedIndex(0);
		
		recargarCombos();
		txtCodbarras.requestFocus();

		cbUnidadMedida.setSelectedItem("Unidad");
		cbCategoria.setSelectedItem(".General");
		cbAlmacen.setSelectedItem(".Principal");
		
		
	}
	
	public void recargarCombos(){
		try {
			cbUnidadMedida.removeAllItems();
			UnidadMed unidades = new UnidadMed();
			unidades.cargarUnidadesMed(cbUnidadMedida);
			AutoCompleteDecorator.decorate(cbUnidadMedida);
			
			cbCategoria.removeAllItems();
			Categoria categoria = new Categoria();
			categoria.cargarCategorias(cbCategoria);
			AutoCompleteDecorator.decorate(cbCategoria);
			
			cbAlmacen.removeAllItems();
			Almacen almacen = new Almacen();
			almacen.cargarAlmacenes(cbAlmacen);
			AutoCompleteDecorator.decorate(cbAlmacen);
			
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
	
	protected void keyReleasedTxtPrecioCompra(KeyEvent e) { // LOS SIGUIENTES METODOS SON PARA MODIFICAR EL PRECIO DE VENTA SEGUN EL PORCENTAJE Y LAS RESTRICCIONES EN LOS TEXTBOX
		try {
			if(txtPrecioCompra.getText().equals("0"))
				txtPtjGanancia.setText("");
			else{
				double preco = Float.parseFloat(txtPrecioCompra.getText());
				double preve = Float.parseFloat(txtPrecioVenta.getText());
				double ptjga = Float.parseFloat(txtPtjGanancia.getText());
				double newpreve = preco + (preco * (ptjga*0.01));
				newpreve = redondearDecimales(newpreve, 2);
				txtPrecioVenta.setText(""+newpreve);
			}
		} catch (Exception e2) {
			if(txtPrecioCompra.getText().length() == 0){
				txtPrecioCompra.setText("0");
				txtPtjGanancia.setText("0");				
			}
			else
				txtPrecioVenta.setText(txtPrecioCompra.getText());
		}	
	}
	protected void keyTypedTxtPrecioCompra(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtPrecioCompra.getText().length() == 8)
			e.consume();
		if (c == '.' && txtPrecioCompra.getText().contains("."))
			e.consume();
	}
	protected void keyReleasedTxtPtjGanancia(KeyEvent e) {
		try {
			if(txtPrecioCompra.getText().length() == 0 && txtPtjGanancia.getText().length() == 0)
				txtPrecioVenta.setText("0");
			else{
				double preco = Float.parseFloat(txtPrecioCompra.getText());
				double preve = Float.parseFloat(txtPrecioVenta.getText());
				double ptjga = Float.parseFloat(txtPtjGanancia.getText());
				double newpreve = preco + (preco * (ptjga*0.01));
				newpreve = redondearDecimales(newpreve, 2);
				txtPrecioVenta.setText(""+newpreve);
			}
		} catch (Exception e2) {
			txtPrecioVenta.setText(txtPrecioCompra.getText());
		}		
	}
	protected void keyTypedTxtPtjGanancia(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtPtjGanancia.getText().length() == 8)
			e.consume();
		if (c == '.' && txtPtjGanancia.getText().contains("."))
			e.consume();
	}
	protected void keyReleasedTxtPrecioVenta(KeyEvent e) {
		try {
			if((txtPrecioCompra.getText().equals("0")) || txtPrecioCompra.getText().length() == 0)
				txtPtjGanancia.setText("");
			else{
				double preco = Float.parseFloat(txtPrecioCompra.getText());
				double preve = Float.parseFloat(txtPrecioVenta.getText());
				double newptj = ((preve-preco)/preco) * 100;
				newptj = redondearDecimales(newptj, 2);
				txtPtjGanancia.setText(""+newptj);
			}
		} catch (Exception e2) {
			txtPtjGanancia.setText("0");
		}		
	}
	protected void keyTypedTxtPrecioVenta(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtPrecioVenta.getText().length() == 8)
			e.consume();
		if (c == '.' && txtPrecioVenta.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtStockInicial(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtStockInicial.getText().length() == 8)
			e.consume();
		if (c == '.' && txtStockInicial.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtStockMinimo(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtStockMinimo.getText().length() == 8)
			e.consume();
		if (c == '.' && txtStockMinimo.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtCodbarras(KeyEvent e) {
		/*char c = e.getKeyChar();
		if (txtCodbarras.getText().length() == 100 || c=='(' || c==')')
			e.consume();*/
	}
	protected void keyTypedTxtNombreProducto(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtNombreProducto.getText().length() == 200 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtDescripcion(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtDescripcion.getText().length() == 200 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtMarca(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtMarca.getText().length() == 30 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtColor(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtColor.getText().length() == 30 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtLaboratorio(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtLaboratorio.getText().length() == 50 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtLote(KeyEvent e) {
		char c = e.getKeyChar();
		if (txtLote.getText().length() == 50 || c=='(' || c==')')
			e.consume();
	}
	protected void keyTypedTxtNombrePromo1(KeyEvent e) {
		if (txtNombrePromo1.getText().length() == 20)
			e.consume();
	}
	protected void keyTypedTxtCantPromo1(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtCantPromo1.getText().length() == 8)
			e.consume();
		if (c == '.' && txtCantPromo1.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtPrePromo1(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtPrePromo1.getText().length() == 8)
			e.consume();
		if (c == '.' && txtPrePromo1.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtNombrePromo2(KeyEvent e) {
		if (txtNombrePromo2.getText().length() == 20)
			e.consume();
	}
	protected void keyTypedTxtCantPromo2(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtCantPromo2.getText().length() == 8)
			e.consume();
		if (c == '.' && txtCantPromo2.getText().contains("."))
			e.consume();
	}
	protected void keyTypedTxtPrePromo2(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER) && (c != '.')) 
			e.consume();		
		if (txtPrePromo2.getText().length() == 8)
			e.consume();
		if (c == '.' && txtPrePromo2.getText().contains("."))
			e.consume();
	}
	protected void windowClosingThis(WindowEvent arg0) {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //CERRAR VENTANA
		this.dispose();
	}
	
	protected void actionPerformedBtnCrearProducto(ActionEvent arg0) {
		int rs = 0;
		try {
			if (txtID.getText().length() == 0 || txtNombreProducto.getText().length() == 0 || cbUnidadMedida.getSelectedItem().toString().length() == 0 ||  cbCategoria.getSelectedItem().toString().length() == 0 
					|| cbAlmacen.getSelectedItem().toString().length() == 0 || cbDistribuidor.getItemCount()==0 || cbUnidadMedida.getSelectedItem().toString().length() > 30 ||  cbCategoria.getSelectedItem().toString().length() > 30 || cbAlmacen.getSelectedItem().toString().length() > 50 || txtStockInicial.getText().length() == 0 || txtStockMinimo.getText().length() == 0
					|| txtPrecioCompra.getText().length() == 0 || txtPrecioVenta.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente.\nNOTA: Los campos UMedida, categoria y almacen, no pueden ser mayores a 30 caracteres");
			
			} else {			
				int id = 0;				id = Integer.parseInt(txtID.getText());
				String codbarra = "";	codbarra = txtCodbarras.getText();
				
				
				try {
					
					consultas consulta2 = new consultas();
					
					consulta2.iniciar();
					ResultSet rs2 = consulta2.buscarProductoBarras(codbarra);
					rs2.next();
					String cb = rs2.getString("codbarra");
					JOptionPane.showMessageDialog(null, "Ya existe producto con este c�digo de barras");
					
					
					
				} catch (Exception e) {
					
					String nombreprod = ""; nombreprod = txtNombreProducto.getText();
					String descripcion = "";descripcion = txtDescripcion.getText();
					String umedida = ""; 	umedida = cbUnidadMedida.getSelectedItem().toString();
					String categoria = ""; 	categoria = cbCategoria.getSelectedItem().toString();
						if(categoria.equals("General")) categoria = ".General";
					String almacen = ""; 	almacen = cbAlmacen.getSelectedItem().toString();
						if(almacen.equals("Principal")) almacen = ".Principal";
					int iddistrib = 0;		iddistrib = cbDistribuidor.getItemAt(cbDistribuidor.getSelectedIndex()).getIddist();
					String marca = ""; 		marca = txtMarca.getText();
					String color = ""; 		color = txtColor.getText();
					double stockini = 0; 	if(txtStockInicial.getText().length()>0) stockini = Float.parseFloat(txtStockInicial.getText());
					double stockmin = 0; 	if(txtStockMinimo.getText().length()>0) stockmin = Float.parseFloat(txtStockMinimo.getText());
					double stockmax = 0; 	if(txtStockMaximo.getText().length()>0) stockmax = Float.parseFloat(txtStockMaximo.getText());
					double precoNew = 0; 	if(txtPrecioCompra.getText().length()>0) precoNew = Float.parseFloat(txtPrecioCompra.getText());
					double ptjgana = 0; 	if(txtPtjGanancia.getText().length()>0) ptjgana = Float.parseFloat(txtPtjGanancia.getText());
					double preveNew = 0; 	if(txtPrecioVenta.getText().length()>0) preveNew = Float.parseFloat(txtPrecioVenta.getText());
					
					java.sql.Date fechaVencimiento = null;
					try { // Cambio de utils a sql.Date para envio
						Date datevencimiento = dateFechaVenc.getDate();
						long d = datevencimiento.getTime();
						fechaVencimiento = new java.sql.Date(d);
					} catch (Exception eq) {
					}
					
					String laboratiorio = ""; 	laboratiorio = txtLaboratorio.getText();
					String lote = ""; 	lote = txtLote.getText();
					
					String nombrePromo1 = txtNombrePromo1.getText();
					double cantPromo1 = Float.parseFloat(txtCantPromo1.getText());
					double prePromo1 = Float.parseFloat(txtPrePromo1.getText());
					
					String nombrePromo2 = txtNombrePromo2.getText();
					double cantPromo2 = Float.parseFloat(txtCantPromo2.getText());
					double prePromo2 = Float.parseFloat(txtPrePromo2.getText());
					
					String nombrePromo3 = txtNombrePromo3.getText();
					double cantPromo3 = Float.parseFloat(txtCantPromo3.getText());
					double prePromo3 = Float.parseFloat(txtPrePromo3.getText());
					
					try {
						stockini = redondearDecimales(stockini, 2);
						stockmin = redondearDecimales(stockmin, 2);
						stockmax = redondearDecimales(stockmax, 2);
						precoNew = redondearDecimales(precoNew, 2);
						cantPromo1 = redondearDecimales(cantPromo1, 2);
						prePromo1 = redondearDecimales(prePromo1, 2);
						cantPromo2 = redondearDecimales(cantPromo2, 2);
						prePromo2 = redondearDecimales(prePromo2, 2);
						cantPromo3 = redondearDecimales(cantPromo3, 2);
						prePromo3 = redondearDecimales(prePromo3, 2);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error al redondear: " + ex);
					}
					
					java.util.Date date = new Date(); // FECHA ACTUAL
					Object fechaActual = new java.sql.Timestamp(date.getTime());

					consulta.iniciar();
					rs = consulta.ingresarProducto(codbarra, nombreprod, descripcion, umedida, categoria, almacen, iddistrib,
							marca, color, stockini, stockmin, precoNew, ptjgana, preveNew, fechaVencimiento, laboratiorio,
							lote, nombrePromo1, cantPromo1, prePromo1, nombrePromo2, cantPromo2, prePromo2, nombrePromo3, cantPromo3, prePromo3, primeravez, stockmax);
					
					if (rs == 0) {
						consulta.registrarIngreso(id, stockini, 0, 0, precoNew, preveNew, usuario, fechaActual);
						
						
						if (nc != null) {
							String prod = nombreprod + " " +  descripcion + " " + marca + " " + color + " * " +  umedida + " - " + almacen + " - (" + id + ")"; 
							nc.cargarProducto(prod);
							this.dispose();
						}
						else{
							String nomUsuario = mantenimientoProductos.vp.lblUsuario.getText(); // USUARIO
							mantenimientoProductos.cargar();
							mantenimientoProductos.cargarBuscador();
							mantenimientoProductos.selecionarProducto(""+id);
							limpiar();
						}
					} else
							JOptionPane.showMessageDialog(null, "Ya existe producto con este ID");
				}
			}
		} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al registrar producto: " + e);
		}finally {
			try {
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}
	protected void itemStateChangedCbUnidadMedida(ItemEvent arg0) {
		try {
			if(cbUnidadMedida.getSelectedItem().toString().equals("Servicio")){
				txtStockInicial.setText("99999999");
				txtStockInicial.setEditable(false);
			}
			else{
				txtStockInicial.setText("0");
				txtStockInicial.setEditable(true);
			}
		} catch (Exception e) {
		}
	}
	
	protected void actionPerformedBtnAnadirDistri(ActionEvent arg0) {
		NuevoDistribuidor nd = new NuevoDistribuidor(null, this, null, null);
		try {
			if (nd.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				nd.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				nd.setVisible(true); 
			} else {
				nd = new NuevoDistribuidor(null, this, null, null);
				nd.setLocationRelativeTo(null);
				nd.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	private void seleccionarTexto(FocusEvent e){
		Object o = e.getSource();
        if(o instanceof javax.swing.JTextField){
            javax.swing.JTextField txt = (javax.swing.JTextField) o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
	}
	protected void focusGainedTxtStockInicial(FocusEvent e) {
		seleccionarTexto(e);
		if(txtStockInicial.getText().equals("0"))
			txtStockInicial.setText("");
	}
	protected void focusLostTxtStockInicial(FocusEvent e) {
		if(txtStockInicial.getText().equals(""))
			txtStockInicial.setText("0");
	}
	
	protected void focusGainedTxtStockMinimo(FocusEvent e) {
		seleccionarTexto(e);
		if(txtStockMinimo.getText().equals("0"))
			txtStockMinimo.setText("");
	}
	protected void focusLostTxtStockMinimo(FocusEvent e) {
		if(txtStockMinimo.getText().equals(""))
			txtStockMinimo.setText("0");
	}
	
	protected void focusGainedTxtPrecioCompra(FocusEvent e) {
		seleccionarTexto(e);
		if(txtPrecioCompra.getText().equals("0"))
			txtPrecioCompra.setText("");
	}
	protected void focusGainedTxtPtjGanancia(FocusEvent e) {
		seleccionarTexto(e);
		if(txtPtjGanancia.getText().equals("0"))
			txtPtjGanancia.setText("");
	}
	protected void focusGainedTxtPrecioVenta(FocusEvent e) {
		seleccionarTexto(e);
		if(txtPrecioVenta.getText().equals("0"))
			txtPrecioVenta.setText("");
	}
	protected void focusGainedTxtCodbarras(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtNombreProducto(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtDescripcion(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtMarca(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtColor(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtLaboratorio(FocusEvent e) {
		//seleccionarTexto(e);
	}
	protected void focusGainedTxtLote(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtNombrePromo1(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtCantPromo1(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtPrePromo1(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtNombrePromo2(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtCantPromo2(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtPrePromo2(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusLostTxtNombrePromo1(FocusEvent e) {
		if(txtNombrePromo1.getText().length()==0)
			txtNombrePromo1.setText("0");
	}
	protected void focusLostTxtCantPromo1(FocusEvent e) {
		if(txtCantPromo1.getText().length()==0)
			txtCantPromo1.setText("0");
	}
	protected void focusLostTxtPrePromo1(FocusEvent e) {
		if(txtPrePromo1.getText().length()==0)
			txtPrePromo1.setText("0");
	}
	protected void focusLostTxtNombrePromo2(FocusEvent e) {
		if(txtNombrePromo2.getText().length()==0)
			txtNombrePromo2.setText("0");
	}
	protected void focusLostTxtCantPromo2(FocusEvent e) {
		if(txtCantPromo2.getText().length()==0)
			txtCantPromo2.setText("0");
	}
	protected void focusLostTxtPrePromo2(FocusEvent e) {
		if(txtPrePromo2.getText().length()==0)
			txtPrePromo2.setText("0");
	}
}
