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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.mxrck.autocompleter.TextAutoCompleter;

import clases.Cliente;
import clases.Productos;
import clases.UnidadMed;
import gui_clientes.NuevoCliente;
import gui_configuracion.Configuraciones;
import gui_principal.VentanaPrincipal;
import mysql.MySQLConexion;
import mysql.consultas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Ventas2 extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
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
	private JMenu mncrearProductoNuevo;
	
	public VentanaPrincipal vp;
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();
	NuevoCliente nc = new NuevoCliente(null, this);
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
	private JLabel label_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventas2 frame = new Ventas2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventas2(VentanaPrincipal vp) {
		this.vp = vp;
		
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
		tbCarrito.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		tbCarrito.setBackground(Color.WHITE);
		tbCarrito.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCarrito);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCarrito.getTableHeader().setReorderingAllowed(false);
		btnX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setBounds(1030, 0, 63, 30);
		getContentPane().add(btnX);
		
		this.lblCdigo = new JLabel("Buscar producto:");
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setFont(new Font("Arial", Font.BOLD, 20));
		this.lblCdigo.setBounds(10, 188, 195, 34);
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
		lblCliente.setFont(new Font("Arial", Font.BOLD, 20));
		lblCliente.setBounds(10, 24, 101, 34);
		getContentPane().add(lblCliente);
		
		cbClientes = new JComboBox();
		cbClientes.setBackground(new Color(245, 245, 245));
		cbClientes.setFont(new Font("Arial", Font.PLAIN, 20));
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
		lblNotaAdicionalDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblNotaAdicionalDe.setBounds(10, 104, 404, 34);
		getContentPane().add(lblNotaAdicionalDe);
		
		txtInfoAdicional = new JTextField();
		txtInfoAdicional.setHorizontalAlignment(SwingConstants.LEFT);
		txtInfoAdicional.setForeground(SystemColor.windowBorder);
		txtInfoAdicional.setFont(new Font("Arial", Font.PLAIN, 20));
		txtInfoAdicional.setColumns(10);
		txtInfoAdicional.setBackground(new Color(245, 245, 245));
		txtInfoAdicional.setBounds(10, 138, 404, 34);
		getContentPane().add(txtInfoAdicional);
		
		lblMtodoDePago = new JLabel("Pago 1:");
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Arial", Font.BOLD, 20));
		lblMtodoDePago.setBounds(441, 20, 110, 34);
		getContentPane().add(lblMtodoDePago);
		
		cbPago1 = new JComboBox();
		cbPago1.setBackground(new Color(245, 245, 245));
		cbPago1.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbPago1.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago1.setBounds(441, 58, 139, 35);
		getContentPane().add(cbPago1);
		
		lblTotalVentaFinal = new JLabel("0");
		lblTotalVentaFinal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalVentaFinal.setForeground(new Color(30, 144, 255));
		lblTotalVentaFinal.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblTotalVentaFinal.setBackground(new Color(50, 205, 50));
		lblTotalVentaFinal.setBounds(911, 188, 197, 36);
		getContentPane().add(lblTotalVentaFinal);
		
		lblTitTotal = new JLabel("TOTAL S/ ");
		lblTitTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotal.setForeground(new Color(30, 144, 255));
		lblTitTotal.setFont(new Font("SansSerif", Font.BOLD, 35));
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
		btnVender.setFont(new Font("Arial", Font.BOLD, 25));
		btnVender.setBackground(new Color(0, 250, 154));
		btnVender.setBounds(908, 227, 200, 34);
		getContentPane().add(btnVender);
		
		txtNroImpresiones = new JTextField();
		txtNroImpresiones.setText("0");
		txtNroImpresiones.setHorizontalAlignment(SwingConstants.CENTER);
		txtNroImpresiones.setForeground(Color.BLACK);
		txtNroImpresiones.setFont(new Font("Arial", Font.BOLD, 15));
		txtNroImpresiones.setColumns(10);
		txtNroImpresiones.setBackground(Color.LIGHT_GRAY);
		txtNroImpresiones.setBounds(890, 243, 19, 18);
		getContentPane().add(txtNroImpresiones);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 25));
		btnLimpiar.setBackground(new Color(220, 20, 60));
		btnLimpiar.setBounds(690, 227, 200, 34);
		getContentPane().add(btnLimpiar);
		
		txtVuelto = new JTextField();
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(new Color(30, 144, 255));
		txtVuelto.setFont(new Font("Arial", Font.BOLD, 20));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBackground(new Color(245, 245, 245));
		txtVuelto.setBounds(583, 227, 82, 34);
		getContentPane().add(txtVuelto);
		
		lblTitDescuento = new JLabel("Descuento S/");
		lblTitDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitDescuento.setForeground(new Color(30, 144, 255));
		lblTitDescuento.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitDescuento.setBackground(new Color(50, 205, 50));
		lblTitDescuento.setBounds(690, 142, 200, 30);
		getContentPane().add(lblTitDescuento);
		
		lblDescuento = new JLabel("0");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setForeground(new Color(30, 144, 255));
		lblDescuento.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDescuento.setBackground(new Color(50, 205, 50));
		lblDescuento.setBounds(911, 142, 197, 30);
		getContentPane().add(lblDescuento);
		
		txtPago1 = new JTextField();
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
		txtPago1.setFont(new Font("Arial", Font.BOLD, 20));
		txtPago1.setColumns(10);
		txtPago1.setBackground(new Color(245, 245, 245));
		txtPago1.setBounds(583, 58, 85, 34);
		getContentPane().add(txtPago1);
		
		lblMtodoDePago_1 = new JLabel("Pago 2:");
		lblMtodoDePago_1.setForeground(Color.DARK_GRAY);
		lblMtodoDePago_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblMtodoDePago_1.setBounds(441, 104, 110, 34);
		getContentPane().add(lblMtodoDePago_1);
		
		cbPago2 = new JComboBox();
		cbPago2.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbPago2.setFont(new Font("Arial", Font.ITALIC, 18));
		cbPago2.setBackground(new Color(245, 245, 245));
		cbPago2.setBounds(441, 138, 139, 35);
		getContentPane().add(cbPago2);
		
		txtPago2 = new JTextField();
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
		txtPago2.setFont(new Font("Arial", Font.BOLD, 20));
		txtPago2.setColumns(10);
		txtPago2.setBackground(new Color(245, 245, 245));
		txtPago2.setBounds(583, 138, 85, 34);
		getContentPane().add(txtPago2);
		
		lblS = new JLabel("S/");
		lblS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblS.setForeground(Color.DARK_GRAY);
		lblS.setFont(new Font("Arial", Font.BOLD, 22));
		lblS.setBounds(601, 24, 32, 34);
		getContentPane().add(lblS);
		
		label_1 = new JLabel("S/");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Arial", Font.BOLD, 22));
		label_1.setBounds(605, 108, 32, 34);
		getContentPane().add(label_1);
		
		lblElVueltoDe = new JLabel("El vuelto de 0 es:");
		lblElVueltoDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblElVueltoDe.setForeground(new Color(30, 144, 255));
		lblElVueltoDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblElVueltoDe.setBounds(441, 190, 224, 34);
		getContentPane().add(lblElVueltoDe);
		
		lblTitTotOri = new JLabel("Total original S/ ");
		lblTitTotOri.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitTotOri.setForeground(new Color(30, 144, 255));
		lblTitTotOri.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitTotOri.setBackground(new Color(50, 205, 50));
		lblTitTotOri.setBounds(705, 100, 185, 34);
		getContentPane().add(lblTitTotOri);
		
		lblTotOriginal = new JLabel("0");
		lblTotOriginal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotOriginal.setForeground(new Color(30, 144, 255));
		lblTotOriginal.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTotOriginal.setBackground(new Color(50, 205, 50));
		lblTotOriginal.setBounds(911, 100, 197, 34);
		getContentPane().add(lblTotOriginal);
		
		lblTotalCompra = new JLabel("0");
		lblTotalCompra.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCompra.setForeground(new Color(30, 144, 255));
		lblTotalCompra.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTotalCompra.setBackground(new Color(50, 205, 50));
		lblTotalCompra.setBounds(768, 5, 68, 17);
		getContentPane().add(lblTotalCompra);
		
		lblGananciaTotal = new JLabel("0");
		lblGananciaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblGananciaTotal.setForeground(new Color(30, 144, 255));
		lblGananciaTotal.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblGananciaTotal.setBackground(new Color(50, 205, 50));
		lblGananciaTotal.setBounds(846, 5, 68, 17);
		getContentPane().add(lblGananciaTotal);
		
		lblTitIgv = new JLabel("IGV S/");
		lblTitIgv.setVisible(false);
		lblTitIgv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitIgv.setForeground(new Color(30, 144, 255));
		lblTitIgv.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitIgv.setBackground(new Color(50, 205, 50));
		lblTitIgv.setBounds(690, 59, 200, 30);
		getContentPane().add(lblTitIgv);
		
		lblIGV = new JLabel("0");
		lblIGV.setVisible(false);
		lblIGV.setHorizontalAlignment(SwingConstants.LEFT);
		lblIGV.setForeground(new Color(30, 144, 255));
		lblIGV.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblIGV.setBackground(new Color(50, 205, 50));
		lblIGV.setBounds(911, 58, 197, 30);
		getContentPane().add(lblIGV);
		
		label_2 = new JLabel("S/");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(30, 144, 255));
		label_2.setFont(new Font("Arial", Font.BOLD, 22));
		label_2.setBounds(548, 227, 32, 34);
		getContentPane().add(label_2);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBuscarProd, cbClientes, btnNewCliente, txtInfoAdicional, cbPago1, txtPago1, cbPago2, txtPago2, btnVender, btnLimpiar, btnX}));
		
		mnCrearProducto = new JMenu("|Ver / modificar / eliminar / ventas realizadas| ");
		mnCrearProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnCrearProducto(arg0);
			}
		});
		mnCrearProducto.setForeground(new Color(65, 105, 225));
		mnCrearProducto.setBackground(SystemColor.control);
		mnCrearProducto.setFont(new Font("Arial", Font.BOLD, 22));
		menuBar.add(mnCrearProducto);
		
		mnlistaDeProductos = new JMenu("|Lista de productos| ");
		mnlistaDeProductos.setForeground(new Color(60, 179, 113));
		mnlistaDeProductos.setFont(new Font("Arial", Font.BOLD, 22));
		mnlistaDeProductos.setBackground(SystemColor.menu);
		menuBar.add(mnlistaDeProductos);
		
		mncrearProductoNuevo = new JMenu("|Crear producto nuevo| ");
		mncrearProductoNuevo.setForeground(new Color(220, 20, 60));
		mncrearProductoNuevo.setFont(new Font("Arial", Font.BOLD, 22));
		mncrearProductoNuevo.setBackground(SystemColor.menu);
		menuBar.add(mncrearProductoNuevo);
		
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //QUITA LA BARRA DE TÍTULO
		
		cargar();
		cargarBuscador();
		ajustarAnchoColumnas();
	}
	
	public void cargar() {
		tbCarrito.setRowHeight(30);
		
		Cliente cliente = new Cliente();
		cliente.cargarClientes(cbClientes);
		
		tbCarrito.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "IDPROD", "PC" });
		ajustarAnchoColumnas();
		
	}
	
	public void cargarBuscador() {
		
		
		ac = new TextAutoCompleter(txtBuscarProd);
		consultas model = new consultas();
		ResultSet rs = model.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				ac.addItem(rs.getString("cantidad") + " " + rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
						" = S/" + rs.getString("precioVe") + "  -  (" + rs.getString("codproducto") + ")");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}
	
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbCarrito.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(7)); // Cantidad
		tcm.getColumn(1).setPreferredWidth(anchoColumna(50)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // Stock
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Precio
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // Descuento
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10)); // SubTotal
		tcm.getColumn(6).setPreferredWidth(anchoColumna(1)); //ID
		tcm.getColumn(7).setPreferredWidth(anchoColumna(1));//Preco
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tbCarrito.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(2).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(3).setCellRenderer(tcr);
		tbCarrito.getColumnModel().getColumn(4).setCellRenderer(tcr);		
	}

	protected void actionPerformedBtnX(ActionEvent arg0) {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarCliente(int iddistrib){
		try {
			cbClientes.removeAllItems();
			Cliente cliente = new Cliente();
			cliente.cargarClientes(cbClientes);
			
			for(int i = 0; i<cbClientes.getItemCount(); i++){
				JOptionPane.showMessageDialog(null, "" + cbClientes.getItemAt(i).getId() + " - "  + iddistrib);
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
			int idProd = Integer.parseInt( nomProducto.substring(nomProducto.indexOf("(")+1, nomProducto.indexOf(")")));
			rs = model.buscarProductoID(idProd);
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
					//JOptionPane.showMessageDialog(null, "ERROR al agregar producto al carrito: " + e);
				}
			}
			txtBuscarProd.setText(null);
			sumarSubTotales();
			sumarTotalGenerales();

		} catch (Exception e) { // AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO DE BARRAS
			try {
				String codbarra = txtBuscarProd.getText();
				rs = model.buscarProductoBarras(codbarra);
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
							dtm.addRow(new Object[] { "1", rs.getString("producto"), rs.getString("detalles"),
									rs.getString("cantidad"), rs.getFloat("precioVe"), "", rs.getString("codproducto"),
									rs.getFloat("precioCo") });
							tbCarrito.setRowSelectionInterval(tbCarrito.getRowCount() - 1, tbCarrito.getRowCount() - 1);
						}
					} catch (Exception ex) {
					}
				}
				limpiarVentana();
				sumarSubTotales();
				sumarTotalGenerales();
			} catch (Exception e2) {
				txtBuscarProd.setText(null);
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
				double preU = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
				double desc = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
				double subt = Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
				
				subt = (cant * preU) - desc;
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
	
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
		try {
			/*if (np.isShowing()) {
				//JOptionPane.showMessageDialog(null, "Ya tiene abierta la ventana");
				np.setExtendedState(0); //MOSTRAR VENTANA ABIERTA
				np.setVisible(true); 
			} else {
				np = new NuevoProducto2(this);
				np.setLocationRelativeTo(null);
				np.setVisible(true);
			}*/
		} catch (Exception f) {
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	public int verificarStock() {
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			int idProd = Integer.parseInt(tbCarrito.getValueAt(i, 6).toString());
			float cantV = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
			float stock = 0;
			rs = model.buscarProductoID(idProd);
			try {
				rs.next();
				stock = rs.getFloat("cantidad");
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
					ResultSet rsvss; //Verificar si está permitido vender sin stock
					rsvss = model.verificarVentaSinStock();
					rsvss.next();
					ventasinstock = rsvss.getInt("ventasinstock");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al consultar permisos de venta sin stock " + e2);
				}
				if(ventasinstock == 0) // NO ESTÁ PERMITIDO VENDER SIN STOCK, SE DEBE VERIFICAR
					flag = verificarStock();
				else
					flag = 1;
				
				if (flag == 1) {
					int idcliente = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getId();
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
							
					model.Vender(idcliente, idusuario, totCompra, totVenta, gananciaTot, descuentoTot, nota, metpago1, monto1, metpago2, monto2);
					
					/*
					for (int i = 0; i < tbCarrito.getRowCount(); i++) {
						float cantidad = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
						int idproducto = Integer.parseInt(tbCarrito.getValueAt(i, 6).toString());
						double precioVeUni = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
							precioVeUni = redondearDecimales(precioVeUni, 2);
						double descuentoUni = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
							descuentoUni = redondearDecimales(descuentoUni, 2);
						double subTotModif = Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
							subTotModif = redondearDecimales(subTotModif, 2);
						double precioCoUni = Float.parseFloat(tbCarrito.getValueAt(i, 7).toString());
							precioCoUni = redondearDecimales(precioCoUni, 2);
						
							
						// CALCULO DE PRECIOS
						totalOriginalVenta = totalOriginalVenta + precioVeUni;
							totalOriginalVenta = redondearDecimales(totalOriginalVenta, 2);
							
						pretotCompra = pretotCompra + precioCoUni;
							pretotCompra = redondearDecimales(pretotCompra, 2);
					
					}
*//*
					
					int codVenta = 0;
					try {
						int idcliente = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getId();
						String cli = cbClientes.getSelectedItem().toString();
						String nrodoc = cbClientes.getItemAt(cbClientes.getSelectedIndex()).getNrodoc();
						
						float preTotalVentaFinal = Float.parseFloat(lblTotal.getText());
						double gananciaOriginal = pretotVenta - pretotCompra;
						gananciaOriginal = redondearDecimales(gananciaOriginal, 2);
						double gananciaFinal = preTotalVentaFinal - pretotCompra;
						gananciaFinal = redondearDecimales(gananciaFinal, 2);
						
						String nota = txtInfoAdicional.getText();
						int idusuario = Integer.parseInt(vp.lblIdusuario.getText()); // USUARIO
						float descTotal = Float.parseFloat(lblDescuento.getText());
						
						int metpago1 = cbPago1.getSelectedIndex();
						int metpago2 = cbPago2.getSelectedIndex();
						float monto1 = Float.parseFloat(txtPago1.getText());
						float monto2 = Float.parseFloat(txtPago2.getText());
						
						model.Vender(idcliente, idusuario, pretotCompra, preTotalVentaFinal, gananciaFinal, descTotal, nota, metpago1, monto1, metpago2, monto2);
						rs = model.ObtenerUltimoCodigo();
						try {
							while (rs.next())
								codVenta = rs.getInt("codventa");
						} catch (Exception e3) {
							JOptionPane.showMessageDialog(null, "ERROR al obtener ultimo codigo: " + e3);
						}

						for (int i = 0; i < tbCarrito.getRowCount(); i++) {
							String codProducto = tbCarrito.getValueAt(i, 5).toString();
							float cantventa = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());

							double preVeUnidadOriginal = 0;

							for (int y = 0; y < listprod.size(); y++) {
								if (listprod.get(y).getCodigo().equals(codProducto)) {
									preVeUnidadOriginal = listprod.get(y).getPrecioVe();
									y = listprod.size();
								}
							}
							preVeUnidadOriginal = redondearDecimales(preVeUnidadOriginal, 2);

							double preTotalUnidadOriginal = cantventa * preVeUnidadOriginal;
							preTotalUnidadOriginal = redondearDecimales(preTotalUnidadOriginal, 2);

							double preUnidadFinal = Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
							preUnidadFinal = redondearDecimales(preUnidadFinal, 2);
							double preTotalUnidadFinal = Float.parseFloat(tbCarrito.getValueAt(i, 5).toString());
							preTotalUnidadFinal = redondearDecimales(preTotalUnidadFinal, 2);

							model.RegistarDetalleVenta(codVenta, codProducto, cantventa, preVeUnidadOriginal,
									preTotalUnidadOriginal, preUnidadFinal, preTotalUnidadFinal, 0);
							model.RealizarDescuentoStock(codProducto, cantventa);
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "ERROR: " + e2);
					}*/

					// IMPRIMIR TICKET
					int copias = Integer.parseInt(txtNroImpresiones.getText());
					Connection con = null;
//
//					for (int i = 0; i < copias; i++) {
//						try {
//							Map<String, Object> parameters = new HashMap();
//							parameters.put("prtNVenta", codVenta);
//							parameters.put("prtCliente", cli);
//							parameters.put("prtNroDoc", nrodoc);
//							parameters.put("prtVendedor", usuario);
//							parameters.put("prtNota", nota);
//							/*
//							 * new AbstractJasperReports().createReport(
//							 * con.getConn(), "rPrueba.jasper", null);
//							 * AbstractJasperReports.showViewer();
//							 */
//							try {
//								con = MySQLConexion.getConection();
//								/*
//								 * JasperReport reporte = (JasperReport)
//								 * JRLoader.loadObjectFromFile(
//								 * "bin/rComprobante.jasper"); JasperPrint
//								 * jasperPrint =
//								 * JasperFillManager.fillReport(reporte,
//								 * parameters, con);
//								 * AbstractJasperReports.showViewer();
//								 * JasperPrintManager.printReport(
//								 * jasperPrint, false);
//								 * 
//								 */
//								JasperPrint impressao = JasperFillManager.fillReport(
//										getClass().getClassLoader().getResourceAsStream("rComprobante.jasper"),
//										parameters, con);
//
//								// AbstractJasperReports.showViewer();
//								JasperPrintManager.printReport(impressao, false);
//								/*
//								 * this.setAlwaysOnTop(false);
//								 * //JOptionPane.showMessageDialog(null,
//								 * "VENTA CORRECTA");
//								 * this.setAlwaysOnTop(true);
//								 */
//
//							} catch (JRException ex) {
//								// JOptionPane.showMessageDialog(null,
//								// "ERROR " + ex.getMessage());
//								// System.err.println("Error iReport: " +
//								// ex.getMessage());
//							}
//
//						} catch (Exception e) {
//							JOptionPane.showMessageDialog(null, "ERROR " + e);
//						}
//					}
					
					
					JOptionPane.showMessageDialog(null, "VENTA CORRECTA", "", JOptionPane.OK_OPTION);
					limpiarVentana();

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
			JOptionPane.showMessageDialog(null, "Error: " + f);
		}
	}
	
	protected void mouseClickedTbCarrito(MouseEvent arg0) {
		// "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "Descuento", "SubTotal", "ID", "PC" 
		
		int idProd = Integer.parseInt(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 6).toString());
		double cantActual = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 0).toString());
		String nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		double preU = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 3).toString());
		double desc = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 4).toString());
		double subT = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 5).toString());
		double preC = Float.parseFloat(tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 7).toString());
		
		String unimedida = nomProd.substring(nomProd.indexOf("(")+1, nomProd.indexOf(")"));
		
		ModificarPrecioVenta cp = new ModificarPrecioVenta(this, idProd, nomProd, unimedida, cantActual, preU, subT, preC, desc);
		cp.setVisible(true);
		
		this.setEnabled(false);
		int fila = tbCarrito.getSelectedRow();
		tbCarrito.setRowSelectionInterval(fila, fila);
	}
	
	public void actualizartabla(double cant, double preu, double preo, double pret, double desc, String newUniMed, String oldUniMed) {
		String nomProd = null;
		nomProd = tbCarrito.getValueAt(tbCarrito.getSelectedRow(), 1).toString();
		String newNomProd = nomProd.replaceAll(oldUniMed, newUniMed);
		
		tbCarrito.setValueAt(redondearDecimales(cant, 2), tbCarrito.getSelectedRow(), 0);
		tbCarrito.setValueAt(newNomProd,					  tbCarrito.getSelectedRow(), 1);
		tbCarrito.setValueAt(redondearDecimales(preu, 2), tbCarrito.getSelectedRow(), 3);
		tbCarrito.setValueAt(redondearDecimales(preo, 2), tbCarrito.getSelectedRow(), 7);
		tbCarrito.setValueAt(redondearDecimales(pret, 2), tbCarrito.getSelectedRow(), 5);
		tbCarrito.setValueAt(redondearDecimales(desc, 2), tbCarrito.getSelectedRow(), 4);
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
				lblElVueltoDe.setText("El vuelto de " + sumaPagos + " es: ");
				txtVuelto.setText("0.00");
			}
			else{
				lblElVueltoDe.setText("El vuelto de " + sumaPagos + " es: ");
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
}
