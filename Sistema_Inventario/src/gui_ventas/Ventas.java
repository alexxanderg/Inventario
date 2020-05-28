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
import clases.Cliente;
import clases.Usuarios;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;

public class Ventas extends JInternalFrame {
	private JMenuBar menuBar;
	private JButton btnX;
	private JLabel lblCdigo;
	private JTextField txtBuscarProd;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
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
	private JButton btnLimpiar;
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
	private JLabel lblElVueltoDe;
	private JLabel lblIGV;
	private JLabel lblTitIgv;
	private JLabel lblTitTotOri;
	private JLabel lblTotOriginal;
	private JLabel lblTotalCompra;
	private JLabel lblGananciaTotal;
	private JLabel lblElVueltoDe_1;
	private JDateChooser dchFechaVenta;
	private JLabel lblFechaDeVenta;
	private JTextField txtHora;
	private JTextField txtMin;
	private JLabel lblHora;
	private JLabel lblMin;
	

	public VentanaPrincipal vp;
	JTable tb;
	ResultSet rs = null;
	consultas consulta = new consultas();
	NuevoCliente nc = new NuevoCliente(null, this);
	int nroVentaModificar = -1;
	private JLabel lblNroCompramodificar;

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
		
		btnX = new JButton("X");
		this.btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnX(arg0);
			}
		});
		
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
		tbCarrito.setFont(new Font("Candara", Font.BOLD | Font.ITALIC, 20));
		tbCarrito.setBackground(Color.WHITE);
		tbCarrito.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCarrito);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCarrito.getTableHeader().setReorderingAllowed(false);
		btnX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setBounds(1045, 0, 63, 30);
		getContentPane().add(btnX);
		
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
		btnNewCliente.setBackground(new Color(50, 205, 50));
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
		
		lblMtodoDePago = new JLabel("Pago 1:");
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago.setBounds(441, 34, 110, 23);
		getContentPane().add(lblMtodoDePago);
		
		cbPago1 = new JComboBox();
		cbPago1.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbPago1.setBackground(new Color(245, 245, 245));
		cbPago1.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbPago1.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago1.setBounds(441, 58, 139, 35);
		getContentPane().add(cbPago1);
		
		lblTotalVentaFinal = new JLabel("0");
		lblTotalVentaFinal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalVentaFinal.setForeground(new Color(30, 144, 255));
		lblTotalVentaFinal.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotalVentaFinal.setBackground(new Color(50, 205, 50));
		lblTotalVentaFinal.setBounds(911, 188, 197, 36);
		getContentPane().add(lblTotalVentaFinal);
		
		lblTitTotal = new JLabel("TOTAL S/ ");
		lblTitTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotal.setForeground(new Color(30, 144, 255));
		lblTitTotal.setFont(new Font("Candara", Font.BOLD, 30));
		lblTitTotal.setBackground(new Color(50, 205, 50));
		lblTitTotal.setBounds(690, 188, 211, 36);
		getContentPane().add(lblTitTotal);
		
		btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVender(e);
			}
		});
		btnVender.setForeground(Color.WHITE);
		btnVender.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVender.setBackground(new Color(0, 250, 154));
		btnVender.setBounds(908, 227, 200, 34);
		getContentPane().add(btnVender);
		
		txtNroImpresiones = new JTextField();
		txtNroImpresiones.setText("1");
		txtNroImpresiones.setHorizontalAlignment(SwingConstants.CENTER);
		txtNroImpresiones.setForeground(Color.BLACK);
		txtNroImpresiones.setFont(new Font("Arial", Font.BOLD, 15));
		txtNroImpresiones.setColumns(10);
		txtNroImpresiones.setBackground(Color.YELLOW);
		txtNroImpresiones.setBounds(890, 227, 19, 34);
		getContentPane().add(txtNroImpresiones);
		
		btnLimpiar = new JButton("Limpiar");
		this.btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnLimpiar(arg0);
			}
		});
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLimpiar.setBackground(new Color(220, 20, 60));
		btnLimpiar.setBounds(690, 227, 200, 34);
		getContentPane().add(btnLimpiar);
		
		txtVuelto = new JTextField();
		txtVuelto.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(new Color(30, 144, 255));
		txtVuelto.setFont(new Font("Arial", Font.ITALIC, 20));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBackground(new Color(245, 245, 245));
		txtVuelto.setBounds(583, 227, 82, 34);
		getContentPane().add(txtVuelto);
		
		lblTitDescuento = new JLabel("Descuento S/");
		lblTitDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitDescuento.setForeground(new Color(102, 205, 170));
		lblTitDescuento.setFont(new Font("Candara", Font.BOLD, 20));
		lblTitDescuento.setBackground(new Color(50, 205, 50));
		lblTitDescuento.setBounds(690, 142, 200, 30);
		getContentPane().add(lblTitDescuento);
		
		lblDescuento = new JLabel("0");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setForeground(new Color(102, 205, 170));
		lblDescuento.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDescuento.setBackground(new Color(50, 205, 50));
		lblDescuento.setBounds(911, 142, 197, 30);
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
		txtPago1.setBounds(583, 58, 85, 34);
		getContentPane().add(txtPago1);
		
		lblMtodoDePago_1 = new JLabel("Pago 2:");
		lblMtodoDePago_1.setForeground(Color.DARK_GRAY);
		lblMtodoDePago_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblMtodoDePago_1.setBounds(441, 115, 110, 23);
		getContentPane().add(lblMtodoDePago_1);
		
		cbPago2 = new JComboBox();
		cbPago2.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		cbPago2.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbPago2.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago2.setBackground(new Color(245, 245, 245));
		cbPago2.setBounds(441, 138, 139, 35);
		getContentPane().add(cbPago2);
		
		txtPago2 = new JTextField();
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
		txtPago2.setBounds(583, 138, 85, 34);
		getContentPane().add(txtPago2);
		
		lblS = new JLabel("S/");
		lblS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblS.setForeground(Color.DARK_GRAY);
		lblS.setFont(new Font("Candara", Font.BOLD, 20));
		lblS.setBounds(601, 24, 32, 34);
		getContentPane().add(lblS);
		
		label_1 = new JLabel("S/");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Candara", Font.BOLD, 20));
		label_1.setBounds(605, 108, 32, 34);
		getContentPane().add(label_1);
		
		lblElVueltoDe = new JLabel("S/ 0 es:");
		lblElVueltoDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblElVueltoDe.setForeground(new Color(30, 144, 255));
		lblElVueltoDe.setFont(new Font("Candara", Font.BOLD, 20));
		lblElVueltoDe.setBounds(441, 233, 130, 23);
		getContentPane().add(lblElVueltoDe);
		
		lblTitTotOri = new JLabel("Total original S/ ");
		lblTitTotOri.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotOri.setForeground(new Color(102, 205, 170));
		lblTitTotOri.setFont(new Font("Candara", Font.BOLD, 20));
		lblTitTotOri.setBackground(new Color(50, 205, 50));
		lblTitTotOri.setBounds(705, 100, 185, 34);
		getContentPane().add(lblTitTotOri);
		
		lblTotOriginal = new JLabel("0");
		lblTotOriginal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotOriginal.setForeground(new Color(102, 205, 170));
		lblTotOriginal.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTotOriginal.setBackground(new Color(50, 205, 50));
		lblTotOriginal.setBounds(911, 100, 197, 34);
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
		
		lblTitIgv = new JLabel("IGV S/");
		lblTitIgv.setVisible(false);
		lblTitIgv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitIgv.setForeground(new Color(102, 205, 170));
		lblTitIgv.setFont(new Font("Candara", Font.BOLD, 20));
		lblTitIgv.setBackground(new Color(50, 205, 50));
		lblTitIgv.setBounds(690, 34, 200, 30);
		getContentPane().add(lblTitIgv);
		
		lblIGV = new JLabel("0");
		lblIGV.setVisible(false);
		lblIGV.setHorizontalAlignment(SwingConstants.LEFT);
		lblIGV.setForeground(new Color(102, 205, 170));
		lblIGV.setFont(new Font("Calibri", Font.BOLD, 25));
		lblIGV.setBackground(new Color(50, 205, 50));
		lblIGV.setBounds(911, 33, 197, 18);
		getContentPane().add(lblIGV);
		
		lblElVueltoDe_1 = new JLabel("El vuelto de ");
		lblElVueltoDe_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblElVueltoDe_1.setForeground(new Color(30, 144, 255));
		lblElVueltoDe_1.setFont(new Font("Candara", Font.BOLD, 20));
		lblElVueltoDe_1.setBounds(441, 205, 130, 23);
		getContentPane().add(lblElVueltoDe_1);
		
		dchFechaVenta = new JDateChooser();
		dchFechaVenta.setBounds(911, 70, 94, 23);
		getContentPane().add(dchFechaVenta);
		
		lblFechaDeVenta = new JLabel("Fecha de venta: ");
		lblFechaDeVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeVenta.setForeground(new Color(102, 205, 170));
		lblFechaDeVenta.setFont(new Font("Candara", Font.BOLD, 20));
		lblFechaDeVenta.setBackground(new Color(50, 205, 50));
		lblFechaDeVenta.setBounds(705, 69, 185, 23);
		getContentPane().add(lblFechaDeVenta);
		
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
		txtHora.setBounds(1010, 75, 47, 18);
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
		txtMin.setBounds(1062, 75, 47, 18);
		getContentPane().add(txtMin);
		
		lblHora = new JLabel("hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(1010, 62, 46, 14);
		getContentPane().add(lblHora);
		
		lblMin = new JLabel("min");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(1062, 62, 46, 14);
		getContentPane().add(lblMin);
		
		lblNroCompramodificar = new JLabel("0");
		lblNroCompramodificar.setVisible(false);
		lblNroCompramodificar.setHorizontalAlignment(SwingConstants.LEFT);
		lblNroCompramodificar.setForeground(new Color(102, 205, 170));
		lblNroCompramodificar.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNroCompramodificar.setBackground(new Color(50, 205, 50));
		lblNroCompramodificar.setBounds(795, 0, 63, 18);
		getContentPane().add(lblNroCompramodificar);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBuscarProd, cbClientes, btnNewCliente, txtInfoAdicional, cbPago1, txtPago1, cbPago2, txtPago2, btnVender, btnLimpiar, btnX, dchFechaVenta, txtHora, txtMin}));
		
		mnlistaDeProductos = new JMenu("|Ver lista completa de productos| ");
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
		mnlistaDeProductos.setForeground(new Color(60, 179, 113));
		mnlistaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 20));
		mnlistaDeProductos.setBackground(SystemColor.menu);
		menuBar.add(mnlistaDeProductos);
		
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
		ajustarAnchoColumnas();
	}
	
	public void cargar() {
		tbCarrito.setRowHeight(35);
		lblNroCompramodificar.setText(""+nroVentaModificar);
		
		Cliente cliente = new Cliente();
		cliente.cargarClientes(cbClientes);
		
		tbCarrito.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Stock", "Pre Indiv C/Desc", "Desc tot aplicado", "SubTotal", "IDPROD", "PC" });
	
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
		
		
			if(nroVentaModificar != -1){
			consulta.iniciar();
			ResultSet rsVD = consulta.cargarVentaDetalles(nroVentaModificar);
			try {
				int cont = 0;
				while (rsVD.next()) {// "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
					
					try {
						int codproducto = rsVD.getInt("codproducto");
						txtBuscarProd.setText("(" + codproducto+")");
						AgregarProductoATabla();
						float cantidad = rsVD.getFloat("cantidad");
							tbCarrito.setValueAt(cantidad, cont, 0);
						float preVeSDInd = rsVD.getFloat("preVeSDInd");
							tbCarrito.setValueAt(preVeSDInd, cont, 3);
						float descTotal = rsVD.getFloat("descTotal");
							tbCarrito.setValueAt(descTotal, cont, 4);	
						float subTotal = rsVD.getFloat("subTotal");
							tbCarrito.setValueAt(subTotal, cont, 5);
						
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
									stock = Float.parseFloat( tbCarrito.getValueAt(cont, 2).toString());
									stock = stock + cantPromo1;
									tbCarrito.setValueAt(stock, cont, 2);
								}
								else if (newUniMed.equals(nomPromo2)){
									double stock = 0;
									stock = Float.parseFloat( tbCarrito.getValueAt(cont, 2).toString());
									stock = stock + cantPromo2;
									tbCarrito.setValueAt(stock, cont, 2);
								}								
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error al verificar unidades de medida " + e);
							}
						}
						else{
							float stock = 0;
							stock = Float.parseFloat( tbCarrito.getValueAt(cont, 2).toString());
							stock = stock + cantidad;
							tbCarrito.setValueAt(stock, cont, 2);
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
		ac = new TextAutoCompleter(txtBuscarProd);
		consulta.iniciar();
		rs = consulta.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				ac.addItem(rs.getString("cantidad") + " " + rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
						" = S/" + rs.getString("precioVe") + "  -  (" + rs.getString("codproducto") + ")");
			}
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
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCarrito.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(8)); // Cantidad
		tcm.getColumn(1).setPreferredWidth(anchoColumna(50)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(6)); // Stock
		tcm.getColumn(3).setPreferredWidth(anchoColumna(12)); // Precio
		tcm.getColumn(4).setPreferredWidth(anchoColumna(12)); // Descuento
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10)); // SubTotal
		tcm.getColumn(6).setPreferredWidth(anchoColumna(1)); //ID
		tcm.getColumn(7).setPreferredWidth(anchoColumna(1));//Preco
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCarrito.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(4).setCellRenderer(tcr);	
		tbCarrito.getColumnModel().getColumn(5).setCellRenderer(tcr);	
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		vp.ventas = null;
		this.dispose();
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
			int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			consulta.iniciar();
			rs = consulta.buscarProductoID(idProd);
			int flag = 0;
			float cantidad = 0;
			for (int i = 0; i < tbCarrito.getRowCount(); i++) { 
				try {
					rs.beforeFirst();
					while (rs.next()) {
						if (rs.getString("codproducto").equals(tbCarrito.getValueAt(i, 6).toString())) {// AQUÍ ENTRA SI
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
					rs.beforeFirst(); // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
					while (rs.next()) {
						dtm.addRow(new Object[] { "1", rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " (" + rs.getString("unimedida") + ")",     
								rs.getFloat("cantidad"), rs.getFloat("precioVe"), "0", rs.getFloat("precioVe"), rs.getInt("codproducto"), rs.getFloat("precioCo"),
								rs.getFloat("precioCo") });
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
							if (rs.getInt("codproducto") == Integer.parseInt(tbCarrito.getValueAt(i, 6).toString())) {
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
							dtm.addRow(new Object[] { "1", rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " (" + rs.getString("unimedida") + ")",     
									rs.getFloat("cantidad"), rs.getFloat("precioVe"), "0", rs.getFloat("precioVe"), rs.getInt("codproducto"), rs.getFloat("precioCo"),
									rs.getFloat("precioCo") });
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
			lblIGV.setText(null);
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
				double preCDesc = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
				double desc = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
				double subt = Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
				
				subt = (cant * preCDesc);
				subt = redondearDecimales(subt, 2);
				
				tbCarrito.setValueAt(subt, i, 5);
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
			lblIGV.setText("");
			lblTotalCompra.setText("");
			lblTotOriginal.setText("");
			lblTotalVentaFinal.setText("");
		}
		else {
			for (int i = 0; i < tbCarrito.getRowCount(); i++) {
				try {
					totalCompra = totalCompra + Float.parseFloat(tbCarrito.getValueAt(i, 7).toString());
					totalCompra = redondearDecimales(totalCompra, 2);
					
					descuento = descuento + Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
					descuento = redondearDecimales(descuento, 2);
					
					totalVentaOriginal = totalVentaOriginal + (Float.parseFloat(tbCarrito.getValueAt(i, 0).toString()) * Float.parseFloat(tbCarrito.getValueAt(i, 3).toString()));
					totalVentaOriginal = redondearDecimales(totalVentaOriginal, 1);
					
					totalVentaFinal = totalVentaFinal + Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
					totalVentaFinal = redondearDecimales(totalVentaFinal, 1);

					ganancia = totalVentaFinal - totalCompra;
					ganancia = redondearDecimales(ganancia, 2);
					
					igv = (totalVentaFinal*1.18) - totalVentaFinal;
					igv = redondearDecimales(igv, 2);
					
					
					lblDescuento.setText("" + descuento);
					lblGananciaTotal.setText("" + ganancia);
					lblIGV.setText("" + igv);
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
			int idProd = Integer.parseInt(tbCarrito.getValueAt(i, 6).toString());
			float cantV = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
			float stock = 0;
			try {
				consulta.iniciar();
				rs = consulta.buscarProductoID(idProd);
				rs.next();
				stock = rs.getFloat("cantidad");
				stock = Float.parseFloat(tbCarrito.getValueAt(i, 2).toString());
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
		int opc = JOptionPane.showConfirmDialog(null, "¿Realizar venta?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			
			int ventasinstock = 0; // 0NO 1SI
			int flag = 0; //permite pasar a vender segun stock 0NO 1SI
			
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
					flag = 1;
				
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
						if(fechaVauto == 0){
							if(nroVentaModificar != -1)
								consulta.modificarVenta(nroVentaModificar);
							consulta.Vender(idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2);
						}
						else if (fechaVauto == 1){
							
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
							
							if(nroVentaModificar != -1)
								consulta.modificarVenta(nroVentaModificar);
							consulta.Vender2(idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2, fechaElegida);
							
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
									 * A CONTINUACION SE REGISTRARAN LOS DETALLES DE ESTA
									 * 
									 * */
									
					int ultCodVenta = 0;
					
					try { // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC"
					
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
						
						for (int i = 0; i < tbCarrito.getRowCount(); i++) {
							

							String productoCompleto = tbCarrito.getValueAt(i, 1).toString();
							String uMedidaUsada = productoCompleto.substring(productoCompleto.indexOf("(")+1, productoCompleto.indexOf(")"));
							double cantADisminuir = 0;
							
							
							double cantProdVenta = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
							int idProdVenta = Integer.parseInt(tbCarrito.getValueAt(i, 6).toString());
							double precioVeUniSDescVenta = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
								precioVeUniSDescVenta = redondearDecimales(precioVeUniSDescVenta, 2);
							double descuentoTotProdVenta = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
								descuentoTotProdVenta = redondearDecimales(descuentoTotProdVenta, 2);
							double descuentoIndivProdVenta = descuentoTotProdVenta/cantProdVenta;
								descuentoIndivProdVenta = redondearDecimales(descuentoIndivProdVenta, 2);
							double subTotVenta = Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
								subTotVenta = redondearDecimales(subTotVenta, 2);
							double precioCoVenta = Float.parseFloat(tbCarrito.getValueAt(i, 7).toString());
								precioCoVenta = redondearDecimales(precioCoVenta, 2);
							double gananciaProdVenta = subTotVenta - precioCoVenta;
								gananciaProdVenta = redondearDecimales(gananciaProdVenta, 2);
								

							
							if(nroVentaModificar!=-1){
								try {
									consulta.iniciar();
									ResultSet rsVD = consulta.cargarVentaDetalles(nroVentaModificar);
									
									int cont = -1;
									while (rsVD.next()) {// "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
										cont++;
										int codproducto = rsVD.getInt("codproducto");
										double cantidadVendida = rsVD.getDouble("cantidad");
										consulta.reset();
										
										consulta.iniciar();
										ResultSet rsPr = consulta.buscarProductoID(codproducto);
										rsPr.next();
										double cantidadActual = rsPr.getDouble("cantidad");
										double cantTotal = cantidadVendida + cantidadActual;
										consulta.reset();
										
										consulta.iniciar();
										consulta.actualizarStock(cantTotal, codproducto);
										consulta.reset();
									}
								} catch (Exception e2) {
									// TODO: handle exception
								}
								

								consulta.iniciar();
								consulta.ModificarDetalleVenta(nroVentaModificar);
								consulta.reset();
							}
							consulta.iniciar();
							consulta.RegistarDetalleVenta(ultCodVenta, idProdVenta, cantProdVenta, precioVeUniSDescVenta, redondearDecimales((precioVeUniSDescVenta*cantProdVenta),2),
									descuentoIndivProdVenta, descuentoTotProdVenta, subTotVenta, gananciaProdVenta, uMedidaUsada);
							
							consulta.reset();

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

					//IMPRIMIR TICKET
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
										getClass().getClassLoader().getResourceAsStream("rComprobante.jasper"),
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
								// JOptionPane.showMessageDialog(null,
								// "ERROR " + ex.getMessage());
								// System.err.println("Error iReport: " +
								// ex.getMessage());
							}

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "ERROR " + ex);
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
		
		int idProd = Integer.parseInt(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 6).toString());
		double cantActual = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 0).toString());
		String nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		double preCDesc = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 3).toString());
		double descT = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 4).toString());
		double subT = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 5).toString());
		double preC = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 7).toString());
		
		String unimedida = nomProd.substring(nomProd.indexOf("(")+1, nomProd.indexOf(")"));
		
		ModificarPrecioVenta cp = new ModificarPrecioVenta(this, idProd, nomProd, unimedida, cantActual, preCDesc, subT, preC, descT);
		cp.setVisible(true);
		
		this.setEnabled(false);
		int fila = tbCarrito.getSelectedRow();
		tbCarrito.setRowSelectionInterval(fila, fila);
	}
	
	public void actualizartabla(double cant, double preCDesc, double preo, double pret, double desc, String newUniMed, String oldUniMed) {
		String nomProd = null;
		nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		String newNomProd = nomProd.replaceAll(oldUniMed, newUniMed);
		
		tbCarrito.setValueAt(redondearDecimales(cant, 2), 		tbCarrito.getSelectedRow(), 0);
		tbCarrito.setValueAt(newNomProd,						tbCarrito.getSelectedRow(), 1);
		tbCarrito.setValueAt(redondearDecimales(preCDesc, 2), 	tbCarrito.getSelectedRow(), 3);
		tbCarrito.setValueAt(redondearDecimales(preo, 2), 		tbCarrito.getSelectedRow(), 7);
		tbCarrito.setValueAt(redondearDecimales(pret, 2), 		tbCarrito.getSelectedRow(), 5);
		tbCarrito.setValueAt(redondearDecimales(desc, 2), 		tbCarrito.getSelectedRow(), 4);
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
				lblElVueltoDe.setText("S/ " + sumaPagos + " es: ");
				txtVuelto.setText("0.00");
			}
			else{
				lblElVueltoDe.setText("S/ " + sumaPagos + " es: ");
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
	protected void actionPerformedBtnLimpiar(ActionEvent arg0) {
		limpiarVentana();
	}
	
	protected void actionPerformedMnlistaDeProductos(ActionEvent arg0) {
		
	}
	
	public int AnadirProductosdeListaCompleta(String codigo, String cantidad) {
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			if (codigo.equals(tbCarrito.getValueAt(i, 6).toString())) {
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
}
