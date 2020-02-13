package gui_mantenimiento_productos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;

import gui_principal.EleccionVentanas;
import mysql.consultas;
import utils.Utilitarios;

public class ModificarProducto extends JDialog implements ActionListener, KeyListener, WindowListener{
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JLabel lblCantidad;
	private JTextArea txtDeta;
	private JLabel lblPrecio;
	private JTextField txtPreComInd;
	private JLabel lblCantdad;
	private JTextField txtCantidad;
	private JLabel lblPrecioVenta;
	private JTextField txtPrecioVenInd;
	private JButton btnModificar;
	private JLabel lblUMedida;
	private JComboBox cbUMedida;
	
	consultas model = new consultas();
	ResultSet rs;
	String codigoProducto;
	String nombreProducto;
	String detalleProducto;
	String laboratorio;
	String fechaVen;
	String nroLote;
	String uniMedidaProducto;
	String cantidadProducto;
	String preciocoProducto;
	String pctjVenta;
	String preciovePoducto;
	String marca;
	String color;
	MantenimientoProductos mp;
	EleccionVentanas el;
	private JTextField txtModificarProducto;
	private JLabel label;
	private JTextField txtPrePromo2;
	private JTextField txtCantPromo2;
	private JTextField txtPromo2;
	private JTextField txtPrePromo1;
	private JTextField txtCantPromo1;
	private JTextField txtPromo1;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JTextField txtMarca;
	private JTextField txtColor;
	private JLabel lblLaboratorio;
	private JTextField txtLaboratorio;
	private JLabel lblFVencimiento;
	private JLabel lblNroLote;
	private JTextField txtNroLote;
	private JDateChooser fecVencimiento;
	
	public static void main(String[] args) {
		try {
			ModificarProducto dialog = new ModificarProducto(null, null, null, null,  null, null, null, null,null,null,null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModificarProducto(String temp, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6,String temp7,String temp8,String temp9,String temp10,String temp11, MantenimientoProductos temp12) {
		setTitle("Modificar producto");
		codigoProducto = temp;
		nombreProducto = temp1;
		detalleProducto = temp2;
		laboratorio= temp3;
		fechaVen = temp4;
		nroLote = temp5;
		uniMedidaProducto = temp6;
		cantidadProducto = temp7;
		preciocoProducto = temp8;
		preciovePoducto = temp9;
		marca = temp10;
		color = temp11;
		
		mp = temp12;
		
		addWindowListener(this);
		setBounds(100, 100, 526, 873);
		getContentPane().setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		lblCdigo = new JLabel("C\u00D3DIGO:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setForeground(SystemColor.desktop);
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigo.setBounds(10, 69, 138, 25);
		getContentPane().add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.addKeyListener(this);
		txtCodigo.setForeground(SystemColor.windowBorder);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(SystemColor.controlHighlight);
		txtCodigo.setBounds(158, 71, 352, 25);
		getContentPane().add(txtCodigo);
		
		lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProducto.setForeground(SystemColor.desktop);
		lblProducto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblProducto.setBounds(10, 105, 148, 23);
		getContentPane().add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.addKeyListener(this);
		txtProducto.setForeground(SystemColor.windowBorder);
		txtProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtProducto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtProducto.setColumns(10);
		txtProducto.setBackground(SystemColor.controlHighlight);
		txtProducto.setBounds(158, 105, 352, 25);
		getContentPane().add(txtProducto);
		
		lblCantidad = new JLabel("DETALLES:");
		lblCantidad.setVerticalAlignment(SwingConstants.TOP);
		lblCantidad.setForeground(SystemColor.desktop);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidad.setBounds(12, 139, 146, 29);
		getContentPane().add(lblCantidad);
		
		txtDeta = new JTextArea();
		txtDeta.setLineWrap(true);
		txtDeta.addKeyListener(this);
		txtDeta.setForeground(SystemColor.windowBorder);
		txtDeta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtDeta.setBackground(Color.LIGHT_GRAY);
		txtDeta.setBounds(158, 139, 352, 29);
		getContentPane().add(txtDeta);
		
		lblPrecio = new JLabel("PRE. COMPRA INDV:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(14, 433, 243, 25);
		getContentPane().add(lblPrecio);
		
		txtPreComInd = new JTextField();
		txtPreComInd.addKeyListener(this);
		txtPreComInd.setForeground(SystemColor.windowBorder);
		txtPreComInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreComInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPreComInd.setColumns(10);
		txtPreComInd.setBackground(SystemColor.controlHighlight);
		txtPreComInd.setBounds(257, 435, 253, 25);
		getContentPane().add(txtPreComInd);
		
		lblCantdad = new JLabel("CANTIDAD:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(14, 397, 159, 23);
		getContentPane().add(lblCantdad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setForeground(SystemColor.windowBorder);
		txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(259, 397, 251, 25);
		getContentPane().add(txtCantidad);
		
		lblPrecioVenta = new JLabel("PRE. VENTA INDV:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(12, 468, 215, 25);
		getContentPane().add(lblPrecioVenta);
		
		txtPrecioVenInd = new JTextField();
		txtPrecioVenInd.addKeyListener(this);
		txtPrecioVenInd.setForeground(SystemColor.windowBorder);
		txtPrecioVenInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrecioVenInd.setColumns(10);
		txtPrecioVenInd.setBackground(SystemColor.controlHighlight);
		txtPrecioVenInd.setBounds(257, 470, 253, 25);
		getContentPane().add(txtPrecioVenInd);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(SystemColor.menu);
		btnModificar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(0, 788, 520, 55);
		getContentPane().add(btnModificar);
		
		lblUMedida = new JLabel("U. MEDIDA:");
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(14, 359, 171, 25);
		getContentPane().add(lblUMedida);
		
		cbUMedida = new JComboBox();
		cbUMedida.setModel(new DefaultComboBoxModel(new String[] {"Unidad", "Kilo", "Litro", "Gramo"}));
		cbUMedida.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbUMedida.setBounds(257, 361, 253, 25);
		getContentPane().add(cbUMedida);
		
		txtModificarProducto = new JTextField();
		txtModificarProducto.setText("MODIFICAR PRODUCTO");
		txtModificarProducto.setRequestFocusEnabled(false);
		txtModificarProducto.setIgnoreRepaint(true);
		txtModificarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtModificarProducto.setForeground(Color.WHITE);
		txtModificarProducto.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtModificarProducto.setFocusable(false);
		txtModificarProducto.setFocusTraversalKeysEnabled(false);
		txtModificarProducto.setEditable(false);
		txtModificarProducto.setColumns(10);
		txtModificarProducto.setBackground(Color.DARK_GRAY);
		txtModificarProducto.setBounds(0, 0, 520, 58);
		getContentPane().add(txtModificarProducto);
		
		this.label = new JLabel("<html>ATENCI\u00D3N: Si modifica aqu\u00ED su Stock(cantidad), no ser\u00E1 registrado en los reportes<br>ya que se considera como una correci\u00F3n solamente.<br>Para  hacerlo debe ir al bot\u00F3n: AGREGAR STOCK</html>");
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		this.label.setForeground(Color.RED);
		this.label.setFont(new Font("Tahoma", Font.ITALIC, 12));
		this.label.setBounds(10, 727, 500, 59);
		getContentPane().add(this.label);
		
		txtPrePromo2 = new JTextField();
		txtPrePromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo2.setForeground(SystemColor.windowBorder);
		txtPrePromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrePromo2.setColumns(10);
		txtPrePromo2.setBackground(SystemColor.controlHighlight);
		txtPrePromo2.setBounds(255, 691, 255, 25);
		getContentPane().add(txtPrePromo2);
		
		txtCantPromo2 = new JTextField();
		txtCantPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo2.setForeground(SystemColor.windowBorder);
		txtCantPromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantPromo2.setColumns(10);
		txtCantPromo2.setBackground(SystemColor.controlHighlight);
		txtCantPromo2.setBounds(257, 655, 253, 25);
		getContentPane().add(txtCantPromo2);
		
		txtPromo2 = new JTextField();
		txtPromo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtPromo2.setForeground(SystemColor.windowBorder);
		txtPromo2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPromo2.setColumns(10);
		txtPromo2.setBackground(SystemColor.controlHighlight);
		txtPromo2.setBounds(257, 619, 253, 25);
		getContentPane().add(txtPromo2);
		
		txtPrePromo1 = new JTextField();
		txtPrePromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrePromo1.setForeground(SystemColor.windowBorder);
		txtPrePromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrePromo1.setColumns(10);
		txtPrePromo1.setBackground(SystemColor.controlHighlight);
		txtPrePromo1.setBounds(257, 583, 253, 25);
		getContentPane().add(txtPrePromo1);
		
		txtCantPromo1 = new JTextField();
		txtCantPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantPromo1.setForeground(SystemColor.windowBorder);
		txtCantPromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantPromo1.setColumns(10);
		txtCantPromo1.setBackground(SystemColor.controlHighlight);
		txtCantPromo1.setBounds(255, 542, 255, 25);
		getContentPane().add(txtCantPromo1);
		
		txtPromo1 = new JTextField();
		txtPromo1.setHorizontalAlignment(SwingConstants.LEFT);
		txtPromo1.setForeground(SystemColor.windowBorder);
		txtPromo1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPromo1.setColumns(10);
		txtPromo1.setBackground(SystemColor.controlHighlight);
		txtPromo1.setBounds(257, 506, 253, 25);
		getContentPane().add(txtPromo1);
		
		label_1 = new JLabel("PROMOCION 1:");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(new Color(50, 205, 50));
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_1.setBounds(14, 506, 211, 25);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("CANTIDAD PROMO 1:");
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(new Color(50, 205, 50));
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_2.setBounds(12, 542, 235, 25);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("PRECIO PROMO1 :");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(new Color(50, 205, 50));
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_3.setBounds(14, 583, 211, 25);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("PROMOCION 2:");
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(new Color(30, 144, 255));
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_4.setBounds(14, 619, 211, 25);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("CANTIDAD PROMO 2:");
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(new Color(30, 144, 255));
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_5.setBounds(14, 655, 235, 25);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("PRECIO PROMO 2:");
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(new Color(30, 144, 255));
		label_6.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		label_6.setBounds(12, 691, 211, 25);
		getContentPane().add(label_6);
		
		this.label_7 = new JLabel("COLOR:");
		this.label_7.setVerticalAlignment(SwingConstants.BOTTOM);
		this.label_7.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_7.setForeground(Color.BLACK);
		this.label_7.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.label_7.setBounds(12, 215, 215, 25);
		getContentPane().add(this.label_7);
		
		this.label_8 = new JLabel("MARCA:");
		this.label_8.setVerticalAlignment(SwingConstants.BOTTOM);
		this.label_8.setHorizontalAlignment(SwingConstants.LEFT);
		this.label_8.setForeground(Color.BLACK);
		this.label_8.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.label_8.setBounds(12, 179, 215, 25);
		getContentPane().add(this.label_8);
		
		this.txtMarca = new JTextField();
		this.txtMarca.setText("<dynamic>");
		this.txtMarca.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtMarca.setForeground(SystemColor.windowBorder);
		this.txtMarca.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtMarca.setColumns(10);
		this.txtMarca.setBackground(SystemColor.controlHighlight);
		this.txtMarca.setBounds(257, 181, 253, 25);
		getContentPane().add(this.txtMarca);
		
		this.txtColor = new JTextField();
		this.txtColor.setText("<dynamic>");
		this.txtColor.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtColor.setForeground(SystemColor.windowBorder);
		this.txtColor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtColor.setColumns(10);
		this.txtColor.setBackground(SystemColor.controlHighlight);
		this.txtColor.setBounds(257, 215, 253, 25);
		getContentPane().add(this.txtColor);
		
		this.lblLaboratorio = new JLabel("LABORATORIO:");
		this.lblLaboratorio.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblLaboratorio.setForeground(Color.BLACK);
		this.lblLaboratorio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblLaboratorio.setBounds(12, 251, 215, 25);
		getContentPane().add(this.lblLaboratorio);
		
		this.txtLaboratorio = new JTextField();
		this.txtLaboratorio.setText("<dynamic>");
		this.txtLaboratorio.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtLaboratorio.setForeground(SystemColor.windowBorder);
		this.txtLaboratorio.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtLaboratorio.setColumns(10);
		this.txtLaboratorio.setBackground(SystemColor.controlHighlight);
		this.txtLaboratorio.setBounds(257, 251, 253, 25);
		getContentPane().add(this.txtLaboratorio);
		
		this.lblFVencimiento = new JLabel("F. VENCIMIENTO:");
		this.lblFVencimiento.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblFVencimiento.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblFVencimiento.setForeground(Color.BLACK);
		this.lblFVencimiento.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblFVencimiento.setBounds(12, 287, 215, 25);
		getContentPane().add(this.lblFVencimiento);
		
		this.lblNroLote = new JLabel("NRO. LOTE:");
		this.lblNroLote.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblNroLote.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNroLote.setForeground(Color.BLACK);
		this.lblNroLote.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		this.lblNroLote.setBounds(12, 323, 215, 25);
		getContentPane().add(this.lblNroLote);
		
		this.txtNroLote = new JTextField();
		this.txtNroLote.setText("<dynamic>");
		this.txtNroLote.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtNroLote.setForeground(SystemColor.windowBorder);
		this.txtNroLote.setFont(new Font("Segoe UI", Font.BOLD, 18));
		this.txtNroLote.setColumns(10);
		this.txtNroLote.setBackground(SystemColor.controlHighlight);
		this.txtNroLote.setBounds(257, 323, 253, 25);
		getContentPane().add(this.txtNroLote);
		
		this.fecVencimiento = new JDateChooser();
		this.fecVencimiento.setBounds(257, 287, 253, 25);
		getContentPane().add(this.fecVencimiento);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtProducto, txtDeta, txtMarca, txtColor, cbUMedida, txtCantidad, txtPreComInd, txtPrecioVenInd, txtPromo1, txtCantPromo1, txtPrePromo1, txtPromo2, txtCantPromo2, txtPrePromo2, btnModificar}));
		cargarDatos();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnCrear(arg0);
		}
	}
	
	public void cargarDatos(){
		this.setLocationRelativeTo(null);
		this.isAlwaysOnTop();
		txtCodigo.setText(codigoProducto);
		txtProducto.setText(nombreProducto);
		txtLaboratorio.setText(laboratorio);
		fecVencimiento.setDate(Utilitarios.textoAFecha(fechaVen));
		txtCantidad.setText(cantidadProducto);
		txtDeta.setText(detalleProducto);
		txtPreComInd.setText(preciocoProducto);
		txtPrecioVenInd.setText(preciovePoducto);
		txtMarca.setText(marca);
		txtColor.setText(color);
		
		String promo1 = "";
		double cantp1 = 0;
		double prep1 = 0;
		String promo2 = "";
		double cantp2 = 0;
		double prep2 = 0;
		String marca ="";
		String color ="";
		String nroLote = "";
		String laboratorio = "";
		
		rs = model.buscarProducto(codigoProducto);
		try {
			while (rs.next()){
				promo1 = rs.getString("promo1");
				cantp1 = rs.getFloat("cantp1");
				prep1 = rs.getFloat("prep1");
				promo2 = rs.getString("promo2");
				cantp2 = rs.getFloat("cantp2");
				prep2 = rs.getFloat("prep2");
				marca=rs.getString("marca");
				color=rs.getString("color");
				nroLote = "" + rs.getInt("nrolote");
				laboratorio = rs.getString("laboratorio");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}

		txtPromo1.setText("0");
		txtCantPromo1.setText(""+cantp1);
		txtPrePromo1.setText(""+prep1);
		txtPromo2.setText("0");
		txtCantPromo2.setText(""+cantp2);
		txtPrePromo2.setText(""+prep2);
		txtMarca.setText(marca);
		txtColor.setText(color);
		txtNroLote.setText(nroLote);
		txtLaboratorio.setText(laboratorio);
		
		switch(uniMedidaProducto){
		case "Unidad":
			cbUMedida.setSelectedIndex(0);
			break;
		case "Kilo":
			cbUMedida.setSelectedIndex(1);
			break;
		case "Litro":
			cbUMedida.setSelectedIndex(2);
			break;
		case "Gramo":
			cbUMedida.setSelectedIndex(3);
			break;
		case "Metro":
			cbUMedida.setSelectedIndex(4);
			break;			
		}		
	}
	
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		try {
			if(txtCodigo.getText().length() == 0 || txtProducto.getText().length() == 0 || txtDeta.getText().length() == 0 || txtCantidad.getText().length() == 0 || txtPreComInd.getText().length() == 0 || txtPrecioVenInd.getText().length() == 0 || cbUMedida.getSelectedIndex() == -1){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
				this.setAlwaysOnTop(true);
			}
			else{
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
				String marca = txtMarca.getText();
				String color = txtColor.getText();
				cantPromo1 = redondearDecimales(cantPromo1, 1);
				prePromo1 = redondearDecimales(prePromo1, 1);
				cantPromo2 = redondearDecimales(cantPromo2, 1);
				prePromo2 = redondearDecimales(prePromo2, 1);

				String nrolote = "0";
				java.sql.Date fechavencimineto = null;
				try {
					//Cambio de utils a sql.Date para envio
					Date  datevencimiento = fecVencimiento.getDate();
					long d = datevencimiento.getTime();
					fechavencimineto = new java.sql.Date(d);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					nrolote = txtNroLote.getText();
					if(nrolote.length() == 0)
						nrolote = "0";
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				model.modificarProducto(codigoProducto, txtCodigo.getText(), txtProducto.getText(), txtDeta.getText(), txtLaboratorio.getText(),fechavencimineto,nrolote,cbUMedida.getSelectedItem().toString(), Float.parseFloat(txtCantidad.getText()), Float.parseFloat(""+pc), Float.parseFloat(""+pv)
				, promo1, Float.parseFloat(""+cantPromo1), Float.parseFloat(""+prePromo1), promo2, Float.parseFloat(""+cantPromo2), Float.parseFloat(""+prePromo2),marca,color		);
				
				mp.cargarDatos();
				mp.selecionarProducto(txtCodigo.getText());
				mp.ajustarAnchoColumnas();
				mp.limpiar();
				mp.setEnabled(true);
				dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
		}
	}
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtDeta) {
			keyTypedTxtDeta(arg0);
		}
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
		if ((c<'0' || c>'9') && (c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER)){
			arg0.consume();
		}
		if (txtCodigo.getText().length() == 40){
			arg0.consume();
		}			
	}
	protected void keyTypedTxtProducto(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (txtProducto.getText().length() == 40 || c == '_'){
			arg0.consume();
		}			
	}
	protected void keyTypedTxtCantidad(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER) && (c!= '.')){
			arg0.consume();
		}
		if (txtCantidad.getText().length() == 10){
			arg0.consume();
		}		
		if (c == '.' && txtCantidad.getText().contains(".")) {
			arg0.consume();
		}
	}
	protected void keyTypedTxtPrecioC(KeyEvent arg0) {
		//txtPrecioV.setText(null);
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER) && (c!= '.')){
			arg0.consume();
		}
		if (txtPreComInd.getText().length() == 15){
			arg0.consume();
		}
		if (c == '.' && txtPreComInd.getText().contains(".")) {
			arg0.consume();
		}
	}
	protected void keyTypedTxtPrecioV(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if ((c<'0' || c>'9') && (c!=(char)KeyEvent.VK_DELETE) && (c!=(char)KeyEvent.VK_BACK_SPACE) && (c!=(char)KeyEvent.VK_ENTER) && (c!= '.')){
			arg0.consume();
		}
		if (txtPrecioVenInd.getText().length() == 15){
			arg0.consume();
		}
		if (c == '.' && txtPrecioVenInd.getText().contains(".")) {
			arg0.consume();
		}
	}
	protected void keyTypedTxtDeta(KeyEvent arg0) {
		if (txtDeta.getText().length() == 100) {
			arg0.consume();
		}
		char c = arg0.getKeyChar();
		if(c == (char) KeyEvent.VK_TAB){
			cbUMedida.requestFocus();
		}
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
		mp.setEnabled(true);
	}
}






















