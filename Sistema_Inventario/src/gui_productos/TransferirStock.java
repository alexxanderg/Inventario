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
	private JLabel lblStock2;
	
	float cantPrincipal = 0;
	float stock2 = 0;
	float stock3 = 0;
	float stock4 = 0;
	private JLabel lblStock3;
	private JLabel lblStock4;
	private JLabel lblTienda;
	private JLabel lblTienda_1;
	private JLabel lblTienda_2;
	private JLabel lblTienda_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransferirStock dialog = new TransferirStock(0, 0, 0, 0, 0, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TransferirStock(int idProducto, float cantPrincipal, float stock2, float stock3, float stock4, String usuario, MantenimientoProd mp) {
		
		this.idProducto = idProducto;
		this.cantActual = cantPrincipal;
		this.usuario = usuario;
		this.precioCoOld = precioCoOld;
		this.precioVeOld = precioVeOld;
		this.fv = fv;
		this.mp = mp;
		
		this.cantPrincipal = cantPrincipal;
		this.stock2 = stock2;
		this.stock3 = stock3;
		this.stock4 = stock4;
		
		addWindowListener(this);
		setResizable(false);
		setBounds(100, 100, 390, 372);
		getContentPane().setLayout(null);
		
		lblStockActual = new JLabel("Origen:");
		lblStockActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStockActual.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockActual.setForeground(Color.BLACK);
		lblStockActual.setFont(new Font("Candara", Font.BOLD, 20));
		lblStockActual.setBounds(65, 144, 105, 38);
		getContentPane().add(lblStockActual);
		
		lblCantidadAIngresar = new JLabel("Destino:");
		lblCantidadAIngresar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCantidadAIngresar.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadAIngresar.setForeground(Color.BLACK);
		lblCantidadAIngresar.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidadAIngresar.setBounds(65, 185, 105, 38);
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
		txtCantidad.setFont(new Font("Candara", Font.BOLD, 20));
		txtCantidad.setColumns(10);
		txtCantidad.setBackground(SystemColor.controlHighlight);
		txtCantidad.setBounds(180, 242, 128, 25);
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
		lblCantidad.setFont(new Font("Candara", Font.BOLD, 20));
		lblCantidad.setBounds(65, 229, 105, 38);
		getContentPane().add(lblCantidad);
		
		cbOrigen = new JComboBox();
		cbOrigen.setModel(new DefaultComboBoxModel(new String[] {"Tienda 1", "Tienda 2", "Tienda 3", "Tienda 4"}));
		cbOrigen.setFont(new Font("Candara", Font.BOLD, 15));
		cbOrigen.setBounds(180, 155, 128, 25);
		getContentPane().add(cbOrigen);
		
		cbDestino = new JComboBox();
		cbDestino.setModel(new DefaultComboBoxModel(new String[] {"Tienda 1", "Tienda 2", "Tienda 3", "Tienda 4"}));
		cbDestino.setFont(new Font("Candara", Font.BOLD, 15));
		cbDestino.setBounds(180, 198, 128, 25);
		getContentPane().add(cbDestino);
		
		lblPrincipal = new JLabel("Stock principal: ");
		lblPrincipal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrincipal.setForeground(new Color(219, 112, 147));
		lblPrincipal.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrincipal.setBounds(135, 58, 56, 38);
		getContentPane().add(lblPrincipal);
		
		lblStock2 = new JLabel("Stock almacén:");
		lblStock2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStock2.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock2.setForeground(new Color(240, 128, 128));
		lblStock2.setFont(new Font("Candara", Font.BOLD, 20));
		lblStock2.setBounds(135, 95, 56, 38);
		getContentPane().add(lblStock2);
		
		lblStock3 = new JLabel("Tienda 1: 0.0");
		lblStock3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStock3.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock3.setForeground(new Color(240, 128, 128));
		lblStock3.setFont(new Font("Candara", Font.BOLD, 20));
		lblStock3.setBounds(307, 58, 39, 38);
		getContentPane().add(lblStock3);
		
		lblStock4 = new JLabel("Tienda 2: 0.0");
		lblStock4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStock4.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock4.setForeground(new Color(240, 128, 128));
		lblStock4.setFont(new Font("Candara", Font.BOLD, 20));
		lblStock4.setBounds(307, 95, 39, 38);
		getContentPane().add(lblStock4);
		
		lblTienda = new JLabel("Tienda 1 ->");
		lblTienda.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTienda.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienda.setForeground(new Color(0, 0, 0));
		lblTienda.setFont(new Font("Candara", Font.BOLD, 20));
		lblTienda.setBounds(31, 58, 97, 38);
		getContentPane().add(lblTienda);
		
		lblTienda_1 = new JLabel("Tienda 2 ->");
		lblTienda_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTienda_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienda_1.setForeground(new Color(0, 0, 0));
		lblTienda_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblTienda_1.setBounds(31, 95, 97, 38);
		getContentPane().add(lblTienda_1);
		
		lblTienda_2 = new JLabel("Tienda 3 ->");
		lblTienda_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTienda_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienda_2.setForeground(Color.BLACK);
		lblTienda_2.setFont(new Font("Candara", Font.BOLD, 20));
		lblTienda_2.setBounds(201, 58, 97, 38);
		getContentPane().add(lblTienda_2);
		
		lblTienda_3 = new JLabel("Tienda 4 ->");
		lblTienda_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTienda_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienda_3.setForeground(Color.BLACK);
		lblTienda_3.setFont(new Font("Candara", Font.BOLD, 20));
		lblTienda_3.setBounds(201, 95, 97, 38);
		getContentPane().add(lblTienda_3);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCantidad, btnGuardar, lblStock3, lblStock4, lblTienda, lblTienda_1, lblTienda_2, lblTienda_3}));
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
		
		
		lblPrincipal.setText(""+cantPrincipal);
		lblStock2.setText(""+stock2);
		lblStock3.setText(""+stock3);
		lblStock4.setText(""+stock4);
		
	}
	
	public void actualizar_ingresar(){
		try {
			float ca = Float.parseFloat(txtCantidad.getText());
			
			if(cbOrigen.getSelectedIndex() == cbDestino.getSelectedIndex())
				JOptionPane.showMessageDialog(null, "El destino no puede ser el mismo que el origen");
			else {
				if(ca <= 0)
					JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a cero");
				else{
					
					int tiendaOrigen = cbOrigen.getSelectedIndex();
					int tiendaDestino = cbDestino.getSelectedIndex();
					float newStockOrigen = 0;
					float newStockDestino = 0;
					
					if(tiendaOrigen == 0)
						cantPrincipal = cantPrincipal - ca;
					if(tiendaOrigen == 1)
						stock2 = stock2 - ca;
					if(tiendaOrigen == 2)
						stock3 = stock3 - ca;
					if(tiendaOrigen == 3)
						stock4 = stock4 - ca;
					
					if(tiendaDestino == 0)
						cantPrincipal = cantPrincipal + ca;
					if(tiendaDestino == 1)
						stock2 = stock2 + ca;
					if(tiendaDestino == 2)
						stock3 = stock3 + ca;
					if(tiendaDestino == 3)
						stock4 = stock4 + ca;
					
					//JOptionPane.showMessageDialog(null, cantPrincipal + " - " + stock2 + " - " + stock3 + " - " + stock4); 
					
					model.iniciar();
					model.modificarStocks(idProducto, cantPrincipal, stock2, stock3, stock4);
					
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
