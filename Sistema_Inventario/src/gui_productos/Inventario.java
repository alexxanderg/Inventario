package gui_productos;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mxrck.autocompleter.TextAutoCompleter;

import clases.AbstractJasperReports;
import gui_principal.VentanaPrincipal;
import gui_ventas.Ventas;
import mysql.MySQLConexion;
import mysql.consultas;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.EmptyBorder;

public class Inventario extends JInternalFrame {
	private JMenuBar menuBar;
	private JMenu mnCrearProducto;
	private JMenu mnModificarProducto;
	private JMenu mnEliminarProducto;
	private JLabel lblCdigo;
	private JTextField txtCodigo;
	private JScrollPane scrollPane;
	private TextAutoCompleter ac;
	private JTable tbProductos;
	private JCheckBox chckbxFiltrar;
	
	JTable tb;
	ResultSet rs;
	consultas consulta = new consultas();
	ModificarProducto mp = null;
	DefaultTableModel dtm = new DefaultTableModel();
	consultas model = new consultas();
	public VentanaPrincipal vp;
	private JTextField txtCodigo2;
	private JButton btnExportar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inventario(VentanaPrincipal vp) {
		this.vp = vp;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("ALMAC\u00C9N");
		setBounds(100, 100, 780, 679);
		getContentPane().setLayout(null);
		
		this.lblCdigo = new JLabel("Buscar:");
		lblCdigo.setVerticalAlignment(SwingConstants.TOP);
		lblCdigo.setForeground(Color.DARK_GRAY);
		this.lblCdigo.setFont(new Font("Candara", Font.BOLD, 30));
		this.lblCdigo.setBounds(10, 45, 113, 34);
		getContentPane().add(this.lblCdigo);
		
		this.txtCodigo = new JTextField();
		txtCodigo.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedTxtCodigo(e);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtCodigo(arg0);
			}
		});
		this.txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtCodigo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		this.txtCodigo.setColumns(10);
		this.txtCodigo.setBackground(new Color(245, 245, 245));
		this.txtCodigo.setBounds(123, 45, 391, 34);
		getContentPane().add(this.txtCodigo);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		scrollPane.setAutoscrolls(true);
		this.scrollPane.setBounds(10, 90, 744, 519);
		getContentPane().add(this.scrollPane);
		
		tbProductos = new JTable();
		tbProductos.setAutoCreateRowSorter(true);
		tbProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbProductos.setFont(new Font("Arial", Font.ITALIC, 14));
		tbProductos.setBackground(Color.WHITE);
		tbProductos.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setViewportView(tbProductos);
		
		chckbxFiltrar = new JCheckBox("\u00BFFiltrar al escribir?");
		chckbxFiltrar.setForeground(new Color(30, 144, 255));
		chckbxFiltrar.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxFiltrar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				itemStateChangedChckbxFiltrar(arg0);
			}
		});
		chckbxFiltrar.setBackground(Color.WHITE);
		chckbxFiltrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxFiltrar.setBounds(317, 21, 197, 20);
		getContentPane().add(chckbxFiltrar);
		
		txtCodigo2 = new JTextField();
		txtCodigo2.setVisible(false);
		txtCodigo2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyReleasedTxtCodigo2(arg0);
			}
		});
		txtCodigo2.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		txtCodigo2.setColumns(10);
		txtCodigo2.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		txtCodigo2.setBackground(new Color(245, 245, 245));
		txtCodigo2.setBounds(123, 45, 391, 34);
		getContentPane().add(txtCodigo2);
		
		btnExportar = new JButton("EXPORTAR INVENTARIO");
		btnExportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExportar.setBorder(new LineBorder(new Color(138, 43, 226), 3, true));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnExportar(arg0);
			}
		});
		btnExportar.setForeground(new Color(138, 43, 226));
		btnExportar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExportar.setBackground(new Color(255, 255, 255));
		btnExportar.setBounds(523, 21, 231, 58);
		getContentPane().add(btnExportar);
		// tbProductos.getTableHeader().setResizingAllowed(false);
		tbProductos.getTableHeader().setReorderingAllowed(false);

		
		menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtCodigo, chckbxFiltrar, txtCodigo2, btnExportar}));
		
		mnCrearProducto = new JMenu("|Crear nuevo producto| ");
		mnCrearProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickedMnCrearProducto(arg0);
			}
		});
		mnCrearProducto.setForeground(new Color(30, 144, 255));
		mnCrearProducto.setBackground(SystemColor.control);
		mnCrearProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnCrearProducto);
		
		mnModificarProducto = new JMenu("|Modificar producto| ");
		mnModificarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnModificarProducto(e);
			}
		});
		mnModificarProducto.setForeground(new Color(50, 205, 50));
		mnModificarProducto.setBackground(SystemColor.control);
		mnModificarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnModificarProducto);
		
		mnEliminarProducto = new JMenu("|Eliminar producto| ");
		mnEliminarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedMnNewMenu_2(e);
			}
		});
		mnEliminarProducto.setForeground(new Color(240, 128, 128));
		mnEliminarProducto.setBackground(SystemColor.control);
		mnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnEliminarProducto);

		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		cargar();
		cargarBuscador();
	}
	
	public void cargar() {
		tb = this.tbProductos;
		tb.setRowHeight(30);
		tb.setModel(dtm);
		
		// CARGAR ATRIBUTOS EN TABLA
		cargarTabla("todos");
	}
	
	public void cargarTabla(String prod){
		limpiarTabla();
		
		String atribTodos = "";
		try {
			consulta.iniciar();
			rs = consulta.cargarConfiguraciones();
			rs.next();
			atribTodos = rs.getString("atributosprod");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar atributos: " + e);
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

        List<String> list = new ArrayList<String>();
        list.add("ID");
        list.add("C�DIGO");
        list.add("NOMBRE");
        list.add("DESCRIPCI�N");
		String[] parts = atribTodos.split(",");
		for (int x=0; x<parts.length; x++){
			if(parts[x].equals("marca"))
				list.add("MARCA");
			if(parts[x].equals("color"))
				list.add("COLOR");
			if(parts[x].equals("lote"))
				list.add("LOTE");
			if(parts[x].equals("laboratorio"))
				list.add("LAB");
			if(parts[x].equals("fvencimiento"))
				list.add("FECHA VENC.");
		}
		list.add("UNI MED");
		list.add("CATEGORIA");
		list.add("ALMAC�N");
		list.add("DISTRIBUIDOR");
		list.add("STOCK");
		list.add("PREC CO");
		list.add("% GAN");
		list.add("PREC VE");
		String[] columnas = list.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
		/*dtm.setColumnIdentifiers(new Object[] { "Codigo", "Producto", "Detalle","Categor�a", "Marca", "Color",
				"F. Vencimiento", "Uni. Medida", "Cantidad", "PrecioCompra", "PrecioVenta" });*/
		dtm.setColumnIdentifiers(columnas);
		
		
		try {
			consulta.iniciar();
			if(prod.equals("todos"))
				rs = consulta.cargarProductos();
			else{
				rs = consulta.cargarProductoParticular(prod);
			}

			while (rs.next()){
				
				if(rs.getInt("estado") == 1){
					List<String> listProds = new ArrayList<String>();
			        listProds.add(rs.getString("codproducto"));
			        listProds.add(rs.getString("codbarra"));
			        listProds.add(rs.getString("producto"));
			        listProds.add(rs.getString("detalles"));
			        for (int x=0; x<parts.length; x++){
						if(parts[x].equals("marca"))
							listProds.add(rs.getString("marca"));
						if(parts[x].equals("color"))
							listProds.add(rs.getString("color"));
						if(parts[x].equals("lote"))
							listProds.add(rs.getString("lote"));
						if(parts[x].equals("laboratorio"))
							listProds.add(rs.getString("laboratorio"));
						if(parts[x].equals("fvencimiento")){
							try {
								// En esta linea de c�digo estamos indicando el nuevo formato que queremos para nuestra fecha.
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								// Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
								String fechaOrdenada = formatter.format(rs.getDate("fechaVenc"));
								listProds.add(fechaOrdenada);
							} catch (Exception e) {
								listProds.add("");
							}
						}
					}
			        listProds.add(rs.getString("unimedida"));
			        listProds.add(rs.getString("categoria"));
			        listProds.add(rs.getString("almacen"));
			        
			        int iddistrib = rs.getInt("iddistrib");
			        try {
			        	 ResultSet rs2 = consulta.buscarDistribuidor(iddistrib);
			        	 rs2.next();
					     listProds.add(rs2.getString("nombre"));		        	 
					} catch (Exception e) {}
			        
			        listProds.add(rs.getString("cantidad"));
			        listProds.add(rs.getString("precioCo"));
			        listProds.add(rs.getString("ptjganancia"));
			        listProds.add(rs.getString("precioVe"));
			        
			        String[] columnasProds = listProds.toArray(new String[list.size()]); // CONVERTIR ARRAYLIST EN ARRAY
					dtm.addRow(columnasProds); // AGREGAMOS EL PRODUCTO A LA LISTA
				}				
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS2: " + e);
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

		ajustarAnchoColumnas();
	}
	
	public void cargarBuscador() {
		ac = new TextAutoCompleter(txtCodigo);
		consulta.iniciar();
		ResultSet rs = consulta.cargarProductos();
		ac.setMode(0);
		try {
			while (rs.next()) {
				ac.addItem(rs.getString("producto") + " " + rs.getString("detalles") + " " + rs.getString("marca") + " " + rs.getString("color") + " " + rs.getString("laboratorio") + " " + rs.getString("lote") + " * " + rs.getString("unimedida") + 
					"  -  (" + rs.getString("codproducto") + ")");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR al cargar buscador: " + e);
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
	
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	public void ajustarAnchoColumnas() {
		TableColumnModel tcm = tbProductos.getColumnModel(); // 
		tcm.getColumn(0).setPreferredWidth(anchoColumna(2)); // ID
		tcm.getColumn(1).setPreferredWidth(anchoColumna(5)); // C�digo
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // Producto
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15)); // Detalle
		
		for(int i=0; i<tbProductos.getColumnCount(); i++)
			if(tbProductos.getColumnName(i).equals("FECHA VENC."))
				tcm.getColumn(i).setPreferredWidth(anchoColumna(10)); // FECHA DE VENCIMIENTO
		
		/*DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
		tcr2.setHorizontalAlignment(SwingConstants.CENTER);
		tbProductos.getColumnModel().getColumn(5).setCellRenderer(tcr2);*/
	}
	
	public void selecionarProducto(String id) {
		int cantProductos = tbProductos.getRowCount();
		for (int i = 0; i < cantProductos; i++) {
			if (id.equals(tbProductos.getValueAt(i, 0))) {
				tbProductos.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	protected void mouseClickedMnCrearProducto(MouseEvent arg0) {
				
	}
	
	// De manera global
	
	protected void mouseClickedMnModificarProducto(MouseEvent e) {
		String codigoProducto = tbProductos.getValueAt(tb.getSelectedRow(), 0).toString();
		abrirModificarProducto(codigoProducto);
	}
	private void abrirModificarProducto(String idProd){
		
	}
	
	protected void mouseClickedMnNewMenu_2(MouseEvent e) {
		String codigoProducto = tbProductos.getValueAt(tb.getSelectedRow(), 0).toString();
		String producto = tbProductos.getValueAt(tb.getSelectedRow(), 2).toString();
		String descripcion = tbProductos.getValueAt(tb.getSelectedRow(), 3).toString();
		String marca = tbProductos.getValueAt(tb.getSelectedRow(), 4).toString();
		String color = tbProductos.getValueAt(tb.getSelectedRow(), 5).toString();
		
		int opc = JOptionPane.showConfirmDialog(null, "�Seguro de querer ELIMINAR el producto:\n" + producto + " " + descripcion + " " + marca + " " + color + " ?", "Confirmaci�n", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opc == 0) {
			
		}else{
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}
	protected void keyTypedTxtCodigo(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == (char) KeyEvent.VK_ENTER){
			if(txtCodigo.getText().length()==0)
				JOptionPane.showMessageDialog(null, "Escriba el producto que desee buscar");
			else{
				String producto = txtCodigo.getText();

				txtCodigo.setText("");
				
				String[] opciones = { "MODIFICAR", "ELIMINAR", "CANCELAR" };
				int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccione una opcion",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

				if (seleccion == 0) {// MODIFICAR
					try {
						int idProd = Integer.parseInt( producto.substring(producto.indexOf("(")+1, producto.indexOf(")")));
						JOptionPane.showMessageDialog(null, ""+idProd);
						abrirModificarProducto(""+idProd);
						
					} catch (Exception e2) {// AQUI ES SI LO QUE SE INGRESA ES UN C�DIGO DE BARRAS
						try {
							model.iniciar();
							rs = model.buscarProductoBarras(producto);
							rs.next();
							int idProd = rs.getInt("codproducto");
							abrirModificarProducto(""+idProd);
						} catch (Exception e3) {
							// TODO: handle exception
						}	finally {
							try {
								if (rs != null)
									rs.close();
								if (model != null)
									model.reset();
				            } catch (Exception ex) {
				            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
				            }
						}			
					}
				}
				
				if (seleccion == 1) {//  ELIMINAR
					try {
						int idProd = Integer.parseInt( producto.substring(producto.indexOf("(")+1, producto.indexOf(")")));
						
					} catch (Exception e2) {
						try {
							model.iniciar();
							rs = model.buscarProductoBarras(producto);
							rs.next();
							int idProd = rs.getInt("codproducto");
							
							model.reset();
							cargarBuscador();
							JOptionPane.showMessageDialog(null, "Inventario");
						} catch (Exception e3) {
							// TODO: handle exception
						}	finally {
							try {
								if (rs != null)
									rs.close();
								if (model != null)
									model.reset();
				            } catch (Exception ex) {
				            	JOptionPane.showMessageDialog(null, "Error al cerrar consulta");
				            }
						}
					}
				}
			}
		}
		
	}
	
	public void cerrarVentanas(){
		if(mp != null)
			mp.dispose();
		mp = null;
	}
	protected void keyReleasedTxtCodigo(KeyEvent arg0) {
	}
	protected void keyReleasedTxtCodigo2(KeyEvent arg0) {
		if(txtCodigo2.getText().length()==0){
			limpiarTabla();
		}
		else
			cargarTabla(txtCodigo2.getText());
	}
	protected void itemStateChangedChckbxFiltrar(ItemEvent arg0) {
		if(txtCodigo.isVisible()){
			txtCodigo.setVisible(false);
			txtCodigo2.setVisible(true);
		}
		else{
			cargarTabla("todos");
			txtCodigo.setVisible(true);
			txtCodigo2.setVisible(false);			
		}
	}
	
	private void limpiarTabla(){
		for (int i = 0; i < tbProductos.getRowCount(); i++) {
			dtm.removeRow(i);
			i -= 1;
		}
	}
	
	protected void actionPerformedBtnExportar(ActionEvent arg0) {
		Connection con = null;
		try {
			con = MySQLConexion.getConection();
			String prod = "";
			if (chckbxFiltrar.isSelected())
				prod = txtCodigo2.getText();
			else
				prod = txtCodigo.getText();
			
			Map parameters = new HashMap();
			parameters.put("prod", prod);

			new AbstractJasperReports().createReport(con, "rInventario.jasper", parameters);
			AbstractJasperReports.showViewer();
			
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro al cargar reporte: " + ex);
		}	
	}
}




