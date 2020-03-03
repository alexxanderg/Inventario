package gui_ventas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui_ventas.Ventas;
import mysql.consultas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.beans.PropertyChangeEvent;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JToolBar;

public class ModificarPrecioVenta extends JFrame implements ActionListener, WindowListener, KeyListener, ItemListener{

	private JPanel contentPane;
	private JLabel txtTitulo;
	private JLabel lblNewLabel;
	private JLabel lblPrecioPorUnidad;
	private JLabel lblSubtotal;
	private JTextField txtCantidad;
	private JTextField txtPUnidad;
	private JTextField txtSTotal;
	private JButton btnCambiar;
	private JButton btnMenos1;
	private JButton btnMas1;
	private JButton btnEliminarProducto;
	private JTextField txtPreCompra;
	private JCheckBox chckbxMostrar;
	private JComboBox cbPrecio;
	
	
	String promo1;
	String cpromo1;
	String ppromo1;
	String promo2;
	String cpromo2;
	String ppromo2;
	
	Ventas2 ventas;
	String prod;
	double cant; 
	double preU;
	double subt;
	double preC;
	int idprod;
	private JPanel panel;
	private JLabel lblDescuento;
	private JTextField textField;
	private JButton btnCancelar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarPrecioVenta frame = new ModificarPrecioVenta(null, null, 0, 0, 0, 0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ModificarPrecioVenta(Ventas2 ventas, String prod, double cant, double preU, double subt, double preC, int idprod) {
		setTitle("Modificar Producto en venta");
		this.ventas = ventas;
		this.prod = prod;
		this.cant = cant;
		this.preU = preU;
		this.subt = subt;
		this.preC = preC;
		this.idprod = idprod;
				
		setAlwaysOnTop(true);
		addWindowListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitulo = new JLabel("TITULO");
		txtTitulo.setForeground(Color.WHITE);
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(0, 0, 437, 50);
		contentPane.add(txtTitulo);
		
		lblNewLabel = new JLabel("Cantidad:");
		lblNewLabel.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblNewLabel.setBounds(42, 124, 144, 31);
		contentPane.add(lblNewLabel);
		
		lblPrecioPorUnidad = new JLabel("Precio:");
		lblPrecioPorUnidad.setEnabled(false);
		lblPrecioPorUnidad.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblPrecioPorUnidad.setBounds(42, 208, 144, 31);
		contentPane.add(lblPrecioPorUnidad);
		
		lblSubtotal = new JLabel("Precio final:");
		lblSubtotal.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblSubtotal.setBounds(42, 317, 144, 31);
		contentPane.add(lblSubtotal);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(this);
		txtCantidad.setFont(new Font("EngraversGothic BT", Font.BOLD, 15));
		txtCantidad.setBounds(188, 124, 211, 31);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtPUnidad = new JTextField();
		txtPUnidad.setEnabled(false);
		txtPUnidad.addKeyListener(this);
		txtPUnidad.setFont(new Font("EngraversGothic BT", Font.BOLD, 15));
		txtPUnidad.setColumns(10);
		txtPUnidad.setBounds(188, 208, 211, 31);
		contentPane.add(txtPUnidad);
		
		txtSTotal = new JTextField();
		txtSTotal.setEnabled(false);
		txtSTotal.setForeground(new Color(220, 20, 60));
		txtSTotal.addKeyListener(this);
		txtSTotal.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		txtSTotal.setColumns(10);
		txtSTotal.setBounds(188, 317, 211, 31);
		contentPane.add(txtSTotal);
		
		btnCambiar = new JButton("Cambiar");
		btnCambiar.setForeground(new Color(255, 255, 255));
		btnCambiar.setEnabled(false);
		btnCambiar.setBackground(new Color(30, 144, 255));
		btnCambiar.addActionListener(this);
		btnCambiar.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnCambiar.setBounds(224, 368, 175, 43);
		contentPane.add(btnCambiar);
		
		btnMenos1 = new JButton("-1");
		btnMenos1.addActionListener(this);
		btnMenos1.setForeground(Color.WHITE);
		btnMenos1.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnMenos1.setBackground(new Color(244, 164, 96));
		btnMenos1.setBounds(247, 62, 86, 31);
		contentPane.add(btnMenos1);
		
		btnMas1 = new JButton("+1");
		btnMas1.addActionListener(this);
		btnMas1.setForeground(Color.WHITE);
		btnMas1.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnMas1.setBackground(new Color(60, 179, 113));
		btnMas1.setBounds(343, 62, 76, 31);
		contentPane.add(btnMas1);
		
		btnEliminarProducto = new JButton("Quitar Producto");
		btnEliminarProducto.addActionListener(this);
		btnEliminarProducto.setForeground(Color.WHITE);
		btnEliminarProducto.setFont(new Font("EngraversGothic BT", Font.BOLD, 18));
		btnEliminarProducto.setBackground(new Color(220, 20, 60));
		btnEliminarProducto.setBounds(10, 63, 227, 31);
		contentPane.add(btnEliminarProducto);
		
		txtPreCompra = new JTextField();
		txtPreCompra.setVisible(false);
		txtPreCompra.setEditable(false);
		txtPreCompra.setText("<dynamic>");
		txtPreCompra.setFont(new Font("Dialog", Font.BOLD, 15));
		txtPreCompra.setColumns(10);
		txtPreCompra.setBounds(0, 27, 45, 31);
		contentPane.add(txtPreCompra);
		
		chckbxMostrar = new JCheckBox("Mostrar");
		chckbxMostrar.setVisible(false);
		chckbxMostrar.addActionListener(this);
		chckbxMostrar.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxMostrar.setBounds(0, 7, 45, 23);
		contentPane.add(chckbxMostrar);
		
		cbPrecio = new JComboBox();
		cbPrecio.addItemListener(this);
		cbPrecio.setFont(new Font("Dialog", Font.BOLD, 16));
		cbPrecio.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un precio:", "Precio unitario"}));
		cbPrecio.setBounds(188, 166, 211, 31);
		contentPane.add(cbPrecio);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 437, 50);
		contentPane.add(panel);
		
		lblDescuento = new JLabel("Descuento:");
		lblDescuento.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		lblDescuento.setBounds(42, 250, 144, 31);
		contentPane.add(lblDescuento);
		
		textField = new JTextField();
		textField.setText("0.0");
		textField.setFont(new Font("EngraversGothic BT", Font.BOLD, 15));
		textField.setColumns(10);
		textField.setBounds(188, 251, 211, 31);
		contentPane.add(textField);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("EngraversGothic BT", Font.BOLD, 20));
		btnCancelar.setEnabled(false);
		btnCancelar.setBackground(new Color(220, 20, 60));
		btnCancelar.setBounds(39, 368, 175, 43);
		contentPane.add(btnCancelar);
		
		//setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCantidad, txtPUnidad, txtSTotal, btnEliminarProducto, btnMenos1, btnMas1, btnCambiar}));
		cargar();
		cantidad();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == chckbxMostrar) {
			actionPerformedChckbxMostrar(arg0);
		}
		if (arg0.getSource() == btnEliminarProducto) {
			actionPerformedBtnEliminarProducto(arg0);
		}
		if (arg0.getSource() == btnMenos1) {
			actionPerformedBtnMenos1(arg0);
		}
		if (arg0.getSource() == btnMas1) {
			actionPerformedBtnMas1(arg0);
		}
		if (arg0.getSource() == btnCambiar) {
			actionPerformedBtnCambiar(arg0);
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
		ventas.setEnabled(true);
		ventas.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.dispose();
	}
	
	public void cargar(){
		this.setLocationRelativeTo(null);
		txtTitulo.setText(prod);
		txtCantidad.setText("" + cant);
		txtPUnidad.setText("" + preU);
		txtPreCompra.setText(""+preC);
		txtSTotal.setText(""+subt);
		

		consultas model = new consultas();
		ResultSet rs = model.buscarProductoID(idprod);
		
		try {
			while (rs.next()) {
				promo1 = rs.getString("promo1");
				cpromo1 = rs.getString("cantp1");;
				ppromo1 = rs.getString("prep1");;
				promo2 = rs.getString("promo2");;
				cpromo2 = rs.getString("cantp2");;
				ppromo2 = rs.getString("prep2");;
				
				/*
				lblPromo1.setText(promo1);
				txtPromo1.setText(ppromo1);
				lblPromo2.setText(promo2);
				txtPromo2.setText(ppromo2);*/
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
		try {
			if(!promo1.equals("0"))
				cbPrecio.addItem(promo1);
			if(!promo2.equals("0"))
				cbPrecio.addItem(promo2);
			cbPrecio.setSelectedIndex(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		cbPrecio.setSelectedIndex(1);
	}
	
	protected void actionPerformedBtnCambiar(ActionEvent arg0) {
		try {
			float cant = Float.parseFloat(txtCantidad.getText());
			float pre = -1;
			float cantfinal = -1;
			
			switch (cbPrecio.getSelectedIndex()) {
			case 1:
				pre = Float.parseFloat(txtPUnidad.getText());
				cantfinal = cant; 
				break;
			case 2:
				pre = Float.parseFloat(txtPUnidad.getText());
				cantfinal = cant * Float.parseFloat(cpromo1);
				break;
			case 3:
				pre = Float.parseFloat(txtPUnidad.getText());
				cantfinal = cant * Float.parseFloat(cpromo2);
				break;
			default:
				break;
			}
			
			float pret = Float.parseFloat(txtSTotal.getText());
			float preo = Float.parseFloat(txtPreCompra.getText());
			
			if(cant <= 0 || pre <= 0 || preo < 0 || pret <=0){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Ingrese valores válidos");
				this.setAlwaysOnTop(true);
			}
			else{
				
				
				ventas.actualizartabla(cantfinal, pre, preo, pret);	
				ventas.setEnabled(true);
				this.dispose();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
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
		if (arg0.getSource() == txtSTotal) {
			keyReleasedTxtSTotal(arg0);
		}
		if (arg0.getSource() == txtPUnidad) {
			keyReleasedTxtPUnidad(arg0);
		}
		if (arg0.getSource() == txtCantidad) {
			keyReleasedTxtCantidad(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTxtCantidad(KeyEvent arg0) {
		cantidad();
	}
	
	public void cantidad(){
		try {
			float cant = Float.parseFloat(txtCantidad.getText());
			float pre = -1;
			switch (cbPrecio.getSelectedIndex()) {
			case 0:
				pre = 0;
				break;
			case 1:
				pre = Float.parseFloat(txtPUnidad.getText());
				break;
			case 2:
				//pre = Float.parseFloat(txtPromo1.getText());
				break;
			case 3:
				//pre = Float.parseFloat(txtPromo2.getText());
				break;

			default:
				break;
			}
			
			
			float pret = Float.parseFloat(txtSTotal.getText());
			double stTemp = cant * pre;
			stTemp = redondearDecimales(stTemp,2);
			txtSTotal.setText(""+stTemp);
			
		} catch (Exception e) {
			txtSTotal.setText("0.00");
		}
	}
	
	protected void keyReleasedTxtPUnidad(KeyEvent arg0) {
		try {
			float cant = Float.parseFloat(txtCantidad.getText());
			float preu = Float.parseFloat(txtPUnidad.getText());
			float pret = Float.parseFloat(txtSTotal.getText());
			double stTemp = cant * preu;
			stTemp = redondearDecimales(stTemp,2);
			txtSTotal.setText(""+stTemp);
		} catch (Exception e) {
			txtSTotal.setText("0.00");
		}
	}
	
	protected void keyReleasedTxtSTotal(KeyEvent arg0) {
		try {
			float cant = Float.parseFloat(txtCantidad.getText());
			float pret = Float.parseFloat(txtSTotal.getText());
			double pre = pret/cant;
			pre = redondearDecimales(pre,2);
			
			switch (cbPrecio.getSelectedIndex()) {
			case 1:
				txtPUnidad.setText("" + pre);				
				break;
			case 2:
				/*txtPromo1.setText("" + pre);
				float pu = Float.parseFloat(txtPromo1.getText()) / Float.parseFloat(cpromo1);
				txtPUnidad.setText("" + redondearDecimales(pu, 2));*/
				break;
			case 3:
				/*txtPromo2.setText(""+pre);
				float pu2 = Float.parseFloat(txtPromo2.getText()) / Float.parseFloat(cpromo2);
				txtPUnidad.setText("" + redondearDecimales(pu2, 2));*/
				break;

			default:
				break;
			}
			
			
		} catch (Exception e) {
			txtPUnidad.setText("0.00");
		}
	}
	protected void actionPerformedBtnMas1(ActionEvent arg0) {
		try {
			txtCantidad.setText(""+(Float.parseFloat(txtCantidad.getText()) + 1));
			cantidad();
		} catch (Exception e) {
		}
	}
	protected void actionPerformedBtnMenos1(ActionEvent arg0) {
		try {
			float cant = Float.parseFloat(txtCantidad.getText());
			if(cant <= 0)
				txtCantidad.setText("0.00");
			else
				txtCantidad.setText("" + (cant-1));
			cantidad();
		} catch (Exception e) {
		}
	}
	protected void actionPerformedBtnEliminarProducto(ActionEvent arg0) {
		ventas.eliminarFila();
		ventas.setEnabled(true);
		this.dispose();
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == chckbxMostrar) {
			propertyChangeChckbxMostrar(evt);
		}
	}
	protected void propertyChangeChckbxMostrar(PropertyChangeEvent evt) {

	}
	
	protected void actionPerformedChckbxMostrar(ActionEvent arg0) {
		if (chckbxMostrar.isSelected()) {
			txtPreCompra.setVisible(true);
		}else {
			txtPreCompra.setVisible(false);
		}
	}
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == cbPrecio) {
			itemStateChangedComboBox(arg0);
		}
	}
	
	protected void itemStateChangedComboBox(ItemEvent arg0) {
		if(cbPrecio.getSelectedIndex() == 0){
			txtSTotal.setEnabled(false);
			btnCambiar.setEnabled(false);
			
			lblPrecioPorUnidad.setEnabled(false);
			txtPUnidad.setEnabled(false);
		/*	
			lblPromo1.setEnabled(false);
			txtPromo1.setEnabled(false);
			
			lblPromo2.setEnabled(false);
			txtPromo2.setEnabled(false);	*/		
		}
		if(cbPrecio.getSelectedIndex() == 1){
			txtSTotal.setEnabled(true);
			btnCambiar.setEnabled(true);
			
			lblPrecioPorUnidad.setEnabled(true);
			txtPUnidad.setEnabled(true);
			/*
			lblPromo1.setEnabled(false);
			txtPromo1.setEnabled(false);
			
			lblPromo2.setEnabled(false);
			txtPromo2.setEnabled(false);*/
		}
		if(cbPrecio.getSelectedIndex() == 2){
			txtSTotal.setEnabled(true);
			btnCambiar.setEnabled(true);
			
			lblPrecioPorUnidad.setEnabled(false);
			txtPUnidad.setEnabled(false);
			
		/*	lblPromo1.setEnabled(true);
			txtPromo1.setEnabled(true);
			
			lblPromo2.setEnabled(false);
			txtPromo2.setEnabled(false);
			
			float pu = Float.parseFloat(txtPromo1.getText()) / Float.parseFloat(cpromo1);
			txtPUnidad.setText("" + redondearDecimales(pu, 2) );*/
			
		}
		if(cbPrecio.getSelectedIndex() == 3){
			txtSTotal.setEnabled(true);
			btnCambiar.setEnabled(true);
			
			lblPrecioPorUnidad.setEnabled(false);
			txtPUnidad.setEnabled(false);
			/*
			lblPromo1.setEnabled(false);
			txtPromo1.setEnabled(false);
			
			lblPromo2.setEnabled(true);			
			txtPromo2.setEnabled(true);
			
			float pu = Float.parseFloat(txtPromo2.getText()) / Float.parseFloat(cpromo2);
			txtPUnidad.setText("" + redondearDecimales(pu, 2));*/
		}
		cantidad();
	}
	
	public void calcularPreUni(){
		
	}
}
