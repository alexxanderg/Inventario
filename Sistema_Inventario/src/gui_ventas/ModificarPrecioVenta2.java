package gui_ventas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Image;
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

import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class ModificarPrecioVenta2 extends JFrame implements ActionListener, WindowListener, KeyListener, ItemListener {
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
  
  private JTextField txtPreCompra;
  
  private JComboBox cbPrecio;
  
  private JLabel lblDescuento;
  
  private JTextField txtDescuentoIndiv;
  
  private JButton btnCancelar;
  
  private JLabel lblPreCdescuento;
  
  private JTextField txtNewPrecio;
  
  private JLabel lblDescuentoTotal;
  
  private JTextField txtDescuentoTot;
  
  private JLabel lblS;
  
  private JLabel label_3;
  
  String nomPromo1;
  
  double cantPromo1;
  
  double prePromo1;
  
  String nomPromo2;
  
  double cantPromo2;
  
  double prePromo2;
  
  String nomPromo3;
  
  double cantPromo3;
  
  double prePromo3;
  
  String uniMedOriginal;
  
  double preUniOriginal;
  
  Ventas ventas;
  
  String nomProdVenta;
  
  double cantVenta;
  
  double preEnUso;
  
  double subTotVenta;
  
  double preCompraVenta;
  
  double descTVenta;
  
  int idProd;
  
  String uniMedVenta;
  
  consultas consulta = new consultas();
  private JButton btnBasura;
  private JButton btnUno;
  private JButton btnDos;
  private JButton btnTres;
  private JButton btnCuatro;
  private JButton btnCinco;
  private JButton btnSeis;
  private JButton btnSiete;
  private JButton btnOcho;
  private JButton btnNueve;
  private JButton btnBorrar;
  private JButton btnCero;
  private JButton btnPunto;
  private JTextField txtSeleccionCaja;
  private JTextField txtAgregarModificar;
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            try {
              ModificarPrecioVenta2 frame = new ModificarPrecioVenta2(null, 0, null, null, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
              frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        });
  }
  
  public ModificarPrecioVenta2(Ventas ventas, int idProd, String nomProdVenta, String uniMedVenta, double cantVenta, double preCDesC, double subTotVenta, double preCompraVenta, double descTVenta) {
    setTitle("Modificar precio de venta");
    this.ventas = ventas;
    this.nomProdVenta = nomProdVenta;
    this.uniMedVenta = uniMedVenta;
    this.cantVenta = cantVenta;
    this.preEnUso = preCDesC;
    this.subTotVenta = subTotVenta;
    this.preCompraVenta = preCompraVenta;
    this.descTVenta = descTVenta;
    this.idProd = idProd;
    setAlwaysOnTop(true);
    addWindowListener(this);
    setResizable(false);
    setDefaultCloseOperation(3);
    setBounds(100, 100, 753, 480);
    this.contentPane = new JPanel();
    contentPane.setForeground(Color.WHITE);
    contentPane.setBackground(Color.DARK_GRAY);
    this.contentPane.setBorder(null);
    setContentPane(this.contentPane);
    this.contentPane.setLayout((LayoutManager)null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    this.txtPreCompra = new JTextField();
    txtPreCompra.setVisible(false);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    contentPane.setLayout(null);
    this.txtPreCompra.setEditable(false);
    this.txtPreCompra.setHorizontalAlignment(0);
    this.txtPreCompra.setText("<dynamic>");
    this.txtPreCompra.setFont(new Font("Dialog", 1, 15));
    this.txtPreCompra.setColumns(10);
    this.txtPreCompra.setBounds(0, 0, 42, 31);
    this.contentPane.add(this.txtPreCompra);
    this.txtTitulo = new JLabel("TITULO");
    this.txtTitulo.setForeground(new Color(255, 255, 255));
    this.txtTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
    this.txtTitulo.setHorizontalAlignment(0);
    this.txtTitulo.setBounds(0, 0, 737, 50);
    this.contentPane.add(this.txtTitulo);
    this.lblPrecioPorUnidad = new JLabel("Precio por:");
    lblPrecioPorUnidad.setForeground(Color.WHITE);
    this.lblPrecioPorUnidad.setVerticalAlignment(3);
    this.lblPrecioPorUnidad.setHorizontalAlignment(2);
    this.lblPrecioPorUnidad.setFont(new Font("Candara", 1, 20));
    this.lblPrecioPorUnidad.setBounds(44, 160, 160, 31);
    this.contentPane.add(this.lblPrecioPorUnidad);
    this.lblSubtotal = new JLabel("<html>TOTAL:</html>");
    this.lblSubtotal.setForeground(new Color(205, 92, 92));
    this.lblSubtotal.setVerticalAlignment(SwingConstants.TOP);
    this.lblSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
    this.lblSubtotal.setFont(new Font("Candara", Font.BOLD, 30));
    this.lblSubtotal.setBounds(489, 183, 134, 31);
    this.contentPane.add(this.lblSubtotal);
    this.lblNewLabel = new JLabel("Cantidad:");
    lblNewLabel.setForeground(Color.WHITE);
    this.lblNewLabel.setVerticalAlignment(3);
    this.lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
    this.lblNewLabel.setFont(new Font("Candara", 1, 20));
    this.lblNewLabel.setBounds(44, 79, 160, 31);
    this.contentPane.add(this.lblNewLabel);
    this.txtPUnidadOriginal = new JTextField();
    txtPUnidadOriginal.setBorder(null);
    txtPUnidadOriginal.setForeground(Color.WHITE);
    txtPUnidadOriginal.setHorizontalAlignment(SwingConstants.LEFT);
    txtPUnidadOriginal.setBackground(Color.DARK_GRAY);
    this.txtPUnidadOriginal.setEditable(false);
    this.txtPUnidadOriginal.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtPUnidad(e);
          }
        });
    this.txtPUnidadOriginal.addKeyListener(this);
    this.txtPUnidadOriginal.setFont(new Font("Tahoma", 1, 16));
    this.txtPUnidadOriginal.setColumns(10);
    this.txtPUnidadOriginal.setBounds(104, 229, 100, 31);
    this.contentPane.add(this.txtPUnidadOriginal);
    this.txtTotal = new JTextField();
    txtTotal.setBorder(null);
    txtTotal.setHorizontalAlignment(SwingConstants.LEFT);
    txtTotal.setBackground(Color.DARK_GRAY);
    this.txtTotal.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtSTotal(e);
          }
        });
    this.txtTotal.setForeground(new Color(205, 92, 92));
    this.txtTotal.addKeyListener(this);
    this.txtTotal.setFont(new Font("Arial", Font.BOLD, 23));
    this.txtTotal.setColumns(10);
    this.txtTotal.setBounds(530, 225, 93, 31);
    this.contentPane.add(this.txtTotal);
    this.btnCambiar = new JButton("Cambiar");
    this.btnCambiar.setForeground(new Color(255, 255, 255));
    this.btnCambiar.setBackground(new Color(30, 144, 255));
    this.btnCambiar.addActionListener(this);
    this.btnCambiar.setFont(new Font("Tahoma", 1, 18));
    this.btnCambiar.setBounds(262, 375, 175, 43);
    this.contentPane.add(this.btnCambiar);
    this.btnMenos1 = new JButton("-1");
    btnMenos1.setVisible(false);
    this.btnMenos1.addActionListener(this);
    this.btnMenos1.setForeground(Color.WHITE);
    this.btnMenos1.setFont(new Font("Arial", Font.BOLD, 16));
    this.btnMenos1.setBackground(new Color(220, 20, 60));
    this.btnMenos1.setBounds(0, 29, 55, 31);
    this.contentPane.add(this.btnMenos1);
    this.btnMas1 = new JButton("+1");
    btnMas1.setVisible(false);
    this.btnMas1.addActionListener(this);
    this.btnMas1.setForeground(Color.WHITE);
    this.btnMas1.setFont(new Font("Arial", Font.BOLD, 16));
    this.btnMas1.setBackground(new Color(60, 179, 113));
    this.btnMas1.setBounds(52, 29, 55, 31);
    this.contentPane.add(this.btnMas1);
    this.cbPrecio = new JComboBox();
    cbPrecio.setForeground(Color.DARK_GRAY);
    cbPrecio.setBackground(SystemColor.control);
    cbPrecio.setBorder(null);
    this.cbPrecio.addItemListener(this);
    this.cbPrecio.setFont(new Font("Dialog", 1, 16));
    this.cbPrecio.setBounds(44, 192, 160, 31);
    this.contentPane.add(this.cbPrecio);
    this.lblDescuento = new JLabel("Desc. individual: S/");
    this.lblDescuento.setForeground(new Color(102, 205, 170));
    this.lblDescuento.setVerticalAlignment(3);
    this.lblDescuento.setHorizontalAlignment(4);
    this.lblDescuento.setFont(new Font("Candara", 1, 20));
    this.lblDescuento.setBounds(473, 112, 172, 31);
    this.contentPane.add(this.lblDescuento);
    this.txtDescuentoIndiv = new JTextField();
    txtDescuentoIndiv.setBorder(null);
    txtDescuentoIndiv.setEditable(false);
    txtDescuentoIndiv.setBackground(Color.DARK_GRAY);
    this.txtDescuentoIndiv.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtDescuentoIndiv(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta2.this.focusLostTxtDescuentoIndiv(e);
          }
        });
    this.txtDescuentoIndiv.setForeground(new Color(102, 205, 170));
    this.txtDescuentoIndiv.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent arg0) {
            ModificarPrecioVenta2.this.keyReleasedTxtDescuento(arg0);
          }
        });
    this.txtDescuentoIndiv.setText("0.0");
    this.txtDescuentoIndiv.setFont(new Font("Tahoma", 1, 16));
    this.txtDescuentoIndiv.setColumns(10);
    this.txtDescuentoIndiv.setBounds(661, 112, 69, 31);
    this.contentPane.add(this.txtDescuentoIndiv);
    this.btnCancelar = new JButton("Cancelar");
    this.btnCancelar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            ModificarPrecioVenta2.this.actionPerformedBtnCancelar(arg0);
          }
        });
    this.btnCancelar.setForeground(new Color(255, 255, 255));
    this.btnCancelar.setFont(new Font("Tahoma", 1, 18));
    this.btnCancelar.setBackground(new Color(220, 20, 60));
    this.btnCancelar.setBounds(77, 375, 175, 43);
    this.contentPane.add(this.btnCancelar);
    this.lblPreCdescuento = new JLabel("Nuevo precio:");
    lblPreCdescuento.setForeground(Color.WHITE);
    this.lblPreCdescuento.setVerticalAlignment(3);
    this.lblPreCdescuento.setHorizontalAlignment(SwingConstants.LEFT);
    this.lblPreCdescuento.setFont(new Font("Candara", 1, 20));
    this.lblPreCdescuento.setBounds(44, 271, 160, 31);
    this.contentPane.add(this.lblPreCdescuento);
    this.txtNewPrecio = new JTextField();
    txtNewPrecio.setForeground(Color.DARK_GRAY);
    txtNewPrecio.setHorizontalAlignment(SwingConstants.LEFT);
    txtNewPrecio.setBackground(SystemColor.controlHighlight);
    this.txtNewPrecio.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent e) {
            ModificarPrecioVenta2.this.keyReleasedTxtPreCDesc(e);
          }
        });
    this.txtNewPrecio.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtPreCDesc(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta2.this.focusLostTxtPreCDesc(e);
          }
        });
    this.txtNewPrecio.setText("0.0");
    this.txtNewPrecio.setFont(new Font("Tahoma", 1, 16));
    this.txtNewPrecio.setColumns(10);
    this.txtNewPrecio.setBounds(83, 309, 121, 31);
    this.contentPane.add(this.txtNewPrecio);
    this.txtCantidad = new JTextField();
    txtCantidad.setForeground(Color.DARK_GRAY);
    txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
    txtCantidad.setBackground(SystemColor.controlHighlight);
    this.txtCantidad.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtCantidad(e);
          }
          
          public void focusLost(FocusEvent arg0) {
            ModificarPrecioVenta2.this.focusLostTxtCantidad(arg0);
          }
        });
    this.txtCantidad.addKeyListener(this);
    this.txtCantidad.setFont(new Font("Tahoma", 1, 16));
    this.txtCantidad.setBounds(44, 112, 160, 31);
    this.contentPane.add(this.txtCantidad);
    this.txtCantidad.setColumns(10);
    this.lblDescuentoTotal = new JLabel("Descuento total: S/");
    this.lblDescuentoTotal.setForeground(new Color(102, 205, 170));
    this.lblDescuentoTotal.setVerticalAlignment(3);
    this.lblDescuentoTotal.setHorizontalAlignment(4);
    this.lblDescuentoTotal.setFont(new Font("Candara", 1, 20));
    this.lblDescuentoTotal.setBounds(473, 141, 172, 31);
    this.contentPane.add(this.lblDescuentoTotal);
    this.txtDescuentoTot = new JTextField();
    txtDescuentoTot.setBorder(null);
    txtDescuentoTot.setEditable(false);
    txtDescuentoTot.setBackground(Color.DARK_GRAY);
    this.txtDescuentoTot.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            ModificarPrecioVenta2.this.focusGainedTxtDescuentoTot(e);
          }
          
          public void focusLost(FocusEvent e) {
            ModificarPrecioVenta2.this.focusLostTxtDescuentoTot(e);
          }
        });
    this.txtDescuentoTot.addKeyListener(new KeyAdapter() {
          public void keyReleased(KeyEvent e) {
            ModificarPrecioVenta2.this.keyReleasedTxtDescuentoTot(e);
          }
        });
    this.txtDescuentoTot.setForeground(new Color(102, 205, 170));
    this.txtDescuentoTot.setText("0.0");
    this.txtDescuentoTot.setFont(new Font("Tahoma", 1, 16));
    this.txtDescuentoTot.setColumns(10);
    this.txtDescuentoTot.setBounds(663, 141, 69, 31);
    this.contentPane.add(this.txtDescuentoTot);
    this.lblS = new JLabel("S/");
    lblS.setForeground(Color.WHITE);
    this.lblS.setVerticalAlignment(3);
    this.lblS.setHorizontalAlignment(SwingConstants.LEFT);
    this.lblS.setFont(new Font("Candara", 1, 20));
    this.lblS.setBounds(50, 309, 33, 31);
    this.contentPane.add(this.lblS);
    this.label_3 = new JLabel("S/");
    label_3.setForeground(Color.WHITE);
    this.label_3.setVerticalAlignment(3);
    this.label_3.setHorizontalAlignment(SwingConstants.LEFT);
    this.label_3.setFont(new Font("Candara", Font.BOLD, 22));
    this.label_3.setBounds(70, 229, 24, 31);
    this.contentPane.add(this.label_3);
    
    JLabel lblS_1 = new JLabel("S/");
    lblS_1.setVerticalAlignment(SwingConstants.BOTTOM);
    lblS_1.setHorizontalAlignment(SwingConstants.LEFT);
    lblS_1.setForeground(new Color(205, 92, 92));
    lblS_1.setFont(new Font("Candara", Font.BOLD, 25));
    lblS_1.setBounds(489, 227, 61, 32);
    contentPane.add(lblS_1);
    
    btnBasura = new JButton("");
    btnBasura.addActionListener(this);
    btnBasura.setBackground(Color.DARK_GRAY);
    btnBasura.setBounds(546, 316, 124, 102);
    contentPane.add(btnBasura);
    Image imgLogo = new ImageIcon(this.getClass().getResource("/imgbasura.png")).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
    btnBasura.setIcon(new ImageIcon(imgLogo));
    
    btnUno = new JButton("1");
    btnUno.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnUno(e);
    	}
    });
    btnUno.setForeground(Color.WHITE);
    btnUno.setFont(new Font("Arial", Font.BOLD, 16));
    btnUno.setBackground(new Color(220, 20, 60));
    btnUno.setBounds(241, 139, 55, 43);
    contentPane.add(btnUno);
    
    btnDos = new JButton("2");
    btnDos.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnDos(e);
    	}
    });
    btnDos.setForeground(Color.WHITE);
    btnDos.setFont(new Font("Arial", Font.BOLD, 16));
    btnDos.setBackground(new Color(220, 20, 60));
    btnDos.setBounds(310, 139, 55, 43);
    contentPane.add(btnDos);
    
    btnTres = new JButton("3");
    btnTres.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnTres(e);
    	}
    });
    btnTres.setForeground(Color.WHITE);
    btnTres.setFont(new Font("Arial", Font.BOLD, 16));
    btnTres.setBackground(new Color(220, 20, 60));
    btnTres.setBounds(382, 139, 55, 43);
    contentPane.add(btnTres);
    
    btnCuatro = new JButton("4");
    btnCuatro.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnCuatro(e);
    	}
    });
    btnCuatro.setForeground(Color.WHITE);
    btnCuatro.setFont(new Font("Arial", Font.BOLD, 16));
    btnCuatro.setBackground(new Color(220, 20, 60));
    btnCuatro.setBounds(241, 193, 55, 43);
    contentPane.add(btnCuatro);
    
    btnCinco = new JButton("5");
    btnCinco.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnCinco(e);
    	}
    });
    btnCinco.setForeground(Color.WHITE);
    btnCinco.setFont(new Font("Arial", Font.BOLD, 16));
    btnCinco.setBackground(new Color(220, 20, 60));
    btnCinco.setBounds(310, 193, 55, 43);
    contentPane.add(btnCinco);
    
    btnSeis = new JButton("6");
    btnSeis.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnSeis(e);
    	}
    });
    btnSeis.setForeground(Color.WHITE);
    btnSeis.setFont(new Font("Arial", Font.BOLD, 16));
    btnSeis.setBackground(new Color(220, 20, 60));
    btnSeis.setBounds(382, 193, 55, 43);
    contentPane.add(btnSeis);
    
    btnSiete = new JButton("7");
    btnSiete.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnSiete(e);
    	}
    });
    btnSiete.setForeground(Color.WHITE);
    btnSiete.setFont(new Font("Arial", Font.BOLD, 16));
    btnSiete.setBackground(new Color(220, 20, 60));
    btnSiete.setBounds(241, 247, 55, 43);
    contentPane.add(btnSiete);
    
    btnOcho = new JButton("8");
    btnOcho.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnOcho(e);
    	}
    });
    btnOcho.setForeground(Color.WHITE);
    btnOcho.setFont(new Font("Arial", Font.BOLD, 16));
    btnOcho.setBackground(new Color(220, 20, 60));
    btnOcho.setBounds(310, 247, 55, 43);
    contentPane.add(btnOcho);
    
    btnNueve = new JButton("9");
    btnNueve.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnNueve(e);
    	}
    });
    btnNueve.setForeground(Color.WHITE);
    btnNueve.setFont(new Font("Arial", Font.BOLD, 16));
    btnNueve.setBackground(new Color(220, 20, 60));
    btnNueve.setBounds(382, 247, 55, 43);
    contentPane.add(btnNueve);
    
    btnBorrar = new JButton("Borrar");
    btnBorrar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnBorrar(e);
    	}
    });
    btnBorrar.setForeground(Color.WHITE);
    btnBorrar.setFont(new Font("Arial", Font.BOLD, 16));
    btnBorrar.setBackground(new Color(220, 20, 60));
    btnBorrar.setBounds(310, 88, 127, 43);
    contentPane.add(btnBorrar);
    
    btnCero = new JButton("0");
    btnCero.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnCero(e);
    	}
    });
    btnCero.setForeground(Color.WHITE);
    btnCero.setFont(new Font("Arial", Font.BOLD, 16));
    btnCero.setBackground(new Color(220, 20, 60));
    btnCero.setBounds(310, 297, 55, 43);
    contentPane.add(btnCero);
    
    btnPunto = new JButton(".");
    btnPunto.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		actionPerformedBtnPunto(e);
    	}
    });
    btnPunto.setForeground(Color.WHITE);
    btnPunto.setFont(new Font("Arial", Font.BOLD, 16));
    btnPunto.setBackground(new Color(220, 20, 60));
    btnPunto.setBounds(241, 297, 55, 43);
    contentPane.add(btnPunto);
    
    txtSeleccionCaja = new JTextField();
    txtSeleccionCaja.setText("0");
    txtSeleccionCaja.setBounds(118, 36, 42, 20);
    contentPane.add(txtSeleccionCaja);
    txtSeleccionCaja.setColumns(10);
    
    txtAgregarModificar = new JTextField();
    txtAgregarModificar.setText("0");
    txtAgregarModificar.setColumns(10);
    txtAgregarModificar.setBounds(118, 61, 42, 20);
    contentPane.add(txtAgregarModificar);

    
    cargar();
  }
  
  public void actionPerformed(ActionEvent arg0) {
  	if (arg0.getSource() == btnBasura) {
  		actionPerformedBtnBasura(arg0);
  	}
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
        this.nomPromo3 = rs.getString("promo3");
        this.cantPromo3 = rs.getDouble("cantp3");
        this.prePromo3 = rs.getDouble("prep3");
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
    if (!this.nomPromo3.equals("0"))
        this.cbPrecio.addItem(this.nomPromo3); 
    this.cbPrecio.setSelectedItem(this.uniMedVenta);
    this.txtTitulo.setText(this.nomProdVenta);
    this.txtCantidad.setText("" + this.cantVenta);
    this.txtNewPrecio.setText("" + this.preEnUso);
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
      
      double newPrecio = 0.0;
      newPrecio = Double.parseDouble(this.txtNewPrecio.getText());
      
      double descIndiv = 0.0;
      descIndiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
      
      double descTot = 0.0;
      descTot = Double.parseDouble(this.txtDescuentoTot.getText());
      
      double total = 0.0;
      total = Double.parseDouble(this.txtTotal.getText());
      
      double preCompra = 0.0;
      preCompra = Double.parseDouble(this.txtPreCompra.getText());
      
      if (newCant <= 0.0 || newPrecio < 0.0 || total < 0.0) {
        JOptionPane.showMessageDialog(null, "No está permitido valores negativos");
      } else {
          //this.ventas.actualizartabla(newCant, newPrecio, preCompra * newCant, total, descTot, newUnimed, this.uniMedVenta); 
    	  this.ventas.actualizartabla(newCant, newPrecio, preCompra * newCant, total, descTot, newUnimed, this.uniMedVenta);
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
      double newPrecio = Double.parseDouble(this.txtNewPrecio.getText());
      newPrecio = redondearDecimales(newPrecio, 2);
      double newcant = Double.parseDouble(this.txtCantidad.getText());
      newcant = redondearDecimales(newcant, 2);
      double precioUniEnUso = 0.0;
      
      if (this.cbPrecio.getSelectedIndex() == 0)
        precioUniEnUso = this.preEnUso; 
      if (this.cbPrecio.getSelectedIndex() == 1)
        precioUniEnUso = this.prePromo1; 
      if (this.cbPrecio.getSelectedIndex() == 2)
        precioUniEnUso = this.prePromo1;
      
      double descindiv = precioUniEnUso - newPrecio;
      descindiv = redondearDecimales(descindiv, 2);
      this.txtDescuentoIndiv.setText("" + descindiv);
      
      double desctot = descindiv * newcant;
      desctot = redondearDecimales(desctot, 2);
      this.txtDescuentoTot.setText("" + desctot);
      
      double newTot = newcant * newPrecio;
      newTot = redondearDecimales(newTot, 2);
      this.txtTotal.setText("" + newTot);

      calcular(1);
    } catch (Exception exception) {}
  }
  
  public void calcular(int origen) {
    try {
    	
      if (origen != 1) {
        double precioUniEnUso = 0.0;
        double newprecioCompra = 0.0;
        if (this.cbPrecio.getSelectedIndex() == 0) {
          precioUniEnUso = this.preEnUso;
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
        
        double newCant = 0.0;
        newCant = Double.parseDouble(this.txtCantidad.getText());
        double newPreUniSDesc = 0.0;
        newPreUniSDesc = Double.parseDouble(this.txtPUnidadOriginal.getText());
        double newPrecioModif = 0.0;
        newPrecioModif = Double.parseDouble(this.txtNewPrecio.getText());
        double newDescIndiv = 0.0;
        newDescIndiv = Double.parseDouble(this.txtDescuentoIndiv.getText());
        double newDescTot = 0.0;
        newDescTot = Double.parseDouble(this.txtDescuentoTot.getText());
        double preTotal = 0.0;
        preTotal = Double.parseDouble(this.txtTotal.getText());
        newprecioCompra = redondearDecimales(newprecioCompra, 2);
        newPreUniSDesc = redondearDecimales(newPreUniSDesc, 2);
        newPrecioModif = newPreUniSDesc - newDescIndiv;
        newPrecioModif = redondearDecimales(newPrecioModif, 2);
        preTotal = newPrecioModif * newCant;
        preTotal = redondearDecimales(preTotal, 2);
        this.txtPreCompra.setText("" + newprecioCompra);
        this.txtPUnidadOriginal.setText("" + newPreUniSDesc);
        this.txtNewPrecio.setText("" + newPrecioModif);
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
  
  protected void propertyChangeChckbxMostrar(PropertyChangeEvent evt) {}
  
  public void itemStateChanged(ItemEvent arg0) {
    if (arg0.getSource() == this.cbPrecio)
      itemStateChangedComboBox(arg0); 
  }
  
  protected void itemStateChangedComboBox(ItemEvent arg0) {
    if (this.cbPrecio.getSelectedIndex() == 0) {
      this.txtPUnidadOriginal.setText("" + this.preUniOriginal);
    }
    if (this.cbPrecio.getSelectedIndex() == 1) {
      this.txtCantidad.setText("1");
      this.txtPUnidadOriginal.setText("" + this.prePromo1);
    } 
    if (this.cbPrecio.getSelectedIndex() == 2) {
      this.txtCantidad.setText("1");
      this.txtPUnidadOriginal.setText("" + this.prePromo2);
    } 
    if (this.cbPrecio.getSelectedIndex() == 3) {
        this.txtCantidad.setText("1");
        this.txtPUnidadOriginal.setText("" + this.prePromo3);
      }
    
    this.txtDescuentoIndiv.setText("0");
    this.txtDescuentoTot.setText("0");
    
    
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
	  txtSeleccionCaja.setText("0");
	  txtAgregarModificar.setText("0");
	  seleccionarTexto(e);
  }
  
  protected void focusGainedTxtSTotal(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtDescuentoIndiv(FocusEvent e) {
    seleccionarTexto(e);
  }
  
  protected void focusGainedTxtPreCDesc(FocusEvent e) {
	txtSeleccionCaja.setText("1");
	 txtAgregarModificar.setText("0");
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
    if (this.txtNewPrecio.getText().length() == 0) {
      this.txtNewPrecio.setText("0");
      calcular(0);
    } 
  }
  
  protected void focusLostTxtDescuentoTot(FocusEvent e) {
    if (this.txtDescuentoTot.getText().length() == 0) {
      this.txtDescuentoTot.setText("0");
      calcular(0);
    } 
  }
	protected void actionPerformedBtnBasura(ActionEvent arg0) {
		
		this.ventas.eliminarFila();
	    this.ventas.setEnabled(true);
	    dispose();
		
	}
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		 
		if(txtSeleccionCaja.getText().equals("0")) {
				txtCantidad.setText("");
		}	
		else {
				txtNewPrecio.setText("");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnUno(ActionEvent e) {
		 String cantidad = txtCantidad.getText().toString();
		 String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("1");
			else
				txtCantidad.setText(cantidad+"1");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("1");
			else
				txtNewPrecio.setText(newprecio+"1");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR		
	}
	protected void actionPerformedBtnDos(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("2");
			else
				txtCantidad.setText(cantidad+"2");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("2");
			else
				txtNewPrecio.setText(newprecio+"2");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR		
	}
	protected void actionPerformedBtnTres(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("3");
			else
				txtCantidad.setText(cantidad+"3");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("3");
			else
				txtNewPrecio.setText(newprecio+"3");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnCuatro(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("4");
			else
				txtCantidad.setText(cantidad+"4");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("4");
			else
				txtNewPrecio.setText(newprecio+"4");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnCinco(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("5");
			else
				txtCantidad.setText(cantidad+"5");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("5");
			else
				txtNewPrecio.setText(newprecio+"5");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnSeis(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("6");
			else
				txtCantidad.setText(cantidad+"6");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("6");
			else
				txtNewPrecio.setText(newprecio+"6");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnSiete(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("7");
			else
				txtCantidad.setText(cantidad+"7");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("7");
			else
				txtNewPrecio.setText(newprecio+"7");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnOcho(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("8");
			else
				txtCantidad.setText(cantidad+"8");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("8");
			else
				txtNewPrecio.setText(newprecio+"8");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnNueve(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("9");
			else
				txtCantidad.setText(cantidad+"9");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("9");
			else
				txtNewPrecio.setText(newprecio+"9");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnCero(ActionEvent e) {
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("0");
			else
				txtCantidad.setText(cantidad+"0");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("0");
			else
				txtNewPrecio.setText(newprecio+"0");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
	protected void actionPerformedBtnPunto(ActionEvent e) {	
		String cantidad = txtCantidad.getText().toString();
		String newprecio = txtNewPrecio.getText().toString();
		 
		if(txtSeleccionCaja.getText().equals("0")) {
			if(txtAgregarModificar.getText().equals("0"))
				txtCantidad.setText("");
			else
				txtCantidad.setText(cantidad+".");
		}	
		else {
			if(txtAgregarModificar.getText().equals("0"))
				txtNewPrecio.setText("");
			else
				txtNewPrecio.setText(newprecio+".");
		}
		txtAgregarModificar.setText("1"); // 0=MODIFICAR TODO   1=AGREGAR	
	}
}
