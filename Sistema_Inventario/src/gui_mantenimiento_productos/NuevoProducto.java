package gui_mantenimiento_productos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_principal.Login;
import gui_mantenimiento_productos.MantenimientoProductos;
import gui_ventas.Ventas;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class NuevoProducto extends JDialog implements ActionListener, KeyListener, WindowListener {
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JLabel lblCantidad;
	private JLabel lblPrecio;
	private JTextField txtPreComInd;
	private JLabel lblCantdad;
	private JTextField txtCantidad;
	private JLabel lblPrecioVenta;
	private JTextField txtPrecioVenInd;
	private JButton btnCrear;
	private JLabel lblUMedida;
	private JComboBox cbUMedida;
	private TextAutoCompleter ac;
	private TextAutoCompleter ac1;

	MantenimientoProductos inv;
	consultas model = new consultas();
	Ventas v;
	String usuario;
	private JTextField txtNuevoProducto;
	private JTextField txtDeta;
	private JLabel lblPromocion;
	private JTextField txtPromo1;
	private JLabel lblCantidadPromo;
	private JTextField txtCantPromo1;
	private JLabel lblPrecioPromo;
	private JTextField txtPrePromo1;
	private JLabel lblPromocion_1;
	private JTextField txtPromo2;
	private JLabel lblCantidadPromo_1;
	private JTextField txtCantPromo2;
	private JLabel lblPrecioPromo_1;
	private JTextField txtPrePromo2;
	private JLabel lblnotaSiNo;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JLabel lblColor;
	private JTextField txtColor;
	private JLabel label;
	private JTextField txtLaboratorio;
	private JLabel label_1;
	private JDateChooser fecVencimiento;
	private JLabel label_2;
	private JTextField txtNroLote;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel lblCategoria;
	private JTextField txtCategoria;

	public static void main(String[] args) {
		try {
			NuevoProducto dialog = new NuevoProducto(null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NuevoProducto(MantenimientoProductos temp2, Ventas temp3, String temp4) {
		setTitle("Nuevo producto");
		inv = temp2;
		v = temp3;
		usuario = temp4;
		setAlwaysOnTop(true);
		setBounds(100, 100, 526, 877);
		getContentPane().setLayout(null);
		setResizable(false);
		addWindowListener(this);

		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		txtCantidad.addKeyListener(this);
		txtCantidad.setForeground(SystemColor.windowBorder);
		txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(252, 427, 257, 25);
		getContentPane().add(txtCantidad);

		cbUMedida = new JComboBox();
		cbUMedida.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbUMedida.setModel(new DefaultComboBoxModel(new String[] { "Unidad", "Kilo", "Litro", "Gramo" }));
		cbUMedida.setBounds(252, 389, 257, 25);
		getContentPane().add(cbUMedida);
		cbUMedida.setSelectedIndex(0);

		txtPreComInd = new JTextField();
		txtPreComInd.setText("0");
		txtPreComInd.addKeyListener(this);
		txtPreComInd.setForeground(SystemColor.windowBorder);
		txtPreComInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreComInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPreComInd.setColumns(10);
		txtPreComInd.setBackground(SystemColor.controlHighlight);
		txtPreComInd.setBounds(252, 465, 257, 25);
		getContentPane().add(txtPreComInd);

		txtPrecioVenInd = new JTextField();
		txtPrecioVenInd.setText("0");
		txtPrecioVenInd.addKeyListener(this);
		txtPrecioVenInd.setForeground(SystemColor.windowBorder);
		txtPrecioVenInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrecioVenInd.setColumns(10);
		txtPrecioVenInd.setBackground(SystemColor.controlHighlight);
		txtPrecioVenInd.setBounds(252, 501, 257, 25);
		getContentPane().add(txtPrecioVenInd);
		lblCdigo = new JLabel("C\u00D3DIGO:");
		lblCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setForeground(SystemColor.desktop);
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigo.setBounds(10, 69, 138, 23);
		getContentPane().add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setForeground(SystemColor.windowBorder);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(SystemColor.controlHighlight);
		txtCodigo.setBounds(158, 69, 352, 25);
		getContentPane().add(txtCodigo);

		lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducto.setForeground(SystemColor.desktop);
		lblProducto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblProducto.setBounds(10, 103, 178, 25);
		getContentPane().add(lblProducto);

		txtProducto = new JTextField();
		txtProducto.addKeyListener(this);
		txtProducto.setForeground(SystemColor.windowBorder);
		txtProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtProducto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtProducto.setColumns(10);
		txtProducto.setBackground(SystemColor.controlHighlight);
		txtProducto.setBounds(158, 103, 352, 25);
		getContentPane().add(txtProducto);

		lblCantidad = new JLabel("DETALLES:");
		lblCantidad.setVerticalAlignment(SwingConstants.TOP);
		lblCantidad.setForeground(SystemColor.desktop);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidad.setBounds(10, 139, 178, 25);
		getContentPane().add(lblCantidad);

		lblPrecio = new JLabel("PRE. COMPRA INDV:");
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(9, 463, 235, 25);
		getContentPane().add(lblPrecio);

		lblCantdad = new JLabel("CANTIDAD:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(9, 425, 159, 25);
		getContentPane().add(lblCantdad);

		lblPrecioVenta = new JLabel("PRE. VENTA INDV:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(9, 501, 211, 25);
		getContentPane().add(lblPrecioVenta);

		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setForeground(SystemColor.menu);
		btnCrear.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(-1, 792, 520, 55);
		getContentPane().add(btnCrear);

		lblUMedida = new JLabel("U. MEDIDA:");
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(9, 389, 171, 25);
		getContentPane().add(lblUMedida);

		txtNuevoProducto = new JTextField();
		txtNuevoProducto.setText("NUEVO PRODUCTO");
		txtNuevoProducto.setRequestFocusEnabled(false);
		txtNuevoProducto.setIgnoreRepaint(true);
		txtNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNuevoProducto.setForeground(Color.WHITE);
		txtNuevoProducto.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtNuevoProducto.setFocusable(false);
		txtNuevoProducto.setFocusTraversalKeysEnabled(false);
		txtNuevoProducto.setEditable(false);
		txtNuevoProducto.setColumns(10);
		txtNuevoProducto.setBackground(Color.DARK_GRAY);
		txtNuevoProducto.setBounds(0, 0, 520, 58);
		getContentPane().add(txtNuevoProducto);

		txtDeta = new JTextField();
		txtDeta.setHorizontalAlignment(SwingConstants.LEFT);
		txtDeta.setForeground(SystemColor.windowBorder);
		txtDeta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtDeta.setColumns(10);
		txtDeta.setBackground(SystemColor.controlHighlight);
		txtDeta.setBounds(158, 139, 352, 25);
		getContentPane().add(txtDeta);

		lblPromocion = new JLabel("PROMOCION 1:");
		lblPromocion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPromocion.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromocion.setForeground(new Color(50, 205, 50));
		lblPromocion.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPromocion.setBounds(9, 537, 211, 25);
		getContentPane().add(lblPromocion);

		txtPromo1 = new JTextField();
		txtPromo1.setText("0");
		txtPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPromo1.setForeground(SystemColor.windowBorder);
		txtPromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPromo1.setColumns(10);
		txtPromo1.setBackground(SystemColor.controlHighlight);
		txtPromo1.setBounds(252, 537, 257, 25);
		getContentPane().add(txtPromo1);

		lblCantidadPromo = new JLabel("CANTIDAD PROMO 1:");
		lblCantidadPromo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo.setForeground(new Color(50, 205, 50));
		lblCantidadPromo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidadPromo.setBounds(9, 573, 235, 25);
		getContentPane().add(lblCantidadPromo);

		txtCantPromo1 = new JTextField();
		txtCantPromo1.setText("0");
		txtCantPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo1.setForeground(SystemColor.windowBorder);
		txtCantPromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantPromo1.setColumns(10);
		txtCantPromo1.setBackground(SystemColor.controlHighlight);
		txtCantPromo1.setBounds(252, 573, 257, 25);
		getContentPane().add(txtCantPromo1);

		lblPrecioPromo = new JLabel("PRECIO PROMO1 :");
		lblPrecioPromo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioPromo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioPromo.setForeground(new Color(50, 205, 50));
		lblPrecioPromo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioPromo.setBounds(9, 609, 211, 25);
		getContentPane().add(lblPrecioPromo);

		txtPrePromo1 = new JTextField();
		txtPrePromo1.setText("0");
		txtPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo1.setForeground(SystemColor.windowBorder);
		txtPrePromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrePromo1.setColumns(10);
		txtPrePromo1.setBackground(SystemColor.controlHighlight);
		txtPrePromo1.setBounds(252, 609, 257, 25);
		getContentPane().add(txtPrePromo1);

		lblPromocion_1 = new JLabel("PROMOCION 2:");
		lblPromocion_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPromocion_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPromocion_1.setForeground(new Color(30, 144, 255));
		lblPromocion_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPromocion_1.setBounds(9, 645, 211, 25);
		getContentPane().add(lblPromocion_1);

		txtPromo2 = new JTextField();
		txtPromo2.setText("0");
		txtPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPromo2.setForeground(SystemColor.windowBorder);
		txtPromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPromo2.setColumns(10);
		txtPromo2.setBackground(SystemColor.controlHighlight);
		txtPromo2.setBounds(252, 645, 257, 25);
		getContentPane().add(txtPromo2);

		lblCantidadPromo_1 = new JLabel("CANTIDAD PROMO 2:");
		lblCantidadPromo_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadPromo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadPromo_1.setForeground(new Color(30, 144, 255));
		lblCantidadPromo_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidadPromo_1.setBounds(9, 681, 235, 25);
		getContentPane().add(lblCantidadPromo_1);

		txtCantPromo2 = new JTextField();
		txtCantPromo2.setText("0");
		txtCantPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo2.setForeground(SystemColor.windowBorder);
		txtCantPromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantPromo2.setColumns(10);
		txtCantPromo2.setBackground(SystemColor.controlHighlight);
		txtCantPromo2.setBounds(252, 681, 257, 25);
		getContentPane().add(txtCantPromo2);

		lblPrecioPromo_1 = new JLabel("PRECIO PROMO 2:");
		lblPrecioPromo_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioPromo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioPromo_1.setForeground(new Color(30, 144, 255));
		lblPrecioPromo_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioPromo_1.setBounds(9, 717, 211, 25);
		getContentPane().add(lblPrecioPromo_1);

		txtPrePromo2 = new JTextField();
		txtPrePromo2.setText("0");
		txtPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo2.setForeground(SystemColor.windowBorder);
		txtPrePromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrePromo2.setColumns(10);
		txtPrePromo2.setBackground(SystemColor.controlHighlight);
		txtPrePromo2.setBounds(252, 717, 257, 25);
		getContentPane().add(txtPrePromo2);

		lblnotaSiNo = new JLabel("<html>NOTA: Si no desea a\u00F1adir promociones, deje los campos con \" 0 \"</html>");
		lblnotaSiNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblnotaSiNo.setForeground(Color.RED);
		lblnotaSiNo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblnotaSiNo.setBounds(9, 748, 490, 33);
		getContentPane().add(lblnotaSiNo);

		this.lblMarca = new JLabel("MARCA:");
		this.lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblMarca.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblMarca.setForeground(Color.BLACK);
		this.lblMarca.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblMarca.setBounds(9, 210, 211, 25);
		getContentPane().add(this.lblMarca);

		this.txtMarca = new JTextField();
		this.txtMarca.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtMarca.setForeground(SystemColor.windowBorder);
		this.txtMarca.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtMarca.setColumns(10);
		this.txtMarca.setBackground(SystemColor.controlHighlight);
		this.txtMarca.setBounds(252, 210, 257, 25);
		getContentPane().add(this.txtMarca);

		this.lblColor = new JLabel("COLOR:");
		this.lblColor.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblColor.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblColor.setForeground(Color.BLACK);
		this.lblColor.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblColor.setBounds(9, 246, 211, 25);
		getContentPane().add(this.lblColor);

		this.txtColor = new JTextField();
		this.txtColor.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtColor.setForeground(SystemColor.windowBorder);
		this.txtColor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtColor.setColumns(10);
		this.txtColor.setBackground(SystemColor.controlHighlight);
		this.txtColor.setBounds(252, 246, 257, 25);
		getContentPane().add(this.txtColor);

		this.label = new JLabel("LABORATORIO:");
		this.label.setVerticalAlignment(SwingConstants.BOTTOM);
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		this.label.setForeground(Color.BLACK);
		this.label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.label.setBounds(9, 281, 215, 25);
		getContentPane().add(this.label);

		this.txtLaboratorio = new JTextField();
		this.txtLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtLaboratorio.setForeground(SystemColor.windowBorder);
		this.txtLaboratorio.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtLaboratorio.setColumns(10);
		this.txtLaboratorio.setBackground(SystemColor.controlHighlight);
		this.txtLaboratorio.setBounds(254, 281, 253, 25);
		getContentPane().add(this.txtLaboratorio);

		this.label_1 = new JLabel("F. VENCIMIENTO:");
		this.label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		this.label_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_1.setForeground(Color.BLACK);
		this.label_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.label_1.setBounds(9, 317, 215, 25);
		getContentPane().add(this.label_1);

		this.fecVencimiento = new JDateChooser();
		this.fecVencimiento.setBounds(254, 317, 253, 25);
		getContentPane().add(this.fecVencimiento);

		this.label_2 = new JLabel("NRO. LOTE:");
		this.label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		this.label_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_2.setForeground(Color.BLACK);
		this.label_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.label_2.setBounds(9, 353, 215, 25);
		getContentPane().add(this.label_2);

		this.txtNroLote = new JTextField();
		this.txtNroLote.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtNroLote.setForeground(SystemColor.windowBorder);
		this.txtNroLote.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtNroLote.setColumns(10);
		this.txtNroLote.setBackground(SystemColor.controlHighlight);
		this.txtNroLote.setBounds(254, 353, 253, 25);
		getContentPane().add(this.txtNroLote);

		label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_3.setBounds(103, 54, 20, 33);
		getContentPane().add(label_3);

		label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_4.setBounds(125, 95, 20, 33);
		getContentPane().add(label_4);

		label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_5.setBounds(103, 131, 20, 33);
		getContentPane().add(label_5);

		label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_6.setBounds(125, 384, 20, 33);
		getContentPane().add(label_6);

		label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_7.setBounds(171, 310, 20, 33);
		getContentPane().add(label_7);

		label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_8.setBounds(121, 416, 20, 33);
		getContentPane().add(label_8);

		this.lblCategoria = new JLabel("CATEGOR\u00CDA:");
		this.lblCategoria.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblCategoria.setForeground(Color.BLACK);
		this.lblCategoria.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblCategoria.setBounds(10, 177, 142, 25);
		getContentPane().add(this.lblCategoria);

		this.txtCategoria = new JTextField();
		this.txtCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtCategoria.setForeground(SystemColor.windowBorder);
		this.txtCategoria.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtCategoria.setColumns(10);
		this.txtCategoria.setBackground(SystemColor.controlHighlight);
		this.txtCategoria.setBounds(252, 177, 258, 25);
		getContentPane().add(this.txtCategoria);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtCodigo, txtProducto, txtDeta, txtMarca,
				txtColor, cbUMedida, txtCantidad, txtPreComInd, txtPrecioVenInd, txtPromo1, txtCantPromo1, txtPrePromo1,
				txtPromo2, txtCantPromo2, txtPrePromo2, btnCrear, txtCategoria }));
		cargar();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
	}

	public void cargar() {

		/*
		 * Trae la informacion de producto y detalle a sus respectivos textfield
		 */
		ac = new TextAutoCompleter(txtProducto);
		ac1 = new TextAutoCompleter(txtCategoria);
		consultas model1 = new consultas();
		ResultSet rs1 = model1.cargarProductos();
		ResultSet rs2 = model1.cargarCategoria();
		try {
			while (rs1.next()) {
				ac.addItem(rs1.getString("producto"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		try {
			while (rs2.next()) {
				ac1.addItem(rs2.getString("categoria"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}

		consultas model = new consultas();
		ResultSet rs = model.ultimoProducto();
		try {
			int may = -1;
			while (rs.next()) {
				String cod = rs.getString("codproducto");
				int flag = 0;
				if (cod.length() >= 6) {
					flag = 1;
				} else {
					for (int i = 0; i < cod.length(); i++) {
						if (Character.isLetter(cod.charAt(i))) {
							flag = 1;
						}
					}
				}

				if (flag == 0) {
					int code = Integer.parseInt(cod);
					if (code > may) {
						may = code;
					}
				}
			}
			may++;
			txtCodigo.setText("" + may);
		} catch (Exception e) {
			txtCodigo.setText("1");
		}
	}

	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		int rs = 0;
		try {
			if (txtCodigo.getText().length() == 0 || txtProducto.getText().length() == 0
					|| txtDeta.getText().length() == 0 || txtCategoria.getText().length() == 0
					|| txtCantidad.getText().length() == 0 || txtPreComInd.getText().length() == 0
					|| txtPrecioVenInd.getText().length() == 0 || cbUMedida.getSelectedIndex() == -1) {
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
				this.setAlwaysOnTop(true);
			} else {
				this.setAlwaysOnTop(false);
				double pc = Float.parseFloat(txtPreComInd.getText());
				double pv = Float.parseFloat(txtPrecioVenInd.getText());
				pc = redondearDecimales(pc, 1);
				pv = redondearDecimales(pv, 1);

				String promo1 = txtPromo1.getText();
				double cantPromo1 = Float.parseFloat(txtCantPromo1.getText());
				double prePromo1 = Float.parseFloat(txtPrePromo1.getText());
				String promo2 = txtPromo2.getText();
				double cantPromo2 = Float.parseFloat(txtCantPromo2.getText());
				double prePromo2 = Float.parseFloat(txtPrePromo2.getText());
				cantPromo1 = redondearDecimales(cantPromo1, 1);
				prePromo1 = redondearDecimales(prePromo1, 1);
				cantPromo2 = redondearDecimales(cantPromo2, 1);
				prePromo2 = redondearDecimales(prePromo2, 1);
				String nrolote = "0";

				java.sql.Date fechavencimineto = null;
				try {
					// Cambio de utils a sql.Date para envio
					Date datevencimiento = fecVencimiento.getDate();
					long d = datevencimiento.getTime();
					fechavencimineto = new java.sql.Date(d);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					nrolote = txtNroLote.getText();
					if (nrolote.length() == 0)
						nrolote = "0";
				} catch (Exception e) {
					// TODO: handle exception
				}

				rs = model.ingresarProducto(txtCodigo.getText(), txtProducto.getText(), txtDeta.getText(),
						txtCategoria.getText(), txtLaboratorio.getText(), fechavencimineto, nrolote,
						cbUMedida.getSelectedItem().toString(), Float.parseFloat(txtCantidad.getText()),
						Float.parseFloat("" + pc), Float.parseFloat("" + pv), promo1, Float.parseFloat("" + cantPromo1),
						Float.parseFloat("" + prePromo1), promo2, Float.parseFloat("" + cantPromo2),
						Float.parseFloat("" + prePromo2), txtMarca.getText(), txtColor.getText());

				if (rs == 0) {
					model.registrarFechaIngreso(txtCodigo.getText(), Float.parseFloat(txtCantidad.getText()),
							Float.parseFloat("" + pc), Float.parseFloat("" + pv), usuario);
					if (inv != null) {
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
					}
				} else
					JOptionPane.showMessageDialog(null, "Ya existe producto con este código ");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al registrar ingreso: " + e);
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

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtCodigo) {
			keyReleasedTxtCodigo(arg0);
		}
	}

	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtPrecioVenInd) {
			keyTypedTxtPrecioV(arg0);
		}
		if (arg0.getSource() == txtPreComInd) {
			keyTypedTxtPrecioC(arg0);
		}
		if (arg0.getSource() == txtCantidad) {
			keyTypedTxtCantidad(arg0);
		}
		if (arg0.getSource() == txtProducto) {
			keyTypedTxtProducto(arg0);
		}
		if (arg0.getSource() == txtCodigo) {
			keyTypedTxtCodigo(arg0);
		}
	}

	protected void keyTypedTxtCodigo(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != (char) KeyEvent.VK_DELETE)
				&& (c != (char) KeyEvent.VK_BACK_SPACE) && (c != (char) KeyEvent.VK_ENTER)) {
			arg0.consume();
		}
		if (txtCodigo.getText().length() == 40) {
			arg0.consume();
		}
	}

	protected void keyTypedTxtProducto(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (txtProducto.getText().length() == 40 || c == '_') {
			arg0.consume();
		}
	}

	protected void keyReleasedTxtCodigo(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c == (char) KeyEvent.VK_ENTER)) {
			String nombreProducto = "";
			try {
				String cod = txtCodigo.getText();
				cod = cod.substring(0, 5);
				ResultSet rs = model.ObtenerNombreProducto(cod);

				rs.next();
				nombreProducto = rs.getString("producto");
			} catch (Exception ex) {
				// JOptionPane.showMessageDialog(null, "ERROR al obtener nombre
				// de producto: " + ex);
			}
			txtProducto.setText(nombreProducto);
		}
	}

	protected void keyTypedTxtCantidad(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)
				&& (c != (char) KeyEvent.VK_ENTER) && (c != '.')) {
			arg0.consume();
		}
		if (txtCantidad.getText().length() == 10) {
			arg0.consume();
		}
		if (c == '.' && txtCantidad.getText().contains(".")) {
			arg0.consume();
		}
	}

	protected void keyTypedTxtPrecioC(KeyEvent arg0) {
		// txtPrecioV.setText(null);
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)
				&& (c != (char) KeyEvent.VK_ENTER) && (c != '.')) {
			arg0.consume();
		}
		if (txtPreComInd.getText().length() == 15) {
			arg0.consume();
		}
		if (c == '.' && txtPreComInd.getText().contains(".")) {
			arg0.consume();
		}
	}

	protected void keyTypedTxtPrecioV(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c < '0' || c > '9') && (c != (char) KeyEvent.VK_DELETE) && (c != (char) KeyEvent.VK_BACK_SPACE)
				&& (c != (char) KeyEvent.VK_ENTER) && (c != '.')) {
			arg0.consume();
		}
		if (txtPrecioVenInd.getText().length() == 15) {
			arg0.consume();
		}
		if (c == '.' && txtPrecioVenInd.getText().contains(".")) {
			arg0.consume();
		}
	}

	public void limpiar() {
		// txtCodigo.setText(null);
		java.util.Date fecha = new Date();
		txtProducto.setText(null);
		txtDeta.setText(null);
		txtCategoria.setText(null);
		txtLaboratorio.setText(null);
		fecVencimiento.setDate(fecha);
		txtNroLote.setText("0");
		txtMarca.setText(null);
		txtColor.setText(null);
		txtCantidad.setText("1");
		txtPreComInd.setText("0");
		txtPrecioVenInd.setText("0");
		cbUMedida.setSelectedIndex(0);
		txtCantPromo1.setText("0");
		txtPromo1.setText("0");
		txtPrePromo1.setText("0");
		txtPromo2.setText("0");
		txtCantPromo2.setText("0");
		txtPrePromo2.setText("0");
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
		if (inv != null)
			inv.setEnabled(true);
		if (v != null)
			v.setEnabled(true);
	}
}
