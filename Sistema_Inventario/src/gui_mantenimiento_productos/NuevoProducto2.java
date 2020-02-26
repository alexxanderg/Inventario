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

	ResultSet rs;
	consultas model = new consultas();
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
	
	public String nomUsuario = null;
	
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
	public NuevoProducto2(String nomUsuario) {
		this.nomUsuario = nomUsuario;
		
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCdigoIntbarras = new JLabel("Cod. Interno/Barras:");
		lblCdigoIntbarras.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigoIntbarras.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigoIntbarras.setForeground(Color.BLACK);
		lblCdigoIntbarras.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCdigoIntbarras.setBounds(11, 54, 190, 23);
		contentPane.add(lblCdigoIntbarras);
		
		txtCodbarras = new JTextField();
		txtCodbarras.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodbarras.setForeground(SystemColor.windowBorder);
		txtCodbarras.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCodbarras.setColumns(10);
		txtCodbarras.setBackground(SystemColor.controlHighlight);
		txtCodbarras.setBounds(211, 54, 300, 25);
		contentPane.add(txtCodbarras);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombre.setBounds(11, 88, 190, 25);
		contentPane.add(lblNombre);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreProducto.setForeground(SystemColor.windowBorder);
		txtNombreProducto.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreProducto.setColumns(10);
		txtNombreProducto.setBackground(SystemColor.controlHighlight);
		txtNombreProducto.setBounds(211, 88, 300, 25);
		contentPane.add(txtNombreProducto);
		
		lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcin.setForeground(Color.BLACK);
		lblDescripcin.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDescripcin.setBounds(11, 124, 190, 25);
		contentPane.add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescripcion.setForeground(SystemColor.windowBorder);
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBackground(SystemColor.controlHighlight);
		txtDescripcion.setBounds(211, 124, 300, 25);
		contentPane.add(txtDescripcion);
		
		lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategora.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora.setForeground(Color.BLACK);
		lblCategora.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCategora.setBounds(12, 196, 190, 25);
		contentPane.add(lblCategora);
		
		txtCategoria = new JTextField();
		txtCategoria.setText("General");
		txtCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		txtCategoria.setForeground(SystemColor.windowBorder);
		txtCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCategoria.setColumns(10);
		txtCategoria.setBackground(SystemColor.controlHighlight);
		txtCategoria.setBounds(212, 196, 300, 25);
		contentPane.add(txtCategoria);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setVisible(false);
		lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		lblMarca.setForeground(Color.BLACK);
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMarca.setBounds(11, 268, 191, 25);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setVisible(false);
		txtMarca.setHorizontalAlignment(SwingConstants.LEFT);
		txtMarca.setForeground(SystemColor.windowBorder);
		txtMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMarca.setColumns(10);
		txtMarca.setBackground(SystemColor.controlHighlight);
		txtMarca.setBounds(212, 268, 300, 25);
		contentPane.add(txtMarca);
		
		lblColor = new JLabel("Color:");
		lblColor.setVisible(false);
		lblColor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		lblColor.setForeground(Color.BLACK);
		lblColor.setFont(new Font("Arial", Font.PLAIN, 18));
		lblColor.setBounds(11, 304, 191, 25);
		contentPane.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setVisible(false);
		txtColor.setHorizontalAlignment(SwingConstants.LEFT);
		txtColor.setForeground(SystemColor.windowBorder);
		txtColor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtColor.setColumns(10);
		txtColor.setBackground(SystemColor.controlHighlight);
		txtColor.setBounds(212, 304, 300, 25);
		contentPane.add(txtColor);
		
		lblFechaVencimiento = new JLabel("Fecha vencimiento:");
		lblFechaVencimiento.setVisible(false);
		lblFechaVencimiento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaVencimiento.setForeground(Color.BLACK);
		lblFechaVencimiento.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFechaVencimiento.setBounds(533, 18, 193, 25);
		contentPane.add(lblFechaVencimiento);
		
		dateFechaVenc = new JDateChooser();
		dateFechaVenc.setVisible(false);
		dateFechaVenc.setBounds(738, 18, 300, 25);
		contentPane.add(dateFechaVenc);
		
		lblUniMedida = new JLabel("Uni. Medida");
		lblUniMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUniMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniMedida.setForeground(Color.BLACK);
		lblUniMedida.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUniMedida.setBounds(12, 160, 190, 25);
		contentPane.add(lblUniMedida);
		
		cbUnidadMedida = new JComboBox();
		cbUnidadMedida.setModel(new DefaultComboBoxModel(new String[] {"Caja", "Galon", "Gramo", "Hora", "Kilo", "Litro", "Metro", "Pies", "Pulgadas", "Servicio", "Unidad", "Yardas"}));
		cbUnidadMedida.setSelectedIndex(10);
		cbUnidadMedida.setFont(new Font("Arial", Font.PLAIN, 16));
		cbUnidadMedida.setBounds(213, 160, 299, 25);
		contentPane.add(cbUnidadMedida);
		
		lblCantidadActual = new JLabel("Stock inicial:");
		lblCantidadActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadActual.setForeground(Color.BLACK);
		lblCantidadActual.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadActual.setBounds(11, 340, 190, 25);
		contentPane.add(lblCantidadActual);
		
		txtStockInicial = new JTextField();
		txtStockInicial.setText("0");
		txtStockInicial.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockInicial.setForeground(SystemColor.windowBorder);
		txtStockInicial.setFont(new Font("Arial", Font.PLAIN, 16));
		txtStockInicial.setColumns(10);
		txtStockInicial.setBackground(SystemColor.controlHighlight);
		txtStockInicial.setBounds(212, 342, 150, 25);
		contentPane.add(txtStockInicial);
		
		lblPrecioDeCompra = new JLabel("Precio de Compra:");
		lblPrecioDeCompra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeCompra.setForeground(Color.BLACK);
		lblPrecioDeCompra.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrecioDeCompra.setBounds(12, 412, 190, 25);
		contentPane.add(lblPrecioDeCompra);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setText("0");
		txtPrecioCompra.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioCompra.setForeground(SystemColor.windowBorder);
		txtPrecioCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecioCompra.setColumns(10);
		txtPrecioCompra.setBackground(SystemColor.controlHighlight);
		txtPrecioCompra.setBounds(213, 414, 149, 25);
		contentPane.add(txtPrecioCompra);
		
		lblPrecioDeVenta = new JLabel("Precio de Venta:");
		lblPrecioDeVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioDeVenta.setForeground(Color.BLACK);
		lblPrecioDeVenta.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrecioDeVenta.setBounds(12, 450, 190, 25);
		contentPane.add(lblPrecioDeVenta);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setText("0");
		txtPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenta.setForeground(SystemColor.windowBorder);
		txtPrecioVenta.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBackground(SystemColor.controlHighlight);
		txtPrecioVenta.setBounds(213, 450, 149, 25);
		contentPane.add(txtPrecioVenta);
		
		lblNombrePromo1 = new JLabel("NOMBRE PROMO 1:");
		lblNombrePromo1.setVisible(false);
		lblNombrePromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo1.setForeground(new Color(50, 205, 50));
		lblNombrePromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombrePromo1.setBounds(533, 124, 193, 25);
		contentPane.add(lblNombrePromo1);
		
		txtNombrePromo1 = new JTextField();
		txtNombrePromo1.setVisible(false);
		txtNombrePromo1.setText("0");
		txtNombrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombrePromo1.setForeground(SystemColor.windowBorder);
		txtNombrePromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombrePromo1.setColumns(10);
		txtNombrePromo1.setBackground(SystemColor.controlHighlight);
		txtNombrePromo1.setBounds(738, 124, 300, 25);
		contentPane.add(txtNombrePromo1);
		
		lblCantidadPromo1 = new JLabel("CANTIDAD PROMO 1:");
		lblCantidadPromo1.setVisible(false);
		lblCantidadPromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo1.setForeground(new Color(50, 205, 50));
		lblCantidadPromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadPromo1.setBounds(533, 160, 193, 25);
		contentPane.add(lblCantidadPromo1);
		
		txtCantPromo1 = new JTextField();
		txtCantPromo1.setVisible(false);
		txtCantPromo1.setText("0");
		txtCantPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo1.setForeground(SystemColor.windowBorder);
		txtCantPromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantPromo1.setColumns(10);
		txtCantPromo1.setBackground(SystemColor.controlHighlight);
		txtCantPromo1.setBounds(738, 160, 300, 25);
		contentPane.add(txtCantPromo1);
		
		lblPrePromo1 = new JLabel("PRECIO PROMO1 :");
		lblPrePromo1.setVisible(false);
		lblPrePromo1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo1.setForeground(new Color(50, 205, 50));
		lblPrePromo1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrePromo1.setBounds(533, 196, 193, 25);
		contentPane.add(lblPrePromo1);
		
		txtPrePromo1 = new JTextField();
		txtPrePromo1.setVisible(false);
		txtPrePromo1.setText("0");
		txtPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo1.setForeground(SystemColor.windowBorder);
		txtPrePromo1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrePromo1.setColumns(10);
		txtPrePromo1.setBackground(SystemColor.controlHighlight);
		txtPrePromo1.setBounds(738, 196, 300, 25);
		contentPane.add(txtPrePromo1);
		
		lblNombrePromo2 = new JLabel("NOMBRE PROMO 2:");
		lblNombrePromo2.setVisible(false);
		lblNombrePromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePromo2.setForeground(new Color(30, 144, 255));
		lblNombrePromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombrePromo2.setBounds(533, 232, 193, 25);
		contentPane.add(lblNombrePromo2);
		
		txtNombrePromo2 = new JTextField();
		txtNombrePromo2.setVisible(false);
		txtNombrePromo2.setText("0");
		txtNombrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombrePromo2.setForeground(SystemColor.windowBorder);
		txtNombrePromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombrePromo2.setColumns(10);
		txtNombrePromo2.setBackground(SystemColor.controlHighlight);
		txtNombrePromo2.setBounds(738, 232, 300, 25);
		contentPane.add(txtNombrePromo2);
		
		lblCantidadPromo2 = new JLabel("CANTIDAD PROMO 2:");
		lblCantidadPromo2.setVisible(false);
		lblCantidadPromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo2.setForeground(new Color(30, 144, 255));
		lblCantidadPromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadPromo2.setBounds(533, 268, 193, 25);
		contentPane.add(lblCantidadPromo2);
		
		txtCantPromo2 = new JTextField();
		txtCantPromo2.setVisible(false);
		txtCantPromo2.setText("0");
		txtCantPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo2.setForeground(SystemColor.windowBorder);
		txtCantPromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantPromo2.setColumns(10);
		txtCantPromo2.setBackground(SystemColor.controlHighlight);
		txtCantPromo2.setBounds(738, 268, 300, 25);
		contentPane.add(txtCantPromo2);
		
		lblPrePromo2 = new JLabel("PRECIO PROMO 2:");
		lblPrePromo2.setVisible(false);
		lblPrePromo2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrePromo2.setForeground(new Color(30, 144, 255));
		lblPrePromo2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrePromo2.setBounds(533, 304, 193, 25);
		contentPane.add(lblPrePromo2);
		
		txtPrePromo2 = new JTextField();
		txtPrePromo2.setVisible(false);
		txtPrePromo2.setText("0");
		txtPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo2.setForeground(SystemColor.windowBorder);
		txtPrePromo2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrePromo2.setColumns(10);
		txtPrePromo2.setBackground(SystemColor.controlHighlight);
		txtPrePromo2.setBounds(738, 304, 300, 25);
		contentPane.add(txtPrePromo2);
		
		lblId = new JLabel("ID:");
		lblId.setVerticalAlignment(SwingConstants.BOTTOM);
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblId.setBounds(11, 18, 190, 23);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setText("0");
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setForeground(SystemColor.windowBorder);
		txtID.setFont(new Font("Arial", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBackground(SystemColor.controlHighlight);
		txtID.setBounds(211, 18, 300, 25);
		contentPane.add(txtID);
		
		lblLote = new JLabel("Lote:");
		lblLote.setVisible(false);
		lblLote.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLote.setHorizontalAlignment(SwingConstants.LEFT);
		lblLote.setForeground(Color.BLACK);
		lblLote.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLote.setBounds(532, 92, 194, 25);
		contentPane.add(lblLote);
		
		txtLote = new JTextField();
		txtLote.setVisible(false);
		txtLote.setHorizontalAlignment(SwingConstants.LEFT);
		txtLote.setForeground(SystemColor.windowBorder);
		txtLote.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLote.setColumns(10);
		txtLote.setBackground(SystemColor.controlHighlight);
		txtLote.setBounds(738, 89, 300, 25);
		contentPane.add(txtLote);
		
		lblLaboratorio = new JLabel("Laboratorio:");
		lblLaboratorio.setVisible(false);
		lblLaboratorio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaboratorio.setForeground(Color.BLACK);
		lblLaboratorio.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLaboratorio.setBounds(532, 54, 194, 25);
		contentPane.add(lblLaboratorio);
		
		txtLaboratorio = new JTextField();
		txtLaboratorio.setVisible(false);
		txtLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		txtLaboratorio.setForeground(SystemColor.windowBorder);
		txtLaboratorio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLaboratorio.setColumns(10);
		txtLaboratorio.setBackground(SystemColor.controlHighlight);
		txtLaboratorio.setBounds(738, 54, 300, 25);
		contentPane.add(txtLaboratorio);
		
		lblAlmacn = new JLabel("Almac\u00E9n");
		lblAlmacn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAlmacn.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlmacn.setForeground(Color.BLACK);
		lblAlmacn.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAlmacn.setBounds(11, 232, 191, 25);
		contentPane.add(lblAlmacn);
		
		txtAlmacen = new JTextField();
		txtAlmacen.setText("Principal");
		txtAlmacen.setHorizontalAlignment(SwingConstants.LEFT);
		txtAlmacen.setForeground(SystemColor.windowBorder);
		txtAlmacen.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAlmacen.setColumns(10);
		txtAlmacen.setBackground(SystemColor.controlHighlight);
		txtAlmacen.setBounds(212, 232, 300, 25);
		contentPane.add(txtAlmacen);
		
		btnCrearProducto = new JButton("CREAR PRODUCTO");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCrearProducto(arg0);
			}
		});
		btnCrearProducto.setForeground(SystemColor.menu);
		btnCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnCrearProducto.setBackground(new Color(30, 144, 255));
		btnCrearProducto.setBounds(533, 414, 505, 61);
		contentPane.add(btnCrearProducto);
		
		lblNota = new JLabel("<html>- Los * son campos obligatorios.<br>- Si no desea a\u00F1adir promociones, deje los campos con \" 0 \"</html>");
		lblNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblNota.setForeground(new Color(220, 20, 60));
		lblNota.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNota.setBounds(533, 367, 505, 36);
		contentPane.add(lblNota);
		
		lblCantidadMnima = new JLabel("Stock m\u00EDnimo:");
		lblCantidadMnima.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadMnima.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadMnima.setForeground(Color.BLACK);
		lblCantidadMnima.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCantidadMnima.setBounds(11, 376, 190, 25);
		contentPane.add(lblCantidadMnima);
		
		txtStockMinimo = new JTextField();
		txtStockMinimo.setText("1");
		txtStockMinimo.setHorizontalAlignment(SwingConstants.LEFT);
		txtStockMinimo.setForeground(SystemColor.windowBorder);
		txtStockMinimo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtStockMinimo.setColumns(10);
		txtStockMinimo.setBackground(SystemColor.controlHighlight);
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
		lblDeGanancia.setForeground(new Color(186, 85, 211));
		lblDeGanancia.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDeGanancia.setBounds(372, 412, 139, 25);
		contentPane.add(lblDeGanancia);
		
		txtPtjGanancia = new JTextField();
		txtPtjGanancia.setText("0");
		txtPtjGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		txtPtjGanancia.setForeground(new Color(186, 85, 211));
		txtPtjGanancia.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPtjGanancia.setColumns(10);
		txtPtjGanancia.setBackground(SystemColor.controlHighlight);
		txtPtjGanancia.setBounds(382, 450, 117, 25);
		contentPane.add(txtPtjGanancia);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtID, txtCodbarras, txtNombreProducto, txtDescripcion, cbUnidadMedida, txtCategoria, txtAlmacen, txtMarca, txtColor, txtStockInicial, txtStockMinimo, txtPrecioCompra, txtPtjGanancia, txtPrecioVenta, dateFechaVenc, dateFechaVenc.getCalendarButton(), txtLaboratorio, txtLote, txtNombrePromo1, txtCantPromo1, txtPrePromo1, txtNombrePromo2, txtCantPromo2, txtPrePromo2, btnCrearProducto}));
		
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
		
	}
	
	protected void windowClosingThis(WindowEvent arg0) {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.dispose();
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
				double precoNew = 0; 		if(txtPrecioCompra.getText().length()>0) precoNew = Float.parseFloat(txtPrecioCompra.getText());
				double ptjgana = 0; 	if(txtPtjGanancia.getText().length()>0) ptjgana = Float.parseFloat(txtPtjGanancia.getText());
				double preveNew = 0; 		if(txtPrecioVenta.getText().length()>0) preveNew = Float.parseFloat(txtPrecioVenta.getText());
				
				Object fechaVencimineto = null;
				try {
					java.util.Date d = new java.util.Date();
					java.util.Date date = new Date();
					fechaVencimineto = new java.sql.Timestamp(date.getTime());					
				} catch (Exception e) {	JOptionPane.showMessageDialog(null, "Error al obtener fecha: " + e); }
				
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
				Object date2 = new java.sql.Timestamp(date.getTime());
				
				rs = model.ingresarProducto(codbarra, nombreprod, descripcion, umedida, categoria, almacen,
						marca, color, stockini, stockmin, precoNew, ptjgana, preveNew, fechaVencimineto, laboratiorio,
						lote, nombrePromo1, cantPromo1, prePromo1, nombrePromo2, cantPromo2, prePromo2);

				if (rs == 0) {
					model.registrarFechaIngreso(id, stockini, 0, 0, precoNew, preveNew, nomUsuario, date2);
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
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}
}
