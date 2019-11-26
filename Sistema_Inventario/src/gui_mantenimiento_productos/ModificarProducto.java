package gui_mantenimiento_productos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import gui_principal.EleccionVentanas;
import gui_principal.Login;
import gui_mantenimiento_usuarios.MantenimientoUsuarios;
import gui_mantenimiento_productos.MantenimientoProductos;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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
	String uniMedidaProducto;
	String cantidadProducto;
	String preciocoProducto;
	String pctjVenta;
	String preciovePoducto;
	MantenimientoProductos mp;
	EleccionVentanas el;
	private JTextField txtModificarProducto;
	private JLabel label;
	
	public static void main(String[] args) {
		try {
			ModificarProducto dialog = new ModificarProducto(null, null, null, null,  null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModificarProducto(String temp, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6, MantenimientoProductos temp7) {
		codigoProducto = temp;
		nombreProducto = temp1;
		detalleProducto = temp2;
		uniMedidaProducto = temp3;
		cantidadProducto = temp4;
		preciocoProducto = temp5;
		preciovePoducto = temp6;
		mp = temp7;
		
		addWindowListener(this);
		setBounds(100, 100, 526, 683);
		getContentPane().setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		lblCdigo = new JLabel("C\u00D3DIGO:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setForeground(SystemColor.desktop);
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigo.setBounds(10, 82, 138, 38);
		getContentPane().add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.addKeyListener(this);
		txtCodigo.setForeground(SystemColor.windowBorder);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(SystemColor.controlHighlight);
		txtCodigo.setBounds(158, 97, 352, 25);
		getContentPane().add(txtCodigo);
		
		lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProducto.setForeground(SystemColor.desktop);
		lblProducto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblProducto.setBounds(10, 137, 178, 38);
		getContentPane().add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.addKeyListener(this);
		txtProducto.setForeground(SystemColor.windowBorder);
		txtProducto.setHorizontalAlignment(SwingConstants.LEFT);
		txtProducto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtProducto.setColumns(10);
		txtProducto.setBackground(SystemColor.controlHighlight);
		txtProducto.setBounds(158, 152, 352, 25);
		getContentPane().add(txtProducto);
		
		lblCantidad = new JLabel("DETALLES:");
		lblCantidad.setVerticalAlignment(SwingConstants.TOP);
		lblCantidad.setForeground(SystemColor.desktop);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidad.setBounds(12, 199, 178, 38);
		getContentPane().add(lblCantidad);
		
		txtDeta = new JTextArea();
		txtDeta.setLineWrap(true);
		txtDeta.addKeyListener(this);
		txtDeta.setForeground(SystemColor.windowBorder);
		txtDeta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtDeta.setBackground(Color.LIGHT_GRAY);
		txtDeta.setBounds(158, 199, 352, 96);
		getContentPane().add(txtDeta);
		
		lblPrecio = new JLabel("PRE. COMPRA INDV:");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(12, 409, 253, 38);
		getContentPane().add(lblPrecio);
		
		txtPreComInd = new JTextField();
		txtPreComInd.addKeyListener(this);
		txtPreComInd.setForeground(SystemColor.windowBorder);
		txtPreComInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreComInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPreComInd.setColumns(10);
		txtPreComInd.setBackground(SystemColor.controlHighlight);
		txtPreComInd.setBounds(255, 424, 202, 25);
		getContentPane().add(txtPreComInd);
		
		lblCantdad = new JLabel("CANTIDAD:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(10, 355, 159, 38);
		getContentPane().add(lblCantdad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setForeground(SystemColor.windowBorder);
		txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(255, 370, 202, 25);
		getContentPane().add(txtCantidad);
		
		lblPrecioVenta = new JLabel("PRE. VENTA INDV:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(10, 462, 253, 38);
		getContentPane().add(lblPrecioVenta);
		
		txtPrecioVenInd = new JTextField();
		txtPrecioVenInd.addKeyListener(this);
		txtPrecioVenInd.setForeground(SystemColor.windowBorder);
		txtPrecioVenInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrecioVenInd.setColumns(10);
		txtPrecioVenInd.setBackground(SystemColor.controlHighlight);
		txtPrecioVenInd.setBounds(255, 477, 202, 25);
		getContentPane().add(txtPrecioVenInd);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setForeground(SystemColor.menu);
		btnModificar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnModificar.setBackground(new Color(30, 144, 255));
		btnModificar.setBounds(0, 599, 520, 55);
		getContentPane().add(btnModificar);
		
		lblUMedida = new JLabel("U. MEDIDA:");
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(10, 306, 171, 38);
		getContentPane().add(lblUMedida);
		
		cbUMedida = new JComboBox();
		cbUMedida.setModel(new DefaultComboBoxModel(new String[] {"Unidad", "Kilo", "Litro", "Gramo", "Metro"}));
		cbUMedida.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbUMedida.setBounds(253, 321, 204, 25);
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
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setForeground(Color.RED);
		this.label.setFont(new Font("Tahoma", Font.ITALIC, 12));
		this.label.setBounds(0, 526, 500, 59);
		getContentPane().add(this.label);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtProducto, txtDeta, cbUMedida, txtCantidad, txtPreComInd, txtPrecioVenInd, btnModificar}));
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
		txtCantidad.setText(cantidadProducto);
		txtDeta.setText(detalleProducto);
		txtPreComInd.setText(preciocoProducto);
		txtPrecioVenInd.setText(preciovePoducto);
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
			if(txtCodigo.getText().length() == 0 || txtProducto.getText().length() == 0 || txtDeta.getText().length() == 0 || txtCantidad.getText().length() == 0 || txtPreComInd.getText().length() == 0 || txtPrecioVenInd.getText().length() == 0 || cbUMedida.getSelectedIndex() == -1 ){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
				this.setAlwaysOnTop(true);
			}
			else{
				this.setAlwaysOnTop(false);
				double pc = Float.parseFloat(txtPreComInd.getText());
				double pv = Float.parseFloat(txtPrecioVenInd.getText());
				pc = redondearDecimales(pc, 2);
				pv = redondearDecimales(pv, 2);
				model.modificarProducto(codigoProducto, txtCodigo.getText(), txtProducto.getText(), txtDeta.getText(), cbUMedida.getSelectedItem().toString(), Float.parseFloat(txtCantidad.getText()), Float.parseFloat(""+pc), Float.parseFloat(""+pv));
				mp.selecionarProducto(txtCodigo.getText());
				mp.cargarDatos();
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






















