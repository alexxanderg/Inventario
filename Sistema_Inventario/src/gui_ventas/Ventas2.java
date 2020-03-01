package gui_ventas;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.mxrck.autocompleter.TextAutoCompleter;

import clases.Cliente;
import clases.Productos;
import clases.UnidadMed;
import gui_clientes.NuevoCliente;
import gui_configuracion.Configuraciones;
import gui_principal.VentanaPrincipal;
import mysql.consultas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
	private JLabel label;
	private JTextField txtInfoAdicional;
	private JLabel lblMtodoDePago;
	private JComboBox cbMetPago;
	private JLabel lblTotal;
	private JLabel lblTotalS;
	private JButton btnVender;
	private JTextField txtNroImpresiones;
	private JButton btnLimpiar;
	private JTextField txtPaga;
	private JTextField txtVuelto;
	private JLabel lblPagaConS;
	private JLabel lblSuVueltoEs;
	private JCheckBox chckbxCodigo;
	public DefaultTableModel dtm = new DefaultTableModel();
	private JMenu mnlistaDeProductos;
	private JMenu mncrearProductoNuevo;
	
	public VentanaPrincipal vp;
	JTable tb;
	ResultSet rs;
	consultas model = new consultas();
	NuevoCliente nc = new NuevoCliente(null, this);

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
		setTitle("ALMAC\u00C9N");
		setBounds(100, 100, 1134, 669);
		getContentPane().setLayout(null);
		
		btnX = new JButton("X");
		this.btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnX(arg0);
			}
		});
		btnX.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setBounds(1030, 0, 63, 30);
		getContentPane().add(btnX);
		
		this.lblCdigo = new JLabel("Buscar producto:");
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setFont(new Font("Arial", Font.BOLD, 22));
		this.lblCdigo.setBounds(10, 212, 195, 34);
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
		this.txtBuscarProd.setBounds(10, 251, 639, 34);
		getContentPane().add(this.txtBuscarProd);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 290, 1083, 310);
		getContentPane().add(this.scrollPane);
		
		tbCarrito = new JTable();
		tbCarrito.setAutoCreateRowSorter(true);
		tbCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCarrito.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		tbCarrito.setBackground(Color.WHITE);
		tbCarrito.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbCarrito);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.DARK_GRAY);
		lblCliente.setFont(new Font("Arial", Font.BOLD, 22));
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
		btnNewCliente.setBounds(358, 58, 56, 34);
		getContentPane().add(btnNewCliente);
		
		label = new JLabel("Informaci\u00F3n adicional:");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Arial", Font.BOLD, 22));
		label.setBounds(10, 104, 311, 34);
		getContentPane().add(label);
		
		txtInfoAdicional = new JTextField();
		txtInfoAdicional.setHorizontalAlignment(SwingConstants.LEFT);
		txtInfoAdicional.setForeground(SystemColor.windowBorder);
		txtInfoAdicional.setFont(new Font("Arial", Font.PLAIN, 20));
		txtInfoAdicional.setColumns(10);
		txtInfoAdicional.setBackground(new Color(245, 245, 245));
		txtInfoAdicional.setBounds(10, 138, 639, 34);
		getContentPane().add(txtInfoAdicional);
		
		lblMtodoDePago = new JLabel("M\u00E9todo de pago:");
		lblMtodoDePago.setForeground(Color.DARK_GRAY);
		lblMtodoDePago.setFont(new Font("Arial", Font.BOLD, 22));
		lblMtodoDePago.setBounds(441, 20, 208, 34);
		getContentPane().add(lblMtodoDePago);
		
		cbMetPago = new JComboBox();
		cbMetPago.setBackground(new Color(245, 245, 245));
		cbMetPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Cr\u00E9dito/D\u00E9bito", "Transferencia", "Dep\u00F3sito", "CR\u00C9DITO"}));
		cbMetPago.setFont(new Font("Arial", Font.PLAIN, 20));
		cbMetPago.setBounds(441, 58, 208, 35);
		getContentPane().add(cbMetPago);
		
		lblTotal = new JLabel("");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(30, 144, 255));
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblTotal.setBackground(new Color(50, 205, 50));
		lblTotal.setBounds(891, 174, 202, 50);
		getContentPane().add(lblTotal);
		
		lblTotalS = new JLabel("Total: S/ ");
		lblTotalS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalS.setForeground(new Color(30, 144, 255));
		lblTotalS.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblTotalS.setBackground(new Color(50, 205, 50));
		lblTotalS.setBounds(675, 174, 195, 50);
		getContentPane().add(lblTotalS);
		
		btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnVender(e);
			}
		});
		btnVender.setForeground(Color.WHITE);
		btnVender.setFont(new Font("Arial", Font.BOLD, 25));
		btnVender.setBackground(new Color(0, 250, 154));
		btnVender.setBounds(893, 235, 200, 50);
		getContentPane().add(btnVender);
		
		txtNroImpresiones = new JTextField();
		txtNroImpresiones.setText("0");
		txtNroImpresiones.setHorizontalAlignment(SwingConstants.CENTER);
		txtNroImpresiones.setForeground(Color.BLACK);
		txtNroImpresiones.setFont(new Font("Arial", Font.BOLD, 15));
		txtNroImpresiones.setColumns(10);
		txtNroImpresiones.setBackground(Color.ORANGE);
		txtNroImpresiones.setBounds(875, 266, 18, 18);
		getContentPane().add(txtNroImpresiones);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 25));
		btnLimpiar.setBackground(new Color(220, 20, 60));
		btnLimpiar.setBounds(675, 235, 200, 50);
		getContentPane().add(btnLimpiar);
		
		txtPaga = new JTextField();
		txtPaga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtPaga(arg0);
			}
		});
		txtPaga.setHorizontalAlignment(SwingConstants.CENTER);
		txtPaga.setForeground(SystemColor.windowBorder);
		txtPaga.setFont(new Font("Arial", Font.BOLD, 20));
		txtPaga.setColumns(10);
		txtPaga.setBackground(new Color(245, 245, 245));
		txtPaga.setBounds(893, 59, 200, 34);
		getContentPane().add(txtPaga);
		
		txtVuelto = new JTextField();
		txtVuelto.setHorizontalAlignment(SwingConstants.CENTER);
		txtVuelto.setForeground(SystemColor.windowBorder);
		txtVuelto.setFont(new Font("Arial", Font.BOLD, 20));
		txtVuelto.setEditable(false);
		txtVuelto.setColumns(10);
		txtVuelto.setBackground(new Color(245, 245, 245));
		txtVuelto.setBounds(893, 105, 200, 34);
		getContentPane().add(txtVuelto);
		
		lblPagaConS = new JLabel("Paga con: S/");
		lblPagaConS.setForeground(Color.DARK_GRAY);
		lblPagaConS.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPagaConS.setFont(new Font("Arial", Font.BOLD, 22));
		lblPagaConS.setBounds(684, 59, 186, 34);
		getContentPane().add(lblPagaConS);
		
		lblSuVueltoEs = new JLabel("Su vuelto es: S/");
		lblSuVueltoEs.setForeground(Color.DARK_GRAY);
		lblSuVueltoEs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSuVueltoEs.setFont(new Font("Arial", Font.BOLD, 22));
		lblSuVueltoEs.setBounds(684, 104, 186, 34);
		getContentPane().add(lblSuVueltoEs);
		
		chckbxCodigo = new JCheckBox("Por c\u00F3digo");
		chckbxCodigo.setVerticalAlignment(SwingConstants.BOTTOM);
		chckbxCodigo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxCodigo.setSelected(true);
		chckbxCodigo.setForeground(new Color(30, 144, 255));
		chckbxCodigo.setFont(new Font("Arial", Font.ITALIC, 13));
		chckbxCodigo.setBackground(Color.WHITE);
		chckbxCodigo.setBounds(535, 214, 114, 30);
		getContentPane().add(chckbxCodigo);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbCarrito.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
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
		dtm.setColumnIdentifiers(new Object[] { "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "SubTotal", "IDPROD", "PC" });
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
		tcm.getColumn(1).setPreferredWidth(anchoColumna(60)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // Stock
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Precio
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // SubTotal
		tcm.getColumn(5).setPreferredWidth(anchoColumna(1)); //ID
		tcm.getColumn(6).setPreferredWidth(anchoColumna(1));//Preco
		
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
			String busquedacompleta = txtBuscarProd.getText();
			int idProd = Integer.parseInt( busquedacompleta.substring(busquedacompleta.indexOf("(")+1, busquedacompleta.indexOf(")")));
			rs = model.buscarProductoID(idProd);
			int flag = 0;
			float cantidad = 0;
			for (int i = 0; i < tbCarrito.getRowCount(); i++) { // AQUÍ ENTRA SI
																// YA EXISTE EL
																// PRODUCTO EN
																// LA TABLA
				try {
					rs.beforeFirst();
					while (rs.next()) {
						
						if (rs.getString("codproducto").equals(tbCarrito.getValueAt(i, 5).toString())) {
							cantidad = (Float.parseFloat(tbCarrito.getValueAt(i, 0).toString()) + 1);
							tbCarrito.setValueAt(cantidad, i, 0);
							flag = 1;
							txtBuscarProd.setText(null);
							txtBuscarProd.requestFocus();
							tbCarrito.setRowSelectionInterval(i, i);
						}
					}
				} catch (SQLException e) {
					// JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
			}

			if (flag == 0) { // AQUÍ ENTRA SI EL PRODUCTO AGREGADO ES NUEVO
				try {
					rs.beforeFirst(); // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "SubTotal", "ID", "PC" 
					while (rs.next()) {
						dtm.addRow(new Object[] { "1", rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote")     
								, rs.getFloat("cantidad"), rs.getFloat("precioVe"), rs.getFloat("precioVe"), rs.getInt("codproducto"), rs.getFloat("precioCo"),
								rs.getFloat("precioCo") });
						tbCarrito.setRowSelectionInterval(tbCarrito.getRowCount() - 1, tbCarrito.getRowCount() - 1);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: " + e);
				}
				txtBuscarProd.setText(null);
				txtBuscarProd.requestFocus();
			}
			sumarSubTotales();
			sumarTotal();

		} catch (Exception e) { // AQUI ES SI LO QUE SE INGRESA ES UN CÓDIGO			
			try {
				String pcompleto = txtBuscarProd.getText();
				rs = model.buscarProducto(pcompleto);
				int flag = 0;
				float cantidad = 0;
				for (int i = 0; i < tbCarrito.getRowCount(); i++) {
					try {// AQUÍ ENTRA SI YA EXISTE EL PRODUCTO EN LA TABLA
						rs.beforeFirst();
						while (rs.next()) {
							if (rs.getString("codproducto").equals(tbCarrito.getValueAt(i, 6))) {
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
					txtBuscarProd.setText(null);
					txtBuscarProd.requestFocus();
				}
				/*sumarSubTotales();
				sumarTotal();*/

			} catch (Exception e2) {
				txtBuscarProd.setText(null);
			}
			
		}

	}
	
	public void sumarSubTotales() { // "Cantidad", "Producto y detalles", "Stock", "Precio Uni", "SubTotal", "ID", "PC" 
		for (int i = 0; i < tbCarrito.getRowCount(); i++) {
			try {
				float cant = Float.parseFloat(tbCarrito.getValueAt(i, 0).toString());
				float preU = Float.parseFloat(tbCarrito.getValueAt(i, 3).toString());
				double subT = cant * preU;
				subT = redondearDecimales(subT, 2);
				tbCarrito.setValueAt(subT, i, 4);
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "ERROR: " + e);
			}
		}
	}

	public void sumarTotal() {
		double Total = 0;
		if (tbCarrito.getRowCount() < 1)
			lblTotal.setText("");
		else {
			for (int i = 0; i < tbCarrito.getRowCount(); i++) {
				try {
					Total = Total + Float.parseFloat(tbCarrito.getValueAt(i, 4).toString());
					Total = redondearDecimales(Total, 1);
					lblTotal.setText("" + Total + "0");
					txtPaga.setText(null);
					txtVuelto.setText(null);
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
	protected void actionPerformedBtnVender(ActionEvent e) {
	}
	protected void keyTypedTxtBuscarProd(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER)
			AgregarProductoATabla();
	}
	protected void keyReleasedTxtPaga(KeyEvent arg0) {
		try {
			double pagacon = Float.parseFloat(txtPaga.getText());
			double tot = Float.parseFloat(lblTotal.getText());
			double vuelto = redondearDecimales(pagacon - tot, 2);
			if (vuelto < 0)
				txtVuelto.setText("0.00");
			else
				txtVuelto.setText("" + vuelto + "0");
		} catch (Exception e2) {
			txtVuelto.setText("0.00");
		}
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
}
