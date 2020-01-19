package gui_mantenimiento_productos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mysql.consultas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.mxrck.autocompleter.TextAutoCompleter;
import gui_mantenimiento_productos.AgregarStock;
import gui_mantenimiento_productos.ModificarProducto;
import gui_mantenimiento_productos.NuevoProducto;
import gui_principal.EleccionVentanas;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MantenimientoProductos extends JFrame implements ActionListener, WindowListener, KeyListener {

	private JPanel contentPane;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTextField txtCodigo;
	private JLabel lblCdigo;
	private JTable tbProductos;
	private TextAutoCompleter ac;
	private JScrollPane scrollPane;
	private JTextField txtMantenimientoDeProductos;
	private JButton btnVolver;

	JTable tb;
	ResultSet rs;
	String usuario;
	consultas model = new consultas();
	private JLabel lblNuevoProducto;
	private JLabel lblModificarProducto;
	private JLabel lblEliminarProducto;
	private JButton btnAgregarStock;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoProductos frame = new MantenimientoProductos(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MantenimientoProductos(String temp2) {
		usuario = temp2;
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAgregar = new JButton(new ImageIcon(this.getClass().getResource("/imgbtnanadir.png")));
		btnAgregar.setBackground(new Color(0, 0, 0));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(139, 91, 105, 105);
		contentPane.add(btnAgregar);

		btnModificar = new JButton(new ImageIcon(this.getClass().getResource("/imgbtnmodificar.png")));
		btnModificar.setBackground(new Color(0, 0, 0));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(268, 91, 160, 105);
		contentPane.add(btnModificar);

		btnEliminar = new JButton(new ImageIcon(this.getClass().getResource("/imgbtneliminar.png")));
		btnEliminar.setBackground(new Color(0, 0, 0));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(449, 91, 160, 105);
		contentPane.add(btnEliminar);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(this);
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setFont(new Font("Swis721 LtEx BT", Font.BOLD | Font.ITALIC, 20));
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(SystemColor.controlHighlight);
		txtCodigo.setBounds(139, 233, 932, 34);
		contentPane.add(txtCodigo);

		lblCdigo = new JLabel("Buscar:");
		lblCdigo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCdigo.setFont(new Font("EngraversGothic BT", Font.BOLD, 30));
		lblCdigo.setBounds(10, 229, 138, 38);
		contentPane.add(lblCdigo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 278, 1061, 402);
		contentPane.add(scrollPane);

		tbProductos = new JTable();
		tbProductos.setFont(new Font("Tw Cen MT", Font.ITALIC, 17));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tbProductos);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		btnVolver.setForeground(new Color(0, 255, 0));
		btnVolver.setFont(new Font("EngraversGothic BT", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setBackground(Color.DARK_GRAY);
		btnVolver.setBounds(0, 0, 132, 75);
		contentPane.add(btnVolver);

		txtMantenimientoDeProductos = new JTextField();
		txtMantenimientoDeProductos.setForeground(new Color(255, 255, 255));
		txtMantenimientoDeProductos.setText("ALMAC\u00C9N");
		txtMantenimientoDeProductos.setRequestFocusEnabled(false);
		txtMantenimientoDeProductos.setIgnoreRepaint(true);
		txtMantenimientoDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		txtMantenimientoDeProductos.setFont(new Font("EngraversGothic BT", Font.BOLD, 40));
		txtMantenimientoDeProductos.setFocusable(false);
		txtMantenimientoDeProductos.setFocusTraversalKeysEnabled(false);
		txtMantenimientoDeProductos.setEditable(false);
		txtMantenimientoDeProductos.setColumns(10);
		txtMantenimientoDeProductos.setBackground(Color.DARK_GRAY);
		txtMantenimientoDeProductos.setBounds(0, 0, 1080, 75);
		contentPane.add(txtMantenimientoDeProductos);

		lblNuevoProducto = new JLabel("Crear Nuevo producto");
		lblNuevoProducto.setFont(new Font("EngraversGothic BT", Font.PLAIN, 15));
		lblNuevoProducto.setBounds(110, 196, 166, 26);
		contentPane.add(lblNuevoProducto);

		lblModificarProducto = new JLabel("Modificar producto");
		lblModificarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarProducto.setFont(new Font("EngraversGothic BT", Font.PLAIN, 15));
		lblModificarProducto.setBounds(269, 196, 160, 26);
		contentPane.add(lblModificarProducto);

		lblEliminarProducto = new JLabel("Eliminar producto");
		lblEliminarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarProducto.setFont(new Font("EngraversGothic BT", Font.PLAIN, 15));
		lblEliminarProducto.setBounds(450, 196, 160, 26);
		contentPane.add(lblEliminarProducto);

		btnAgregarStock = new JButton("<html>Agregar Stock<br>\u00A0a producto </html>");
		btnAgregarStock.addActionListener(this);
		btnAgregarStock.setForeground(Color.WHITE);
		btnAgregarStock.setBackground(new Color(30, 144, 255));
		btnAgregarStock.setFont(new Font("EngraversGothic BT", Font.BOLD, 28));
		btnAgregarStock.setBounds(776, 91, 295, 105);
		contentPane.add(btnAgregarStock);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { txtCodigo, contentPane, btnAgregar,
				btnModificar, btnEliminar, lblCdigo, scrollPane, tbProductos }));

		cargarDatos();
		cargarBuscador();
		ajustarAnchoColumnas();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAgregarStock) {
			actionPerformedBtnAgregarStock(arg0);
		}
		if (arg0.getSource() == btnVolver) {
			actionPerformedBtnVolver(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}

	public void cargarDatos() {
		this.setLocationRelativeTo(null);
		DefaultTableModel dtm = new DefaultTableModel();
		tb = this.tbProductos;
		tb.setRowHeight(25);
		tb.setModel(dtm);
		dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Marca","Color", "Uni. Medida", "Cantidad",
				"PrecioCompra", "PrecioVenta" });
		consultas model = new consultas();
		rs = model.cargarProductos();
		try {
			while (rs.next())
				dtm.addRow(new Object[] { rs.getString("codproducto"), rs.getString("producto"),
						rs.getString("detalles"),rs.getString("marca"),rs.getString("color"), rs.getString("unimedida"), rs.getFloat("cantidad"),
						rs.getFloat("precioCo"), rs.getFloat("precioVe") });
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtCodigo);
		consultas model = new consultas();
		ResultSet rs = model.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				// ac.addItem(rs.getString("codproducto"));
				ac.addItem(rs.getString("producto") + "_" + rs.getString("detalles"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e);
		}
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(12)); // Codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(24)); // Producto
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20)); // Detalle
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // Uni Med
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8)); // Stock
		tcm.getColumn(5).setPreferredWidth(anchoColumna(7)); // PrecioCompra
		tcm.getColumn(6).setPreferredWidth(anchoColumna(7 )); // PrecioVenta
		tcm.getColumn(7).setPreferredWidth(anchoColumna(6)); // Marca
		tcm.getColumn(8).setPreferredWidth(anchoColumna(6)); // Color

		DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);

		DefaultTableCellRenderer tcr3 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(6).setCellRenderer(tcr3);
		
		DefaultTableCellRenderer tcr4 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(7).setCellRenderer(tcr4);
		
		DefaultTableCellRenderer tcr5 = new DefaultTableCellRenderer();
		tcr3.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(8).setCellRenderer(tcr5);
	}

	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		NuevoProducto np = new NuevoProducto(this, null,usuario);
		np.setVisible(true);
		np.setLocationRelativeTo(null);
		this.setEnabled(false);
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		try {		
			String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));
			String nombreProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 1));
			String detalleProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 2));
			String marcaProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 3));
			String colorProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 4));
			String uniMedidaProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 5));
			String cantidadProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 6));
			String preciocoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 7));
			String preciovePoducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 8));
			ModificarProducto mp = new ModificarProducto(codigoProducto, nombreProducto, detalleProducto,
					uniMedidaProducto, cantidadProducto, preciocoProducto, preciovePoducto,marcaProducto, colorProducto, this);
			mp.setVisible(true);
			this.setEnabled(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar");
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el producto?\nAl eliminar el producto tambien estará eliminando la informacion\nde venta detalle y ingreso relacionada con el producto eliminado", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
			try {
				String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));
				String nombreProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 1));
				model.eliminarProductoIngreso(codigoProducto, nombreProducto);
				model.eliminarProductoDetalle(codigoProducto, nombreProducto);
				model.eliminarProducto(codigoProducto, nombreProducto);
				cargarDatos();
				ajustarAnchoColumnas();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar: " + e);
			}
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
		int opc = JOptionPane.showConfirmDialog(null, "¿Cerrar Sistema?", "Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0){
			try {
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy  HH.mm.ss");
				Date today = Calendar.getInstance().getTime();       
				String reportDate = df.format(today);
				File directorio=new File("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA"); 
				directorio.mkdirs(); 
				Process p;
				p = Runtime.getRuntime().exec("mysqldump -u root -pAa123 db_inventario");
				InputStream is = p.getInputStream();
				FileOutputStream fos = new FileOutputStream("D:\\ INFORMACION_DEL_SISTEMA\\BACKUP_SISTEMA\\backup_inventario  "+reportDate+".sql");
				byte[] buffer = new byte[1000];
				int leido = is.read(buffer);
				while(leido>0){
					fos.write(buffer, 0, leido);
					leido = is.read(buffer);
				}
				//JOptionPane.showMessageDialog(null, "Copia de segudidad creada en: \n D:/ INFORMACION DEL SISTEMA / BACKUP_SISTEMA / ");
				//JOptionPane.showMessageDialog(null, "Copia de segudidad realizada correctamente");
				fos.close();
			} catch (IOException e1) {
				//JOptionPane.showMessageDialog(null, e1);
			}
			System.exit(0);
		}
		else
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == txtCodigo) {
			keyPressedTxtCodigo(arg0);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		int posi = -1;
		if ((c == (char) KeyEvent.VK_ENTER)) {
			try {
				String pcompleto = txtCodigo.getText();
				String[] parts = pcompleto.split("_");
				String prd = parts[0]; // 123
				String dtll = parts[1]; // 654321
				rs = model.buscarProductoDetalle(prd, dtll);
				rs.next();
				if (rs.equals(null)) {
					JOptionPane.showMessageDialog(null, "Producto no registrado");
					limpiar();
				} else {
					String codigoProducto = rs.getString("codproducto");
					String nombreProducto = rs.getString("producto");
					String detalleProducto = rs.getString("detalles");
					String uniMedidaProducto = rs.getString("unimedida");
					String cantidadProducto = rs.getString("cantidad");
					String preciocoProducto = rs.getString("precioCo");
					String preciovePoducto = rs.getString("precioVe");
					String marcaProducto = rs.getString("marca");
					String colorProducto = rs.getString("color");
					
					String[] opciones = { "MODIFICAR", "AGREGAR STOCK", "CANCELAR" };
					int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccione una opcion",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
					
					if (seleccion == 0) {//Modificar
						ModificarProducto mp = new ModificarProducto(codigoProducto, nombreProducto, detalleProducto,
								uniMedidaProducto, cantidadProducto, preciocoProducto, preciovePoducto,marcaProducto,colorProducto, this);
						mp.setVisible(true);
						this.setEnabled(false);
						txtCodigo.setText("");						
					}
					if (seleccion == 1) {// agregar
						txtCodigo.setText("");
						
						try {
							AgregarStock as = new AgregarStock(codigoProducto, cantidadProducto, preciocoProducto, preciovePoducto, this, usuario);
							as.setVisible(true);
							this.setEnabled(false);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al abrir Agregar Stock: " + e.getMessage());
						}
					}
					
				}
			} catch (Exception e) {
				try {
					String pcompleto = txtCodigo.getText();
					rs = model.buscarProducto(pcompleto);
					rs.next();
					if (rs.equals(null)) {
						JOptionPane.showMessageDialog(null, "Producto no registrado");
						limpiar();
					} else {
						String codigoProducto = rs.getString("codproducto");
						String nombreProducto = rs.getString("producto");
						String detalleProducto = rs.getString("detalles");
						String uniMedidaProducto = rs.getString("unimedida");
						String cantidadProducto = rs.getString("cantidad");
						String preciocoProducto = rs.getString("precioCo");
						String preciovePoducto = rs.getString("precioVe");
						String marcaProducto = rs.getString("marca");
						String colorProducto = rs.getString("color");
						
						String[] opciones = { "MODIFICAR", "AGREGAR STOCK", "CANCELAR" };
						int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccione una opcion",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
						
						if (seleccion == 0) {//Modificar
							ModificarProducto mp = new ModificarProducto(codigoProducto, nombreProducto, detalleProducto,
									uniMedidaProducto, cantidadProducto, preciocoProducto, preciovePoducto,marcaProducto,colorProducto, this);
							mp.setVisible(true);
							this.setEnabled(false);
							txtCodigo.setText("");						
						}
						if (seleccion == 1) {// agregar
							txtCodigo.setText("");
							
							try {
								AgregarStock as = new AgregarStock(codigoProducto, cantidadProducto, preciocoProducto, preciovePoducto, this, usuario);
								as.setVisible(true);
								this.setEnabled(false);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Error al abrir Agregar Stock: " + e1.getMessage());
							}
						}
					}

				} catch (Exception e2) {
					limpiar();
				}

			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	protected void keyPressedTxtCodigo(KeyEvent arg0) {

	}

	public void limpiar() {
		txtCodigo.setText(null);
	}

	public void selecionarProducto(String cod) {
		int cantProductos = tbProductos.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (cod.equals(tbProductos.getValueAt(i, 0))) {
				tbProductos.setRowSelectionInterval(i, i);
				break;
			}
		}
	}

	protected void actionPerformedBtnVolver(ActionEvent arg0) {
		EleccionVentanas el = new EleccionVentanas(usuario);
		el.setVisible(true);
		dispose();
	}

	protected void actionPerformedBtnAgregarStock(ActionEvent arg0) {
		DefaultTableModel tm = (DefaultTableModel) tbProductos.getModel();
		try {
			String codigoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 0));
			String cantidadProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 4));
			String preciocoProducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 5));
			String preciovePoducto = String.valueOf(tm.getValueAt(tbProductos.getSelectedRow(), 6));

			AgregarStock as = new AgregarStock(codigoProducto, cantidadProducto,preciocoProducto,preciovePoducto,this,usuario);
			as.setVisible(true);
			this.setEnabled(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Seleccione el producto a modificar");
		}
	}
}
