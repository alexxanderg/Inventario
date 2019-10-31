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

	public static void main(String[] args) {
		try {
			NuevoProducto dialog = new NuevoProducto(null, null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NuevoProducto(MantenimientoProductos temp2, Ventas temp3,String temp4) {
		inv = temp2;
		v = temp3;
		usuario = temp4;
		setAlwaysOnTop(true);
		setBounds(100, 100, 526, 569);
		getContentPane().setLayout(null);
		setResizable(false);
		addWindowListener(this);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setForeground(SystemColor.windowBorder);
		txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(253, 311, 204, 25);
		getContentPane().add(txtCantidad);

		cbUMedida = new JComboBox();
		cbUMedida.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cbUMedida.setModel(new DefaultComboBoxModel(new String[] { "Unidad", "Kilo", "Litro", "Gramo", "Metro" }));
		cbUMedida.setBounds(253, 260, 204, 25);
		getContentPane().add(cbUMedida);
		cbUMedida.setSelectedIndex(0);

		txtPreComInd = new JTextField();
		txtPreComInd.addKeyListener(this);
		txtPreComInd.setForeground(SystemColor.windowBorder);
		txtPreComInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPreComInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPreComInd.setColumns(10);
		txtPreComInd.setBackground(SystemColor.controlHighlight);
		txtPreComInd.setBounds(253, 362, 204, 25);
		getContentPane().add(txtPreComInd);

		txtPrecioVenInd = new JTextField();
		txtPrecioVenInd.addKeyListener(this);
		txtPrecioVenInd.setForeground(SystemColor.windowBorder);
		txtPrecioVenInd.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecioVenInd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtPrecioVenInd.setColumns(10);
		txtPrecioVenInd.setBackground(SystemColor.controlHighlight);
		txtPrecioVenInd.setBounds(253, 415, 204, 25);
		getContentPane().add(txtPrecioVenInd);
		lblCdigo = new JLabel("C\u00D3DIGO:");
		lblCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setForeground(SystemColor.desktop);
		lblCdigo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCdigo.setBounds(10, 82, 138, 38);
		getContentPane().add(lblCdigo);

		txtCodigo = new JTextField();
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
		lblProducto.setHorizontalAlignment(SwingConstants.LEFT);
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
		txtProducto.setBounds(158, 150, 352, 25);
		getContentPane().add(txtProducto);

		lblCantidad = new JLabel("DETALLES:");
		lblCantidad.setVerticalAlignment(SwingConstants.TOP);
		lblCantidad.setForeground(SystemColor.desktop);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantidad.setBounds(10, 208, 178, 38);
		getContentPane().add(lblCantidad);

		lblPrecio = new JLabel("PRE. COMPRA INDV:");
		lblPrecio.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setForeground(SystemColor.desktop);
		lblPrecio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecio.setBounds(10, 347, 235, 38);
		getContentPane().add(lblPrecio);

		lblCantdad = new JLabel("CANTIDAD:");
		lblCantdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantdad.setForeground(SystemColor.desktop);
		lblCantdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantdad.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblCantdad.setBounds(10, 296, 159, 38);
		getContentPane().add(lblCantdad);

		lblPrecioVenta = new JLabel("PRE. VENTA INDV:");
		lblPrecioVenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioVenta.setForeground(SystemColor.desktop);
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioVenta.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPrecioVenta.setBounds(10, 402, 211, 38);
		getContentPane().add(lblPrecioVenta);

		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setForeground(SystemColor.menu);
		btnCrear.setFont(new Font("EngraversGothic BT", Font.BOLD, 35));
		btnCrear.setBackground(new Color(30, 144, 255));
		btnCrear.setBounds(0, 488, 520, 55);
		getContentPane().add(btnCrear);

		lblUMedida = new JLabel("U. MEDIDA:");
		lblUMedida.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUMedida.setHorizontalAlignment(SwingConstants.LEFT);
		lblUMedida.setForeground(Color.BLACK);
		lblUMedida.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblUMedida.setBounds(10, 251, 171, 38);
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
		txtDeta.setBounds(158, 208, 352, 25);
		getContentPane().add(txtDeta);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, txtProducto, txtDeta, cbUMedida, txtCantidad, txtPreComInd, txtPrecioVenInd, btnCrear}));
		cargar();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
	}

	public void cargar() {
		
		/*Trae la informacion de producto y detalle a sus respectivos textfield*/		
		ac = new TextAutoCompleter(txtProducto);
		ac1 = new TextAutoCompleter(txtDeta);
		consultas model1 = new consultas();
		ResultSet rs1 = model1.cargarProductos( );
		try {
			while (rs1.next()) {
				ac.addItem(rs1.getString("producto"));
				ac1.addItem(rs1.getString("detalles"));
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
					|| txtDeta.getText().length() == 0 || txtCantidad.getText().length() == 0
					|| txtPreComInd.getText().length() == 0 || txtPrecioVenInd.getText().length() == 0
					|| cbUMedida.getSelectedIndex() == -1) {
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Por favor llene todos los campos correctamente");
				this.setAlwaysOnTop(true);
			} else {
				this.setAlwaysOnTop(false);
				double pc = Float.parseFloat(txtPreComInd.getText());
				double pv = Float.parseFloat(txtPrecioVenInd.getText());
				pc = redondearDecimales(pc, 1);
				pv = redondearDecimales(pv, 1);
				rs = model.ingresarProducto(txtCodigo.getText(), txtProducto.getText(), txtDeta.getText(),
						cbUMedida.getSelectedItem().toString(), Float.parseFloat(txtCantidad.getText()),
						Float.parseFloat("" + pc), Float.parseFloat("" + pv));
				
				java.util.Date date = new Date();
				Object date2 = new java.sql.Timestamp(date.getTime());
				//Date date2 = new java.sql.Date(date.getTime());
				rs = model.registrarFechaIngreso(txtCodigo.getText(), Float.parseFloat(txtCantidad.getText()), Float.parseFloat("" + pc), Float.parseFloat("" + pv),date2, usuario);
				
				if (rs == 0) {
					if (inv != null) {
						inv.cargarDatos();
						inv.selecionarProducto(txtCodigo.getText());
						txtCodigo.requestFocus();
						cargar();
						this.setAlwaysOnTop(true);
						inv.ajustarAnchoColumnas();
						limpiar();
					}
					if (v != null) {
						v.setVisible(true);
						v.setEnabled(true);
						this.dispose();
						v.dtm.addRow(new Object[] { 1, txtProducto.getText(), txtDeta.getText(), txtCantidad.getText(),
								txtPrecioVenInd.getText(), txtPrecioVenInd.getText(), txtCodigo.getText(), txtPreComInd.getText() });
						v.seleccionarRow();
						// v.sumarSubTotales();
						v.sumarTotal();
					}
				} else
					JOptionPane.showMessageDialog(null, "Ya existe producto con este código");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al registrar ingreso: "+e);
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
		txtProducto.setText(null);
		txtCantidad.setText(null);
		txtPreComInd.setText(null);
		txtPrecioVenInd.setText(null);
		cbUMedida.setSelectedIndex(0);
		txtDeta.setText(null);
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
