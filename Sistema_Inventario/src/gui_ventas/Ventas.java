package gui_ventas;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;

import clases.AbstractJasperReports;
import clases.Cliente;
import gui_clientes.NuevoCliente;
import gui_principal.VentanaPrincipal;
import mysql.MySQLConexion;
import mysql.consultas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JRadioButtonMenuItem;

public class Ventas extends JInternalFrame {
	private JMenuBar menuBar;
	private JLabel lblCdigo;
	private JTextField txtBuscarProd;
	private JScrollPane scrollPane;
	private TextAutoCompleter acBuscador;
	private TextAutoCompleter acNotas;
	private JTable tbCarrito;
	private JLabel lblCliente;
	private JComboBox <Cliente> cbClientes;
	private JButton btnNewCliente;
	private JLabel lblNotaAdicionalDe;
	private JTextField txtInfoAdicional;
	private JLabel lblMtodoDePago;
	private JComboBox cbPago1;
	private JLabel lblTotalVentaFinal;
	private JLabel lblTitTotal;
	private JButton btnVender;
	private JTextField txtNroImpresiones;
	private JTextField txtVuelto;
	public DefaultTableModel dtm = new DefaultTableModel();
	private JMenu mnlistaDeProductos;
	private JLabel lblTitDescuento;
	private JLabel lblDescuento;
	private JTextField txtPago1;
	private JLabel lblMtodoDePago_1;
	private JComboBox cbPago2;
	private JTextField txtPago2;
	private JLabel lblS;
	private JLabel label_1;
	private JLabel lblTitTotOri;
	private JLabel lblTotOriginal;
	private JLabel lblTotalCompra;
	private JLabel lblGananciaTotal;
	private JLabel lblElVueltoDe_1;
	private JDateChooser dchFechaVenta;
	private JTextField txtHora;
	private JTextField txtMin;
	private JLabel lblHora;
	private JLabel lblMin;
	private JCheckBox chckImrpimir;

	public VentanaPrincipal vp;
	JTable tb;
	ResultSet rs = null;
	consultas consulta = new consultas();
	NuevoCliente nc = new NuevoCliente(null, this);
	int nroVentaModificar = -1;
	private JLabel lblNroCompramodificar;
	private JMenu mnlimpiarVentana;
	private JLabel lblFechaDeVenta;
	private ButtonGroup grupobuttons;
	private JRadioButton rbtnVenta;
	private JRadioButton rbtnCoti;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventas frame = new Ventas(null, -1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventas(VentanaPrincipal vp, int nroVentaModificar) {
		this.vp = vp;
		this.nroVentaModificar = nroVentaModificar;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Ventas");
		setBounds(100, 100, 1134, 669);
		getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 272, 1098, 328);
		getContentPane().add(this.scrollPane);
		
		tbCarrito = new JTable();
		tbCarrito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedTbCarrito(arg0);
			}
		});
		tbCarrito.setAutoCreateRowSorter(true);
		tbCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCarrito.setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 17));
		tbCarrito.setBackground(Color.WHITE);
		tbCarrito.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCarrito);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCarrito.getTableHeader().setReorderingAllowed(false);
		
		this.lblCdigo = new JLabel("Buscar producto:");
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setFont(new Font("Candara", Font.BOLD, 20));
		this.lblCdigo.setBounds(10, 205, 195, 23);
		getContentPane().add(this.lblCdigo);
		
		this.txtBuscarProd = new JTextField();
		txtBuscarProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtBuscarProd(e);
			}
		});
		txtBuscarProd.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		this.txtBuscarProd.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtBuscarProd.setFont(new Font("Arial", Font.ITALIC, 20));
		this.txtBuscarProd.setColumns(10);
		this.txtBuscarProd.setBackground(new Color(245, 245, 245));
		this.txtBuscarProd.setBounds(10, 227, 404, 34);
		getContentPane().add(this.txtBuscarProd);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.DARK_GRAY);
		lblCliente.setFont(new Font("Candara", Font.BOLD, 20));
		lblCliente.setBounds(10, 34, 101, 23);
		getContentPane().add(lblCliente);
		
		cbClientes = new JComboBox();
		cbClientes.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbClientes.setBackground(new Color(245, 245, 245));
		cbClientes.setFont(new Font("Arial", Font.ITALIC, 18));
		cbClientes.setBounds(10, 58, 346, 35);
		getContentPane().add(cbClientes);
		
		btnNewCliente = new JButton("+");
		btnNewCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnNewCliente(arg0);
			}
		});
		btnNewCliente.setForeground(Color.WHITE);
		btnNewCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewCliente.setBackground(new Color(30, 144, 255));
		btnNewCliente.setBounds(358, 58, 56, 35);
		getContentPane().add(btnNewCliente);
		
		lblNotaAdicionalDe = new JLabel("Nota adicional de la venta:");
		lblNotaAdicionalDe.setForeground(Color.DARK_GRAY);
		lblNotaAdicionalDe.setFont(new Font("Candara", Font.BOLD, 20));
		lblNotaAdicionalDe.setBounds(10, 115, 404, 23);
		getContentPane().add(lblNotaAdicionalDe);
		
		txtInfoAdicional = new JTextField();
		txtInfoAdicional.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtInfoAdicional.setHorizontalAlignment(SwingConstants.LEFT);
		txtInfoAdicional.setForeground(SystemColor.windowBorder);
		txtInfoAdicional.setFont(new Font("Arial", Font.ITALIC, 18));
		txtInfoAdicional.setColumns(10);
		txtInfoAdicional.setBackground(new Color(245, 245, 245));
		txtInfoAdicional.setBounds(10, 138, 404, 34);
		getContentPane().add(txtInfoAdicional);
		
		lblMtodoDePago = new JLabel("Paga con:");
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago.setBounds(844, 44, 147, 23);
		getContentPane().add(lblMtodoDePago);
		
		cbPago1 = new JComboBox();
		cbPago1.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbPago1.setBackground(new Color(245, 245, 245));
		cbPago1.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta", "Transferencia", "Dep\u00F3sito", "YAPE/PLIN"}));
		cbPago1.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago1.setBounds(844, 68, 153, 35);
		getContentPane().add(cbPago1);
		
		lblTotalVentaFinal = new JLabel("0");
		lblTotalVentaFinal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalVentaFinal.setForeground(new Color(50, 205, 50));
		lblTotalVentaFinal.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotalVentaFinal.setBackground(new Color(50, 205, 50));
		lblTotalVentaFinal.setBounds(671, 136, 101, 36);
		getContentPane().add(lblTotalVentaFinal);
		
		lblTitTotal = new JLabel("TOTAL S/ ");
		lblTitTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotal.setForeground(new Color(50, 205, 50));
		lblTitTotal.setFont(new Font("Candara", Font.BOLD, 30));
		lblTitTotal.setBackground(new Color(50, 205, 50));
		lblTitTotal.setBounds(497, 136, 164, 36);
		getContentPane().add(lblTitTotal);
		
		btnVender = new JButton("FINALIZAR");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVender(e);
			}
		});
		btnVender.setForeground(Color.WHITE);
		btnVender.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVender.setBackground(new Color(50, 205, 50));
		btnVender.setBounds(844, 227, 264, 34);
		getContentPane().add(btnVender);
		
		txtNroImpresiones = new JTextField();
		txtNroImpresiones.setVisible(false);
		txtNroImpresiones.setText("1");
		txtNroImpresiones.setHorizontalAlignment(SwingConstants.CENTER);
		txtNroImpresiones.setForeground(Color.BLACK);
		txtNroImpresiones.setFont(new Font("Arial", Font.BOLD, 15));
		txtNroImpresiones.setColumns(10);
		txtNroImpresiones.setBackground(Color.ORANGE);
		txtNroImpresiones.setBounds(1076, 11, 32, 18);
		getContentPane().add(txtNroImpresiones);
		
		txtVuelto = new JTextField();
		txtVuelto.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(new Color(30, 144, 255));
		txtVuelto.setFont(new Font("Arial", Font.ITALIC, 20));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBackground(new Color(245, 245, 245));
		txtVuelto.setBounds(1007, 147, 101, 34);
		getContentPane().add(txtVuelto);
		
		lblTitDescuento = new JLabel("Descuento S/");
		lblTitDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitDescuento.setForeground(new Color(102, 205, 170));
		lblTitDescuento.setFont(new Font("Candara", Font.BOLD, 20));
		lblTitDescuento.setBackground(new Color(50, 205, 50));
		lblTitDescuento.setBounds(497, 90, 153, 30);
		getContentPane().add(lblTitDescuento);
		
		lblDescuento = new JLabel("0");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setForeground(new Color(102, 205, 170));
		lblDescuento.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDescuento.setBackground(new Color(50, 205, 50));
		lblDescuento.setBounds(671, 90, 101, 30);
		getContentPane().add(lblDescuento);
		
		txtPago1 = new JTextField();
		txtPago1.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPago1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPago1(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPago1(e);
			}
		});
		txtPago1.setText("0");
		txtPago1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtPago1(arg0);
			}
		});
		txtPago1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPago1.setForeground(SystemColor.windowBorder);
		txtPago1.setFont(new Font("Arial", Font.ITALIC, 18));
		txtPago1.setColumns(10);
		txtPago1.setBackground(new Color(245, 245, 245));
		txtPago1.setBounds(1007, 68, 101, 34);
		getContentPane().add(txtPago1);
		
		lblMtodoDePago_1 = new JLabel("Pago 2:");
		lblMtodoDePago_1.setVisible(false);
		lblMtodoDePago_1.setForeground(Color.DARK_GRAY);
		lblMtodoDePago_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago_1.setBounds(276, 170, 101, 25);
		getContentPane().add(lblMtodoDePago_1);
		
		cbPago2 = new JComboBox();
		cbPago2.setVisible(false);
		cbPago2.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbPago2.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbPago2.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago2.setBackground(new Color(245, 245, 245));
		cbPago2.setBounds(276, 199, 101, 18);
		getContentPane().add(cbPago2);
		
		txtPago2 = new JTextField();
		txtPago2.setVisible(false);
		txtPago2.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtPago2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtPago2(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtPago2(e);
			}
		});
		txtPago2.setText("0");
		txtPago2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtPago2(arg0);
			}
		});
		txtPago2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPago2.setForeground(SystemColor.windowBorder);
		txtPago2.setFont(new Font("Arial", Font.ITALIC, 18));
		txtPago2.setColumns(10);
		txtPago2.setBackground(new Color(245, 245, 245));
		txtPago2.setBounds(382, 200, 32, 17);
		getContentPane().add(txtPago2);
		
		lblS = new JLabel("S/");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setForeground(Color.DARK_GRAY);
		lblS.setFont(new Font("Candara", Font.BOLD, 20));
		lblS.setBounds(1041, 34, 32, 34);
		getContentPane().add(lblS);
		
		label_1 = new JLabel("S/");
		label_1.setVisible(false);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1.setBounds(382, 170, 32, 25);
		getContentPane().add(label_1);
		
		lblTitTotOri = new JLabel("Total original S/ ");
		lblTitTotOri.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotOri.setForeground(new Color(102, 205, 170));
		lblTitTotOri.setFont(new Font("Candara", Font.BOLD, 20));
		lblTitTotOri.setBackground(new Color(50, 205, 50));
		lblTitTotOri.setBounds(497, 48, 153, 34);
		getContentPane().add(lblTitTotOri);
		
		lblTotOriginal = new JLabel("0");
		lblTotOriginal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotOriginal.setForeground(new Color(102, 205, 170));
		lblTotOriginal.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTotOriginal.setBackground(new Color(50, 205, 50));
		lblTotOriginal.setBounds(671, 48, 101, 34);
		getContentPane().add(lblTotOriginal);
		
		lblTotalCompra = new JLabel("0");
		lblTotalCompra.setVisible(false);
		lblTotalCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCompra.setForeground(new Color(30, 144, 255));
		lblTotalCompra.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTotalCompra.setBackground(new Color(50, 205, 50));
		lblTotalCompra.setBounds(889, 0, 68, 17);
		getContentPane().add(lblTotalCompra);
		
		lblGananciaTotal = new JLabel("0");
		lblGananciaTotal.setVisible(false);
		lblGananciaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblGananciaTotal.setForeground(new Color(30, 144, 255));
		lblGananciaTotal.setFont(new Font("Calibri", Font.BOLD, 25));
		lblGananciaTotal.setBackground(new Color(50, 205, 50));
		lblGananciaTotal.setBounds(967, 0, 68, 17);
		getContentPane().add(lblGananciaTotal);
		
		lblElVueltoDe_1 = new JLabel("SU VUELTO ES:");
		lblElVueltoDe_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblElVueltoDe_1.setForeground(new Color(30, 144, 255));
		lblElVueltoDe_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblElVueltoDe_1.setBounds(844, 148, 176, 34);
		getContentPane().add(lblElVueltoDe_1);
		
		dchFechaVenta = new JDateChooser();
		dchFechaVenta.setBounds(466, 227, 151, 34);
		getContentPane().add(dchFechaVenta);
		
		txtHora = new JTextField();
		txtHora.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				focusGainedTxtHora(arg0);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtHora(e);
			}
		});
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setText("00");
		txtHora.setBounds(627, 227, 53, 34);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		txtMin = new JTextField();
		txtMin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				focusGainedTxtMin(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				focusLostTxtMin(e);
			}
		});
		txtMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtMin.setText("00");
		txtMin.setColumns(10);
		txtMin.setBounds(690, 227, 56, 34);
		getContentPane().add(txtMin);
		
		lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(627, 210, 53, 14);
		getContentPane().add(lblHora);
		
		lblMin = new JLabel("Min");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(690, 210, 56, 14);
		getContentPane().add(lblMin);
		
		lblNroCompramodificar = new JLabel("0");
		lblNroCompramodificar.setVisible(false);
		lblNroCompramodificar.setHorizontalAlignment(SwingConstants.LEFT);
		lblNroCompramodificar.setForeground(new Color(102, 205, 170));
		lblNroCompramodificar.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNroCompramodificar.setBackground(new Color(50, 205, 50));
		lblNroCompramodificar.setBounds(795, 0, 63, 18);
		getContentPane().add(lblNroCompramodificar);
		
		lblFechaDeVenta = new JLabel("Fecha de la venta:");
		lblFechaDeVenta.setForeground(Color.DARK_GRAY);
		lblFechaDeVenta.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaDeVenta.setBounds(466, 205, 164, 23);
		getContentPane().add(lblFechaDeVenta);
		
		chckImrpimir = new JCheckBox("\u00BFImprimir?");
		chckImrpimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckImrpimir.setSelected(true);
		chckImrpimir.setHorizontalAlignment(SwingConstants.RIGHT);
		chckImrpimir.setBackground(SystemColor.window);
		chckImrpimir.setBounds(1007, 203, 101, 23);
		getContentPane().add(chckImrpimir);
		
		rbtnCoti = new JRadioButton("COTIZACI\u00D3N");
		rbtnCoti.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtnCoti.setBackground(Color.WHITE);
		rbtnCoti.setBounds(914, 204, 106, 23);
		getContentPane().add(rbtnCoti);
		
		grupobuttons = new ButtonGroup();
		grupobuttons.add(rbtnCoti);
		
		rbtnVenta = new JRadioButton("VENTA");
		rbtnVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbtnVenta.setBackground(Color.WHITE);
		rbtnVenta.setBounds(844, 205, 68, 23);
		getContentPane().add(rbtnVenta);
		grupobuttons.add(rbtnVenta);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBuscarProd, cbClientes, btnNewCliente, txtInfoAdicional, cbPago1, txtPago1, cbPago2, txtPago2, btnVender, dchFechaVenta, txtHora, txtMin}));
		
		mnlistaDeProductos = new JMenu("|Ver lista completa de productos| ");
		mnlistaDeProductos.setVisible(false);
		this.mnlistaDeProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnlistaDeProductos(arg0);
			}
		});
		this.mnlistaDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedMnlistaDeProductos(arg0);
			}
		});
		mnlistaDeProductos.setForeground(new Color(30, 144, 255));
		mnlistaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnlistaDeProductos.setBackground(SystemColor.menu);
		menuBar.add(mnlistaDeProductos);
		
		mnlimpiarVentana = new JMenu("|Limpiar ventana| ");
		mnlimpiarVentana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnlimpiarVentana(e);
			}
		});
		mnlimpiarVentana.setForeground(new Color(240, 128, 128));
		mnlimpiarVentana.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnlimpiarVentana.setBackground(SystemColor.menu);
		menuBar.add(mnlimpiarVentana);
		
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
		ajustarAnchoColumnas();
	}
	
	public void cargar() {
		tbCarrito.setRowHeight(35);
		lblNroCompramodificar.setText(""+nroVentaModificar);
		rbtnVenta.setSelected(true);
		
		
		Cliente cliente = new Cliente();
		cliente.cargarClientes(cbClientes);
		
		tbCarrito.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Precio Unitario", "Descuento", "SubTotal", "IDPROD", "PC", "Stock", "Pre Indiv C/Desc"});
	
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(239, 198, 46));
		for (int i = 0; i < tbCarrito.getModel().getColumnCount(); i++)
			tbCarrito.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		
		
			try {
				consulta.iniciar();
				rs = consulta.cargarConfiguraciones();
				rs.next();
				int fechaVauto = rs.getInt("fechaVauto");
				if(fechaVauto == 0){
					lblFechaDeVenta.setVisible(false);
					dchFechaVenta.setVisible(false);
					txtHora.setVisible(false);
					txtMin.setVisible(false);
					lblHora.setVisible(false);
					lblMin.setVisible(false);
				}
				else if (fechaVauto == 1){
					lblFechaDeVenta.setVisible(true);
					dchFechaVenta.setVisible(true);
					txtHora.setVisible(true);
					txtMin.setVisible(true);
					lblHora.setVisible(true);
					lblMin.setVisible(true);
					
					java.util.Date date = new Date();
					date.getTime();
					dchFechaVenta.setDate(date);
				}
					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al cargar permisos de modificación de fecha para venta " + e);
			}
			finally {
				try {
					if (rs != null)
						rs.close();
					if (consulta != null)
						consulta.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}
		
			
			// ACÁ ENTRA SI ES UNA VENTA PARA MODIFICAR
		
			if(nroVentaModificar != -1){
			consulta.iniciar();
			ResultSet rsVD = consulta.cargarVentaDetalles(nroVentaModificar);
			try {
				int cont = 0;
				while (rsVD.next()) {// "Cantidad", "Producto y detalles", "Pre Indiv Ori", "Desc tot aplicado", "SubTotal", "IDPROD", "PC", "Stock", "Pre Indiv C/Desc" 
					
					try {
						int codproducto = rsVD.getInt("codproducto");
						txtBuscarProd.setText("(" + codproducto+")");
						AgregarProductoATabla();
						float cantidad = rsVD.getFloat("cantidad");
							tbCarrito.setValueAt(cantidad, cont, 0);
						float preVeOri = rsVD.getFloat("precioVe");
							tbCarrito.setValueAt(preVeOri, cont, 2);	
						float descTotal = rsVD.getFloat("descTotal");
							tbCarrito.setValueAt(descTotal, cont, 3);	
						float subTotal = rsVD.getFloat("subTotal");
							tbCarrito.setValueAt(subTotal, cont, 4);
						float preVeSDInd = rsVD.getFloat("preVeSDInd");
							tbCarrito.setValueAt(preVeSDInd, cont, 8);
						
						String newUniMed = rsVD.getString("uMedidaUsada");
						String prodCompletoLista = dtm.getValueAt(cont, 1).toString();					
						String oldUniMed = prodCompletoLista.substring(prodCompletoLista.indexOf("(")+1, prodCompletoLista.indexOf(")"));
						String newNomProd = prodCompletoLista.replaceAll(oldUniMed, newUniMed);
						tbCarrito.setValueAt(newNomProd, cont, 1);
						
						if(!newUniMed.equals(oldUniMed)){
							consulta.iniciar();
							ResultSet rsBP = consulta.buscarProductoID(codproducto);
							try {
								rsBP.next();
								String nomPromo1 = rsBP.getString("promo1");
								double cantPromo1 = rsBP.getDouble("cantp1");
								String nomPromo2 = rsBP.getString("promo2");
								double cantPromo2 = rsBP.getDouble("cantp2");
								if(newUniMed.equals(nomPromo1)){
									double stock = 0;
									stock = Float.parseFloat( tbCarrito.getValueAt(cont, 7).toString());
									stock = stock + cantPromo1;
									tbCarrito.setValueAt(stock, cont, 7);
								}
								else if (newUniMed.equals(nomPromo2)){
									double stock = 0;
									stock = Float.parseFloat( tbCarrito.getValueAt(cont, 7).toString());
									stock = stock + cantPromo2;
									tbCarrito.setValueAt(stock, cont, 7);
								}								
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error al verificar unidades de medida " + e);
							}
						}
						else{
							float stock = 0;
							stock = Float.parseFloat( tbCarrito.getValueAt(cont, 7).toString());
							stock = stock + cantidad;
							tbCarrito.setValueAt(stock, cont, 7);
						}

						cont++;
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al cargar detalles venta: " + e);
			}	finally {
				try {
					if (rsVD != null)
						rsVD.close();
					if (consulta != null)
						consulta.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}
			
			
			try {
				consulta.iniciar();
				rs = consulta.cargarVenta(nroVentaModificar);
				rs.next();
				int idCliente = rs.getInt("idcliente");
				for(int i= 0; i<cbClientes.getItemCount(); i++)
					if(cbClientes.getItemAt(i).getId() == idCliente)
						cbClientes.setSelectedIndex(i);
				String notaVenta = rs.getString("nota");
					txtInfoAdicional.setText(notaVenta);
				int metPago1 = rs.getInt("metpago1");
				float montPago1 = rs.getFloat("montPago1");
				int metPago2 = rs.getInt("metpago2");
				float montPago2 = rs.getFloat("montPago2");
					cbPago1.setSelectedIndex(metPago1);
					cbPago2.setSelectedIndex(metPago2);
					txtPago1.setText(""+montPago1);
					txtPago2.setText(""+montPago2);
			} catch (Exception e) {
				// TODO: handle exception
			} 	finally {
				try {
					if (rs != null)
						rs.close();
					if (consulta != null)
						consulta.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}

			sumarSubTotales();
			sumarTotalGenerales();

			calcularVuelto();
		}
		
	}
	
	public void cargarBuscador() {
		acBuscador = new TextAutoCompleter(txtBuscarProd);
		acNotas = new TextAutoCompleter(txtInfoAdicional);
		
		consulta.iniciar();
		rs = consulta.cargarProductos();
		acBuscador.setMode(0);
		acNotas.setMode(0);
		try {
			while (rs.next()) {
				acBuscador.addItem(rs.getString("cantidad") + " " + rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
						" = S/" + rs.getString("precioVe") + "  -  (" + rs.getString("codproducto") + ")");
			}
			
			acNotas.addItem("5% de descuento en su siguiente compra");
			acNotas.addItem("10% de descuento en su siguiente compra");
			acNotas.addItem("15% de descuento en su siguiente compra");

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar buscador: " + e);
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}
	}
	
	private int anchoColumna(int porcentaje) {
		if(porcentaje <= 0)
			return 0;
		else
			return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCarrito.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10)); // Cantidad
		tcm.getColumn(1).setPreferredWidth(anchoColumna(50)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(16)); // Precio ORIGINAL
		tcm.getColumn(3).setPreferredWidth(anchoColumna(16)); // Descuento
		tcm.getColumn(4).setPreferredWidth(anchoColumna(16)); // SubTotal
		tcm.getColumn(5).setPreferredWidth(anchoColumna(0)); //ID
		tcm.getColumn(6).setPreferredWidth(anchoColumna(0));//Preco
		tcm.getColumn(7).setPreferredWidth(anchoColumna(0)); // Stock
		tcm.getColumn(8).setPreferredWidth(anchoColumna(0)); // Precio
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCarrito.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(4).setCellRenderer(tcr);	
	}
	
	public void agregarCliente(int iddistrib){
		try {
			cbClientes.removeAllItems();
			Cliente cliente = new Cliente();
			cliente.cargarClientes(cbClientes);
			
			for(int i = 0; i<cbClientes.getItemCount(); i++){
				if(cbClientes.getItemAt(i).getId() == iddistrib)
					cbClientes.setSelectedIndex(i);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al recargar combo");
		}
		
	}
	
	public void seleccionarProducto(String id) {
		/*int cantProductos = tbCarrito.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (id.equals(tbCarrito.getValueAt(i, 0))) {
				tbCarrito.setRowSelectionInterval(i, i);
				break;
			}
		}*/
	}
	
	public void AgregarProductoATabla() {
		try { // SI LO QUE SE INGRESA ES UN NOMBRE DE PRODUCTO
			String nomProducto = txtBuscarProd.getText();
			txtPago1.setText("0");
			txtPago2.setText("0");
			txtVuelto.setText("0");
			int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			consulta.iniciar();
			rs = consulta.buscarProductoID(idProd);
			int flag = 0;
			float cantidad = 0;
			for (int i = 0; i < tbCarrito.getRowCount(); i++) { 
				try {
					rs.beforeFirst();
					while (rs.next()) {
						if (rs.getString("codproducto").equals(tbCarrito.getValueAt(i, 5).toString())) {// AQUÍ ENTRA SI
																										// YA EXISTE EL
																										// PRODUCTO EN
																										// LA TABLA
							cantidad = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString()) + 1;
							tbCarrito.setValueAt(cantidad, i, 0);
							flag = 1;
							txtBuscarProd.setText(null);
							txtBuscarProd.requestFocus();
							tbCarrito.setRowSelectionInterval(i, i);
						}
					}
				} catch (SQLException e) {
					//JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
			}

			if (flag == 0) { // AQUÍ ENTRA SI EL 
							//	PRODUCTO AGREGADO ES NUEVO
				try {
					rs.beforeFirst(); // "Cantidad", "Producto y detalles", "Pre Indiv Ori", "Desc tot aplicado", "SubTotal", "IDPROD", "PC", "Stock", "Pre Indiv C/Desc" 
					while (rs.next()) {
						dtm.addRow(new Object[] { 
								"1", 
								rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " (" + rs.getString("unimedida") + ")",     
								rs.getFloat("precioVe"), 
								"0",
								rs.getFloat("precioVe"), 
								rs.getInt("codproducto"), 
								rs.getFloat("precioCo"),								 
								rs.getFloat("cantidad"),
								rs.getFloat("precioVe")});
						tbCarrito.setRowSelectionInterval(tbCarrito.getRowCount() - 1, tbCarrito.getRowCount() - 1);
					}
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(null, "No existe el producto: " + e);
				}
			}
			txtBuscarProd.setText(null);
			sumarSubTotales();
			sumarTotalGenerales();

		} catch (Exception e) { // AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
			try {
				String codbarra = txtBuscarProd.getText();
				consulta.iniciar();
				rs = consulta.buscarProductoBarras(codbarra);
				int flag = 0;
				float cantidad = 0;
				for (int i = 0; i < tbCarrito.getRowCount(); i++) {
					try {// AQUÍ ENTRA SI YA EXISTE EL PRODUCTO EN LA TABLA 
						rs.beforeFirst();
						while (rs.next()) {
							if (rs.getInt("codproducto") == Integer.parseInt(tbCarrito.getValueAt(i, 5).toString())) {
								cantidad = (Float.parseFloat(tbCarrito.getValueAt(i, 0).toString()) + 1);
								tbCarrito.setValueAt(cantidad, i, 0);
								flag = 1;
								txtBuscarProd.setText(null);
								txtBuscarProd.requestFocus();
								tbCarrito.setRowSelectionInterval(i, i);
							}
						}
					} catch (SQLException ex) {
					}
				}
				if (flag == 0) { // AQUÍ ENTRA SI EL PRODUCTO AGREGADO ES NUEVO
					try {
						rs.beforeFirst();
						while (rs.next()) {
							dtm.addRow(new Object[] { 
									"1", 
									rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " (" + rs.getString("unimedida") + ")",     
									rs.getFloat("precioVe"), 
									"0", 
									rs.getFloat("precioVe"), 
									rs.getInt("codproducto"), 
									rs.getFloat("precioCo"),								 
									rs.getFloat("cantidad"),
									rs.getFloat("precioVe")});
							tbCarrito.setRowSelectionInterval(tbCarrito.getRowCount() - 1, tbCarrito.getRowCount() - 1);
						}
					} catch (Exception ex) {
					}
				}
				

				consulta.reset();
				//limpiarVentana();
				txtBuscarProd.setText(null);
				sumarSubTotales();
				sumarTotalGenerales();
			} catch (Exception e2) {
				txtBuscarProd.setText(null);
			}finally {
				try {
					if (rs != null)
						rs.close();
					if (consulta != null)
						consulta.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (consulta != null)
					consulta.reset();
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
            }
		}
	}
	
	private void limpiarVentana(){
		try {
			for (int i = 0; i < tbCarrito.getRowCount(); i++) {
				dtm.removeRow(i);
				i -= 1;
			}
			txtBuscarProd.requestFocus();
			txtBuscarProd.setText(null);
			cbClientes.setSelectedIndex(0);
			txtInfoAdicional.setText(null);
			cbPago1.setSelectedIndex(0);
			txtPago1.setText(null);
			cbPago2.setSelectedIndex(0);
			txtPago2.setText(null);
			
			lblDescuento.setText(null);
			lblGananciaTotal.setText(null);
			lblTotalCompra.setText(null);
			lblTotOriginal.setText(null);
			lblTotalVentaFinal.setText(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione producto a eliminar");
		}
		
	}
	
	
	public void sumarSubTotales() { // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			try {
				double cant = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
				double preCDesc = Float.parseFloat(tbCarrito.getValueAt(i, 8).toString());
				double desc = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
				double subt = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
				
				subt = (cant * preCDesc);
				subt = redondearDecimales(subt, 1);
				
				tbCarrito.setValueAt(subt, i, 4);
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
		}
	}

	public void sumarTotalGenerales() {
		double totalCompra = 0;
		double descuento = 0;
		double ganancia = 0;
		double igv = 0;
		double totalVentaOriginal = 0;
		double totalVentaFinal = 0;
		
		if (tbCarrito.getRowCount() < 1){
			lblDescuento.setText("");
			lblGananciaTotal.setText("");
			lblTotalCompra.setText("");
			lblTotOriginal.setText("");
			lblTotalVentaFinal.setText("");
		}
		else {
			for (int i = 0; i < tbCarrito.getRowCount(); i++) {
				try {
					totalCompra = totalCompra + Float.parseFloat(tbCarrito.getValueAt(i, 6).toString());
					totalCompra = redondearDecimales(totalCompra, 2);
					
					descuento = descuento + Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
					descuento = redondearDecimales(descuento, 2);
					
					totalVentaOriginal = totalVentaOriginal + (Float.parseFloat(tbCarrito.getValueAt(i, 0).toString()) * Float.parseFloat(tbCarrito.getValueAt(i, 8).toString()));
					totalVentaOriginal = redondearDecimales(totalVentaOriginal, 1);
					
					totalVentaFinal = totalVentaFinal + Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
					totalVentaFinal = redondearDecimales(totalVentaFinal, 1);

					ganancia = totalVentaFinal - totalCompra;
					ganancia = redondearDecimales(ganancia, 2);
					
					igv = (totalVentaFinal*1.18) - totalVentaFinal;
					igv = redondearDecimales(igv, 2);
					
					
					lblDescuento.setText("" + descuento);
					lblGananciaTotal.setText("" + ganancia);
					lblTotalCompra.setText("" + totalCompra);
					lblTotOriginal.setText("" + totalVentaOriginal);
					lblTotalVentaFinal.setText("" + totalVentaFinal + "0");
					
					
					
				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
			}
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
	
	public int verificarStock() { 
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			int idProd = Integer.parseInt(tbCarrito.getValueAt(i, 5).toString());
			float cantV = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
			float stock = 0;
			try {
				consulta.iniciar();
				rs = consulta.buscarProductoID(idProd);
				rs.next();
				stock = rs.getFloat("cantidad");
				stock = Float.parseFloat(tbCarrito.getValueAt(i, 7).toString());
				String producto = rs.getString("producto");
				String detalle = rs.getString("detalles");
				String marca = rs.getString("marca");
				String color = rs.getString("color");
				if (cantV > stock) {
					tbCarrito.setRowSelectionInterval(i, i);
					JOptionPane.showMessageDialog(null,
							"Stock insuficiente de: " + producto + " " + detalle + " " + marca + " " + color);
					return 0; //NO HAY STOCK
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR al verificar stock para venta: " + e);
			}
			finally {
				try {
					if (rs != null)
						rs.close();
					if (consulta != null)
						consulta.reset();
	            } catch (Exception ex) {
	            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
	            }
			}
		}
		return 1; //SI HAY STOCK
	}
	
	protected void actionPerformedBtnVender(ActionEvent e) {
		int opc = 0;
		
		if(rbtnVenta.isSelected())
			opc = JOptionPane.showConfirmDialog(null, "¿Realizar venta?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(rbtnCoti.isSelected())
			opc = JOptionPane.showConfirmDialog(null, "¿Realizar cotización?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		
		if (opc == 0) {
			
// VERIFICA SI ES COTIZACIÓN
			if (this.rbtnVenta.isSelected()){
			// ESTO ES VENTA
				int ventasinstock = 0; // 0NO 1SI
				int flag = 0; //Permite pasar a vender segun stock 0NO 1SI
				
				if (tbCarrito.getRowCount() < 1) {
					JOptionPane.showMessageDialog(null, "Agregue algún producto a la lista");
				} else {
					try {
						consulta.iniciar();
						rs = consulta.verificarVentaSinStock();
						rs.next();
						ventasinstock = rs.getInt("ventasinstock");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error al consultar permisos de venta sin stock " + e2);
					}finally {
						try {
							if (rs != null)
								rs.close();
							if (consulta != null)
								consulta.reset();
			            } catch (Exception ex) {
			            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			            }
					}
					
					if(ventasinstock == 0) // NO ESTÁ PERMITIDO VENDER SIN STOCK, SE DEBE VERIFICAR
						flag = verificarStock();
					else
						flag = 1;  // NO TIENE RESTRICCION Y SE VENDE SIN PROBLEMA
					
		//EMPIEZA LA VENTA
					if (flag == 1) {
						
						int idcliente = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getId();
						String nomCliente = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getNombre();
						String nroDoc = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getNrodoc();
						int idusuario = Integer.parseInt(vp.lblIdusuario.getText());
						String nota = txtInfoAdicional.getText();
						
						float totCompra = Float.parseFloat(lblTotalCompra.getText());
						float totVenta = Float.parseFloat(lblTotalVentaFinal.getText());
						float gananciaTot = Float.parseFloat(lblGananciaTotal.getText());
						float descuentoTot = Float.parseFloat(lblDescuento.getText());
						
						int metpago1 = 0;	metpago1 = cbPago1.getSelectedIndex();
						int metpago2 = 0;	metpago2 = cbPago2.getSelectedIndex();
						float monto1 = 0;	if(txtPago1.getText().length()>0) monto1 = Float.parseFloat(txtPago1.getText());
						float monto2 = 0;	if(txtPago2.getText().length()>0) monto2 = Float.parseFloat(txtPago2.getText());
						
						try {
							consulta.iniciar();
							rs = consulta.cargarConfiguraciones();
							rs.next();
							int fechaVauto = rs.getInt("fechaVauto");
							
							
							if(fechaVauto == 0){ // AQUI SI LA FECHA ES AUTOMATICA
								
								if(nroVentaModificar != -1){	//AQUI SI ES MODIFICACIÓN DE VENTA
									//consulta.modificarVenta(nroVentaModificar);
									
									consulta.modificarVenta(nroVentaModificar, idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2);
									
								}
								else{	//AQUI SI ES VENTA NUEVA
									consulta.Vender(idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2);
								}
							}
							else if (fechaVauto == 1){ // AQUI SI LA FECHA ES PERSONALIZADA
								
								int añoi = dchFechaVenta.getCalendar().get(Calendar.YEAR);
								int mesi = dchFechaVenta.getCalendar().get(Calendar.MARCH) + 1;
								int diai = dchFechaVenta.getCalendar().get(Calendar.DAY_OF_MONTH);
								String hora = txtHora.getText();
								String min = txtMin.getText();
								String fechaActualString = añoi + "-" + mesi + "-" + diai + " " + hora + ":" + min + ":00";
		
								DateFormat formatter;
								formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								Date date = (Date) formatter.parse(fechaActualString);
								Object fechaElegida = new java.sql.Timestamp(date.getTime());
								
								
									
								if(nroVentaModificar != -1){	//AQUI SI ES MODIFICACIÓN DE VENTA
									consulta.modificarVenta2(nroVentaModificar, idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2, fechaElegida);
								}
								else{	//AQUI SI ES VENTA NUEVA
									consulta.Vender2(idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2, fechaElegida);
								}
								
							}
						}catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "1er Error al verificar permiso para vender sin reducir stock " + e2);
						}
						finally {
							try {
								if (rs != null)
									rs.close();
								if (consulta != null)
									consulta.reset();
				            } catch (Exception ex) {
				            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
				            }
						}
						
						
						
										/*
										 * SE REALIZO EL REGISTRO DE LA VENTA
										 * 
										 * A CONTINUACION SE REGISTRARÁN LOS DETALLES DE ESTA
										 * 
										 * */
									
						int ultCodVenta = 0;
						
						try { // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC"
							
							if(nroVentaModificar!=-1){  // AQUI ENTRA SI ES VENTA A MODIFICAR
								ultCodVenta = nroVentaModificar;
							}
							else{
								consulta.iniciar();
								rs = consulta.ObtenerUltimoCodigo();
								try {
									while (rs.next())
										ultCodVenta = rs.getInt("codventa");
								} catch (Exception e3) {
									JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo código: " + e3);
								}finally {
									try {
										if (rs != null)
											rs.close();
										if (consulta != null)
											consulta.reset();
						            } catch (Exception ex) {
						            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						            }
								}
							}
							
							for (int i = 0; i < tbCarrito.getRowCount(); i++) {
								String productoCompleto = tbCarrito.getValueAt(i, 1).toString();
								String uMedidaUsada = productoCompleto.substring(productoCompleto.indexOf("(")+1, productoCompleto.indexOf(")"));
								double cantADisminuir = 0;
								
								
								double cantProdVenta = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
								int idProdVenta = Integer.parseInt(tbCarrito.getValueAt(i, 5).toString());
								double precioVeUniSDescVenta = Float.parseFloat(tbCarrito.getValueAt(i, 8).toString());
									precioVeUniSDescVenta = redondearDecimales(precioVeUniSDescVenta, 2);
								double descuentoTotProdVenta = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
									descuentoTotProdVenta = redondearDecimales(descuentoTotProdVenta, 2);
								double descuentoIndivProdVenta = descuentoTotProdVenta/cantProdVenta;
									descuentoIndivProdVenta = redondearDecimales(descuentoIndivProdVenta, 2);
								double subTotVenta = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
									subTotVenta = redondearDecimales(subTotVenta, 2);
								double precioCoVenta = Float.parseFloat(tbCarrito.getValueAt(i, 6).toString());
									precioCoVenta = redondearDecimales(precioCoVenta, 2);
								double gananciaProdVenta = subTotVenta - precioCoVenta;
									gananciaProdVenta = redondearDecimales(gananciaProdVenta, 2);
						
								consulta.iniciar();
								consulta.RegistarDetalleVenta(ultCodVenta, idProdVenta, cantProdVenta, precioVeUniSDescVenta, redondearDecimales((precioVeUniSDescVenta*cantProdVenta),2),
										descuentoIndivProdVenta, descuentoTotProdVenta, subTotVenta, gananciaProdVenta, uMedidaUsada);
														
								/*
								 * A CONTINUACION SE DISMINUIRÁ EL STOCK DE CADA PRODUCTO
								 * 
								 * */
								try {
									consulta.iniciar();
									rs = consulta.cargarConfiguraciones();
									rs.next();
									int reducirstock = rs.getInt("reducirstock");
									
									if(reducirstock == 1){
										try {
											rs = consulta.buscarProductoID(idProdVenta);
											rs.next();
											if(rs.getString("promo1").equals(uMedidaUsada)){
												cantADisminuir = cantProdVenta * rs.getFloat("cantp1");
												cantADisminuir = redondearDecimales(cantADisminuir, 2);
											}
											else if (rs.getString("promo2").equals(uMedidaUsada)){
												cantADisminuir = cantProdVenta * rs.getFloat("cantp2");
												cantADisminuir = redondearDecimales(cantADisminuir, 2);
											}
											else{
												cantADisminuir = cantProdVenta;
											}
											
											consulta.RealizarDescuentoStock(idProdVenta, cantADisminuir);
										} catch (Exception e2) {
											JOptionPane.showMessageDialog(null, "Error al disminuir Stock " + e2);
										}
									}
								
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "2do Error al verificar permiso para vender sin reducir stock " + e2);
								}
								finally {
									try {
										if (rs != null)
											rs.close();
										if (consulta != null)
											consulta.reset();
						            } catch (Exception ex) {
						            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
						            }
								}
							}
		
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "ERROR al registrar detalles de venta: " + e2);
						}
		
						// IMPRIMIR TICKET
						
						if(chckImrpimir.isSelected()) {
							int copias = Integer.parseInt(txtNroImpresiones.getText());
							Connection con = null;
			
							for (int i = 0; i < copias; i++) {
								try {
									Map<String, Object> parameters = new HashMap();
									parameters.put("prtNVenta", ultCodVenta);
									/*
									 * new AbstractJasperReports().createReport(
									 * con.getConn(), "rPrueba.jasper", null);
									 * AbstractJasperReports.showViewer();
									 */
									try {
										con = MySQLConexion.getConection();
										/*
										 * JasperReport reporte = (JasperReport)
										 * JRLoader.loadObjectFromFile(
										 * "bin/rComprobante.jasper"); JasperPrint
										 * jasperPrint =
										 * JasperFillManager.fillReport(reporte,
										 * parameters, con);
										 * AbstractJasperReports.showViewer();
										 * JasperPrintManager.printReport(
										 * jasperPrint, false);
										 * 
										 */
										JasperPrint impressao = JasperFillManager.fillReport(
												getClass().getClassLoader().getResourceAsStream("rComprobante80mm.jasper"),
												parameters, con);
			
										// AbstractJasperReports.showViewer();
										JasperPrintManager.printReport(impressao, false);
										/*
										 * this.setAlwaysOnTop(false);
										 * //JOptionPane.showMessageDialog(null,
										 * "VENTA CORRECTA");
										 * this.setAlwaysOnTop(true);
										 */
			
									} catch (JRException ex) {
										 JOptionPane.showMessageDialog(null,
										 "ERROR " + ex.getMessage());
										 System.err.println("Error iReport: " +
										 ex.getMessage());
									}
			
								} catch (Exception ex) {
									JOptionPane.showMessageDialog(null, "ERROR " + ex);
								}
							}
						}
						
						JOptionPane.showMessageDialog(null, "VENTA CORRECTA", "", JOptionPane.INFORMATION_MESSAGE);
						//limpiarVentana();
						//vp.abrirVentanaVentas();
						vp.actionPerformedBtnVentas(null);
						this.dispose();
						/*
						 * lblPaga.setText("Paga con: "); lblVuelto.setText(
						 * "Su vuelto es: ");
						 */
					}
//TERMINA LA VENTA
					
				}
			}
			
	
			if (this.rbtnCoti.isSelected()){
//EMPIEZA LA COTIZACIÓN					
				
				 int idcliente = ((Cliente)this.cbClientes.getItemAt(this.cbClientes.getSelectedIndex())).getId();
			        String nomCliente = ((Cliente)this.cbClientes.getItemAt(this.cbClientes.getSelectedIndex())).getNombre();
			        String nroDoc = ((Cliente)this.cbClientes.getItemAt(this.cbClientes.getSelectedIndex())).getNrodoc();
			        int idusuario = Integer.parseInt(this.vp.lblIdusuario.getText());
			        String nota = this.txtInfoAdicional.getText();
			        float totCompra = Float.parseFloat(this.lblTotalCompra.getText());
			        float totVenta = Float.parseFloat(this.lblTotalVentaFinal.getText());
			        float gananciaTot = Float.parseFloat(this.lblGananciaTotal.getText());
			        float descuentoTot = Float.parseFloat(this.lblDescuento.getText());
			        int metpago1 = 0;
			        metpago1 = this.cbPago1.getSelectedIndex();
			        int metpago2 = 0;
			        metpago2 = this.cbPago2.getSelectedIndex();
			        float monto1 = 0.0F;
			        if (this.txtPago1.getText().length() > 0)
			          monto1 = Float.parseFloat(this.txtPago1.getText()); 
			        float monto2 = 0.0F;
			        if (this.txtPago2.getText().length() > 0)
			          monto2 = Float.parseFloat(this.txtPago2.getText()); 
			        try {
			          this.consulta.iniciar();
			          this.rs = this.consulta.cargarConfiguraciones();
			          this.rs.next();
			          int fechaVauto = this.rs.getInt("fechaVauto");
			          if (fechaVauto == 0) {
			            this.consulta.Cotizar(idcliente, idusuario, totVenta, nota);
			          } else if (fechaVauto == 1) {
			            int a = this.dchFechaVenta.getCalendar().get(1);
			            int mesi = this.dchFechaVenta.getCalendar().get(2) + 1;
			            int diai = this.dchFechaVenta.getCalendar().get(5);
			            String hora = this.txtHora.getText();
			            String min = this.txtMin.getText();
			            String fechaActualString = String.valueOf(a) + "-" + mesi + "-" + diai + " " + hora + ":" + min + ":00";
			            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			            Date date = formatter.parse(fechaActualString);
			            Object fechaElegida = new Timestamp(date.getTime());
			            this.consulta.Cotizar2(idcliente, idusuario, totVenta, nota, fechaElegida);
			          } 
			        } catch (Exception e2) {
			          JOptionPane.showMessageDialog(null, "1er Error al verificar permiso para vender sin reducir stock " + e2);
			        } finally {
			          try {
			            if (this.rs != null)
			              this.rs.close(); 
			            if (this.consulta != null)
			              this.consulta.reset(); 
			          } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			          } 
			        } 
			        
			        
			        
			        
			        
			        int ultCoti = 0;
			        try {
			          this.consulta.iniciar();
			          this.rs = this.consulta.ObtenerUltimoCodigoCoti();
			          try {
			        	  rs.next();
			              ultCoti = this.rs.getInt("codcoti"); 
			          } catch (Exception e3) {
			            JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo c" + e3);
			          } finally {
			            try {
			              if (this.rs != null)
			                this.rs.close(); 
			              if (this.consulta != null)
			                this.consulta.reset(); 
			            } catch (Exception ex) {
			              JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
			            } 
			          } 
			          for (int j = 0; j < this.tbCarrito.getRowCount(); j++) {
			            String productoCompleto = this.tbCarrito.getValueAt(j, 1).toString();
			            String uMedidaUsada = productoCompleto.substring(productoCompleto.indexOf("(") + 1, productoCompleto.indexOf(")"));
			            double cantADisminuir = 0.0D;
			            double cantProdVenta = Float.parseFloat(this.tbCarrito.getValueAt(j, 0).toString());
			            int idProdVenta = Integer.parseInt(this.tbCarrito.getValueAt(j, 5).toString());
			            double precioVeUniSDescVenta = Float.parseFloat(this.tbCarrito.getValueAt(j, 8).toString());
			            precioVeUniSDescVenta = redondearDecimales(precioVeUniSDescVenta, 2);
			            double descuentoTotProdVenta = Float.parseFloat(this.tbCarrito.getValueAt(j, 3).toString());
			            descuentoTotProdVenta = redondearDecimales(descuentoTotProdVenta, 2);
			            double descuentoIndivProdVenta = descuentoTotProdVenta / cantProdVenta;
			            descuentoIndivProdVenta = redondearDecimales(descuentoIndivProdVenta, 2);
			            double subTotVenta = Float.parseFloat(this.tbCarrito.getValueAt(j, 4).toString());
			            subTotVenta = redondearDecimales(subTotVenta, 2);
			            double precioCoVenta = Float.parseFloat(this.tbCarrito.getValueAt(j, 6).toString());
			            precioCoVenta = redondearDecimales(precioCoVenta, 2);
			            double gananciaProdVenta = subTotVenta - precioCoVenta;
			            gananciaProdVenta = redondearDecimales(gananciaProdVenta, 2);
			            this.consulta.iniciar();
			            this.consulta.RegistarDetalleCoti(ultCoti, idProdVenta, cantProdVenta, precioVeUniSDescVenta, redondearDecimales(precioVeUniSDescVenta * cantProdVenta, 2), 
			                descuentoIndivProdVenta, descuentoTotProdVenta, subTotVenta, gananciaProdVenta, uMedidaUsada);
			          } 
			        } catch (Exception e2) {
			          JOptionPane.showMessageDialog(null, "ERROR al registrar detalles de coti: " + e2);
			        } 

			        JOptionPane.showMessageDialog(null, "COTIZACIÓN REALIZADA CORRECTAMENTE\n A CONTINUACIÓN SE GENERARÁ SU COMPROBANTE, PUEDE GUARDARLO EN PDF O IMPRIMIRLO", "", 1);
			        
			        Connection con = null;
			        for (int i = 0; i < 1; i++) {
			          try {
			            Map<String, Object> parameters2 = new HashMap<>();
			            parameters2.put("prtNVenta", Integer.valueOf(ultCoti));
			            try {
			              con = MySQLConexion.getConection();
			             
			              JasperPrint impressao = JasperFillManager.fillReport(
									getClass().getClassLoader().getResourceAsStream("rCotizacion80mm.jasper"),
									parameters2, con);

							JasperPrintManager.printReport(impressao, false);
							
			              
			              new AbstractJasperReports().createReport(con, "rCotizacion80mm.jasper", parameters2);
							AbstractJasperReports.showViewer();
			              
			              
			            } catch (Exception ex) {
			              JOptionPane.showMessageDialog(null, 
			                  "ERROR " + ex.getMessage());
			              System.err.println("Errorrrr iReport: " + 
			                  ex.getMessage());
			            } 
			          } catch (Exception ex) {
			            JOptionPane.showMessageDialog(null, "ERROR al generar reporte de cotizacion" + ex);
			          } 
			        } 
			        this.vp.actionPerformedBtnVentas(null);
			        dispose();
				
				
			}
		}
	}
	
	protected void keyTypedTxtBuscarProd(KeyEvent e) {
		char c = e.getKeyChar();
		
		if (c == (char) KeyEvent.VK_ENTER)
			if(txtBuscarProd.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Escriba el producto que desee vender");
			else
				AgregarProductoATabla();
	}
	
	protected void actionPerformedBtnNewCliente(ActionEvent arg0) {
		try {
			if (nc.isShowing()) {
				// JOptionPane.showMessageDialog(null, "Ya tiene abierta la
				// ventana");
				nc.setExtendedState(0); // MOSTRAR VENTANA ABIERTA
				nc.setVisible(true);
			} else {
				nc = new NuevoCliente(null, this);
				nc.setLocationRelativeTo(null);
				nc.setVisible(true);
			}
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "ErrorC: " + f);
		}
	}
	
	protected void mouseClickedTbCarrito(MouseEvent arg0) {
		// "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
		
		int idProd = Integer.parseInt(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 5).toString());
		double cantActual = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 0).toString());
		String nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		double preCDesc = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 8).toString());
		double descT = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 3).toString());
		double subT = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 4).toString());
		double preC = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 6).toString());
		
		String unimedida = nomProd.substring(nomProd.indexOf("(")+1, nomProd.indexOf(")"));

		this.setEnabled(false);
		ModificarPrecioVenta2 cp = new ModificarPrecioVenta2(this, idProd, nomProd, unimedida, cantActual, preCDesc, subT, preC, descT);
		cp.setVisible(true);
		
		int fila = tbCarrito.getSelectedRow();
		tbCarrito.setRowSelectionInterval(fila, fila);
	}
	
	public void actualizartabla(double cant, double preCDesc, double preo, double pret, double desc, String newUniMed, String oldUniMed) {
		String nomProd = null;
		nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		String newNomProd = nomProd.replaceAll(oldUniMed, newUniMed);
		
		tbCarrito.setValueAt(redondearDecimales(cant, 2), 		tbCarrito.getSelectedRow(), 0);
		tbCarrito.setValueAt(newNomProd,						tbCarrito.getSelectedRow(), 1);
		tbCarrito.setValueAt(redondearDecimales(preCDesc, 2), 	tbCarrito.getSelectedRow(), 8);
		tbCarrito.setValueAt(redondearDecimales(preo, 2), 		tbCarrito.getSelectedRow(), 6);
		tbCarrito.setValueAt(redondearDecimales(pret, 2), 		tbCarrito.getSelectedRow(), 4);
		tbCarrito.setValueAt(redondearDecimales(desc, 2), 		tbCarrito.getSelectedRow(), 3);
		sumarSubTotales();
		sumarTotalGenerales();
	}
	
	public void eliminarFila() {
		try {
			dtm.removeRow(tbCarrito.getSelectedRow());
			sumarSubTotales();
			sumarTotalGenerales();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione producto a eliminar");
		}
	}
	protected void keyReleasedTxtPago1(KeyEvent arg0) {
		calcularVuelto();
	}
	protected void keyReleasedTxtPago2(KeyEvent arg0) {
		calcularVuelto();
	}
	private void calcularVuelto(){
		try {
			double pagacon1 = Float.parseFloat(txtPago1.getText());
			double pagacon2 = Float.parseFloat(txtPago2.getText());
			double sumaPagos = pagacon1 + pagacon2;
				sumaPagos = redondearDecimales(sumaPagos, 2);
			double tot = Float.parseFloat(lblTotalVentaFinal.getText());
			double vuelto = redondearDecimales(sumaPagos - tot, 2);
			
			if (vuelto < 0){
				txtVuelto.setText("0.00");
			}
			else{
				txtVuelto.setText("" + vuelto + "0");
			}
		} catch (Exception e2) {
			txtVuelto.setText("0.00");
		}
	}
	private void seleccionarTexto(FocusEvent e){
		Object o = e.getSource();
        if(o instanceof javax.swing.JTextField){
            javax.swing.JTextField txt = (javax.swing.JTextField) o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
	}
	protected void focusGainedTxtPago1(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtPago2(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusLostTxtPago1(FocusEvent e) {
		if(txtPago1.getText().length()==0){
			txtPago1.setText("0");
			calcularVuelto();
		}
	}
	protected void focusLostTxtPago2(FocusEvent e) {
		if(txtPago2.getText().length()==0){
			txtPago2.setText("0");
			calcularVuelto();
		}
	}
	
	protected void actionPerformedMnlistaDeProductos(ActionEvent arg0) {
		
	}
	
	public int AnadirProductosdeListaCompleta(String codigo, String cantidad) {
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			if (codigo.equals(tbCarrito.getValueAt(i, 5).toString())) {
				float temp = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
				float temp2 = Float.parseFloat(cantidad);
				
				float suma = temp + temp2;
				tbCarrito.setValueAt(suma, i, 0);
				txtBuscarProd.requestFocus();
				tbCarrito.setRowSelectionInterval(i, i);
				return 0;
			}
		}
		return 1; // 1 = NO ENCONTRÓ COINCIDENCIA
	}
	public void seleccionarRow() {
		int cant = tbCarrito.getRowCount() - 1;
		tbCarrito.setRowSelectionInterval(cant, cant);
	}
	protected void mouseClickedMnlistaDeProductos(MouseEvent arg0) {
		ListaDeProductos lp = new ListaDeProductos(this);
		lp.setLocationRelativeTo(null);
		lp.setVisible(true);
		this.setEnabled(false);
	}
	protected void focusGainedTxtHora(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusGainedTxtMin(FocusEvent e) {
		seleccionarTexto(e);
	}
	protected void focusLostTxtHora(FocusEvent e) {
		if(txtHora.getText().length()==0)
			txtHora.setText("00");
	}
	protected void focusLostTxtMin(FocusEvent e) {
		if(txtMin.getText().length()==0)
			txtMin.setText("00");
	}
	protected void mouseClickedMnlimpiarVentana(MouseEvent e) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Desea limpiar todos los campos llenados?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			limpiarVentana();
		}
		else{
			
		}
	}
}
