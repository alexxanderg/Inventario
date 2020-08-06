package gui_ventas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import mysql.consultas;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class ModificarPrecioVenta extends JFrame implements ActionListener, WindowListener, KeyListener, ItemListener {
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
              ModificarPrecioVenta frame = new ModificarPrecioVenta(null, 0, null, null, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
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
    setDefaultCloseOperation(3);
    setBounds(100, 100, 447, 500);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout((LayoutManager)null);
    this.txtTitulo = new JLabel("TITULO");
    this.txtTitulo.setForeground(Color.WHITE);
    this.txtTitulo.setFont(new Font("Tahoma", 0, 20));
    this.txtTitulo.setHorizontalAlignment(0);
    this.txtTitulo.setBounds(0, 0, 441, 50);
    this.contentPane.add(this.txtTitulo);
    this.lblNewLabel = new JLabel("Cantidad:");
    this.lblNewLabel.setVerticalAlignment(3);
    this.lblNewLabel.setHorizontalAlignment(4);
    this.lblNewLabel.setFont(new Font("Candara", 1, 20));
    this.lblNewLabel.setBounds(42, 124, 172, 31);
    this.contentPane.add(this.lblNewLabel);
    this.lblPrecioPorUnidad = new JLabel("Precio original por:");
    this.lblPrecioPorUnidad.setVerticalAlignment(3);
    this.lblPrecioPorUnidad.setHorizontalAlignment(2);
    this.lblPrecioPorUnidad.setFont(new Font("Candara", 1, 20));
    this.lblPrecioPorUnidad.setBounds(42, 167, 172, 31);
    this.contentPane.add(this.lblPrecioPorUnidad);
    this.lblSubtotal = new JLabel("TOTAL:     S/");
    this.lblSubtotal.setForeground(new Color(220, 20, 60));
    this.lblSubtotal.setVerticalAlignment(3);
    this.lblSubtotal.setHorizontalAlignment(4);
    this.lblSubtotal.setFont(new Font("Candara", 1, 25));
    this.lblSubtotal.setBounds(45, 348, 216, 31);
    this.contentPane.add(this.lblSubtotal);
    this.txtCantidad = new JTextField();
    this.txtCantidad.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtCantidad(e);
          }
          
          public void focusLost(FocusEvent arg0) {
            ModificarPrecioVenta.this.focusLostTxtCantidad(arg0);
          }
        });
    this.txtCantidad.addKeyListener(this);
    this.txtCantidad.setFont(new Font("Tahoma", 1, 16));
    this.txtCantidad.setBounds(268, 125, 134, 31);
    this.contentPane.add(this.txtCantidad);
    this.txtCantidad.setColumns(10);
    this.txtPUnidadOriginal = new JTextField();
    this.txtPUnidadOriginal.setEditable(false);
    this.txtPUnidadOriginal.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtPUnidad(e);
          }
        });
    this.txtPUnidadOriginal.addKeyListener(this);
    this.txtPUnidadOriginal.setFont(new Font("Tahoma", 1, 16));
    this.txtPUnidadOriginal.setColumns(10);
    this.txtPUnidadOriginal.setBounds(268, 196, 134, 31);
    this.contentPane.add(this.txtPUnidadOriginal);
    this.txtTotal = new JTextField();
    this.txtTotal.setEditable(false);
    this.txtTotal.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtSTotal(e);
          }
        });
    this.txtTotal.setForeground(new Color(220, 20, 60));
    this.txtTotal.addKeyListener(this);
    this.txtTotal.setFont(new Font("Arial", 1, 20));
    this.txtTotal.setColumns(10);
    this.txtTotal.setBounds(268, 348, 134, 31);
    this.contentPane.add(this.txtTotal);
    this.btnCambiar = new JButton("Cambiar");
    this.btnCambiar.setForeground(new Color(255, 255, 255));
    this.btnCambiar.setBackground(new Color(30, 144, 255));
    this.btnCambiar.addActionListener(this);
    this.btnCambiar.setFont(new Font("Tahoma", 1, 18));
    this.btnCambiar.setBounds(227, 407, 175, 43);
    this.contentPane.add(this.btnCambiar);
    this.btnMenos1 = new JButton("-1");
    this.btnMenos1.addActionListener(this);
    this.btnMenos1.setForeground(Color.WHITE);
    this.btnMenos1.setFont(new Font("Tahoma", 1, 15));
    this.btnMenos1.setBackground(new Color(244, 164, 96));
    this.btnMenos1.setBounds(224, 61, 86, 31);
    this.contentPane.add(this.btnMenos1);
    this.btnMas1 = new JButton("+1");
    this.btnMas1.addActionListener(this);
    this.btnMas1.setForeground(Color.WHITE);
    this.btnMas1.setFont(new Font("Tahoma", 1, 15));
    this.btnMas1.setBackground(new Color(60, 179, 113));
    this.btnMas1.setBounds(323, 61, 76, 31);
    this.contentPane.add(this.btnMas1);
    this.btnEliminarProducto = new JButton("Eliminar Producto");
    this.btnEliminarProducto.addActionListener(this);
    this.btnEliminarProducto.setForeground(Color.WHITE);
    this.btnEliminarProducto.setFont(new Font("Tahoma", 1, 15));
    this.btnEliminarProducto.setBackground(new Color(220, 20, 60));
    this.btnEliminarProducto.setBounds(42, 63, 172, 31);
    this.contentPane.add(this.btnEliminarProducto);
    this.txtPreCompra = new JTextField();
    this.txtPreCompra.setVisible(false);
    this.txtPreCompra.setEnabled(false);
    this.txtPreCompra.setEditable(false);
    this.txtPreCompra.setHorizontalAlignment(0);
    this.txtPreCompra.setText("<dynamic>");
    this.txtPreCompra.setFont(new Font("Dialog", 1, 15));
    this.txtPreCompra.setColumns(10);
    this.txtPreCompra.setBounds(0, 49, 42, 31);
    this.contentPane.add(this.txtPreCompra);
    this.cbPrecio = new JComboBox();
    this.cbPrecio.addItemListener(this);
    this.cbPrecio.setFont(new Font("Dialog", 1, 16));
    this.cbPrecio.setBounds(42, 196, 172, 31);
    this.contentPane.add(this.cbPrecio);
    this.panel = new JPanel();
    this.panel.setBackground(Color.DARK_GRAY);
    this.panel.setBounds(0, 0, 441, 50);
    this.contentPane.add(this.panel);
    this.lblDescuento = new JLabel("Desc. individual:");
    this.lblDescuento.setForeground(new Color(102, 205, 170));
    this.lblDescuento.setVerticalAlignment(3);
    this.lblDescuento.setHorizontalAlignment(4);
    this.lblDescuento.setFont(new Font("Candara", 1, 20));
    this.lblDescuento.setBounds(42, 226, 172, 31);
    this.contentPane.add(this.lblDescuento);
    this.txtDescuentoIndiv = new JTextField();
    this.txtDescuentoIndiv.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtDescuentoIndiv(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta.this.focusLostTxtDescuentoIndiv(e);
          }
        });
    this.txtDescuentoIndiv.setForeground(new Color(102, 205, 170));
    this.txtDescuentoIndiv.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent arg0) {
            ModificarPrecioVenta.this.keyReleasedTxtDescuento(arg0);
          }
        });
    this.txtDescuentoIndiv.setText("0.0");
    this.txtDescuentoIndiv.setFont(new Font("Tahoma", 1, 16));
    this.txtDescuentoIndiv.setColumns(10);
    this.txtDescuentoIndiv.setBounds(268, 227, 134, 31);
    this.contentPane.add(this.txtDescuentoIndiv);
    this.btnCancelar = new JButton("Cancelar");
    this.btnCancelar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            ModificarPrecioVenta.this.actionPerformedBtnCancelar(arg0);
          }
        });
    this.btnCancelar.setForeground(new Color(255, 255, 255));
    this.btnCancelar.setFont(new Font("Tahoma", 1, 18));
    this.btnCancelar.setBackground(new Color(220, 20, 60));
    this.btnCancelar.setBounds(42, 407, 175, 43);
    this.contentPane.add(this.btnCancelar);
    this.lblPreCdescuento = new JLabel("Precio c/descuento:");
    this.lblPreCdescuento.setVerticalAlignment(3);
    this.lblPreCdescuento.setHorizontalAlignment(4);
    this.lblPreCdescuento.setFont(new Font("Candara", 1, 20));
    this.lblPreCdescuento.setBounds(42, 257, 172, 31);
    this.contentPane.add(this.lblPreCdescuento);
    this.txtPreCDesc = new JTextField();
    txtPreCDesc.setEditable(false);
    this.txtPreCDesc.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent e) {
            ModificarPrecioVenta.this.keyReleasedTxtPreCDesc(e);
          }
        });
    this.txtPreCDesc.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtPreCDesc(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta.this.focusLostTxtPreCDesc(e);
          }
        });
    this.txtPreCDesc.setText("0.0");
    this.txtPreCDesc.setFont(new Font("Tahoma", 1, 16));
    this.txtPreCDesc.setColumns(10);
    this.txtPreCDesc.setBounds(268, 258, 134, 31);
    this.contentPane.add(this.txtPreCDesc);
    this.lblDescuentoTotal = new JLabel("Descuento total:");
    this.lblDescuentoTotal.setForeground(new Color(102, 205, 170));
    this.lblDescuentoTotal.setVerticalAlignment(3);
    this.lblDescuentoTotal.setHorizontalAlignment(4);
    this.lblDescuentoTotal.setFont(new Font("Candara", 1, 20));
    this.lblDescuentoTotal.setBounds(42, 316, 172, 31);
    this.contentPane.add(this.lblDescuentoTotal);
    this.txtDescuentoTot = new JTextField();
    this.txtDescuentoTot.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta.this.focusGainedTxtDescuentoTot(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta.this.focusLostTxtDescuentoTot(e);
          }
        });
    this.txtDescuentoTot.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent e) {
            ModificarPrecioVenta.this.keyReleasedTxtDescuentoTot(e);
          }
        });
    this.txtDescuentoTot.setForeground(new Color(102, 205, 170));
    this.txtDescuentoTot.setText("0.0");
    this.txtDescuentoTot.setFont(new Font("Tahoma", 1, 16));
    this.txtDescuentoTot.setColumns(10);
    this.txtDescuentoTot.setBounds(268, 317, 134, 31);
    this.contentPane.add(this.txtDescuentoTot);
    this.lblS = new JLabel("S/");
    this.lblS.setVerticalAlignment(3);
    this.lblS.setHorizontalAlignment(4);
    this.lblS.setFont(new Font("Candara", 1, 20));
    this.lblS.setBounds(230, 257, 31, 31);
    this.contentPane.add(this.lblS);
    this.label_1 = new JLabel("S/");
    this.label_1.setForeground(new Color(102, 205, 170));
    this.label_1.setVerticalAlignment(3);
    this.label_1.setHorizontalAlignment(4);
    this.label_1.setFont(new Font("Candara", 1, 20));
    this.label_1.setBounds(230, 316, 31, 31);
    this.contentPane.add(this.label_1);
    this.label_2 = new JLabel("S/");
    this.label_2.setForeground(new Color(102, 205, 170));
    this.label_2.setVerticalAlignment(3);
    this.label_2.setHorizontalAlignment(4);
    this.label_2.setFont(new Font("Candara", 1, 20));
    this.label_2.setBounds(230, 226, 31, 31);
    this.contentPane.add(this.label_2);
    this.label_3 = new JLabel("S/");
    this.label_3.setVerticalAlignment(3);
    this.label_3.setHorizontalAlignment(4);
    this.label_3.setFont(new Font("Candara", 1, 20));
    this.label_3.setBounds(230, 196, 31, 31);
    this.contentPane.add(this.label_3);
    setFocusTraversalPolicy((FocusTraversalPolicy)new FocusTraversalOnArray(new Component[] { 
            this.txtCantidad, this.cbPrecio, this.txtDescuentoIndiv, this.txtPreCDesc, this.txtDescuentoTot, this.txtTotal, this.btnCambiar, this.btnCancelar, this.btnEliminarProducto, this.btnMenos1, 
            this.btnMas1 }));
    cargar();
  }
  
  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource() == this.btnEliminarProducto)
      actionPerformedBtnEliminarProducto(arg0); 
    if (arg0.getSource() == this.btnMenos1)
      actionPerformedBtnMenos1(arg0); 
    if (arg0.getSource() == this.btnMas1)
      actionPerformedBtnMas1(arg0); 
    if (arg0.getSource() == this.btnCambiar)
      actionPerformedBtnCambiar(arg0); 
  }
  
  public void windowActivated(WindowEvent arg0) {}
  
  public void windowClosed(WindowEvent arg0) {}
  
  public void windowClosing(WindowEvent arg0) {
    if (arg0.getSource() == this)
      windowClosingThis(arg0); 
  }
  
  public void windowDeactivated(WindowEvent arg0) {}
  
  public void windowDeiconified(WindowEvent arg0) {}
  
  public void windowIconified(WindowEvent arg0) {}
  
  public void windowOpened(WindowEvent arg0) {}
  
  protected void windowClosingThis(WindowEvent arg0) {
    this.ventas.setEnabled(true);
    this.ventas.setVisible(true);
    setDefaultCloseOperation(0);
    dispose();
  }
  
  public void cargar() {
    setLocationRelativeTo((Component)null);
    this.consulta.iniciar();
    ResultSet rs = this.consulta.buscarProductoID(this.idProd);
    try {
      while (rs.next()) {
        this.nomPromo1 = rs.getString("promo1");
        this.cantPromo1 = rs.getDouble("cantp1");
        this.prePromo1 = rs.getDouble("prep1");
        this.nomPromo2 = rs.getString("promo2");
        this.cantPromo2 = rs.getDouble("cantp2");
        this.prePromo2 = rs.getDouble("prep2");
        this.uniMedOriginal = rs.getString("unimedida");
        this.preUniOriginal = rs.getFloat("precioVe");
        this.preCompraVenta = rs.getFloat("precioCo");
      } 
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al llamar datos originales " + e);
    } finally {
      try {
        if (rs != null)
          rs.close(); 
        if (this.consulta != null)
          this.consulta.reset(); 
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
      } 
    } 
    if (!this.uniMedVenta.equals(this.uniMedOriginal)) {
      this.cbPrecio.addItem(this.uniMedOriginal);
    } else {
      this.cbPrecio.addItem(this.uniMedVenta);
    } 
    if (!this.nomPromo1.equals("0"))
      this.cbPrecio.addItem(this.nomPromo1); 
    if (!this.nomPromo2.equals("0"))
      this.cbPrecio.addItem(this.nomPromo2); 
    this.cbPrecio.setSelectedItem(this.uniMedVenta);
    this.txtTitulo.setText(this.nomProdVenta);
    this.txtCantidad.setText("" + this.cantVenta);
    this.txtPreCDesc.setText("" + this.preCDesc);
    this.txtPreCompra.setText("" + this.preCompraVenta);
    this.txtTotal.setText("" + this.subTotVenta);
    this.txtDescuentoTot.setText("" + redondearDecimales(this.descTVenta, 2));
    keyReleasedTxtDescuentoTot((KeyEvent)null);
    calcular(0);
  }
  
  protected void actionPerformedBtnCambiar(ActionEvent arg0) {
    try {
      double newCant = 0.0;
      newCant = Double.parseDouble(this.txtCantidad.getText());
      String newUnimed = "0";
      newUnimed = this.cbPrecio.getSelectedItem().toString();
      double newPreCDesc = 0.0;
      newPreCDesc = Double.parseDouble(this.txtPreCDesc.getText());
      double newDescIndiv = 0.0;
      newDescIndiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
      double newDescTot = 0.0;
      newDescTot = Double.parseDouble(this.txtDescuentoTot.getText());
      double newSTot = 0.0;
      newSTot = Double.parseDouble(this.txtTotal.getText());
      double preCompra = 0.0;
      preCompra = Double.parseDouble(this.txtPreCompra.getText());
      if (newCant <= 0.0 || newPreCDesc < 0.0 || newDescIndiv < 0.0 || newSTot < 0.0 || newDescTot < 0.0) {
        JOptionPane.showMessageDialog(null, "No estpermitido valores negativos");
      } else {
        if (this.cbPrecio.getSelectedIndex() == 0)
          this.ventas.actualizartabla(newCant, newPreCDesc, preCompra * newCant, newSTot, newDescTot, newUnimed, this.uniMedVenta); 
        if (this.cbPrecio.getSelectedIndex() == 1)
          this.ventas.actualizartabla(newCant, newPreCDesc, preCompra * newCant, newSTot, newDescTot, newUnimed, this.uniMedVenta); 
        if (this.cbPrecio.getSelectedIndex() == 2)
          this.ventas.actualizartabla(newCant, newPreCDesc, preCompra * newCant, newSTot, newDescTot, newUnimed, this.uniMedVenta); 
        dispose();
      } 
    } catch (Exception e) {
      setAlwaysOnTop(false);
      JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
      setAlwaysOnTop(true);
    } 
  }
  
  public double redondearDecimales(double valorInicial, int numeroDecimales) {
    double resultado = valorInicial;
    double parteEntera = Math.floor(resultado);
    resultado = (resultado - parteEntera) * Math.pow(10.0D, numeroDecimales);
    resultado = Math.round(resultado);
    resultado = resultado / Math.pow(10.0D, numeroDecimales) + parteEntera;
    return resultado;
  }
  
  public void keyReleased(KeyEvent arg0) {
    if (arg0.getSource() == this.txtTotal)
      keyReleasedTxtSTotal(arg0); 
    if (arg0.getSource() == this.txtPUnidadOriginal)
      keyReleasedTxtPUnidad(arg0); 
    if (arg0.getSource() == this.txtCantidad)
      keyReleasedTxtCantidad(arg0); 
  }
  
  protected void keyReleasedTxtCantidad(KeyEvent arg0) {
    try {
      double descindiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
      descindiv = redondearDecimales(descindiv, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double desctot = newcant * descindiv;
      desctot = redondearDecimales(desctot, 2);
      this.txtDescuentoTot.setText("" + desctot);
      calcular(0);
    } catch (Exception exception) {}
  }
  
  protected void keyReleasedTxtPUnidad(KeyEvent arg0) {}
  
  protected void keyReleasedTxtDescuento(KeyEvent arg0) {
    try {
      double descindiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
      descindiv = redondearDecimales(descindiv, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double desctot = newcant * descindiv;
      desctot = redondearDecimales(desctot, 2);
      this.txtDescuentoTot.setText("" + desctot);
      calcular(0);
    } catch (Exception exception) {}
  }
  
  protected void keyReleasedTxtDescuentoTot(KeyEvent arg0) {
    try {
      double desctot = Double.parseDouble(this.txtDescuentoTot.getText());
      desctot = redondearDecimales(desctot, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double descindiv = desctot / newcant;
      descindiv = redondearDecimales(descindiv, 2);
      this.txtDescuentoIndiv.setText("" + descindiv);
      calcular(0);
    } catch (Exception exception) {}
  }
  
  protected void keyReleasedTxtPreCDesc(KeyEvent arg0) {
    try {
      double preUniCDescu = Double.parseDouble(this.txtPreCDesc.getText());
      preUniCDescu = redondearDecimales(preUniCDescu, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double precioUniEnUso = 0.0D;
      if (this.cbPrecio.getSelectedIndex() == 0)
        precioUniEnUso = this.preCDesc; 
      if (this.cbPrecio.getSelectedIndex() == 1)
        precioUniEnUso = this.prePromo1; 
      if (this.cbPrecio.getSelectedIndex() == 2)
        precioUniEnUso = this.prePromo1; 
      double descindiv = precioUniEnUso - preUniCDescu;
      descindiv = redondearDecimales(descindiv, 2);
      this.txtDescuentoIndiv.setText("" + descindiv);
      calcular(1);
    } catch (Exception exception) {}
  }
  
  public void calcular(int origen) {
    try {
      if (origen != 1) {
        double precioUniEnUso = 0.0D;
        double newprecioCompra = 0.0D;
        if (this.cbPrecio.getSelectedIndex() == 0) {
          precioUniEnUso = this.preCDesc;
          newprecioCompra = this.preCompraVenta;
        } 
        if (this.cbPrecio.getSelectedIndex() == 1) {
          precioUniEnUso = this.prePromo1;
          newprecioCompra = this.preCompraVenta * this.cantPromo1;
        } 
        if (this.cbPrecio.getSelectedIndex() == 2) {
          precioUniEnUso = this.prePromo2;
          newprecioCompra = this.preCompraVenta * this.cantPromo2;
        } 
        double newCant = 0.0D;
        newCant = Double.parseDouble(this.txtCantidad.getText());
        double newPreUniSDesc = 0.0D;
        newPreUniSDesc = Double.parseDouble(this.txtPUnidadOriginal.getText());
        double newPreUniCDesc = 0.0D;
        newPreUniCDesc = Double.parseDouble(this.txtPreCDesc.getText());
        double newDescIndiv = 0.0D;
        newDescIndiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
        double newDescTot = 0.0D;
        newDescTot = Double.parseDouble(this.txtDescuentoTot.getText());
        double preTotal = 0.0D;
        preTotal = Double.parseDouble(this.txtTotal.getText());
        newprecioCompra = redondearDecimales(newprecioCompra, 2);
        newPreUniSDesc = redondearDecimales(newPreUniSDesc, 2);
        newPreUniCDesc = newPreUniSDesc - newDescIndiv;
        newPreUniCDesc = redondearDecimales(newPreUniCDesc, 2);
        preTotal = newPreUniCDesc * newCant;
        preTotal = redondearDecimales(preTotal, 1);
        this.txtPreCompra.setText("" + newprecioCompra);
        this.txtPUnidadOriginal.setText("" + newPreUniSDesc);
        this.txtPreCDesc.setText("" + newPreUniCDesc);
        this.txtTotal.setText("" + preTotal);
      } 
    } catch (Exception e) {
      this.txtTotal.setText("0");
    } 
  }
  
  protected void keyReleasedTxtSTotal(KeyEvent arg0) {
    try {
      float cant = Float.parseFloat(this.txtCantidad.getText());
      float pret = Float.parseFloat(this.txtTotal.getText());
      double pre = (pret / cant);
      pre = redondearDecimales(pre, 2);
      switch (this.cbPrecio.getSelectedIndex()) {
        case 1:
          this.txtPUnidadOriginal.setText("" + pre);
          break;
      } 
    } catch (Exception e) {
      this.txtPUnidadOriginal.setText("0.00");
    } 
  }
  
  protected void actionPerformedBtnMas1(ActionEvent arg0) {
    try {
      this.txtCantidad.setText("" + (Float.parseFloat(this.txtCantidad.getText()) + 1.0));
      double descindiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
      descindiv = redondearDecimales(descindiv, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double desctot = newcant * descindiv;
      desctot = redondearDecimales(desctot, 2);
      this.txtDescuentoTot.setText("" + desctot);
      calcular(0);
    } catch (Exception exception) {}
  }
  
  protected void actionPerformedBtnMenos1(ActionEvent arg0) {
    try {
      float cant = Float.parseFloat(this.txtCantidad.getText());
      if (cant <= 0.0) {
        this.txtCantidad.setText("1.00");
      } else {
        this.txtCantidad.setText("" + (cant - 1.0));
        double descindiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
        descindiv = redondearDecimales(descindiv, 2);
        double newcant = Double.parseDouble(this.txtCantidad.getText());
        newcant = redondearDecimales(newcant, 2);
        double desctot = newcant * descindiv;
        desctot = redondearDecimales(desctot, 2);
        this.txtDescuentoTot.setText("" + desctot);
      } 
      calcular(0);
    } catch (Exception exception) {}
  }
  
  protected void actionPerformedBtnEliminarProducto(ActionEvent arg0) {
    this.ventas.eliminarFila();
    this.ventas.setEnabled(true);
    dispose();
  }
  
  protected void propertyChangeChckbxMostrar(PropertyChangeEvent evt) {}
  
  public void itemStateChanged(ItemEvent arg0) {
    if (arg0.getSource() == this.cbPrecio)
      itemStateChangedComboBox(arg0); 
  }
  
  protected void itemStateChangedComboBox(ItemEvent arg0) {
    if (this.cbPrecio.getSelectedIndex() == 0)
      this.txtPUnidadOriginal.setText("" + this.preUniOriginal); 
    if (this.cbPrecio.getSelectedIndex() == 1) {
      this.txtCantidad.setText("1");
      this.txtPUnidadOriginal.setText("" + this.prePromo1);
    } 
    if (this.cbPrecio.getSelectedIndex() == 2) {
      this.txtCantidad.setText("1");
      this.txtPUnidadOriginal.setText("" + this.prePromo2);
    } 
    calcular(0);
  }
  
  protected void actionPerformedBtnCancelar(ActionEvent arg0) {
    dispose();
  }
  
  public void keyPressed(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
  
  private void seleccionarTexto(FocusEvent e) {
    Object o = e.getSource();
    if (o instanceof JTextField) {
      JTextField txt = (JTextField)o;
      txt.setSelectionStart(0);
      txt.setSelectionEnd(txt.getText().length());
    } 
  }
  
  protected void focusGainedTxtPUnidad(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtCantidad(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtSTotal(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtDescuentoIndiv(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtPreCDesc(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtDescuentoTot(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusLostTxtCantidad(FocusEvent arg0) {
    if (this.txtCantidad.getText().length() == 0) {
      this.txtCantidad.setText("0");
      calcular(0);
    } 
  }
  
  protected void focusLostTxtDescuentoIndiv(FocusEvent e) {
    if (this.txtDescuentoIndiv.getText().length() == 0) {
      this.txtDescuentoIndiv.setText("0");
      calcular(0);
    } 
  }
  
  protected void focusLostTxtPreCDesc(FocusEvent e) {
    if (this.txtPreCDesc.getText().length() == 0) {
      this.txtPreCDesc.setText("0");
      calcular(0);
    } 
  }
  
  protected void focusLostTxtDescuentoTot(FocusEvent e) {
    if (this.txtDescuentoTot.getText().length() == 0) {
      this.txtDescuentoTot.setText("0");
      calcular(0);
    } 
  }
}
