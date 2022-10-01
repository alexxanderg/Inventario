package gui_productos;

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
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.WindowEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TransferirStock extends JDialog implements ActionListener, WindowListener {
	private JLabel lblStockActual;
	private JLabel lblCantidadAIngresar;
	private JTextField txtCantidad;
	private JButton btnGuardar;

	String usuario;
	int idProducto;
	float cantActual;
	float precioCoOld;
	float precioVeOld;
	Date fv;
	
	
	private JTextField txtAgregarStock;
	JComboBox cbOrigen;
	JComboBox cbDestino;
	
	MantenimientoProd mp = null;
	consultas model = new consultas();
	ResultSet rs;
	private JLabel lblPrincipal;
	private JLabel lblAlmacen;
	
	float cantPrincipal = 0;
	float cantAlmacen = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransferirStock dialog = new TransferirStock(0, 0, 0, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TransferirStock(int idProducto, float cantPrincipal, float cantAlmacen, String usuario, MantenimientoProd mp) {
		
		this.idProducto = idProducto;
		this.cantActual = cantPrincipal;
		this.usuario = usuario;
		this.precioCoOld = precioCoOld;
		this.precioVeOld = precioVeOld;
		this.fv = fv;
		this.mp = mp;
		
		this.cantPrincipal = cantPrincipal;
		this.cantAlmacen = cantAlmacen;
		
		addWindowListener(this);
		setResizable(false);
		setBounds(100, 100, 390, 372);
		getContentPane().setLayout(null);
		
		lblStockActual = new JLabel("Origen:");
		lblStockActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStockActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockActual.setForeground(Color.BLACK);
		lblStockActual.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblStockActual.setBounds(40, 144, 136, 38);
		getContentPane().add(lblStockActual);
		
		lblCantidadAIngresar = new JLabel("Destino:");
		lblCantidadAIngresar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadAIngresar.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadAIngresar.setForeground(Color.BLACK);
		lblCantidadAIngresar.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCantidadAIngresar.setBounds(40, 185, 136, 38);
		getContentPane().add(lblCantidadAIngresar);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				keyTypedTxtCantidadAdicinal(arg0);
			}
		});
		txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCantidad.setForeground(new Color(220, 20, 60));
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(180, 242, 166, 25);
		getContentPane().add(txtCantidad);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(this);
		btnGuardar.setForeground(SystemColor.menu);
		btnGuardar.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		btnGuardar.setBackground(new Color(30, 144, 255));
		btnGuardar.setBounds(0, 278, 374, 55);
		getContentPane().add(btnGuardar);
		
		txtAgregarStock = new JTextField();
		txtAgregarStock.setText("TRANSFERIR");
		txtAgregarStock.setRequestFocusEnabled(false);
		txtAgregarStock.setIgnoreRepaint(true);
		txtAgregarStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtAgregarStock.setForeground(Color.WHITE);
		txtAgregarStock.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		txtAgregarStock.setFocusable(false);
		txtAgregarStock.setFocusTraversalKeysEnabled(false);
		txtAgregarStock.setEditable(false);
		txtAgregarStock.setColumns(10);
		txtAgregarStock.setBackground(Color.DARK_GRAY);
		txtAgregarStock.setBounds(0, 0, 374, 58);
		getContentPane().add(txtAgregarStock);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setForeground(Color.BLACK);
		lblCantidad.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCantidad.setBounds(40, 229, 136, 38);
		getContentPane().add(lblCantidad);
		
		cbOrigen = new JComboBox();
		cbOrigen.setModel(new DefaultComboBoxModel(new String[] {"Principal", "Almacén"}));
		cbOrigen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbOrigen.setBounds(180, 158, 166, 22);
		getContentPane().add(cbOrigen);
		
		cbDestino = new JComboBox();
		cbDestino.setModel(new DefaultComboBoxModel(new String[] {"Principal", "Almacén"}));
		cbDestino.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cbDestino.setBounds(180, 199, 166, 24);
		getContentPane().add(cbDestino);
		
		lblPrincipal = new JLabel("Stock principal: ");
		lblPrincipal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipal.setForeground(Color.BLACK);
		lblPrincipal.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblPrincipal.setBounds(40, 57, 303, 38);
		getContentPane().add(lblPrincipal);
		
		lblAlmacen = new JLabel("Stock almacén:");
		lblAlmacen.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAlmacen.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlmacen.setForeground(Color.BLACK);
		lblAlmacen.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblAlmacen.setBounds(40, 94, 303, 38);
		getContentPane().add(lblAlmacen);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCantidad, btnGuardar}));
		cargar();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
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
		//mp.setEnabled(true);
	}
	
	public void cargar(){
		this.setLocationRelativeTo(null);
		
		java.util.Date date = new Date();
		date.getTime();
		
		
		lblPrincipal.setText("Stock principal: "+cantPrincipal);
		lblAlmacen.setText("Stock almacén: "+cantAlmacen);
		
	}
	
	public void actualizar_ingresar(){
		try {
			float ca = Float.parseFloat(txtCantidad.getText());
			
			if(cbOrigen.getSelectedIndex() == cbDestino.getSelectedIndex())
				JOptionPane.showMessageDialog(null, "No destino no puede ser el mismo que el origen");
			else {
				if(ca <= 0)
					JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a cero");
				else{
					
					int origen = cbOrigen.getSelectedIndex();
					int destino = cbOrigen.getSelectedIndex();
					float newPrincipal = 0;
					float newAlmacen = 0;
					
					if(origen == 0) {
						newPrincipal = cantPrincipal - ca;
						newAlmacen = cantAlmacen + ca;
					}
					else if (origen == 1) {
						newPrincipal = cantPrincipal + ca;
						newAlmacen = cantAlmacen - ca;
					}
					
					//JOptionPane.showMessageDialog(null, newPrincipal + " - " + newAlmacen); 
					
					model.iniciar();
					model.modificarStocks(idProducto, newPrincipal, newAlmacen);
					
					//mp.setEnabled(true);
					mp.cargar();
					mp.selecionarProducto(""+idProducto);
					mp.ajustarAnchoColumnas();
					//mp.limpiar();
					this.dispose();
					
					model.reset();
					
					
					
					
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese la cantidad correctamente");
		}
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		actualizar_ingresar();
		
	}
	protected void keyTypedTxtCantidadAdicinal(KeyEvent arg0) {
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
}
