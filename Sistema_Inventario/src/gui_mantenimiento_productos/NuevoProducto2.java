package gui_mantenimiento_productos;

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
import com.toedter.calendar.JDateChooser;

import clases.Almacen;
import clases.Categoria;
import mysql.consultas;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NuevoProducto2 extends JFrame {

	private JPanel contentPane;
	private JLabel lblCdigoIntbarras;
	private JTextField txtCodbarras;
	private JLabel lblNombre;
	private JTextField txtNombreProducto;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JLabel lblCategora;
	private JTextField txtCategoria;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JLabel lblColor;
	private JTextField txtColor;
	private JLabel lblFechaVencimiento;
	private JDateChooser dateFechaVenc;
	private JLabel lblUniMedida;
	private JComboBox cbUnidadMedida;
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
	private JLabel lblId;
	private JTextField txtID;
	private JLabel lblLote;
	private JTextField txtLote;
	private JLabel lblLaboratorio;
	private JTextField txtLaboratorio;
	private JLabel lblAlmacn;
	private JTextField txtAlmacen;
	private JButton btnCrearProducto;
	private JLabel lblNota;
	private JLabel lblCantidadMnima;
	private JTextField txtStockMinimo;
	private JLabel label;
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

	ResultSet rs;
	consultas model = new consultas();
	InternalMantenimiento mantenimientoProductos;
	private JComboBox <Almacen> cbAlmacen;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoProducto2 frame = new NuevoProducto2(null);
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
	public NuevoProducto2(InternalMantenimiento mantenimientoProductos) {
		this.mantenimientoProductos = mantenimientoProductos;
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowClosingThis(arg0);
			}
		});
		setTitle("NUEVO PRODUCTO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 522);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCdigoIntbarras = new JLabel("Cod. Interno/Barras:");
		lblCdigoIntbarras.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.DARK_GRAY);
		lblCdigoIntbarras.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCdigoIntbarras.setBounds(11, 54, 190, 23);
		contentPane.add(lblCdigoIntbarras);
		
		txtCodbarras = new JTextField();
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
		txtCodbarras.setBounds(211, 54, 300, 25);
		contentPane.add(txtCodbarras);
		
		lblNombre = new JLabel("Nombre Producto:");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.DARK_GRAY);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombre.setBounds(11, 88, 190, 25);
		contentPane.add(lblNombre);
		
		txtNombreProducto = new JTextField();
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
		txtNombreProducto.setBounds(211, 88, 300, 25);
		contentPane.add(txtNombreProducto);
		
		lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.DARK_GRAY);
		lblDescripcin.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDescripcin.setBounds(11, 124, 190, 25);
		contentPane.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
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
		txtDescripcion.setBounds(211, 124, 300, 25);
		contentPane.add(txtDescripcion);
		
		lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategora.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora.setForeground(Color.DARK_GRAY);
		lblCategora.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCategora.setBounds(12, 196, 190, 25);
		contentPane.add(lblCategora);
		
		txtCategoria = new JTextField();
		txtCategoria.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCategoria(e);
			}
		});
		txtCategoria.setText("General");
		txtCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		txtCategoria.setForeground(Color.DARK_GRAY);
		txtCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCategoria.setColumns(10);
		txtCategoria.setBackground(new Color(245, 245, 245));
		txtCategoria.setBounds(453, 196, 59, 25);
		contentPane.add(txtCategoria);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setVisible(false);
		lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setForeground(Color.DARK_GRAY);
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMarca.setBounds(11, 268, 191, 25);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
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
		txtMarca.setBounds(212, 268, 300, 25);
		contentPane.add(txtMarca);
		
		lblColor = new JLabel("Color:");
		lblColor.setVisible(false);
		lblColor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setForeground(Color.DARK_GRAY);
		lblColor.setFont(new Font("Arial", Font.PLAIN, 18));
		lblColor.setBounds(11, 304, 191, 25);
		contentPane.add(lblColor);
		
		txtColor = new JTextField();
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
		txtColor.setBounds(212, 304, 300, 25);
		contentPane.add(txtColor);
		
		lblFechaVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaVencimiento.setVisible(false);
		lblFechaVencimiento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.DARK_GRAY);
		lblFechaVencimiento.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFechaVencimiento.setBounds(533, 18, 193, 25);
		contentPane.add(lblFechaVencimiento);
		
		dateFechaVenc = new JDateChooser();
		dateFechaVenc.setFont(new Font("Arial", Font.PLAIN, 16));
		dateFechaVenc.setForeground(Color.DARK_GRAY);
		dateFechaVenc.setVisible(false);
		dateFechaVenc.setBounds(738, 18, 300, 25);
		contentPane.add(dateFechaVenc);
		
		lblCantidadActual = new JLabel("Stock inicial:");
		lblCantidadActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadActual.setForeground(Color.DARK_GRAY);
		lblCantidadActual.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadActual.setBounds(11, 340, 190, 25);
		contentPane.add(lblCantidadActual);
		
		txtStockInicial = new JTextField();
		txtStockInicial.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtStockInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtStockInicial(e);
			}
		});
		txtStockInicial.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockInicial.setForeground(Color.DARK_GRAY);
		txtStockInicial.setFont(new Font("Arial", Font.PLAIN, 16));
		txtStockInicial.setColumns(10);
		txtStockInicial.setBackground(new Color(245, 245, 245));
		txtStockInicial.setBounds(212, 342, 150, 25);
		contentPane.add(txtStockInicial);
		
		lblUniMedida = new JLabel("Uni. Medida");
		lblUniMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUniMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniMedida.setForeground(Color.DARK_GRAY);
		lblUniMedida.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUniMedida.setBounds(12, 160, 190, 25);
		contentPane.add(lblUniMedida);
		
		cbUnidadMedida = new JComboBox();
		cbUnidadMedida.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedCbUnidadMedida(arg0);
			}
		});
		cbUnidadMedida.setBackground(new Color(245, 245, 245));
		cbUnidadMedida.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbUnidadMedida.setModel(new DefaultComboBoxModel(new String[] {"Caja", "Galon", "Gramo", "Hora", "Kilo", "Litro", "Metro", "Pies", "Pulgadas", "Servicio", "Unidad", "Yardas"}));
		cbUnidadMedida.setSelectedIndex(10);
		cbUnidadMedida.setFont(new Font("Arial", Font.PLAIN, 16));
		cbUnidadMedida.setBounds(213, 160, 238, 25);
		contentPane.add(cbUnidadMedida);
		
		lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeCompra.setForeground(Color.DARK_GRAY);
		lblPrecioDeCompra.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrecioDeCompra.setBounds(12, 412, 190, 25);
		contentPane.add(lblPrecioDeCompra);
		
		txtPrecioCompra = new JTextField();
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
		txtPrecioCompra.setBounds(213, 414, 149, 25);
		contentPane.add(txtPrecioCompra);
		
		lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeVenta.setForeground(Color.DARK_GRAY);
		lblPrecioDeVenta.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrecioDeVenta.setBounds(12, 450, 190, 25);
		contentPane.add(lblPrecioDeVenta);
		
		txtPrecioVenta = new JTextField();
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
		txtPrecioVenta.setBounds(213, 450, 149, 25);
		contentPane.add(txtPrecioVenta);
		
		lblNombrePromo1 = new JLabel("NOMBRE PROMO 1:");
		lblNombrePromo1.setVisible(false);
		lblNombrePromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo1.setForeground(new Color(32, 178, 170));
		lblNombrePromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombrePromo1.setBounds(533, 124, 193, 25);
		contentPane.add(lblNombrePromo1);
		
		txtNombrePromo1 = new JTextField();
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
		txtNombrePromo1.setBounds(738, 124, 300, 25);
		contentPane.add(txtNombrePromo1);
		
		lblCantidadPromo1 = new JLabel("CANTIDAD PROMO 1:");
		lblCantidadPromo1.setVisible(false);
		lblCantidadPromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo1.setForeground(new Color(32, 178, 170));
		lblCantidadPromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadPromo1.setBounds(533, 160, 193, 25);
		contentPane.add(lblCantidadPromo1);
		
		txtCantPromo1 = new JTextField();
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
		txtCantPromo1.setBounds(738, 160, 300, 25);
		contentPane.add(txtCantPromo1);
		
		lblPrePromo1 = new JLabel("PRECIO PROMO 1:");
		lblPrePromo1.setVisible(false);
		lblPrePromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo1.setForeground(new Color(32, 178, 170));
		lblPrePromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrePromo1.setBounds(533, 196, 193, 25);
		contentPane.add(lblPrePromo1);
		
		txtPrePromo1 = new JTextField();
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
		txtPrePromo1.setBounds(738, 196, 300, 25);
		contentPane.add(txtPrePromo1);
		
		lblNombrePromo2 = new JLabel("NOMBRE PROMO 2:");
		lblNombrePromo2.setVisible(false);
		lblNombrePromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo2.setForeground(new Color(147, 112, 219));
		lblNombrePromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombrePromo2.setBounds(533, 232, 193, 25);
		contentPane.add(lblNombrePromo2);
		
		txtNombrePromo2 = new JTextField();
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
		txtNombrePromo2.setBounds(738, 232, 300, 25);
		contentPane.add(txtNombrePromo2);
		
		lblCantidadPromo2 = new JLabel("CANTIDAD PROMO 2:");
		lblCantidadPromo2.setVisible(false);
		lblCantidadPromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo2.setForeground(new Color(147, 112, 219));
		lblCantidadPromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadPromo2.setBounds(533, 268, 193, 25);
		contentPane.add(lblCantidadPromo2);
		
		txtCantPromo2 = new JTextField();
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
		txtCantPromo2.setBounds(738, 268, 300, 25);
		contentPane.add(txtCantPromo2);
		
		lblPrePromo2 = new JLabel("PRECIO PROMO 2:");
		lblPrePromo2.setVisible(false);
		lblPrePromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo2.setForeground(new Color(147, 112, 219));
		lblPrePromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrePromo2.setBounds(533, 304, 193, 25);
		contentPane.add(lblPrePromo2);
		
		txtPrePromo2 = new JTextField();
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
		txtPrePromo2.setBounds(738, 304, 300, 25);
		contentPane.add(txtPrePromo2);
		
		lblId = new JLabel("ID:");
		lblId.setVerticalAlignment(SwingConstants.BOTTOM);
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(Color.DARK_GRAY);
		lblId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblId.setBounds(11, 18, 190, 23);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtID.setEditable(false);
		txtID.setText("0");
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setForeground(Color.DARK_GRAY);
		txtID.setFont(new Font("Arial", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBackground(new Color(245, 245, 245));
		txtID.setBounds(211, 18, 300, 25);
		contentPane.add(txtID);
		
		lblLote = new JLabel("Lote:");
		lblLote.setVisible(false);
		lblLote.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLote.setHorizontalAlignment(SwingConstants.LEFT);
		lblLote.setForeground(Color.DARK_GRAY);
		lblLote.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLote.setBounds(532, 92, 194, 25);
		contentPane.add(lblLote);
		
		txtLote = new JTextField();
		txtLote.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtLote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtLote(e);
			}
		});
		txtLote.setVisible(false);
		txtLote.setHorizontalAlignment(SwingConstants.LEFT);
		txtLote.setForeground(Color.DARK_GRAY);
		txtLote.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLote.setColumns(10);
		txtLote.setBackground(new Color(245, 245, 245));
		txtLote.setBounds(738, 89, 300, 25);
		contentPane.add(txtLote);
		
		lblLaboratorio = new JLabel("Laboratorio:");
		lblLaboratorio.setVisible(false);
		lblLaboratorio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaboratorio.setForeground(Color.DARK_GRAY);
		lblLaboratorio.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLaboratorio.setBounds(532, 54, 194, 25);
		contentPane.add(lblLaboratorio);
		
		txtLaboratorio = new JTextField();
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
		txtLaboratorio.setBounds(738, 54, 300, 25);
		contentPane.add(txtLaboratorio);
		
		lblAlmacn = new JLabel("Almac\u00E9n");
		lblAlmacn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAlmacn.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlmacn.setForeground(Color.DARK_GRAY);
		lblAlmacn.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAlmacn.setBounds(11, 232, 191, 25);
		contentPane.add(lblAlmacn);
		
		txtAlmacen = new JTextField();
		txtAlmacen.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtAlmacen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtAlmacen(e);
			}
		});
		txtAlmacen.setText("Principal");
		txtAlmacen.setHorizontalAlignment(SwingConstants.LEFT);
		txtAlmacen.setForeground(Color.DARK_GRAY);
		txtAlmacen.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAlmacen.setColumns(10);
		txtAlmacen.setBackground(new Color(245, 245, 245));
		txtAlmacen.setBounds(453, 232, 59, 25);
		contentPane.add(txtAlmacen);
		
		btnCrearProducto = new JButton("Crear");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnCrearProducto.setForeground(SystemColor.menu);
		btnCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCrearProducto.setBackground(new Color(30, 144, 255));
		btnCrearProducto.setBounds(798, 414, 240, 61);
		contentPane.add(btnCrearProducto);
		
		lblNota = new JLabel("<html>- Los * son campos obligatorios.<br>- Si no desea a\u00F1adir promociones, deje los campos con \" 0 \"</html>");
		lblNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblNota.setForeground(new Color(220, 20, 60));
		lblNota.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNota.setBounds(533, 367, 505, 36);
		contentPane.add(lblNota);
		
		lblCantidadMnima = new JLabel("Stock m\u00EDnimo:");
		lblCantidadMnima.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadMnima.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadMnima.setForeground(Color.DARK_GRAY);
		lblCantidadMnima.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadMnima.setBounds(11, 376, 190, 25);
		contentPane.add(lblCantidadMnima);
		
		txtStockMinimo = new JTextField();
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
		txtStockMinimo.setBounds(212, 378, 150, 25);
		contentPane.add(txtStockMinimo);
		
		label = new JLabel("*");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(193, 18, 20, 25);
		contentPane.add(label);
		
		label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(193, 88, 20, 25);
		contentPane.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(193, 196, 20, 25);
		contentPane.add(label_2);
		
		label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(193, 232, 20, 25);
		contentPane.add(label_3);
		
		label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(193, 340, 20, 25);
		contentPane.add(label_4);
		
		label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(193, 376, 20, 25);
		contentPane.add(label_5);
		
		label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(193, 414, 20, 25);
		contentPane.add(label_6);
		
		label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(193, 448, 20, 25);
		contentPane.add(label_7);
		
		label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_8.setBounds(193, 160, 20, 25);
		contentPane.add(label_8);
		
		lblDeGanancia = new JLabel("% de ganancia:");
		lblDeGanancia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDeGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeGanancia.setForeground(new Color(30, 144, 255));
		lblDeGanancia.setFont(new Font("Arial", Font.BOLD, 18));
		lblDeGanancia.setBounds(372, 412, 139, 25);
		contentPane.add(lblDeGanancia);
		
		txtPtjGanancia = new JTextField();
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
		txtPtjGanancia.setBounds(382, 450, 121, 25);
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
		btnCancelar.setBounds(533, 414, 240, 61);
		contentPane.add(btnCancelar);
		
		cbCategoria = new JComboBox();
		cbCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		cbCategoria.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbCategoria.setBackground(new Color(245, 245, 245));
		cbCategoria.setBounds(211, 196, 240, 25);
		contentPane.add(cbCategoria);
		
		cbAlmacen = new JComboBox();
		cbAlmacen.setFont(new Font("Arial", Font.PLAIN, 16));
		cbAlmacen.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbAlmacen.setBackground(new Color(245, 245, 245));
		cbAlmacen.setBounds(211, 232, 240, 25);
		contentPane.add(cbAlmacen);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodbarras, txtNombreProducto, txtDescripcion, cbUnidadMedida, txtCategoria, txtAlmacen, txtMarca, txtColor, txtStockInicial, txtStockMinimo, txtPrecioCompra, txtPtjGanancia, txtPrecioVenta, dateFechaVenc, dateFechaVenc.getCalendarButton(), txtLaboratorio, txtLote, txtNombrePromo1, txtCantPromo1, txtPrePromo1, txtNombrePromo2, txtCantPromo2, txtPrePromo2, btnCrearProducto}));
		
		cargar();
		
	}
	
	private void cargar(){
		// CARGAR ID CORRESPONDIENTE
		try {
			ResultSet rs = model.cargarID();
			rs.next();
			int idSiguiente = rs.getInt("codproducto")+1;
			txtID.setText(""+idSiguiente);
		} catch (Exception e) {
			txtID.setText("1");
		}
		
		// CARGAR ATRIBUTOS
		String atribTodos = "";
		try {
			ResultSet rs = model.cargarAtributosProd();
			rs.next();
			atribTodos = rs.getString("atributosprod");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
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
		
		// COMBO CATEGORIA
		Categoria categoria = new Categoria();
		Categoria todasCategorias = new Categoria("General");
		cbCategoria.addItem(todasCategorias);
		categoria.cargarCategorias(cbCategoria);
		
		// COMBO ALMACEN
		Almacen almacen = new Almacen();
		Almacen todosAlmacenes = new Almacen("Principal");
		cbAlmacen.addItem(todosAlmacenes);
		almacen.cargarAlmacenes(cbAlmacen);
		
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
				txtPrecioCompra.setText("");
				txtPtjGanancia.setText("");				
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
				txtPrecioVenta.setText("");
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
			txtPtjGanancia.setText("");
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
		if (txtCodbarras.getText().length() == 100)
			e.consume();
	}
	protected void keyTypedTxtNombreProducto(KeyEvent e) {
		if (txtNombreProducto.getText().length() == 200)
			e.consume();
	}
	protected void keyTypedTxtDescripcion(KeyEvent e) {
		if (txtDescripcion.getText().length() == 200)
			e.consume();
	}
	protected void keyTypedTxtCategoria(KeyEvent e) {
		if (txtCategoria.getText().length() == 30)
			e.consume();
	}
	protected void keyTypedTxtAlmacen(KeyEvent e) {
		if (txtAlmacen.getText().length() == 50)
			e.consume();
	}
	protected void keyTypedTxtMarca(KeyEvent e) {
		if (txtMarca.getText().length() == 30)
			e.consume();
	}
	protected void keyTypedTxtColor(KeyEvent e) {
		if (txtColor.getText().length() == 30)
			e.consume();
	}
	protected void keyTypedTxtLaboratorio(KeyEvent e) {
		if (txtLaboratorio.getText().length() == 50)
			e.consume();
	}
	protected void keyTypedTxtLote(KeyEvent e) {
		if (txtLote.getText().length() == 50)
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
		txtID.setText("" + (Integer.parseInt(txtID.getText())+1));;
		txtCodbarras.setText(null);
		txtNombreProducto.setText(null);
		txtDescripcion.setText(null);
		cbUnidadMedida.setSelectedIndex(10);
		txtCategoria.setText(null);
		txtAlmacen.setText(null);
		txtMarca.setText(null);
		txtColor.setText(null);
		txtStockInicial.setText(null);
		txtStockMinimo.setText("1");
		txtPrecioCompra.setText(null);
		txtPrecioVenta.setText(null);
		txtPtjGanancia.setText(null);
		dateFechaVenc.setDate(null);
		txtLaboratorio.setText(null);
		txtLote.setText(null);
		txtNombrePromo1.setText("0");
		txtCantPromo1.setText("0");
		txtPrePromo1.setText("0");
		txtNombrePromo2.setText("0");
		txtCantPromo2.setText("0");
		txtPrePromo2.setText("0");
		
		txtCodbarras.requestFocus();
	}
	
	protected void actionPerformedBtnCrearProducto(ActionEvent arg0) {
		int rs = 0;
		try {
			if (txtID.getText().length() == 0 || txtNombreProducto.getText().length() == 0 || txtCategoria.getText().length() == 0 
					|| txtAlmacen.getText().length() == 0 || txtStockInicial.getText().length() == 0 || txtStockMinimo.getText().length() == 0
					|| txtPrecioCompra.getText().length() == 0 || txtPrecioVenta.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
			} else {
				
				int id = 0;				id = Integer.parseInt(txtID.getText());
				String codbarra = "";	codbarra = txtCodbarras.getText();
				String nombreprod = ""; nombreprod = txtNombreProducto.getText();
				String descripcion = "";descripcion = txtDescripcion.getText();
				String umedida = ""; 	umedida = cbUnidadMedida.getSelectedItem().toString();
				String categoria = ""; 	categoria = txtCategoria.getText();
				String almacen = ""; 	almacen = txtAlmacen.getText();
				String marca = ""; 		marca = txtMarca.getText();
				String color = ""; 		color = txtColor.getText();
				double stockini = 0; 	if(txtStockInicial.getText().length()>0) stockini = Float.parseFloat(txtStockInicial.getText());
				double stockmin = 0; 	if(txtStockMinimo.getText().length()>0) stockmin = Float.parseFloat(txtStockMinimo.getText());
				double precoNew = 0; 	if(txtPrecioCompra.getText().length()>0) precoNew = Float.parseFloat(txtPrecioCompra.getText());
				double ptjgana = 0; 	if(txtPtjGanancia.getText().length()>0) ptjgana = Float.parseFloat(txtPtjGanancia.getText());
				double preveNew = 0; 	if(txtPrecioVenta.getText().length()>0) preveNew = Float.parseFloat(txtPrecioVenta.getText());
				
				java.sql.Date fechaVencimiento = null;
				try { // Cambio de utils a sql.Date para envio
					Date datevencimiento = dateFechaVenc.getDate();
					long d = datevencimiento.getTime();
					fechaVencimiento = new java.sql.Date(d);
				} catch (Exception e) {
				}		
				
				String laboratiorio = ""; 	laboratiorio = txtLaboratorio.getText();
				String lote = ""; 	lote = txtLote.getText();
				
				String nombrePromo1 = txtPrePromo1.getText();
				double cantPromo1 = Float.parseFloat(txtCantPromo1.getText());
				double prePromo1 = Float.parseFloat(txtPrePromo1.getText());
				String nombrePromo2 = txtPrePromo2.getText();
				double cantPromo2 = Float.parseFloat(txtCantPromo2.getText());
				double prePromo2 = Float.parseFloat(txtPrePromo2.getText());
				
				try {
						
					stockini = redondearDecimales(stockini, 2);
					stockmin = redondearDecimales(stockmin, 2);
					precoNew = redondearDecimales(precoNew, 2);
					cantPromo1 = redondearDecimales(cantPromo1, 2);
					prePromo1 = redondearDecimales(prePromo1, 2);
					cantPromo2 = redondearDecimales(cantPromo2, 2);
					prePromo2 = redondearDecimales(prePromo2, 2);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al redondear: " + e);
				}
				
				java.util.Date date = new Date(); // FECHA ACTUAL
				Object fechaActual = new java.sql.Timestamp(date.getTime());
				
				String nomUsuario = mantenimientoProductos.vp.lblUsuario.getText(); // USUARIO
				
				rs = model.ingresarProducto(codbarra, nombreprod, descripcion, umedida, categoria, almacen,
						marca, color, stockini, stockmin, precoNew, ptjgana, preveNew, fechaVencimiento, laboratiorio,
						lote, nombrePromo1, cantPromo1, prePromo1, nombrePromo2, cantPromo2, prePromo2);

				if (rs == 0) {
					model.registrarFechaIngreso(id, stockini, 0, 0, precoNew, preveNew, nomUsuario, fechaActual);
					mantenimientoProductos.cargar();
					mantenimientoProductos.selecionarProducto(""+id);
					limpiar();
					
					/*if (inv != null) {
						inv.cargarDatos();
						inv.selecionarProducto(txtCodigo.getText());
						cargar();
						this.setAlwaysOnTop(true);
						inv.ajustarAnchoColumnas();
						limpiar();
						txtCodigo.requestFocus();
					}
					if (v != null) {
						v.setVisible(true);
						v.setEnabled(true);
						this.dispose();
						v.dtm.addRow(new Object[] { 1, txtProducto.getText(), txtDeta.getText(), txtCantidad.getText(),
								txtPrecioVenInd.getText(), txtPrecioVenInd.getText(), txtCodigo.getText(),
								txtPreComInd.getText() });
						v.seleccionarRow();
						// v.sumarSubTotales();
						v.sumarTotal();
					}*/
				} else
					JOptionPane.showMessageDialog(null, "Ya existe producto con este ID");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al registrar produto: " + e);
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}
	protected void itemStateChangedCbUnidadMedida(ItemEvent arg0) {
		if(cbUnidadMedida.getSelectedIndex() == 9){
			txtStockInicial.setText("99999999");
			txtStockInicial.setEditable(false);
		}
		else{
			txtStockInicial.setText("");
			txtStockInicial.setEditable(true);
		}
	}
}
