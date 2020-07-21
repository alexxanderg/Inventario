package gui_ventas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModificarPrecioVenta extends JFrame implements ActionListener, WindowListener, KeyListener, ItemListener{

	private JPanel contentPane;
	private JLabel txtTitulo;
	private JLabel lblNewLabel;
	private JLabel lblPrecioPorUnidad;
	private JLabel lblSubtotal;
	private JTextField txtCantidad;
	private JTextField txtPUnidadOriginal;
	private JTextField txtTotal;
	private JButton btnCambiar;
	private JButton btnMenos1;
	private JButton btnMas1;
	private JButton btnEliminarProducto;
	private JTextField txtPreCompra;
	private JComboBox cbPrecio;
	private JPanel panel;
	private JLabel lblDescuento;
	private JTextField txtDescuentoIndiv;
	private JButton btnCancelar;
	private JLabel lblPreCdescuento;
	private JTextField txtPreCDesc;
	private JLabel lblDescuentoTotal;
	private JTextField txtDescuentoTot;
	private JLabel lblS;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	
	
	String nomPromo1;
	double cantPromo1;
	double prePromo1;
	String nomPromo2;
	double cantPromo2;
	double prePromo2;
	String uniMedOriginal;
	double preUniOriginal;
	
	Ventas ventas;
	String nomProdVenta;
	double cantVenta; 
	double preCDesc;
	double subTotVenta;
	double preCompraVenta;
	double descTVenta;
	int idProd;
	String uniMedVenta;
	
	consultas consulta = new consultas();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarPrecioVenta frame = new ModificarPrecioVenta(null, 0, null, null, 0, 0, 0, 0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ModificarPrecioVenta(Ventas ventas, int idProd, String nomProdVenta, String uniMedVenta, double cantVenta, double preCDesC, double subTotVenta, double preCompraVenta, double descTVenta) {
		setTitle("Modificar precio de venta");
		this.ventas = ventas;
		this.nomProdVenta = nomProdVenta;
		this.uniMedVenta = uniMedVenta;
		this.cantVenta = cantVenta;
		this.preCDesc = preCDesC;
		this.subTotVenta = subTotVenta;
		this.preCompraVenta = preCompraVenta;
		this.descTVenta = descTVenta;
		this.idProd = idProd;
				
		setAlwaysOnTop(true);
		addWindowListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTitulo = new JLabel("TITULO");
		txtTitulo.setForeground(Color.WHITE);
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setBounds(0, 0, 441, 50);
		contentPane.add(txtTitulo);
		
		lblNewLabel = new JLabel("Cantidad:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
		lblNewLabel.setBounds(42, 124, 172, 31);
		contentPane.add(lblNewLabel);
		
		lblPrecioPorUnidad = new JLabel("Precio original por:");
		lblPrecioPorUnidad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioPorUnidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecioPorUnidad.setFont(new Font("Candara", Font.BOLD, 20));
		lblPrecioPorUnidad.setBounds(42, 167, 172, 31);
		contentPane.add(lblPrecioPorUnidad);
		
		lblSubtotal = new JLabel("TOTAL:     S/");
		lblSubtotal.setForeground(new Color(220, 20, 60));
		lblSubtotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Candara", Font.BOLD, 25));
		lblSubtotal.setBounds(45, 348, 216, 31);
		contentPane.add(lblSubtotal);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtCantidad(e);
			}
		});
		txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCantidad.setBounds(268, 125, 134, 31);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtPUnidadOriginal = new JTextField();
		txtPUnidadOriginal.setEditable(false);
		txtPUnidadOriginal.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPUnidadOriginal.setColumns(10);
		txtPUnidadOriginal.setBounds(268, 196, 134, 31);
		contentPane.add(txtPUnidadOriginal);
		
		txtTotal = new JTextField();
		txtTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtTotal(e);
			}
		});
		txtTotal.setForeground(new Color(220, 20, 60));
		txtTotal.setFont(new Font("Arial", Font.BOLD, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(268, 348, 134, 31);
		contentPane.add(txtTotal);
		
		btnCambiar = new JButton("Cambiar");
		btnCambiar.setForeground(new Color(255, 255, 255));
		btnCambiar.setBackground(new Color(30, 144, 255));
		btnCambiar.addActionListener(this);
		btnCambiar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCambiar.setBounds(227, 407, 175, 43);
		contentPane.add(btnCambiar);
		
		btnMenos1 = new JButton("-1");
		btnMenos1.addActionListener(this);
		btnMenos1.setForeground(Color.WHITE);
		btnMenos1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMenos1.setBackground(new Color(244, 164, 96));
		btnMenos1.setBounds(224, 61, 86, 31);
		contentPane.add(btnMenos1);
		
		btnMas1 = new JButton("+1");
		btnMas1.addActionListener(this);
		btnMas1.setForeground(Color.WHITE);
		btnMas1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMas1.setBackground(new Color(60, 179, 113));
		btnMas1.setBounds(323, 61, 76, 31);
		contentPane.add(btnMas1);
		
		btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.addActionListener(this);
		btnEliminarProducto.setForeground(Color.WHITE);
		btnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminarProducto.setBackground(new Color(220, 20, 60));
		btnEliminarProducto.setBounds(42, 63, 172, 31);
		contentPane.add(btnEliminarProducto);
		
		txtPreCompra = new JTextField();
		txtPreCompra.setVisible(false);
		txtPreCompra.setEnabled(false);
		txtPreCompra.setEditable(false);
		txtPreCompra.setHorizontalAlignment(SwingConstants.CENTER);
		txtPreCompra.setText("<dynamic>");
		txtPreCompra.setFont(new Font("Dialog", Font.BOLD, 15));
		txtPreCompra.setColumns(10);
		txtPreCompra.setBounds(0, 49, 42, 31);
		contentPane.add(txtPreCompra);
		
		cbPrecio = new JComboBox();
		cbPrecio.addItemListener(this);
		cbPrecio.setFont(new Font("Dialog", Font.BOLD, 16));
		cbPrecio.setBounds(42, 196, 172, 31);
		contentPane.add(cbPrecio);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 441, 50);
		contentPane.add(panel);
		
		lblDescuento = new JLabel("Desc. individual:");
		lblDescuento.setForeground(new Color(102, 205, 170));
		lblDescuento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuento.setFont(new Font("Candara", Font.BOLD, 20));
		lblDescuento.setBounds(42, 226, 172, 31);
		contentPane.add(lblDescuento);
		
		txtDescuentoIndiv = new JTextField();
		txtDescuentoIndiv.setEditable(false);
		txtDescuentoIndiv.setForeground(new Color(102, 205, 170));
		txtDescuentoIndiv.setText("0.0");
		txtDescuentoIndiv.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDescuentoIndiv.setColumns(10);
		txtDescuentoIndiv.setBounds(268, 227, 134, 31);
		contentPane.add(txtDescuentoIndiv);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnCancelar(arg0);
			}
		});
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancelar.setBackground(new Color(220, 20, 60));
		btnCancelar.setBounds(42, 407, 175, 43);
		contentPane.add(btnCancelar);
		
		lblPreCdescuento = new JLabel("Precio c/descuento:");
		lblPreCdescuento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPreCdescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreCdescuento.setFont(new Font("Candara", Font.BOLD, 20));
		lblPreCdescuento.setBounds(42, 257, 172, 31);
		contentPane.add(lblPreCdescuento);
		
		txtPreCDesc = new JTextField();
		txtPreCDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyReleasedTxtPreCDesc(e);
			}
		});
		txtPreCDesc.setText("0.0");
		txtPreCDesc.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPreCDesc.setColumns(10);
		txtPreCDesc.setBounds(268, 258, 134, 31);
		contentPane.add(txtPreCDesc);
		
		lblDescuentoTotal = new JLabel("Descuento total:");
		lblDescuentoTotal.setForeground(new Color(102, 205, 170));
		lblDescuentoTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDescuentoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuentoTotal.setFont(new Font("Candara", Font.BOLD, 20));
		lblDescuentoTotal.setBounds(42, 316, 172, 31);
		contentPane.add(lblDescuentoTotal);
		
		txtDescuentoTot = new JTextField();
		txtDescuentoTot.setEditable(false);
		txtDescuentoTot.setForeground(new Color(102, 205, 170));
		txtDescuentoTot.setText("0.0");
		txtDescuentoTot.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDescuentoTot.setColumns(10);
		txtDescuentoTot.setBounds(268, 317, 134, 31);
		contentPane.add(txtDescuentoTot);
		
		lblS = new JLabel("S/");
		lblS.setVerticalAlignment(SwingConstants.BOTTOM);
		lblS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblS.setFont(new Font("Candara", Font.BOLD, 20));
		lblS.setBounds(230, 257, 31, 31);
		contentPane.add(lblS);
		
		label_1 = new JLabel("S/");
		label_1.setForeground(new Color(102, 205, 170));
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1.setBounds(230, 316, 31, 31);
		contentPane.add(label_1);
		
		label_2 = new JLabel("S/");
		label_2.setForeground(new Color(102, 205, 170));
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Candara", Font.BOLD, 20));
		label_2.setBounds(230, 226, 31, 31);
		contentPane.add(label_2);
		
		label_3 = new JLabel("S/");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Candara", Font.BOLD, 20));
		label_3.setBounds(230, 196, 31, 31);
		contentPane.add(label_3);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCantidad, cbPrecio, txtDescuentoIndiv, txtPreCDesc, txtDescuentoTot, txtTotal, btnCambiar, btnCancelar, btnEliminarProducto, btnMenos1, btnMas1}));
		
		cargar();
	}
	
	public void actionPerformed(ActionEvent arg0) {
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

		consulta.iniciar();		
		ResultSet rs = consulta.buscarProductoID(idProd);
		try {
			while (rs.next()) {
				nomPromo1 	= rs.getString("promo1");
				cantPromo1	= rs.getDouble("cantp1");
				prePromo1 	= rs.getDouble("prep1");
				nomPromo2 	= rs.getString("promo2");
				cantPromo2 	= rs.getDouble("cantp2");
				prePromo2 	= rs.getDouble("prep2");
				uniMedOriginal = rs.getString("unimedida");
				preUniOriginal = rs.getFloat("precioVe");
				preCompraVenta = rs.getFloat("precioCo");
			}
		} catch (Exception e) {
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Error al llamar datos originales " + e);
			this.setAlwaysOnTop(true);
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	this.setAlwaysOnTop(false);
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            	this.setAlwaysOnTop(true);
            }
		}
		
		if( !(uniMedVenta.equals(uniMedOriginal)) )
			cbPrecio.addItem(uniMedOriginal);
		else
			cbPrecio.addItem(uniMedVenta);
			
		if(!nomPromo1.equals("0"))
			cbPrecio.addItem(nomPromo1);
		if(!nomPromo2.equals("0"))
			cbPrecio.addItem(nomPromo2);
		
		
		cbPrecio.setSelectedItem(uniMedVenta);
		/*for (int i = 0; i < cbPrecio.getItemCount(); i++)
            if (cbPrecio.getItemAt(i).toString().equals(uniMedVenta))
        		cbPrecio.setSelectedIndex(i);*/
		
		txtTitulo.setText(nomProdVenta);
		
		txtCantidad.setText("" + cantVenta);
		
		txtPreCDesc.setText("" + preCDesc);
		
		txtPreCompra.setText("" + preCompraVenta);
		
		txtTotal.setText("" + subTotVenta);
			
		
		txtDescuentoIndiv.setText("" + redondearDecimales(descTVenta/cantVenta, 2));
		

		txtDescuentoTot.setText("" + redondearDecimales(descTVenta, 2));
		
	}
	
	protected void actionPerformedBtnCambiar(ActionEvent arg0) {
		try {
			double newCant = 0; 	newCant = Double.parseDouble(txtCantidad.getText());
			String newUnimed = "0";	newUnimed = cbPrecio.getSelectedItem().toString();
			double newPreCDesc = 0;	newPreCDesc = Double.parseDouble(txtPreCDesc.getText());
			double newDescIndiv = 0; 	newDescIndiv = Double.parseDouble(txtDescuentoIndiv.getText());
			double newDescTot = 0; 	newDescTot = Double.parseDouble(txtDescuentoTot.getText());
			double newSTot = 0; 	newSTot = Double.parseDouble(txtTotal.getText());
			double preCompra = 0; 	preCompra = Double.parseDouble(txtPreCompra.getText());
			
			if(newCant<=0 || newPreCDesc<0 || newDescIndiv<0 || newSTot<0 || newDescTot<0){
				this.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "No está permitido valores negativos");
				this.setAlwaysOnTop(true);
			}
			else{
				if(cbPrecio.getSelectedIndex() == 0)
					ventas.actualizartabla(newCant, newPreCDesc, (preCompra*newCant), newSTot, newDescTot, newUnimed, uniMedVenta);
				if(cbPrecio.getSelectedIndex() == 1)
					ventas.actualizartabla(newCant, newPreCDesc, (preCompra*newCant), newSTot, newDescTot, newUnimed, uniMedVenta);
				if(cbPrecio.getSelectedIndex() == 2)
					ventas.actualizartabla(newCant, newPreCDesc, (preCompra*newCant), newSTot, newDescTot, newUnimed, uniMedVenta);
				this.dispose();
			}
		} catch (Exception e) {
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
			this.setAlwaysOnTop(true);
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
	
	public void keyReleased(KeyEvent arg0) {
	}
	protected void keyReleasedTxtDescuentoTot(KeyEvent arg0) {
		/*try {
			double desctot = Double.parseDouble(txtDescuentoTot.getText());
				desctot = redondearDecimales(desctot, 2);
			double newcant = Double.parseDouble(txtCantidad.getText());
				newcant = redondearDecimales(newcant, 2);
			double descindiv = desctot/newcant;
				descindiv = redondearDecimales(descindiv, 2);
			txtDescuentoIndiv.setText("" + descindiv);
			calcular(0);
			
		} catch (Exception e) {
		}/*
	}
	protected void keyReleasedTxtPreCDesc(KeyEvent arg0) {
		/*try {
			double preUniCDescu = Double.parseDouble(txtPreCDesc.getText());
				preUniCDescu = redondearDecimales(preUniCDescu, 2);
			double newcant = Double.parseDouble(txtCantidad.getText());
				newcant = redondearDecimales(newcant, 2);
			
			double precioUniEnUso = 0;
			
			if(cbPrecio.getSelectedIndex() == 0)
				precioUniEnUso = preCDesc;
			if(cbPrecio.getSelectedIndex() == 1)
				precioUniEnUso = prePromo1;
			if(cbPrecio.getSelectedIndex() == 2)
				precioUniEnUso = prePromo1;
			
			double descindiv = precioUniEnUso - preUniCDescu;
				descindiv = redondearDecimales(descindiv, 2);
			txtDescuentoIndiv.setText("" + descindiv);
			calcular(1);//1VIENE DE PRECIO CON DESCUENTO
			
		} catch (Exception e) {
		}*/
	}
	
	
	public void calcular(int origen){
		try {
				double precioUniEnUso = 0;
				double newprecioCompra = 0;
				
				if(cbPrecio.getSelectedIndex() == 0){
					precioUniEnUso = preCDesc;
					newprecioCompra = preCompraVenta;
				}
				if(cbPrecio.getSelectedIndex() == 1){
					precioUniEnUso = prePromo1;
					newprecioCompra = preCompraVenta * cantPromo1;
				}
				if(cbPrecio.getSelectedIndex() == 2){
					precioUniEnUso = prePromo2;
					newprecioCompra = preCompraVenta * cantPromo2;
				}
				
				double newCant = 0; 		newCant = Double.parseDouble(txtCantidad.getText());
				double newPreUniSDesc = 0;	newPreUniSDesc = Double.parseDouble(txtPUnidadOriginal.getText());
				double newPreUniCDesc = 0;	newPreUniCDesc = Double.parseDouble(txtPreCDesc.getText());
				double newDescIndiv = 0;	newDescIndiv = Double.parseDouble(txtDescuentoIndiv.getText());
				double newDescTot = 0; 		newDescTot = Double.parseDouble(txtDescuentoTot.getText());
				double preTotal = 0; 		preTotal = Double.parseDouble(txtTotal.getText());
				
				
					newprecioCompra = redondearDecimales(newprecioCompra, 2);
					newPreUniSDesc = redondearDecimales(newPreUniSDesc, 2);
				newPreUniCDesc = newPreUniSDesc - newDescIndiv;
					newPreUniCDesc = redondearDecimales(newPreUniCDesc, 2);
				preTotal = newPreUniCDesc * newCant;
					preTotal = redondearDecimales(preTotal, 1);
				
				txtPreCompra.setText(""+newprecioCompra);
				txtPUnidadOriginal.setText(""+newPreUniSDesc);
				txtPreCDesc.setText(""+newPreUniCDesc);
				txtTotal.setText("" + preTotal);
			
		} catch (Exception e) {
			txtTotal.setText("0");
		}
	}
	
	protected void actionPerformedBtnEliminarProducto(ActionEvent arg0) {
		ventas.eliminarFila();
		ventas.setEnabled(true);
		this.dispose();
	}
	protected void propertyChangeChckbxMostrar(PropertyChangeEvent evt) {

	}
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == cbPrecio) {
			itemStateChangedComboBox(arg0);
		}
	}
	
	
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		this.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public void recalcularContador(String sumrest){
		try {
			
			double cant = Float.parseFloat(txtCantidad.getText());
			cant = redondearDecimales(cant, 2);
			
			double precioOriginal = Double.parseDouble(txtPUnidadOriginal.getText());
			precioOriginal = redondearDecimales(precioOriginal, 2);
			
			double descindiv = Double.parseDouble(txtDescuentoIndiv.getText());
			descindiv = redondearDecimales(descindiv, 2);
			
			if(sumrest.equals("+")){
				double newcant = cant + 1;
				newcant = redondearDecimales(newcant, 2);
				
				double desctot = newcant * descindiv;
				desctot = redondearDecimales(desctot, 1);
				
				double total = (newcant * precioOriginal) - desctot;
					total = redondearDecimales(total, 2);
				
				txtCantidad.setText("" + newcant);
				txtDescuentoTot.setText("" + desctot);
				txtTotal.setText("" + total);
			
			} else if(sumrest.equals("-")){
				double newcant = cant - 1;
				newcant = redondearDecimales(newcant, 2);
								
				double desctot = newcant * descindiv;
					desctot = redondearDecimales(desctot, 1);

				double total = (newcant * precioOriginal) - desctot;
					total = redondearDecimales(total, 2);
					
				txtCantidad.setText("" + (newcant));
				txtDescuentoTot.setText("" + desctot);
				txtTotal.setText("" + total);
				
				
			} else if(sumrest.equals("")){				
				double desctot = cant * descindiv;
					desctot = redondearDecimales(desctot, 1);
					
				double total = (cant * precioOriginal) - desctot;
					total = redondearDecimales(total, 2);
					
				txtDescuentoTot.setText("" + desctot);
				txtTotal.setText("" + total);
			
			} else if(sumrest.equals("TxtPreCDesc")){
				double newPreCDesc = Float.parseFloat(txtPreCDesc.getText());
					newPreCDesc = redondearDecimales(newPreCDesc, 2);
					
				descindiv = precioOriginal - newPreCDesc;
					descindiv = redondearDecimales(descindiv, 2);
				
				double desctot = cant * descindiv;
					desctot = redondearDecimales(desctot, 1);
				
				double total = (cant * precioOriginal) - desctot;
					total = redondearDecimales(total, 2);
				
				txtDescuentoIndiv.setText("" + descindiv);
				txtDescuentoTot.setText("" + desctot);
				txtTotal.setText("" + total);
				
			} else if(sumrest.equals("TxtTotal")){
				double total = Float.parseFloat(txtTotal.getText());
					total = redondearDecimales(total, 2);
				
				double newPreCDesc = total/cant;
					newPreCDesc = redondearDecimales(newPreCDesc, 2);
				
				descindiv = precioOriginal - newPreCDesc;
					descindiv = redondearDecimales(descindiv, 2);
			
				double desctot = cant * descindiv;
					desctot = redondearDecimales(desctot, 1);
			
				txtPreCDesc.setText("" + newPreCDesc);
				txtDescuentoIndiv.setText("" + descindiv);
				txtDescuentoTot.setText("" + desctot);
				
			}	
		} catch (Exception e) {
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Ingrese valores correctos");
			this.setAlwaysOnTop(true);
		}
		
			
	}
	
	protected void actionPerformedBtnMas1(ActionEvent arg0) {
		recalcularContador("+");
	}
	
	protected void actionPerformedBtnMenos1(ActionEvent arg0) {
		recalcularContador("-");		
	}
	
	protected void keyReleasedTxtCantidad(KeyEvent e) {
		recalcularContador("");
	}
	
	protected void keyReleasedTxtPreCDesc(KeyEvent e) {
		recalcularContador("TxtPreCDesc");
	}
	
	protected void keyReleasedTxtTotal(KeyEvent e) {
		recalcularContador("TxtTotal");
	}
	
	protected void itemStateChangedComboBox(ItemEvent arg0) {
		if(cbPrecio.getSelectedIndex() == 0){
			txtPUnidadOriginal.setText(""+preUniOriginal);
			txtPreCDesc.setText("" + txtPUnidadOriginal);
			txtTotal.setText("" + txtPUnidadOriginal);
		}
		if(cbPrecio.getSelectedIndex() == 1){
			txtCantidad.setText("1");
			txtPUnidadOriginal.setText(""+prePromo1);
			txtPreCDesc.setText("" + prePromo1);
			txtTotal.setText("" + prePromo1);
		}
		if(cbPrecio.getSelectedIndex() == 2){
			txtCantidad.setText("1");
			txtPUnidadOriginal.setText(""+prePromo2);
			txtPreCDesc.setText("" + prePromo2);
			txtTotal.setText("" + prePromo2);
		}

		txtDescuentoIndiv.setText("0");
		txtDescuentoTot.setText("0");
	}
}




